package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class GetterReturnRule implements Rule {
    private static class GetterReturnRuleListener extends RuleListenerBase {

        public GetterReturnRuleListener(RuleContext ruleContext) {
            super("NormalClassDeclaration", ruleContext);
        }

        private Location functionDeclarationLocation(Java8Parser.MethodHeaderContext methodHeaderContext) {
            Location location = new Location();
            location.line = methodHeaderContext.start.getLine();
            location.column = methodHeaderContext.start.getCharPositionInLine();

            return location;
        }

        private void report(Java8Parser.MethodHeaderContext method, String violation) {
            LintReport report = new LintReport();
            report.setMessage(violation + "InNormalClassDeclaration");
            report.setLocation(functionDeclarationLocation(method));
            report.setNodeType(getNodeType());

            ruleContext.report(report);
        }

        private void reportList(List<Java8Parser.MethodHeaderContext> methods, String violation) {
            for (Java8Parser.MethodHeaderContext method : methods)
                report(method, violation);
        }

        private boolean isGetter(TerminalNode terminalNode) {
            return terminalNode.getText().startsWith("get");
        }

        private void checkList(List<Java8Parser.MethodHeaderContext> methods) {

            List<Java8Parser.MethodHeaderContext> getters = new ArrayList<>();
            for (Java8Parser.MethodHeaderContext method: methods) {
                TerminalNode identifier = method.methodDeclarator().Identifier();
                Java8Parser.ResultContext result = method.result();
                if (isGetter(identifier) && result.VOID() != null) {
                    getters.add(method);
                }
            }

            if (!getters.isEmpty()) {
                reportList(getters, "getterReturnRule");
            }
        }

        private void checkGetterReturns(Java8Parser.NormalClassDeclarationContext normalClassDeclarationContext) {
            List<Java8Parser.ClassBodyDeclarationContext> classBodyDeclarationContexts = normalClassDeclarationContext.classBody().classBodyDeclaration();
            List<Java8Parser.MethodHeaderContext> methods = new ArrayList<>();

            for (Java8Parser.ClassBodyDeclarationContext classBody : classBodyDeclarationContexts) {
                if (classBody.classMemberDeclaration() != null &&
                        classBody.classMemberDeclaration().methodDeclaration() != null) {
                    methods.add(classBody.classMemberDeclaration().methodDeclaration().methodHeader());
                }
            }

            checkList(methods);
        }

        @Override
        public void act(ParserRuleContext parserRuleContext) {
            checkGetterReturns((Java8Parser.NormalClassDeclarationContext) parserRuleContext);
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new GetterReturnRuleListener(ruleContext));
        return listeners;
    }
}
