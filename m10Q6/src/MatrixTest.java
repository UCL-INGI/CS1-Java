package src;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import StudentCode.*;
import student.Translations.Translator;
import java.text.MessageFormat;

/**
 * @author ogoletti
 * @version 30/11/16
 */
@RunWith(value = Parameterized.class)
public class MatrixTest extends TestCase {
    private String filename;
    private double[][] expectedMatrix;

    public MatrixTest(String filename, double[][] expectedMatrix) {
        this.filename = filename;
        this.expectedMatrix = expectedMatrix;
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"matrix_small.dat", new double[][]{{1.1, 1.2, 1.2}, {1.3, 1.5, 1.6}}},
                {"matrix_mixed.dat", new double[][]{{1.1, 1.2, 1.2}, {1.3, 1.5, 1.6}}},
                {"empty.dat", null},
                {"matrix_wrong_1.dat", null},
                {"matrix_wrong_2.dat", null},
                {"matrix_wrong_3.dat", null},
                {"matrix_wrong_4.dat", null},
                {"matrix_wrong_5.dat", null},
                {"matrix_wrong_6.dat", null},
                {"matrix_wrong_7.dat", null}
        });
    }

    /**
     * @pre fileName !=null
     * @post retourne le contenu du fichier sous la forme d'un String
     **/
    public static String fileToString(String fileName) {
        String ret = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String r;
            while ((r = br.readLine()) != null) {
                ret += r + "\n";
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    return null;
                }
        }

        return ret;

    }

    @Test
    public void testLoadMatrix() {
        String msg, msge;
        double[][] r = null;

        String filePath = filenameWithPath(filename);
        String fileToString = fileToString(filePath);
        msge = MessageFormat.format(Translator.translate("Lors de l''exécution de votre méthode loadMatrix avec comme argument un fichier contenant \n{0}, votre méthode a lancé une exception "), fileToString);
        try {
            r = Etudiant.loadMatrix(filePath);
            // r = Vector.loadVector("./student/" + file);
        } catch (Exception e) {
            fail(msge + e.getMessage());
        }
        msg = MessageFormat.format(Translator.translate("Lors de l''exécution de votre méthode loadMatrix avec comme argument un fichier contenant \n{0}\nvotre méthode a retourné le tableau {1}\nalors que le résultat attendu est {2}\n"), fileToString, matrixToString(r), matrixToString(expectedMatrix));
        assertTrue(msg, matrixToString(expectedMatrix) == matrixToString(r));
    }

    public static String matrixToString(double[][] m) {
        if (m == null) return null;
        String ret = "";
        ret += m.length + "\n";
        ret += m[0].length + "\n";
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                ret += i + "," + j + " " + m[i][j] + "\n";
            }
        }
        return ret;
    }

    private String filenameWithPath(String filename) {
        String path = filename;
        URL resource = this.getClass().getResource(filename);
        if (resource == null) return filename;
        try {
            path = Paths.get(resource.toURI()).toString();
        } catch (URISyntaxException e) {
        }
        return path;
    }
}
