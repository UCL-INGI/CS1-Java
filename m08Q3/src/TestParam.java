
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

import StudentCode.StringTab;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam {

	StringTab s1;
	StringTab s2;
	boolean res;

	public TestParam(StringTab s1,StringTab s2, boolean res) {
		this.s1 = s1;
		this.s2 = s2;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new StringTab(new char [] {'h','e','l','l','o'}),new StringTab(new char []{}),true},
			{new StringTab(new char [] {}),new StringTab(new char []{'H','a'}),false},
			{new StringTab(new char [] {'M','o','n','t','a','g','n','e'}),new StringTab(new char [] {'M','o','n','t','a','g','n','e','s'}),false},
			{new StringTab(new char [] {'g','é','o','g','r','a','p','h','i','e'}),new StringTab(new char [] { 'G','e','o','r','g','e','s'}),false},
			{new StringTab(new char [] {'J','e','u','x'}),new StringTab(new char [] {'e','u','x'}),true},
			{new StringTab(new char [] {'C','a','u','s','a','l'}),new StringTab(new char [] {'C','a','u'}),true},
			{new StringTab(new char [] {'f','o','r','e','t'}),new StringTab(new char [] {'o','r'}),true}
		});
	}

	@Test
	public void test() {
		try {
			String msg = _("Lorsque l''on vérifie que le StringTab {0} apparait dans {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,Arrays.toString(this.s2.getS()),Arrays.toString(this.s1.getS()),s1.contains(s2),res);
			assertThat(feed,s1.contains(s2),is(res));
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
