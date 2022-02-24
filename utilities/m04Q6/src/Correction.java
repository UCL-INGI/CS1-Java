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
     * @pre s!=null, c!=null
     * @post retourne true si la chaine s contient uniquement des
     *       caracteres dans la chaine c, false sinon
     */
    
    public static boolean containsOnly(String s, String c) {
        for(int i=0;i<s.length();i++) {
            boolean found=false;
            for(int j=0; (!found && j<c.length()); j++) {
                if(s.charAt(i) == c.charAt(j) ) {
                    found=true;
                }
            }
            if(!found) {
                return false;
            }
        }
        return true;
    }
}
