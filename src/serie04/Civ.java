package serie04;

import java.util.ArrayList;

public enum Civ
{
    UKN("Unknow"),
    MR("M."),
    MRS("Mme."),
    MS("Mlle.");

    private String name;

    Civ(String name){
        this.name = name;
    }

    public boolean canEvolveTo(Civ candidate) {
            return this.getPossible(this).contains(candidate);
    }

    private ArrayList<Civ> getPossible(Civ c)
    {
        ArrayList<Civ> possible = new ArrayList<Civ>();
        if(c.equals(UKN)){
            possible.add(MR);
            possible.add(MS);
            possible.add(MRS);
        }

        if(c.equals(MS)){
            possible.add(MRS);
        }

        if(c.equals(MRS)){
            possible.add(MR);
        }

        return possible;
    }

}
