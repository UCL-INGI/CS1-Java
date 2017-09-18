
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

import StudentCode.Drapeau;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestParam1 {

	Drapeau d;
	boolean init;
	boolean arg;

	public TestParam1(Drapeau d,boolean init,boolean arg) {
		this.d = d;
		this.init = init;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{new Drapeau(true),true,true},
			{new Drapeau(true),true,false},
			{new Drapeau(false),false,true},
			{new Drapeau(false),false,false}
		});
	}

	@Test
	public void test() {
        String pre = "@1 :\n";
		try {
			d.set(this.arg);
			String msg = pre + _("Test 1 : lorsque l''on exécute votre méthode set({0}) sur un drapeau avec l''état {1}, l''état du drapeau devient {2}");
			String feed = MessageFormat.format(msg,arg,init,d.toBoolean());
			assertThat(feed,d.toBoolean(),is(arg));
        }catch (ArithmeticException e){
            fail(pre + _("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(pre + _("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(pre + _("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(pre + _("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(pre + _("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(pre + _("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(pre + _("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(pre + _("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
