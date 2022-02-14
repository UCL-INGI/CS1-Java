
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

	Employe e;
	Object o;
	boolean isZero;
	boolean isPositive;

	public TestParam(Employe e, Object o, boolean isZero, boolean isPositive) {
		this.e = e;
		this.o = o;
		this.isZero = isZero;
		this.isPositive = isPositive;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Employe("Dupont","Jean",1200),(Object) new Employe("Dupont","Jean",1200),true,true},
			{new Employe("Hubert","Albert",1100),(Object) new Employe("Hubert","Albert",1500),false,false},
			{new Employe("Hubert","Albert",1800),(Object) new Employe("Hubert","Albert",1500),false,true},
			{new Employe("Ule","Jacque",1700),(Object) new Employe("Ule","Brice",1700),false,true},
			{new Employe("Ule","Baudoin",1700),(Object) new Employe("Ule","Brice",1700),false,false},
			{new Employe("Grat","Yve",1650),(Object) new Employe("Frietz","Yve",1650),false,true},
			{new Employe("Grat","Yve",1650),(Object) new Employe("Troy","Yve",1650),false,false},
			{new Employe("Broki","George",1200),(Object) new Employe("Gaspard","Berta",1200),false,false},
			{new Employe("Zerg","Bill",1400),(Object) new Employe("Tru","Han",1400),false,true},
			{new Employe("Serk","Armand",2010),(Object) new Employe("Juh","Asna",3000),false,true}

		});
	}

	@Test
	public void test() {
		try {
			int res = e.compareTo(o);
			String feed = MessageFormat.format(Translator.translate("Lorsque l''on appelle votre méthode avec l''employé {0} sur l''objet {1}, votre méthode renvoie {2}. Relisez les spécifications !"),e,(Employe) o,res);
			if(isZero)
				assertThat(feed,res,is(0));
			else {
				if(isPositive)
					assertThat(feed,res > 0,is(true));
				else
					assertThat(feed,res < 0, is(true));
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
