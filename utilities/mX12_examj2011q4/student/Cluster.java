package student;

/**
 * Une grappe (cluster) d'ordinateurs formant une ressource commune pour
 * l'ex�cution de processus.  Les ordinateurs du cluster sont g�r�s comme
 * une liste circulaire, de telle mani�re que les processus soient distribu�s
 * � tour de r�le � chaque ordinateur, dans la limite de leurs ressources disponibles.  
 * La t�te de la liste correspond prochain ordinateur � recevoir un nouveau processus, 
 * pour autant qu'il ait les ressources n�cessaires.
 * 
 * @author O. Bonaventure, Ch. Pecheur
 * @version Dec. 2007
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Cluster
{
    // classe interne: un noeud de la liste circulaire des ordinateurs du cluster
    private class ListNode {
        ListNode next;
        ComputerIF elem;
    }

    /**
     * La t�te courante de la liste des ordinateurs. Les noeuds suivants sont
     * cha�n�s de mani�re circulaire: la cha�ne finit toujours par revenir �
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
     * @pre p != null, p ne se trouve pas d�j� sur un ordinateur du cluster.
     * @post Le processus p a �t� ajout� au premier ordinateur, � partir de la
     *       t�te de la liste, disposant des ressources n�cessaires. La nouvelle
     *       t�te de liste est le noeud qui suit celui de l'ordinateur o� p a
     *       �t� ajout�. Si aucun ordinateur ne dispose de ressources
     *       suffisantes, la t�te de liste est inchang�e et une
     *       UnavailableException est lanc�e.
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
    			// p a �t� ajout�
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
     * @post Le processus p a �t� retir� du premier ordinateur du cluster
     *       sur lequel il se trouvait, � partir de la t�te de la liste. 
     *       Si p n'est pas trouv�, lance UnavailableException.
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
        	  // p a �t� retir�
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
     * @post Tous les processus de tous les ordinateurs ont �t� retir�s.
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
     * @pre  comp != null, comp ne fait pas d�j� partie du cluster.
     * @post L'ordinateur comp est ajout� � la liste des ordinateurs.
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
     * @post L'ordinateur comp a �t� retir� du cluster, s'il s'y trouvait. Si
     *       comp est en t�te de liste, la t�te de liste passe au noeud suivant,
     *       sinon elle est inchang�e. Retourne true si comp a �t� retir�, false
     *       sinon.
     */
    public boolean removeComputer(ComputerIF comp)
   {
        assert comp != null;
        
    	int i = 0;
    	ListNode c = current;
    	while (i < count)
        {
        	// il faut consid�rer c.next pour pouvoir le
        	// retirer � partir de c
        	if (c.next.elem.equals(comp))
        	{
        		if (count == 1)
        		{
        			// comp �tait seul, le cluster devient vide
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
     * @post Le fichier filename contient l'�tat du cluster sous forme de texte.
     *       Pour chaque processus de chaque ordinateur du cluster, le fichier
     *       contient une ligne compos�e du nom et de la capacit� demand�e,
     *       s�par�s par un espace. Le nom des ordinateurs sur lesquels se
     *       trouvent les processus n'est pas sauvegard�. Arr�te le programme si
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
     * @pre filename le nom d'un fichier sauvegard� par saveState
     * @post Retire tous les processus pr�sents dans le cluster, puis ajoute au
     *       cluster les processus dont les noms et capacit�s sont donn�s dans
     *       le fichier, selon le format g�n�r� par saveState.  Arr�te le
     *       programme si une erreur d'I/O se produit ou si la capacit� du
     *       cluster est insuffisante.  Les processus sont r�partis �quitablement
     *       entre les diff�rents ordinateurs du cluster.
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
    			int storage = Integer.parseInt(s[1]);       // capacit� de stockage
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
    
    /**
     * test incomplet
     */
    
    public static void main(String[] args)
    {
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
