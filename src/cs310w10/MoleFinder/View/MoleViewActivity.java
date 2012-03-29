package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Mole;

public class MoleViewActivity extends ViewActivity<Mole> {
	private ImageButton editDescriptionButton;
	private ImageButton addPictureButton;
	private TextView name;
	private TextView description;
	private Gallery gallery;

	@Override
	protected void setViews() {
		setContentView(R.layout.mole);

		gallery = (Gallery) findViewById(R.id.MoleViewGallery);
		name = (TextView) findViewById(R.id.MoleViewName);
		description = (TextView) findViewById(R.id.MoleViewDescription);
		editDescriptionButton = (ImageButton) findViewById(R.id.MoleViewEditButton);
		addPictureButton = (ImageButton) findViewById(R.id.MoleViewAddPictureButton);
	}

	@Override
	protected void addListeners() {
		editDescriptionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressEditButton();
			}
		});

		addPictureButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddPictureButton();
			}
		});

	}

	protected void pressAddPictureButton() {
		launchCamera();
	}

	protected void pressEditButton() {
		launchEditMole();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EDIT_MOLE_REQUEST && resultCode == RESULT_OK) {
			setResult(Activity.RESULT_OK);
		}
		if (requestCode == CAMERA_PICTURE_REQUEST && resultCode == RESULT_OK) {
			Bitmap newPicture = (Bitmap) data.getExtras().get("data");
			PictureController newPictureController = new PictureController(
					newPicture);
			MoleFinderApplication.getListPictureController().insertPicture(
					newPictureController);
			Intent intent = new Intent(this, EditImageViewActivity.class);
			intent.putExtra("moleId", newPictureController.getPicture().getId());
			startActivityForResult(intent, EDIT_PIC_REQUEST);
		}
		update(mole);
	}

	public void update(Mole model) {
		if(mole != null) {
		name.setText(mole.getName());
		description.setText(mole.getDescription());
		updateGallery();
		}
	}

	@Override
	protected void updateSelf() {
		update(mole);
	}

	protected void updateGallery() {
		// tutorial for this part:
		// http://developer.android.com/guide/tutorials/views/hello-gallery.html
		gallery.setAdapter(null); // / TODO: Implement TBI
	}
}
