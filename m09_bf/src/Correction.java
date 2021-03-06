
/**
 *  Copyright (c) 2017 Brandon Naitali
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
import java.io.*;
import java.util.ArrayList;

public class Correction{
	/**
	 * @pre filename != null, filename est le nom d’un fichier de texte.
	 *      Chaque ligne valide du fichier représente un seul nombre réel.
	 * @post - La valeur renvoyée contient le nombre flottant avec la plus grande valeur
	 *         parmi tous les nombres flottants valides du fichier.
	 *         S'il n'y a aucun nombre valide, la valeur renvoyée contient -∞.
	 *       - Si le fichier n’existe pas ou si une erreur d'entrée/sortie survient,
	 *         le programme se termine en affichant ERREUR System.err.
	 *
	 *       Par exemple, la méthode renvoie 10.0 pour le contenu de fichier suivant :
	 *          0.345.67
	 *          hello
	 *          -543.0
	 *          500.0 1000.0 2000.0
	 *          10.0
	 *          3.1416
	**/
	public static double getMax (String filename){
		ArrayList<Double> al = new ArrayList<Double>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
            		String thisLine;
			while ((thisLine = br.readLine()) != null) {
				try{
					al.add(Double.parseDouble(thisLine));
				}catch(NumberFormatException e){
				}
			} 
		}catch (IOException e) {
			System.err.println("ERREUR");
		}
		Double max = (Double.NEGATIVE_INFINITY); 
		for(Double i: al){
			if(i > max) max = i;
		}
		return max;
	}
}
