package StudentCode;

/**
 * @author Dubray Alexandre
 */

import java.io.*;

public class Correction {

	/**
	 * @pre		v != null, fileName != null
	 * @post	Enregistre dans le fichier de nom 
	 *			fileName le contenu du vecteur v sous
	 *			le format suivant :
	 *				- Ligne 1 : la taille de v
	 *				- Lignes suivante : un élément de v par ligne en commençant par v[0]
	 */
	public static void saveVector(int [] v, String fileName) throws IOException{
		PrintWriter f = null;
		try {
			f = new PrintWriter(new FileWriter(fileName));
			f.println(v.length);
			for(int e : v)
				f.println(e);
		} catch(IOException e) {
			return;
		} finally {
			if (f != null)
				f.close();
		}
	}
}
