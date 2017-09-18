
/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

package src;

public class Correction
{
    /*
     * @pre s!=null
     * @post retourne true uniquement si la chaine de caractères
     *       s ne contient que des chiffres
     *       
     */
    // Il y a une méthode dans la classe Character 
    // qui devrait vous aider à vérifier qu'un caractère est un chiffre
    // https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
    public static boolean entierValide(String s) {
        for(int i=0; i<s.length(); i++) {
            if(!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
