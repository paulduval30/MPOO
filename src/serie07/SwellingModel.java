package serie07;

import java.awt.Dimension;

/**
 * Notre modèle consiste en une Dimension que l'on fait croître et décroître
 *  à volonté.
 * Cette Dimension ne peut évoluer que dans certaines limites.
 * @inv <pre>
 *     getMinDimension() != null
 *     getDimension() != null
 *     getMaxDimension() != null
 *     getMinDimension().width <= getDimension().width
 *     getDimension().width <= getMaxDimension().width
 *     getMinDimension().height <= getDimension().height
 *     getDimension().height <= getMaxDimension().height </pre>
 * @cons <pre>
 * $DESC$ un modèle de Dimension égale à 0
 * $POST$
 *     getMinDimension().width == getMinDimension().height == 0
 *     getMaxDimension().width == getMaxDimension().height == 0 </pre>
 */
public interface SwellingModel extends ObservableModel {
    
    // REQUETES

    /**
     * Une dimension équivalente à la dimension courante du modèle.
     */
    Dimension getDimension();
    
    /**
     * Une dimension équivalente à la dimension maximale du modèle.
     */
    Dimension getMaxDimension();    
    
    /**
     * Une dimension équivalente à la dimension minimale du modèle.
     */
    Dimension getMinDimension();    
    
    // COMMANDES
    
    /**
     * Fixe les dimensions minimale et maximale du modèle.
     * Retaille la dimension courante si nécessaire.
     * @pre <pre>
     *     min != null && max != null
     *     0 <= min.width <= max.width
     *     0 <= min.height <= max.height </pre>
     * @post <pre>
     *     getMinDimension().equals(min)
     *     getMaxDimension().equals(max)
     *     getDimension().width == min(
     *         max(old getDimension().width, getMinDimension().width),
     *         getMaxDimension().width
     *     )
     *     getDimension().height == min(
     *         max(old getDimension().height, getMinDimension().height),
     *         getMaxDimension().height
     *     ) </pre>
     */
    void setBounds(Dimension min, Dimension max);

    /**
     * Change la dimension du modèle en multipliant ses caractéristiques par
     *  1 + factor.
     * Si factor est positif, cela constitue une augmentation de taille.
     * Si factor est entre -1 et 0, cela constitue une diminution de taille.
     * @pre <pre>
     *     factor >= -1 </pre>
     * @post <pre>
     *     getDimension().width == min(
     *         max(
     *             old getDimension().width * (1 + factor),
     *             getMinDimension().width
     *         ),
     *         getMaxDimension().width
     *     )
     *     getDimension().height == min(
     *         max(
     *             old getDimension().height * (1 + factor),
     *             getMinDimension().height
     *         ),
     *         getMaxDimension().height
     *     ) </pre>
     */
    void scale(double factor);
}
