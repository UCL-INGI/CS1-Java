
/**
 * Classe simple contenant quelques methodes utilisant la methode rescale
 * de la classe ImageGray
 * 
 * @author O. Bonaventure
 * @version Octobre 2012
 */
public class TestRescale
{
	    /**
     * @pre -
     * @post effectue un test de la methode rescale en utilisant des images
     *       simples en noir et blanc. Affiche le temps d'execution du test
     */
    public static void main(String[] args)
    {
        int[][] image=LibrairieImage.createGrid(200,10);

        System.out.println("Voici l'image utilisee par ce test");
        LibrairieImage.show(image);
        long start = System.currentTimeMillis();
        int[][] smaller=ImageGray.rescale(image,60,60);
        if(smaller!=null)
        {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Votre methode rescale a retourne l'image suivante et a pris "+elapsed+" millisecondes pour s'executer");
            LibrairieImage.show(smaller);
        }
        else
        {
           System.out.println("Votre methode rescale a retourne null !");
        }
        
        start = System.currentTimeMillis();
        int[][] larger=ImageGray.rescale(image,500,500);
        if(smaller!=null)
        {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Votre methode rescale a retourne l'image suivante et a pris "+elapsed+" millisecondes pour s'executer");
            LibrairieImage.show(larger);
        }
        else
        {
           System.out.println("Votre methode rescale a retourne null !");
        }
        
    }
}
