
/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */
package src;

public class Correction
{
    /* @pre s!=null
     * @post retourne le nombre d’occurrences du caractère c dans la chaîne s
     */
    public static int countChar(String s, char c) {
       int count=0;
       for (int i=0;i<s.length();i++) {
           if(s.charAt(i)==c)   count++;
       }
       return count;
    }
}
