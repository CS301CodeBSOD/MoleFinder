package cs310w10.MoleFinder.View;

import java.util.ArrayList;
import java.util.HashMap;

import cs310w10.MoleFinder.Model.Mole;

public class MoleMapListAdapter extends ArrayList<HashMap<String, String>> {
	private static final long serialVersionUID = 1L;

	public MoleMapListAdapter(ArrayList<Mole> moles) {
		for (Mole mole : moles) {
			add(makeMap(mole));
		}
	}

	public HashMap<String, String> makeMap(Mole mole) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", mole.getName());
		map.put("description", mole.getDescription());
		map.put("id", Long.toString(mole.getId()));
		map.put("location", mole.getLocation());
		return map;
	}

}
