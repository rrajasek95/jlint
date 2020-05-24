package com.rrajasek.jlint.lint.scope;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.java.Java8ParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ReferencePhaseListener extends Java8ParserBaseListener {
    private ParseTreeProperty<Scope> scopes;
    private Scope currentScope;

    public ReferencePhaseListener(ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public void enterExpressionName(Java8Parser.ExpressionNameContext ctx) {

        NamedScope namedScope = currentScope.resolve(ctx.Identifier().getText());
        if (namedScope != null && "variable".equals(namedScope.getType())) {
            ((Variable) namedScope).markAsUsed();
        }
        super.enterExpressionName(ctx);
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        currentScope = scopes.get(ctx);
        super.enterMethodDeclaration(ctx);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        super.exitMethodDeclaration(ctx);
    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        currentScope = scopes.get(ctx);
        super.enterNormalClassDeclaration(ctx);
    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        super.exitNormalClassDeclaration(ctx);
    }
}
