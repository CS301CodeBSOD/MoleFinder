package cs310w10.MoleFinder.Model;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Controller.PictureController;

/**
 * The class is used to store and to retrieve model class objects to and from
 * the SQLite database provided by android.
 * 
 * @author Bing Pan
 */
public class MolesDataSource {
	/**
	 * These are the private variables associated with the database
	 */
	private SQLiteDatabase database;
	private final MoleSQLiteHelper moledbhelper;

	/**
	 * Create a MoleDataSource Object.
	 * 
	 * @param current
	 *            context
	 */
	public MolesDataSource(Context context) {
		moledbhelper = new MoleSQLiteHelper(context);
	}

	/**
	 * Open the database.
	 */
	public void open() {
		database = moledbhelper.getWritableDatabase();
	}

	// close the database
	/**
	 * Close the database.
	 */
	public void close() {
		moledbhelper.close();
	}

	// insert a mole into the database
	/**
	 * Create a Mole object and set its attribute to the values supplied. Id is
	 * assigned by the database.
	 * 
	 * @param the
	 *            name of the mole
	 * @param the
	 *            description of the mole
	 * @param the
	 *            location of the mole
	 * @return A Mole object
	 */
	public Mole createMole(String name, String description, String location) {
		Mole mole = new Mole();
		ContentValues values = new ContentValues();
		values.put(TableMoles.COLUMN_NAME, name);
		values.put(TableMoles.COLUMN_LOCATION, location);
		values.put(TableMoles.COLUMN_DESCRIPTION, description);

		long rowId = database.insert(TableMoles.TABLE_MOLES, null, values);
		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS,
				TableMoles.COLUMN_ID + " = " + rowId, null, null, null, null);
		if (cursor.moveToFirst()) {
			mole = cursorToMole(cursor);
		}
		return mole;
	}

	// delete a mole
	/**
	 * Delete a mole from the database.
	 * 
	 * @param the
	 *            Mole object
	 */
	public void deleteMole(Mole mole) {
		long moleid = mole.getId();
		database.delete(TableMoles.TABLE_MOLES,
				TableMoles.COLUMN_ID + " = " + moleid, null);
	}

	/**
	 * edits a mole that has previously been set in the database
	 * 
	 * @param mole
	 * @param name
	 * @param description
	 * @param location
	 * @return
	 */
	public Mole editMole(Mole mole, String name, String description,
			String location) {
		long moleid = mole.getId();
		ContentValues values = new ContentValues();
		values.put(TableMoles.COLUMN_NAME, name);
		values.put(TableMoles.COLUMN_LOCATION, location);
		values.put(TableMoles.COLUMN_DESCRIPTION, description);
		long rowId = database.update(TableMoles.TABLE_MOLES, values,
				TableMoles.COLUMN_ID + " = " + moleid, null);

		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS,
				TableMoles.COLUMN_ID + " = " + rowId, null, null, null, null);
		if (cursor.moveToFirst()) {
			mole = cursorToMole(cursor);
		}
		return mole;
	}

	//
	/**
	 * Retrieve all moles from the database as a ListMole object.
	 * 
	 * @return a ListMole containing all moles
	 */
	public ListMole getAllMoles() {
		ListMole allmoles = new ListMole();
		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Mole mole = cursorToMole(cursor);
			allmoles.add(mole);
			cursor.moveToNext();
		}
		cursor.close();
		return allmoles;

	}

	/**
	 * Delete all moles from the database
	 */
	public void deleteAllMoles() {
		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			// Find a way to do this that resets the id field, maybe
			Mole mole = cursorToMole(cursor);
			deleteMole(mole);
			cursor.moveToNext();
		}
		cursor.close();

	}

	/**
	 * Process all the information from the row pointed by the cursor supplied
	 * and package the information into a mole object.
	 * 
	 * @param cursor
	 * @return a Mole object
	 */
	private Mole cursorToMole(Cursor cursor) {
		Mole mole = new Mole();
		int moleId = cursor.getInt(0);
		mole.setId(moleId);
		mole.setName(cursor.getString(1));
		mole.setDescription(cursor.getString(2));
		mole.setLocation(cursor.getString(3));

		// TODO: This seems to have been removed? idk ~Claire
		// ArrayList<Integer> photoIds = this.getPhotoIdsFromeMole(moleId);
		// mole.setPhotoId(photoIds);

		return mole;
	}

	// insert a picture into the database
	/**
	 * @param picture
	 */
	public void insertPicture(Picture picture) {
		ContentValues values = new ContentValues();
		PictureController picturecontroller = new PictureController(
				MoleFinderApplication.getAppContext());
		values.put(TablePictures.COLUMN_ID, picture.getId());
		values.put(TablePictures.COLUMN_DATE,
				picturecontroller.getDateAsString());
		values.put(TablePictures.COLUMN_DESCRIPTION, picture.getDescription());
		values.put(TablePictures.COLUMN_URI, picturecontroller.getUriAsString());

		database.insert(TablePictures.TABLE_PICTURES, null, values);
	}

	/**
	 * Delete a picture from the database
	 * 
	 * @param picture
	 */
	public void deletePicture(Picture picture) {
		long picid = picture.getId();
		database.delete(TablePictures.TABLE_PICTURES,
				TablePictures.COLUMN_ID + " = " + picid, null);
	}

	/**
	 * Retrieve all the pictures from the database
	 * 
	 * @return a list of pictures
	 */
	public ListMole getAllPictures() {
		ListPicture allpictures = new ListPicture();
		Cursor cursor = database.query(TablePictures.TABLE_PICTURES,
				TablePictures.ALLCOLUMNS, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
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
	 * Associate a picture with a mole and insert that relationship into the
	 * database.
	 * 
	 * @param the
	 *            id of the mole
	 * @param the
	 *            id of the picture
	 */
	public void insertMolePicturePair(int moleID, int PhotoID) {
		ContentValues values = new ContentValues();
		values.put(TableMolesPictures.COLUMN_MOLEID, moleID);
		values.put(TableMolesPictures.COLUMN_PICTUREID, PhotoID);

		database.insert(TableMolesPictures.TABLE_MOLESPICTURES, null, values);

	}

	/**
	 * Obtain all the ids of the pictures related to the mole provided.
	 * 
	 * @param the
	 *            id of the mole
	 * @return a list of picture ids
	 */
	public ArrayList<Integer> getPhotoIdsFromeMole(int moleId) {
		ArrayList<Integer> photoids = new ArrayList<Integer>();
		String[] columns = { TableMolesPictures.COLUMN_PICTUREID };
		Cursor cursor = database.query(TableMolesPictures.TABLE_MOLESPICTURES,
				columns,
				TableMolesPictures.COLUMN_PICTUREID + " = " + moleId, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int photoid = cursor.getInt(0);
			photoids.add(photoid);
			cursor.moveToNext();
		}
		cursor.close();
		return photoids;
	}

	/**
	 * Retrieve a list of picture related to the given mole.
	 * 
	 * @param the
	 *            id of the mole
	 * @return a list of pictures
	 */
	public ListPicture getListPictureFromMole(int moleID) {
		ListPicture pictures = new ListPicture();
		String[] columns = { TableMolesPictures.COLUMN_PICTUREID };
		Cursor cursor = database.query(TableMolesPictures.TABLE_MOLESPICTURES,
				columns,
				TableMolesPictures.COLUMN_PICTUREID + " = " + moleID, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Picture picture = new Picture();
			int PhotoID = cursor.getInt(0);
			picture.setId(PhotoID);
			// a temporal cursor to get the
			Cursor tempcursor = database.query(TablePictures.TABLE_PICTURES,
					TablePictures.ALLCOLUMNS,
					"where " + TablePictures.COLUMN_ID + " = " + PhotoID, null,
					null, null, null);
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

	/**
	 * Retrieve a mole from the database using its id.
	 * 
	 * @param the
	 *            id of the mole
	 * @return the Mole object
	 */
	public Mole getMoleFromId(long id) {
		Mole mole = new Mole();
		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS,
				TableMoles.COLUMN_ID + " = " + String.valueOf(id), null, null,
				null, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			mole = cursorToMole(cursor);
		}
		cursor.close();
		return mole;
	}
}
