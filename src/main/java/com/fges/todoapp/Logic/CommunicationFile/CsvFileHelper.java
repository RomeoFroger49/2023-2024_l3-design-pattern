package com.fges.todoapp.Logic.CommunicationFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CsvFileHelper {
    public static void InsertCSV(Path filePath, String fileContent, String todo) {

        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todo;

        try {
            Files.writeString(filePath, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ListCSV(String fileContent, Boolean done) {
        if (fileContent.isEmpty()) {
            return;
        }

        System.out.println(Arrays.stream(fileContent.split("\n"))
                .filter(todo -> !done || todo.startsWith("Done: "))
                .map(todo -> "- " + todo)
                .collect(Collectors.joining("\n"))
        );
    }
}
