package com.github.jsf.scanners.beans;

public record Range(int start, int end) {

    public static final Range EMPTY = new Range(-1, -1);

}
