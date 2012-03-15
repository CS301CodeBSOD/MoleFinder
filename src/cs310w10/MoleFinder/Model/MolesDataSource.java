package cs310w10.MoleFinder.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MolesDataSource {
	private SQLiteDatabase database;
	private MoleSQLiteHelper moledbhelper;
	private String[] allcolumns = { TableMoles.COLUMN_ID, TableMoles.COLUMN_NAME,
			TableMoles.COLUMN_LOCATION, TableMoles.COLUMN_DESCRIPTION };
	
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
	public void insertMole(Mole mole){
		ContentValues values = new ContentValues();
		values.put(TableMoles.COLUMN_ID, mole.getId());
		values.put(TableMoles.COLUMN_NAME, mole.getName());
		values.put(TableMoles.COLUMN_LOCATION, mole.getLocation());
		values.put(TableMoles.COLUMN_DESCRIPTION, mole.getDescription());
		
		database.insert(TableMoles.TABLE_MOLES, null, values);
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
		Cursor cursor = database.query(TableMoles.TABLE_MOLES, allcolumns, null, null, null, null, null);
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
		// need to implement this eventually but 
		// MolesPicutures table methods needed to be implemented first
		return null;
	}
	
	/* Temporarily commented out since part of the functionality of this 
	 * class need to reallocated to the table classes
	 * will fix this issue later
	public void insertPicture(Picture picture) {
		ContentValues values = new ContentValues();
		values.put(TablePictures.COLUMN_ID, picture.getId());
		values.put(TablePictures.COLUMN_DATE, picture.getDateAsString());
		values.put(TablePictures.COLUMN_DESCRIPTION, picture.getDescription());
		values.put(TablePictures.COLUMN_URI, picture.getURI());
		
		database.insert(TablePictures.TABLE_PICTURES, null, values);
	}
	
	//delete a picture
	public void deletePicture(Picture picture){
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES, 
				TablePictures.COLUMN_ID + " = " + picid, null);
	}
	
	//
	public ListMole getAllPictures(){
		ListPicture allpictures = new ListPicture();
		Cursor cursor = database.query(TablePictures.TABLE_PICTURES, allcolumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
				Mole mole = cursorToMole(cursor);
				allmoles.add(mole);
				cursor.moveToNext();
		}
		cursor.close();
		return null;
		
	} */
}
