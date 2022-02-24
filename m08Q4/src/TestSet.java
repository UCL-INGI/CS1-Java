
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

import StudentCode.ByteString;
import student.Translations.Translator;

@RunWith(Parameterized.class)
public class TestSet {

	ByteString bs;
	int arg;
	String res;
	String org;

	private String reverse(String s){
		String r = "";
		for(int i=s.length()-1;i>=0;i--)
			r += s.charAt(i);
		return r;
	}

	public TestSet(String in,int arg,String out) {
		this.bs = new ByteString();
		this.bs.setSb(new StringBuilder(in));
		this.org = in;
		this.arg = arg;
		this.res = out;
	}

	/**
	 * On passe en paramètre la représentation naturelle des bytes car si les 
	 * étudiant n'ont pas bien fait le constructeur, ça ne doit pas influencer
	 * le reste des tests
	 */
	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{"00000000",2,"00100000"},
			{"11111111",4,"11111111"},
			{"10101010",7,"10101011"},
			{"11001011",7,"11001011"},
			{"10011010",0,"10011010"},
			{"01100101",0,"11100101"},
			{"11010110",3,"11010110"},
			{"10011011",2,"10111011"}
		});
	}

	@Test
	public void test() {
		String pre = "@3 :\n";
		try {
			String msg = "@3 :\n" + Translator.translate("Test 4 : lorsque l''on exécute votre méthode setBit({0}) sur le ByteString dont la représentation en ByteString est {1}, le chaîne devient {2} au lieu de {3}.");
			this.bs.setBit(arg);
			String feed = MessageFormat.format(msg,this.arg,this.org,this.bs.getRep(),res);
			assertThat(feed,this.bs.getRep(),is(res));
		}catch (ArithmeticException e){
            fail(pre + Translator.translate("Attention, il est interdit de diviser par zéro."));
        }catch(ClassCastException e){
            fail(pre + Translator.translate("Attention, certaines variables ont été mal castées !"));
        }catch(StringIndexOutOfBoundsException e){
            fail(pre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
        }catch(ArrayIndexOutOfBoundsException e){
            fail(pre + Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
        }catch(NullPointerException e){
            fail(pre + Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
        }catch(NegativeArraySizeException e){
            fail(pre + Translator.translate("Vous initialisez un tableau avec une taille négative."));
        }catch(StackOverflowError e){
            fail(pre + Translator.translate("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même."));
        }catch(Exception e){
            fail(pre + Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
        }
	}
}
