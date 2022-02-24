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
import java.util.Random;

import StudentCode.De;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	De d1;
	Object d2;
	boolean res;
	static String s = "";

	public TestParam(De d1,Object d2,boolean res) {
		this.d1 = d1;
		this.d2 = d2;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		De de1 = new De("dé1");
		De de1bis = new De("dé1"+s);
		De de2 = new De("dé2");
		De de3 = new De("dé3");
		De de4 = new De("dé4");
		de4.setRandom(de1.getRandom());
		De de1id = new De("dé1"+s);
		de1id.setRandom(de1.getRandom());
		
		return Arrays.asList(new Object [][] {
			{de1,null,false},
			{de1,1,false},
			{de1,de1bis,false},
			{de1,de4,false},
			{de2,de3,false},
			{de1,de1id,true},
			{de1,de1,true}
		});
	}

	@Test
	public void test() {
		try {
			String msg = Translator.translate("Test 1 : lorsque l''on exécute votre code avec le dé {0} par rapport à l''objet {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,d1,d2,d1.equals(d2),res);
			assertThat(feed,d1.equals(d2),is(res));
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
