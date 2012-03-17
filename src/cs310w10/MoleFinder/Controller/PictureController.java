package cs310w10.MoleFinder.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import android.graphics.Bitmap;
import android.net.Uri;
import cs310w10.MoleFinder.Model.Picture;

public class PictureController {
	Picture picture;

	public PictureController(Picture picture) {
		this.picture = picture;
	}

	public PictureController(Bitmap imagedata) {
		int id = MoleFinderApplication.getListPictureController()
				.getNextFreeID();
		Uri uri = makeFile(imagedata, id);
		if (uri == null) {
			picture = null;
		} else {
			picture = new Picture();
			picture.setId(id);
			picture.setDate(new Date());
			picture.setImageData(uri);
		}
	}

	private Uri makeFile(Bitmap imagedata, int id) {
		String folderpath = MoleFinderApplication.getSavePath();
		String format = MoleFinderApplication.getSaveFormat();
		String filename = "pic" + id + format;

		File folder;
		File file = null;
		FileOutputStream out = null;
		try {
			folder = new File(folderpath);
			folder.mkdirs();

			file = new File(folder, filename);
			out = new FileOutputStream(file);
			imagedata.compress(Bitmap.CompressFormat.PNG, 90, out);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Uri.Builder builder = new Uri.Builder();
		builder.path(file.getPath());

		return builder.build();
	}

	private Uri makeUri(File file) {

		return null;
	}

	public String getDateAsString() {
		try {
			Date date = picture.getDate();
			return date.toGMTString();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public long getDateAslong() {
		try {
			Date date = picture.getDate();
			return date.getTime();
		} catch (NullPointerException e) {
			return -1;
		}
	}

	public String getUriAsString() {
		try {
			Uri uri = picture.getImageData();
			return uri.toString();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Picture getPicture() {
		return picture;
	}
}
