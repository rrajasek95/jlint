package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoEmptyRule implements Rule {

    private static class NoEmptyRuleListener extends RuleListenerBase {

        public NoEmptyRuleListener(RuleContext ruleContext) {
            super("Block", ruleContext);
        }

        private Location emptyBlockLocation(Java8Parser.BlockContext blockContext) {
            Location location = new Location();
            location.line = blockContext.start.getLine();
            location.column = blockContext.start.getCharPositionInLine();

            return location;
        }

        public void checkBlock(Java8Parser.BlockContext blockContext) {
            if (blockContext.blockStatements() == null) {
                LintReport report = new LintReport();
                report.setMessage("System.out.print* statements not allowed");
                report.setLocation(emptyBlockLocation(blockContext));
                report.setNodeType(getNodeType());
                ruleContext.report(report);
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
