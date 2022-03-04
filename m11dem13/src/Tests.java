package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import StudentCode.*;


/**
 * Un exemple de test avec JUNIT
 *
 * @author Clémentine Munyabarenzi Juillet 2015
 * @original version O. Bonaventure Decembre 2008
 */

public class Tests {

	/**
     * @pre -
     * @post verifie le bon fonctionnement de la methode contains sur plusieurs
     *       exemples d'utilisation de la liste de Double: vérification de la 
     *       réussite de la méthode quand il le faut.
     */
    @Test
    public void testReussiteContains()
    {
        // Creation de la liste
        DList l=new DList();
   
        // Ajout de trois Double
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // Verification sur base des elements ajoutes
        
        assertTrue("La verification de la presence d'un element present une fois dans la liste devrait renvoyer true",true == l.contains(new Double(2.3)));
        assertTrue("La verification de la presence d'un element present deux fois dans la liste devrait renvoyer true",true == l.contains(new Double(2.4)));
 
    }
    
    /**
     * @pre -
     * @post verifie le bon fonctionnement de la methode contains sur plusieurs
     *       exemples d'utilisation de la liste de Double: vérification de 
     *       l'échec de la méthode quand il la faut. 
     */
    @Test
    public void testEchecContains()
    {
        // Creation de la liste
        DList l=new DList();
   
        // Ajout de trois Double
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // Verification sur base des elements ajoutes
        assertEquals("La verification de la presence d'un element absent de la liste devrait renvoyer false",false,l.contains(new Double(2.2)));
 
        // Verification que l'element supprime n'est plus contenu dans la liste
        l.remove(new Double(2.3));
        assertTrue("La verification de la presence d'un element qui n'est plus present dans la liste devrait renvoyer false",false == l.contains(new Double(2.3)));
    }
    
    /**
     * @pre
     * @post verifie le bon fonctionnement de la methode size
     */
    @Test
    public void testSize()
    {
        // creation de la liste
        DList l=new DList();
        assertTrue("Une liste vide "
        		+ "devrait avoir une taille de zéro",0 == l.size());
 
        // ajout d'un premier element
        l.add(new Double(2.3));
        assertTrue("Une liste d'un élément "
        		+ "devrait avoir une taille de 1",1 == l.size());

        // ajout d'un second element
        l.add(new Double(2.4));
        assertTrue("Une liste de deux éléments "
        		+ "devrait avoir une taille de deux",2 == l.size());
        
        // retrait d'un element
        l.remove(new Double(2.3));
        assertTrue("Une liste d'un élément "
        		+ "devrait avoir une taille de 1",1 == l.size());
 
        // retrait d'un second element
        l.remove(new Double(2.4));
        assertTrue("Une liste vide "
        		+ "devrait avoir une taille de zéro",0 == l.size());        
    }

    /**
     * @pre
     * @post verification du bon fonctionnement de la methode remove:
     * 		 vérification de la réussite de cette méthode quand il le faut.
     */
    @Test
    public void testReussiteRemove()
    {
        // Creation de la liste
        DList l=new DList();
        
        // ajout de trois elements a la liste
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // retrait de ces trois elements
        assertTrue("Le retrait d'un element se trouvant dans la liste "
        		+ "devrait renvoyer true",true == l.remove(new Double(2.3)));
        assertTrue("Le retrait d'un element se trouvant deux fois dans la liste"
        		+ " devrait renvoyer true",true == l.remove(new Double(2.4)));
    }
    
    /**
     * @pre
     * @post verification du bon fonctionnement de la methode remove:
     * 		 vérification de l'échec de cette méthode quand il le faut.
     */
    @Test
    public void testEchecRemove(){
    	
        // Creation de la liste
        DList l=new DList();
        
        // Retrait d'un element d'une liste vide
        assertTrue("Le retrait d'un element ne se trouvant pas dans la liste"
        		+ " devriat renvoyer false",false == l.remove(new Double(2.3)));

        // Ajout de trois elements a la liste
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // Retrait de ces trois elements
        l.remove(new Double(2.3));
        assertTrue("Le retrait d'un element ne se trouvant plus dans la liste "
        		+ "devrait renvoyer false",false == l.remove(new Double(2.3)));
        assertTrue("Le retrait d'un element ne se trouvant pas dans la liste "
        		+ "devriat renvoyer false",false == l.remove(new Double(2.2)));
        l.remove(new Double(2.4));
        assertTrue("Vous n'avez pas corrigé l'erreur contenue dans la classe.",false == l.remove(new Double(2.4)));
       
    }
}