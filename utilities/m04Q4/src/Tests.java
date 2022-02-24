
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

import java.util.Random;
import java.util.HashSet;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import java.text.MessageFormat;
import student.Translations.Translator;
import StudentCode.*;

import src.librairies.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Tests{
    @Rule public TestName name = new TestName();
    /**
     * Renvoie un string proportionnel à taille et times, respectant la règle: min 1 majuscule, min 1 minuscule, min 1 chiffre, avec taille > 8
     **/
    public String generateMdp(int taille, int times, boolean majuscule, boolean minuscule, boolean chiffre){  // taille est > 8!!!!!
        String s = minuscule? StringHelper.generateNumberOrLetter(taille,false): StringHelper.generateNumberOrLetter(taille,true) ;
        Random r = new Random();
        String toReturn="";
        if(chiffre){
            for(int i = 0 ; i < (r.nextInt(times) + 1) ; i++){
                char random = (char) ((r.nextInt(9) + '0')) ; // random number
                int firstIndice = r.nextInt(s.length()/2);
                toReturn = s.substring(0, firstIndice) + random + s.substring(firstIndice, taille);
            }
        }else{
            toReturn = s;
        }
        String toReturn2="";
        if(minuscule){
            for(int i = 0 ; i < (r.nextInt(times) + 1) ; i++){
                char random = (char) ((r.nextInt(26) + 97));// random minuscule
                int firstIndice = r.nextInt(toReturn.length()/2);
                toReturn2 = toReturn.substring(0, firstIndice) + random + toReturn.substring(firstIndice, taille);
            }
        }else{
            toReturn2 = toReturn;
        }
        String toReturn3="";
        if(majuscule){
            for(int i = 0 ; i < (r.nextInt(times) + 1) ; i++){
                char random = (char) ((r.nextInt(26) + 65)); // random majuscule
                int firstIndice = r.nextInt(toReturn2.length()/2);
                toReturn3 = toReturn2.substring(0, firstIndice) + random + toReturn2.substring(firstIndice, taille);
            }
        }else{
            toReturn3 = toReturn2;
        }
        return toReturn3;
    }
    /**
     *	Cette fonction permet de ne pas effectuer les tests si jamais la vérification de la signature n'est pas bonne
     **/
    public boolean check(){
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "motDePasseValide", boolean.class, new Class[]{String.class});
            return true;
        }catch(Throwable e){
            return false;
        }
    }
    @Test
    public void test_1() throws ClassNotFoundException{
        FunctionHelper.check_etudiant_function("StudentCode.Etudiant", "motDePasseValide", boolean.class, new Class[]{String.class});
        
        String feedback  =Translator.translate("Vérifiez-vous la présence d'une majuscule ?\n");
        catcher(new TestMdp1(generateMdp(8, 10, false, true, true), feedback), 1);
    }
    @Test
    public void test_2(){
        if(!check()) return;
        String feedback  =Translator.translate("Vérifiez-vous la présence d'une minuscule ?\n");
        catcher(new TestMdp1(generateMdp(8, 10, true, false, true), feedback), 1);
    }
    @Test
    public void test_3(){
        if(!check()) return;
        String feedback  =Translator.translate("Vérifiez-vous la présence d'un chiffre ?\n");
        catcher(new TestMdp1(generateMdp(8, 10, true, true, false), feedback), 1);
    }
    @Test
    public void test_4(){
        if(!check()) return;
        String feedback  =Translator.translate("Vérifiez-vous toutes les conditions simultanément ?\n");
        catcher(new TestMdp1(generateMdp(8, 10, true, true, true), feedback), 1);
    }
    @Test
    public void test_5(){
        if(!check()) return;
        String feedback  =Translator.translate("Vérifiez-vous la longueur du mot de passe ?\n");
        catcher(new TestMdp1(generateMdp(3, 1, true, true, true), feedback), 1);
    }
    
    private class TestMdp1 implements Callable<Void> {
        String mdp;
        String feedbackAdd;
        public TestMdp1(String mdp, String feedback){
            this.mdp = mdp;
            this.feedbackAdd = feedback;
        }
        public Void call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
            String feedback  =Translator.translate("Avec l''argument s=\"{0}\", la réponse attendue est {1}, malheureusement, votre code renvoie {2}.\n");
            boolean result = Correction.motDePasseValide(mdp);
            boolean resultStudent = (boolean) FunctionHelper.run_student_function("motDePasseValide", mdp);
            if(result != resultStudent) fail(test_name() + " : " + MessageFormat.format(feedback, mdp, result, resultStudent) + " " + feedbackAdd);
            System.err.println(MessageFormat.format(Translator.translate("{0} : réussi"), test_name()));
            return null;
        }
        
    }
    public void catcher(Callable<Void> test, int n) {
        try{
            test.call();
        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
            }else if(t instanceof ClassCastException){
                fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
            }else if(t instanceof NullPointerException){
                fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
            }else if(t instanceof NegativeArraySizeException){
                fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
            }else if(t instanceof StackOverflowError){
                fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
            }else{
                fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + t.toString());
            }
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
    }
    private String test_name(){
        String s = name.getMethodName().replaceAll("_", " ");
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
