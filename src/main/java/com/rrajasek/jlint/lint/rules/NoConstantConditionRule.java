package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoConstantConditionRule implements Rule {

    static class IfConstant extends RuleListenerBase {
        public IfConstant(String selector, RuleContext ruleContext) {
            super(selector, ruleContext);
        }

        private Location constantLocation(Java8Parser.ConditionalExpressionContext conditionalExpressionContext) {
            Location location = new Location();
            location.line = conditionalExpressionContext.start.getLine();
            location.column = conditionalExpressionContext.start.getCharPositionInLine();

            return location;
        }

        private void checkConditionConstant(Java8Parser.ExpressionContext context) {
            Java8Parser.ConditionalExpressionContext conditional = context.assignmentExpression().conditionalExpression();

            if (conditional != null) {
                boolean isConstant = "true".equals(conditional.getText()) || "false".equals(conditional.getText());
                if (isConstant) {
                    LintReport report = new LintReport();
                    report.setMessage("ConstantInConditionalExpression");
                    report.setLocation(constantLocation(conditional));
                    report.setNodeType(getNodeType());

                    ruleContext.report(report);
                }
            }
        }

        @Override
        public void act(ParserRuleContext parserRuleContext) {
            if (Java8Parser.IfThenStatementContext.class.equals(parserRuleContext.getClass())) {
                checkConditionConstant(((Java8Parser.IfThenStatementContext) parserRuleContext).expression());
            } else if (Java8Parser.IfThenElseStatementContext.class.equals(parserRuleContext.getClass())) {
                checkConditionConstant(((Java8Parser.IfThenElseStatementContext) parserRuleContext).expression());
            }
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new IfConstant("IfThenStatement", ruleContext));
        listeners.add(new IfConstant("IfThenElseStatement", ruleContext));
        return listeners;
    }
}
