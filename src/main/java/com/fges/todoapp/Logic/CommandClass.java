package com.fges.todoapp.Logic;


import java.nio.file.Path;

public abstract class CommandClass {

    protected final String command;
    protected final String fileName;
    protected final Path path;

    public CommandClass(String command, String fileName) {
        this.command = command;
        this.fileName = fileName;
        this.path = Path.of(fileName);
    }

}
