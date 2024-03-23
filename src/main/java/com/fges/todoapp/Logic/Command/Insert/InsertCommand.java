package com.fges.todoapp.Logic.Command.Insert;

import com.fges.todoapp.Logic.CommandClass;
import com.fges.todoapp.Logic.CommunicationFile.CsvFileHelper;
import com.fges.todoapp.Logic.CommunicationFile.JsonFileHelper;
import com.fges.todoapp.Logic.Const.TypoEnum;

public class InsertCommand extends CommandClass implements DoneParam  {

    private String todo;

    private final String fileContent;

    private final Boolean done;

    @Override
    public Boolean Done() {
        return this.done;
    }

    public InsertCommand(String command, String fileName, String todo, String fileContent, Boolean done) {
        super(command, fileName);
        this.todo = todo;
        this.fileContent = fileContent;
        this.done = done;
    }

      public void execute() {
        if (Done()) {
            this.todo = "Done: " + this.todo;
        }


          if (fileName.endsWith(TypoEnum.CSV.getTypo())) {
              CsvFileHelper.InsertCSV(path, fileContent, todo);
          } else if (fileName.endsWith(TypoEnum.JSON.getTypo())) {
              JsonFileHelper.InsertJSON(path, fileContent, todo);
          }
      }

}
