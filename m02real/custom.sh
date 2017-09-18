#!/bin/bash

for file in student/*.java; do
	CHARSET=$(file -b --mime-encoding ${file})
    if [ "${CHARSET}" == "unknown-8bit" ];	then
    	iconv -f WINDOWS-1252 -t utf-8  "$file" -o "abc.out"
    else
		iconv -f ${CHARSET} -t utf-8 "$file" -o "abc.out"
    fi
	#enca -L "french" "$file" -x utf8 > abc.out
    if [ $? -eq 0 ];	then
		cat abc.out > "$file"
		rm abc.out
	fi
done
exit 0
