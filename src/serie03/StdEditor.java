package serie03;

import serie03.cmd.Clear;
import serie03.cmd.Command;
import util.Contract;

public class StdEditor implements Editor
{
    private History<Command> history;
    private int nbOfPossibleUndo;
    private int nbOfPossibleRedo;
    private Text text;

    public StdEditor()
    {
        this.history = new StdHistory<Command>(Editor.DEFAULT_HISTORY_SIZE);
        this.nbOfPossibleRedo = 0;
        this.nbOfPossibleUndo = 0;
    }

    public StdEditor(int historySize)
    {
        this.nbOfPossibleRedo = 0;
        this.nbOfPossibleUndo = 0;
        this.history = new StdHistory<Command>(historySize);
        this.text = new StdText();
    }
    @Override
    public int getTextLinesNb()
    {
        return text.getLinesNb();
    }

    @Override
    public String getTextContent()
    {
        return text.getContent();
    }

    @Override
    public int getHistorySize()
    {
        return this.history.getMaxHeight();
    }

    @Override
    public int nbOfPossibleUndo()
    {
        return this.nbOfPossibleUndo;
    }

    @Override
    public int nbOfPossibleRedo()
    {
        return this.nbOfPossibleRedo;
    }

    @Override
    public void clear()
    {
        Clear c = new Clear(this.text);
        c.act();
        this.history.add(c);
    }

    @Override
    public void insertLine(int numLine, String s)
    {
        Contract.checkCondition(s != null, "You can't add a null line");
        Contract.checkCondition( 1 <= numLine, "You can't add a " +
                "line out of the text");
        Contract.checkCondition(numLine <= this.getTextLinesNb() + 1);
        this.text.insertLine(numLine, s);
    }

    @Override
    public void deleteLine(int numLine)
    {
        Contract.checkCondition( 1 <= numLine && numLine <= getTextLinesNb()
        ,"You can't delet a line that doesn't exist");
        this.text.deleteLine(numLine);
    }

    @Override
    public void redo()
    {
        Contract.checkCondition(this.nbOfPossibleRedo > 0, "Their is nothing to redo");
        this.history.goForward();
        this.history.getCurrentElement().act();
        this.nbOfPossibleUndo++;
        this.nbOfPossibleRedo--;
    }

    @Override
    public void undo()
    {
        Contract.checkCondition(this.nbOfPossibleUndo > 0, "Their is nothing to undo");
        this.history.getCurrentElement().act();
        this.history.goBackward();
        this.nbOfPossibleUndo--;
        this.nbOfPossibleRedo++;
    }
}
