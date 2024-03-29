#! /bin/bash
EXERCICE="M1EC13"
CODELITTERAL="::\n\n"
JAVAC="javac -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar"
JAVA="run_student --time 20 java -ea -cp .:./student:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar"

# On met la machine en UTF-8
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export LANGUAGE=en_US.UTF-8

parsetemplate -o student/M1EC13Stu.java student/M1EC13Vide.java

# On compile la tâche et on récupère le résultat dans un fichier
${JAVAC} src/${EXERCICE}.java 2> logOther.out
${JAVAC} src/Correction.java 2> log.out



OUTPUT=$(cat log.out)

OUTCORR=""

OUTOTHER=$(cat logOther.out)
ERREURENSEIGNANT=0
MESSAGEENSEIGNANT=""
if [ "$OUTCORR" != "" ];	then
	# feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans un fichier puis en le lisant.
	# sed permet d'indenter chaque ligne de son input avec une tabulation
	OUTCORR=$(echo "$OUTCORR" | sed -e 's/^/\t/')
	ERREURENSEIGNANT=1
	MESSAGEENSEIGNANT="$OUTCORR"
fi

if [ "$OUTOTHER" != "" ];	then
	# feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans un fichier puis en le lisant.
	# sed permet d'indenter chaque ligne de son input avec une tabulation
	OUTOTHER=$(echo "$OUTOTHER" | sed -e 's/^/\t/' | sed 's/%/%%/g' )
	ERREURENSEIGNANT=1
	MESSAGEENSEIGNANT="${MESSAGEENSEIGNANT}\n$OUTOTHER"
fi

if [ $ERREURENSEIGNANT -ne 0 ];	then
	FEED=$(printf "Le programme ne compile pas: \n ${CODELITTERAL}$MESSAGEENSEIGNANT\n")
	feedback -r failed -f "$FEED"
	exit 1
fi

for i in {0..10}
do
    A=$((1 + $RANDOM % 10))
    B=$((1 + $RANDOM % 10))
    C=$((1 + $RANDOM % 10))

    #javac student/M1EC13Corr.java student/M1EC13Stu.java student/M1EC13.java
    OUTPUT1=$(java student/M1EC13 correction $A $B $C 2> err1.out)
    OUTPUT2=$(java student/M1EC13 etudiant $A $B $C 2> err2.out)

    ERR1=$(cat err1.out)
    ERR2=$(cat err2.out)

    if [ "$ERR1" != "" ];	then
        feedback -r failed -f "ERR1 : $ERR1"
        exit 1
    fi

    if [ "$ERR2" != "" ];	then
        # feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans un fichier puis en le lisant.
        # sed permet d'indenter chaque ligne de son input avec une tabulation
        ERR2=$(printf "$ERR2" | sed -e 's/^/\t/')
        FEED=$(printf "Il y a des erreurs dans votre code: \n ${CODELITTERAL}$ERR2\n")
        feedback -r failed -f "$FEED"
        exit 1
    fi

    if [ "$OUTPUT1" != "$OUTPUT2" ]; then
        # feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans 		un fichier puis en le lisant.
        # sed permet d'indenter chaque ligne de son input avec une tabulation
        OUTPUT1bis=$(printf "Votre résultat: \n$OUTPUT2" | sed -e 's/^/\t/')
        OUTPUT2bis=$(printf "\nLe résultat attendu: \n$OUTPUT1" | sed -e 's/^/\t/')
        FEED=$(printf "Votre réponse ne correspond pas à la réponse correcte. \n ${CODELITTERAL}$OUTPUT1bis$OUTPUT2bis")
        feedback -r failed -f "$FEED"
        exit 1
    fi
    ERR1=""
    ERR2=""
done
FEED=$(printf "Votre réponse correspond bien à la réponse correcte.")
feedback -r success -f "$FEED"
exit 1