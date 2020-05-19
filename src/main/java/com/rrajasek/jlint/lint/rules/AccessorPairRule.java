package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.linter.LintReport;
import com.rrajasek.jlint.lint.linter.Location;
import com.rrajasek.jlint.lint.linter.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class AccessorPairRule implements Rule {

    static class AccessorPairListener extends RuleListenerBase {
        private boolean isGetter(TerminalNode terminalNode) {
            return terminalNode.getText().startsWith("get");
        }

        private boolean isSetter(TerminalNode terminalNode) {
            return terminalNode.getText().startsWith("set");
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

            for (Java8Parser.MethodHeaderContext method: methods) {
                report(method, violation);
            }
        }

        private void checkList(List<Java8Parser.MethodHeaderContext> methods) {

            List<Java8Parser.MethodHeaderContext> getters = new ArrayList<>();
            List<Java8Parser.MethodHeaderContext> setters = new ArrayList<>();
            for (Java8Parser.MethodHeaderContext method: methods) {
                TerminalNode identifier = method.methodDeclarator().Identifier();
                if (isGetter(identifier)) {
                    getters.add(method);
                } else if (isSetter(identifier)) {
                    setters.add(method);
                }
            }

            if (getters.size() > setters.size()) {
                reportList(getters, "missingSetter");
            } else if (setters.size() > getters.size()) {
                reportList(setters, "missingGetter");
            }
        }

        public AccessorPairListener(RuleContext ruleContext) {
            super("NormalClassDeclaration", ruleContext);
        }

        public void checkClassBody(Java8Parser.NormalClassDeclarationContext normalClassDeclarationContext) {
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
            checkClassBody((Java8Parser.NormalClassDeclarationContext) parserRuleContext);
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new AccessorPairListener(ruleContext));

        return listeners;
    }
}
