package student;

/**
 * Une grappe (cluster) d'ordinateurs formant une ressource commune pour
 * l'exécution de processus.  Les ordinateurs du cluster sont gérés comme
 * une liste circulaire, de telle manière que les processus soient distribués
 * à tour de rôle à chaque ordinateur, dans la limite de leurs ressources disponibles.  
 * La tête de la liste correspond prochain ordinateur à recevoir un nouveau processus, 
 * pour autant qu'il ait les ressources nécessaires.
 * 
 * @author O. Bonaventure, Ch. Pecheur
 * @version Dec. 2007
 */

import java.io.*;

public class Cluster
{
    // classe interne: un noeud de la liste circulaire des ordinateurs du cluster
    public class ListNode {
        ListNode next;
        ComputerIF elem;
    }

    /**
     * La tête courante de la liste des ordinateurs. Les noeuds suivants sont
     * chaînés de manière circulaire: la chaîne finit toujours par revenir à
     * current.
     */
    private ListNode current;
    private int count; // nombre d'ordinateurs dans le cluster
    
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
     *       tête de la liste, disposant des ressources nécessaires. La nouvelle
     *       tête de liste est le noeud qui suit celui de l'ordinateur où p a
     *       été ajouté. Si aucun ordinateur ne dispose de ressources
     *       suffisantes, la tête de liste est inchangée et une
     *       UnavailableException est lancée.
	 */
    public void addProcess(Process p) throws UnavailableException
    {
@		@q1@@
    }

    /**
     * @pre p != null
     * @post Le processus p a été retiré du premier ordinateur du cluster
     *       sur lequel il se trouvait, à partir de la tête de la liste. 
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
     *       comp est en tête de liste, la tête de liste passe au noeud suivant,
     *       sinon elle est inchangée. Retourne true si comp a été retiré, false
     *       sinon.
     */
    public boolean removeComputer(ComputerIF comp)
   {
        assert comp != null;
        
    	int i = 0;
    	ListNode c = current;
    	while (i < count)
        {
        	// il faut considérer c.next pour pouvoir le
        	// retirer à partir de c
        	if (c.next.elem.equals(comp))
        	{
        		if (count == 1)
        		{
        			// comp était seul, le cluster devient vide
        			current = null;
        			count = 0;
        		}
        		else
        		{
        			c.next = c.next.next;
        			if (c.next == current)
        			{
        				current = current.next;
        			}
        			count--;
        		}
        		return true;
        	}
            c = c.next;
            i++;
        }
    	// on a fait le tour de la liste
    	return false;
   }

    
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
    
    // On cache les noms
    public int get__C_oou__n_t(){
		return this.count;
	}
	
	public ListNode get_____Ccur_ren_rt__(){
		return this.current;
	}
    
    /**
     * test incomplet
     */
    
    public static void main(String[] args)
    {
        System.exit(127);
    	ComputerIF c1,c2,c3, c4;
    	c1=new FullComputer("joe",1,10);
    	c2=new FullComputer("jack",2,0);
    	c3=new FullComputer("bill",3,20);
    	c4=new BasicComputer("bob");

    	Cluster c=new Cluster();
    	c.addComputer(c1);
    	System.out.println(c);
    	c.addComputer(c2);
    	System.out.println(c);
    	c.addComputer(c3);
    	System.out.println(c);
    	c.addComputer(c4);
    	System.out.println(c);

    	Process p1,p2,p3,p4,p5;
    	p1=new Process("p1",0);
    	p2=new Process("p2",0);
    	p3=new Process("p3",2);
    	p4=new Process("p4",4);
    	p5=new Process("p5",1);
    	try
    	{
    		c.addProcess(p1);
    		System.out.println(c);
    		c.addProcess(p2);
    		System.out.println(c);
    		c.addProcess(p3);
    		System.out.println(c);
    		c.addProcess(p4);
    		System.out.println(c);
    		c.addProcess(p5);
    		System.out.println(c);

    		c.saveState("test.dat");
    		System.out.println(c);

    		c.removeProcess(p3);
    		System.out.println(c);
    		c.removeProcess(p1);
    		System.out.println(c);
    		c.removeProcess(p5);
    		System.out.println(c);

    		c.loadState("test.dat");
    		System.out.println(c);
    		
    		c.removeComputer(c2);
    		System.out.println(c);
    		c.removeComputer(c1);
    		System.out.println(c);
    		c.removeComputer(c3);
    		System.out.println(c);
    	}
    	catch (UnavailableException e)
    	{
    		System.err.println("Problem");
    	}
    }
   
}
