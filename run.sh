#!/bin/sh

rm -f main-src/classloadertest/*.class a-src/classloadertest/*.class

javac main-src/classloadertest/*.java
javac -cp main-src a-src/classloadertest/*.java

(cd main-src && jar cf ../main.jar classloadertest/*.class)
(cd a-src && jar cf ../a.jar classloadertest/*.class)

java -cp main.jar classloadertest.Main
