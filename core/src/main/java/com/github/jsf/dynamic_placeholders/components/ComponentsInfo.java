package com.github.jsf.dynamic_placeholders.components;

import com.github.jsf.scanners.beans.Range;

import java.util.List;

public record ComponentsInfo(List<Parameter<?>> parameters,
                             Range range) {
}
