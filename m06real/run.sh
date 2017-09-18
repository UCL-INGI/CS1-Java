#! /bin/bash

#	@author: François MICHEL


# Ce script est un template pour lancer les exercices de réalisation de missions sur INGINIOUS.
# Ce script va compiler et exécuter les fichiers suivants :
# MissionTest.java
# Mission${NMISSION}Test.java
# et optionnellement les fichiers écrits par l'étudiant.
# Ce script va compiler les fichiers de l'étudiants et y exécuter les tests rédigés par Bastien Bodart, modifiés.
# Il est important de bien respecter les noms des fichiers !

# L'id du sous-problème sur INGInious doit s'appeler "q1" pour des raisons de facilité d'utilisation du script

# Le fichier MissionTest.java contiendra la main qui lancera les tests. Losque les tests ont réussi, la main se termine avec System.exit(127). Lorsqu'un problème survient, la main se termine avec une autre valeur que 127.

# Le fichier Mission${NMISSION}Test.java contient les tests qui seront exécutés pour tester la soumission de l'étudiant.

# Le fichiers custom.sh est un fichier optionnel, qui contient un script sh pour par exemple procéder à des vérifications dans le code (comme détecter l'utilisation de Math.pow à un endroit on on ne pourrait pas l'utiliser). Ce script doit renvoyer 0 (avec echo, par exemple), lorsque l'exécution du script s'est bien déroulée. Il doit renvoyer une autre valeur quand un probème est survenu (par exemple, on a détecté l'utilisation de Math.pow alors que c'est interdit). Lorsqu'un problème est survenu, le script doit retourner un feedback négatif pour la question en cours ainsi qu'un feedback négatif pour la tâche. Lorsqu'une erreur est survenue dans custom.sh, ce script-ci arrêtera automatiquement sa propre exécution directement avec exit, une fois custom.sh terminé. Le script custom.sh peut aussi servir à compiler des fichiers non prévus par ce script-ci

# Pour utiliser ce template sur une de vos tâches, les variables à modifier sont :
# NMISSION, qui contient le numéro de la mission. Ce numéro doit se retrouver dans les noms de fichiers java comme indiqué ci-dessus.
# EXECCUSTOM, qui vaudra 0 lorsqu'il n'y a pas de script custom.sh à exécuter et une autre valeur quand il faut en exécuter un.
# SOUMISSION qui contient une chaîne de caractère contenant tous les fichiers java soumis par l'étudiant qu'il faudra compiler. Ces fichiers sont séparés par des espaces et sont préfixés de la chaîne de caractères "student/", pour pouvoir les extraire dans un dossier "student"

# Pour le reste du script, il n'y a rien à modifier sauf peut-être pour certains cas précis.



NMISSION=6
CUSTOMSCRIPT="sh custom.sh"
SOUMISSION="student/*.java"
# EXECCUSTOM vaut 0 si on n'exécute pas de script "custom" pour faire des vérifications supplémentaires
EXECCUSTOM=1
CODELITTERAL="::\n\n"
JAVAC="javac -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest-core.jar"
JAVA="run_student java -da -cp .:./student:/usr/share/java/junit.jar:/usr/share/java/hamcrest-core.jar"

# On met la machine en UTF-8
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export LANGUAGE=en_US.UTF-8


# On compile la tâche et on récupère le résultat dans un fichier
# On commence par les tests
${JAVAC} student/MissionTest.java student/Mission${NMISSION}Test.java 2> logOther.out
# On compile ensuite les fichiers de l'étudiant
${JAVAC} $SOUMISSION 2> log.out

OUTPUT=$(cat log.out)

OUTOTHER=$(cat logOther.out)
ERREURENSEIGNANT=0
MESSAGEENSEIGNANT=""

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
	exit
fi

ERREUR=0

OUTCUSTOM=0
if [ $EXECCUSTOM -ne 0 ]; then
	OUTCUSTOM=$($CUSTOMSCRIPT)
fi

if [ $OUTCUSTOM -ne 0 ]; then
	exit
fi

# Si le fichier est vide et qu'il n'y a donc pas d'erreur de compilation
if [ "$OUTPUT" = "" ]; then
	# On lance l'exercice 1
	OUTPUT=$(${JAVA} student/MissionTest file:/student student.Mission${NMISSION}Test 2> err.txt)
	RESULTAT=$?
	echo $RESULTAT
	OUTERR=$(cat err.txt)
	# S'il y a des erreurs dans l'exécution du programme (écrites sur stderr)
	if [ $RESULTAT -eq 127 ]; then
		feedback -i q1 -r success -f "Bravo, votre code est correct !"
	else
		# Sinon c'est que les tests ont échoué, le programme possède des erreurs.
		# feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans un fichier puis en le lisant.
		OUTERR=$(echo "$OUTERR" | sed -e 's/^/\t/' | sed 's/%/%%/g' )
		FEED=$(printf "Il semble que vous ayiez fait des erreurs dans votre code...\n ${CODELITTERAL}$OUTERR\n")
		feedback -i q1 -r failed -f "$FEED"
		ERREUR=1
	fi

	# On vérifie si la tâche s'est bien déroulée ou s'il y a eu un souci, et on fait un feedback de la tâche complète
	if [ $ERREUR -eq 0 ]; then
		feedback -r success -f "Votre soumission est correcte !"
	else
		feedback -r failed -f "Vous n'avez pas réussi tous les exercices"
	fi
else
	# feedback n'aime pas les "\n", donc on contourne le probleme en l'écrivant dans un fichier puis en le lisant.
	# sed permet d'indenter chaque ligne de son input avec une tabulation
	OUTPUT=$(echo "$OUTPUT" | sed -e 's/^/\t/' | sed 's/%/%%/g' )
	FEED=$(printf "Votre programme ne compile pas: \n ${CODELITTERAL}$OUTPUT\n")
	feedback -r failed -f "$FEED"
fi

printf "$FEED" > feedback.out



