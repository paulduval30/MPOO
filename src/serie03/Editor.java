package serie03;

/**
 * Une interface qui sp�cifie la notion d'�diteur de texte (r�duit)
 *  dot� d'un m�canisme d'historique de commandes.
 * Les fonctionnalit�s (minimales ici) sont :
 * <ul>
 *   <li>ins�rer une ligne de texte ;</li>
 *   <li>supprimer une ligne de texte ;</li>
 *   <li>effacer le texte ;</li>
 * </ul>
 * @inv <pre>
 *     getTextLinesNb() >= 0
 *     getTextContent() != null
 *     getTextContent().equals("") <==> getTextLinesNb() == 0
 *     0 <= nbOfPossibleUndo() 
 *     0 <= nbOfPossibleRedo()
 *     0 < getHistorySize()
 *     nbOfPossibleUndo() + nbOfPossibleRedo() <= getHistorySize() </pre>
 * @cons
 * $DESC$
 *     Un �diteur dot� d'un texte vide et d'un historique de taille 
 *      <code>DEFAULT_HISTORY_SIZE</code>
 * $ARGS$
 * $PRE$
 * $POST$
 *     getTextLinesNb() == 0
 *     nbOfPossibleUndo() == 0
 *     nbOfPossibleRedo() == 0
 *     getHistorySize() == DEFAULT_HISTORY_SIZE
 * @cons
 * $DESC$
 *     Un �diteur dot� d'un texte vide et d'un historique de taille 
 *      <code>historySize</code>
 * $ARGS$
 *     int historySize
 * $PRE$
 *     historySize > 0
 * $POST$
 *     getTextLinesNb() == 0
 *     nbOfPossibleUndo() == 0
 *     nbOfPossibleRedo() == 0
 *     getHistorySize() == historySize
 */
public interface Editor {

    // CONSTANTES
    
    /**
     * La taille par d�faut de l'historique.
     */
    int DEFAULT_HISTORY_SIZE = 5;

    // REQUETES
    
    /**
     * Retourne le nombre de lignes du texte de l'�diteur.
     */
    int getTextLinesNb();
    
    /**
     * Les lignes de texte de l'�diteur, mises bout � bout, s�par�es
     *  par des retours chariot.
     */
    String getTextContent();
    
    /**
     * Le nombre maximal de commandes que peut contenir l'historique.
     */
    int getHistorySize();
    
    /**
     * Le nombre d'appels � <code>undo</code> qu'il est possible de r�aliser
     *  avant d'atteindre le d�but de l'historique.
     */
    int nbOfPossibleUndo();
    
    /**
     * Le nombre d'appels � <code>redo</code> qu'il est possible de r�aliser
     *  avant d'atteindre la fin de l'historique.
     */
    int nbOfPossibleRedo();
    
    // COMMANDES
    
    /**
     * Efface le texte.
     * @post <pre>
     *     getTextLinesNb() == 0
     *     nbOfPossibleUndo() == 
     *         min(old nbOfPossibleUndo() + 1, getHistorySize())
     *     nbOfPossibleRedo() == 0
     *     La commande d'effacement que l'on vient d'ex�cuter
     *      est la premi�re commande qui sera annul�e par undo </pre>
     */
    void clear();
    
    /**
     * Ins�re la cha�ne <code>s</code> � la ligne <code>numLine</code>.
     * @pre <pre>
     *     s != null
     *     1 <= numLine <= getTextLinesNb() + 1 </pre>
     * @post <pre>
     *     getTextLinesNb() == old getTextLinesNb() + 1
     *     nbOfPossibleUndo() == 
     *         min(old nbOfPossibleUndo() + 1, getHistorySize())
     *     nbOfPossibleRedo() == 0
     *     La ligne s a �t� ins�r�e dans le texte � la position numLine
     *     La commande d'insertion que l'on vient d'ex�cuter
     *      est la premi�re commande qui sera annul�e par undo </pre>
     */
    void insertLine(int numLine, String s);
    
    /**
     * Supprime la ligne <code>numLine</code>.
     * @pre <pre>
     *     1 <= numLine <= getTextLinesNb() </pre>
     * @post <pre>
     *     getTextLinesNb() == old getTextLinesNb() - 1
     *     nbOfPossibleUndo() == 
     *         min(old nbOfPossibleUndo() + 1, getHistorySize())
     *     nbOfPossibleRedo() == 0
     *     La ligne en position numLine a �t� retir�e du texte
     *     La commande de suppression que l'on vient d'ex�cuter
     *      est la premi�re commande qui sera annul�e par undo </pre>
     */
    void deleteLine(int numLine);
    
    /**
     * Refait la derni�re commande annul�e (non d�j� refaite).
     * @pre <pre>
     *     nbOfPossibleRedo() > 0 </pre>
     * @post <pre>
     *     nbOfPossibleUndo() == old nbOfPossibleUndo() + 1
     *     nbOfPossibleRedo() == old nbOfPossibleRedo() - 1
     *     La derni�re commande annul�e a �t� r�p�t�e
     *      un appel imm�diat � undo permet de l'annuler � nouveau </pre>
     */
    void redo();
    
    /**
     * Annule la derni�re commande ex�cut�e.
     * @pre <pre>
     *     nbOfPossibleUndo() > 0 </pre>
     * @post <pre>
     *     nbOfPossibleUndo() == old nbOfPossibleUndo() - 1
     *     nbOfPossibleRedo() == old nbOfPossibleRedo() + 1
     *     La derni�re commande a �t� annul�e
     *      un appel imm�diat � redo permet de l'ex�cuter � nouveau </pre>
     */
    void undo();
}
