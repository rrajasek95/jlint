package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.linter.RuleContext;

public abstract class RuleListenerBase implements RuleListener {
    protected String nodeType;
    protected RuleContext ruleContext;

    public RuleListenerBase(String nodeType, RuleContext ruleContext) {
        this.nodeType = nodeType;
        this.ruleContext = ruleContext;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }
}
