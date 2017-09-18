package student;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.Assert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	M11Q7Corr.DListTest.class
})
public class M11Q7Corr {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(DListTest.class);
		Iterator<Failure> failures = result.getFailures().iterator();
		Failure f;
		while(failures.hasNext()){
			f = failures.next();
			System.err.println(f.getMessage());
			//System.err.println(f.getTrace());
		}
		if(result.wasSuccessful() == true){
			System.out.println(true);
			/**127 : nombre magique afin de signaler que tout le tests sont passés */
			System.exit(127);
		}
	}

	public static class DListTest extends junit.framework.TestCase
	{

	    /**
	     * @pre -
	     * @post verifie le bon fonctionnement de la methode contains sur plusieurs
	     *       exemples d'utilisation de la liste de Double: vérification de la 
	     *       réussite de la méthode quand il le faut.
	     */
	    public void testReussiteContains()
	    {
	        // Creation de la liste
	    	DListCorr l=new DListCorr();
	   
	        // Ajout de trois Double
	        l.add(new Double(2.3));
	        l.add(new Double(2.4));
	        l.add(new Double(2.4));
	        
	        // Verification sur base des elements ajoutes
	        
	        assertEquals("La verification de la presence d'un element present une fois dans la liste devrait renvoyer true",true,l.contains(new Double(2.3)));
	        assertEquals("La verification de la presence d'un element present deux fois dans la liste devrait renvoyer true",true,l.contains(new Double(2.4)));
	 
	    }
	    
	    /**
	     * @pre -
	     * @post verifie le bon fonctionnement de la methode contains sur plusieurs
	     *       exemples d'utilisation de la liste de Double: vérification de 
	     *       l'échec de la méthode quand il la faut. 
	     */
	    public void testEchecContains()
	    {
	        // Creation de la liste
	    	DListCorr l=new DListCorr();
	   
	        // Ajout de trois Double
	        l.add(new Double(2.3));
	        l.add(new Double(2.4));
	        l.add(new Double(2.4));
	        
	        // Verification sur base des elements ajoutes
	        assertEquals("La verification de la presence d'un element absent de la liste devrait renvoyer false",false,l.contains(new Double(2.2)));
	 
	        // Verification que l'element supprime n'est plus contenu dans la liste
	        l.remove(new Double(2.3));
	        assertEquals("La verification de la presence d'un element qui n'est plus present dans la liste devrait renvoyer false",false,l.contains(new Double(2.3)));
	    }
	    
	    /**
	     * @pre
	     * @post verifie le bon fonctionnement de la methode size
	     */
	    public void testSize()
	    {
	        // creation de la liste
	        DListCorr l=new DListCorr();
	        assertEquals("Une liste vide devrait avoir une taille de zéro",0,l.size());
	 
	        // ajout d'un premier element
	        l.add(new Double(2.3));
	        assertEquals("Une liste d'un élément devrait avoir une taille de 1",1,l.size());

	        // ajout d'un second element
	        l.add(new Double(2.4));
	        assertEquals("Une liste de deux éléments devrait avoir une taille de deux",2,l.size());
	        
	        // retrait d'un element
	        l.remove(new Double(2.3));
	        assertEquals("Une liste d'un élément devrait avoir une taille de 1",1,l.size());
	 
	        // retrait d'un second element
	        l.remove(new Double(2.4));
	        assertEquals("Une liste vide devrait avoir une taille de zéro",0,l.size());        
	    }

	    /**
	     * @pre
	     * @post verification du bon fonctionnement de la methode remove:
	     * 		 vérification de la réussite de cette méthode quand il le faut.
	     */
	    public void testReussiteRemove()
	    {
	        // Creation de la liste
	    	DListCorr l=new DListCorr();
	        
	        // ajout de trois elements a la liste
	        l.add(new Double(2.3));
	        l.add(new Double(2.4));
	        l.add(new Double(2.4));
	        
	        // retrait de ces trois elements
	        assertEquals("Le retrait d'un element se trouvant dans la liste devrait renvoyer true",true,l.remove(new Double(2.3)));
	        assertEquals("Le retrait d'un element se trouvant deux fois dans la liste devrait renvoyer true",true,l.remove(new Double(2.4)));
	    }
	    
	    /**
	     * @pre
	     * @post verification du bon fonctionnement de la methode remove:
	     * 		 vérification de l'échec de cette méthode quand il le faut.
	     */
	    public void testEchecRemove(){
	    	
	        // Creation de la liste
	    	DListCorr l=new DListCorr();
	        
	        // Retrait d'un element d'une liste vide
	        assertEquals("Le retrait d'un element ne se trouvant pas dans la liste devriat renvoyer false",false,l.remove(new Double(2.3)));

	        // Ajout de trois elements a la liste
	        l.add(new Double(2.3));
	        l.add(new Double(2.4));
	        l.add(new Double(2.4));
	        
	        // Retrait de ces trois elements
	        l.remove(new Double(2.3));
	        assertEquals("Le retrait d'un element ne se trouvant plus dans la liste devrait renvoyer false",false,l.remove(new Double(2.3)));
	        assertEquals("Le retrait d'un element ne se trouvant pas dans la liste devriat renvoyer false",false,l.remove(new Double(2.2)));
	        l.remove(new Double(2.4));
	        assertEquals("Le retrait d'un element dont les deux exemplaires ne sont plus dans la liste devrait renvoyer false",false,l.remove(new Double(2.4)));
	    }
	 
	}

}

/**
 * Une petite classe permettant de manipuler une liste de Double
 * Cette classe contient une erreur. Cette classe ne fait pas partie
 * de la mission mais est un exemple d'utilisation de JUnit. Voir egalement
 * le chapitre 6 dans NH3
 * 
 * @author O. Bonaventure
 * @version Dec. 2008
 */
class DListCorr
{
	List<Double> l;

	/**
	 * @pre 
	 * @post a construit une liste initialement vide
	 */
	public DListCorr()
	{
		l=new LinkedList<Double>();
	}

	/**
	 * @pre d!=null
	 * @post a ajout le Double d dans la liste
	 */
	public void add(Double d)
	{
		assert d!=null;
		l.add(d);
	}
	/**
	 * @pre d!=null
	 * @post retourne true si d se trouvait dans la liste et false sinon
	 *       a retire de la liste toutes les instances de Double egales 
	 *       a d
	 */
	public boolean remove(Double d)
	{
		assert d!=null;
		int i=l.indexOf(d);
		if(i>=0)
		{
			Double d1=l.remove(i);
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @pre d!=null
	 * @post retourne true si d se trouve dans la liste et false sinon
	 */
	public boolean contains(Double d)
	{
		return l.contains(d);
	}

	/**
	 * @pre -
	 * @post retourne le nombre de Double se trouvant dans la liste
	 */
	public int size()
	{
		return l.size();
	}
}