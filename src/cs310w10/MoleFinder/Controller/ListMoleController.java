package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;

import cs310w10.MoleFinder.Model.ListMole;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.MolesDataSource;


public class ListMoleController {
	private ListMole sMoles;

	public ListMole getMoles() {
		if (sMoles == null) {
			sMoles = new ListMole();
		}
		
		MolesDataSource source = new MolesDataSource(MoleFinderApplication.getAppContext());
	        source.open();
	        sMoles = source.getAllMoles();
	        source.close();
		return sMoles;
	}

	public int addMole(String name, String description, String location) {
		ListMole moles = getMoles();

		//Mole newmole = MoleController.newMole(name, description, location);
		Mole newmole = new Mole();
		newmole.setName(name);
		newmole.setDescription(description);
		newmole.setLocation(location);

		moles.add(newmole);
		
		return newmole.getId();
	}
	
	public int getIndexFromMole(Mole mole){
	        ListMole moles = getMoles();
	        int id = moles.indexOf(mole);
	    return id;
	}
        
	public static void deleteAllMoles(){
            MolesDataSource source = new MolesDataSource(MoleFinderApplication.getAppContext());
            source.open();
            source.deleteAllMoles();
            source.close();

        }
}
