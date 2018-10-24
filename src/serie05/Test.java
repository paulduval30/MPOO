package serie05;

import serie04.Civ;
import serie04.StdContact;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] argv) throws IOException, BadSyntaxException
    {
        StdPersistentPhoneBook pb = new StdPersistentPhoneBook(new File("test.txt"));
        StdContact c = new StdContact(Civ.MR, "Bricot", "Judas");
        List<String> nums = new ArrayList<>();
        nums.add("06.07.88.01.00");
        nums.add("06.98.74.81.00");
        pb.addEntry(c, nums);
        nums.clear();
        nums.add("06.06.06.06.06");
        nums.add("07.07.07.07.07");
        c = new StdContact(Civ.MRS,"Judas", "Sticot");
        pb.addEntry(c, nums);
        try
        {
            pb.save();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        pb = new StdPersistentPhoneBook(new File("test.txt"));
        pb.load();
        pb.setFile(new File("test2.txt"));
        pb.save();

    }
}
