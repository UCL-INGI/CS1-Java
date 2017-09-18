/**
 *  Copyright (c) Francois Michel, 2017 Brandon Naitali
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
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.Random;
import java.util.ArrayList;
import java.text.MessageFormat;
import static student.Translations.Translator._;
import StudentCode.*;

public class Tests{
    
    public void testStringLength(){
        String feedback  =_("Avec les arguments {1}, votre méthode doit afficher\n{2}\nMalheureusement, votre code renvoie \n{3}\n");
        ArrayList<String> test = generateString(3);
        String result = Correction.getLength(test);
        String resultStudent = Etudiant.getLength(test);
        if(!result.equals(resultStudent)){
            fail(MessageFormat.format(feedback, 1, listToString(test), result, resultStudent));
        }
    }
    // Génère une version string propre d'un arraylist
    public static String listToString(ArrayList<String> stringAL){
        String toReturn = "[ ";
        for(String s: stringAL){
            toReturn += "\"" + s + "\"";
            toReturn += " ";
        }
        toReturn += "]" ;
        return toReturn;
    }
    // Genère un arraylist de taille count de Strings de taille random entre 1 et 20
    public static ArrayList<String> generateString(int count){
        Random rLength = new Random();
        ArrayList<String> toReturn = new ArrayList<String>();
        Random rChar = new Random();
        for(int j = 0; j < count; j++){
            String s = "";
            int length = rLength.nextInt(19) + 1; // taille random entre 1 et 20
            for(int i = 0 ; i < length ; i++){
                s += (char) ((rChar.nextInt('z' - 'a') + 'a'));
            }
            toReturn.add(s);
        }
        return toReturn;
    }
    
    @Test
    public void testLauncher() {
        try{
            for(int i = 0; i < 5; i++)
                testStringLength();
        }catch (ArithmeticException e){
            fail(_("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }	
}


