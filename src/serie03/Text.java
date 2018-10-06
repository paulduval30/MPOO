package serie03;

/**
 * Un texte est une cha�ne de caract�res structur�e en lignes.
 * @inv <pre>
 *     getLinesNb() >= 0
 *     forall i:[1..getLinesNb()] : getLine(i) != null
 *     getContent() != null
 *     getContent().equals(sum(i:[1..getLinesNb()], getLine(i) + NL)) </pre>
 * @cons
 * $DESC$
 *     Un texte vide
 * $ARGS$
 * $PRE$
 * $POST$
 *     getLinesNb() == 0
 */
public interface Text {
    
    // CONSTANTES
    
    String NL = System.getProperty("line.separator");
    
    // REQUETES
    
    /**
     * Le nombre de lignes du texte.
     */
    int getLinesNb();
    
    /**
     * La i-�me ligne du texte.
     * @pre <pre>
     *     1 <= i <= getLinesNb() </pre>
     */
    String getLine(int i);
    
    /**
     * Le contenu de ce texte sous forme d'une cha�ne constitu�e des lignes
     *  termin�es par des retours chariot.
     */
    String getContent();
    
    // COMMANDES

    /**
     * Ins�re la cha�ne <code>s</code> en ligne <code>numLine</code>.
     * @pre <pre>
     *     (1 <= numLine) && (numLine <= getLinesNb()+1)
     *     s != null </pre>
     * @post <pre>
     *     getLinesNb() == old getLinesNb() + 1
     *     getLine(numLine).equals(s)
     *     forall i:[numLine + 1 .. getLinesNb()] :
     *         getLine(i).equals(old getLine(i - 1)) </pre>
     */
    void insertLine(int numLine, String s);
    
    /**
     * Supprime la ligne <code>numLine</code>.
     * @pre <pre>
     *     (1 <= numLine) && (numLine <= getLinesNb()) </pre>
     * @post <pre>
     *     getLinesNb() == old getLinesNb() - 1
     *     forall i:[numLine .. getLinesNb()] :
     *         getLine(i).equals(old getLine(i + 1)) </pre>
     */
    void deleteLine(int numLine);
    
    /**
     * Efface la totalit� du texte.
     * @post <pre>
     *     getContent().equals("") </pre>
     */
    void clear();
}
