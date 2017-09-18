/**
 * Un panneau pour dessiner un {@code Shape}.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;

public class DrawPanel extends JPanel
{
    private Shape shape;                // le Shape a dessiner
    private boolean withFrame;          // si true, dessiner le cadre
    private int xmin, xmax, ymin, ymax; // limites interieures du cadre

    /**
     * @pre  {@code Shape != null}
     * @post cree et affiche une fenetre contenant {@code shape}, sans cadre.
     */
    public DrawPanel(Shape shape) {
        this.shape = shape;
        this.withFrame = false;
        JFrame app = new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.add(this);
        app.setSize(512,512);
        app.setVisible(true);
    }

    /**
     * @pre  {@code Shape != null, xmin <= xmax, ymin <= ymax}
     * @post cree et affiche une fenetre contenant {@code shape}, 
     *       avec un cadre de limites interieures {@code xmin, xmax, ymin, ymax}.
     */
    public DrawPanel(Shape shape, int xmin, int xmax, int ymin, int ymax) {
        this.shape = shape;
        this.withFrame = true;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        JFrame app = new JFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.add(this);
        app.setSize(512,512);
        app.setVisible(true);
    }

    /**
     * @pre  {@code g} a ete initialise prealablement.
     * @post paint le dessin sur {@code g}.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (withFrame) {
            paintFrame(g, xmin, ymin, xmax-xmin, ymax-ymin);
        }

        shape.draw(g);
    }

    /**
     * @pre  {@code w > 0, h > 0}
     * @post paint un cadre de coin supérieur gauche {@code (x,y)} et de dimensions {@code (w,h)}.
     */
    private static void paintFrame(Graphics g, int x, int y, int w, int h) {
        final int FRAME = 25;
        final int MARGIN = 20;
        g.setColor(Color.GRAY);
        g.fillRect(x-FRAME, y-FRAME, w+2*FRAME, h+2*FRAME);
        g.setColor(Color.WHITE);
        g.fillRect(x-MARGIN, y-MARGIN, w+2*MARGIN, h+2*MARGIN);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, w, h);
    }
}
