/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Vérifier si une chaine de caractères est un palindrome https://en.wikipedia.org/wiki/Palindrome
 */
package src;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;

public class Correction
{
    /*
     * @pre s!=null
     * @post retourne true si s est un palindrome, false sinon
     * 
     */
    public static boolean palindrome(String s) {
       for(int i=0;i<s.length()/2;i++) {
           if(s.charAt(i) != s.charAt(s.length()-1-i))
              return false;
       }
       return true;
        
    }
}
