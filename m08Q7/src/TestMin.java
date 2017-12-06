
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

import StudentCode.MatriceCarree;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestMin {

	double [][] m;
	double res;

	public TestMin(double [][] m, double res) {
		this.m = m;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new double [][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},0},
			{new double [][]{{1,1,1},{1,1,1},{1,1,1}},1},
			{new double [][]{{12,1,4},{0,-12,14},{4,1,3}},-12},
			{new double [][]{{10,1},{5,2}},1},
			{new double [][]{{-4,-1,-2},{-144,-10,-5},{-10,-50,-5}},-144}
		});
	}

	@Test
	public void test() {
    String method_tested = "min() : ";
		try {
			MatriceCarree m = new MatriceCarree(this.m);
			String msg = _("Test min : lorsque l''on exécute votre méthode min() sur la matrice\n{0}\nvotre code renvoie {1} au lieu de {2}");
			String feed = MessageFormat.format(msg,m,m.min(),res);
			assertThat(feed,m.min(),is(res));
		}catch (ArithmeticException e){
            fail(method_tested + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(method_tested + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(method_tested + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(method_tested + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(method_tested + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(method_tested + _("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(method_tested + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(method_tested + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
