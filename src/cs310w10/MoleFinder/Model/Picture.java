package cs310w10.MoleFinder.Model;

import java.util.Calendar;

import android.net.Uri;
import cs310w10.MoleFinder.View.fView;

/**
 * This class is used to represent a picture.
 * 
 * @author Bing Pan
 */
@SuppressWarnings("rawtypes")
public class Picture extends fModel<fView> {
	/**
	 * Variables within the Picture class id - the id associated with the
	 * picture data in the database description - description of the image date
	 * - date and time the image was taken moleID - the list of moles it is
	 * associated with imageData - the image that was taken with the camera
	 * pre-loaded
	 */
	private int id;
	private String description;
	private Calendar date;
	private Uri imageData;

	/**
	 * Return the picture's id as an integer.
	 * 
	 * @return the picture's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the picture's id to the integer provided.
	 * 
	 * @param the
	 *            picture's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the picture's description.
	 * 
	 * @return the picture's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the picture's description to the description provided.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return the picture's date as a Date object.
	 * 
	 * @return the picture's date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Set the picture's date to the date provided.
	 * 
	 * @param date
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * Return the picture's URI.
	 * 
	 * @return the picture's URI
	 */
	public Uri getImageData() {
		return imageData;
	}

	/**
	 * Set the picture's URI to the URI provided.
	 * 
	 * @param imageData
	 */
	public void setImageData(Uri imageData) {
		this.imageData = imageData;
	}

}
