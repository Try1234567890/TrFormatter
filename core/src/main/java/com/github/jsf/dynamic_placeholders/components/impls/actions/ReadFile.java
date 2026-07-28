package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class ReadFile extends Action {
    public static final UName ID = new UName("read_file");
    public static final UName FILE_PATH = new UName("file_path", "file", "path", "fp");
    public static final UName ERROR_SHOW = new UName("error_show", "error", "es");

    public ReadFile(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        Path path = as(FILE_PATH, String.class).map(Paths::get).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + FILE_PATH + " is needed for \"" + ID + "\" action"));
        ErrorShow errorShow = as(ERROR_SHOW, String.class).map(ErrorShow::of).orElse(ErrorShow.EXCEPTION);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            errorShow.show("Error while reading file at " + path + " --> " + e.getMessage());
            return "";
        }
    }

    public enum ErrorShow {
        NONE {
            @Override
            public void show(String message) { /* Do nothing */ }
        },
        EXCEPTION {
            @Override
            public void show(String message) {
                throw new IllegalArgumentException(message);
            }
        },
        LOG_CONSOLE {
            @Override
            public void show(String message) {
                LOGGER.severe(message);
            }
        };

        public static final Logger LOGGER = Logger.getLogger(ErrorShow.class.getName());

        public abstract void show(String message);

        public static ErrorShow of(String str) {
            for (ErrorShow errorShow : values()) {
                if (errorShow.name().equalsIgnoreCase(str)) {
                    return errorShow;
                }
            }
            return EXCEPTION;
        }


    }
}
