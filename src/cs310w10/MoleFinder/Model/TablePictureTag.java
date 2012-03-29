package cs310w10.MoleFinder.Model;

import android.database.sqlite.SQLiteDatabase;

public class TablePictureTag {
		public static final String TABLE_PICTURETAG = "molespictures";
		public static final String COLUMN_PICTUREID = "pictureid";
		public static final String COLUMN_TAG = "tag";
		public static final String[] ALLCOLUMNS = { COLUMN_PICTUREID, COLUMN_TAG };
		
		private static final String TABLE_CREATE = "create table "
						+ TABLE_PICTURETAG + "( " + COLUMN_PICTUREID
						+ " integer unique not null, " + COLUMN_TAG
						+ " text not null);";
		
		public static void onCreate(SQLiteDatabase db){
			db.execSQL(TABLE_CREATE);
		}
		
		public static void onUpdate(SQLiteDatabase db){
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURETAG);
			onCreate(db);
		}
}
