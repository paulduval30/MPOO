package serie07;

/**
 * @inv
 *     moneyLost() >= 0
 *     moneyWon() >= 0
 *     result() != null
 *     result().length() >= MIN_RESULT_SIZE
 *     credit(n) >= 0
 *     Soit n ::= le plus grand nombre de lettres identiques dans result()
 *         lastPayout() == credit(n)
 * @cons
 *     $ARGS$ int[] credits
 *     $PRE$
 *         credits != null
 *         credits.length >= MIN_RESULT_SIZE + 1
 *         forall i : credits[i] >= 0
 *     $POST$
 *         forall i : credit(i) == credits[i]
 *         moneyLost() == 0
 *         result().length() == credits.length - 1
 *         forall i : result().charAt(i) == ' '
 *         moneyWon() == credit(0)
 */
public interface SlotModel extends ObservableModel {
    
    // CONSTANTES
    
    int MIN_RESULT_SIZE = 3;
    
    // REQUETES
    
    /**
     * Ce que rapporte le fait que result() contienne n fois une même lettre,
     *  n étant maximal.
     * @pre
     *     0 <= n <= result().length()
     */
    int credit(int n);

    /**
     * Le dernier gain obtenu.
     */
    int lastPayout();
    
    /**
     * Le montant des pertes.
     */
    int moneyLost();
    
    /**
     * Le montant des gains.
     */
    int moneyWon();
    
    /**
     * Le résultat du dernier coup joué.
     */
    String result();
    
    // COMMANDES
    
    /**
     * Joue un coup.
     * @post
     *     moneyLost() == old moneyLost() + 1
     *     result() retourne une chaîne qui vient d'être aléatoirement
     *         sélectionnée et dont les éléments sont dans 'A' - 'Z'
     *     moneyWon() == (old moneyWon()) + lastPayout()
     */
    void gamble();
}
