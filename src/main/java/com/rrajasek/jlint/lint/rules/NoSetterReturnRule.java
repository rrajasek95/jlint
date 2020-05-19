package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.RuleContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class NoSetterReturnRule implements Rule {
    class NoSetterReturnListener extends RuleListenerBase {

        public NoSetterReturnListener(RuleContext ruleContext) {
            super("NormalClassDeclaration", ruleContext);
        }

        private void reportList(List<Java8Parser.MethodHeaderContext> methods, String violation) {
            System.out.println("Rule violation!");
            this.ruleContext.report();
        }

        private boolean isSetter(TerminalNode terminalNode) {
            return terminalNode.getText().startsWith("set");
        }

        private void checkList(List<Java8Parser.MethodHeaderContext> methods) {

            List<Java8Parser.MethodHeaderContext> getters = new ArrayList<>();
            List<Java8Parser.MethodHeaderContext> setters = new ArrayList<>();
            for (Java8Parser.MethodHeaderContext method: methods) {
                TerminalNode identifier = method.methodDeclarator().Identifier();
                Java8Parser.ResultContext result = method.result();
                if (isSetter(identifier) && result.VOID() == null) {
                    setters.add(method);
                }
            }

            if (!setters.isEmpty()) {
                reportList(setters, "noSetterReturn");
            }
        }

        private void checkSetterReturns(Java8Parser.NormalClassDeclarationContext normalClassDeclarationContext) {
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
            System.out.println("NoSetterReturn");
            checkSetterReturns((Java8Parser.NormalClassDeclarationContext) parserRuleContext);
        }
    }

    @Override
    public List<RuleListener> create(RuleContext ruleContext) {
        List<RuleListener> listeners = new ArrayList<>();
        listeners.add(new NoSetterReturnListener(ruleContext));
        return listeners;
    }
}
