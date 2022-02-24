package src;

public class Correction {
    List<Double> l;
    
    /**
     * @pre 
     * @post a construit une liste initialement vide
     */
    public Correction(){
        l=new LinkedList<Double>();
    }
    
    /**
     * @pre d!=null
     * @post a ajout le Double d dans la liste
     */
    public void add(Double d){
        assert d!=null;
        l.add(d);
    }
    /**
     * Méthode corrigée
     * @pre d!=null
     * @post retourne true si d se trouvait dans la liste et false sinon
     *       a retire de la liste toutes les instances de Double egales 
     *       a d
     */
    public boolean remove(Double d){
    	assert d!=null;
    	int i=l.indexOf(d);
    	if(i>=0){
    		while(i>=0)
    		{
    			Double d1=l.remove(i);
    			i=l.indexOf(d);

    		}
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
    public boolean contains(Double d){
        return l.contains(d);
    }
    
    /**
     * @pre -
     * @post retourne le nombre de Double se trouvant dans la liste
    */
    public int size(){
        return l.size();
    }
}