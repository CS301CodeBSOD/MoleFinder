package cs310w10.MoleFinder.Model;

import java.util.Date;

import cs310w10.MoleFinder.Controller.PictureController;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PictureFactory {

	private SQLiteDatabase database;
	
	public PictureFactory(SQLiteDatabase database){
		this.database = database;
	}
	
	 public void insertPicture(Picture picture) {
	        ContentValues values = new ContentValues();
	        PictureController picturecontroller = new PictureController(picture);
	        values.put(TablePictures.COLUMN_ID, picture.getId());
	        values.put(TablePictures.COLUMN_DATE, picturecontroller.getDateAsString());
	        values.put(TablePictures.COLUMN_DESCRIPTION, picture.getDescription());
	        values.put(TablePictures.COLUMN_URI, picturecontroller.getUriAsString());

	        database.insert(TablePictures.TABLE_PICTURES, null, values);
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
	        // TODO need to implement this method 
	        return null;
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
