package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.RuleContext;
import com.rrajasek.jlint.lint.scope.Scope;
import com.rrajasek.jlint.lint.scope.Variable;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class NoUnusedVariablesRule implements Rule {

    private static class NoUnusedVariablesRuleListener extends RuleListenerBase {
        public NoUnusedVariablesRuleListener(RuleContext context) {

            super("CompilationUnit", context, true);
        }


        @Override
        public void act(ParserRuleContext parserRuleContext) {
            List<Variable> unusedVars = collectUnusedVariables(ruleContext.getScope(), new ArrayList<>());
            for (Variable var : unusedVars) {
                LintReport lintReport = new LintReport();
                lintReport.setMessage("Unused variable " + var.getIdentifier());
                lintReport.setLocation(var.getLocation());
                ruleContext.report(lintReport);
            }
        }

        private List<Variable> collectUnusedVariables(Scope scope, List<Variable> unusedVariables) {
            List<Variable> variables = scope.getVariables();
            for (Variable variable : variables) {
                if (variable.isUsed()) {
                    continue;
                }
                unusedVariables.add(variable);

            }

            for (Scope childScope : scope.getChildScopes()) {
                collectUnusedVariables(childScope, unusedVariables);
            }

            return unusedVariables;
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoUnusedVariablesRuleListener(ruleContext));
        return listeners;
    }
}
