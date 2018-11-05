package serie06;

/**
 * Mod�lise un distributeur automatique de boissons.
 * Un distributeur contient :
 *  - un stock de boissons de diff�rents types ;
 *  - une caisse (cash) contenant une certaine quantit� de chaque type de
 *     pi�ces utilisables ;
 *  - une r�serve permettant de stocker le credit ins�r� par le client (credit).
 *  - une r�serve permettant de stocker la monnaie � rendre au client
 *     (change).
 * Lorsque la caisse est insuffisamment remplie, la machine signale 
 *  qu'elle ne rendra pas la monnaie (canGetChange).
 * Le client d�sirant obtenir une boisson introduit des pi�ces (insertCoin).
 * Lorsqu'il s�lectionne une boisson (selectDrink), son cr�dit est revers� dans
 *  la caisse, sa boisson est d�bit�e du stock et la monnaie lui est 
 *  �ventuellement revers�e.
 * @inv <pre>
 *     forall d:DrinkTypes : 0 <= getDrinkNb(d) <= MAX_DRINK
 *     forall c:CoinTypes : 0 <= getCashNb(c) <= MAX_COIN
 *     forall c:CoinTypes : 0 <= getCreditNb(c) <= MAX_COIN
 *     forall c:CoinTypes : 0 <= getChangeNb(c)
 *     getCashAmount() == sum(c:CoinTypes, getCashNb(c) * c.getFaceValue())
 *     getCreditAmount() == sum(c:CoinTypes, getCreditNb(c) * c.getFaceValue())
 *     getChangeAmount() == sum(c:CoinTypes, getChangeNb(c) * c.getFaceValue())
 *     canGetChange()
 *         <==> la machine peut rendre la monnaie pour tout
 *              cr�dit fourni et pour toute boisson demand�e. </pre>
 * @cons
 * $POST$
 *     forall d:DrinkTypes : getDrinkNb(d) == MAX_DRINK
 *     forall c:CoinTypes : getCashNb(c) == 0
 *     forall c:CoinTypes : getCreditNb(c) == 0
 *     forall c:CoinTypes : getChangeNb(c) == 0
 */
public interface DrinksMachineModel {
    
    // CONSTANTES
    
    /**
     * Nombre maximal de boissons de chaque type.
     */
    int MAX_DRINK = 50;
    
    /**
     * Nombre maximal de pi�ces de chaque type.
     */
    int MAX_COIN = 100;

    // REQUETES
    
    /**
     * Le nombre de boissons du type d actuellement stock�es dans la machine.
     * @pre
     *     d != null
     */
    int getDrinkNb(DrinkTypes d);
    
    /**
     * Indique le type de la derni�re boisson obtenue du mod�le.
     * Retourne null si la boisson a �t� prise, ou si le mod�le n'a pas encore
     *  d�livr� de boisson.
     */
    DrinkTypes getLastDrink();
    
    /**
     * Le montant total du cr�dit du client actuellement stock� dans la machine.
     * On peut annuler ce cr�dit en faisant cancelCredit(), ce qui a pour effet
     *  de remplir d'autant getChange().
     */
    int getCreditAmount();
    
    /**
     * Le nombre de pi�ces de type c paticipant au credit actuellement stock�
     *  dans la machine.
     * @pre
     *     c != null
     */
    int getCreditNb(CoinTypes c);
    
    /**
     * Le montant total actuellement stock� dans la caisse de la machine.
     */
    int getCashAmount();
    
    /**
     * Le nombre de pi�ces de type c pr�sentes dans la caisse de la machine.
     * @pre
     *     c != null
     */
    int getCashNb(CoinTypes c);
    
    /**
     * La monnaie actuellement rendue par la machine.
     * On peut prendre cette monnaie en faisant takeChange().
     */
    int getChangeAmount();
    
    /**
     * Le nombre de pi�ces de type c pr�sentes dans la monnaie rendue par
     *  la machine.
     * @pre
     *     c != null
     */
    int getChangeNb(CoinTypes c);
    
    /**
     * La machine pourra-t-elle rendre la monnaie quels que soient la boisson
     *  command�e et le montant cr�dit� ?
     */
    boolean canGetChange();
    
    // COMMANDES
    
    /**
     * La machine fournit une boisson de type <code>d</code>.
     * Le cr�dit est mis en caisse et la monnaie est �ventuellement rendue.
     * @pre
     *     d != null
     *     getDrinkNb(d) >= 1
     *     getCreditAmount() >= d.getPrice()
     *     getLastDrink() == null
     * @post
     *     getDrinkNb(d) == old getDrinkNb(d) - 1
     *     getLastDrink() == d
     *     getCreditAmount() == 0
     *     old canGetChange() ==> 
     *         getCashAmount() == old getCashAmount() + d.getPrice()
     *         getChangeAmount()  ==
     *             old (getChangeAmount() + getCreditAmount()) - d.getPrice()
     *     !old canGetChange() ==> 
     *         getCashAmount() == old (getCashAmount() + getCreditAmount())
     */
    void selectDrink(DrinkTypes d);
    
    /**
     * Remplit le stock de boissons de type d dans la machine.
     * @pre
     *     d != null
     *     q > 0 && getDrinkNb(d) + q <= MAX_DRINK
     * @post
     *     getDrinkNb(d) == old getDrinkNb(d) + q
     */
    void fillStock(DrinkTypes d, int q);
    
    /**
     * Remplit la caisse de monnaie de la machine.
     * @pre
     *     c != null
     *     q > 0 && getCashNb(c) + q <= MAX_COIN
     * @post
     *     getCashNb(c) == old getCashNb(c) + q
     */
    void fillCash(CoinTypes c, int q);
    
    /**
     * Augmente le cr�dit d'une pi�ce de type c si possible.
     * @pre
     *     c != null
     * @post
     *     old getCashNb(c) + getCreditNb(c) == MAX_COIN
     *         ==> getChangeNb(c) == old getChangeNb(c) + 1
     *     old getCashNb(c) + getCreditNb(c) < MAX_COIN
     *         ==> getCreditNb(c) == old getCreditNb(c) + 1
     */
    void insertCoin(CoinTypes c);
    
    /**
     * Annule le cr�dit.
     * @post
     *     getCreditAmount() == 0
     *     getChangeAmount() == old (getChangeAmount() + getCreditAmount())
     */
    void cancelCredit();
    
    /**
     * Simule la prise de la boisson d�livr�e.
     * @post
     *     getLastDrink() == null
     */
    void takeDrink();
    
    /**
     * Simule la prise de la monnaie rendue.
     * @post
     *     getChangeAmount() == 0
     */
    void takeChange();
    
    /**
     * Vide totalement la machine.
     * @post
     *     getCashAmount() == 0
     *     getCreditAmount() == 0
     *     getChangeAmount() == 0
     *     getLastDrink() == null
     *     forall d:DrinkTypes : getDrinkNb(d) == 0
     */
    void reset();
}
