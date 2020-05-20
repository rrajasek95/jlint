package com.rrajasek.jlint.lint.engine.formatters;

import com.rrajasek.jlint.lint.linter.LintMessage;
import com.rrajasek.jlint.lint.linter.LintResult;

public class UnixFormatter implements LintResultFormatter {

    @Override
    public String formatResult(LintResult[] lintResults) {
        int total = 0;

        StringBuilder outputBuilder = new StringBuilder();
        for (LintResult result: lintResults) {
            total += result.getMessages().length;
            for (LintMessage message: result.getMessages()) {
                outputBuilder.append(result.getFilePath()).append(":");
                outputBuilder.append(message.getLine()).append(":");
                outputBuilder.append(message.getColumn()).append(":");
                outputBuilder.append(" ").append(message.getMessage()).append(" ");
                outputBuilder
                        .append("[")
//                        .append(message.getSeverity().getDescriptor())
                        .append(message.getRuleId())
                        .append("]");
                outputBuilder.append("\n");
            }
        }

        if (total > 0) {
            outputBuilder.append("\n").append(total).append(" problem").append(total != 1 ? "s": "");
        }

        return outputBuilder.toString();
    }
}
