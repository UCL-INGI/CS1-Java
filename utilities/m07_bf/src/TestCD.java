
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

import StudentCode.CD;;
import student.Translations.Translator;
import src.librairies.Inspector;

@RunWith(Parameterized.class)
public class TestCD {

	String author;
	String title;
	int length;
	String res;

	public TestCD(String author, String title, int length,String res) {
		this.author = author;
		this.title = title;
		this.length = length;
		this.res = res;
	}


	@Parameters
	public static Collection<Object []> data() {
		return Arrays.asList(new Object [][] {
			{"Sabaton","Panzerkampf",315,"[100001] Sabaton, Panzerkampf (315 s)"},
			{"DragonForce","Judgment Day",373,"[100002] DragonForce, Judgment Day (373 s)"},
			{"Infected Mushroom","Becoming Insane",440,"[100003] Infected Mushroom, Becoming Insane (440 s)"}
		});
	}

	private boolean check_construct(CD cd,String author, String title, int length) {
		Object [] instance_variables = Inspector.getAllInstanceValue(CD.class,cd);
		boolean found_auth = false, found_title = false, found_length = false;
		for(Object o : instance_variables) {
			if(o instanceof String) {
				String s = (String) o;
				if(s.equals(author))
					found_auth = true;
				if(s.equals(title))
					found_title = true;
			} 
			if(o instanceof Integer) {
                
				if((Integer)o == length)
					found_length = true;
			}
		}
		Object [] parent_instance_variables = Inspector.getAllParentInstanceValue(CD.class,cd);
		for(Object o : parent_instance_variables) {
			if(o instanceof String) {
				String s = (String) o;
				if(s.equals(author))
					found_auth = true;
				if(s.equals(title))
					found_title = true;
			} 
			if(o instanceof Integer) {
                
				if((Integer)o == length)
					found_length = true;
			}
		}
		return found_auth && found_title && found_length;
	}

	@Test
	public void test() {
		try {
			String resStu = null;
			try {
				CD cd = (CD) Inspector.run_construct_specified(CD.class,this.author,this.title,this.length);
				if(cd == null) {
					String failfeed =MessageFormat.format("@2 :\n" + Translator.translate("{0} : aucun constructeur avec comme premier paramètre un String, comme deuxième un String et comme troisème un int n''a été trouvé dans votre réponse !"),"Test 5");
					fail(failfeed);
				}
				if(!check_construct(cd,this.author,this.title,this.length)) {
					String feed = "@2 :\n" + Translator.translate("{0} : après avoir appelé votre constructeur avec les paramètre {1} (auteur), {2} (titre} et {3} (durée), il manque certaines variables d''instance avec ces valeurs !");
					fail(MessageFormat.format(feed,"Test 5",this.author,this.title,this.length));
				}
				resStu = (String) Inspector.run_method(cd,"toString",new Object []{});
			} catch(NoSuchMethodException e) {
				String feed = "@2 :\n"+ Translator.translate("La méthode toString() n''as pas été trouvée dans votre réponse !");
				fail(feed);
			}
			String msg = "@2 :\n" + Translator.translate("{0} : lorsque l''on exécute votre méthode toString() sur le CD ayant pour auteur {1}, titre {2}, et durée {3,number,#}, votre méthode retourne\n{4}\nau lieu de\n{5}");
			String feed = MessageFormat.format(msg,"Test 5",this.author,this.title,this.length,resStu,this.res);
			if(!resStu.equals(this.res))
				fail(feed);
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
