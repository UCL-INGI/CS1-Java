
/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Faire des méthodes simples avec des pre et des post qui permettent aux étudiants de comprendre comment les caractères fonctionnent.
 *
 */
package src;

public class Correction
{
    /* @pre s!=null
     * @post retourne true si la caractère c est présent dans la chaîne s, false sinon
     */
    public static boolean isIn(char c, String s) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c) {
                return true;
            }
        }
        return false;
       
    }
}
