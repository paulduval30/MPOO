package serie03.cmd;

import serie03.Text;
import util.Contract;

/**
 * @inv <pre>
 *     1 <= getIndex()
 *     getLine() != null
 *     canDo() ==> getIndex() <= getText().getLinesNb() + 1
 *     canUndo() ==> getIndex() <= getText().getLinesNb() </pre>
 */
public class InsertLine extends AbstractCommand {

    //ATTRIBUTS
    private String line;
    private int numLine;
    private Text text;
    
    // CONSTRUCTEURS
    
    /**
     * Une commande d'insertion de la chaîne <code>line</code> en
     *  position <code>index</code> dans <code>text</code>.
     * @pre <pre>
     *     text != null
     *     line != null
     *     1 <= numLine </pre>
     * @post <pre>
     *     getText() == text
     *     getIndex() == numLine
     *     getLine().equals(line)
     *     getState() == State.DO </pre>
     */
    public InsertLine(Text text, int numLine, String line) {
        super(text);
        Contract.checkCondition(line != null,"You can't add a null line");
        Contract.checkCondition(1 <= numLine, "You can't add a line in nowhere");
        this.text = text;
        this.numLine = numLine;
        this.line = line;

    }
    
    // REQUETES
    
    @Override
    public boolean canDo() {
       return super.canDo();
    }
    
    @Override
    public boolean canUndo() {
      return super.canUndo();
    }
    
    /**
     * Le rang où l'on doit insérer la ligne dans le texte.
     */
    int getIndex() {
        return this.numLine;
    }
    
    /**
     * La ligne à insérer.
     */
    String getLine() {
        return this.line;
    }
    
    // COMMANDES
    
    /**
     * Exécute l'insertion de la chaîne dans le texte.
     * @post <pre>
     *     getText().getLinesNb() == old getText().getLinesNb() + 1
     *     getText().getLine(getIndex()).equals(getLine())
     *     forall i:[getIndex() + 1..getText().getLinesNb()] :
     *         getText().getLine(i).equals(old getText().getLine(i - 1)) </pre>
     */
    @Override
    protected void doIt() {
        this.text.insertLine(this.numLine, this.line);
    }
    
    /**
     * Annule l'insertion de la chaîne dans le texte.
     * @post <pre>
     *     getText().getLinesNb() == old getText().getLinesNb() - 1
     *     forall i:[getIndex()..getText().getLinesNb()] :
     *         getText().getLine(i).equals(old getText().getLine(i + 1)) </pre>
     */
    @Override
    protected void undoIt() {
        this.text.deleteLine(this.numLine);
    }
}
