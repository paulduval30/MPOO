package serie07;

import com.sun.deploy.util.StringUtils;
import util.Contract;

import java.util.Observable;
import java.util.regex.Matcher;

public class StdSlotModel extends Observable implements SlotModel
{
    private int[] credits;
    private int lastPayout;
    private int moneyWon;
    private int moneyLost;
    private String result;

    public StdSlotModel(int[] credits)
    {
        Contract.checkCondition(credits != null," null crédits");
        Contract.checkCondition(credits.length >= SlotModel.MIN_RESULT_SIZE);
        for(int i : credits){
            Contract.checkCondition(i >= 0);
        }
        this.credits = credits;
        this.lastPayout = 0;
        this.moneyWon = 0;
        this.moneyLost = 0;
        this.result = "   ";
    }
    @Override
    public int credit(int n)
    {
        Contract.checkCondition(n < credits.length);
        return this.credits[n];
    }

    @Override
    public int lastPayout()
    {
        return this.lastPayout;
    }

    @Override
    public int moneyLost()
    {
        return this.moneyLost;
    }

    @Override
    public int moneyWon()
    {
        return this.moneyWon;
    }

    @Override
    public String result()
    {
        return this.result;
    }

    @Override
    public void gamble()
    {
        this.moneyLost++;
        this.lastPayout = 0;
        this.result = this.randomize();
        for(char c : result.toCharArray()){

            this.lastPayout =  (Math.max(this.credits[this.compterOccurrences(result, c) - 1], lastPayout));
        }
        this.moneyWon = moneyWon +  lastPayout;
        setChanged();
        notifyObservers();
    }

    private String randomize()
    {
        String sResult = "";
        char lettre;
        for(int i = 0; i < credits.length - 1; i++){
            lettre = (char)(65 + (int)(Math.random() * 26));
            sResult += lettre;
        }
        System.out.println(sResult);
        return sResult;
    }

    private int compterOccurrences(String maChaine, char recherche)
    {
        int nb = 0;
        for (int i=0; i < maChaine.length(); i++)
        {
            if (maChaine.charAt(i) == recherche)
                nb++;
        }
        return nb;
    }


}
