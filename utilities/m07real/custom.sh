#!/bin/bash

for file in student/*.java; do
	CHARSET=$(file -b --mime-encoding ${file})
	iconv -f ${CHARSET} -t utf-8 "$file" -o "abc.out"
	if [ $? -eq 0 ];	then
		cat abc.out > "$file"
		rm abc.out
	fi
done
exit 0
