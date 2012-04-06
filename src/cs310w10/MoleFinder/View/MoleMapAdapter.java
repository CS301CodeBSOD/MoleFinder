package cs310w10.MoleFinder.View;

import java.util.HashMap;

import cs310w10.MoleFinder.Model.Mole;

public class MoleMapAdapter extends HashMap<String, String> {
	// serves as an interface between android's built-in views and our moles
	private static final long serialVersionUID = 1L;

	public MoleMapAdapter(Mole mole) {
		put("name", mole.getName());
		put("description", mole.getDescription());
		put("id", Integer.toString(mole.getId()));
		put("location", mole.getLocation());
	}
}
