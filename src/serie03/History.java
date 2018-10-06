package serie03;

/**
 * Une interface sp�cifiant les fonctionnalit�s d'un outil de gestion
 *  d'historique.
 * Un historique est une structure lin�aire dynamique born�e.
 * On peut toujours ajouter des �l�ments � un historique : lorsque 
 *  l'historique est plein, rajouter un nouvel �l�ment fait dispara�tre
 *  le plus ancien.
 * On peut avancer et reculer le curseur rep�rant l'�l�ment courant �
 *  loisir dans l'historique, mais si le curseur n'est pas sur l'�l�ment le 
 *  plus r�cent, ajouter un �l�ment dans l'historique � cet instant fait 
 *  dispara�tre les �l�ments post�rieurs au curseur.
 * Enfin, les �l�ments d'un historique sont rang�s entre les positions 1 et
 *  getMaxHeight(), et par convention getEndPosition() == 0 ssi l'historique
 *  est vide.
 * @inv <pre>
 *     getMaxHeight() > 0
 *     0 <= getCurrentPosition() <= getEndPosition() <= getMaxHeight()
 *     getCurrentElement() != null
 *     getCurrentElement() == l'�l�ment � la position getCurrentPosition()
 * </pre>
 * @cons <pre>
 *     $DESC$ Un historique vide de taille maximale maxHeight.
 *     $ARGS$ int maxHeight
 *     $PRE$
 *         maxHeight > 0
 *     $POST$
 *         getMaxHeight() == maxHeight
 *         getCurrentPosition() == 0
 *         getEndPosition() == 0 </pre>
 */
public interface History<E> {
    
    // REQUETES
    
    /**
     * La taille maximale de l'historique.
     */
    int getMaxHeight();
    
    /**
     * La position courante dans l'historique.
     */
    int getCurrentPosition();
    
    /**
     * L'�l�ment d�sign� par la position courante.
     * @pre <pre>
     *     getCurrentPosition() > 0 </pre>
     */
    E getCurrentElement();
    
    /**
     * La derni�re position de l'historique.
     */
    int getEndPosition();

    // COMMANDES
    
    /**
     * Ajoute l'�l�ment <code>e</code> � la suite de l'�l�ment courant
     *  et supprime les �l�ments post�rieurs � cet �l�ment courant.
     * S'il n'y a pas d'�l�ment courant, ajoute simplement <code>e</code>
     *  comme premier �l�ment.
     * L'�l�ment <code>e</code> devient le nouvel �l�ment courant.
     * @pre <pre>
     *     e != null </pre>
     * @post <pre>
     *     getCurrentPosition() == 
     *         min(old getCurrentPosition() + 1, getMaxHeight())
     *     getCurrentElement() == e
     *     getEndPosition() == getCurrentPosition()
     *     si l'historique �tait plein, le plus ancien �l�ment a disparu </pre>
     */
    void add(E e);
    
    /**
     * Avance le curseur dans la direction du plus r�cent �l�ment.
     * @pre <pre>
     *     getCurrentPosition() < getEndPosition() </pre>
     * @post <pre>
     *     getCurrentPosition() == old getCurrentPosition() + 1 </pre>
     */
    void goForward();
    
    /**
     * Recule le curseur dans la direction du plus ancien �l�ment.
     * @pre <pre>
     *     getCurrentPosition() > 0 </pre>
     * @post <pre>
     *     getCurrentPosition() == old getCurrentPosition() - 1 </pre>
     */
    void goBackward();
}
