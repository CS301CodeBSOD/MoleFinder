package cs310w10.MoleFinder.View;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;
import cs310w10.MoleFinder.Controller.ListPictureController;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Mole;
import cs310w10.MoleFinder.Model.Picture;

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
			int id = data.getIntExtra("moleId", -1);
			mole = new MoleController(this).getMoleFromId(id);
			setResult(Activity.RESULT_OK);
		}
		if (requestCode == CAMERA_PICTURE_REQUEST && resultCode == RESULT_OK) {
			Bitmap imagedata = (Bitmap) data.getExtras().get("data");
			PictureController newPictureController = new PictureController(this);

			Calendar date = Calendar.getInstance();
			long time = date.getTimeInMillis();
			picture = newPictureController.createPicture(time, "",
					imagedata);

			launchEditPicture();
		}
		update(mole);
	}

	public void update(Mole model) {
		if (model != null) {
			mole = model;
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
		List<Picture> pictures = new ListPictureController(getBaseContext())
				.getListPictureFromMole(mole.getId());
		gallery.setAdapter(new ImageAdapter(getBaseContext(), pictures));
	}
}
