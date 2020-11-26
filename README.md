# maven-artifact-generator
 Console program to generate maven artifacts in the local repository, and configure dependency for pom.xml, based on the path to the jars.
 
## help
java -jar file.jar -h

## use
### options
  - -f, --jar-file=\<jar>
  - -g, --group-id=\<groupId>
  - -h, --help
  - -p, --jars-path=\<jarsPath>
  - -P, --artifact-packaging=\<packaging>
  - -v, --version
  - -V, --artifact-version=\<version>
  
requires -p or -f and -g, default -V 1.0.0 and -P jar

### example
#### path jars
java -jar file.jar -p path_to_jars -g com.test -V 1.2.3 -P jar
#### jar
java -jar file.jar -f file_jar -g com.test -V 1.2.3 -P jar

This will generate an artifact in the local maven repository, and generate dependecies for pom.xml in gen.log

## build
mvn clean install

## support
windows, macOS X(unix), equires an installed maven

## system testing

### windows 7:
- maven 3.6.1
- java version "1.8.0_162"
- Java(TM) SE Runtime Environment (build 1.8.0_162-b12)
- Java HotSpot(TM) 64-Bit Server VM (build 25.162-b12, mixed mode)

### macOS X:
- maven 3.6.3
- openjdk version "14.0.1" 2020-04-14
- OpenJDK Runtime Environment (build 14.0.1+7)
- OpenJDK 64-Bit Server VM (build 14.0.1+7, mixed mode, sharing)


