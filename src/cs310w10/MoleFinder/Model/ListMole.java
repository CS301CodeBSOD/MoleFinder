package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.ViewActivity;

public class ListMole extends MoleModel<ViewActivity>{
	private ArrayList<Mole> moles;

	public ArrayList<Mole> getMoles() {
		return moles;
	}

	public void setMoles(ArrayList<Mole> moles) {
		this.moles = moles;
	}
}
