package student;

/**
 * Un ordinateur, sur lequel on peut ajouter et retirer des processus.
 * L'ordinateur dispose de ressources (nombre de processus, volume de stockage)
 * �ventuellement limit�es, et donc peut refuser l'ajout de processus suppl�mentaires.
 * Un ordinateur a un nom.
 * 
 * @author O. Bonaventure, Ch. Pecheur
 * @version Dec 2007
 */

public interface ComputerIF
{
    /**
     * @pre  p != null, p ne se trouve pas d�j� sur cet ordinateur
     * @post le processus p a �t� ajout� � cet ordinateur, si les ressources 
     *       n�cessaires sont disponibles.  Retourne true si le processus
     *       a �t� ajout�, false sinon.
     */
    public boolean addProcess(Process p);
    
    /**
     * @pre p != null
     * @post le processus p a �t� retir� de cet ordinateur, si ce processus
     *       se trouve sur cet ordinateur.  Retourne true si le processus
     *       a �t� supprim�, false sinon.
     */
    public boolean removeProcess(Process p);
    
    /**
     * @pre -
     * @post Tous les processus de cet ordinateur ont �t� retir�s.
     */
    public void removeAllProcesses();
    
    /**
     * @pre -
     * @post retourne le nom de l'ordinateur.
     */
    public String getName();

    /**
     * @pre -
     * @post Retourne la liste des processus de cet ordinateur sous forme de texte,
     *       avec une ligne par processus, chaque ligne comprenant le nom du processus
     *       et sa taille de stockage, s�par�s par un espace, et se termine par
     *       un passage � la ligne.  Par exemple:
     *     
     *       process1 0
     *       bigprocess 200
     *       smallprocess 20
     */
    public String getState();
 }
