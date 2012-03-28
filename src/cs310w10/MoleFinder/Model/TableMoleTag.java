package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

	public class TableMoleTag {
		public static final String TABLE_MOLETAG = "moletag";
		public static final String COLUMN_MOLEID = "moleid";
		public static final String COLUMN_TAG = "tag";
		public static final String[] ALLCOLUMNS = { COLUMN_MOLEID, COLUMN_TAG };
	
		private static final String TABLE_CREATE = "create table "
				+ TABLE_MOLETAG + "( " + COLUMN_MOLEID
				+ " integer unique not null, " + COLUMN_TAG
				+ " text not null);";
	
		public static void onCreate(SQLiteDatabase db){
			db.execSQL(TABLE_CREATE);
		}
	
		public static void onUpdate(SQLiteDatabase db){
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOLETAG);
			onCreate(db);
		}
}
