package com.rrajasek.jlint.lint.engine;

import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;
import com.rrajasek.jlint.lint.engine.formatters.UnixFormatter;
import com.rrajasek.jlint.lint.linter.LintInput;
import com.rrajasek.jlint.lint.linter.LintMessage;
import com.rrajasek.jlint.lint.linter.LintResult;
import com.rrajasek.jlint.lint.linter.Linter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CommandLineEngine {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final Linter linter;

    public CommandLineEngine() {
        linter = new Linter();
    }

    public LintResultFormatter loadFormatter(ResultFormat format) {
        return new UnixFormatter();
    }

    public List<LintResult> executeOnText(String text) {
        List<LintResult> results = new ArrayList<>();

        LintInput input = new LintInput();
        input.setText(text);
        results.add(verifyText(input));

        return results;
    }

    public LintResult[] lintFiles(File[] files) {
        List<LintResult> results = new ArrayList<>();
        for (File file: files) {
            try {
                String text = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset()) ;
                LintInput input = new LintInput();
                input.setText(text);
                LintResult res = verifyText(input);
                results.add(res);
            } catch (IOException ex) {
                logger.error("Failed to read file {}", file.toPath());
            }
        }

        return results.toArray(new LintResult[]{});
    }



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
