#!/bin/bash
if [ "$(cat $1)" == "$(cat $2)" ]
then
	exit 0
else
	exit 1
fi
