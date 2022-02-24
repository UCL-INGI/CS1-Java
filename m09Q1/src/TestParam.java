
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

import StudentCode.Fraction;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestParam {

	Fraction f;
	Object o;
	boolean isZero;
	boolean isPositive;

	public TestParam(Fraction f, Object o, boolean isZero, boolean isPositive) {
		this.f = f;
		this.o = o;
		this.isZero = isZero;
		this.isPositive = isPositive;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Fraction(0,1),(Object) new Fraction(0,1),true,true},
			{new Fraction(1,1),(Object) new Fraction(1,1),true,true},
			{new Fraction(14,2),(Object) new Fraction(14,2),true,true},
			{new Fraction(13,3),(Object) new Fraction(13,3),true,true},
			{new Fraction(2,1),(Object) new Fraction(1,1),false,true},
			{new Fraction(12,2),(Object) new Fraction(4,2),false,true},
			{new Fraction(1,1),(Object) new Fraction(4,3),false,false},
			{new Fraction(5,4),(Object) new Fraction(10,4),false,false}
		});
	}

	@Test
	public void test() {
		try {
			int res = f.compareTo(o);
			String feed = MessageFormat.format(Translator.translate("Lorsque l''on appelle votre méthode avec la fraction {0} sur l''objet {1}, votre méthode renvoie {2}. Relisez les spécifications !"),f,(Fraction) o,res);
			if(isZero){
				if(res != 0)
					fail(feed);
			}
			else {
				if(isPositive){
					if(res < 0)
						fail(feed);
				} else {
					if(res > 0)
						fail(feed);
				}
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
