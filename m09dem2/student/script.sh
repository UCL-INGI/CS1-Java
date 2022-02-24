#!/bin/bash

if [ $(cat ./studentRep.txt) == $(cat ./student/answer.txt)]
then
	exit 0
else
	exit 1
fi
