package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MoleFactory {
	private final SQLiteDatabase database;

	public MoleFactory(SQLiteDatabase database) {
		this.database = database;
	}

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
	public ArrayList<Mole> getAllMoles() {
		ArrayList<Mole> allmoles = new ArrayList<Mole>();
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
}
