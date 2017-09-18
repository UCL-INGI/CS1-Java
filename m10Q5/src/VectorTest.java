package src;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import StudentCode.*;
import static student.Translations.Translator._;
import java.text.MessageFormat;
/**
 * @author ogoletti
 * @version 29/11/16
 */
@RunWith(value = Parameterized.class)
public class VectorTest extends TestCase {
    private String filename;
    private double[] expectedVector;

    public VectorTest(String filename, double[] expectedVector) {
        this.filename = filename;
        this.expectedVector = expectedVector;
    }

    // name attribute is optional, provide an unique name for test
    // multiple parameters, uses Collection<Object[]>
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"vector_small.dat", new double[]{1.1, 1.2, 1.2, 1.3, 1.5}},
                {"vector_small_wrong_line.dat", new double[]{1.1, 1.2, 1.3, 1.5}},
                {"empty.dat", new double[]{}}
        });
    }

    @Test
    public void testReadVector2() {
        String msg, msge;
        double[] r = null;

        msge = MessageFormat.format(_("Lors de l'exécution de votre méthode loadVector avec comme argument un fichier contenant \n{0}, votre méthode a lancé une exception "), vectorToString(expectedVector));
        try {
            r = Etudiant.loadVector(filenameWithPath(filename));
        } catch (Exception e) {
            fail(msge + e.getMessage());
        }
        msg = MessageFormat.format(_("Lors de l'exécution de votre méthode loadVector avec comme argument un fichier contenant \n{0}, votre méthode a retourné le tableau {1} alors que le résultat attendu est {2}"), vectorToString(expectedVector), Arrays.toString(r), Arrays.toString(expectedVector));
        assertTrue(msg, Arrays.equals(expectedVector, r));
    }


    private String vectorToString(double[] v) {
        if (v == null) return null;
        String ret = "";
        for (double elem : v) {
            ret += elem + "\n";
        }
        return ret;
    }

    private String filenameWithPath(String filename) {
        String path = filename;
        URL resource = this.getClass().getResource(filename);
        if (resource==null) return filename;
        try {
            path = Paths.get(resource.toURI()).toString();
        } catch (URISyntaxException e) {
        }
        return path;
    }

}
