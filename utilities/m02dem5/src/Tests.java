/**
 *  Copyright (c)  François MICHEL, 2017 Brandon NAITALI
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package src;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.util.ArrayList;

import java.text.MessageFormat;
import student.Translations.Translator;
import StudentCode.*;

public class Tests{
    private String msgFeedback = Translator.translate("Test {0} : le test de nombre premier sur {1,number,#} renvoie {2} et votre programme renvoie {3}.\n");
    static int countTest = 1;
    static ArrayList<Integer> primes;
    static ArrayList<Integer> nonPrimes;
    
    // Renvoie un nombre random non-premier entre 1 et 1000
    public int nonRandomPrime(){
        nonPrimes = new ArrayList<Integer>();
        for(int i = 0; i < 1000; i++){
            if(!nonPrimes.contains(i)) nonPrimes.add(i);
        }
        Random r = new Random();
        return nonPrimes.get(r.nextInt(nonPrimes.size()));
    }
    // Renvoie un nombre random premier entre 1 et 1000
    public int randomPrime(){
        primes = new ArrayList<Integer>();
        for(int i = 0; i < 1000; i++){
            if(Correction.prime(i)) primes.add(i);
        }
        Random r = new Random();
        return primes.get(r.nextInt(primes.size()));
    }
    
    public void testPrime(int x){
        boolean resultat = Correction.prime(x); // résultat attendu
        boolean etudiantResult = Etudiant.prime(x); // résultat de l'étudiant
        String erreur = MessageFormat.format(msgFeedback, countTest, x, resultat, etudiantResult);
        assertTrue(erreur, resultat == etudiantResult);
        countTest++;
    }
    
    @Test
    public void testLauncher(){
        try{
            // Verification sur des nombres premiers;
            testPrime(11);
            testPrime(23);
            testPrime(211);
            
            // Verification sur des nombres derniers;
            testPrime(10);
            testPrime(25);
            testPrime(99);
            
            // Random prime
            testPrime(randomPrime());
            testPrime(randomPrime());
            testPrime(randomPrime());
            
            // Random non-prime
            testPrime(nonRandomPrime());
            testPrime(nonRandomPrime());
            testPrime(nonRandomPrime());
            
            for(int i = 0; i < 50; i++)
                testPrime(i);
            
        }catch (ArithmeticException e){
            fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }		
    }
}
