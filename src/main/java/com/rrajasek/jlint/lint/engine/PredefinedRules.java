package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.rules.*;

import java.util.HashMap;
import java.util.Map;

public class PredefinedRules {
    private static Map<String, Rule> predefinedRules = new HashMap<>();

    static {
        predefinedRules.put("accessor-pairs", new AccessorPairRule());
        predefinedRules.put("no-constant-condition", new NoConstantConditionRule());
        predefinedRules.put("no-setter-return", new NoSetterReturnRule());
        predefinedRules.put("getter-return", new GetterReturnRule());
        predefinedRules.put("no-empty", new NoEmptyRule());
    }
    public static Map<String, Rule> getPredefinedRules() {
        return predefinedRules;
    }
}
