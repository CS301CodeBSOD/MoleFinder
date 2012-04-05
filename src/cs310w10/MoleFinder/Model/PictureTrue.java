package cs310w10.MoleFinder.Model;

import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;
import cs310w10.MoleFinder.View.fView;

/**
 * @author James Helberg Picture is a class designed to hold an image and the
 *         moles that it is associated with. the image will be loaded here for
 *         ease of access for display when a mole is referenced.
 */
@SuppressWarnings("rawtypes")
public class PictureTrue extends fModel<fView> {
	/**
	 * Variables within the Picture class id - the id associated with the
	 * picture data in the database description - description of the image date
	 * - date and time the image was taken moleID - the list of moles it is
	 * associated with imageData - the image that was taken with the camera
	 * pre-loaded
	 */
	private int id;
	private String description;
	private Date date;
	private ArrayList<Integer> moleID;
	private Uri imageData;

	/**
	 * constructor for the picture with default id as -1
	 */
	public PictureTrue() {
		id = -1;
		description = "";
		date = new Date();
		moleID = new ArrayList<Integer>();
		imageData = null;
	}

	/**
	 * constructor for the picture with single parameter for setting id
	 * 
	 * @param id
	 */
	public PictureTrue(int n_id) {
		id = n_id;
		description = "";
		date = new Date();
		moleID = new ArrayList<Integer>();
		imageData = null;
	}

	/**
	 * returns the Picture id associated with the database
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the picture id from the data base
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * returns the description of the image
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the description of the picture
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * returns the date that the picture was taken
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * sets the date in which the picture was taken
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * returns the list of mole ids that are associated with this picture. I a
	 * majority of the cases it will be a single digit, though in cases where in
	 * several moles are close enough together to fit in one image, it may be
	 * necessary to have them be recorded in the same image
	 * 
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getMoleID() {
		return moleID;
	}

	/**
	 * can set all the moleIDs directly
	 * 
	 * @param moleID
	 */
	public void setMoleID(ArrayList<Integer> moleID) {
		this.moleID = moleID;
	}

	/**
	 * Add an id to the mole list
	 * 
	 * @param id
	 */
	public void addMoleID(Integer id) {
		this.moleID.add(id);
	}

	/**
	 * Returns the Uri image that is stored in the picture class
	 * 
	 * @return imageData
	 */
	public Uri getImageData() {
		return imageData;
	}

	/**
	 * set the image data for the picture object
	 * 
	 * @param imageData
	 */
	public void setImageData(Uri imageData) {
		this.imageData = imageData;
	}
}
