
/**
 *  Copyright (c)  2016 Ludovic Taffin
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
package student;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;

public class Mission4 {
	
	private static String str = "Le code semble comporter des erreurs : ";
	
	@Test
    	public void testContainsOnly() {
       
		String s="abcdefabbba";
		String c="bcdefa";
		String msg;
		boolean val;
		
		val=true;
		msg="Pour la chaîne de caractères "+s+", containsOnly(\""+s+"\", \""+c+"\") retourne "+Mission4Stu.containsOnly(s,c)+" alors que la réponse attendue est "+val+"\n";
		assertEquals(msg,Mission4Stu.containsOnly(s,c),val); 
		
		s="abcdef";
		c="xyz";
		val=false;
		msg="Pour la chaîne de caractères "+s+", containsOnly(\""+s+"\", \""+c+"\") retourne "+Mission4Stu.containsOnly(s,c)+" alors que la réponse attendue est "+val+"\n";
		assertEquals(msg,Mission4Stu.containsOnly(s,c),val); 
	      
		s="abbxabab";
		c="ba";
		val=false;
		msg="Pour la chaîne de caractères "+s+", containsOnly(\""+s+"\", \""+c+"\") retourne "+Mission4Stu.containsOnly(s,c)+" alors que la réponse attendue est "+val+"\n";
		assertEquals(msg,Mission4Stu.containsOnly(s,c),val); 
		
		s="xabbabab";
		c="ba";
		val=false;
		msg="Pour la chaîne de caractères "+s+", containsOnly(\""+s+"\", \""+c+"\") retourne "+Mission4Stu.containsOnly(s,c)+" alors que la réponse attendue est "+val+"\n";
		assertEquals(msg,Mission4Stu.containsOnly(s,c),val); 
		
	       s="abbababx";
		c="ba";
		val=false;
		msg="Pour la chaîne de caractères "+s+", containsOnly(\""+s+"\", \""+c+"\") retourne "+Mission4Stu.containsOnly(s,c)+" alors que la réponse attendue est "+val+"\n";
		assertEquals(msg,Mission4Stu.containsOnly(s,c),val); 
    	}
	

	
	// Code verificateur
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Mission4.class);
		for (Failure failure: result.getFailures()) {
			System.err.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Tous les tests se sont passés sans encombre");
			System.exit(127);
		}
	}
}
