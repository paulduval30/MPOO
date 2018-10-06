package serie03.cmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import serie03.StdText;
import serie03.Text;
import util.Contract;

/**
 * @inv <pre>
 *     getBackup() != null </pre>
 */
public class Clear extends AbstractCommand {
    private List<String> backup;

    // ATTRIBUTS
    
    private Text text;

    
    // CONSTRUCTEURS
    
    /**
     * Une commande de vidage du texte.
     * @pre <pre>
     *     text != null </pre>
     * @post <pre>
     *     getText() == text
     *     getState() == State.DO
     *     getBackup().size() == 0 </pre>
     */
    public Clear(Text text) {
        super(text);
        this.backup = new LinkedList<String>();
        String content = text.getContent();
        String[] contentTab = content.split(System.getProperty(System.lineSeparator()));
        for(String l : contentTab)
            backup.add(l);

    }
    
    // REQUETES
    
    /**
     * La liste des lignes composant le texte juste avant d'exécuter undoIt.
     */
    List<String> getBackup() {
        return this.backup;
    }
    
    // COMMANDES

    /**
     * Efface la totalité du texte.
     * @post <pre>
     *     getBackup().size() == old getText().getLinesNb()
     *     forall i:[0..getBackup().size()[ :
     *         getBackup().get(i).equals(old getText().getLine(i+1))
     *     getText().getLinesNb() == 0 </pre>
     */
    @Override
    protected void doIt() {
        this.text.clear();
    }
    
    /**
     * Régénère la totalité du texte.
     * @post <pre>
     *     getText().getLinesNb() == getBackup().size()
     *     forall i:[0..getText().getLinesNb()[ :
     *         getText().getLine(i+1).equals(getBackup().get(i)) </pre>
     */
    @Override
    protected void undoIt() {
        for(int i = 0; i < backup.size(); i++)
            this.text.insertLine(i, backup.get(i));
    }
}
