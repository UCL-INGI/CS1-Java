package src;
	
/**
 * Une classe représentant un rectangle
 * 
 * @author O. Bonaventure
 * @version Oct. 2016
 * refactor 2017 Dubray Alexandre
 */
public class Correction
{
    private double longueur;
    private double largeur;

    /**
     * @pre long, larg >0
     * @post a construit un rectangle de longueur lon et de largeur larg
     */
    public Correction(double lon, double larg)
    {
        longueur=lon;
        largeur=larg;
    }

    /*
     * @pre -
     * @post retourne la longueur du rectangle
     */
    public double getLongueur() 
    {
        return longueur;
    }
    
    /*
     * @pre -
     * @post retourne la largeur du rectangle
     */
    
    public double getLargeur()
    {
        return largeur;
    }
        
    
    //Q1
    /*
     * @pre -
     * @post retourne la surface du rectangle
     */
    public double surface()
    {
		return this.getLongueur()*this.getLargeur();
    }
    
    //Q2
    /*
     * @pre r!=null
     * @post return true si this et r sont identiques, false sinon
     */
    public boolean identique(Correction r)
    {
		return (this.getLongueur() == r.getLongueur()) && (this.getLargeur() == r.getLargeur());
        
    }
    
    //Q3
    /*
     * @pre r!=null
     * @post return true si this et r ont la même surface, false sinon
     */
    public boolean memeSurface(Correction r)
    {
        return this.surface()==r.surface();
        
    }
    
    // non fourni aux étudiants
    public String toString()
    {
        return "Rect[longueur="+longueur+", largeur="+largeur+"]";
        
    }
    
}
