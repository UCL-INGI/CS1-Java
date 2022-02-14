# Les méthodes de tests
Lorsque vous créerez une nouvelle tâche Inginious, il sera important de déterminer ce que vous devez tester (valeurs de retour d'une fonction, accès à une classe, etc). Dans un souci d'uniformisation du format des tâches, ces tests doivent se faire d'une certaine manière. Afin de rendre la chose plus concrète, des exemples de tâches sont indiqués à côté de chaque procédure de test. 

# En général
Si vous vérifiez
- **les valeurs de retour d'une méthode ou plusieurs méthodes**     
    - `Tests avec des instances "fixes" et / ou aléatoires` ( voir m01Q11 ) 
    - `Tests paramétrisés` ( avec les instances problématiques ) ( voir m09Q1 )
    - `Suite de tests` ( plus approprié pour donner un feedback préçis pour chaque instance problématique ) ( voir m02Q8 )
    
- **la gestion des exceptions ( le "catch" + la fermeture des flux )**
    - `Mocking` ( voir m09Q4 )
    - `try...catch... dans la suite de test` 

- **la signature d'une fonction ( "modifiers" + type de retour/des arguments + nom )**
    - `Reflection` en utilisant les méthodes de `Inspector.java`
- **la présence de fonctions interdite d'utilisation / forcer l'utilisation**
    - `Mocking` ( voir m09Q4 )
    - `Custom script` (voir m02dem3)
