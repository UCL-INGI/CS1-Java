/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Olivier Martin
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

import student.Translations.Translator;

import StudentCode.*;

public class Tests {
    
    private double []values_abs = {0, -0.1, -0.4, -0.5, -0.6, -0.9, -1, 1, 0.5, -7.5};
    private double []values = {0, 0.1, 0.4, 0.5, 0.6, 0.9, 1, 7.5};
    private String feedbackBuilderAbs = "@{0} :\n" + Translator.translate("{1} en valeur absolue donne {2}. Cependant, votre code calcule {3}.\n");
    private String feedbackBuilderCeil = "@{0} :\n" + Translator.translate("{1} arrondi au dessus donne {2}. Cependant, votre code calcule {3}.\n");
    private String feedbackBuilderRound = "@{0} :\n" + Translator.translate("{1} arrondi donne {2}. Cependant, votre code calcule {3}.\n");
    private String feedbackBuilderFloor = "@{0} :\n" + Translator.translate("{1} arrondi en dessous donne {2}. Cependant, votre code calcule {3}.\n");

    @Test
    public void test_abs(){ catcher(new t1(),1); }
    private class t1 implements Callable<Void> {
        public Void call() {
            int question = 1;
            for (Double d : values_abs){
                double reponse_etudiant = Etudiant.abs(d);
                double expected = Correction.abs(d);
                if (Math.abs(expected - reponse_etudiant) > 0.001){
                    fail(MessageFormat.format(feedbackBuilderAbs, question, d, expected, reponse_etudiant));
                    return null;
                }
            }
            return null;
        }
    }
    
    @Test
    public void test_ceil(){ catcher(new t2(),2); }
    private class t2 implements Callable<Void> {
        public Void call() {
            int question = 2;
            for (Double d : values){
                double reponse_etudiant = Etudiant.ceil(d);
                double expected = Correction.ceil(d);
                if (Math.abs(expected - reponse_etudiant) > 0.001){
                    fail(MessageFormat.format(feedbackBuilderCeil, question, d, expected, reponse_etudiant));
                    return null;
                }
            }
            return null;
        }
    }
    
    @Test
    public void test_round(){ catcher(new t3(),3); }
    private class t3 implements Callable<Void> {
        public Void call() {
            int question = 3;
            for (Double d : values){
                double reponse_etudiant = Etudiant.round(d);
                double expected = Correction.round(d);
                if (Math.abs(expected - reponse_etudiant) > 0.001){
                    fail(MessageFormat.format(feedbackBuilderRound, question, d, expected, reponse_etudiant));
                    return null;
                }
            }
            return null;
        }
    }
    
    @Test
    public void test_floor(){ catcher(new t4(),4); }
    private class t4 implements Callable<Void> {
        public Void call() {
            int question = 4;
            for (Double d : values){
                double reponse_etudiant = Etudiant.floor(d);
                double expected = Correction.floor(d);
                if (Math.abs(expected - reponse_etudiant) > 0.001){
                    fail(MessageFormat.format(feedbackBuilderFloor, question, d, expected, reponse_etudiant));
                    return null;
                }
            }
            return null;
        }
    }
    
    public void catcher(Callable<Void> test, int nQuestion) {
        try{
            test.call();
        }catch (ArithmeticException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(Exception e){
            fail(MessageFormat.format("@{0} :\n", nQuestion) + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.getMessage());
            e.printStackTrace();
        }
    }
}
