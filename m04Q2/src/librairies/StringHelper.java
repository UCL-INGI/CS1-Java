/**
 *  Copyright (c)  2017 Naitali Brandon
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

package src.librairies;
import java.util.Random;



public class StringHelper {
	/**
	 * 	@pre	-
	 * 	@post	Génère un String aléatoirement, de taille length, soit que des nombres si numbered est à true, soit que des lettres si à false.
	 */
	public static String generateNumberOrLetter(int length, boolean numbered){
		String s = "";
		Random r = new Random();
		for(int i = 0 ; i < length ; i++){
			char random = numbered ? (char) ((r.nextInt('	') + '0')) : (char) ((r.nextInt('z' - 'a') + 'a'));
			if(!s.contains(String.valueOf(random)))
				s += random;
			else{
				i--;			
			}
		}
		return s;
	}
	
	/**
	 *	@pre	-
	 *      @post	Renvoie un char non-alphabetique, de ' ' à '?' compris
	 */

	public static char generateRandomNotAlphabetic(){
		Random r = new Random();
		return (char) (r.nextInt(' ') + ' '); // un char de 32 à 64 , voir https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
	}
	/**
	 *	@pre	-
	 *      @post	Renvoie un char non-alphabetique, de ' ' à '?' compris
	 */

	public static char getRandomCharFrom(String s){
		Random r = new Random();
		return s.charAt(r.nextInt(s.length()));
	}
}
