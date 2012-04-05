package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.fView;

/**
 * This class is used to represent a list of pictures.
 * 
 * @author Bing Pan
 * @deprecated
 */
@Deprecated
public class ListPicture extends fModel<fView> {

	/**
	 * The list of pictures stored as an ArrayList of Picture objects.
	 */
	private ArrayList<Picture> pictures;

	public ListPicture() {
		pictures = new ArrayList<Picture>();
	}

	/**
	 * Returns the list of pictures.
	 * 
	 * @return an ArrayList of Picture objects
	 */
	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	/**
	 * Gets a specific picture from the list of Pictures the index indicates the
	 * position in the loaded ArrayList, not the index within the database
	 * 
	 * @param index
	 * @return Picture
	 */
	public Picture getPicture(int index) {
		return pictures.get(index);
	}

	/**
	 * Set the list of pictures to the list provided.
	 * 
	 * @param a
	 *            list of pictures
	 */
	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * Add a picture to the list.
	 * 
	 * @param a
	 *            picture
	 */
	public void add(Picture picture) {
		pictures.add(picture);

	}

	/**
	 * find the picture within the listPicture array that has the same id as the
	 * one passed
	 * 
	 * @param id
	 *            - to be found
	 * @return picture - found picture with matching id
	 */
	public Picture getPictureById(int id) {
		for (Picture pictureIterator : pictures) {
			if (pictureIterator.getId() == id) {
				return pictureIterator;
			}

		}
		return null;
	}
}
