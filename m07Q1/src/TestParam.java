
/**
 *  Copyright (c) 2017 Dubray Alexandre 
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.is;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import StudentCode.Employe;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	Employe e1;
	Object e2;
	boolean res;
	static String s = "";

	public TestParam(Employe e1,Object e2,boolean res) {
		this.e1 = e1;
		this.e2 = e2;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Employe("Louis",2000),null,false},
			{new Employe("Jean",1000),new Employe("Jean"+s,1000),true},
			{new Employe("Jacques",972.4),new Employe("Jacques"+s,1989),false},
			{new Employe("Pierre",10000),new Employe("Laura"+s,10000),false},
			{new Employe("Sarah",1888),new Employe("Arthur"+s,1000),false},
			{new Employe("Luc",1234),1,false}
		});
	}

	@Test
	public void test() {
		try {
			String msg = Translator.translate("Test 2 : lorsque l''on exécute votre code avec l'employé {0} par rapport à l'objet {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,e1,e2,e1.equals(e2),res);
			assertThat(feed,e1.equals(e2),is(res));
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
        }catch(NegativeArraySizeException e){
            fail(Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
