package serie03.cmd;

import serie03.Text;
import util.Contract;

/**
 * Classe fournissant un m�canisme g�n�ral pour les commandes.
 */
public abstract class AbstractCommand implements Command {

    // ATTRIBUTS
    
    private State state;
    
    private final Text text;

    // CONSTRUCTEURS

    /**
     * @pre <pre>
     *     text != null </pre>
     * @post <pre>
     *     getText() == text
     *     getState() == State.DO </pre>
     */
    protected AbstractCommand(Text text) {
        Contract.checkCondition(text != null, "le texte donn� est null");

        this.text = text;
        state = State.DO;
    }

    // REQUETES

    @Override
    public boolean canDo() {
        return state == State.DO;
    }
    
    @Override
    public boolean canUndo() {
        return state == State.UNDO;
    }
    
    @Override
    public Text getText() {
        return text;
    }
    
    @Override
    public State getState() {
        return state;
    }

    // COMMANDES
    
    @Override
    public void act() {
        Contract.checkCondition(canDo() || canUndo());

        if (canDo()) {
            doIt();
            state = State.UNDO;
        } else { // n�cessairement canUndo() == true
            undoIt();
            state = State.DO;
        }
    }
    
    /**
     * Cette m�thode doit �tre red�finie dans les sous-classes, de sorte
     *  qu'elle implante l'action � r�aliser pour ex�cuter la commande.
     * Elle est appel�e par act() et ne doit pas �tre appel�e directement.
     * @pre
     *     canDo()
     * @post
     *     La commande a �t� ex�cut�e
     */
    protected abstract void doIt();
    
    /**
     * Cette m�thode doit �tre red�finie dans les sous-classes, de sorte
     *  qu'elle implante l'action � r�aliser pour annuler la commande.
     * Si l'�tat du texte correspond � celui dans lequel il �tait apr�s doIt,
     *  alors undoIt r�tablit le texte dans l'�tat o� il �tait avant
     *  l'ex�cution de doIt.
     * Elle est appel�e par act() et ne doit pas �tre appel�e directement.
     * @pre
     *     canUndo()
     * @post
     *     La commande a �t� annul�e
     */
    protected abstract void undoIt();
}
