package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NoConsolePrintRule implements Rule {
    private static class NoConsolePrintRuleListener extends RuleListenerBase {

        private Pattern SYS_OUT_PRINT_PATTERN = Pattern.compile("System\\.out\\.print(ln|f)?\\(.*\\)");

        public NoConsolePrintRuleListener(RuleContext ruleContext) {
            super("MethodInvocation", ruleContext);
        }

        private Location methodInvocationLocation(Java8Parser.MethodInvocationContext methodInvocationContext) {
            Location location = new Location();
            location.line = methodInvocationContext.start.getLine();
            location.column = methodInvocationContext.start.getCharPositionInLine();

            return location;
        }

        private void checkStatement(Java8Parser.MethodInvocationContext methodInvocationContext) {
            if (SYS_OUT_PRINT_PATTERN.matcher(methodInvocationContext.getText()).matches()) {
                LintReport report = new LintReport();
                report.setMessage("System.out.print* statements not allowed");
                report.setLocation(methodInvocationLocation(methodInvocationContext));
                report.setNodeType(getNodeType());

                ruleContext.report(report);
            }
        }

        @Override
        public void act(ParserRuleContext parserRuleContext) {
            checkStatement((Java8Parser.MethodInvocationContext) parserRuleContext);
        }
    }
    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoConsolePrintRuleListener(ruleContext));
        return listeners;
    }
}
