package cs310w10.MoleFinder.View;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MoleSQLiteHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "moles.db";
	private static final int DATABASE_VERSION = 1;
	
	public MoleSQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public MoleSQLiteHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		TableMoles.onCreate(db);
		TablePictures.onCreate(db);
		TableMolesPictures.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MoleSQLiteHelper.class.getName(), "Upgrading database from version "
					+ oldVersion + " to " + newVersion);
		TableMoles.onUpdate(db);
		TablePictures.onUpdate(db);
		TableMoles.onUpdate(db);
	}

}
