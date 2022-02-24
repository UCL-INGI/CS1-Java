package src;

public class Employe{

   private String nom ;
   private double salaire ;

   /**
	* Constructeur vide nécessaire pour que si les étudiants
	* n'appellent pas super(), il n'y ait pas d'erreur de compilation
	*/
   public Employe(){}
   
   /*
	* Constructeur
	* @pre nom!=null, salaire>0
	* @post a construit une instance de la classe Employe avec nom et salaire
	*/
   public Employe ( String nom, double salaire){ 
	  
	  this.nom = nom ; 
	  this.salaire = salaire ;
   }
   /*
	* @pre -
	* @post retourne le nom de l'employé this
	*/
   public String getNom(){ 
	   return nom ;
   }
   
   /*
	* @pre -
	* @post retourne le salaire de l'employé this
	*/
   public double getSalaire() { 
	   return salaire ;
	}
   /*
	* Note: ce n'est pas la seule et unique réponse. Tant que l'étudiant utilise le nom
	* et le salaire de l'employé pour créer un string, les tests passeront
	*
	* @pre -
	* @post retourne un String décrivant l'employé this
	*/
   public String toString() { 
	   return getNom()+" "+getSalaire(); 
	}
   
   /*
	* @pre -
	* @post retourne true si this et other sont deux employés qui ont le même nom et le même salaire
	*/ 
   public boolean equals(Object other)
   {
	   if(other instanceof Employe) {
		   Employe o = (Employe) other;
		   return (this.getNom().equals(o.getNom())) && (this.getSalaire() == o.getSalaire());
	   }
	   return false;
   }
}

