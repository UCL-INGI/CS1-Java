package src;

/**
 * Une classe représentant une fraction
 * 
 * @author O. Bonaventure
 * @version Oct. 2016
 * refactor Dubray Alexandre 2017
 */
public class Correction 
{
    private int num; // numerateur
    private int den; // denominateur

    /**
     * @pre num>=0, den>0
     * @post construit la fraction num/den
     */
    public Correction(int num, int den)
    {
		this.num = num;
		this.den = den;
    }

    //Q1
    
    /* 
     * @pre -
     * @post retourne le dénominateur de la fraction
     */
    public int getDen() 
    {
        return this.den;
    }
    
    //Q1
    
    /* 
     * @pre -
     * @post retourne le numérateur de la fraction
     */
    public int getNum()
    {
        
        return this.num;
    }
    
    // Q2
    
    /*
     * @pre -
     * @post retourne true si la valeur numérique de la fraction est égale à une valeur entière
     */
    public boolean entier() 
    {
        return (this.getNum()%this.getDen()==0);
        
    }
    
    
    // ne pas fournir aux étudiants mais on l'utilise
    
    /*
     * @pre -
     * @post représentation de la fraction sous forme de String
     */
    public String toString() {
        return this.getNum()+"/"+this.getDen();
    }
    
    
        // ne pas utiliser maintenant ce qui suit
    /*
     * @pre -
     * @post modifie la fraction de façon à ce qu'elle soit irréductible
     */
    public void reduit() {
        int n=this.getNum();
        int d=this.getDen();
        for(int i=2; i<Math.min(n,d); i++) {
            if( ((n%i)==0) && ((d%i)==0) ) {
                n=n/i;
                d=d/i;
            }
        }
        this.num=n;
        this.den=d;
    }
    /*
     * @pre f!=null
     * @post retourne true si la fraction f est equivalente à this, c'est-à-dire
     *       correspond à la même valeur numérique
     * 
     */
    public boolean equivalente(Correction f) 
    {
        
        return false;
    }
    
    /*
     * @pre !=null
     * @post retourne la fraction qui est la somme entre this et la fraction f
     */
    public Correction somme(Correction f)
    {
        return new Correction(this.getNum()*f.getDen()+this.getDen()*f.getNum(),this.getDen()*f.getDen());   
        
    }
}
