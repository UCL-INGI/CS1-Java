# Weblate
Nous utilisons Weblate (https://weblate.info.ucl.ac.be) pour effectuer et gérer les traductions des tâches. Weblate est une application permettant à n'importe qui d'effectuer les traductions des exercices en toute simplicité même sans connaissances en informatique. 

## Fonctionnement
Weblate `pull` les changements (des nouveaux Strings dans les fichiers Java par exemple) sur le git et `push` les traductions effectuées par les traducteurs sur le git au bon endroit à l'aide de scripts. 

### Pull
A l'heure actuelle  (14 septembre 2017), l'opération `pull` est manuelle et demande à un administrateur d'appuyer sur un bouton pour effectuer l'opération. Quand le repo sera disponible sur GitHub, il serait bien de configurer l'intégration entre GitHub et Weblate : https://docs.weblate.org/en/latest/admin/continuous.html#automatically-receiving-changes-from-github.

### Push
L'opération `push` est automatique en fonction de certaines conditions. Ces conditions sont reprises dans la documentation de Weblate : https://docs.weblate.org/en/latest/admin/continuous.html#lazy-commits. Ces conditions sont : 	
* somebody else works on the translation
* merge from upstream occurs
* import of translation happens
* translation for a language is completed
* explicit commit is requested

L'opération peut aussi être effectuée manuellement par un administrateur.

### Intervention manuelle
Si vous disposez d'un accès administrateur sur le projet INGInious de Weblate, rendez-vous sur https://weblate.info.ucl.ac.be/projects/inginious/#repository pour voir l'état du référentiel. 
Les boutons qui nous intéressent sont :
* `Récupérer` pour effectuer un `pull`.
* `Envoyer` pour effectuer un `push`.
* `Réinitialiser` en cas de problème de merge. Ceci réglera rapidement le problème mais les changements effectués par Webate seront perdus.

# Traductions
## Ce qui est traduit
Actuellement (14 septembre 2017), certains composants des tâches sont totalement traductible grâce à Weblate. Ces composants sont : 
* Les fichiers Java
* Le script run
* Les éventuels fichiers `custom_translatable.py`

## Fichiers concernés
Voici ci-dessous la liste des fichiers utilisés par le mécanisme de traduction. Les fichiers contenant une étoile `*` représentent le code de langue comme `en_US`. Il ne faut en aucun cas renommer ou déplacer ces fichiers car sinon, ils seront introuvables par le mécanisme de traduction.

### Scripts exécutés par weblate
Ces 5 scripts sont exécutés automatiquement par Weblate depuis la racine du git pour garder les traductions à jour :
* `utilities/weblate_scripts/post-add.sh`
* `utilities/weblate_scripts/post-commit.sh`
* `utilities/weblate_scripts/post-push.sh`
* `utilities/weblate_scripts/post-update.sh`
	* Ce script est exécuté quand Weblate fait un `pull`. 
	* Il parcourt tous les fichiers (les .java, les `custom_translatable.py` et le `utilities/run.py`), et crée le fichier `utilities/Translations/main.pot`.
	* Il met à jour les fichiers .po sur base du .pot.
* `utilities/weblate_scripts/pre-commit.sh`
	* Ce script est exécuté quand Weblate fait un `push`.
	* Il crée les fichiers .properties (utilisé par Java)
	* Il crée les fichiers .mo (utilisé par Python)
	* Il copie les fichiers .properties et .mo dans toutes les tâches au bon endroit (voir section *Fichiers de traductions*).

### Fichiers de traductions
Les fichiers présentés dans cette section sont gérés automatiquement par Weblate ainsi que les 5 scripts de la section ci-dessus. Il ne faut pas modifier ces fichiers manuellement car cela risque de provoquer un merge conflict avec Weblate et demandera l'intervention d'un administrateur de Weblate pour régler le conflit.
* `utilities/Translations/main.pot`
	* Fichier contenant toutes chaînes source.
	* Utilisé pour mettre à jour les fichiers .po.
	* Utilisé par Weblate quand on ajoute une nouvelle langue.
* `utilities/Translations/*.po`
	* Fichier contenant les traductions pour la langue `*`.
* `utilities/Translations/MessagesBundle_*.properties`
	* Fichiers contenant les traductions (comme le .po)
	* Obtenu en dérivant le fichier .po en .properties
	* Utilisé par Java (voir Translator.java)
* `utilities/Translations/translations_run/*/LC_MESSAGES/run.mo`
	* Fichiers contenant les traductions (comme le .po)
	* Obtenu en compilant le fichier .po en .mo
	* Utilisé par Python (voir run.py)

### Script run
* `utilities/run.py`
	* Le script run contient aussi des Strings pouvant être traduits. 
	* La langue utilisée est celle définie par INGInious. 

### Utilitaire pour Java
* `utilities/Translations/Translator.java`
	* Les fichiers Java contenant des Strings pouvant être traduits doivent importer ce fichiers et utiliser la fonction `_()`.
	* La langue utilisée est celle définie par le script run.

## Comment traduire une tâche ?
0) [optional] Si vous désirez utiliser un script custom et que celui-ci doit être traduit, vous DEVEZ nommer ce script `custom_translatable.py`, le placer à coté du fichier `run` de votre tâche et définir la fonction `def main(_):` dedans. Un exemple de ce fichier peut être trouvé dans `utilities/example`. La fonction `main(_)` sera par la suite appelée par le script run. Si vous ne respectez pas ces conditions, les traductions ne seront pas compatibles.

1) Importer dans les fichiers Java : 
    `import static student.Translations.Translator._;`
Ceci permettra de disposer de la fonction `_()` qui s'occupera de retourner la traduction en fonction de la langue définie.

2) La tâche Java doit disposer du fichier `Translator.java`. Pour l'avoir, deux possibilités : 
	* Copier/collé le fichier `utilities/Translations/Translator.java` vers `my_task/student/Translations/Translator.java` où `my_task` est le nom de dossier de votre tâche.
	* Ajouter votre tâche dans `utilities/update_tasks.sh` et exécuter ce script qui s'occupera de copier pour vous le fichier `Translator.java` au bon endroit. 

3) Afin de rendre votre exercice compatible avec les traductions, vous devez 'tagger' tous les Strings de feedback qui peuvent être traduits avec `_("blabla")`.
Par exemple, si vous avez : `String s = "Bonjour"`, modifiez le comme suit : `String s = _("Bonjour")`.

4) Afin de minimiser le nombre de String à traduire et faciliter la traduction, on utilise `MessageFormat.format()`. Ceci permet d'éviter la concaténation de Strings et d'avoir des traduction de meilleur qualité (car on impose pas de tournure de phrases). Ainsi plutôt que de faire par exemle : 
`String s = _("Le nombre ") + n + _(" est pair");`
on utilisera MessageFormat comme suit : 
`String s = MessageFormat.format(_("Le nombre {0} est pair"), n)`. 

    **Cas particuliers et conseils** :
* Si vous utilisez des quotes `'`, il faut en placer 2 si on utilise MessageFormat.format(). Par exemple il faut faire : `MessageFormat.format(_("L''entier"))`. Le résultat donnera : `L'entier`
* Si vous utiliser des nombres >999 dans `MessageFormat.format()`, il faut utiliser `{0,number,#}` à la place de `{0}`. Car sinon le nombre s'affichera avec des virgules.
* Lorsque xgettext (l'utilitaire qui construit le fichier .pot) recherche les `_()` dans les fichiers, le code n'est pas exécuté. Il ne faut donc JAMAIS faire `_(str + "bonjour")` mais plutôt : `str + _("bonjour")`.
* Il faut essayer de garder un nombre minimum de Strings différents. Cela afin de faciliter la traduction et de garder des fichiers de traductions légers. Par exemple, si vous avez le String `_("La condition 5 > 3")` et un String  `_("La condition 5 < 3")`, transformez les comme suit : 
	`MessageFormat.format(_("La condition 5 {0} 3"), ">")`.
* Faites attention à l'orthographe et à la ponctuation dans les chaînes sources des fichiers Java. Modifier une chaîne source implique de perdre les traductions pour cette chaîne et ce, pour toutes les langues. 
* MessageFormat.format() peut provoquer des exceptions si les `{}` sont mal définies ou mal traduites depuis Weblate.

5) Ouvrir le fichier `utilities/weblate_scripts/pre-commit.sh` et ajouter votre tâche dans le tableau `arr`. Cette opération est nécessaire afin que Weblate puisse envoyer les traductions vers votre tâche.

6) Votre exercice est désormais compatible aves les traductions. Vous pouvez vous arrêter ici ou continuer si vous souhaitez traduire vos chaines soucres.

7) Weblate ne pull pas automatiquement les changements du git. L'operation est pour le moment manuelle. Contactez olivier.martin@student.uclouvain.be ou un administrateur de Weblate pour que l'opération soit faite.

8) Vous rendre sur https://weblate.info.ucl.ac.be/projects/inginious/lfsab1401/ et traduire vos chaines sources. 

9) Weblate enverra automatiquement les traductions sur le git après un certain temps. 
