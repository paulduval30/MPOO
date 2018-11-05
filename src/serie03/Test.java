package serie03;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class Test
{
    public static void main(String[] argv)
    {
        Editor e = new StdEditor(5);
        e.insertLine(1, "AAAAAAAAAAAAAA");
        inv(e);
        System.out.println(e.getTextContent());
        e.undo();
        inv(e);
        System.out.println(e.getTextContent());
        e.redo();
        inv(e);
        System.out.println(e.getTextContent());
        e.clear();
        inv(e);
        System.out.println(e.getTextContent());
        e.undo();
        inv(e);
        System.out.println(e.getTextContent());
        e.redo();
        inv(e);
        System.out.println(e.getTextContent());
        inv(e);
    }

    public static void inv(Editor e) {
        if(!(e.getTextLinesNb() >= 0))
            System.out.println("Line nb < 0");
        if((e.getTextContent() == null))
            System.out.println(" text null");
        if(!(e.getTextContent().equals("") && e.getTextLinesNb() == 0 )|| (e.getTextContent().equals("") && e.getTextLinesNb() != 0))
            System.out.println("blablabla");
        if(0 > e.nbOfPossibleUndo())
            System.out.println("undo < 0");
        if(0 > e.nbOfPossibleRedo())
            System.out.println("redo < 0");
        if(0 > e.getHistorySize())
            System.out.println("history < 0");
        if(e.nbOfPossibleUndo() + e.nbOfPossibleRedo() > e.getHistorySize())
            System.out.println("blablabla 2");
    }
}