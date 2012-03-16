package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.ListPictureController;
import cs310w10.MoleFinder.Model.MoleFinder;
import android.app.Application;
import android.content.Context;

public class MoleFinderApplication extends Application {
	transient private static MoleFinder moleFinder;
	transient private static ListMoleController listMoleController;
	transient private static ListPictureController listPictureController;
	private static Context context;

	public void onCreate(){
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
			listPictureController = new ListPictureController();
		}
		return listPictureController;
	}
}
