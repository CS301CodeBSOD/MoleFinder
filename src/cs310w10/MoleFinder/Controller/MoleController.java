package cs310w10.MoleFinder.Controller;

import cs310w10.MoleFinder.Model.Mole;


public class MoleController {
	public static Mole newMole(String name, String description, String location) {
		Mole mole = new Mole();
		mole.setName(name);
		mole.setDescription(description);
		mole.setLocation(location);
		return mole;
	}
}
