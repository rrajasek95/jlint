package com.rrajasek.jlint.lint.linter;

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
        predefinedRules.put("no-continue", new NoContinueRule());
        predefinedRules.put("no-console-print", new NoConsolePrintRule());
    }
    public static Map<String, Rule> getPredefinedRules() {
        return predefinedRules;
    }
}
