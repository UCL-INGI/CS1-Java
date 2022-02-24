/**
 * Classe de test initiale pour la classe Facture
 * 
 * @author Charles Pecheur
 * @version 27 octobre 2012
 */
public class TestFactureInitial {

    static Article[] articles = {
        new Article("laptop 15\" 4Gb", 4.50),
        new Article("stage LaTeX", 12.0),
        new Article("installation wifi", 45.22),
        new Article("carte graphique", 119.49),
        new ArticleReparation(3.5),
        new ArticlePiece(3,new Piece("ventirads", 39.45))
    };
    
    public static void main(String[] args) {
        Facture fac = new Facture("PC store 22 octobre", articles);
        fac.printFacture();
    }
    
}
