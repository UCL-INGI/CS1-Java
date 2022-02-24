/**
 * Classe simple contenant quelques methodes utilisant la methode contains
 * de la classe ImageGray
 * 
 * @author O. Bonaventure
 * @version Octobre 2012
 */
public class TestContains
{
    /**
     * @pre -
     * @post execute les trois tests definis dans la classe: testCross, testPhoto et suspectAux24h
     */
    public static void main(String[] args) {
        System.out.println("====== Test en utilisant des images simples NB testCross() =======");
        testCross();
        if(LibrairieIO.readString("Voulez vous continuer (O pour oui / N pour non)? ").equalsIgnoreCase("N"))
            return;
        System.out.println("====== Test en utilisant une photo prise par une camera de surveillance testPhoto() =======");
        testPhoto();
        if(LibrairieIO.readString("Voulez vous continuer (O pour oui / N pour non)? ").equalsIgnoreCase("N"))
            return;
        System.out.println("====== Test pour trouver un suspect parmis un ensemble de photos suspectAux24h() =======");
        suspectAux24h();
    }
    
    /**
     * @pre -
     * @post effectue un test de la methode contains en utilisant des images 
     *       simples en noir et blanc. Affiche le temps d'execution du test
     */
    public static void testCross()
    {
        int[][] large=LibrairieImage.createCross(600);
        int[][] small=LibrairieImage.createCross(50);
        
        long start = System.currentTimeMillis();
        boolean found=ImageGray.contains(large,small,0);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Votre methode contains a retourne "+found+"[true attendu] et a pris "+elapsed+" millisecondes pour s'executer");

        int[][] smallGrid=LibrairieImage.createGrid(100,10);
        start = System.currentTimeMillis();
        found=ImageGray.contains(large,smallGrid,0);
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Votre methode contains a retourne "+found+"[false attendu] et a pris "+elapsed+" millisecondes pour s'executer sur la deuxieme image");
        System.out.println("Les images utilisees pour le test sont maintenant affichees");
        LibrairieImage.show(large);
        LibrairieImage.show(small);
        LibrairieImage.show(smallGrid);
    }


    /**
     * @pre -
     * @post effectue un test de la m�thode contains en utilisant une photo
     *       prise par une camera de surveillance
     */
     public static void testPhoto()
     {
         System.out.println("La premiere fenetre presente une image prise par une camera de surveillance");
         int[][] camera=LibrairieImage.imageGrayFromFile("image3.png");
         LibrairieImage.show(camera);
         System.out.println("La deuxieme fenetre presente une voiture extraite de cette photo");
         int[][] car=LibrairieImage.imageGrayFromFile("car.png");
         LibrairieImage.show(car);
        
         long start = System.currentTimeMillis();
         boolean found=ImageGray.contains(camera,car,1);
         long elapsed = System.currentTimeMillis() - start;
         System.out.println("Votre methode contains a retourne "+found+"[true attendu] et a pris "+elapsed+" millisecondes pour s'executer");


         camera=LibrairieImage.imageGrayFromFile("image2.png");
         start = System.currentTimeMillis();
         found=ImageGray.contains(camera,car,0);
         elapsed = System.currentTimeMillis() - start;
         System.out.println("La troisieme fenetre presente une autre image prise par une camera de surveillance");
         LibrairieImage.show(camera);
         System.out.println("Votre methode contains a retourne "+found+"[false attendu] et a pris "+elapsed+" millisecondes pour s'executer sur la deuxieme image");
    }
    

    /**
     * @pre - 
     * @post Utilise la methode contains pour trouver si un suspect donné a été présent sur la place
     *          de l'Université, pendant une période donnée, et cela en se basant sur les photos prises
     *          par la webcam de la place prenant une nouvelle photo toutes les 10 secondes.
     *          Si le suspect est trouvé, les photos sur lequel il est trouvé sont affichées
     */
    public static void suspectAux24h() {
     
        long start = System.currentTimeMillis();
         
        final int NB_PIC = 345;
        int[][] toFind=LibrairieImage.imageGrayFromFile("24h/suspect.jpg"); 
        LibrairieImage.show(toFind);        
        
        System.out.println("Recherche du suspect parmis "+NB_PIC+" photos");
        
        for (int i = 1; i < NB_PIC+1 ; i++ ){
               int[][] pic=LibrairieImage.imageGrayFromFile("24h/wallons-"+i+".jpg");
               if (ImageGray.contains(pic,toFind,40)) {
                    System.out.println("Suspect trouve dans l'image 'wallons-"+i+"'");
                    LibrairieImage.show(pic); 
                }
        }
        
        System.out.println("Temps ecoule : "+(System.currentTimeMillis() - start)+ "ms");
        
    }
    
    
}
