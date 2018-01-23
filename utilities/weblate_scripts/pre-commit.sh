#! /bin/bash

#   Copyright (c) 2017 Olivier Martin
#   This program is free software: you can redistribute it and/or modify
#   it under the terms of the GNU Affero General Public License as published by
#   the Free Software Foundation, either version 3 of the License, or
#   (at your option) any later version.
#
#   This program is distributed in the hope that it will be useful,
#   but WITHOUT ANY WARRANTY; without even the implied warranty of
#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#   GNU Affero General Public License for more details.
#
#   You should have received a copy of the GNU Affero General Public License
#   along with this program.  If not, see <http://www.gnu.org/licenses/>.

echo "[3] Generating .properties files"

TASK_JAVA_TRANSLATION_FOLDER="student/Translations/translations_java"
TASK_RUN_TRANSLATION_FOLDER="student/Translations/translations_run"
TASK_TRANSLATION="student/Translations"

shopt -s nullglob;

for i in utilities/Translations/*.po; do

    # Extract the file name (without path and extention)
    filename=$(basename "$i");
    extension="${filename##*.}";
    filename="${filename%.*}";
    code_LANG=$(basename "$filename") # for example : en, en_US, fr, ...

    #The main .properties for the LANG code_LANG
    properties_file="utilities/Translations/MessagesBundle_$code_LANG.properties"

    #For the translations of the run script
    mkdir -p "utilities/Translations/translations_run/$code_LANG/LC_MESSAGES"
    run_mo_file="utilities/Translations/translations_run/$code_LANG/LC_MESSAGES/run.mo"

    #Generates the .properties file for JAVA
    msgcat $i --properties-output --output-file=$properties_file;
    #Generates the .mo file for PYTHON
    msgfmt $i -o $run_mo_file

    git add $properties_file
    git add $run_mo_file

    mkdir -p \$i18n/
    cp $run_mo_file \$i18n/$code_LANG.mo
    git add \$i18n/$code_LANG.mo

    # We copy all translations in all tasks
    for taskfile in */task.yaml; do
	j=$(dirname $taskfile);

        #for Java
        mkdir -p $j/$TASK_JAVA_TRANSLATION_FOLDER/
        cp $properties_file $j/$TASK_JAVA_TRANSLATION_FOLDER/MessagesBundle_$code_LANG.properties
        git add $j/$TASK_JAVA_TRANSLATION_FOLDER/MessagesBundle_$code_LANG.properties

        #for run script
        cp -r utilities/Translations/translations_run $j/$TASK_TRANSLATION/;
        git add $j/$TASK_RUN_TRANSLATION_FOLDER

	#for tasks
	mkdir -p $j/\$i18n/
	cp $run_mo_file $j/\$i18n/$code_LANG.mo
	git add $j/\$i18n/$code_LANG.mo
    done
done

git commit -m "Update translations of all tasks"
git push
