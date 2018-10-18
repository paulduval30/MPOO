/*
package serie04;

public class StdContact implements Contact
{
    private String nom;
    private String prenom;
    private Civ civ;

    public StdContact(String nom, String prenom)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.civ = Civ.UKN;
    }
    public StdContact(Civ civility, String nom, String prenom)
    {
        this.prenom = prenom;
        this.nom = nom;
        this.civ = civility;
    }

    @Override
    public int compareTo(Contact c)
    {
        if(this.nom.compareTo(c.getLastName()) < 0 || this.nom.compareTo(c.getLastName()) == 0
                && this.prenom.compareTo(c.getFirstName()) < 0 ||
                this.nom.compareTo(c.getLastName()) == 0 &&
                        this.prenom.compareTo(c.getFirstName()) == 0
                && this.civ.compareTo(c.getCivility()) < 0)
            return -1;

        if(this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) == 0
                && this.getCivility().compareTo(c.getCivility()) == 0)
            return 0;
        if(this.getLastName().compareTo(c.getLastName()) > 0
                || (this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) > 0)
                || (this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) == 0
                && this.getCivility().compareTo(c.getCivility()) > 0))
            return 1;
        return -1;
    }

    @Override
    public boolean equals(Object other)
    {
        return this.hashCode() == ((Contact)other).hashCode();
    }

    @Override
    public int hashCode()
    {
        return combine_hash(combine_hash(this.nom.hashCode(), this.prenom.hashCode()), this.civ.hashCode());
    }

    @Override
    public Civ getCivility()
    {
        return this.civ;
    }

    @Override
    public String getFirstName()
    {
        return this.prenom;
    }

    @Override
    public String getLastName()
    {
        return this.nom;
    }

    @Override
    public Contact evolve(Civ civility) {
        if(this.civ.canEvolveTo(civility))
            return new StdContact(civility, this.nom, this.prenom);
        return null;
    }

    static int combine_hash(int x,int y)
    {
        int rc=0;

        for (int i=7;i>=0;--i)
        {
            if (i==4) x=y;
            rc=rc*21+(char)x;
            x >>>=8;
        }
        return rc;
    }

}
*/

package serie04;

import util.Contract;

public class StdContact implements Contact {

    private String nom;
    private String prenom;
    private Civ civilite;

    public StdContact(String nom, String prenom) {
        Contract.checkCondition(nom!= null, "Nom invalide.");
        Contract.checkCondition(prenom!=null, "Prénom invalide.");
        Contract.checkCondition(!prenom.equals("") || !nom.equals(""), "Il doit y avoir au moins un nom ou un prénom.");
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = Civ.UKN;
    }

    public StdContact(Civ c, String nom, String prenom) {
        Contract.checkCondition(nom!= null, "Nom invalide.");
        Contract.checkCondition(prenom!=null, "Prénom invalide.");
        Contract.checkCondition(!prenom.equals("") || !nom.equals(""), "Il doit y avoir au moins un nom ou un prénom.");
        Contract.checkCondition(c != null, "La civilité ne peut pas être nulle.");
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = c;
    }

    /**
     * L'ordre naturel sur les contacts.
     * @pre
     *     c != null
     * @throws NullPointerException si c == null
     */
    @Override
    public int compareTo(Contact c) throws NullPointerException{
        if(c == null)
            throw new NullPointerException();

        if(this.getLastName().compareTo(c.getLastName()) < 0
                || (this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) < 0)
                || (this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) == 0
                && this.getCivility().compareTo(c.getCivility()) < 0)) {
            return -1;

        }
        else if(this.getLastName().compareTo(c.getLastName()) == 0
                && this.getFirstName().compareTo(c.getFirstName()) == 0
                && this.getCivility().compareTo(c.getCivility()) == 0) {
            return 0;
        }
        else return 1;
    }

    /**
     * Teste l'équivalence de ce contact avec other.
     */
    @Override
    public boolean equals(Object other) {
        if(other instanceof StdContact) {
            if(getCivility().equals(((StdContact) other).getCivility()) && getFirstName().equals(((StdContact) other).getFirstName())
                    && getLastName().equals(((StdContact) other).getLastName()) && hashCode() == other.hashCode()) {
                return true;
    			/*return getCivility().equals(autre.getCivility())
        				&& getFirstName().equals(autre.getFirstName())
        				&& getLastName().equals(autre.getLastName())
        				&& hashCode() == autre.hashCode();*/
            }
        }
        return false;
    }

    /**
     * La civilité du contact.
     */
    @Override
    public Civ getCivility() {
        return civilite;
    }

    /**
     * Le prénom du contact.
     */
    @Override
    public String getFirstName() {
        return this.prenom;
    }

    /**
     * Le nom de famille du contact.
     */
    @Override
    public String getLastName() {
        return this.nom;
    }

    /**
     * Fonction de dispersion définie sur les contacts.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + getCivility().hashCode();
        result = prime * result + getFirstName().hashCode();
        result = prime * result + getLastName().hashCode();

        return result;
    }

    /**
     * Représentation textuelle de ce contact.
     */
    @Override
    public String toString() {
        return getCivility() + " " + getFirstName() + " " + getLastName();
    }


    /**
     * Donne un contact qui diffère de this uniquement par la civilité.
     * Les modification suivantes sont autorisées :
     * <pre>
     *   UKN --> MR, MRS ou MS
     *   MRS --> MS
     *   MS  --> MRS
     * </pre>
     * Toute autre modification est interdite (voir {@link serie04.Civ}).
     * @pre <pre>
     *     civility != null && getCivility().canEvolveTo(civility) </pre>
     * @post <pre>
     *     result.getCivility() == civility
     *     result.getFirstName() == this.getFirstName()
     *     result.getLastName() == this.getLastName() </pre>
     */
    @Override
    public Contact evolve(Civ civility) {
        Contract.checkCondition(civility!=null && getCivility().canEvolveTo(civility), "L'une des civilité est incompatible.");
        Contact c = new StdContact(civility, getLastName(), getFirstName());
        return c;
    }

}
