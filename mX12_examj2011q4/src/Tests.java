package src;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.*;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.util.Arrays;

import student.Process;
import StudentCode.FullComputer;

public class Tests {
		
		/**
		 * Test du constructeur de la classe FullComputer.
		 */
		@Test
		public void testaddProcess(){
			//informations du FullComputer test
			String cname = "Eniac";
			int numProc = 10;
			int maxStorage  =  4096;
			//creation du FullComputer test
			FullComputer fc = new FullComputer(cname, numProc, maxStorage);
			//informations du Processus test 1
			String pname = "bash";
			String pnameBig = "firefox";
			int pstorage = 128;
			int pstorageBig = 4097;
			//creation du procesus test 1
			Process p = new Process(pname, pstorage);
			//creation du procesus test 2
			Process pBig = new Process(pnameBig, pstorageBig);
			//information qui servira de référence
			int oldCount = fc.getCount();
			int oldStorage = fc.getAvailStorage();
			//ajout du petit processus
			boolean res = false;
			try{
				res = fc.addProcess(p);
			}
			catch(Throwable e){
				fail("L'ajout d'un processus à l'ordinateur provoque l'exception suivante: "+e.toString());
			}
			//asserts
			assertEquals("L'ajout réussi d'un processus à l'ordinateur devrait renvoyer true. ", true, res);
			assertEquals("Le nombre de processus d'un ordinateur après l'ajout d'un processus"
					+ " devrait être de incrémenté de 1", oldCount +1 ,fc.getCount() );
			assertTrue("L'ordinateur devrait avoir accès"
						+ " à un processus qui lui a été ajouté: "+p.toString()+"versus"+fc.getProcs()[fc.getCount()-1].toString(),
						p.equals(fc.getProcs()[fc.getCount()-1]));
			assertEquals("La capacité de stockage de l'ordinateur après ajout d'un processus plus petit"
					+ "est incorrecte.", oldStorage - p.getRequiredStorage() , fc.getAvailStorage());
			//ajout du grand processus
			oldCount = fc.getCount();
			oldStorage = fc.getAvailStorage();
			res = false;
			try{
				res = fc.addProcess(pBig);
			}
			catch(Throwable e){
				fail("L'ajout d'un processus \"trop grand\" à l'ordinateur provoque l'exception suivante: "+e.toString());
			}
			assertEquals("L'ajout non réussi d'un processus à l'ordinateur devrait renvoyer false. ", false, res );
			assertEquals("Le nombre de processus d'un ordinateur après une tentative "
					+ "d'ajout d'un processus \"trop grand\" est incorrecte. "
					, oldCount ,fc.getCount() );
			assertFalse("L'odinateur ne devrait pas avoir accès"
						+ " à un processus qui ne lui a pas été ajouté.",
						pBig.equals(fc.getProcs()[fc.getCount()-1]));
			assertEquals("La capacité de stockage de l'ordinateur après ajout d'un processus \"plus grand\" "
					+ "est incorrecte.", oldStorage , fc.getAvailStorage());
		}
	}