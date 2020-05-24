package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.linter.RuleContext;

public abstract class RuleListenerBase implements RuleListener {
    protected String nodeType;
    protected RuleContext ruleContext;
    protected boolean isExit;

    public RuleListenerBase(String nodeType, RuleContext ruleContext, boolean isExit) {
        this.nodeType = nodeType;
        this.ruleContext = ruleContext;
        this.isExit = isExit;
    }

    public RuleListenerBase(String nodeType, RuleContext ruleContext) {
        this(nodeType, ruleContext, false);
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
