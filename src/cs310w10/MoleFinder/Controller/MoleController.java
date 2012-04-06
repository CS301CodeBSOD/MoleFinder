package cs310w10.MoleFinder.Controller;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.MoleSQLiteHelper;
import cs310w10.MoleFinder.Model.TableMoles;
import cs310w10.MoleFinder.Model.TableMolesPictures;

/**
 * @author Bing
 */
public class MoleController {

	private Mole mole;
	private final MoleSQLiteHelper connection;

	public MoleController(Context context) {
		this.connection = MoleSQLiteHelper.getInstance(context);
	}

	public MoleController(Mole mole, Context context) {
		this.mole = mole;
		this.connection = MoleSQLiteHelper.getInstance(context);
	}

	/**
	 * create new mole
	 * 
	 * @param name
	 * @param description
	 * @param location
	 */
	public Mole createMole(String name, String description, String location) {
		SQLiteDatabase database = connection.getWritableDatabase();
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
		connection.close();
		return mole;
	}

	/**
	 * Delete a mole from the database.
	 * 
	 * @param the
	 *            Mole object
	 */
	public void deleteMole() {
		if (mole == null) {
			return;
		}
		SQLiteDatabase database = connection.getWritableDatabase();
		int moleid = mole.getId();
		database.delete(TableMoles.TABLE_MOLES,
				TableMoles.COLUMN_ID + " = " + moleid, null);
		connection.close();
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

	public Mole getMole() {
		return mole;
	}

	/**
	 * @param name
	 * @param description
	 * @param location
	 */
	public void editMole(String name, String description, String location) {
		if (mole == null) {
			return;
		}
		SQLiteDatabase database = connection.getWritableDatabase();
		int moleid = mole.getId();
		ContentValues values = new ContentValues();
		values.put(TableMoles.COLUMN_NAME, name);
		values.put(TableMoles.COLUMN_LOCATION, location);
		values.put(TableMoles.COLUMN_DESCRIPTION, description);
		database.update(TableMoles.TABLE_MOLES, values,
				TableMoles.COLUMN_ID + " = " + moleid, null);

		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS,
				TableMoles.COLUMN_ID + " = " + moleid, null, null, null, null);
		if (cursor.moveToFirst()) {
			mole = cursorToMole(cursor);
		}
		cursor.close();
		connection.close();
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
		int moleid = cursor.getInt(0);
		mole.setId(moleid);
		mole.setName(cursor.getString(1));
		mole.setDescription(cursor.getString(2));
		mole.setLocation(cursor.getString(3));
		// ArrayList<Integer> photoIds = this.getPhotoIdsFromeMole(moleId);
		// mole.setPhotoId(photoIds);
		return mole;
	}

	/**
	 * Obtain all the ids of the pictures related to the mole provided.
	 * 
	 * @param the
	 *            id of the mole
	 * @return a list of picture ids
	 */
	public ArrayList<Integer> getPhotoIdsFromeMole(int moleid) {
		SQLiteDatabase database = connection.getWritableDatabase();
		ArrayList<Integer> photoids = new ArrayList<Integer>();
		String[] columns = { TableMolesPictures.COLUMN_PICTUREID };
		Cursor cursor = database.query(TableMolesPictures.TABLE_MOLESPICTURES,
				columns,
				TableMolesPictures.COLUMN_PICTUREID + " = " + moleid, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int photoid = cursor.getInt(0);
			photoids.add(photoid);
			cursor.moveToNext();
		}
		cursor.close();
		connection.close();
		return photoids;
	}

	/**
	 * Retrieve a mole from the database using its id.
	 * 
	 * @param the
	 *            id of the mole
	 * @return the Mole object
	 */
	public Mole getMoleFromId(int id) {
		SQLiteDatabase database = connection.getWritableDatabase();
		mole = new Mole();
		Cursor cursor = database.query(TableMoles.TABLE_MOLES,
				TableMoles.ALLCOLUMNS,
				TableMoles.COLUMN_ID + " = " + String.valueOf(id), null, null,
				null, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			mole = cursorToMole(cursor);
		}else{
			mole = null;
		}
		cursor.close();
		connection.close();
		return mole;
	}

	/**
	 * Associate the provided PhotoID with mole, store the relationship in the
	 * database
	 * 
	 * @param PhotoID
	 */
	public void associateMoleWithPicture(int PhotoID) {
		
		if (mole == null) {
			return;
		}
		SQLiteDatabase database = connection.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TableMolesPictures.COLUMN_MOLEID, mole.getId());
		values.put(TableMolesPictures.COLUMN_PICTUREID, PhotoID);

		database.insert(TableMolesPictures.TABLE_MOLESPICTURES, null, values);

		connection.close();

	}
}
