package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoContinueRule implements Rule{
    private static class NoContinueRuleListener extends RuleListenerBase {
        public NoContinueRuleListener(RuleContext ruleContext) {
            super("ContinueStatement", ruleContext);
        }

        private Location continueLocation(ParserRuleContext parserRuleContext) {
            Location location = new Location();
            location.line = parserRuleContext.start.getLine();
            location.column = parserRuleContext.start.getCharPositionInLine();

            return location;
        }
        @Override
        public void act(ParserRuleContext parserRuleContext) {
            System.out.println("Rule violation! NoContinue");
            LintReport report = new LintReport();

            report.setNodeType("ContinueStatement");
            report.setLocation(continueLocation(parserRuleContext));
            report.setMessage("No continue allowed");
            ruleContext.report(report);
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoContinueRuleListener(ruleContext));
        return listeners;
    }
}
