package serie01.util;

/**
 * Mod�lise les bases de donn�es de monnaies du monde.
 * @inv <pre>
 *     forall id:CurrencyId :
 *         getExchangeRate(id) > 0
 *         (getIsoCode(id) != null) && (getIsoCode(id).length() == 3)
 *         (getLand(id) != null) && !getLand(id).equals("")
 *         (getName(id) != null) && !getName(id).equals("") </pre>
 */
public interface CurrencyDB {
    // REQUETES
    /**
     * Le taux de change de la monnaie d'identificateur <code>id</code> par
     *  rapport � l'euro.
     * @pre <pre> id != null </pre>
     */
    double getExchangeRate(CurrencyId id);

    /**
     * Le code ISO (cha�ne de caract�res) de la monnaie d'identificateur
     *  <code>id</code>.
     * @pre <pre> id != null </pre>
     */
    String getIsoCode(CurrencyId id);
    
    /**
     * Le pays dans lequel la monnaie d'identificateur <code>id</code> 
     *  a cours.
     * @pre <pre> id != null </pre>
     */
    String getLand(CurrencyId id);
    
    /**
     * Le nom de la monnaie d'identificateur <code>id</code>.
     * @pre <pre> id != null </pre>
     */
    String getName(CurrencyId id);

    // COMMANDES
    /**
     * Fixe � <code>rate</code> le taux de change de la monnaie 
     *  d'identificateur <code>id</code>.
     * @pre <pre>
     *     id != null
     *     rate > 0 </pre>
     * @post <pre>
     *     getExchangeRate(id) == rate </pre>
     */
    void setExchangeRate(CurrencyId id, double rate);
}
