
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

import StudentCode.ByteTab;
import static student.Translations.Translator._;

@RunWith(Parameterized.class)
public class TestSet {

	String input;
	int arg;
	String output;

	public TestSet(String input, int arg, String output) {
		this.input = input;
		this.arg = arg;
		this.output = output;
	}

	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{"00000000",3,"00010000"},
			{"11111111",2,"11111111"},
			{"10011101",0,"10011101"},
			{"10001001",7,"10001001"},
			{"10011010",3,"10011010"},
			{"10100110",1,"11100110"}
		});
	}

	private String reverse(String s){
		String str = "";
		for(int i=s.length()-1;i>=0;i--)
			str += s.charAt(i);
		return str;
	}

	@Test
	public void test() {
		String pre = "Test setBit : ";
		try {
			ByteTab bt = new ByteTab(reverse(this.input));
			bt.setBit(this.arg);
			String out = reverse(bt.toString());
			String msg = _("Test 2 : lorsque l''on appelle votre méthode setBit({0}) avec comme byte {1} (représentation en String), le byte devient {2} au lieu de {3}");
			String feed = MessageFormat.format(msg,this.arg,input,out,output);
			assertThat(feed,out,is(output));
		}catch (ArithmeticException e){
            fail(pre+_("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(pre+_("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(pre+_("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(pre+_("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(pre+_("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(pre+_("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(pre+_("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(pre+_("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
