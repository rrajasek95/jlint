package com.rrajasek.jlint.lint.rules;

import org.antlr.v4.runtime.ParserRuleContext;

public interface RuleListener {
    String getNodeType();
    void act(ParserRuleContext parserRuleContext);
}
