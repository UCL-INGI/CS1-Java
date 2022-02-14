/**
 *  Copyright (c)  2017 Olivier Martin
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
     * @pre m != null
     * @post remplace toutes les d'occurences de la valeur v dans int[][] m par la valeur w
     *       Modifie la matrice m
     */
    public static void replace(int v, int w, int[][] m) {
        int count = 0;
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                if(m[i][j] == v)
                    m[i][j] = w;
            }
        }
    }
}
