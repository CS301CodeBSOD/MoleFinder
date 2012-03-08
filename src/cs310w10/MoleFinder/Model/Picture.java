package cs310w10.MoleFinder.Model;

import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;

public class Picture {
	private int id;
	private String description;
	private Date date;
	private ArrayList<Mole> moleID;
	private Uri imageData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Mole> getMoleID() {
		return moleID;
	}

	public void setMoleID(ArrayList<Mole> moleID) {
		this.moleID = moleID;
	}

	public Uri getImageData() {
		return imageData;
	}

	public void setImageData(Uri imageData) {
		this.imageData = imageData;
	}
}
