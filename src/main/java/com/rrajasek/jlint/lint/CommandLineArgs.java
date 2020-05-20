package com.rrajasek.jlint.lint;

import com.rrajasek.jlint.lint.engine.formatters.ResultFormat;
import picocli.CommandLine;

import java.io.File;

public class CommandLineArgs {

    @CommandLine.Parameters(paramLabel = "FILE", description = "one or more files to lint")
    File[] files;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @CommandLine.Option(names = "--max-warnings", description = "maximum number of warnings to display before JLint gives up")
    int maxWarnings = 10000;

    @CommandLine.Option(names = "--output-format", description = "Supported output formats: ${COMPLETION-CANDIDATES}")
    ResultFormat format = ResultFormat.JSON;
}
