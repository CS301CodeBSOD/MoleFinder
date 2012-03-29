package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.ListMole;
import cs310w10.MoleFinder.Model.ListPicture;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.Picture;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class ViewActivity<M> extends Activity implements fView<M> {
	protected Mole mole;
	protected Picture picture;
	protected ListMole listMole;
	protected ListPicture listPicture;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if(intent.hasExtra("moleId")) {
			int id = extras.getInt("moleId");
			mole = MoleController.getMoleFromId(id);
		}
		if(intent.hasExtra("pictureId")) {
			int id = extras.getInt("pictureId");
			picture = PictureController.getPictureFromId(id);
		}
	}
	
	protected void putMole(Intent putintent, Mole putMole) {
		putintent.putExtra("moleId", putMole.getId());
	}
	protected void putPicture(Intent putintent, Mole putPicture) {
		putintent.putExtra("pictureId", putPicture.getId());
	}
}
