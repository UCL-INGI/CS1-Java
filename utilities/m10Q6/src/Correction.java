package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import StudentCode.*;
/**
 * @author ogoletti
 * @version 30/11/16
 */
public class Correction {
    /**
     * @pre     filename != null
     * @post    lit dans le fichier dont le nom est fileName le contenu d'une matrice mxn
     *          le format du fichier est :
     *          première ligne : l'entier m
     *          deuxième ligne : l'entier n
     *          lignes suivantes : une chaîne du format i,j val
     *                             où 0<=i<=m et à<=j<=n et val est un réel
     *          En cas d'erreur d'entrée/sortie (exception, fichier non lisible,
     *          nombre incorrect d'éléments, mauvais format) retourne null
     */
    public static double[][] loadMatrix(String filename) {
        BufferedReader bf = null;
        double[][] matrix;
        try {
            bf = new BufferedReader(new FileReader(filename));
            String line = null;

            // read m and n
            int m = 0, n = 0;
            try {
                if ((line = bf.readLine()) == null) return null;
                m = Integer.parseInt(line);
                if ((line = bf.readLine()) == null) return null;
                n = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                return null;
            }

            matrix = new double[m][n];

            int i = 0, j = 0, elemCount = 0;
            double val = 0.0;
            while ((line = bf.readLine()) != null) {
                try {
                    String[] matrixElem = line.split(" ");
                    if (matrixElem.length != 2) return null;

                    try {
                        // read i and j
                        String[] ij = matrixElem[0].split(",");
                        if (ij.length != 2) return null;
                        i = Integer.parseInt(ij[0]);
                        j = Integer.parseInt(ij[1]);
                        if (!((i>=0 && i<m) && (j>=0 && j<n))) return null; // out of bounds

                        //read val
                        val = Double.parseDouble(matrixElem[1]);

                        //set val
                        matrix[i][j] = val;
                        elemCount++;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            if (elemCount!=m*n) return null; // wrong number of elements elements
        } catch (IOException e) {
            return null;
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return matrix;
    }
}
