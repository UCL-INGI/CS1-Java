
/**
 *  Une petite librairie permettant de faciliter la lecture de donnees
 *  en ligne de commande pour des etudiants debutants. Le code de cette
 *  libraire contient quelques constructions qui ne seront pas comprehensibles
 *  par des debutants, il ne doit donc *pas* etre lu par les etudiants.
 * 
 * @author O. Bonaventure
 * @version Aout 2008
 */

import java.util.Scanner;

public class LibrairieIO
{

    // suppose que les caracteres sont en format UTF-8
    private static String charsetName = "UTF-8";

    // l'objet Scanner
    private static Scanner scanner = new Scanner(System.in, charsetName);

    // impossible de creer un objet de cette classe
    private LibrairieIO() { }


    /**
     * @pre q contient la question a afficher a l'ecran
     * @post a affiche la question et a retourne la prochaine chaine de caracteres lu sur l'entree standard     * 
     */
    public static String readString(String q) {
         String r; // String lu
         if (q!=null) System.out.print(q);   
         r=scanner.next();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

 
    /**
     * @pre q contient la question a afficher a l'ecran
     * @post a  affiche la question et a retourne le prochain entier lu sur l'entree standard      
     */
    public static int readInt(String q) {
         int r; // entier lu
         if (q!=null)  System.out.print(q);   
         r=scanner.nextInt();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

   /**
     * @pre q contient la question a afficher a l'ecran
     * @post a  affiche la question et a retourne le prochain double lu sur l'entree standard      
     */
    public static double readDouble(String q) {
         double r; // double lu
         if (q!=null) System.out.print(q);   
         r=scanner.nextDouble();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

    /**
     * @pre q contient la question a afficher a l'ecran
     * @post a  affiche la question et a retourne le prochain float lu sur l'entree standard      
     */
    public static float readFloat(String q) {
         float r; // float lu
         if (q!=null) System.out.print(q);   
         r=scanner.nextFloat();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

   /**
     * @pre q contient la question a afficher a l'ecran
     * @post a  affiche la question et a retourne le prochain short lu sur l'entree standard      
     */
    public static short readShort(String q) {
         short r; // String lu
         if (q!=null) System.out.print(q);   
         r=scanner.nextShort();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

   /**
     * @pre s contient la question a afficher a l'ecran
     * @post a  affiche la question et a retourne le prochain long lu sur l'entree standard      
     */
    public static long readLong(String q) {
         long r; // String lu
         if (q!=null) System.out.print(q);   
         r=scanner.nextLong();
         if (q!=null) System.out.println(); // retour a la ligne
         return r;
    }

    /**
     * @pre q contient la question a afficher a l'ecran
     * @post a affiche la question et a  retourne le prochain boolean lu sur l'entree standard. Par 
     *       convention, le boolean true est retourne lorsque true, vrai ou 1
     *       est lu sur l'entree standard. Le boolean false est retourne
     *       lorsque false, faux ou 0 est lu sur l'entree standard
     */
    public static boolean readBoolean(String q) {
        String s;
        if (q!=null) 
            s = readString(q);
        else
            s=readString();
        if (s.equalsIgnoreCase("true"))  return true;
        if (s.equalsIgnoreCase("false")) return false;
        if (s.equalsIgnoreCase("vrai"))  return true;
        if (s.equalsIgnoreCase("faux")) return false;
        if (s.equals("1"))               return true;
        if (s.equals("0"))               return false;
        throw new java.util.InputMismatchException();
    }

     /**
     * @pre -
     * @post a affiche la question et a retourne la prochaine chaine de caracteres lu sur l'entree standard     * 
     */
    public static String readString() {
         String r; // String lu
         r=scanner.next();
         return r;
    }

 
    /**
     * @pre -
     * @post a  affiche la question et a retourne le prochain entier lu sur l'entree standard      
     */
    public static int readInt() {
         int r; // entier lu
         r=scanner.nextInt();
         return r;
    }

   /**
     * @pre -
     * @post a  affiche la question et a retourne le prochain double lu sur l'entree standard      
     */
    public static double readDouble() {
         double r; // double lu
         r=scanner.nextDouble();
         return r;
    }

    /**
     * @pre -
     * @post a  affiche la question et a retourne le prochain float lu sur l'entree standard      
     */
    public static float readFloat() {
         float r; // float lu
         r=scanner.nextFloat();
         return r;
    }

   /**
     * @pre -
     * @post a  affiche la question et a retourne le prochain short lu sur l'entree standard      
     */
    public static short readShort() {
         short r; // String lu
         r=scanner.nextShort();
         return r;
    }

   /**
     * @pre -
     * @post a  affiche la question et a retourne le prochain long lu sur l'entree standard      
     */
    public static long readLong() {
         long r; // String lu
         r=scanner.nextLong();
         return r;
    }

    /**
     * @pre -
     * @post a affiche la question et a  retourne le prochain boolean lu sur l'entree standard. Par 
     *       convention, le boolean true est retourne lorsque true, vrai ou 1
     *       est lu sur l'entree standard. Le boolean false est retourne
     *       lorsque false, faux ou 0 est lu sur l'entree standard
     */
    public static boolean readBoolean() {
        String s=readString();
        if (s.equalsIgnoreCase("true"))  return true;
        if (s.equalsIgnoreCase("false")) return false;
        if (s.equalsIgnoreCase("vrai"))  return true;
        if (s.equalsIgnoreCase("faux")) return false;
        if (s.equals("1"))               return true;
        if (s.equals("0"))               return false;
        throw new java.util.InputMismatchException();
    }

    
   
    /**
     * @pre
     * @post a effectue un petit programme de test qui utilise les
     *       methodes readString, readInt, readBoolean et readDouble
     */
    public static void main(String[] args) {

        String s = LibrairieIO.readString("Tapez une chaine de caracteres ");
        System.out.println("Votre chaine de caracteres etait : " + s);
        System.out.println();

        int a = LibrairieIO.readInt("Tapez un entier: ");
        System.out.println("Votre entier etait: " + a);
        System.out.println();

        boolean b = LibrairieIO.readBoolean("Tapez un booleen: ");
        System.out.println("Votre booleen etait: " + b);
        System.out.println();

        double c = LibrairieIO.readDouble("Tapez un double: ");
        System.out.println("Votre double etait: " + c);
        System.out.println();

    }

}
