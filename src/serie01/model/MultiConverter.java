package serie01.model;

import serie01.util.Currency;

/**
 * Sp�cifie un mod�le de conversion mon�taire.
 * Ce mod�le g�re un nombre quelconque de monnaies et les montants associ�s.
 * Les montants sont toujours �quivalents modulo le taux de change entre
 *  les devises.
 * Il est possible de changer :
 * <ul>
 *  <li>chacune des devises ;</li>
 *  <li>chacun des montants ;</li>
 *  <li>le taux de change (par rapport � l'Euro) d'une devise
 *   quelconque ;</li>
 * </ul>
 * @inv <pre>
 *     getCurrencyNb() >= 2
 *     forall i,j:[0..getCurrencyNb()[ :
 *         getCurrency(i) != null
 *         getExchangeRate(i, j) ==
 *             getCurrency(j).getExchangeRate()
 *                 / getCurrency(i).getExchangeRate()
 *         getAmount(j) == getAmount(i) * getExchangeRate(i, j) </pre>
 * 
 * @cons
 * $DESC$
 *     Le mod�le est initialis� avec des montants nuls, des devises toutes �
 *      l'euro.
 * $ARG$
 *     int n
 * $PRE$
 *     n >= 2
 * $POST$
 *     getCurrencyNb() == n
 *     forall i:[0..n[ :
 *         getCurrency(i) == Currency.get(CurrencyId.EUR)
 *         getAmount(i) == 0.0
 */
public interface MultiConverter {

    // REQUETES
    /**
     * Indique la valeur m�moris�e par le mod�le, convertie dans la monnaie
     *  enregistr�e � l'index <code>index</code>.
     * @pre <pre>
     *     0 <= index < getCurrencyNb() </pre>
     */
    double getAmount(int index);

    /**
     * La devise m�moris�e � l'index <code>index</code>.
     * @pre <pre>
     *     0 <= index < getCurrencyNb() </pre>
     */
    Currency getCurrency(int index);
    
    /**
     * Le nombre total de devises dans ce mod�le.
     */
    int getCurrencyNb();
    
    /**
     * Le taux de change entre les devises d'index pr�cis�s pour ce mod�le.
     * @pre <pre>
     *     0 <= index1 < getCurrencyNb()
     *     0 <= index2 < getCurrencyNb() </pre>
     */
    double getExchangeRate(int index1, int index2);

    // COMMANDES
    /**
     * Fixe la valeur m�moris�e par le mod�le au montant <code>amount</code>
     *  pour la devise enregistr�e au rang <code>index</code>. 
     * @pre <pre>
     *     0 <= index < getCurrencyNb()
     *     amount >= 0.0 </pre>
     * @post <pre>
     *     getAmount(index) == amount </pre>
     */
    void setAmount(int index, double amount);

    /**
     * Enregistre la devise <code>c</code> aupr�s du mod�le
     *  au rang <code>index</code>.
     * @pre <pre>
     *     0 <= index < getCurrencyNb()
     *     c != null </pre>
     * @post <pre>
     *     getCurrency(index) == c </pre>
     */
    void setCurrency(int index, Currency c);
}
