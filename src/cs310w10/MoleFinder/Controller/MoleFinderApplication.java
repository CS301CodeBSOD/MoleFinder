package cs310w10.MoleFinder.Controller;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import cs310w10.MoleFinder.Model.MoleFinder;

/**
 * 
 * MoleFinderApplication owns the singleton controller objects, as well as handles
 * the state of the application context and file path. 
 *
 */
public class MoleFinderApplication extends Application {
	transient private static MoleFinder moleFinder;
	transient private static ListMoleController listMoleController;
	transient private static ListPictureController listPictureController;
	private static final String SAVE_PATH = Environment
			.getExternalStorageDirectory().getPath() + "/Pictures/MoleFinder/";
	private static final String SAVE_FORMAT = ".png";

	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		MoleFinderApplication.context = getApplicationContext();
	}

	public static Context getAppContext() {
		return MoleFinderApplication.context;
	}

	public static MoleFinder getMoleFinder() {
		if (moleFinder == null) {
			moleFinder = new MoleFinder();
		}
		return moleFinder;
	}

	public static ListMoleController getListMoleController() {
		if (listMoleController == null) {
			listMoleController = new ListMoleController();
		}
		return listMoleController;
	}

	public static ListPictureController getListPictureController() {
		if (listPictureController == null) {
			listPictureController = new ListPictureController(getAppContext());
		}
		return listPictureController;
	}

	public static String getSavePath() {
		return SAVE_PATH;
	}

	public static String getSaveFormat() {
		return SAVE_FORMAT;
	}
}
