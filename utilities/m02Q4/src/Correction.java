/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Brandon Naitali
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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Correction {

    /*
    * @pre : -
    * @post : Méthode pour imprimer un H
    *
    */
    public static String lettreH(int hauteur, int largeur){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
	// Solution //
        int mid = (hauteur/2);
        boolean milieu = false;
	for(int i = 0; i < hauteur; i++){
    		milieu = false;
    		if(i == Math.round(mid)) milieu = true;
    		for(int j = 0; j < largeur; j++){
    			if(j == 0 || j == largeur - 1 || milieu) System.out.print('*');
        		else System.out.print(' ');
    		}
    		System.out.println();
	}	
	///////////////
        System.setOut(old);
        return baos.toString();

    }
}
