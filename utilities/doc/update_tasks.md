# Mettre à jour les tâches.
La plupart des fichiers présents dans les différentes tâches sont automatiquement placés (copiés) dans les tâches par le script `utilities/update_tasks.sh`. Cela permet d'avoir des versions de base de fichier en cas de modification à faire puis de les transmettres automatiquement dans toutes les tâches. Il ne faut donc pas modifier les fichiers listés dans la section `Fichiers concernés` depuis dans les tâches mais bien les modifier l'original. Attention tout de même à garder les compatibilités avec les tâches actuelles en cas de changement.

## Fichiers concernés
Nous listons ici les fichiers placés automatiquement dans les tâches ainsi que leur originaux. 
* Runner.java
* Original : `utilities/Runner.java`
* Tâche : `my_task/src/Runner.java`
* Run script
* Original : `utilities/run.py`
* Tâche : `my_task/run`
* Translator.java
* Original : `utilities/Translations/Translator.java`
* Tâche : `my_task/student/Translations/Translator.java`
* FunctionHelper.java (uniquement pour les Missions 3, 4 et 5).
* Original : 'utilities/FunctionHelper.java'
* Tâche : `my_task/src/librairies/FunctionHelper.java`

## Tâches concernées
Le script met à jour les tâches qui sont listées dans `utilities/update_tasks.sh`. Cela permet de choisir quelle tâche doit recevoir les changements.

## Exécuter le script
Le script `utilities/update_tasks.sh` doit être exécuté depuis la racine du git. La commande à faire est donc : `./utilities/update_tasks.sh`. Faites un `git status` pour savoir quels fichiers ont été modifiés et/ou crées.
