#!/bin/sh
set -e

cp -R input/*.java .
cp -R input/junit-4.11.jar .
cp -R input/hamcrest-all-1.3.jar .


javac  -encoding UTF-8 -cp "./hamcrest-all-1.3.jar:./junit-4.11.jar" *.java 2> output/q1.err

java -cp "./hamcrest-all-1.3.jar:./junit-4.11.jar:." TestRunner 1> output/resultat.txt 2> output/q1.err


