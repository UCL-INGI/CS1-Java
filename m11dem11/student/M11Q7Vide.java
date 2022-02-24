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
	M11Q7Stu.DListTest.class
})
public class M11Q7Stu {

	public static void main(String[] args) {

		Result result1 = JUnitCore.runClasses(DListTest.class);
		Result result2 = JUnitCore.runClasses(DListParseTest.class);
		Iterator<Failure> failures1 = result1.getFailures().iterator();
		Failure f;
		while(failures1.hasNext()){
			f = failures1.next();
			System.err.println(f.getMessage());
			//System.err.println(f.getTrace());
		}
		Iterator<Failure> failures2 = result2.getFailures().iterator();
		while(failures2.hasNext()){
			f = failures2.next();
			System.err.println(f.getMessage());
			//System.err.println(f.getTrace());
		}
		
		if(result1.wasSuccessful() && result2.wasSuccessful()){
			//System.out.println(true);
			/**127 : nombre magique afin de signaler que tout les tests sont passés */
            System.exit(127);
        }
	}
/**
 *Test classic
 */
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
        DList l=new DList();
        
        // Ajout de trois Double
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // Verification sur base des elements ajoutes
        try{
        	assertEquals("Question 1 :\nCette assertion ne devrait pas être lancée : La verification de la presence d'un element "+
        "present une fois dans la liste devrait renvoyer true", @@q1@@ , @@q2@@ );
        }
        catch(Throwable e){
    		fail("Question 1 :\nVotre code provoque une exception. Relisez votre code des réponses 1 et 2 : "+e.toString());
	    }
		try{
        	assertEquals("Question 3 :\nCette assertion ne devrait pas être lancée : La verification de la presence d'un element "+
        "present deux fois dans la liste devrait renvoyer true", @@q3@@ , @@q4@@ );
 		}
    	catch(Throwable e){
    		fail("Question 2 :\nVotre code provoque une exception. Relisez votre code des réponses 3 et 4 : "+e.toString());
    	}
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
		DList l=new DList(); 
        
        // Ajout de trois Double
         l.add(new Double(2.3));
         l.add(new Double(2.4));
         l.add(new Double(2.4));
        
        // Verification sur base des elements ajoutes
        try{
        assertEquals("Question 5 :\nCette assertion ne devrait pas être lancée : La verification de la presence d'un element "+
        "absent de la liste devrait renvoyer false", @@q5@@ , @@q6@@ );
        }
       catch(Throwable e){
        	fail("Question 5 :\nVotre code provoque une exception. Relisez votre code des réponses 5 et 6 : "+e.toString());
        }
        // Verification que l'element supprime n'est plus contenu dans la liste
        l.remove(new Double(2.3));
        try{
        assertEquals("Question 7 :\nCette assertion ne devrait pas être lancée : La verification de la presence d'un element "+
        "qui n'est plus present dans la liste devrait renvoyer false", @@q7@@ , @@q8@@ );
     	}
        catch(Throwable e){
        	fail("Question 7 :\nVotre code provoque une exception. Relisez votre code des réponses 7 et 8 : "+e.toString());
        }
    }
 
}
/**
 *Pour restreindre les réponses possibles
 */
public static  class DListParseTest extends junit.framework.TestCase{
	
	public String msg = "Ce n'est pas la réponse attendue.";
	
	public void testrep1(){
		assertTrue("Question 1 :\n"+msg, "@@q1@@".matches(".*true.*"));
	}
	
	public void testrep2(){
		assertTrue("Question 2 :\n"+msg, "@@q2@@".matches(".*l.contains.*2.3.*"));
	}
	
	public void testrep3(){
		assertTrue("Question 3 :\n"+msg, "@@q3@@".matches(".*true.*"));
	}
	
	public void testrep4(){
		assertTrue("Question 4 :\n"+msg, "@@q4@@".matches(".*l.contains.*2.4.*"));
	}
	
	public void testrep5(){
		assertTrue("Question 5 :\n"+msg, "@@q5@@".matches(".*false.*"));
	}

	public void testrep6(){
		assertFalse("Question 6 :\n"+msg, "@@q6@@".matches(".*l.contains.*(.*new.*Double.*(.*2.[34]\\s.*).*).*"));
	}

	public void testrep7(){
		assertTrue("Question 7 :\n"+msg, "@@q7@@".matches(".*false.*"));
	}
	
	public void testrep8(){
		assertTrue("Question 8 :\n"+msg, "@@q8@@".matches(".*l.contains.*2.3.*"));
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
class DList
{
    List<Double> l;
    
    /**
     * @pre 
     * @post a construit une liste initialement vide
     */
    public DList()
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
