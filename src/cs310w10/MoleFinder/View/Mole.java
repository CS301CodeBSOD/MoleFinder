package cs310w10.MoleFinder.View;

import java.util.ArrayList;


import android.graphics.Picture;
/**
 * @author James Helberg 
 *
 * The Mole class contains data pertaining to each individual mole.
 * Within each mole will be the id that is used in the database; the
 * name, description, and location pulled from the database; and a
 * array of picture ids
 * 
 */
public class Mole extends MoleModel<ViewActivity>{
	/**
	 * The variables within the Mole class
	 * id          - Mole id within the database
	 * name        - name of this mole as defined by the user
	 * description - details defining the mole that will be shown visually with picture objects
	 * location    - location of the mole as described by the user
	 * photoId     - A list of photoId from another database for reference to this mole
	 */
	private int id;
	private String name;
	private String description;
	private String location;
	private ArrayList<Integer> photoId;

	/**
	 * @param n_id
	 * Creation method for mole
	 * allows user to set the id (useful for when pulling from database)
	 */
	public Mole(int n_id) {
		id = n_id;
		photoId = new ArrayList<Integer>();
		name = "";
		description = "";
		location = "";
	}

	/**
	 * Default creation for the moles
	 * defaults the id to be -1
	 */
	public Mole() {
		id = -1;
		photoId = new ArrayList<Integer>();
		name = "";
		description = "";
		location = "";
	}

	/**
	 * @return id
	 * returns the id of the mole
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 * set the id of the mole (should be reserved for a mole factory/database setup function)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return name
	 * return the name of the mole
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * set the name of the mole
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return description
	 * The description of the mole that the user provides
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 * set the description of the mole
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return ArrayList<Integer> PhotoId
	 * returns the list of PhotoIds associated with the mole
	 */
	public ArrayList<Integer> getPhotoId() {
		return photoId;
	}

	/**
	 * @Param ArrayList<Integer> PhotoId
	 * Allows the user to set the photoId's associated with the mole
	 */
	public void setPhotoId(ArrayList<Integer> photoId) {
		photoId = photoId;
	}

	/**
	 * @param location
	 * the location of the mole on the body as described by the user
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return location
	 * returns the location that the mole is located (as previously described by the user)
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
