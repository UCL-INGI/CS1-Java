/**
 * Exercices pour la mission 5
 * 
 * @author O. Bonaventure
 * @version 2016
 */

/*
 * retourne la moyenne arithmétique du tableau de réels v
 */
package student;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.util.Arrays;

public class Mission5Corr
{
    /* @pre v!=null
     * @post retourne la moyenne arithmétique du tableau de réels v
    */
    public static double mean(double[] v) {
       double mean=0;
       for(int i=0; i<v.length;i++) {
         mean=mean+v[i];
        }
      
       return mean/v.length;
    }
}
