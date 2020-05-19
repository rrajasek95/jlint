package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.lint.engine.CommandLineEngine;
import com.rrajasek.jlint.lint.linter.LintResult;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JLint {
    private static LintOptions parseOptions() {
        return new LintOptions();
    }

    private static class LintIssueCount {
        private int errorCount;
        private int warningCount;

        public LintIssueCount(int errorCount, int warningCount) {
            this.errorCount = errorCount;
            this.warningCount = warningCount;
        }

        public int getErrorCount() {
            return errorCount;
        }

        public int getWarningCount() {
            return warningCount;
        }
    }

    private static class LintOptions {
        private int maxWarnings = 1000000;

        public int getMaxWarnings() {
            return maxWarnings;
        }
    }

    private static boolean outputResults(CommandLineEngine engine, LintResult[] results,
                               ResultFormat format, Path outputFilePath) {
        LintResultFormatter formatter = engine.loadFormatter(format);
        String output = formatter.formatResult(results);
        try {
            Files.write(outputFilePath, output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static LintIssueCount countLintIssues(LintResult[] results) {
        int totalErrors = 0;
        int totalWarnings = 0;

        for (LintResult result : results) {
            totalErrors += result.getErrorCount();
            totalWarnings += result.getWarningCount();
        }

        return new LintIssueCount(totalErrors, totalWarnings);
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(JLint.class);
        LintOptions options = parseOptions();
        CommandLineEngine engine = new CommandLineEngine();
//        LintResult[] results = engine.lintFiles();

        List<LintResult> resultsList = engine.executeOnText("public class Test { public bool getName() { continue; return true; } public void setName() { return; } } ");
        LintResult[] results = resultsList.toArray(new LintResult[] {});

        if (outputResults(engine, results, ResultFormat.JSON, Paths.get("results.json"))) {
            LintIssueCount counts = countLintIssues(results);

            if (counts.errorCount == 0 && counts.warningCount > options.getMaxWarnings()) {
                logger.error("JLint found too many warnings: {}", options.getMaxWarnings());
            }
        }
    }
}
