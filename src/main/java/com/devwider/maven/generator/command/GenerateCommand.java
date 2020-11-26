package com.devwider.maven.generator.command;


import com.devwider.maven.generator.ArtifactGenerator;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;

@Log4j2
@Getter
@ToString
@CommandLine.Command(name = "mvn-gen")
public class GenerateCommand extends GenerateOptions implements Runnable {

    @Override
    public void run() {
        Path path = getJarsPath();
        File file = getJar();
        if(path != null) {
            logger.info(path.toAbsolutePath());
            ArtifactGenerator.execute(path);
            logger.info(ArtifactGenerator.dependencies(path));
        }
        if(file != null) {
            logger.info(file.getAbsolutePath());
            ArtifactGenerator.execute(file);
            logger.info(ArtifactGenerator.dependency(file));
        }
    }

}
