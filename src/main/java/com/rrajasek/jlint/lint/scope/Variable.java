package com.rrajasek.jlint.lint.scope;

import com.rrajasek.jlint.lint.linter.Location;

public class Variable extends NamedScope {
    private boolean used = false;
    private Location location;

    public boolean isUsed() {
        return used;
    }

    public void markAsUsed() {
        used = true;
    }

    public Variable(String text, Location location) {
        super("variable", text);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
