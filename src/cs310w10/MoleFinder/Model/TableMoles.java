package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

public class TableMoles {
	public static final String TABLE_MOLES = "moles";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_LOCATION = "location";
	public static final String[] ALLCOLUMNS = { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION,
										COLUMN_LOCATION };
	
	private  static final String TABLE_CREATE = "create table "
			+ TABLE_MOLES + "( " + COLUMN_ID 
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null, " + COLUMN_LOCATION
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
