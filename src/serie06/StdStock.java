package serie06;

import util.Contract;

import java.util.HashMap;

public class StdStock<E> implements Stock<E>
{
    private HashMap<E, Integer> stocks;

    public StdStock()
    {
        this.stocks = new HashMap<E, Integer>();
    }
    @Override
    public int getNumber(E e)
    {
        Contract.checkCondition(e != null, "can't get null");
        return (stocks.get(e) != null ? stocks.get(e) : 0);
    }

    @Override
    public int getTotalNumber()
    {
        int total = 0;
        for(E key : stocks.keySet())
        {
            total += stocks.get(key);
        }

        return total;
    }

    @Override
    public void addElement(E e)
    {
        Contract.checkCondition(e != null, "can't add null");
        this.stocks.put(e, (this.stocks.containsKey(e)?this.stocks.get(e) + 1:1));
    }

    @Override
    public void addElement(E e, int qty)
    {
        Contract.checkCondition(e != null, "can't add null");
        this.stocks.put(e, (this.stocks.containsKey(e)?this.stocks.get(e) + qty:qty));
    }

    @Override
    public void removeElement(E e)
    {
        Contract.checkCondition( e != null, "can't remove null");
        Contract.checkCondition(this.stocks.containsKey(e), "can't remove null");
        Contract.checkCondition(stocks.get(e) >= 1, "can't remove if their is nothing");
        this.stocks.put(e, this.stocks.get(e) - 1);
    }

    @Override
    public void removeElement(E e, int qty)
    {
        Contract.checkCondition(e != null, "can't remove null");
        Contract.checkCondition(this.stocks.containsKey(e), "Element don't exist");
        Contract.checkCondition(stocks.get(e) >= qty, "can't remove if their is nothing");
        Contract.checkCondition(qty > 0, "can't remove negativ qty");
        stocks.put(e, stocks.get(e) - qty);
    }

    @Override
    public void reset()
    {
        for(E key : stocks.keySet())
        {
            stocks.replace(key, 0);
        }
    }
}
