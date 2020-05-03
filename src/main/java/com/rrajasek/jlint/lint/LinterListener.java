package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.java.Java8ParserBaseListener;

public class LinterListener extends Java8ParserBaseListener {
    @Override
    public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        System.out.println(ctx.normalClassDeclaration().Identifier().getText());

        super.exitClassDeclaration(ctx);
    }
}
