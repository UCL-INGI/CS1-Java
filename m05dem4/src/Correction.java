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

    /**
     * @pre a,b != null et a et b de même dimensions
     * @post retourne la matrice valant la somme des matrices a et b
     */
    public static int [][] add(int[][] a, int[][] b){
        assert a != null && b != null;
        assert a.length == b.length;
        for(int k = 0; k < a.length; k++){
            assert a[k].length == b[k].length;
        }
        int[][] c = new int[a.length][a[0].length];
        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++){
                c[i][j] = a[i][j] + b[i][j];
            }
        return c;
    }
}
