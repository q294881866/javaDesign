package org.jiumao.example.utils.io;

import java.io.File;

@FunctionalInterface
public interface Command {

    void exec(File file);
}
