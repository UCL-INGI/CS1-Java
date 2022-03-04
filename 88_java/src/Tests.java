package src;

import static org.junit.Assert.*;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;

import StudentCode.*;

public class Tests {
	
	public void testCountDistinctInt(int a, int b, int c, int d, int e){
        String str = "Le code semble comporter des erreurs : ";
        
        int reponseEtudiant = Etudiant.countDistinctInt(a, b, c, d, e);
        int reponseCorrection =Correction.countDistinctInt(a, b, c, d, e);

        String form = "Avec {0}, {1}, {2}, {3} et {4}, vous trouvez {5} entiers différents\nor il y en a : {6}\n";
        assertTrue(str + MessageFormat.format(form, a, b, c, d, e, reponseEtudiant, reponseCorrection), reponseCorrection == reponseEtudiant);
	}
    
    @Test
    public void testLauncher(){
        try{
            testCountDistinctInt(1,1,1,1,1);
            testCountDistinctInt(-1,1,1,1,1);
            testCountDistinctInt(-1,-2,1,1,1);
            testCountDistinctInt(-1,-2,-3,1,1);
            testCountDistinctInt(-1,-2,-3,-4,-1);
            testCountDistinctInt(-1,-2,-3,-4,-5);
            testCountDistinctInt(-1,1,-1,1,-1);
            testCountDistinctInt(-1,2,-1,3,-1);

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
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}
