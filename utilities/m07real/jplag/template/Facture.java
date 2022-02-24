/**
 * Une facture, sous forme d'une liste d'articles.
 * 
 * @author Charles Pecheur
 * @version 27 octobre 2012
 */
public class Facture {

    private Article[] articles;
    private String reference;
    /**/private int numero;

    /**
     * @pre  reference != null
     * @post A cree une facture avec {description} et {articles}.
     */
    public Facture(String reference, Article[] articles) {
        this.reference = reference;
        this.articles = articles;
    }

    /**
     * @pre  -
     * @post A imprime la facture.
     */
    public void printFacture() {
        printEntete();
        double totalPrix = 0.0;
        double totalTVA = 0.0;
        for(int i = 0; i < articles.length; i++) {
            Article art = articles[i];
            printArticle(art);
            totalPrix = totalPrix + art.getPrix();
            totalTVA = totalTVA + art.getTVA();
        }
        printTotaux(totalPrix, totalTVA);
    }

    /**
     * @pre  -
     * @post A imprime l'en-tete de la facture, comprenant le descriptif
     *       et les tetes de colonnes.
     */
    public void printEntete() {
        System.out.println("Facture " + reference);
        System.out.println();
        printBarre();
        System.out.println("| Description                              |  prix HTVA |        TVA |  prix TVAC |");
        printBarre();
    }

    /**
     * @pre  -
     * @post A imprime une barre sur la largeur de la facture.
     */
    public void printBarre() {
        System.out.println("===================================================================================");
    }

    /**
     * @pre  -
     * @post A imprime une ligne de facture pour l'article {art}.
     */
    public void printArticle(Article art) {
        System.out.printf("| %-40s | %10.2f | %10.2f | %10.2f |\n",
            art.getDescription(), art.getPrix(), art.getTVA(), art.getPrixTVAC());
    }
    
    /**
     * @pre  -
     * @post A imprime une ligne de facture avec les totaux {prix} et {tva}.
     */
    public void printTotaux(double prix, double tva) {
        printBarre();
        System.out.printf("| %-40s | %10.2f | %10.2f | %10.2f |\n",
            "T O T A L", prix, tva, prix+tva);
    }

    /**
     * @pre  -
     * @post A imprime une liste de livraison, comprenant toutes les
     *       pieces facturees avec leur poids.
     */
    public void printLivraison() {
        // A COMPLETER
    }

    /**
     * @pre  pce != null
     * @post Retourne le nombre d'exemplaires de {pce} factures,
     *       en totalisant sur plusieurs articles si necessaire.
     */
    //public int getNombre(Piece pce) {
    //	// A COMPLETER
    //}
}
