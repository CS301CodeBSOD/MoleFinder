package cs310w10.MoleFinder.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import cs310w10.MoleFinder.Model.ListMole;
import cs310w10.MoleFinder.Model.ListPicture;
import cs310w10.MoleFinder.Model.Picture;
import cs310w10.MoleFinder.Model.TableMolesPictures;
import cs310w10.MoleFinder.Model.TablePictures;

public class PictureController {
	private Picture picture;
	private SQLiteDatabase database;

	public PictureController(Picture picture, SQLiteDatabase database) {
		this.picture = picture;
		this.database = database;
	}

	public PictureController( SQLiteDatabase database) {
		this.database = database;
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
			picture.setDate(Calendar.getInstance());
			picture.setImageData(uri);
		}
	}
	
	public PictureController(int id) {
		
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
			Calendar date = picture.getDate();
			SimpleDateFormat format = new SimpleDateFormat("MM - dd - yyyy");
			return format.format(date);
		} catch (NullPointerException e) {
			return null;
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

	public static Picture getPictureFromId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void createPicture(String date, String description, String uri){
		picture = new Picture();
		ContentValues values = new ContentValues();
		values.put(TablePictures.COLUMN_DATE, date);
		values.put(TablePictures.COLUMN_DESCRIPTION, description);
		values.put(TablePictures.COLUMN_URI, uri);
		
        long rowId = database.insert(TablePictures.TABLE_PICTURES, null, values);
        Cursor cursor = database.query(TablePictures.TABLE_PICTURES, TablePictures.ALLCOLUMNS,
                TablePictures.COLUMN_ID + " = " + rowId, null, null, null, null);
        if (cursor.moveToFirst()){
            picture = cursorToPicture(cursor);
        }
	}
    
	/**
	 * Delete a picture from the database
	 * @param picture
	 */
	public void deletePicture(){
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES, 
				TablePictures.COLUMN_ID + " = " + picid, null);
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
        Uri imageData = Uri.parse(cursor.getString(3));
        picture.setImageData(imageData);

        return picture;
	} 

	/**
	 * Associate a picture with a mole and insert that relationship into the database.
	 * @param the id of the mole
	 * @param the id of the picture
	 */
	public void insertMolePicturePair ( int moleID, int PhotoID){
		ContentValues values = new ContentValues();
		values.put(TableMolesPictures.COLUMN_MOLEID, moleID);
		values.put(TableMolesPictures.COLUMN_PICTUREID, PhotoID);

		database.insert(TableMolesPictures.TABLE_MOLESPICTURES, null, values);

	}


	/**
	 * Retrieve a list of picture related to the given mole.
	 * @param the id of the mole
	 * @return a list of pictures
	 */
	public ListPicture getListPictureFromMole ( int moleID ){
		ListPicture pictures = new ListPicture();
		String [] columns = { TableMolesPictures.COLUMN_PICTUREID };
		Cursor cursor = database.query(TableMolesPictures.TABLE_MOLESPICTURES, columns,
				TableMolesPictures.COLUMN_PICTUREID + " = " + moleID, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Picture picture = new Picture();
			int PhotoID = cursor.getInt(0);
			picture.setId(PhotoID);
			//a temporal cursor to get the 
			Cursor tempcursor = database.query(TablePictures.TABLE_PICTURES, TablePictures.ALLCOLUMNS,
					"where " + TablePictures.COLUMN_ID + " = " + PhotoID, null, null, null, null);
			tempcursor.moveToFirst();
			picture.setDescription(tempcursor.getString(1));
			Long time = (long) tempcursor.getInt(2);
	        Calendar date = Calendar.getInstance();
	        date.setTimeInMillis(time);
	        picture.setDate(date);
			picture.setImageData(Uri.parse(tempcursor.getString(3)));

		}
		return pictures;
	}

}
