package cs310w10.MoleFinder.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import cs310w10.MoleFinder.Model.MoleSQLiteHelper;
import cs310w10.MoleFinder.Model.Picture;
import cs310w10.MoleFinder.Model.TableMolesPictures;
import cs310w10.MoleFinder.Model.TablePictures;

/**
 * @author Bing
 * 
 * Picture controller uses an sqlite database connection and user provided values
 * to create and manage Picture objects for the MoleFinder Application.
 */
public class PictureController {

	private Picture picture;
	private final MoleSQLiteHelper connection;

	public PictureController(Picture picture, Context context) {
		this.picture = picture;
		this.connection = MoleSQLiteHelper.getInstance(context);
	}

	public PictureController(Context context) {
		this.connection = MoleSQLiteHelper.getInstance(context);
	}

	/*
	 * public PictureController(Bitmap imagedata, Context context) { int id =
	 * MoleFinderApplication.getListPictureController() .getNextFreeID(); Uri
	 * uri = makeFile(imagedata, id); if (uri == null) { picture = null; } else
	 * { picture = new Picture(); picture.setId(id);
	 * picture.setDate(Calendar.getInstance()); picture.setImageData(uri); }
	 * this.connection = MoleSQLiteHelper.getInstance(context); }
	 */
	//
	// public PictureController(int id) {
	//
	// }
/**
 * MakeFile reads in bitmap image data and saves it into a file.
 * 
 * @param imagedata
 * @param filename
 * @return imageUri
 */
	private Uri makeFile(Bitmap imagedata, String filename) {
		String folderpath = MoleFinderApplication.getSavePath();
		String format = MoleFinderApplication.getSaveFormat();
		filename = filename + format;

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

	/**
	 * getDateAsString takes a calendar date out of the picture data and 
	 * returns a simple coresponding date string.
	 * @return DateAsString
	 */
	public String getDateAsString() {
		try {
			Calendar date = picture.getDate();
			SimpleDateFormat format = new SimpleDateFormat("MM - dd - yyyy");
			return format.format(date.getTimeInMillis());
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Pulls the URI out of the picture data as a string
	 * 
	 * @return URIasString
	 */
	public String getUriAsString() {
		try {
			Uri uri = picture.getImageData();
			return uri.toString();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String uriToString(Uri uri) {
		return uri.toString();
	}

	public Uri uriFromString(String string) {
		return Uri.parse(string);
	}

	/**
	 * Get the Picture object the controller is controlling
	 * 
	 * @return Picture object
	 */
	public Picture getPicture() {
		return picture;
	}

	// public static Picture getPictureFromId(int id) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/**
	 * Create a new Picture
	 * 
	 * @param date
	 * @param description
	 * @param data
	 *            The picture data returned by the camera intent
	 * @returns The created picture.
	 */
	public Picture createPicture(long date, String description, Bitmap data) {
		Uri uri = makeFile(data, Long.toString(date));
		SQLiteDatabase database = connection.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TablePictures.COLUMN_DATE, date);
		values.put(TablePictures.COLUMN_DESCRIPTION, description);
		values.put(TablePictures.COLUMN_URI, uriToString(uri));

		long rowId = database
				.insert(TablePictures.TABLE_PICTURES, null, values);
		Cursor cursor = database
				.query(TablePictures.TABLE_PICTURES, TablePictures.ALLCOLUMNS,
						TablePictures.COLUMN_ID + " = " + rowId, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			picture = cursorToPicture(cursor);
		}
		cursor.close();
		connection.close();
		return picture;
	}

	/**
	 * edits a picture that has previously been set in the database
	 * 
	 * @param uri
	 * @param string
	 * @param calendar
	 *            .getTimeInMills()
	 * @param description
	 * @param uri
	 *            .getPath()
	 * @return
	 */

	public Picture editPicture(long date, String description, Uri uri) {
		SQLiteDatabase database = connection.getWritableDatabase();
		long pictureid = picture.getId();
		ContentValues values = new ContentValues();
		values.put(TablePictures.COLUMN_DATE, date);
		values.put(TablePictures.COLUMN_DESCRIPTION, description);
		values.put(TablePictures.COLUMN_URI, uriToString(uri));

		database.update(TablePictures.TABLE_PICTURES, values,
						TablePictures.COLUMN_ID + " = " + pictureid, null);

		Cursor cursor = database
				.query(TablePictures.TABLE_PICTURES,
						TablePictures.ALLCOLUMNS,
						TablePictures.COLUMN_ID + " = " + pictureid, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			picture = cursorToPicture(cursor);
		}
		cursor.close();
		connection.close();
		return picture;
	}

	/**
	 * Delete a picture from the database
	 * 
	 * @param picture
	 */
	public void deletePicture() {
		if (picture == null){
			return;
		}
		SQLiteDatabase database = connection.getWritableDatabase();
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES,
				TablePictures.COLUMN_ID + " = " + picid, null);
		connection.close();
	}

	/**
	 * @param cursor
	 * @return
	 */
	private Picture cursorToPicture(Cursor cursor) {
		Picture picture = new Picture();

		int pictureId = cursor.getInt(0);
		picture.setId(pictureId);

		picture.setDescription(cursor.getString(1));

		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(cursor.getInt(2));
		picture.setDate(date);

		Uri imageData = uriFromString(cursor.getString(3));
		picture.setImageData(imageData);

		return picture;
	}

	/**
	 * Associate a picture with a mole and insert that relationship into the
	 * database.
	 * 
	 * @param the
	 *            id of the mole
	 * @param the
	 *            id of the picture
	 */
	public void AssociatePictureWithMole(int moleID) {
		SQLiteDatabase database = connection.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TableMolesPictures.COLUMN_MOLEID, moleID);
		values.put(TableMolesPictures.COLUMN_PICTUREID, picture.getId());

		database.insert(TableMolesPictures.TABLE_MOLESPICTURES, null, values);
		connection.close();
	}

	/**
	 * Retrieve the picture with the provided ID from the database
	 * 
	 * @param id
	 */
	public Picture getPictureFromId(long id) {
		SQLiteDatabase database = connection.getWritableDatabase();
		picture = new Picture();
		Cursor cursor = database.query(TablePictures.TABLE_PICTURES,
				TablePictures.ALLCOLUMNS,
				TablePictures.COLUMN_ID + " = " + String.valueOf(id), null,
				null, null, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			picture = cursorToPicture(cursor);
		}
		cursor.close();
		connection.close();
		return picture;
	}

}
