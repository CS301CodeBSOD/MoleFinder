package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;

import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.MolesDataSource;
/**
 * @author James
 * 
 * List mole controller uses the MolesDataSource in order to pull moles out of the 
 * database and pass them to the associated views. It also allows for easier storage
 * and deletion of moles from the database
 */
public class ListMoleController {
	/**
	 * The only variable stored is a list of all the moles from the database
	 */
	private ArrayList<Mole> sMoles;

	/**
	 * get moles will pull all the moles from the database (reloading each call)
	 * @return MoleList - an ArrayList of moles
	 */
	public ArrayList<Mole> getMoles() {
		if (sMoles == null) {
			sMoles = new ArrayList<Mole>();
		}

		MolesDataSource source = new MolesDataSource(
				MoleFinderApplication.getAppContext());
		source.open();
		sMoles = source.getAllMoles();
		source.close();
		return sMoles;
	}

	/**
	 * stores a new mole into the database and returns the id for the mole to be assigned
	 * @param name - of the mole
	 * @param description - of the mole
	 * @param location - of the mole
	 * @return id - the new idea for the added mole in the database
	 */
	public int addMole(String name, String description, String location) {
		ArrayList<Mole> moles = getMoles();

		// Mole newmole = MoleController.newMole(name, description, location);
		Mole newmole = new Mole();
		newmole.setName(name);
		newmole.setDescription(description);
		newmole.setLocation(location);

		moles.add(newmole);

		return newmole.getId();
	}

	/**
	 * returns the index of the mole within the local array list of moles
	 * @param mole - to be found
	 * @return index - in the local arrayList
	 */
	public int getIndexFromMole(Mole mole) {
		ArrayList<Mole> moles = getMoles();
		int id = moles.indexOf(mole);
		return id;
	}

	/**
	 * The function will be used should the user wish to purge their database
	 * of all the moles they have set up.
	 */
	public static void deleteAllMoles() {
		MolesDataSource source = new MolesDataSource(
				MoleFinderApplication.getAppContext());
		source.open();
		source.deleteAllMoles();
		source.close();

	}
}
