package student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


/**
 * @author ogoletti
 * @version 01/12/16
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MatrixStu.class)
public class MatrixTestMock {

    public static final String FILENAME = "file.txt";

    @Test
    public void testLoadMatrixIOExceptionInReadLine() {
        try {
            //You can mock concrete classes, not just interfaces
            FileReader mockedFR = mock(FileReader.class);

            BufferedReader mockedBR = mock(BufferedReader.class);
            when(mockedBR.readLine()).thenThrow(new IOException());

            //stubbing
            PowerMockito.whenNew(FileReader.class).withArguments(FILENAME).thenReturn(mockedFR);
            PowerMockito.whenNew(BufferedReader.class).withArguments(mockedFR).thenReturn(mockedBR);

            String msg, msge;
            double[][] r = {{}};

            String msgError = "génère une IOExpetion";
            msge = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a lancé une exception ";
            try {
                r = MatrixStu.loadMatrix(FILENAME);
            } catch (Exception e) {
                fail(msge + e.toString() + " : " + e.getMessage());
            }
            msg = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a retourné le tableau " + MatrixTest.matrixToString(r) + " alors que le résultat attendu est null";
            assertNull(msg, r);

            try {
                verify(mockedBR, times(1)).close();
            } catch (MockitoAssertionError e) {
                throw new AssertionError("Lors de l'exécution de votre méthode loadVector avec une IOException lancée par le readLine() du BufferedReader, le fichier n'a pas été fermé correctement.", e);
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void testLoadMatrixIOExceptionInCtor() {
        try {
            //stubbing
            PowerMockito.whenNew(FileReader.class).withArguments(FILENAME).thenThrow(new IOException());

            String msg, msge;
            double[][] r = {{}};

            String msgError = "génère une IOExpetion";
            msge = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a lancé une exception ";
            try {
                r = MatrixStu.loadMatrix(FILENAME);
            } catch (Exception e) {
                fail(msge + e.toString() + " : " + e.getMessage());
            }
            msg = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a retourné le tableau " + MatrixTest.matrixToString(r) + " alors que le résultat attendu est null";
            assertNull(msg, r);

        } catch (Exception e) {
        }
    }


    @Test
    public void testLoadMatrixIOExceptionInClose() {
        try {
            //You can mock concrete classes, not just interfaces
            FileReader mockedFR = mock(FileReader.class);

            BufferedReader mockedBR = mock(BufferedReader.class);
            doThrow(new IOException()).when(mockedBR).close();

            //stubbing
            PowerMockito.whenNew(FileReader.class).withArguments(FILENAME).thenReturn(mockedFR);
            PowerMockito.whenNew(BufferedReader.class).withArguments(mockedFR).thenReturn(mockedBR);

            String msg, msge;
            double[][] r = {{}};

            String msgError = "génère une IOExpetion";
            msge = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a lancé une exception ";
            try {
                r = MatrixStu.loadMatrix(FILENAME);
            } catch (Exception e) {
                fail(msge + e.toString() + " : " + e.getMessage());
            }
            msg = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a retourné le tableau " + MatrixTest.matrixToString(r) + " alors que le résultat attendu est null";
            assertNull(msg, r);
        } catch (Exception e) {
        }
    }


    @Test
    public void testLoadMatrixUnknownFile() {
        try {
            String msg, msge;
            double[][] r = {{}};

            String msgError = "n'existe pas";
            msge = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a lancé une exception ";
            try {
                r = MatrixStu.loadMatrix(FILENAME);
            } catch (Exception e) {
                fail(msge + e.toString() + " : " + e.getMessage());
            }
            msg = "Lors de l'exécution de votre méthode loadMatrix avec un fichier qui " + msgError + ", votre méthode a retourné le tableau " + MatrixTest.matrixToString(r) + " alors que le résultat attendu est null";
            assertNull(msg, r);
        } catch (Exception e) {
        }
    }


}
