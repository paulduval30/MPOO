package serie03.cmd;

import serie03.Text;

/**
 * Une commande est un objet capable de modifier un texte selon certains 
 *  crit�res.
 * La s�mantique de la commande ne pourra �tre compl�te que dans les classes
 *  qui impl�menteront cette interface.
 * @inv <pre>
 *     getState() != null
 *     getText() != null
 *     canDo() ==> getState() == State.DO
 *     canUndo() ==> getState() == State.UNDO </pre>
 */
public interface Command {
    
    // REQUETES
    
    /**
     * Le texte sur lequel la commande agit.
     */
    Text getText();
    
    /**
     * L'�tat interne de la commande.
     */
    State getState();
    
    /**
     * Indique que la commande et son environnement sont dans un �tat
     *  permettant de faire la commande.
     */
    boolean canDo();
    
    /**
     * Indique que la commande et son environnement sont dans un �tat
     *  permettant de d�faire la commande.
     */
    boolean canUndo();

    // COMMANDES
    
    /**
     * D�finit l'action qu'effectue la commande sur le texte associ�.
     * @pre <pre>
     *     canDo() || canUndo() </pre>
     * @post <pre>
     *     getState() != old getState()
     *     old canDo()
     *         ==> la commande a fait son action sur son texte
     *     old canUndo()
     *         ==> la commande a d�fait son action sur son texte </pre>
     */
    void act();
}
