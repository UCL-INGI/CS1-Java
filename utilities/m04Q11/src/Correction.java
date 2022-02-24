/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Déterminer le caractère le plus présent dans une chaine (plus compliqué)
 */
package src;

public class Correction
{
   /*
     * @pre s!=null
     * @post retourne le caractère qui se trouve le plus grand nombre de fois
     *       dans la chaîne s
     */
    public static char plusFrequent(String s) {
        int bestCount=0;
        char bestChar=s.charAt(0);
        
        for(int i=0;i<s.length();i++) {
            int count=0;
            for(int j=i+1;j<s.length();j++) {
                if(s.charAt(j)==s.charAt(i)) {
                    count++;
                }
            }
            if(count>bestCount) {
                bestCount=count;
                bestChar=s.charAt(i);
            }
        }
        return bestChar;
    }
}
