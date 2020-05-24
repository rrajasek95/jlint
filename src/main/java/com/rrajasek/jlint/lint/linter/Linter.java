package com.rrajasek.jlint.lint.linter;

import com.rrajasek.jlint.java.Java8Lexer;
import com.rrajasek.jlint.java.Java8Parser;
import com.rrajasek.jlint.lint.rules.Rule;
import com.rrajasek.jlint.lint.rules.RuleListener;
import com.rrajasek.jlint.lint.scope.DefinitionPhaseListener;
import com.rrajasek.jlint.lint.scope.ReferencePhaseListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Linter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<LintMessage> verify(String text) {
        return this.verifyWithoutProcessors(text);
    }

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


        DefinitionPhaseListener definitionPhaseListener = new DefinitionPhaseListener();
        walker.walk(definitionPhaseListener, sourceCode.getAst());

        ReferencePhaseListener referencePhaseListener = new ReferencePhaseListener(
                definitionPhaseListener.getScopes());
        walker.walk(referencePhaseListener, sourceCode.getAst());

        LinterListener linterListener = new LinterListener();
        configuredRules.put("accessor-pairs", "Blah");
        configuredRules.put("no-constant-condition", "Bluh");
        configuredRules.put("no-setter-return", "Blaaaaw");
        configuredRules.put("getter-return", "TEST");
        configuredRules.put("no-empty", "TEST");
        configuredRules.put("no-console-print", "SSSDSD");
        configuredRules.put("no-continue", "SDSDSDSDSDSD");
        configuredRules.put("no-unused-vars", "SDSDSDSDSDSDSSD");

        LinterTraversalContext linterTraversalContext = new LinterTraversalContextBase(definitionPhaseListener.getCurrentScope());

        List<LintMessage> messages = new ArrayList<>();
        for (Map.Entry<String, String> ruleEntry: configuredRules.entrySet()) {
            String ruleId = ruleEntry.getKey();
            Rule rule = ruleRegistry.getRule(ruleId);
            if (rule == null)
                continue;

            RuleContext ruleContext = new RuleContext(linterTraversalContext, ruleId, messages);
            List<RuleListener> listeners = rule.create(ruleContext);

            for (RuleListener listener : listeners) {
                linterListener.register(listener.getNodeType(), listener);
            }
        }
        walker.walk(linterListener, sourceCode.getAst());

        return messages;
    }
}
