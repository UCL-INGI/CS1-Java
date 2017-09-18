#!/bin/sh
set -e

cp -R input/Coordinate.java Coordinate.java
cp -R input/EmptyStackException.java EmptyStackException.java
cp -R input/Stack.java Stack.java
cp -R input/StackIF.java StackIF.java
cp -R input/State.java State.java
cp -R input/Test.java Test.java

javac -encoding UTF-8 Test.java 2> output/q1.err
java Test 1> output/q1.out 2> output/q1.err

cp -R resultat.txt output/resultat.txt