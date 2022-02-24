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
	 * 	@post	Génère un presque-palindrome avec des caractères random.
	 */
	public static String generatePresquePalindrome(int length){
		String interference = generateNumberOrLetter(3, false);
		String random = generateNumberOrLetter(length/2, false);
		String base = (length % 2 == 0) ? random : random.substring(0, random.length()-1) ;
		String reverse = new StringBuilder(base).reverse().toString();
		return random + interference+ reverse;
	}
	/**
	 * 	@pre	-
	 * 	@post	Génère un palindrome avec des caractères random.
	 */
	public static String generatePalindrome(int length){
		String random = generateNumberOrLetter(length/2, false);
		String base = (length % 2 == 0) ? random : random.substring(0, random.length()-1) ;
		String reverse = new StringBuilder(base).reverse().toString();
		return random + reverse;
	}
	/**
	 * 	@pre	-
	 * 	@post	Génère un String avec uniquement des 1 et des 0.
	 */
	public static String generateBinaryString(int length){
		String s = "";
		Random r = new Random();
		for(int i = 0 ; i < length ; i++){
			s += (char)(r.nextInt(2) + 48); // génère soit 0 soit 1
		}
		return s;
	}
	/**
	 * 	@pre	-
	 * 	@post	Génère un String aléatoirement, de taille length soit composé de lettres alphabétique soit de chiffres.
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
