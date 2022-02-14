/**
 * Un article de facture simple, comprenant un descriptif et un prix.
 * 
 * @author Charles Pecheur
 * @version 27 octobre 2012
 */
public class Article {

    private static final double TAUX_TVA = 0.21;   // TVA a 21%
    private String description;
    private double prix;
    
    /**
     * @pre  -
     * @post cree un article vide.  Utilise par les extensions
     *       qui n'utilisent pas {description} et {prix}.
     */
    public Article() {
    }
    
    /**
     * @pre  description != null
     * @post cree un article avec {description} et {prix}.
     */
    public Article(String description, double prix) {
        this.description = description;
        this.prix = prix;
    }
    
    /**
     * @pre  -
     * @post retourne la description de cet article.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @pre  -
     * @post retourne le prix (HTVA) de cet article.
     */
    public double getPrix() {
        return prix;
    }
    
    /**
     * @pre  -
     * @post retourne la TVA sur cet article.
     */
    public double getTVA() {
        return getPrix() * TAUX_TVA;
    }
 
    /**
     * @pre  -
     * @post retourne le prix TVAC de cet article.
     */
    public double getPrixTVAC() {
        return getPrix() + getTVA();
    }
    
    /**
     * @pre  -
     * @post retourne un texte decrivant cet article, au format:
     *       "{description}: {prix}".
     */
    public String toString() {
        return String.format("%s: %.2f", getDescription(), getPrix());
    }
}
