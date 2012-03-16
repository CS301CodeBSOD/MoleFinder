package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.ViewActivity;


/**
 * This class is used to represent a list of pictures.
 * @author Bing Pan
 *
 */
public class ListPicture extends MoleModel<ViewActivity>{

	/**
	 * The list of pictures stored as an ArrayList of Picture objects.
	 */
	private ArrayList<Picture> pictures;

	/**
	 * Returns the list of pictures.
	 * @return an ArrayList of Picture objects
	 */
	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	/**
	 * Set the list of pictures to the list provided.
	 * @param a list of pictures
	 */
	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * Add a picture to the list.
	 * @param a picture
	 */
	public void add(Picture picutre) {
		pictures.add(picutre);
		
	}
}
