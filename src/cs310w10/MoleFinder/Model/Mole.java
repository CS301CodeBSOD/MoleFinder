package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import android.graphics.Picture;

public class Mole {
	private int id;
	private String name;
	private String description;
	private ArrayList<Picture> PhotoId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Picture> getPhotoId() {
		return PhotoId;
	}

	public void setPhotoId(ArrayList<Picture> photoId) {
		PhotoId = photoId;
	}
}
