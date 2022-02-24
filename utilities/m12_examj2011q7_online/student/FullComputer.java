package student;

/**
 * Un ordinateur avec capacité de stockage limitée et nombre de processus limité.  
 * 
 * @author O. Bonaventure, Ch. Pecheur
 * @version Dec. 2007
 */
public class FullComputer extends BasicComputer
{
	/**
	 * Les processus présents sur cet ordinateur.  Les processus sont dans
	 * procs[0] .. procs[count-1], et procs[i] == null pour i >= count.
	 */
    private Process[] procs;
    private int count;         // nombre de processus présents
    private int storage;       // capacité de stockage totale
    private int availStorage;  // capacité de stockage restante
    
    /**
     * @pre n > 0, name != null, storage >= 0
     * @post Construit un FullComputer de nom name, supportant n processus
     *       et avec une capacité de stockage égale à storage
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
     * @post le processus procs[index] a été retiré.
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
    
    // On cache les noms (@author: François MICHEL)
    public boolean is__E_mmp__t_yyy_(){
		return this.count == 0;
	}
	
}
