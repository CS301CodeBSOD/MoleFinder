package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.ViewActivity;

public class ListPicture extends MoleModel<ViewActivity>{

	private ArrayList<Picture> pictures;

	public ArrayList<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Picture> pictures) {
		this.pictures = pictures;
	}
}
