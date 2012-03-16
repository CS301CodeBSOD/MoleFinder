package cs310w10.MoleFinder.View;

import java.util.ArrayList;


public class ListPicture extends MoleModel<ViewActivity>{

	private ArrayList<Picture> pictures;

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}

	public void add(Picture picutre) {
		pictures.add(picutre);
		
	}
}
