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
import student.ComputerIF;
import StudentCode.Cluster;
import student.UnavailableException;
import student.BasicComputer;

public class Tests {
		
		/**
		 * Test de la méthode removeComputer.
		 */
		@Test
		public void testRemoveComputer(){
			try{
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

			int oldCount = cl.get__C_oou__n_t();
			//remove un computer qui est dans le cluster assertTrue
			assertTrue("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", cl.removeComputer(bc2));
			//assert que count est décrémenté
			assertTrue("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1 == cl.get__C_oou__n_t());
			//assert que second remove du meme comp echoue
			assertTrue("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false == cl.removeComputer(bc2));
			
			oldCount = cl.get__C_oou__n_t();
			//remove un autre computer qui est dans le cluster assertTrue
			assertTrue("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", cl.removeComputer(bc3));
			//assert que count est décrémenté
			assertTrue("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1 == cl.get__C_oou__n_t());
			//assert que second remove du même comp échoue
			assertTrue("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false == cl.removeComputer(bc3));
			
			//remove seul computer qui est dans le cluster assertTrue
			assertTrue("Le retrait d'un Cluster de son unique Computer "
					+ "devrait revoyer true",true == cl.removeComputer(bc1) );
			//assert que count est décrémenté
			assertTrue("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut count devrait être égal à zéro",0 == cl.get__C_oou__n_t());
			//assert que le current est mis à null
			assertTrue("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut current devrait être mis à null", null == cl.get_____Ccur_ren_rt__() );
			//assert que le second remove du meme comp echoue
			assertTrue("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false == cl.removeComputer(bc1));
                    
  			//réajouter 2 comp
			cl.addComputer(bc1);
			cl.addComputer(bc2);
			//mémoriser le current
			ComputerIF oldcurr = cl.get_____Ccur_ren_rt__().getElem();
			//retirer le current
			cl.removeComputer(cl.get_____Ccur_ren_rt__().getElem());
			//assert que le current a la bonne valeur.
			assertTrue("La référence de l'ordinateur current "
					+ "devrait être correctement mise à jour lorsque cela est nécessaire. "
					, oldcurr.equals(cl.get_____Ccur_ren_rt__().getElem()));

			}
			catch(Throwable e){
				if(!(e instanceof AssertionError)){
					fail("Votre code provoque une exception: "+e.toString());
				}else{
                	throw e;
                }
			}


		}
	}