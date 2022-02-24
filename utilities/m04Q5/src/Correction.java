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
     *       s ne contient que les caractères 0 ou 1
     */
    public static boolean binaire(String s) {
        for(int i=0;i<s.length();i++) {
          if ( (s.charAt(i)!='0') && (s.charAt(i)!='1') ) 
            return false;
        }
        return true;
    }
}
