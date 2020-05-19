package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.engine.formatters.JsonFormatter;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;

import java.util.ArrayList;
import java.util.List;

public class CommandLineEngine {
    private final Linter linter;

    public CommandLineEngine() {
        linter = new Linter();
    }

    public LintResultFormatter loadFormatter(ResultFormat format) {
        return new JsonFormatter();
    }

    public List<LintResult> executeOnText(String text) {
        List<LintResult> results = new ArrayList<>();

        LintResult res = new LintResult();
        LintInput input = new LintInput();
        input.setText(text);
        results.add(verifyText(input));

        return results;
    }

    public LintResult[] lintFiles() {
        LintResult res = new LintResult();
        return new LintResult[]{ res };
    }



    private LintResult verifyText(LintInput input) {
        String text = input.getText();
        linter.verify(text);
        return new LintResult();
    }


    private List<LintResult> executeOnFiles() {
        List<LintResult> results = new ArrayList<>();

        results.add(verifyText(new LintInput()));

        return results;
    }
}
