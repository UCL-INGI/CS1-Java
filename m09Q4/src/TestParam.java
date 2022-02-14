
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

import StudentCode.Etudiant;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	String filename;
	String s;
	boolean res;

	public TestParam(String s, String filename, boolean res) {
		this.filename = filename;
		this.s = s;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{"","./file1",true},
			{"performed supposing by","./file1",true}, // ligne 21
			{"compass. Be visitor females","./file1",false}, // sur ligne 45-46
			{"ghtdenfzmfuezffezjnzk","./file1",false}, // random String
			{"","./file2",true},
			{"Je suis le fichier 2. Je comporte 5 ligne","./file2",false}, // entire file
			{"fichier 2.","./file2",true}, // ligne3
			{"jfnziub","./file2",false}// random string
		});
	}

	@Test
	public void test() {
		try {
			String feed = Translator.translate(" Test 1 : votre code en renvoie pas le bon résultat !");
			assertThat(feed,Etudiant.contains(s,filename),is(res));
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
