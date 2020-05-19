package com.rrajasek.jlint.lint.rules;

import com.rrajasek.jlint.lint.linter.RuleContext;

import java.util.List;

public interface Rule {
    List<RuleListener> create(RuleContext ruleContext);
}
