package cs310w10.MoleFinder.Model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

public class MoleFactory {
	private MolesDataSource connection;
	
	public MoleFactory( MolesDataSource connection){
		connection.;
	}
	
	 public Mole createMole(String name, String description, String location){
	        Mole mole = new Mole();
	        ContentValues values = new ContentValues();
	        values.put(TableMoles.COLUMN_NAME, name);
	        values.put(TableMoles.COLUMN_LOCATION, location);
	        values.put(TableMoles.COLUMN_DESCRIPTION, description);

	        long rowId = database.insert(TableMoles.TABLE_MOLES, null, values);
	        Cursor cursor = database.query(TableMoles.TABLE_MOLES, TableMoles.ALLCOLUMNS,
	                TableMoles.COLUMN_ID + " = " + rowId, null, null, null, null);
	        if (cursor.moveToFirst()){
	            mole = cursorToMole(cursor);
	        }
	        return mole;
	    }

	    //delete a mole
	    /**
	     * Delete a mole from the database.
	     * @param the Mole object
	     */
	    public void deleteMole(Mole mole){
	        long moleid = mole.getId();
	        database.delete(TableMoles.TABLE_MOLES, 
	                TableMoles.COLUMN_ID + " = " + moleid, null);
	    }
	   
	    /**
	     * edits a mole that has previously been set in the database
	     * @param mole
	     * @param name
	     * @param description
	     * @param location
	     * @return
	     */
	    public Mole editMole(Mole mole, String name, String description, String location){
	        long moleid = mole.getId();
	        ContentValues values = new ContentValues();
	        values.put(TableMoles.COLUMN_NAME, name);
	        values.put(TableMoles.COLUMN_LOCATION, location);
	        values.put(TableMoles.COLUMN_DESCRIPTION, description);
	        long rowId = database.update(TableMoles.TABLE_MOLES, values, TableMoles.COLUMN_ID + " = " + moleid, null);
	        
	        Cursor cursor = database.query(TableMoles.TABLE_MOLES, TableMoles.ALLCOLUMNS,
	                TableMoles.COLUMN_ID + " = " + rowId, null, null, null, null);
	        if (cursor.moveToFirst()){
	            mole = cursorToMole(cursor);
	        }
	        return mole;
	    }
	    
	    //
	    /**
	     * Retrieve all moles from the database as a ListMole object.
	     * @return a ListMole containing all moles
	     */
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
	        return allmoles;

	    }
	    /**
	     * Delete all moles from the database
	     */
	    public void deleteAllMoles(){
	        Cursor cursor = database.query(TableMoles.TABLE_MOLES, TableMoles.ALLCOLUMNS, null, null, null, null, null);
	        cursor.moveToFirst();
	        while (!cursor.isAfterLast()){
	            //Find a way to do this that resets the id field, maybe
	            Mole mole = cursorToMole(cursor);
	            deleteMole(mole);
	            cursor.moveToNext();
	        }
	        cursor.close();

	    }

	    /**
	     * Process all the information from the row pointed by the cursor supplied and package the information into a mole object.
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
	        ArrayList<Integer> photoIds = this.getPhotoIdsFromeMole(moleId);
	        mole.setPhotoId(photoIds);

	        return mole;
	    }
}
