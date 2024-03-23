package com.fges.todoapp.Logic.Command;

import com.fges.todoapp.Logic.Command.Insert.DoneParam;
import com.fges.todoapp.Logic.CommandClass;
import com.fges.todoapp.Logic.CommunicationFile.CsvFileHelper;
import com.fges.todoapp.Logic.CommunicationFile.JsonFileHelper;
import com.fges.todoapp.Logic.Const.TypoEnum;

import java.io.IOException;
import java.nio.file.Files;

public class ListCommand extends CommandClass implements DoneParam {

    private final Boolean done;


    public ListCommand(String command, String fileName, Boolean done) {
        super(command, fileName);
        this.done = done;
    }


    private String getFileContent () {
        if (Files.exists(path)) {
            try {
                return Files.readString(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "";
    }


    public void execute() {
        String fileContent = getFileContent();

        if (fileName.endsWith(TypoEnum.CSV.getTypo())) {
            CsvFileHelper.ListCSV(fileContent, Done());
        } else if (fileName.endsWith(TypoEnum.JSON.getTypo())) {
            JsonFileHelper.ListJSON(fileContent, Done());
        }
    }

    @Override
    public Boolean Done() {
        return this.done;
    }
}
