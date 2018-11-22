package serie06;

import util.Contract;

public class StdDrinksMachineModel implements DrinksMachineModel
{
    private StdStock<DrinkTypes> drinks;
    private StdMoneyAmount amounts;

    private StdMoneyAmount credit;
    @Override
    public int getDrinkNb(DrinkTypes d)
    {
        Contract.checkCondition(d != null, "can't get null");
        return drinks.getNumber(d);
    }

    @Override
    public DrinkTypes getLastDrink()
    {
        return null;
    }

    @Override
    public int getCreditAmount()
    {
        return credit.getTotalValue();
    }

    @Override
    public int getCreditNb(CoinTypes c)
    {
        return credit.getValue(c);
    }

    @Override
    public int getCashAmount()
    {
        return 0;
    }

    @Override
    public int getCashNb(CoinTypes c)
    {
        return 0;
    }

    @Override
    public int getChangeAmount()
    {
        return 0;
    }

    @Override
    public int getChangeNb(CoinTypes c)
    {
        return 0;
    }

    @Override
    public boolean canGetChange()
    {
        return false;
    }

    @Override
    public void selectDrink(DrinkTypes d)
    {

    }

    @Override
    public void fillStock(DrinkTypes d, int q)
    {

    }

    @Override
    public void fillCash(CoinTypes c, int q)
    {

    }

    @Override
    public void insertCoin(CoinTypes c)
    {

    }

    @Override
    public void cancelCredit()
    {

    }

    @Override
    public void takeDrink()
    {

    }

    @Override
    public void takeChange()
    {

    }

    @Override
    public void reset()
    {

    }
}
