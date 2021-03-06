package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.fView;

/**
 * @author James Helberg The list mole class manages all the loaded moles from
 *         the Database. It allows for an easier management of the moles created
 *         by the user
 * @deprecated
 */
@SuppressWarnings("rawtypes")
@Deprecated
public class ListMole extends fModel<fView> {
	/**
	 * this is the arrayList of moles that will have been pulled from the
	 * database
	 */
	private ArrayList<Mole> moles;

	public ListMole() {
		moles = new ArrayList<Mole>();
	}

	/**
	 * @param index
	 * @return Mole get will get the mole that is stored in the specified index
	 *         within the ArrayList. This is mostly used when grabbing moles
	 *         from a listview
	 */
	public Mole get(int index) {
		return moles.get(index);
	}

	/**
	 * @return Mole get will get the mole list for use by an adapter class
	 */
	public ArrayList<Mole> get() {
		return moles;
	}

	/**
	 * @param mole
	 *            add will add the mole that is passed with this function. Will
	 *            be used repeatedly when loading in the moles from the database
	 */
	public void add(Mole mole) {
		moles.add(mole);
	}

	/**
	 * @param moles
	 *            will add an ArrayList of moles rapidly to replace the previous
	 *            list should a previous list exist.
	 */
	public void setMoles(ArrayList<Mole> moles) {
		this.moles = moles;
	}

	/**
	 * returns the position of the mole within the listMole internal array
	 * structure
	 * 
	 * @param mole
	 *            - to be found
	 * @return index - of the mole passed
	 */
	public int indexOf(Mole mole)
	{
		return this.moles.indexOf(mole);
	}

	/**
	 * size returns the size of the array list of moles. This can also be used
	 * as count
	 * 
	 * @return size - of the array list or count of the moles present
	 */
	public int size()
	{
		return this.moles.size();

	}
}
