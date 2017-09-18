/**
 *  Copyright (c)  2017 Olivier Martin
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
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static student.Translations.Translator._;

import StudentCode.*;

public class Tests {
    
    @Test
    public void test_1(){ catcher(new t1(),1); }
    private class t1 implements Callable<Void> {
        public Void call() {

            String[] args = {gen(10), gen(9), gen(11), gen(5)};

            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            PrintStream ps1 = new PrintStream(baos1);
            PrintStream ps2 = new PrintStream(baos2);
            PrintStream old = System.out;   // save the old System.out!
            System.setOut(ps1);             // Tell Java to use your special stream
            Etudiant.main_1(args);
            System.out.flush();
            System.setOut(ps2);
            Correction.main_1(args);
            System.out.flush();
            System.setOut(old);

            String rep_student = baos1.toString();
            String rep_expected = baos2.toString();
            
            if (! rep_student.contains(rep_expected))
                fail("@1 :\n" + _("Vous n'affichez pas correctement les Strings présents dans args. N'oubliez pas de faire un retour à la ligne après chaque élément de args."));

            return null;
        }
    }
    
    @Test
    public void test_2(){ catcher(new t2(),2); }
    private class t2 implements Callable<Void> {
        public Void call() {
            
            
            Random r = new Random();
            String[] args = {r.nextInt(100)+"", r.nextInt(100)+""};
            
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            PrintStream ps1 = new PrintStream(baos1);
            PrintStream ps2 = new PrintStream(baos2);
            PrintStream old = System.out;   // save the old System.out!
            System.setOut(ps1);             // Tell Java to use your special stream
            Etudiant.main_2(args);
            System.out.flush();
            System.setOut(ps2);
            Correction.main_2(args);
            System.out.flush();
            System.setOut(old);
            
            String rep_student = baos1.toString();
            String rep_expected = baos2.toString();
            
            if (! rep_student.contains(rep_expected))
                fail(MessageFormat.format("@2 :\n" + _("Avec args = {0} vous affichez ''{1}'' alors qu''il faut afficher ''{2}''.\n"), Arrays.toString(args), rep_student.replace("\n", ""), rep_expected.replace("\n", "")));
            
            return null;
        }
    }

    public void catcher(Callable<Void> test, int nQuestion) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    
    /**
     * Author : Jon Skeet, stackoverflow
     * Génère aléatoirement un string
     */
    private String gen(int length) {
        String characters = "azertyuiopqsdfghjklmwxcvbn";
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
