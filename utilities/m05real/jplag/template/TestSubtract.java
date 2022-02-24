/**
 * Classe simple contenant quelques methodes utilisant la methode subtract
 * de la classe ImageGray
 * 
 * @author O. Bonaventure
 * @version Octobre 2012
 */
public class TestSubtract
{
    /**
     * @pre -
     * @post effectue un test de la methode subtract en utilisant des images
     *       simples en noir et blanc. Affiche le temps d'execution du test
     */
    public static void main(String[] args)
    {
        int[][] image=LibrairieImage.createGrid(600,10);
        int[][] background=LibrairieImage.createLines(600,10);        

        System.out.println("Voici les deux images utilisees par ce test");
        LibrairieImage.show(image);
        LibrairieImage.show(background);
        long start = System.currentTimeMillis();
        int[][] result=ImageGray.subtract(image,background,0);
        if(result!=null)
        {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Votre methode subtract a retourne l'image suivante et a pris "+elapsed+" millisecondes pour s'executer");
            LibrairieImage.show(result);
        }
        else
        {
           System.out.println("Votre methode subtract a retourne null !");
        }
    }
}
