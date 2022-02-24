package src;

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.junit.Test;
import static org.junit.Assert.*;

import StudentCode.Stack;

import student.StackIF;
import student.State;
import student.EmptyStackException;

/**
 * Testing the basic method of the class Stack 
 * 
 * @author Fabien Duchêne
 * @version 22 Dec 2012
 */
public class Tests
{
	private static PrintWriter writer;

    @Test
    public void testPush()
    {
    	Stack s1 = new Stack();
    	
    	State st1 = new State (50.0, 80.9, 16.0);
    	State st2 = new State (20.0, 90.7, 36.2);
    	
    	/* Test 1: try to push a first state */
		s1.push(st1);
		
		if (s1.size() != 1) {
 			writeReport(false, "Votre méthode push ou votre méthode size ne fonctionnent pas (size = "+s1.size()+") après l'ajout d'un State.");
			return;
		}
		
		/* Test 2: try to push a second state */
		s1.push(st2);
		if (s1.size() != 2) {
 			writeReport(false, "Votre méthode push ou votre méthode size ne fonctionnent pas (size = "+s1.size()+") après l'ajout d'un State.");
			return;
		}
		
		try {
			State stTest = (State) s1.pop();
		
			/* Test 3: look what has been pushed  */
			if (!stTest.equals(st2)){
				writeReport(false, "Votre méthode pop ou votre méthode push ne fonctionnent pas (un état different de l'état attendu a été retourné par pop)");
				return;
			}
			
			stTest = s1.pop();
			if (!stTest.equals(st1)) {
				writeReport(false, "Votre méthode pop ou votre méthode push ne fonctionnent pas (un état different de l'état attendu a été retourné par pop)");
				return;
			}
						
    	} catch (Exception e) {
    	    writeReport(false, "Votre méthode pop ou votre méthode push ne fonctionnent pas (Exception lancée lors du pop alors que la pile doit contenir deux états).");
			return;
    	}
    	/* If we reach this point tests are ok. */
    	writeReport(true, "Votre méthode push semble fonctionner correctement.");
        
        assertTrue(true);
    }
    
    @Test
    public void testPop() {
    	Stack s1 = new Stack();
    	
    	/* Test 1: try to pop on an empty stack */
    	try {
    		State t1 = (State) s1.pop();
    		throw new FailedTestException();
    	} catch (EmptyStackException ese) {
    	} catch (FailedTestException fte) {
    		writeReport(false, "Votre méthode pop devrait lancer une EmptyStackException lorsque la pile est vide.");
    		return;
    	}
    	/****** END OF TEST 1 ********/
    	writeReport(true, "Votre méthode pop semble fonctionner correctement.");
		assertTrue(true);
    	/* The rest of the method has already been tested by testPush */
    }

    @Test
	public void testSize() {
    	Stack s1 = new Stack();
    	
    	if (s1.size() != 0) {
    	 	writeReport(false, "Votre méthode size ne fonctionne pas (size = "+s1.size()+") après création du Stack.");
			return;
    	}
    	
    	/* Test 1: try to push a first state */
		s1.push(new State (50.0, 80.9, 16.0));
		s1.push(new State (20.0, 90.7, 36.2));
		
		if (s1.size() != 2) {
 			writeReport(false, "Votre méthode push ou votre méthode size ne fonctionnent pas (size = "+s1.size()+") après l'ajout de deux objets State.");
			return;
		}	
		
		try {
			s1.pop();
			s1.pop();
		} catch (Exception e) {
			/* Ignored here, should be détécted by the testPop method */
    	}
    	
    	if (s1.size() != 0) {
    	 	writeReport(false, "Votre méthode size ne fonctionne pas (size = "+s1.size()+") après vidage du Stack (peut arriver lorsque pop() ne fonctionne pas.");
			return;
    	}
    	writeReport(true, "Votre méthode size semble fonctionner correctement.");
        assertTrue(true);
	}
	
    @Test
	public void testIsEmpty() {
		Stack s1 = new Stack();
		
		if (!s1.isEmpty()) {
  	 		writeReport(false, "Votre méthode isEmpty ne fonctionne pas, elle retourne false juste après la création du Stack.");
			return;		
		}
		
		s1.push(new State (50.0, 80.9, 16.0));
		
		if (s1.isEmpty()) {
  	 		writeReport(false, "Votre méthode isEmpty ne fonctionne pas, elle retourne true juste après l'ajout d'un State au Stack.");
			return;		
		}
		
		try {
			s1.pop();
		} catch (Exception e) {
			/* Ignored here, should be détécted by the testPop method */
    	}
		
		if (!s1.isEmpty()) {
  	 		writeReport(false, "Votre méthode isEmpty ne fonctionne pas, elle retourne true juste après vidage du Stack (peut arriver si pop() ne fonctionne pas).");
			return;		
		}
		writeReport(true, "Votre méthode isEmpty semble fonctionner correctement.");
        assertTrue(true);
	}
	
    @Test
	public void testPeek() {
    	Stack s1 = new Stack();
    	
    	State st1 = new State (50.0, 80.9, 16.0);
    	State st2 = new State (20.0, 90.7, 36.2);
    	State st3 = new State (10.0, 60.0, 34.1);
    	State st4 = new State (11.0, 12.0, 08.0);

		s1.push (st1);
		s1.push (st2);
		s1.push (st3);
		s1.push (st4);

		if (!s1.peek(0).equals(st4)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 0.");
			return;				
		}
		
		if (!s1.peek(1).equals(st3)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 1.");
			return;				
		}
		
		if (!s1.peek(2).equals(st2)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 2.");
			return;				
		}
		
		if (!s1.peek(3).equals(st1)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 3.");
			return;				
		}
		try {
			s1.pop();
			s1.pop();
		} catch (Exception e) {
			/* Should not happend but should be détécted by testPop */
		}
		
		if (!s1.peek(0).equals(st2)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 0 après avoir retiré d'autres objets.");
			return;				
		}
		
		if (!s1.peek(1).equals(st1)) {
  	 		writeReport(false, "Votre méthode peek ne fonctionne pas, elle retourne un objet State inattendu en position 1 après avoir retiré d'autres objets.");
			return;				
		}
		writeReport(true, "Votre méthode peek semble fonctionner correctement.");
        assertTrue(true);
	}
	
    public static void writeReport(boolean success, String message) {   	
    	if (!success)
    		fail(message);
    }
    
    public static class FailedTestException extends Exception {
  		public FailedTestException() { super(); }
  		public FailedTestException(String message) { super(message); }
  		public FailedTestException(String message, Throwable cause) { super(message, cause); }
  		public FailedTestException(Throwable cause) { super(cause); }
  	}
}