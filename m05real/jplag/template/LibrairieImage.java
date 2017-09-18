
/**
 * Une librairie de manipulation d'images en niveaux de gris
 * 
 * @author O. Bonaventure, F. Duchene
 * @version octobre 2012
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class LibrairieImage
{
    public static final int BLANC=255;
    public static final int NOIR=0;
    
            
    /**
     * @pre filename!=null nom du fichier contenant une image en format
     *      gif, png ou jpeg
     * @post charge l'image se trouvant dans le fichier dont le nom
     *       est filename. En cas d'erreur (nom de fichier invalide, 
     *       format invalide, ...) affiche un message d'erreur et arrete 
     *       le programme
     */
    public static int[][] imageGrayFromFile(String filename) 
    {
        BufferedImage newImage=null; 
        int[][] cImage;
        
        try {
            File file = new File(filename);
            newImage = ImageIO.read(file);     
        }
        catch (IOException e) {
            System.err.println("Impossible d'ouvrir le fichier : " + filename);
            System.exit(-1);
        }

        if (newImage == null) {
            System.err.println("Fichier utilisant un format non reconnu : " + filename);
            System.exit(-1);
            return null;
        }
        else
        {
            cImage=new int[newImage.getHeight()][newImage.getWidth()];
            for(int i=0; i<newImage.getHeight();i++)
                for(int j=0;j<newImage.getWidth();j++)
                {
                    int lum=luminance(new Color(newImage.getRGB(j,i)));
                     cImage[i][j]=lum;
                }
                    
            return cImage;
        }
        
    }
    
    /**
     * @pre urlname!=null url contenant une image en format
     *      gif, png ou jpeg
     * @post charge l'image se trouvant a l'url urlname. 
     *       En cas d'erreur (nom de fichier invalide, 
     *       format invalide, ...) affiche un message d'erreur et arrete 
     *       le programme
     */
    public static int[][] imageGrayFromURL(String urlname) 
    {
        BufferedImage newImage=null; 
        int[][] cImage;
        try {
             URL url = new URL(urlname); 
             newImage = ImageIO.read(url);     
        }
        catch (IOException e) {
            System.err.println("Impossible de charger l'image depuis l'URL : " + urlname);
            System.exit(-1);
        }

        if (newImage == null) {
           System.err.println("Fichier utilisant un format non reconnu : " + urlname);
            System.exit(-1);
            return null; // not reached
        }
        else
        {
            cImage=new int[newImage.getHeight()][newImage.getWidth()];
            for(int i=0; i<newImage.getHeight();i++)
                for(int j=0;j<newImage.getWidth();j++)
                {
                    int lum=luminance(new Color(newImage.getRGB(j,i)));
                     cImage[i][j]=lum;
                }
                    
            return cImage;
        }
    }             
    
   /**
    * @pre tab!=null et delay>0 en millisecondes
    * @post a affiche les images tab[0], tab[1], ... dans cet ordre
    *       dans une nouvelle fenetre avec un delai de delay 
    *       millisecondes entre deux images successives
    */
    
    public static void show(int[][][] tab, int delay)
    {
        // create all the bufferedimages that are required
        // create BufferedImage
        BufferedImage[] images= new BufferedImage[tab.length];
        for(int i=0;i<tab.length;i++)
        {
            images[i]= convert(tab[i]);
        }
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);         
        frame.setResizable(false);

            
        frame.setVisible(true);
        for(int i=0;i<tab.length;i++)
        {
            // create the GUI for viewing the image if needed       
            frame.setContentPane( new JLabel(new ImageIcon(images[i])));  
            frame.pack();
            // draw
            frame.repaint();
            try { Thread.currentThread().sleep(delay); }
            catch (InterruptedException e) { System.out.println("Error sleeping"); }
        }
    }
   /**
    * @pre img!=null
    * @post affiche dans une nouvelle fenetre l'image img
    */
    public static void show(int[][] img) 
    {
        int[][][] tab=new int[1][][]; 
        tab[0]=img;
        LibrairieImage.show(tab,0);
    }
    
    
     /**
     * @pre img!=null
     * @post retourne la BufferedImage equivalente a l'image en niveaux de 
     *       gris img
     */
    
    private static BufferedImage convert(int[][] img)
    {
        // create BufferedImage
        BufferedImage t= new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_BYTE_GRAY);
        for(int i=0; i<img.length;i++)
                for(int j=0;j<img[0].length;j++)
                {
                   int lum=img[i][j];
                   Color gray=new Color(lum,lum,lum);
                   t.setRGB(j,i,gray.getRGB()); 
                }
        return t;
    }
    
    /**
     * @pre fileName!=null
     * @post 
     */
    public static void save(int[][] img, String fileName) {
        
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        assert (suffix.equals("jpg") || suffix.equals("png")) : "Erreur : seuls les suffixes .jpg et .png sont supportes";
        BufferedImage image=convert(img);
        try 
            { 
                ImageIO.write(image, suffix, new File(fileName)); 
            }
            catch (IOException e) { 
                System.err.println("Erreur lors de l'ecriture du fichier "+fileName);
                System.exit(-1);
            }

    }
    /**
     * @pre c!=null
     * @post retourne la luminance de la couleur c (entier entre 0 et 255)
     */

    public static int luminance(Color c)
    {
         return (int) (0.299*c.getRed()+ 0.587*c.getGreen()+0.114*c.getBlue()); 
    }
   
    
    
    /**
     * @pre length>0, step>0, length>step
     * @post retourne une image carree de length pixels sur length pixels. Cette
     *       image represente une grille composee de carres noirs de cote step
     *       sur fond blanc
     */  
    public static int[][] createGrid(int length, int step)
    {
        assert length>0;
        assert step>0;
        assert step<length;
        int[][] img=new int[length][length];
        // fond blanc
        for(int i=0;i<img.length;i++)
        {
            for(int j=0;j<img[0].length;j++)
            {
                img[i][j] = BLANC;
            }
        }
        // lignes horizontales
        for(int i=0;i<img.length;i=i+step)
        {
            for(int j=0;j<img[0].length;j++)
            {
                img[i][j] = NOIR;
            }
        }
        // lignes verticales
        for(int j=0;j<img.length;j=j+step)
        {
            for(int i=0;i<img[0].length;i++)
            {
                img[i][j] = NOIR;
            }
        }
        return img;
    }
    
        /**
     * @pre length>0, step>0, length>step
     * @post retourne une image carree de length pixels sur length pixels. Cette
     *       image represente des lignes horizontales noires tous les step pixels
     */  
    public static int[][] createLines(int length, int step)
    {
        assert length>0;
        assert step>0;
        assert step<length;
        int[][] img=new int[length][length];
        // fond blanc
        for(int i=0;i<img.length;i++)
        {
            for(int j=0;j<img[0].length;j++)
            {
                img[i][j] = BLANC;
            }
        }
        // lignes horizontales
        for(int i=0;i<img.length;i=i+step)
        {
            for(int j=0;j<img[0].length;j++)
            {
                img[i][j] = NOIR;
            }
        }
        return img;
    }
    
    
    /**
     * @pre length>0
     * @post retourne une image carree de length pixels sur length pixels. Cette
     *       image represente une croix noire (les deux diagonales) sur fond blanc
     */   
    public static int[][] createCross(int length)
    {
        assert length>0;
        int[][] img=new int[length][length];
        for(int i=0;i<img.length;i++)
        {
            for(int j=0;j<img[0].length;j++)
            {
                if( (i==j)||( (i+j)==(length-1)))
                    img[i][j] = NOIR;
                else
                    img[i][j] = BLANC;
            }
        }
        return img;
    }
    
      
}
