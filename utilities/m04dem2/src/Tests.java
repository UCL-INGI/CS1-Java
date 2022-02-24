/**
 *  Copyright (c) Francois Michel, 2017 Brandon Naitali, Olivier Martin
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

import student.Translations.Translator;
import StudentCode.*;

public class Tests {
    String feedback  ="@{0} :\n La fonction concaténant \"{1}\" et \"{2}\" donne \"{3}\", pourtant, votre code renvoie \"{4}\".\n";
    @Test
    public void testConcat2(){
        catcher(new TestInnerConcat2("Harry", 'P'), 2);
        catcher(new TestInnerConcat2("J'adore les écureuils", '!'), 2);
    }
    private class TestInnerConcat2 implements Callable<Void>{
        String s1;
        char c;
        public TestInnerConcat2(String s1, char c){
            this.s1=s1;
            this.c=c;
        }
        public Void call() {
            String result = Correction.cat(s1, c);
            String resultStudent = Etudiant.cat(s1, c);
            assertEquals(MessageFormat.format(feedback, 2, s1, c, result, resultStudent), result, resultStudent);
            return null;
        }
    }
    @Test
    public void testConcat1(){
        catcher(new TestInnerConcat1("Harry", "Gateau"), 1);
        catcher(new TestInnerConcat1("J'adore les écureuils", " cuits avec des oignons et de la confiture"), 1);
    }
    private class TestInnerConcat1 implements Callable<Void>{
        String s1;
        String s2;
        public TestInnerConcat1(String s1, String s2){
            this.s1=s1;
            this.s2=s2;
        }
        public Void call() {
            String result = Correction.cat(s1, s2);
            String resultStudent = Etudiant.cat(s1, s2);
            assertEquals(MessageFormat.format(feedback, 1, s1, s2, result, resultStudent), result, resultStudent);
            return null;
        }
    }
    
    public void catcher(Callable<Void> test, int n) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(StackOverflowError e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", n) + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
}
