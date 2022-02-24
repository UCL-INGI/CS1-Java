package src;
	
/**
 * Une classe représentant une fraction
 * 
 * @author O. Bonaventure
 * @version Oct. 2016
 * refactor 2017 Dubray Alexandre
 */
public class Correction
{
   private double x;
   private double y;
   
   /*
    * @pre -
    * @post a construit le point de coordonnée x,y
    */
   Correction(double x, double y) {
    this.x=x;
    this.y=y;
   }
   
   /*
    * @pre -
    * @post retourne la coordonnée x du Correction this
    * 
    */
   public double getX() {
    return x;    
   }
   
   /*
    * @pre -
    * @post retourne la coordonnée y du Correction this
    * 
    */
   public double getY() {
    return y;    
   }
   
   
   /*
    * @pre p!=null
    * @post retourne la distance entre this et p
    * 
    * Voir https://fr.wikipedia.org/wiki/Coordonnées_cartésiennes
    * pour le rappel de la distance entre deux points 
    */
   
   public double distance(Correction p)
   {
       return Math.sqrt( Math.pow((this.getX()-p.getX()),2)+Math.pow((this.getY()-p.getY()),2));
       
   }
 
   /*
    * @pre
    * @post retourne true si le Correction p a les mêmes coordonnées x,y que this, false sinon
    */
   public boolean identique(Correction p) {
       
       return (this.x==p.getX() && this.y==p.getY());
       
   }
   
   // code non fourni
   
   public String toString()
   {
       return "("+this.x+","+this.y+")";
       
   }
   
}
