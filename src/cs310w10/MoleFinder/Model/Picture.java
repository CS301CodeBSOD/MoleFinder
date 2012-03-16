package cs310w10.MoleFinder.Model;

import java.util.ArrayList;
import java.util.Date;

import cs310w10.MoleFinder.View.ViewActivity;


import android.net.Uri;

/**
 * This class is used to represent a picture. 
 * 
 * @author Bing Pan
 *
 */
public class Picture extends MoleModel<ViewActivity> {
	/**
	 * The picture's unique id which will be assigned by the database.
	 */
	private int id;
	/**
	 * A text description of the picture.
	 */
	private String description;
	/**
	 * The date when the picture was taken.
	 */
	private Date date;
	/**
	 * A list of moles with which the picture is associated with.
	 */
	private ArrayList<Mole> moleID;
	/**
	 * The picture file's URI.
	 */
	private Uri imageData;

	/**
	 * Return the picture's id as an integer.
	 * @return the picture's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the picture's id to the integer provided.
	 * @param the picture's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the picture's description.
	 * @return the picture's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the picture's description to the description provided.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return the picture's date as a Date object.
	 * @return the picture's date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Set the picture's date to the date provided.
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Return a list of moles with which the picture is associated with.
	 * @return the list of moles
	 */
	public ArrayList<Mole> getMoleID() {
		return moleID;
	}

	/**
	 * Set the list of moles the picture is associated with to the list provided.
	 * @param an ArrayList of mole objects
	 */
	public void setMoleID(ArrayList<Mole> moleID) {
		this.moleID = moleID;
	}

	/**
	 * Return the picture's URI.
	 * @return the picture's URI
	 */
	public Uri getImageData() {
		return imageData;
	}

	/**
	 * Set the picture's URI to the URI provided.
	 * @param imageData
	 */
	public void setImageData(Uri imageData) {
		this.imageData = imageData;
	}

}
