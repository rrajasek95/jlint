package com.rrajasek.jlint.lint.linter;

public enum Severity {
    DISABLED(""),
    WARNING("Warning"),
    ERROR("Error");

    private String descriptor;

    Severity(String messageDescriptor) {
        this.descriptor = messageDescriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
