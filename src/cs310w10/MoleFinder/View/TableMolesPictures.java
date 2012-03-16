package cs310w10.MoleFinder.View;

import android.database.sqlite.SQLiteDatabase;

public class TableMolesPictures {
	
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
