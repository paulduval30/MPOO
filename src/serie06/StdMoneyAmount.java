package serie06;

import util.Contract;

import java.util.HashMap;

public class StdMoneyAmount extends StdStock<CoinTypes> implements MoneyAmount{

    private HashMap<CoinTypes, Integer> coins;

    public StdMoneyAmount()
    {
        super();
        for(CoinTypes c : CoinTypes.values())
        {
            this.addElement(c, 0);
        }
    }


    @Override
    public int getValue(CoinTypes c)
    {
        Contract.checkCondition(c != null, "can't get null value");
        Contract.checkCondition(this.coins.containsKey(c));
        return c.getFaceValue() * this.getNumber(c);

    }

    @Override
    public int getTotalValue()
    {
        int total = 0;
        for(CoinTypes c : CoinTypes.values())
        {
            total += this.getValue(c);
        }

        return total;
    }

    @Override
    public void addAmount(MoneyAmount amount)
    {
        Contract.checkCondition(amount != null , "can't add null");
        for(CoinTypes c : CoinTypes.values())
        {
            this.addElement(c, amount.getNumber(c));
        }
    }

    @Override
    public MoneyAmount computeChange(int s)
    {
        return null;
    }

    @Override
    public void removeAmount(MoneyAmount amount)
    {
        Contract.checkCondition(amount != null, "can't remove null");
        for(CoinTypes c : CoinTypes.values())
        {
            this.removeElement(c, amount.getNumber(c));
        }
    }
}
