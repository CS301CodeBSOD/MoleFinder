package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

public class TableMoles {
	public static final String TABLE_MOLES = "moles";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_LOCATION = "location";
	
	private  static final String TABLE_CREATE = "create table "
			+ TABLE_MOLES + "( " + COLUMN_ID 
			+ " integer primary key, " + COLUMN_NAME
			+ " text unique not null, " + COLUMN_LOCATION
			+ " text, " + COLUMN_DESCRIPTION
			+ " text);";
	
	public static void onCreate(SQLiteDatabase db){
		db.execSQL(TABLE_CREATE);
	}
	
	public static void onUpdate(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOLES);
		onCreate(db);
	}
}
