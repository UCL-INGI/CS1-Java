/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Faire des méthodes simples avec des pre et des post qui permettent aux étudiants de comprendre comment les caractères fonctionnent.
 */
package src;

public class Correction
{
   /* @pre s!=null et s est la représentation d'un nombre entier décimal  (il contient uniquement des chiffres)
     * @post retourne la représentation entière du nombre 
     * 
     * voir http://docs.oracle.com/javase/6/docs/api/java/lang/Character.html#getNumericValue(char)
     */
    public static int d2i(String s) {
        int val=0;
        int exp=1;
        for(int i=s.length()-1;i>=0;i--){
            val=val+exp*Character.getNumericValue(s.charAt(i));
            exp=exp*10;
        }
        return val;
        
    }
}

