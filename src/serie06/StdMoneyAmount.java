package serie06;

import util.Contract;

public class StdMoneyAmount extends StdStock<CoinTypes> implements MoneyAmount {

    public StdMoneyAmount() {
          super();
    }

    @Override
    public int getValue(CoinTypes c)
    {
        Contract.checkCondition(c != null, "c ne doit pas être null");
        return c.getFaceValue()*this.getNumber(c);
    }

    @Override
    public int getTotalValue()
    {
        int somme = 0;
        for (CoinTypes c : CoinTypes.values())
            somme += c.getFaceValue()*this.getNumber(c);
        return somme;
    }

    @Override
    public void addAmount(MoneyAmount amount)
    {

    }

    @Override
    public MoneyAmount computeChange(int s)
    {
        return null;
    }

    @Override
    public void removeAmount(MoneyAmount amount)
    {

    }
}
