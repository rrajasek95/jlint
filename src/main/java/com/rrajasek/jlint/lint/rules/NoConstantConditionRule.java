package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoConstantConditionRule implements Rule {

    static class IfConstant extends RuleListenerBase {
        public IfConstant(String selector, RuleContext ruleContext) {
            super(selector, ruleContext);
        }

        private void checkConditionConstant(Java8Parser.ExpressionContext context) {
            Java8Parser.ConditionalExpressionContext conditional = context.assignmentExpression().conditionalExpression();

            if (conditional != null) {
                boolean isConstant = "true".equals(conditional.getText()) || "false".equals(conditional.getText());
                if (isConstant) {
                    System.out.println("violation!");
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
