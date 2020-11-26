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
        String version = getVersion();
        String groupId = getGroupId();
        String packaging = getPackaging();
        if(path != null) {
            logger.info(path.toAbsolutePath());
            ArtifactGenerator.execute(path, groupId,version,packaging);
            logger.info(ArtifactGenerator.dependencies(path, groupId, version));
        }
        if(file != null) {
            logger.info(file.getAbsolutePath());
            ArtifactGenerator.execute(file, groupId,version,packaging);
            logger.info(ArtifactGenerator.dependency(file, groupId, version));
        }
    }

}
