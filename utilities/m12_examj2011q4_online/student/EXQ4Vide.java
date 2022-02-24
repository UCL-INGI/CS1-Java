package student;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;

import java.util.Iterator;


/**
 * Un exemple de test avec JUNIT
 *
 * @author Clémentine Munyabarenzi Juillet 2015
 * @original version O. Bonaventure Decembre 2008
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({

	/**
	 * Mettre ici la liste des classes tests
	 * qui    constituent    la   TesteSuite
	 */
	EXQ4Stu.EXQ4Tests.class
})
public class EXQ4Stu {
	public static void montest(){
	        @	@zmontest@@
	    }

	public static void main(String[] args) {
		/**
		Result result = JUnitCore.runClasses(EXQ4Tests.class);
		Iterator<Failure> failures = result.getFailures().iterator();
		Failure f;
		while(failures.hasNext()){
			f = failures.next();
			System.err.println(f.getMessage());
			//System.err.println(f.getTrace());
		}
		if(result.wasSuccessful() == true){
			//System.out.println(true);
			//127 : nombre magique afin de signaler que tout les tests sont passés
			System.exit(127);
		}*/
		montest();
		System.exit(127);
	}

	public static class EXQ4Tests extends junit.framework.TestCase{

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
			int oldCount = fc.count;
			int oldStorage = fc.availStorage;
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
					+ " devrait être de incrémenté de 1", oldCount +1 ,fc.count );
			assertTrue("L'ordinateur devrait avoir accès"
						+ " à un processus qui lui a été ajouté: "+p.toString()+"versus"+fc.procs[fc.count-1].toString(),
						p.equals(fc.procs[fc.count-1]));
			assertEquals("La capacité de stockage de l'ordinateur après ajout d'un processus plus petit"
					+ "est incorrecte.", oldStorage - p.getRequiredStorage() , fc.availStorage);
			//ajout du grand processus
			oldCount = fc.count;
			oldStorage = fc.availStorage;
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
					, oldCount ,fc.count );
			assertFalse("L'odinateur ne devrait pas avoir accès"
						+ " à un processus qui ne lui a pas été ajouté.",
						pBig.equals(fc.procs[fc.count-1]));
			assertEquals("La capacité de stockage de l'ordinateur après ajout d'un processus \"plus grand\" "
					+ "est incorrecte.", oldStorage , fc.availStorage);
		}
	}
}
		/**
		 * Un ordinateur avec capacité de stockage limitée et nombre de processus limité.
		 *
		 * @author O. Bonaventure, Ch. Pecheur
		 * @version Dec. 2007
		 */
		/*MODIFpublic*/ class FullComputer extends BasicComputer
		{
			/**
			 * Les processus présents sur cet ordinateur.  Les processus sont dans
			 * procs[0] .. procs[count-1], et procs[i] == null pour i >= count.
			 */
			/*MODIFprivate*/ Process[] procs;
			/*MODIFprivate*/ int count;         // nombre de processus présents
			/*MODIFprivate*/ int storage;       // capacité de stockage totale
			/*MODIFprivate*/ int availStorage;  // capacitéé de stockage restante

			/**
			 * @pre n > 0, name != null, storage >= 0
			 * @post Construit un FullComputer de nom name, supportant n processus
			 *       et avec une capacité de stockage égale é storage
			 */
			public FullComputer(String name, int n, int storage)
			{
				super(name);
				assert n>0;
				this.storage = storage;
				this.procs = new Process[n];
				this.count = 0;
				this.availStorage = storage;
			}

			/**
			 * @pre  p != null, p ne se trouve pas déjà sur cet ordinateur
			 * @post le processus p a été ajouté à cet ordinateur, si (1) le nombre de
			 *       processus maximal n'est pas atteint et (2) la capacité de stockage
			 *       nécessaire pour p est disponible. Retourne true si le processus a
			 *       été ajouté, false sinon.
			 */
			public boolean addProcess(Process p)
			{
				@@q1@@
			}

			/**
			 * @pre p != null
			 * @post le processus p a été retiré de cet ordinateur, si ce processus
			 *       se trouve sur cet ordinateur.  Retourne true si le processus
			 *       a été supprimé, false sinon.
			 */
			public boolean removeProcess(Process p)
			{
				for (int i = 0; i < count; i++)
				{
					if(procs[i].equals(p))
					{
						// trouvé p
						removeProcess(i);
						return true;
					}
				}
				// pas trouvé p
				return false;
			}

			/**
			 * @pre  0 <= index < count
			 * @post le processus procs[index] a été retiré.
			 */
			private void removeProcess(int index)
			{
				assert index >= 0;
				assert index < count;
				availStorage = availStorage + procs[index].getRequiredStorage();
				// décaler tous les éléments à droite de procs[index] vers la gauche
				for(int i = index; i < count-1; i++)
				{
					procs[i]=procs[i+1];
				}
				procs[count-1]=null;
				count--;
			}

			/**
			 * @pre -
			 * @post Tous les processus de cet ordinateur ont été retirés. Réinitialise
			 *       la liste des processus et la capacité disponible.
			 */
			public void removeAllProcesses()
			{
				for (int i = 0; i < count; i++)
				{
					procs[i] = null;
				}
				count = 0;
				availStorage = storage;
			}

			/**
			 * @pre -
			 * @post Retourne la liste des processus de cet ordinateur sous forme de texte,
			 *       avec une ligne par processus, chaque ligne comprenant le nom du processus
			 *       et sa taille de stockage, séparés par un espace, et se termine par
			 *       un passage à la ligne.  Par exemple:
			 *
			 *       process1 0
			 *       bigprocess 200
			 *       smallprocess 20
			 */
			public String getState()
			{
				String state = "";
				for (int i = 0; i < count; i++)
				{
					Process p = procs[i];
					state = state + p.toString() + "\n";
				}
				return state;
			}

			//////////////////////

			/**
			 * pour debugging
			 */
			public String toString()
			{
				return getName() + " (" + (procs.length-count) + "/" + procs.length + " procs, "
						+ availStorage + "/" + storage + " storage)";
			}

			
		}
