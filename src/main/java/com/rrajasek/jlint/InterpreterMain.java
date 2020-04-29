package com.rrajasek.jlint;

import com.rrajasek.jlint.interpreter.InterpreterLexer;
import com.rrajasek.jlint.interpreter.InterpreterParser;
import com.rrajasek.jlint.interpreter.InterpreterVisitorImpl;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Arrays;

public class InterpreterMain {
    public static void main(String[] args) {
        InterpreterMain main = new InterpreterMain();
        System.out.println(main.calculate("1 - 2.5"));
    }

    private Double calculate(String source) {
        return compile(CharStreams.fromString(source));
    }

    private Double compile(CharStream source) {
        InterpreterLexer lexer = new InterpreterLexer(source);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        InterpreterParser parser = new InterpreterParser(tokenStream);
        ParseTree tree = parser.operation();

        InterpreterVisitorImpl visitor = new InterpreterVisitorImpl();
        return visitor.visit(tree);
    }
}
