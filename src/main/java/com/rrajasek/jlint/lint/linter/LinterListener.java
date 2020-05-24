package com.rrajasek.jlint.lint.linter;

import com.rrajasek.jlint.java.Java8ParserBaseListener;
import com.rrajasek.jlint.lint.rules.RuleListener;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinterListener extends Java8ParserBaseListener {
    private final HashMap<String, List<RuleListener>> registeredListeners = new HashMap<>();

    public void register(String selector, RuleListener listener) {
        String key = selector + "Context";
        if (listener.isExit()) {
            key = key + ":exit";
        }
        List<RuleListener> listeners = registeredListeners.computeIfAbsent(key, (k) -> new ArrayList<>());
        listeners.add(listener);
    }

    private void applyOnSelector(String selector, ParserRuleContext ctx) {
        List<RuleListener> listeners = registeredListeners.get(selector);
        if (listeners != null) {
            listeners.forEach(listener -> listener.act(ctx));
        }
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        // This listener listens for all node traversal events and delegates
        // the action to respective rule specific listeners that
        // match a specific selector
        String selector = ctx.getClass().getSimpleName();
        applyOnSelector(selector, ctx);
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        String selector = ctx.getClass().getSimpleName();
        applyOnSelector(selector + ":exit", ctx); // Apply only exit selectors
        super.exitEveryRule(ctx);
    }
}
