package serie05;

import serie04.Civ;
import serie04.Contact;
import serie04.StdContact;
import serie04.StdPhoneBook;
import util.Contract;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;

public class StdPersistentPhoneBook  extends StdPhoneBook implements PersistentPhoneBook
{

    private File file;

    public StdPersistentPhoneBook(File file)
    {
        super();
        this.file = file;
    }

    public StdPersistentPhoneBook()
    {
        super();
    }

    @Override
    public File getFile()
    {
        return this.file;
    }

    @Override
    public void setFile(File file)
    {
        Contract.checkCondition(this.file != null, "Null file");
        this.file = file;

    }

    @Override
    public void load() throws IOException, BadSyntaxException
    {
        Contract.checkCondition(file != null, "null file");
        this.clear();
        BufferedReader buff = null;
        Contract.checkCondition(this.file != null);
        String[] info;
        Civ civ;
        String prenom;
        String nom;
        ArrayList<String> nums = new ArrayList<String>();

        Matcher matcher;
        try
        {
            buff = new BufferedReader(new FileReader(this.file));
            String line;
            while((line = buff.readLine()) != null)
            {
                matcher = PersistentPhoneBook.LINE_RECOGNIZER.matcher(line);
                if(! matcher.matches())
                    throw new BadSyntaxException();
                info = line.split(":");
                civ = Civ.values()[Integer.parseInt(info[0])];
                prenom = info[2];
                nom = info[1];
                Collections.addAll(nums, info[3].split(","));

                StdContact tempCont = new StdContact(civ, nom, prenom);
                this.addEntry(tempCont, nums);
            }
        }
        catch(Exception e)
        {
            buff.close();
            this.clear();
            throw e;
        }
        finally
        {
            buff.close();
        }
    }

    /**<li>Une ligne est constituée, dans cet ordre,
 *       <ol>
 *          <li>d'un entier entre 0 et le nombre de civilités moins une
        *              suivi du caractère ':'</li>
        *          <li>d'un nom suivi du caractère ':'</li>
        *          <li>d'un prénom suivi du caractère ':'</li>
        *          <li>d'un numéro de téléphone</li>
        *          <li>[d'une virgule suivie d'un numéro de téléphone]*</li>
        *       </ol>
        *    </li>*/
    @Override
    public void save() throws IOException
    {
        Contract.checkCondition(file != null, "null file");
        BufferedWriter out = null;

        try
        {
            out = new BufferedWriter(new FileWriter("phonebook.txt"));
            NavigableSet<Contact> contacts = this.contacts();
            for(Contact c : contacts)
            {
                String write = c.getCivility().ordinal() + ":";
                write += c.getLastName() + ":";
                write += c.getFirstName() + ":";
                for(String s : this.phoneNumbers(c))
                {
                    write += s + ",";
                }

                write += System.getProperty(System.lineSeparator());

                out.write(write);

                write = "";
            }
        }
        catch(Exception e)
        {
            out.close();
            throw e;

        }
        finally
        {
            out.close();
        }
    }
}
