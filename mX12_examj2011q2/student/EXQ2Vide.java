package student;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.Assert;

import java.util.Iterator;
import java.util.Random;


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
	EXQ2Stu.EXQ2Tests.class
})
public class EXQ2Stu {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(EXQ2Tests.class);
		Iterator<Failure> failures = result.getFailures().iterator();
		Failure f;
		while(failures.hasNext()){
			f = failures.next();
			System.err.println(f.getMessage());
			//System.err.println(f.getTrace());
		}
		if(result.wasSuccessful() == true){
			//System.out.println(true);
			/**127 : nombre magique afin de signaler que tout les tests sont passés */
			System.exit(127);
		}
	}

	public static class EXQ2Tests extends junit.framework.TestCase{
		
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
					+ "un nombre de processus supportés incorrect. ", numProc, fc.procs.length);
			}
			catch(NullPointerException e){
				fail("Un objet FullComputer est créé avec "
						+ "un nombre de processus supportés incorrect: "+e.toString()+". Vérifiez que vous initialisez bien toutes les variables d'instance.");
			}
			assertEquals("Un objet FullComputer est créé avec "
					+ "une capacité maximale de stockage incorrecte. ", maxStorage, fc.storage);
			assertEquals("Un objet est créé avec "
					+ "une capacité de stockage disponible incorrecte. ", maxStorage, fc.availStorage);
			assertEquals("Un objet FullComputer est créé avec "
					+ "un nombre de processus présents incorrect. ", 0, fc.count);
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
	 *       et avec une capacité de stockage égale à storage
	 */
	
    @@q1@@

	/**
	 * @pre  p != null, p ne se trouve pas déjé sur cet ordinateur
	 * @post le processus p a été ajouté é cet ordinateur, si (1) le nombre de
	 *       processus maximal n'est pas atteint et (2) la capacité de stockage
	 *       nécessaire pour p est disponible. Retourne true si le processus a
	 *       été ajouté, false sinon.
	 */
	public boolean addProcess(Process p)
	{
		assert p!=null;
		if( (p.getRequiredStorage() > availStorage) 
				|| (count == procs.length) ) 
		{
			// pas assez de ressources
			return false;
		} 
		else 
		{        
			procs[count] = p;
			count++;
			availStorage = availStorage - p.getRequiredStorage();
			return true;
		}
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
