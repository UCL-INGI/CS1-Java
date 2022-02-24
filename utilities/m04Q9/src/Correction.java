/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Transformer un nombre en notation binaire en sa représentation sous forme de int https://fr.wikipedia.org/wiki/Système_binaire
 */
package src;

public class Correction
{
   /* @pre s!=null et s est la représentation d'un nombre binaire (il contient uniquement des 1 et des 0)
     * @post retourne la représentation entière du nombre binaire
     */
    public static int b2i(String s) {
        int val=0;
        int exp=1;
        for(int i=s.length()-1;i>=0;i--){
           if(s.charAt(i)=='1') {
               val=val+exp;
            }
            exp=exp*2;
        }
        return val;
        
    }
}
