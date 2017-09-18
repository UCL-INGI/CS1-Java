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
package src;

public class Correction {
   /*
    * @pre : n >= 0 
    * @post : Calcul la somme des n premiers entiers pairs supérieurs à 0
    *
    */
    public static int sommeEntiersPairs(int n){
	int sum = 0;
	for(int i = 1; i <= n; i++){
		sum = sum + (2 * i);
	}
	return sum;
    }
}

