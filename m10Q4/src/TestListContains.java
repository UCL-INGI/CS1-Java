/**
 *  Copyright (c) 2017 Brandon Naitali
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
import org.junit.Rule;
import org.junit.rules.TestName;

import student.Translations.Translator;
import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.Random;

import src.library.*;
import StudentCode.*;

public class TestListContains {
	@Rule public TestName name = new TestName();

	private String preQ1 = "@1 :\n";

	private String currentPre = preQ1;
	
	// On test avec un tableau random;
	@Test 
	public void test_3(){ 
		Random r = new Random(); 
		Etudiant e = new Etudiant();
		int length = 10;
		for(int i = 0; i < length; i++){
			Correction.add(e, i, (Integer) r.nextInt(10));	
		}		
		catcher(new TestContains(e, r.nextInt(10)), 1); // dans le tableau 
		catcher(new TestContains(e, r.nextInt(10) + 10), 1); //  hors du tableau entre 10 et 15
	}
	
	// On test la contenance d'un élément d'office absent du tableau;
	@Test 
	public void test_2(){ 
		Random r = new Random(); 
		Etudiant e = new Etudiant();
		int length = 10;
		for(int i = 0; i < length; i++){
			Correction.add(e, i, (Integer) i);	
		}		
		catcher(new TestContains(e, 11), 1);
	}
	
	// On test la contenance d'un élément d'office dans le tableau;
	@Test 
	public void test_1(){ 
		Random r = new Random(); 
		Etudiant e = new Etudiant();
		int length = 10;
		for(int i = 0; i < length; i++){
			Correction.add(e, i, (Integer) i);	
		}		
		catcher(new TestContains(e, 5), 1);
	}

	private class TestContains implements Callable<Void> {
		Object o; 
		Etudiant l;
		public TestContains(Etudiant liste, Object o){
			this.l= liste;
			this.o = o;
		}
		public Void call() { 
			String feedback = Translator.translate("@{0} :\nla fonction contains avec l''AList\n{1}\net l''objet {2} doit renvoyer\n{3}\npourtant, votre code donne\n{4}\n");
			String feedbackInequality = Translator.translate("@{0} :\nvotre méthode contains ne doit pas modifier le tableau !\n");
			try{
				// On crée deux instances identiques à l, une utilisée avec la correction, l'autre avec la méthode de l'étudiant
				Etudiant tabCorr = AListHelper.newInstance(l);
				Etudiant tabStu = AListHelper.newInstance(l);
				boolean result = Correction.contains(tabCorr, o);
				boolean resultStu = tabStu.contains(o);
				// On compare ces deux instances
				if(!AListHelper.objectArrayEquality(tabCorr.l, tabStu.l)) fail(MessageFormat.format(feedbackInequality, 1));
				if(result != resultStu) fail(MessageFormat.format(feedback, 1, l, o, result, resultStu));
			}catch(IndexOutOfBoundsException e){
			    fail("@1 :\n" + Translator.translate("votre méthode contains() tente d'accéder à un index hors de l'AList !\n"));
			}catch(Exception e){
			    fail("@1 :\n" + Translator.translate("votre méthode contains() lance une exception ("+ e +") ! Vérifiez que vous gérez tous les cas possibles.\n"));
			}
			return null;
		}
	}
	public void catcher(Callable<Void> test, int n) {
	try{
	    test.call();
	}catch (ArithmeticException e){
	    fail(currentPre + Translator.translate("Attention, il est interdit de diviser par zéro.") );
	}catch(ClassCastException e){
	    fail(currentPre + Translator.translate("Attention, certaines variables ont été mal castées !") );
	}catch(StringIndexOutOfBoundsException e){
	    fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)") );
	}catch(ArrayIndexOutOfBoundsException e){
	    fail(currentPre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
	}catch(NullPointerException e){
	    fail(currentPre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.") );
	}catch(NegativeArraySizeException e){
	    fail(currentPre + Translator.translate("Vous initialisez un tableau avec une taille négative.") );
	}catch(StackOverflowError e){
	    fail(currentPre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.") );
	}catch(Exception e){

	    fail(currentPre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") );
	}
	}
	private String test_name(){
		String s = name.getMethodName().replaceAll("_", " ");
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
