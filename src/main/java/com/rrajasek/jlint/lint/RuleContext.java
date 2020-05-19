package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.lint.engine.LintMessage;
import com.rrajasek.jlint.lint.engine.SourceCode;

public class RuleContext implements LinterTraversalContext {
    private final String ruleId;
    private final LinterTraversalContext ctx;
    private final SourceCode sourceCode;
    public RuleContext(LinterTraversalContext sharedContext, String ruleId) {
        ctx = sharedContext;
        this.ruleId = ruleId;
        this.sourceCode = ctx.getSourceCode();
    }

    public void report() {
        LintMessage message = new LintMessage();
        message.setRuleId(ruleId);
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

}
