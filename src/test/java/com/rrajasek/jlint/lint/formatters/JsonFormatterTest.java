package com.rrajasek.jlint.lint.formatters;

import com.rrajasek.jlint.lint.linter.LintResult;
import com.rrajasek.jlint.lint.engine.formatters.JsonFormatter;
import com.rrajasek.jlint.lint.engine.formatters.LintResultFormatter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class JsonFormatterTest {

    public static final String NO_ERROR_LINT = "[{\"messages\":[],\"errorCount\":0,\"warningCount\":0}]";

    @Test
    public void testEmptyResultFormat() {
        LintResultFormatter jsonFormatter = new JsonFormatter();
        LintResult result = new LintResult(Collections.emptyList());
        String json = jsonFormatter.formatResult(new LintResult[]{ result });
        Assert.assertEquals(NO_ERROR_LINT, json);
    }
}
