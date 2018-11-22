package serie06;

public enum CoinTypes
{
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    ONE_HUNDRED(100),
    TWO_HUNDRED(200);

    private final int value;

    CoinTypes(int value)
    {
        this.value = value;
    }

    public int getFaceValue(){
        return this.value;
    }

    public String toString(){
        return this.value +(this.value > 1  ? " cts" : " ct");
    }

    public static CoinTypes getCoinType(int i)
    {
        for (CoinTypes c : CoinTypes.values())
        {
            if (c.getFaceValue() == i)
            {
                return c;
            }
        }

        return null;
    }
}
