package serie06;

public enum DrinkTypes
{
    COFFEE("coffee", 30),
    CHOCOLATE("chocolate", 45),
    ORANGE_JUICE("orange juice", 110);

    private final String name;
    private final int price;

    DrinkTypes(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public int getPrice(){
        return this.price;
    }

    public String toString(){
        return this.name;
    }


}
