
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

public class Correction{
	/**
	 *	Renvoie 0 si l'écriture des n premiers entiers positifs dans le fichier filename s'est bien passée
	 *		-1 sinon 
	 */
	public static int write(int n, String filename) throws IOException{
		try{
		    File ff = new File(filename);
		    FileWriter ffw = new FileWriter(ff);
		    for(int i = 1; i <= n; i++)
				ffw.write(i + "\n");  
			ffw.close();
		}catch(IOException e){
		    return -1;
		}catch(Exception e){
		    return -2;
		}
		return 0;
	}
}
