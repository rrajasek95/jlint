package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.rules.AccessorPairRule;
import com.rrajasek.jlint.lint.rules.NoConstantConditionRule;
import com.rrajasek.jlint.lint.rules.NoSetterReturnRule;
import com.rrajasek.jlint.lint.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class PredefinedRules {
    private static Map<String, Rule> predefinedRules = new HashMap<>();

    static {
        predefinedRules.put("accessor-pairs", new AccessorPairRule());
        predefinedRules.put("no-constant-condition", new NoConstantConditionRule());
        predefinedRules.put("no-setter-return", new NoSetterReturnRule());
    }
    public static Map<String, Rule> getPredefinedRules() {
        return predefinedRules;
    }
}
