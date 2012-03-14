package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import cs310w10.MoleFinder.View.ViewActivity;

import android.graphics.Picture;

public class Mole extends MoleModel<ViewActivity>{
	private static int s_id;
	private int id;
	private String name;
	private String description;
	private String location;
	private ArrayList<Picture> PhotoId;

	public Mole() {
		id = s_id;
		s_id++;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
