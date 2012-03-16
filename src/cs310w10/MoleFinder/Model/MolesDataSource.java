package cs310w10.MoleFinder.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cs310w10.MoleFinder.Controller.PictureController;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MolesDataSource {
	private SQLiteDatabase database;
	private MoleSQLiteHelper moledbhelper;

	public MolesDataSource( Context context ){
		moledbhelper = new MoleSQLiteHelper(context);
	}
	
	//open the database
	public void open (){
		database = moledbhelper.getWritableDatabase();
	}
	
	//close the database
	public void close(){
		moledbhelper.close();
	}
	
	//insert a mole into the database
	public Mole createMole(String name, String description, String location){
		ContentValues values = new ContentValues();
		values.put(TableMoles.COLUMN_NAME, name);
		values.put(TableMoles.COLUMN_LOCATION, location);
		values.put(TableMoles.COLUMN_DESCRIPTION, description);
		
		long rowId = database.insert(TableMoles.TABLE_MOLES, null, values);
		Cursor cursor = database.query(TableMoles.TABLE_MOLES, TableMoles.ALLCOLUMNS,
							TableMoles.COLUMN_ID + " = " + rowId, null, null, null, null);
		
		cursor.moveToFirst();
		Mole mole = cursorToMole(cursor);
		return mole;
	}
	//delete a mole
	public void deleteMole(Mole mole){
		long moleid = mole.getId();
		database.delete(TableMoles.TABLE_MOLES, 
				TableMoles.COLUMN_ID + " = " + moleid, null);
	}
	
	//
	public ListMole getAllMoles(){
		ListMole allmoles = new ListMole();
		Cursor cursor = database.query(TableMoles.TABLE_MOLES, TableMoles.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
				Mole mole = cursorToMole(cursor);
				allmoles.add(mole);
				cursor.moveToNext();
		}
		cursor.close();
		return null;
		
	}

	private Mole cursorToMole(Cursor cursor) {
		Mole mole = new Mole();
		int moleId = cursor.getInt(0);
		mole.setId(moleId);
		mole.setName(cursor.getString(1));
		mole.setDescription(cursor.getString(2));
		mole.setLocation(cursor.getString(3));
		ArrayList<Integer> photoIds = this.getPhotoIdsFromeMole(moleId);
		mole.setPhotoId(photoIds);
		
		return mole;
	}
	
	//insert a picture into the database
	public void insertPicture(Picture picture) {
		ContentValues values = new ContentValues();
		PictureController picturecontroller = new PictureController(picture);
		values.put(TablePictures.COLUMN_ID, picture.getId());
		values.put(TablePictures.COLUMN_DATE, picturecontroller.getDateAsString());
		values.put(TablePictures.COLUMN_DESCRIPTION, picture.getDescription());
		values.put(TablePictures.COLUMN_URI, picturecontroller.getUriAsString());
		
		database.insert(TablePictures.TABLE_PICTURES, null, values);
	}
	
	//delete a picture
	public void deletePicture(Picture picture){
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES, 
				TablePictures.COLUMN_ID + " = " + picid, null);
	}
	
	//retrieve all pictures from the database
	public ListMole getAllPictures(){
		ListPicture allpictures = new ListPicture();
		Cursor cursor = database.query(TablePictures.TABLE_PICTURES, TablePictures.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Picture picutre = cursorToPicture(cursor);
			allpictures.add(picutre);
			cursor.moveToNext();
		}
		cursor.close();
		return null;
		
	}

	private Picture cursorToPicture(Cursor cursor) {
		// TODO need to implement this method 
		return null;
	} 
	
	public void insertMolePicturePair ( int moleID, int PhotoID){
		ContentValues values = new ContentValues();
		values.put(TableMolesPictures.COLUMN_MOLEID, moleID);
		values.put(TableMolesPictures.COLUMN_PICTUREID, PhotoID);
		
		database.insert(TableMolesPictures.TABLE_MOLESPICTURES, null, values);
		
	}
	
	public ArrayList<Integer> getPhotoIdsFromeMole (int moleId){
		ArrayList<Integer> photoids = new ArrayList<Integer>();
		String [] columns = { TableMolesPictures.COLUMN_PICTUREID };
		Cursor cursor = database.query(TableMolesPictures.TABLE_MOLESPICTURES, columns,
				TableMolesPictures.COLUMN_PICTUREID + " = " + moleId, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			int photoid = cursor.getInt(0);
			photoids.add(photoid);
			cursor.moveToNext();
		}
		cursor.close();
		return photoids; 
	}
	
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
