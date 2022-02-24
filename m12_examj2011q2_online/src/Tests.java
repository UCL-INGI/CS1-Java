package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import StudentCode.FullComputer;
import student.Process;


public class Tests {
    

    
		/**
		 * Test du constructeur de la classe FullComputer.
		 */
		@Test
		public void testFullComputerConstructer(){

			String name = "Eniac";
			int numProc = 10;
			int maxStorage  =  4096;
			FullComputer fc = new FullComputer(name, numProc, maxStorage);
			try{
			assertTrue("Un objet FullComputer est créé avec "
					+ "un nom incorrect.", fc.getName().equals("Eniac"));
			}catch(NullPointerException e){
				fail("Un objet FullComputer est créé avec "
						+ "un nom incorrect: "+e.toString());
			}
			try{
			assertEquals("Un objet FullComputer est créé avec "
					+ "un nombre de processus supportés incorrect. ", numProc, fc.getProcs().length);
			}
			catch(NullPointerException e){
				fail("Un objet FullComputer est créé avec "
						+ "un nombre de processus supportés incorrect: "+e.toString()+". Vérifiez que vous initialisez bien toutes les variables d'instance.");
			}
			assertEquals("Un objet FullComputer est créé avec "
					+ "une capacité maximale de stockage incorrecte. ", maxStorage, fc.getStorage());
			assertEquals("Un objet est créé avec "
					+ "une capacité de stockage disponible incorrecte. ", maxStorage, fc.getAvailStorage());
			assertEquals("Un objet FullComputer est créé avec "
					+ "un nombre de processus présents incorrect. ", 0, fc.getCount());
		}
	}