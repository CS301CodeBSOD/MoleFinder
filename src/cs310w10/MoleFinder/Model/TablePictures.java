package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author James Helberg TablePictures is an interface with the database within
 *         the Android environment
 */
public class TablePictures {

	/**
	 * these are Statics associated with the database
	 */
	public static final String TABLE_PICTURES = "pictures";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_URI = "uri";
	public static final String[] ALLCOLUMNS = { COLUMN_ID,
			COLUMN_DESCRIPTION, COLUMN_DATE, COLUMN_URI };

	private static final String TABLE_CREATE = "create table "
			+ TABLE_PICTURES + "( " + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_DESCRIPTION
			+ " text, " + COLUMN_DATE
			+ " integer not null, " + COLUMN_URI
			+ " text );";

	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	public static void onUpdate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURES);
		onCreate(db);
	}

}
