/**
 * Exercices pour la mission 4
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * Faire des méthodes simples avec des pre et des post qui permettent aux étudiants de comprendre comment les caractères fonctionnent.
 */
package student;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;

public class Mission4Corr
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
