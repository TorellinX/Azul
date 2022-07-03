# Readme for Azul team 07

## Dependencies

This project is based on Java 17.
For testing Junit 5.8.2 is set.

## Compile

### _command line_

``mvn compiler:compile``

### _IntelliJ_

maven -> Plugins -> compiler -> compiler:compile


## Build Application

### _command line_

``mvn jar:jar``

### _IntelliJ_

maven -> Plugings -> jar -> jar:jar

## Run the Application 

``java -jar target/DeepThought-1.0.jar``

## Release

Move the jar - file to the release folder and
rename if desired. Don't overwrite older releases.