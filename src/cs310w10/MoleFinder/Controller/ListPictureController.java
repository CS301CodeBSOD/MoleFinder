package cs310w10.MoleFinder.Controller;

import cs310w10.MoleFinder.Model.ListPicture;
import cs310w10.MoleFinder.Model.Picture;

public class ListPictureController {
	private final ListPicture pictures;

	public ListPictureController(ListPicture pictures) {
		this.pictures = pictures;
	}

	public void insertPicture(PictureController picture) {
		pictures.add(picture.getPicture());
	}

	public int getNextFreeID() {
		// TODO: find out the next free id from the database
		return 10;
	}

	public Picture getPictureById(int id) {
		return pictures.getPictureById(id);
	}
}
