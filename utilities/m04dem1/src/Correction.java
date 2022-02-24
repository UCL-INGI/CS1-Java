/**
 *  Copyright (c)  Francois Michel, 2017 Brandon Naitali
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

public class Correction{
	 
	/**
	 * @pre c est un caractère, n'importe lequel
	 * @post retourne true si c est un chiffre, false sinon
	 */
	 
	public static boolean isDigitStudent(char c){
		boolean b;
		b = Character.isDigit(c);
		return b;
	}
	
	/**
	 * @pre d est une lettre minuscule, n'importe laquelle
	 * @post retourne la majuscule de d
	 */
	 
	public static char toUpper(char d){
        	char e;
		e = Character.toUpperCase(d);
		return e;
	}
}

