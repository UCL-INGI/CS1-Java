package src;

import java.io.*;
import src.librairies.ConstructException;

/**
 * @author Dubray Alexandre
 */
class Correction {

	/**
	 * @pre		-
	 * @post	Retourne true si le fichier de nom
	 *			filename contient le String s
	 */
	public static boolean contains(String s,String filename) throws ConstructException,IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		try {
			String line;
			while((line=br.readLine()) != null){
				if(line.contains(s)) {
					br.close();
					return true;
				}
			}
			br.close();
			return false;
		} catch(IOException e){
			br.close();
			return false;
		} 
	}
}
