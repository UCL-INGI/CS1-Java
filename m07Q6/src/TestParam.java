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

import StudentCode.Directeur;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam {

	Directeur d1;
	Object d2;
	boolean res;

	public TestParam(Directeur d1,Object d2,boolean res) {
		this.d1 = d1;
		this.d2 = d2;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Directeur("Jean",2000,0.1),null,false},
			{new Directeur("Jacques",1599,0.2),true,false},
			{new Directeur("George",2500,0.6),new Directeur("Luc",2000,0.9),false},
			{new Directeur("Basil",2000,0.3),new Directeur("Basil",2222,0.11),false},
			{new Directeur("Hervé",1200,0.1),new Directeur("Jo",1200,0.2),false},
			{new Directeur("Hadoc",1100,0.5),new Directeur("Edgard",1111,0.5),false},
			{new Directeur("Phillipe",1567,0.12),new Directeur("Phillipe",1567,0.11),false},
			{new Directeur("Kalid",1400,0.1),new Directeur("Kalid",1200,0.1),false},
			{new Directeur("Gontrand",1200,0.6),new Directeur("Sylvain",1200,0.6),false},
			{new Directeur("Albert",1543,0.98),new Directeur("Albert",1543,0.98),true}
		});
	}

	@Test
	public void test() {
		try {
			String msg = _("Test 2 : lorsque l''on exécute votre code avec le directeur {0} par rapport à l''objet {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,d1,d2,d1.equals(d2),res);
			assertThat(feed,d1.equals(d2),is(res));
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
        }catch(NegativeArraySizeException e){
            fail(_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
