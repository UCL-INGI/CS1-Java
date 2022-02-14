package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A class representing a vector
 *
 * @author ogoletti
 * @version 29/11/16
 */
public class Correction {

    /**
     * @pre filename != null
     * @post lit dans le fichier dont le nom est fileName le contenu du vecteur v
     *       le format du fichier est : un élément par ligne en commençant par v[0] ...
     *     Les lignes à un format incorrect sont ignorées.
     *     En cas d'erreur d'entrée/sortie (exception, fichier non lisible,
     *     nombre incorrect d'éléments ) retourne null
     */
    public static double[] loadVector(String filename) {
        BufferedReader bf = null;
        ArrayList<Double> vectorAL;
        double[] vector = null;
        try {
            bf = new BufferedReader(new FileReader(filename));
            vectorAL = new ArrayList<Double>();
            String line = null;
            while((line=bf.readLine())!=null) {
                try {
                    vectorAL.add(Double.parseDouble(line));
                } catch (NumberFormatException e) {
                    // ignore line
                }
            }
            vector = new double[vectorAL.size()];
            for (int i = 0; i < vector.length; i++) {
                vector[i] = vectorAL.get(i);
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (bf!=null){
                try{
                    bf.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return vector;
    }

}
