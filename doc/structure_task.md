#Structure dossier
A la racine du dossier de la tâche :

## Un script `run`
Le script `run` a plusieurs utilités :
- Il va mettre les réponses des étudiants dans les fichiers `.tmpl` (voir section sur le dossier *Templates*);
- Il va compiler les fichiers `.java` du dossier `src` et ceux générés lors de l'étape précédente;
- Il va éventuellement exécuter un script custom supplémentaire (voir section sur le *config.json*);
- Il va lancer le `Runner` et donner le feedback adéquat à l'étudiant;

Il prend ses arguments depuis le fichier `config.json` qui sera détaillé dans la section suivante.

## Un fichier `config.json`

Le fichier `config.json` est le fichier de configuration de la tâche. Voici ses champs:
- `customscript` : le nom d'un fichier de script personnalisé, dans un langage de script quelconque;
- `execcustom` : s'il faut exécuter oui (1) ou non (0) le script personnalisé;
- `nexercices`: le nombre d'exercice (sous-problèmes) de la tâche

Il y a deux autre champs qui sont optionnels
- `tests`: une liste (i.e. `[elem1,elem2,...]`) qui contient l'ensemble des fichiers de tests à compiler et lancer. Si le champs n'est pas présent ou si la liste est vide, tous les fichiers `.java` se trouvant dans le dossier *src* seront compilés et lancés;
- `runner` : une chaîne de caractère qui spécifie le *runner* à utiliser. Si le champs n'est pas présent, le fichier `Runner.java` est utilisé.

## Un fichier `task.yaml`

Ce fichier est généré automatiquement lors de la création d'une tâche, via l'interface graphique. Sur cette interface vous retrouvez

- Un onglet *Basic settings*. Dans cet onglet vous avez notamment le nom de l'auteur, de la tâche , le contexte et quelques autres options. Le contexte de la tâche est l'énoncé de l'exercice que vous écrivez. Le contexte est écrit en [rST](https://en.wikipedia.org/wiki/ReStructuredText);
- Un onglet *Container setup* dans lequel vous pouvez définir les paramètres de l'environnement dans lequel le code de l'étudiant va être exécuté;
- Un onglet *Subproblems* dans lequel vous pouvez définir de nouveau sous-problème dans votre tâche. Chaque sous-problème est identifié par un **id** unique. Cet identifiant est important pour les fichiers de template (voir section sur le dossier *Templates*);
- Un onglet *Task files* où vous pouvez manipuler les fichiers de la tâches.


## Le dossier *src*

Le dossier *src* contient deux éléments obligatoire ainsi que des éléments optionnel :
1) L'ensemble des fichiers de tests
2) Le ou les *runner* qui lancent les tests
3) Eventuellement, le fichier de correction de l'exercice
4) Si vous avez des fichier `.java` à mettre dans le dossier src qui ne sont **ni** des tests **ni** un runner, par exemple une librairie de lecture de fichier, vous devez le mettre dans un sous-dossiers `src/librairies`.

Les fichiers de tests sont écrit en Java et utilisent le framework [JUnit](http://junit.sourceforge.net/javadoc/) ainsi que [Mockito](http://www.javadoc.io/doc/org.mockito/mockito-core/2.8.47). Les fichiers de tests ne contiennent que des tests (i.e. pas de méthode `main`). C'est le *runner* qui s'occupe de lancer l'ensemble des tests.

De plus, le nom des fichiers n'a pas d'importance. Ce sont ceux repris dans le fichier `config.json` qui sont pris en compte, ou tout ceux dans le dossier. Il est donc facile de faire plusieurs suite de tests différentes dans des fichiers différents.

Le(s) fichier(s) *runner* doivent s'occuper de lancer les tests et si ceux-ci réussissent, faire un `System.exit(127)`.

## Le dossier *Template*

Ce dossier contient des fichiers `.tmpl`. Ces fichiers sont des templates dans lesquels seront insérés les réponses de l'étudiant aux sous-problèmes. Un fichier `.tmpl` est un fichier écrit en Java dans lequel une partie du code a été remplacé par `@	@<id-question>@@` ou `<id-question>` est l'id d'un sous-problème de votre tâche. C'est à cet endroit que sera inséré le code de l'étudiant. Cette opération est effectuée par le fichier `run` et le résultat sera écrit dans un fichier `.java`.

Remarquez que vous pouvez avoir plusieurs fichiere `.tmpl` avec chacun un ou plusieurs sous-problème à remplacer. Les fichiers `.java` seront écrits dans un dossier *StudentCode*. Il est donc important dans vos tests que vous importiez les éléments de ce dossier via `import StudentCode.*;`

## Le dossier *student/Translations*

Ce dossier contient un utilitaire Java (Translator.java) permettant l'intégration de gnugettext dans les autres fichiers Java. Ce dossier contient aussi un dossier `translation_run` et `translation_java`. Ceux-ci contiennent les traductions des chaînes de caractères. Vous ne devez à priori pas y toucher. Il est nécessaire que ces fichiers se trouvent dans le dossier *student* afin que les traductions soient accessibles au runtime (au runtime, seul le dossier *student* est accessible).
