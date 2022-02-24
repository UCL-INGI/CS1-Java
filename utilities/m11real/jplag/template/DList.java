
/**
 * Une petite classe permettant de manipuler une liste de Double
 * Cette classe contient une erreur. Cette classe ne fait pas partie
 * de la mission mais est un exemple d'utilisation de JUnit. Voir egalement
 * le chapitre 6 dans NH3
 * 
 * @author O. Bonaventure
 * @version Dec. 2008
 */
import java.util.List;
import java.util.LinkedList;

public class DList
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
