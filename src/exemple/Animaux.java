package exemple;

public abstract class Animaux
{
    abstract String name();

    void getDetail(Animaux o)
    {
        System.out.println("m " + this.name() + " (" +  o.getClass().getName() + ")" + o.name());
    }

    void getDetail(Chat o)
    {
        System.out.println("m " + this.name() + " ( Chat )" + o.name());
    }
    void getDetail(Felin o)
    {
        System.out.println("m " + this.name() + " (Felin)" + o.name());
    }

    void getDetail(ChatBlanc o)
    {
        System.out.println("m " + this.name() + " (ChatBlanc)" + o.name());
    }

}
