
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.hamcrest.CoreMatchers.is;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;

import StudentCode.MatriceCarree;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestMax {

	double [][] m;
	double res;

	public TestMax(double [][] m, double res) {
		this.m = m;
		this.res = res;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new double [][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},0},
			{new double [][]{{1,1,1},{1,1,1},{1,1,1}},1},
			{new double [][]{{12,1,4},{0,-12,14},{2,6,9}},14},
			{new double [][]{{10,1},{5,2}},10},
			{new double [][]{{-4,-1,-2},{-144,-10,-5},{-7,-12,-11}},-1}
		});
	}

	@Test
	public void test() {
    String method_tested = "max() : ";
		try {
			MatriceCarree m = new MatriceCarree(this.m);
			String msg = Translator.translate("Test max : lorsque l''on exécute votre méthode max() sur la matrice\n{0}\nvotre code renvoie {1} au lieu de {2}") + "\n";
			String feed = MessageFormat.format(msg,m,m.max(),res);
			assertTrue(feed, m.max() == res);
		}catch (ArithmeticException e){
            fail(method_tested + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(method_tested + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(method_tested + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(method_tested + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(method_tested + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(method_tested + Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(method_tested + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(method_tested + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
