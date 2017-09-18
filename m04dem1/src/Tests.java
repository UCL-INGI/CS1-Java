/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Brandon Naitali, Olivier Martin
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
import org.junit.Test;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import static student.Translations.Translator._;
import StudentCode.*;

public class Tests {
    
    @Test
    public void testIsDigit(){
        Random r = new Random();
        catcher(new TestInnerDigit((char)(r.nextInt(9) + 48)),1);  // on génère un char qui est un chiffre entre 1 et 9
        catcher(new TestInnerDigit((char)(r.nextInt(26) + 'a')),1); // on génère un char qui est une lettre de a à z
    }
    private class TestInnerDigit implements Callable<Void>{
        char c;
        public TestInnerDigit(char c){
            this.c = c;
        }
        public Void call() {
            String feedback  ="@{0} :\n La fonction vérifiant si un caractère est un chiffre avec le caractère ''{1}'' doit renvoyer {2}, pourtant, votre code renvoie {3}.\n";
            boolean result = Correction.isDigitStudent(c);
            boolean resultStudent = Etudiant.isDigitStudent(c);
            assertEquals(MessageFormat.format(feedback, 1, c, result, resultStudent), result, resultStudent);
            return null;
        }
    }
    
    @Test
    public void testToUpper(){
        Random r = new Random();
        catcher(new TestInnerUpper((char)(r.nextInt(26) + 'a')), 2); // on génère un char qui est une lettre de a à z
    }
    
    private class TestInnerUpper implements Callable<Void> {
        char c;
        public TestInnerUpper(char c){
            this.c = c;
        }
        public Void call() {
            String feedback  ="@{0} :\n La fonction mise en majuscule avec le caractère ''{1}'' doit renvoyer ''{2}'', pourtant, votre code renvoie ''{3}''.\n";
            char result = Correction.toUpper(c);
            char resultStudent = Etudiant.toUpper(c);
            assertEquals(MessageFormat.format(feedback, 2, c, result, resultStudent), result, resultStudent);
            return null;
        }
        
    }
    public void catcher(Callable<Void> test, int n) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", n) + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
