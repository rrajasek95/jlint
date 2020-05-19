package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.engine.formatters.JsonFormatter;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;
import com.rrajasek.jlint.lint.linter.LintInput;
import com.rrajasek.jlint.lint.linter.LintMessage;
import com.rrajasek.jlint.lint.linter.LintResult;
import com.rrajasek.jlint.lint.linter.Linter;

import javax.xml.transform.Result;
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

        LintInput input = new LintInput();
        input.setText(text);
        results.add(verifyText(input));

        return results;
    }

//    public LintResult[] lintFiles() {
//        LintResult res = new LintResult();
//        return new LintResult[]{ res };
//    }



    private LintResult verifyText(LintInput input) {
        String text = input.getText();
        List<LintMessage> lintMessageList = linter.verify(text);
        return new LintResult(lintMessageList);
    }


    private List<LintResult> executeOnFiles() {
        List<LintResult> results = new ArrayList<>();

        results.add(verifyText(new LintInput()));

        return results;
    }
}
