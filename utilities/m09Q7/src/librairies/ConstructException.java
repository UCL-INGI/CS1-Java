package src.librairies;

/**
 * Exception qui doit être lancée lorsque le constructeur est utilisé
 * (c.f. documentation mocking)
 */

public class ConstructException extends Exception {

	public ConstructException(String message) {
		super(message);
	}
}
