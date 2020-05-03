package com.rrajasek.jlint.lint.formatters;

import com.rrajasek.jlint.lint.engine.LintResult;
import com.rrajasek.jlint.lint.engine.formatters.JsonFormatter;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import org.junit.Assert;
import org.junit.Test;

public class JsonFormatterTest {

    public static final String NO_ERROR_LINT = "[{\"errorCount\":0,\"warningCount\":0}]";

    @Test
    public void testEmptyResultFormat() {
        LintResultFormatter jsonFormatter = new JsonFormatter();
        LintResult result = new LintResult();
        String json = jsonFormatter.formatResult(new LintResult[]{ result });
        Assert.assertEquals(NO_ERROR_LINT, json);
    }
}
