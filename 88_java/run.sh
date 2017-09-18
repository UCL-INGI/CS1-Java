#!/bin/sh
set -e

cp -R input/Q1.java Q1.java

javac -cp ".:input/lib/pythia.jar" Q1.java 2> output/q1.err
java -cp ".:input/lib/opencsv-2.3.jar:input/lib/pythia.jar" Q1 1> output/q1.out 2> output/q1.err