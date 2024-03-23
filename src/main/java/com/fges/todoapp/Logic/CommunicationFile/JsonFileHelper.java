package com.fges.todoapp.Logic.CommunicationFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileHelper {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode transformFileContent(String fileContent) {
        try {
            return mapper.readTree(fileContent);
        } catch (Exception e) {
            return JsonNodeFactory.instance.arrayNode();
        }
    }


    public static void ListJSON (String fileContent, Boolean done) {
        JsonNode actualObj = transformFileContent(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            for (JsonNode node : arrayNode) {
                if (done) {
                    if (node.toString().startsWith("Done: " , 1)) {
                        System.out.println("- " + node);
                    }
                } else {
                    System.out.println("- " + node);
                }
            }
        }
    }

    public static void InsertJSON (  Path filePath, String fileContent, String todo) {
        JsonNode actualObj = transformFileContent(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(todo);
        }


        try {
            Files.writeString(filePath, actualObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
