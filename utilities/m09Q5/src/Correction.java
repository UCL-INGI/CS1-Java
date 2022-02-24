package src;

import java.io.*;

/**
 * @author Dubray Alexandre
 */

public class Correction {

	/**
	 * @pre		-
	 * @post	Retourne true si le fichier de nom
	 *			filename est accessible (i.e. peut
	 *			être ouvert)
	 */
	public static boolean accessible(String fileName) {
		File f = new File(fileName);
    	return f.canRead();
 	}
}
