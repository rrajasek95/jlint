package com.rrajasek.jlint.lint.engine.formatters;

import com.rrajasek.jlint.lint.linter.LintResult;

public interface LintResultFormatter {
    String formatResult(LintResult[] lintResult);
}
