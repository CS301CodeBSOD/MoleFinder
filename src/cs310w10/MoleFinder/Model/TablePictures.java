package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

public class TablePictures {
	
	public static final String TABLE_PICTURES = "pictures";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_URI = "uri";
	public static final String[] ALLCOLUMNS = { COLUMN_ID, COLUMN_DATE,
			COLUMN_URI, COLUMN_DESCRIPTION };
	
	
	private  static final String TABLE_CREATE = "create table "
			+ TABLE_PICTURES + "( " + COLUMN_ID 
			+ " integer primary key, " + COLUMN_DATE
			+ " text not null, " + COLUMN_URI
			+ " text not null, " + COLUMN_DESCRIPTION
			+ " text);";
	
	public static void onCreate(SQLiteDatabase db){
		db.execSQL(TABLE_CREATE);
	}
	
	public static void onUpdate(SQLiteDatabase db){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURES);
		onCreate(db);
	}
	
}
