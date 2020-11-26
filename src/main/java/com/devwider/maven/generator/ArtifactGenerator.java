package com.devwider.maven.generator;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class ArtifactGenerator {

    private static final String GROUP_ID = "com.serotonin";
    private static final String VERSION = "1.0.0";
    private static final String PACKAGING = "jar";

    public static void execute(Path path) {
        _executeCommands(_commands(_jars(path), GROUP_ID,
                VERSION, PACKAGING));
    }

    public static void execute(Path path, String groupId,
                               String version, String packaging) {
        _executeCommands(_commands(_jars(path), groupId, version, packaging));
    }

    public static void execute(File jar) {
        _executeCommands(_commands(Stream.of(jar).collect(Collectors.toList()),GROUP_ID,
                VERSION, PACKAGING));
    }

    public static void execute(File jar, String groupId,
                               String version, String packaging) {
        _executeCommands(_commands(Stream.of(jar).collect(Collectors.toList()),groupId,
                version,packaging));
    }

    public static String dependency(File jar) {
        return String.join("\n", _dependencies(Stream.of(jar).collect(Collectors.toList()), GROUP_ID,
                VERSION));
    }

    public static String dependencies(Path path) {
        return String.join("\n", _dependencies(_jars(path),GROUP_ID,
                VERSION));
    }

    private static List<String> _dependencies(List<File> jars, String groupId,
                                              String version) {
        List<String> deps = new ArrayList<>();
        deps.add("\n<dependencies>");
        for(File file: jars) {
            String dep = MessageFormat.format("        <dependency>\n" +
                    "            <groupId>{0}</groupId>\n" +
                    "            <artifactId>{1}</artifactId>\n" +
                    "            <version>{2}</version>\n" +
                    "        </dependency>", groupId,
                    file.getName().replace(".jar",""),
                    version);

            deps.add(dep);
        }
        deps.add("</dependencies>");
        return deps;
    }

    private static List<File> _jars(Path path) {
        return Stream.of(Objects.requireNonNull(path.toFile().listFiles()))
                .filter(a -> a.getName().contains(".jar"))
                .collect(Collectors.toList());
    }

    private static List<String> _commands(List<File> jars, String groupId,
                                          String version, String packaging) {
        List<String> commands = new ArrayList<>();
        for(File file: jars) {
            String command = MessageFormat.format("cd {0} && mvn install:install-file " +
                    "-Dfile={1} " +
                    "-DgroupId={2} " +
                    "-DartifactId={3} " +
                    "-Dversion={4} " +
                    "-Dpackaging={5} " +
                    "-DgeneratePom=true", file.getParent(), file.getName(), groupId
                    , file.getName().replace(".jar",""), version, packaging);

            commands.add(command);
        }
        return commands;
    }

    private static void _executeCommands(List<String> commands) {
        for(String command: commands) {
            try {
                _execute(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void _execute(String command) throws IOException {
        logger.info(command);
        String osName = System.getProperty("os.name");
        logger.info(osName);
        ProcessBuilder builder = osName.toLowerCase().contains("win") ?
                new ProcessBuilder("cmd.exe", "/c", command) :
                new ProcessBuilder("bash", "-c", command) ;

        builder.redirectErrorStream(true);
        Process p = builder.start();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                logger.info(line);
            }
        }
        p.destroy();
    }
}
