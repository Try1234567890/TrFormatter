package com.github.jsf.dynamic_placeholders.components.impls.conditions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;
import com.github.jsf.scanners.IllegalComponentException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class IfFileExists extends Condition {
    public static final UName ID = new UName("if_file_exists", "if_file");
    public static final UName FILE_PATH = new UName("file_path", "file", "path", "fp");

    public IfFileExists(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public Boolean evaluate() {
        String filePath = as(FILE_PATH, String.class).orElseThrow(() ->
                new IllegalComponentException("The " + FILE_PATH + " parameter is required for " + ID + " condition."));
        return Files.exists(Paths.get(filePath));
    }
}