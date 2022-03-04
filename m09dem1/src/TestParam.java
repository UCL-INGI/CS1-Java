
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

	Integer i;
	Object arg;

	public TestParam(int i, int arg) {
		this.i = i;
		this.arg = arg;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{0,0},
			{12,0},
			{0,1},
			{14,15},
			{2,5},
			{-12,0},
			{0,-3},
			{-11,-8},
			{-4,-8}
		});
	}

	@Test
	public void test() {
		try {
			Etudiant e = new Etudiant(i);
			int arg = (Integer)this.arg;
			int res = e.compareTo(this.arg);
			String feedEq = MessageFormat.format(Translator.translate("Lorsque l''on appelle votre méthode sur l''Integer {0} avec comme argument {1}, votre méthode dit qu''ils sont égaux !"),i,arg);
			String feedLt = MessageFormat.format(Translator.translate("Lorsque l''on appelle votre méthode sur l''Integer {0} avec comme argument {1}, votre méthode dit que {0} < {1}"),i,arg);
			String feedGt = MessageFormat.format(Translator.translate("Lorsque l''on appelle votre méthode sur l''Integer {0} avec comme argument {1}, votre méthode dit que {0} > {1}"),i,arg);
			if(res == 0) {
				if(i < arg || i > arg)
					fail(feedEq);
			} else if( res < 0) {
				if(i == arg || i > arg)
					fail(feedLt);
			} else {
				if(i == arg || i < arg)
					fail(feedGt);
			}
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
