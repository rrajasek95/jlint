package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.java.Java8ParserBaseListener;
import com.rrajasek.jlint.lint.engine.LintMessage;
import com.rrajasek.jlint.lint.rules.RuleListener;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinterListener extends Java8ParserBaseListener {
    // This listener is doing double duty as an emitter and listener

    final List<LintMessage> problems = new ArrayList<>();

    private HashMap<String, List<RuleListener>> registeredListeners = new HashMap<>();

    public void register(String selector, RuleListener listener) {
        System.out.println("Registering " + listener.getNodeType());
        List<RuleListener> listeners = registeredListeners.computeIfAbsent(selector + "Context", (k) -> new ArrayList<>());
        listeners.add(listener);
    }

    private void applyOnSelector(String selector, ParserRuleContext ctx) {
        List<RuleListener> listeners = registeredListeners.get(selector);
        if (listeners != null) {
            System.out.println(selector);
            listeners.forEach(listener -> listener.act(ctx));
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        String selector = ctx.getClass().getSimpleName();
        applyOnSelector(selector, ctx);
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    public List<LintMessage> getProblems() {
        return problems;
    }
}
