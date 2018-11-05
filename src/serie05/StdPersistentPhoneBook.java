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
        Contract.checkCondition(file != null, "Null file");
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
            String line = "";
            while((line = buff.readLine()) != null)
            {
                matcher = PersistentPhoneBook.LINE_RECOGNIZER.matcher(line);
                if(! matcher.matches())
                    throw new BadSyntaxException();
                info = line.split(":");
                civ = Civ.values()[Integer.parseInt(info[0].trim())];
                prenom = info[2].trim();
                nom = info[1].trim();
                Collections.addAll(nums, info[3].split(","));

                StdContact tempCont = new StdContact(civ, nom, prenom);
                this.addEntry(tempCont, nums);

                nums = new ArrayList<String>();

            }
        }
        catch(Exception e)
        {
            if(buff != null)
                buff.close();
            this.clear();
            throw e;
        }
        finally
        {
            if(buff != null)
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
            String write = "";
            out = new BufferedWriter(new FileWriter(this.file));
            NavigableSet<Contact> contacts = this.contacts();
            for(Contact c : contacts)
            {
                write += c.getCivility().ordinal() + ":";
                write += c.getLastName() + ":";
                write += c.getFirstName() + ":";
                if(this.phoneNumbers(c).size() > 1)
                    write += this.phoneNumbers(c).get(0);
                for(int i = 1; i < this.phoneNumbers(c).size(); i++)
                    write += "," + this.phoneNumbers(c).get(i);

                write += "\n";
            }

            out.write(write);
        }
        catch(Exception e)
        {
            if(out != null)
                out.close();
            throw e;

        }
        finally
        {
            if(out != null)
              out.close();
        }
    }
}
