package serie07;

import java.awt.Color;
import java.util.Observable;

/**
 * Notre mod�le est une s�quence de couleurs (toutes distinctes) que l'on
 *  peut parcourir cycliquement.
 * Les valeurs de getColor() apr�s chaque ex�cution de changeColor() sont
 *  celles obtenues en parcourant cycliquement le tableau COLORS.
 */
public class StdRainbowModel extends Observable implements RainbowModel {

    // ATTRIBUTS
    
    private static final Color[] COLORS = {
        Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
        Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW
    };
    private int currentColorIndex;

    // CONSTRUCTEURS
    
    /**
     * Un mod�le standard, dont la couleur courante est la premi�re couleur
     *  du tableau.
     */
    public StdRainbowModel() {
        currentColorIndex = 0;
    }

    // REQUETES
    
    @Override
    public Color getColor() {
        return COLORS[currentColorIndex];
    }

    // COMMANDES
    
    @Override
    public void changeColor() {
        currentColorIndex = (currentColorIndex + 1) % COLORS.length;
        // le mod�le prend en compte le fait que son �tat a chang�
        setChanged();
        notifyObservers();
    }
}
