
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
import java.util.ArrayList;

import StudentCode.DeStats;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam {

	DeStats d;
	Object o;
	boolean res;
	static String s = "";

	public TestParam(DeStats d,Object o,boolean res) {
		this.d = d;
		this.o = o;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		DeStats same = new DeStats("d6",12,new int[] {2,3,1,2,0,4});
		DeStats sameButArray = new DeStats("d12",30,new int [] {15,5,0,0,5,5});
		DeStats sameButArray2 = new DeStats("d12"+s,30,new int [] {5,5,5,5,5,5});
		sameButArray2.setRandom(sameButArray.getRandom());
		int [] tab1 = new int [] {1,0,0,0,0,1};
		return Arrays.asList(new Object [][] {
			{new DeStats("d1"),null,false},
			{new DeStats("d1"),new ArrayList<Integer>(),false},
			{new DeStats("d2",100,new int[]{10,10,20,20,20,20}),new DeStats("d4"+s,10,new int [] { 2,3,1,1,0,3}),false},
			{new DeStats("d6",2,tab1),new DeStats("d3"+s,2,tab1),false},
			{new DeStats("d8",25,new int [] {25,0,0,0,0,0}),new DeStats("d8"+s,25,new int [] {12,12,1,0,0,0}),false},
			{new DeStats("d65",42,new int [] {1,2,3,30,0,6}),new DeStats("d65"+s,1,new int [] {1,0,0,0,0,0}),false},
			{sameButArray,sameButArray2,false},
			{new DeStats("42"),new DeStats("42"+s),false},
			{same,same,true}
		});
	}

	@Test
	public void test() {
		try {
			String msg = _("Test 1 : lorsque l''on exécute votre méthode avec le DeStats {0} par rapport à l''objet {1}, votre code renvoie {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,d,o,d.equals(o),res);
			assertThat(feed,d.equals(o),is(res));
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
