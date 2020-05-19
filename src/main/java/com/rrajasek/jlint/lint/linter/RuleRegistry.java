package com.rrajasek.jlint.lint.linter;

import com.rrajasek.jlint.lint.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class RuleRegistry {
    private final Map<String, Rule> rules;

    public RuleRegistry() {
        rules = new HashMap<>();
        rules.putAll(PredefinedRules.getPredefinedRules());
    }

    public void defineRule(String ruleId, Rule rule) {
        rules.put(ruleId, rule);
    }

    public Rule getRule(String ruleId) {
        return rules.get(ruleId);
    }

}
