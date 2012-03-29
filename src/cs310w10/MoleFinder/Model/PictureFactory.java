package cs310w10.MoleFinder.Model;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PictureFactory {

	private SQLiteDatabase database;
	
	public PictureFactory(SQLiteDatabase database){
		this.database = database;
	}
	
	public Picture createPicture(String date, String description, String uri){
		Picture picture = new Picture();
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
        return picture;
	}
    
	/**
	 * Delete a picture from the database
	 * @param picture
	 */
	public void deletePicture(Picture picture){
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES, 
				TablePictures.COLUMN_ID + " = " + picid, null);
	}

	/**
	 * Retrieve all the pictures from the database
	 * @return a list of pictures
	 */
	public ListMole getAllPictures(){
		ListPicture allpictures = new ListPicture();
		Cursor cursor = database.query(TablePictures.TABLE_PICTURES, TablePictures.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Picture picture = cursorToPicture(cursor);
			allpictures.add(picture);
			cursor.moveToNext();
		}
		cursor.close();
		return null;

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
        Date date = new Date();
        date.setTime(cursor.getInt(2));
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
			Date date = new Date();
			date.setTime(time);
			picture.setDate(date);
			picture.setImageData(Uri.parse(tempcursor.getString(3)));

		}
		return pictures;
	}

}
