package com.rrajasek.jlint.lint.scope;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.java.Java8ParserBaseListener;
import com.rrajasek.jlint.lint.linter.Location;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class DefinitionPhaseListener extends Java8ParserBaseListener {
    ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();
    Scope currentScope = new Scope();

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        MethodScope methodScope = new MethodScope(ctx.methodHeader().methodDeclarator().Identifier().getText(), currentScope);
        currentScope.define(methodScope);
        saveScope(ctx, methodScope);
        this.currentScope = methodScope;

        super.enterMethodDeclaration(ctx);
    }

    private Location variableLocation(ParserRuleContext parserRuleContext) {
        Location location = new Location();
        location.line = parserRuleContext.start.getLine();
        location.column = parserRuleContext.start.getCharPositionInLine();

        return location;
    }

    @Override
    public void exitFormalParameter(Java8Parser.FormalParameterContext ctx) {
        defineVar(ctx.variableDeclaratorId().Identifier().getSymbol(), variableLocation(ctx));
        super.exitFormalParameter(ctx);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        super.exitMethodDeclaration(ctx);
    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        ClassScope classScope = new ClassScope(ctx.Identifier().getText(), currentScope);
        currentScope.define(classScope);
        saveScope(ctx, classScope);
        this.currentScope = classScope;

        super.enterNormalClassDeclaration(ctx);
    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
        super.exitNormalClassDeclaration(ctx);
    }

    @Override
    public void exitVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {
        defineVar(ctx.variableDeclaratorId().Identifier().getSymbol(), variableLocation(ctx));
        super.exitVariableDeclarator(ctx);
    }

    private void defineVar(Token symbol, Location location) {
        Variable var = new Variable(symbol.getText(), location);
        currentScope.define(var);
        currentScope.defineVar(var);
    }

    private void saveScope(ParserRuleContext ctx, Scope scope) {
        scopes.put(ctx, scope);
    }

    public ParseTreeProperty<Scope> getScopes() {
        return scopes;
    }

    public Scope getCurrentScope() {
        return currentScope;
    }
}
