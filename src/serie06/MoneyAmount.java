package serie06;

/**
 * Mod�lise les sommes d'argent.
 * Une telle somme est constitu�e uniquement d'un certain nombre de cents
 *  et d'euros.
 * @inv
 *     forall c:CoinTypes : getValue(c) == getNumber(c) * c.getFaceValue()
 *     getTotalValue() == sum(c:CoinTypes, getValue(c))
 * @cons
 * $POST$ forall c:CoinTypes : getNumber(c) == 0
 */
public interface MoneyAmount extends Stock<CoinTypes> {

    // REQUETES
    
    /**
     * Le montant constitu� par l'ensemble des pi�ces de type 
     *  <code>c</code>.
     * @pre
     *     c != null
     */
    int getValue(CoinTypes c);
    
    /**
     * Le montant total de cette somme, tous types de pi�ce confondus.
     */
    int getTotalValue();
    
    // COMMANDES
    
    /**
     * Ajoute la somme <code>amount</code> � la somme courante.
     * @pre
     *     amount != null
     * @post
     *     forall c:CoinTypes :
     *         getNumber(c) == old getNumber(c) + amount.getNumber(c)
     */
    void addAmount(MoneyAmount amount);
    
    /**
     * Une somme d'argent dont le montant vaut <code>s</code>
     *  et qui peut �tre pr�lev�e de la somme courante.
     * Retourne null s'il n'est pas possible de faire la monnaie.
     * @pre
     *     s > 0
     * @post
     *     result != this
     *     result == null <==> il n'est pas possible de faire la monnaie
     *     result != null
     *         ==> result.getTotalValue() == s
     *             forall c:CoinTypes :
     *                 result.getNumber(c) <= getNumber(c)
     */
    MoneyAmount computeChange(int s);
    
    /**
     * Retire la somme <code>amount</code> de la somme courante.
     * @pre
     *     amount != null
     *     forall c:CoinTypes :
     *         getNumber(c) >= amount.getNumber(c)
     * @post
     *     forall c:CoinTypes :
     *         getNumber(c) == old getNumber(c) - amount.getNumber(c)
     */
    void removeAmount(MoneyAmount amount);
}
