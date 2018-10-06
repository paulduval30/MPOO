package exemple;

public class Main
{
    public static void main(String[] argv)
    {
        // B hérite de A
        // A x = new B();
        // B y = new B();
        Chien shiba = new Shiba();
        Shiba shibaPur = new Shiba();

        //E herite de D qui herite de C
        // C u = new D();
        // D v = new D();
        // E w = new E();
        Felin chat = new Chat();
        Chat chatPur = new Chat();
        ChatBlanc chatBlanc = new ChatBlanc();

        shiba.getDetail(chat);
        shiba.getDetail(chatPur);
        shiba.getDetail(chatBlanc);

        shibaPur.getDetail(chat);
        shibaPur.getDetail(chatPur);
        shibaPur.getDetail(chatBlanc);






    }
}
