package student;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	EXQ6Stu.EXQ6Tests.class
})
public class EXQ6Stu {
	public static void montest(){
			@	@zmontest@@
	}
	public static void main(String[] args) {

		/**Result result = JUnitCore.runClasses(EXQ6Tests.class);
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

	public static class EXQ6Tests extends junit.framework.TestCase{

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

			int oldCount = cl.count;
			//remove un computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", true, cl.removeComputer(bc2));
			//assert que count est décrémenté
			assertEquals("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1, cl.count);
			//assert que second remove du meme comp echoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc2));

			oldCount = cl.count;
			//remove un autre computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster d'un Computer qui en fait partie "
					+ "devrait renvoyer true", true, cl.removeComputer(bc3));
			//assert que count est décrémenté
			assertEquals("Après avoir retiré un Computer d'un Cluster "
					+ "son attribut count devrait être décrémenté de 1", oldCount-1, cl.count);
			//assert que second remove du même comp échoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc3));

			//remove seul computer qui est dans le cluster assertTrue
			assertEquals("Le retrait d'un Cluster de son unique Computer "
					+ "devrait revoyer true",true, cl.removeComputer(bc1) );
			//assert que count est décrémenté
			assertEquals("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut count devrait être égal à zéro",0, cl.count);
			//assert que le current est mis à null
			assertEquals("Après avoir retiré l'unique Computer d'un Cluster "
					+ "son attribut current devrait être mis à null", null, cl.current );
			//assert que le second remove du meme comp echoue
			assertEquals("Le retrait d'un Cluster d'un Computer qui n'en fait plus partie "
					+ "devrait renvoyer false ", false, cl.removeComputer(bc1));

  			//réajouter 2 comp
			cl.addComputer(bc1);
			cl.addComputer(bc2);
			//mémoriser le current
			ComputerIF oldcurr = cl.current.elem;
			//retirer le current
			cl.removeComputer(cl.current.elem);
			//assert que le current a la bonne valeur.
			assertFalse("La référence de l'ordinateur current "
					+ "devrait être correctement mise à jour lorsque cela est nécessaire. "
					, oldcurr.equals(cl.current.elem));

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
}

		class Cluster
		{
		    // classe interne: un noeud de la liste circulaire des ordinateurs du cluster
		    /*private*/ class ListNode {
		        ListNode next;
		        ComputerIF elem;
		    }

		    /**
		     * La tête courante de la liste des ordinateurs. Les noeuds suivants sont
		     * chaînés de manière circulaire: la chaîne finit toujours par revenir à
		     * current.
		     */
		    /*private*/ ListNode current;
		    /*private*/ int count; // nombre d'ordinateurs dans le cluster

		    /**
			 * Constructeur
			 */
		    public Cluster()
		    {
		        count = 0;
		        current = null;
		    }

		    /**
		     * @pre p != null, p ne se trouve pas déjà sur un ordinateur du cluster.
		     * @post Le processus p a été ajouté au premier ordinateur, à partir de la
		     *       téte de la liste, disposant des ressources nécessaires. La nouvelle
		     *       téte de liste est le noeud qui suit celui de l'ordinateur où p a
		     *       été ajouté. Si aucun ordinateur ne dispose de ressources
		     *       suffisantes, la téte de liste est inchangée et une
		     *       UnavailableException est lancée.
			 */
		    public void addProcess(Process p) throws UnavailableException
		    {
		    	assert p != null;

		    	int i = 0;
		    	ListNode c = current;
		    	while (i < count)
		    	{
		    		boolean ok = c.elem.addProcess(p);
		    		if (ok)
		    		{
		    			// p a été ajouté
		    			current = c.next;
		    			return;
		    		}
		    		c = c.next;
		    		i++;
		    	}
		    	// on a fait le tour de la liste
		    	throw new UnavailableException();
		    }

		    /**
		     * @pre p != null
		     * @post Le processus p a été retiré du premier ordinateur du cluster
		     *       sur lequel il se trouvait, à partir de la téte de la liste.
		     *       Si p n'est pas trouvé, lance UnavailableException.
		     */
		    public void removeProcess(Process p) throws UnavailableException
		    {
		    	assert p != null;

		    	int i = 0;
		    	ListNode c = current;
		    	while (i < count)
		       {
		    	  boolean ok = c.elem.removeProcess(p);
		          if (ok)
		          {
		        	  // p a été retiré
		        	  return;
		          }
		          c = c.next;
		          i++;
		       }
		    	// on a fait le tour de la liste
		    	throw new UnavailableException();
		    }

		    /**
		     * @pre -
		     * @post Tous les processus de tous les ordinateurs ont été retirés.
		     */
		    public void removeAllProcesses()
		    {
		    	int i = 0;
		    	ListNode c = current;
		    	while (i < count)
		       {
		    		c.elem.removeAllProcesses();
		    		c = c.next;
		    		i++;
		       }
		    }

		    /**
		     * @pre  comp != null, comp ne fait pas déjà partie du cluster.
		     * @post L'ordinateur comp est ajouté à la liste des ordinateurs.
		     */
		    public void addComputer(ComputerIF comp)
		    {
		        assert comp != null;
		        ListNode l = new ListNode();
		        l.elem = comp;
		       if(count == 0)
		        {
		    	   l.next = l;
		    	   current = l;
		        }
		        else
		        {
		            l.next = current.next;
		            current.next = l;
		        }
		        count++;
		    }

		    /**
		     * @pre comp != null
		     * @post L'ordinateur comp a été retiré du cluster, s'il s'y trouvait. Si
		     *       comp est en téte de liste, la téte de liste passe au noeud suivant,
		     *       sinon elle est inchangée. Retourne true si comp a été retiré, false
		     *       sinon.
		     */

                @@q1@@


		    /**
		     * @pre filename est un nom de fichier
		     * @post Le fichier filename contient l'état du cluster sous forme de texte.
		     *       Pour chaque processus de chaque ordinateur du cluster, le fichier
		     *       contient une ligne composée du nom et de la capacité demandée,
		     *       séparés par un espace. Le nom des ordinateurs sur lesquels se
		     *       trouvent les processus n'est pas sauvegardé. Arrête le programme si
		     *       une erreur d'I/O se produit.
		     */
		    public void saveState(String filename)
		    {
		        assert filename != null;

		        PrintWriter output; // flux de sauvegarde
		        try {
		            output =
		                new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		            int i = 0;
		            ListNode c=current;
		            while(i < count)
		            {
		               output.print(c.elem.getState());
		               c = c.next;
		               i++;
		            }
		            output.close();
		        }
		        catch (IOException e)
		        {
		            System.err.println("IO Error");
		            System.exit(-1);
		        }

		    }

		    /**
		     * @pre filename le nom d'un fichier sauvegardé par saveState
		     * @post Retire tous les processus présents dans le cluster, puis ajoute au
		     *       cluster les processus dont les noms et capacités sont donnés dans
		     *       le fichier, selon le format généré par saveState.  Arrête le
		     *       programme si une erreur d'I/O se produit ou si la capacité du
		     *       cluster est insuffisante.  Les processus sont répartis équitablement
		     *       entre les différents ordinateurs du cluster.
		     */
		    public void loadState(String fileName)
		    {
		    	BufferedReader input;
		    	String line;
		    	try
		    	{
		    		removeAllProcesses();
		    		input = new BufferedReader(new FileReader(fileName));
		    		line = input.readLine();

		    		while (line != null)
		    		{
		    			String[] s = line.split(" ");
		    			String name = s[0];  // nom du processus
		    			int storage = Integer.parseInt(s[1]);       // capacité de stockage
		    			addProcess(new Process(name,storage));
		    			line = input.readLine();
		    		}
		    	}
		    	catch (IOException e)
		    	{
		    		System.err.println("IOException"+e);
		    		System.exit(-1);
		    	}
		    	catch (UnavailableException e)
		    	{
		       		System.err.println("Not enough capacity");
		    		System.exit(-1);
		    	}

		    }

		    /////////////////////////////

		    /**
		     * Pour debugging
		     */
		    public String toString()
		    {
		    	StringBuffer buf = new StringBuffer();

		    	int i = 0;
		    	ListNode c = current;
		    	buf.append("---\n");
		    	while (i < count)
		        {
		    	   	ComputerIF comp = c.elem;
		    	   	buf.append(comp.toString() + "\n");
		    	   	buf.append(comp.getState());
		    	   	buf.append("\n");
		             c = c.next;
		            i++;
		        }
		    	return buf.toString();
		    }
		}
