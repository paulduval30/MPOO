package serie09;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicSpeedometer extends JComponent
{
    // marge horizontale interne de part et d'autre du composant
    private static final int MARGIN = 40;
    // épaisseur de la ligne horizontale graduée
    private static final int THICK = 3;
    // demi-hauteur d'une graduation
    private static final int MARK = 20;
    // largeur de la base du triangle pour la tête de la flèche
    private static final int ARROW_BASE = 20;
    // épaisseur du corps de la flèche
    private static final int ARROW_THICK = 4;
    // hauteur du corps de la flèche
    private static final int ARROW_HEIGHT = 20;
    // hauteur préférée d'un GraphicSpeedometer
    private static final int PREFERRED_HEIGHT = 3 * (3 * MARK + ARROW_BASE / 2 + ARROW_HEIGHT);
    // facteur d'échelle pour l'espacement entre deux graduations
    private static final double ASPECT_RATIO = 1.25;
    // couleur bleu franc lorsque le moteur est allumé
    private static final Color BLUE = Color.BLUE;
    // couleur rouge franc lorsque le moteur est allumé
    private static final Color RED = Color.RED;
    // couleur bleu grisé lorsque le moteur est éteint
    private static final Color GRAYED_BLUE = new Color(0, 0, 255, 50);
    // couleur rouge grisé lorsque le moteur est éteint
    private static final Color GRAYED_RED = new Color(255, 0, 0, 50);
    // les vitesses affichées sont celles, entre 0 et model.getMaxSpeed(), qui sont les multiples de SPLIT_SIZE
    private static final int SPLIT_SIZE = 10;

    private SpeedometerModel model;

    public GraphicSpeedometer(SpeedometerModel model, Dimension prefferedSize)
    {
        JFrame j = new JFrame();
        this.model = model;
        this.setPreferredSize(prefferedSize);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int h = this.getHeight() / 3;
        int w = this.getWidth() - 2 * MARGIN;

        g.setColor(this.model.isOn() ? Color.BLUE : GRAYED_BLUE);
        g.fillRect(MARGIN, this.getHeight() / 3, this.getWidth() -  MARGIN * 2, THICK);

        for(int i = 0; i < this.model.getMaxSpeed(); i ++)
        {
            FontMetrics fm = g.getFontMetrics();
            String s = String.valueOf(i * SPLIT_SIZE);
            int sWidth = fm.stringWidth(s);
            double pxForUnit = w / model.getMaxSpeed();

            if(i % SPLIT_SIZE == 0)
            {
                int xQ = MARGIN + (int)(i * (pxForUnit));
                g.drawLine(xQ, h - MARK, xQ, h + MARK);
                g.drawString(i + "", xQ - sWidth / 4, h - MARK);
            }
        }

        int xA = (int)(MARGIN + (w * model.getSpeed()) / model.getMaxSpeed() - ARROW_BASE / 2);
        int yA = (int)(h + THICK + 2 * MARK + ARROW_BASE / 2);
        int xB = xA + ARROW_BASE / 2;
        int yB = yA - ARROW_HEIGHT / 2;
        int xC = xB + ARROW_BASE / 2;
        int yC = yA;
        g.setColor(this.model.isOn() ? Color.RED : GRAYED_RED);
        g.fillPolygon(new int[]{xA, xB, xC}, new int[]{yA, yB, yC}, 3);
        g.fillRect(xB -  ARROW_THICK / 2, yA, ARROW_THICK, ARROW_HEIGHT);
    }

}
