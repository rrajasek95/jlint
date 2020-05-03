package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.java.Java8Lexer;
import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.LinterListener;
import com.rrajasek.jlint.lint.engine.formatters.JsonFormatter;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class CommandLineEngine {
    public LintResultFormatter loadFormatter(ResultFormat format) {
        return new JsonFormatter();
    }

    public LintResult[] lintText() {
        LintResult res = new LintResult();
        return new LintResult[]{ res };
    }

    public LintResult[] lintFiles() {
        LintResult res = new LintResult();
        return new LintResult[]{ res };
    }

    public void parseFile(String s) {
        try {
            //
            CharStream antlrInputStream = CharStreams.fromFileName(s);
            Java8Lexer java8Lexer = new Java8Lexer(antlrInputStream);
            CommonTokenStream tokenStream = new CommonTokenStream(java8Lexer);
            Java8Parser java8Parser = new Java8Parser(tokenStream);
            ParseTree compilationUnit = java8Parser.compilationUnit();

            ParseTreeWalker walker = new ParseTreeWalker();
            LinterListener linterListener = new LinterListener();

            walker.walk(linterListener, compilationUnit);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
