package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteFile extends Action {
    public static final UName ID = new UName("write_file");
    public static final UName FILE_PATH = new UName("file_path", "file", "path", "fp");
    public static final UName CONTENT = new UName("content", "text", "c");
    public static final UName APPEND = new UName("append", "app", "a");

    public WriteFile(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        Path path = as(FILE_PATH, String.class).map(Paths::get).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + FILE_PATH + " is needed for \"" + ID + "\" action"));
        String content = as(CONTENT, String.class).orElse("");
        boolean append = as(APPEND, Boolean.class).orElse(false);

        try {
            if (append) {
                Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            return content;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while writing file at " + path + " --> " + e.getMessage(), e);
        }
    }
}