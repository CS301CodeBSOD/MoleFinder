package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;
/**
 * 
 * @author James Helberg
 *
 * The database interface with the pictures Database
 */
public class TableMolesPictures {
	
	/**
	 * Statics for pulling adn pushing data to the database of pictures
	 */
	public static final String TABLE_MOLESPICTURES = "molespictures";
	public static final String COLUMN_MOLEID = "moleid";
	public static final String COLUMN_PICTUREID = "pictureid";
	public static final String[] ALLCOLUMNS = { COLUMN_MOLEID, COLUMN_PICTUREID };
	
	private static final String TABLE_CREATE = "create table "
					+ TABLE_MOLESPICTURES + "( " + COLUMN_MOLEID
					+ " integer not null, " + COLUMN_PICTUREID
					+ " integer not null);";
	
	public static void onCreate(SQLiteDatabase db){
		db.execSQL(TABLE_CREATE);
	}
	
	public static void onUpdate(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOLESPICTURES);
		onCreate(db);
	}
	
}
