package com.devwider.maven.generator.command;

import lombok.Getter;
import lombok.ToString;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;


@Getter
@ToString
public class GenerateOptions extends DefaultOptions {

    @CommandLine.Option(names = {"-p", "--jars-path"})
    private Path jarsPath;

    @CommandLine.Option(names = {"-f", "--jar-file"})
    private File jar;

    @CommandLine.Option(names = {"-g", "--group-id"}, defaultValue = "com.serotonin")
    private String groupId;

    @CommandLine.Option(names = {"-V", "--artifact-version"}, defaultValue = "1.0.0")
    private String version;

    @CommandLine.Option(names = {"-P", "--artifact-packaging"}, defaultValue = "jar")
    private String packaging;
}
