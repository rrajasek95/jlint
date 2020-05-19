package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.java.Java8Lexer;
import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.LinterListener;
import com.rrajasek.jlint.lint.LinterTraversalContext;
import com.rrajasek.jlint.lint.LinterTraversalContextBase;
import com.rrajasek.jlint.lint.RuleContext;
import com.rrajasek.jlint.lint.rules.Rule;
import com.rrajasek.jlint.lint.rules.RuleListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Linter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<LintMessage> verify(String text) {
        return this.verifyWithoutProcessors(text);
    }

//    public void parseFile(String s) {
//        try {
//            //
//            CharStream antlrInputStream = CharStreams.fromFileName(s);
//            Java8Lexer java8Lexer = new Java8Lexer(antlrInputStream);
//            CommonTokenStream tokenStream = new CommonTokenStream(java8Lexer);
//            Java8Parser java8Parser = new Java8Parser(tokenStream);
//            ParseTree compilationUnit = java8Parser.compilationUnit();
//
//            ParseTreeWalker walker = new ParseTreeWalker();
//            LinterListener linterListener = new LinterListener();
//
//            walker.walk(linterListener, compilationUnit);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public ParseResult parse(String text) {
        CharStream textStream = CharStreams.fromString(text);
        Java8Lexer java8Lexer = new Java8Lexer(textStream);
        CommonTokenStream tokenStream = new CommonTokenStream(java8Lexer);
        Java8Parser java8Parser = new Java8Parser(tokenStream);
        ParseTree ast = java8Parser.compilationUnit();

        return new ParseResult(ast);
    }



    private List<LintMessage> verifyWithoutProcessors(String text) {

        ParseResult parseResult = parse(text);

        // Java thankfully does not have the notion of global variables
        // outside of a class, so this greatly simplifies analysis
        SourceCode sourceCode = new SourceCode(text, parseResult.getAst());

        return runRules(sourceCode, new HashMap<>());
    }

    private List<LintMessage> runRules(SourceCode sourceCode, Map<String, String> configuredRules) {
        RuleRegistry ruleRegistry = new RuleRegistry();
        ParseTreeWalker walker = new ParseTreeWalker();
        LinterListener linterListener = new LinterListener();
        configuredRules.put("accessor-pairs", "Blah");
        configuredRules.put("no-constant-condition", "Bluh");
        configuredRules.put("no-setter-return", "Blaaaaw");
        configuredRules.put("getter-return", "TEST");
        configuredRules.put("no-empty", "TEST");

        LinterTraversalContext linterTraversalContext = new LinterTraversalContextBase();
        for (Map.Entry<String, String> ruleEntry: configuredRules.entrySet()) {
            String ruleId = ruleEntry.getKey();
            Rule rule = ruleRegistry.getRule(ruleId);
            System.out.println(ruleId);
            if (rule == null)
                continue;

            RuleContext ruleContext = new RuleContext(linterTraversalContext, ruleId);
            List<RuleListener> listeners = rule.create(ruleContext);

            for (RuleListener listener : listeners) {
                linterListener.register(listener.getNodeType(), listener);
            }
        }
        walker.walk(linterListener, sourceCode.getAst());

        return linterListener.getProblems();
    }
}
