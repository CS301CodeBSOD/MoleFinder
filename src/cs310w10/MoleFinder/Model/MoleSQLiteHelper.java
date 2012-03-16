package cs310w10.MoleFinder.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is used to establish a connection with the database, the database will be created if it does not already exist.
 * @author Bing Pan
 *
 */
public class MoleSQLiteHelper extends SQLiteOpenHelper{

	/**
	 * Variable values associated with the SQL helper
	 */
	private static final String DATABASE_NAME = "moles.db";
	private static final int DATABASE_VERSION = 1;
	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public MoleSQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 */
	public MoleSQLiteHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		TableMoles.onCreate(db);
		TablePictures.onCreate(db);
		TableMolesPictures.onCreate(db);
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
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
