
/**
 * Une classe permettant de reprsenter un segment de droite
 * 
 * @author O. Bonaventure, Ch. Pecheur
 * @version 3 Nov 2012
 */

import java.awt.Color;
import java.awt.Graphics;

public class Segment
{
   private int x0,y0; // coordonees de la premiere extremite
   private int x1,y1; // coordonnees de la seconde extremite

   /**
     * @pre x0,y0,x1,y1 >=0 et <512
     * @post a construit un segment de droite reliant x0,y0 a x1,y1
     */
    public Segment(int x0, int y0, int x1, int y1)
    {
        this.x0=x0;
        this.y0=y0;
        this.x1=x1;
        this.y1=y1;
    }
    
    // voir interface Shape
    public void draw(Graphics g)
    {
        // affichage du segment de droite
        g.drawLine(x0,y0,x1,y1);
    }

    // A COMPLETER
}
