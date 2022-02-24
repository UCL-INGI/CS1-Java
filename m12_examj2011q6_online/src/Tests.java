package src;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.*;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.util.Arrays;

import StudentCode.Cluster;
import student.Process;
import student.BasicComputer;
import student.ComputerIF;
import student.UnavailableException;

public class Tests {
		
		/**
		 * Test de la méthode removeComputer.
		 */
		@Test
		public void testRemoveComputer(){
			
			//créer un cluster
			Cluster cl  = new Cluster();
			//créér 3 BasicComputer
			BasicComputer bc1 = new BasicComputer("ThuringMachine");
			BasicComputer bc2 = new BasicComputer("Colossus");
			BasicComputer bc3 = new BasicComputer("MareNostrum");
			//remove un computer qui n'est pas dans le cluster assertFalse
			assertFalse("Le retrait d'un Cluster d'un Computer qui n'en fait pas partie "
					+ "devrait renvoyer false ", cl.removeComputer(bc2));

			//ajouter 3 computers
			cl.addComputer(bc2);
			cl.addComputer(bc3);
			cl.addComputer(bc1);

			int oldCount = cl.getCount();
			//remove un computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", true, cl.removeComputer(bc2));
			//assert que count est décrémenté
			assertEquals("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1, cl.getCount());
			//assert que second remove du meme comp echoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc2));
			
			oldCount = cl.getCount();
			//remove un autre computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", true, cl.removeComputer(bc3));
			//assert que count est décrémenté
			assertEquals("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1, cl.getCount());
			//assert que second remove du même comp échoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc3));
			
			//remove seul computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster de son unique Computer "
					+ "devrait revoyer true",true, cl.removeComputer(bc1) );
			//assert que count est décrémenté
			assertEquals("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut count devrait être égal à zéro",0, cl.getCount());
			//assert que le current est mis à null
			assertEquals("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut current devrait être mis à null", null, cl.get_____Ccur_ren_rt__() );
			//assert que second remove du meme comp echoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc1));

		}
}