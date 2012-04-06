package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.Picture;

public abstract class ViewActivity<M> extends Activity implements fView<M> {
	protected static final int CAMERA_PICTURE_REQUEST = 124;
	protected static final int EDIT_MOLE_REQUEST = 125;
	protected static final int EDIT_PIC_REQUEST = 126;
	protected Mole mole;
	protected Picture picture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (intent.hasExtra("moleId")) {
			int id = extras.getInt("moleId");
			MoleController controller = new MoleController(this);
			controller.getMoleFromId(id);
			mole = controller.getMole();
		}
		if (intent.hasExtra("pictureId")) {
			int id = extras.getInt("pictureId");
			picture = PictureController.getPictureFromId(id);
		}
		setViews();
		addListeners();
		updateSelf();
	}

	protected void putMole(Intent putintent, Mole putMole) {
		if (putMole != null) {
			putintent.putExtra("moleId", putMole.getId());
		}
	}

	protected void putPicture(Intent putintent, Picture putPicture) {
		if (putPicture != null) {
			putintent.putExtra("pictureId", putPicture.getId());
		}
	}

	protected void putMole(Intent putintent, long id) {
		if (id >= 0) {
			putintent.putExtra("moleId", id);
		}
	}

	protected void putPicture(Intent putintent, long id) {
		if (id >= 0) {
			putintent.putExtra("pictureId", id);
		}
	}

	protected void launchViewMole() {
		Intent intent = new Intent(this, MoleViewActivity.class);

		launchIntentNoRequest(intent);
	}

	protected void launchEditMole() {
		Intent intent = new Intent(this, EditMoleViewActivity.class);
		launchIntentRequest(intent, EDIT_MOLE_REQUEST);
	}

	protected void launchCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_PICTURE_REQUEST);
	}

	protected void launchIntentNoRequest(Intent intent) {
		putMole(intent, mole);
		putPicture(intent, picture);
		startActivity(intent);
	}

	protected void launchIntentRequest(Intent intent, int request) {
		putMole(intent, mole);
		putPicture(intent, picture);
		startActivityForResult(intent, request);
	}

	// / Load resources from Layout
	protected abstract void setViews();

	// / Create new buttonlisteners to associate functions to buttons
	protected abstract void addListeners();

	// / Any other view updating that needs to occur, such as adding adapters
	// and calling update(data)
	protected abstract void updateSelf();
}
