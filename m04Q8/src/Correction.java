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
     * @pre s!=null, in!=null
     * @post retourne true si la chaine de caractère i se trouve dans la
     * chaine de caractères s
     * 
     */
    public static boolean contains(String s, String in) {
        if(in.length()>s.length()) {
            return false;
        }
        
        for(int i=0;i<=s.length()-in.length();i++) {
            boolean found=true;
            for(int j=0;found && j<in.length();j++) {
                if(s.charAt(i+j)!=in.charAt(j)) {
                   found=false;
                }
            }
            if(found) {
                return true;
            }
        }
        return false;
    }
}
