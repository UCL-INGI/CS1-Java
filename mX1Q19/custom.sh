#! /bin/bash

COUNT=$(grep -c "Math." StudentCode/Etudiant.java)
if [ $COUNT -gt 0 ]; then
	feedback -i q1 -r failed -f "Il est interdit d'utiliser les fonction mathématiques de java, même en commentaires"
	exit 1
else
	exit 0
fi

