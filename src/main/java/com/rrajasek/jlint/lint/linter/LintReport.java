package com.rrajasek.jlint.lint.linter;

public class LintReport {

    private String message;
    private Location location;
    private String nodeType;

    public void setLocation(Location location) {
        this.location = location;
    }

    public Severity getSeverity() {
        return null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Location getLocation() {
        return location;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return null;
    }
}
