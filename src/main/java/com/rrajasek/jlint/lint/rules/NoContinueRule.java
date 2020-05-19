package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoContinueRule implements Rule{
    private static class NoContinueRuleListener extends RuleListenerBase {
        public NoContinueRuleListener(RuleContext ruleContext) {
            super("ContinueStatement", ruleContext);
        }

        @Override
        public void act(ParserRuleContext parserRuleContext) {
            System.out.println("Rule violation! NoContinue");
            ruleContext.report();
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoContinueRuleListener(ruleContext));
        return listeners;
    }
}
