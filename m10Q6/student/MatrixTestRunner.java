package student;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * @author ogoletti
 * @version 01/12/16
 */
public class MatrixTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MatrixTest.class, MatrixTestMock.class);
        for (Failure failure: result.getFailures()) {
            System.err.println(failure.getTestHeader() + " : " + failure.getException().toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("Tous les tests se sont passés sans encombre");
            System.exit(127);
        }
    }
}
