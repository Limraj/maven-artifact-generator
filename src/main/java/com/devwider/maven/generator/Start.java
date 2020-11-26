package com.devwider.maven.generator;

import com.devwider.maven.generator.command.GenerateCommand;
import picocli.CommandLine;

public class Start {

    public static void main(String[] args) {

        CommandLine testCmd = new CommandLine(new GenerateCommand());
        testCmd.execute(args);
    }


}
