package com.rrajasek.jlint.lint.linter;

import java.util.ArrayList;
import java.util.List;

public class RuleContext implements LinterTraversalContext {
    private final String ruleId;
    private final LinterTraversalContext ctx;
    private final SourceCode sourceCode;
    private final List<LintMessage> lintMessageList;
    private ReportTranslator translator;

    public RuleContext(LinterTraversalContext sharedContext, String ruleId, List<LintMessage> messageList) {
        ctx = sharedContext;
        this.ruleId = ruleId;
        this.sourceCode = ctx.getSourceCode();
        this.lintMessageList = messageList;
    }

    public void report(LintReport report) {
        if (translator == null)
            translator = new ReportTranslator(ruleId);
        LintMessage message = translator.createLintMessage(report);
        lintMessageList.add(message);
    }

    /* Decorated methods */
    @Override
    public void getAncestors() {
        ctx.getAncestors();
    }

    @Override
    public void getDeclaredVariables() {
        ctx.getDeclaredVariables();
    }

    @Override
    public void markVariableAsUsed(String name) {
        ctx.markVariableAsUsed(name);
    }

    @Override
    public void getScope() {
        ctx.getScope();
    }

    @Override
    public SourceCode getSourceCode() {
        return ctx.getSourceCode();
    }

    public List<LintMessage> getLintMessageList() {
        return lintMessageList;
    }
}
