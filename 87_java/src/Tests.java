package src;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import StudentCode.*;

public class Tests {
    
    
    public void test(String s){
        int reponseEtudiant = Etudiant.longueurPlusLongPalindrome(s);
        int reponseCorrection = Correction.longueurPlusLongPalindrome(s);
        
        assertTrue("Le plus long palindrome est de taille " + reponseCorrection 
                   + "\nTandis que vous obtenez " + reponseEtudiant , reponseEtudiant == reponseCorrection);
    }
    
     @Test
    public void testLauncher(){
        try{
            test("KAYAKAK");
            test("AVABCD");
            test("RADARAVION");
            test("MON NOM EST SECRET");
            
        }catch (ArithmeticException e){
            fail("Attention, il est interdit de diviser par zéro.");
        }catch(ClassCastException e){
            fail("Attention, certaines variables ont été mal castées !");
        }catch(StringIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
        }catch(ArrayIndexOutOfBoundsException e){
            fail("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
        }catch(NullPointerException e){
            fail("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
        }catch(NegativeArraySizeException e){
            fail("Vous initialisez un tableau avec une taille négative.");
        }catch(StackOverflowError e){
            fail("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.");
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}