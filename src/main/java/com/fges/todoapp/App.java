package com.fges.todoapp;


import com.fges.todoapp.Presentation.CommandService;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {

    /**
     * Do not change this method
     */
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {

        return CommandService.main(args);

    }
}
