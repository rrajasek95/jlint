package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoEmptyRule implements Rule {

    private static class NoEmptyRuleListener extends RuleListenerBase {

        public NoEmptyRuleListener(RuleContext ruleContext) {
            super("Block", ruleContext);
        }

        public void checkBlock(Java8Parser.BlockContext blockContext) {
            if (blockContext.blockStatements() == null) {
            }
        }

        @Override
        public void act(ParserRuleContext parserRuleContext) {
            checkBlock((Java8Parser.BlockContext) parserRuleContext);
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoEmptyRuleListener(ruleContext));
        return listeners;
    }
}
