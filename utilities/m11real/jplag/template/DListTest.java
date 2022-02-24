

/**
 * Un exemple de test avec JUNIT
 *
 * @author O. Bonaventure
 * @version Decembre 2008
 */
public class DListTest extends junit.framework.TestCase
{

    /**
     * @pre -
     * @post verifie le bon fonctionnement de la methode contains sur plusieurs
     *       exemples d'utilisation de la liste de Double
     */
    public void testContains()
    {
        // Creation de la liste
        DList l=new DList();
        // Ajout de trois Double
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // Verification sur base des elements ajoutes
        
        assertEquals("Verification de la presence d'un element present une fois dans la liste",true,l.contains(new Double(2.3)));
        assertEquals("Verification de la presence d'un element absent de la liste",false,l.contains(new Double(2.2)));
        assertEquals("Verification de la presence d'un element present deux fois dans la liste",true,l.contains(new Double(2.4)));
 
        // Verification que l'element supprime n'est plus contenu dans la liste
        
        l.remove(new Double(2.3));
        assertEquals("Verification de la presence d'un element qui n'est plus present dans la liste",false,l.contains(new Double(2.3)));

    }
    
    /**
     * @pre
     * @post verifie le bon fonctionnement de la methode size
     */
    public void testSize()
    {
        // creation de la liste
        DList l=new DList();
        assertEquals("Une liste vide doit avoir une taille de zéro",0,l.size());
 
        // ajout d'un premier element
        l.add(new Double(2.3));
        assertEquals("Une liste d'un élément doit avoir une taille de 1",1,l.size());

        // ajout d'un second element
        l.add(new Double(2.4));
        assertEquals("Une liste de deux éléments taille de deux",2,l.size());
        
        // retrait d'un element
        l.remove(new Double(2.3));
        assertEquals("Une liste d'un élément doit avoir une taille de 1",1,l.size());
 
        // retrait d'un second element
        l.remove(new Double(2.4));
        assertEquals("Une liste vide doit avoir une taille de zéro",0,l.size());        
    }

    /**
     * @pre
     * @post verification du bon fonctionnement de la methode remove
     */
    public void testRemove()
    {
        // Creation de la liste
        
        DList l=new DList();
        
        // retrait d'un element d'une liste vide
        assertEquals("Retrait d'un element ne se trouvant pas dans la liste",false,l.remove(new Double(2.3)));

        // ajout de trois elements a la liste
        l.add(new Double(2.3));
        l.add(new Double(2.4));
        l.add(new Double(2.4));
        
        // retrait de ces trois elements
        assertEquals("Retrait d'un element se trouvant trouvant dans la liste",true,l.remove(new Double(2.3)));
        assertEquals("Retrait d'un element ne se trouvant plus trouvant dans la liste",false,l.remove(new Double(2.3)));
        assertEquals("Retrait d'un element ne se trouvant trouvant pas dans la liste",false,l.remove(new Double(2.2)));
        assertEquals("Retrait d'un element se trouvant deux fois dans la liste",true,l.remove(new Double(2.4)));
        assertEquals("Retrait d'un element ne se trouvant plus dans la liste",false,l.remove(new Double(2.4)));
    }
    
 
}
