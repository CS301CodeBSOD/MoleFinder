package cs310w10.MoleFinder.Controller;

import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.MolesDataSource;

public class MoleController {
    public static Mole newMole(String name, String description, String location) {
        Mole mole = new Mole();
        mole.setName(name);
        mole.setDescription(description);
        mole.setLocation(location);

        //Storing mole in the database
        MolesDataSource source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        mole = source.createMole(name, description, location);
        source.close();
        return mole;
    }

    public static Mole editMole(String name, String description, String location, long id) {
        Mole mole = MoleController.getMoleFromId(id);
        mole.setName(name);
        mole.setDescription(description);
        mole.setLocation(location);

        //Storing mole in the database
        MolesDataSource source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        mole = source.editMole(mole, name, description, location);
        source.close();
        return mole;
    }

    public static Mole getMoleFromId(long id){
        MolesDataSource source = new MolesDataSource(MoleFinderApplication.getAppContext());
        source.open();
        Mole mole = source.getMoleFromId(id);
        source.close();
        return mole;
    }


}
