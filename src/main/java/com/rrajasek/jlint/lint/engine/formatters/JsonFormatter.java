package com.rrajasek.jlint.lint.engine.formatters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rrajasek.jlint.lint.linter.LintResult;

public class JsonFormatter implements LintResultFormatter {
    private final GsonBuilder builder = new GsonBuilder();

    @Override
    public String formatResult(LintResult[] lintResult) {
        Gson gson = builder.create();
        return gson.toJson(lintResult);
    }
}
