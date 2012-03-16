package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;

import cs310w10.MoleFinder.Model.ListMole;
import cs310w10.MoleFinder.Model.Mole;


public class ListMoleController {
	private ListMole sMoles;

	public ListMole getMoles() {
		if (sMoles == null) {
			sMoles = new ListMole();
		}
		return sMoles;
	}

	public int addMole(String name, String description, String location) {
		ListMole moles = getMoles();

		Mole newmole = MoleController.newMole(name, description, location);

		moles.add(newmole);
		return newmole.getId();
	}
}
