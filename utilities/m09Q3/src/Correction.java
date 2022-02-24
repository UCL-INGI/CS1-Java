package src;

import java.io.*;
import src.librairies.ConstructException;
import java.io.IOException;
/**
 * Classe pour acceuilir le code de l'étudiant
 * @author Dubray Alexandre
 */
class Correction {

	public static int countLines(String fileName) throws ConstructException,IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		try{
			int count=0;
			String line;
			while((line=br.readLine())!=null)
				count++;
			br.close();
			return count;
		} catch (IOException e) {
			br.close();
			return -1;
		}
	}
}
