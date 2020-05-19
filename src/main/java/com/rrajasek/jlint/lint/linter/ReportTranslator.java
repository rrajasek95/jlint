package com.rrajasek.jlint.lint.linter;

public class ReportTranslator {
    private String ruleId;

    public ReportTranslator(String ruleId) {
        this.ruleId = ruleId;
    }

    public LintMessage createLintMessage(LintReport report) {
        return new LintMessage(ruleId,
                report.getSeverity(),
                report.getMessage(),
                report.getLocation(),
                report.getNodeType()
                );
    }
}
