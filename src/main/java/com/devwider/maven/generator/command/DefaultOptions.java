package com.devwider.maven.generator.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import picocli.CommandLine.Option;

@Getter
@EqualsAndHashCode
public class DefaultOptions {

    @Option(names = {"-v", "--version"}, versionHelp = true)
    private boolean version;

    @Option(names = {"-h", "--help"}, usageHelp = true)
    private boolean help;
}
