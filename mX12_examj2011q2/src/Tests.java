package src;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.*;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.util.Arrays;

import student.*;
import StudentCode.FullComputer;


public class Tests{
		
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
					+ "lolun nombre de processus supportés incorrect", numProc, fc.getProcs().length);
			}
			catch(NullPointerException e){
				fail("Un objet FullComputer est créé avec "
						+ "lolun nombre de processus supportés incorrect: "+e.toString()+". Vérifiez que vous initialisez bien toutes les variables d'instance.");
			}
			assertEquals("Un objet FullComputer est créé avec "
					+ "une capacité maximale de stockage incorrecte", maxStorage, fc.getStorage());
			assertEquals("Un objet est créé avec "
					+ "une capacité de stockage disponible in correcte", maxStorage, fc.getAvailStorage());
			assertEquals("Un objet FullComputer est créé avec "
					+ "lolun nombre de processus présents incorrect", 0, fc.getCount());
		}
	}
