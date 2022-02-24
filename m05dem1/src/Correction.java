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
package src;

public class Correction {

    /**
     * Crée un tableaux contenant n entiers
     * @param n
     */
    public static int[] createIntArray(int n){
        int[] v = new int[n];
        return v;
    }
    
    /**
     * Crée une matrice de l * k doubles
     * @param k #colones
     * @param l #lingnes
     * @return
     */
    public static double[][] createDoubleMatrix(int k, int l){
        double [][] m = new double[l][k];
        return m;
    }
    
    /**
     * @param m
     * @return le # de lignes de m
     */
    public static int getNumLines(double[][] m){
        int l = m.length;
        return(l);
    }
    
    /**
     * @param m
     * @return le # de lignes de m
     */
    public static int getNumColumns(double[][] m){
        int k = m[0].length;
        return(k);
    }
}
