package com.fges.todoapp.Presentation;

import com.fges.todoapp.Logic.Command.Insert.InsertCommand;
import com.fges.todoapp.Logic.Command.ListCommand;
import com.fges.todoapp.Logic.Const.TypoEnum;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommandService {



    public static int main(String[] args) throws IOException {
        Options cliOptions = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Set the todo as done");

        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);
        Path filePath = Paths.get(fileName);
        String fileContent = "";
        Boolean done = cmd.hasOption("d");

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        if (command.equals(TypoEnum.INSERT.getTypo())) {

            if (positionalArgs.size() < 2) {
                System.err.println("Missing TODO name");
                return 1;
            }

            String todo = positionalArgs.get(1);

            InsertCommand insertCommand = new InsertCommand(command, fileName, todo, fileContent, done);
            insertCommand.execute();

        } else if (command.equals(TypoEnum.LIST.getTypo())) {

            ListCommand listCommand = new ListCommand(command, fileName, done);
            listCommand.execute();

        } else {

            System.err.println("Unknown command: " + command);
            return 1;

        }

        return 0;
    }

}
