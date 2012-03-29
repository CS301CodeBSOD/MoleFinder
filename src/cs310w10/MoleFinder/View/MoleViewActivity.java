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
	private static final int CAMERA_PICTURE_REQUEST = 124;
	private static final int EDIT_MOLE_REQUEST = 125;
	private static final int EDIT_PIC_REQUEST = 126;
	private ImageButton editDescriptionButton;
	private ImageButton addPictureButton;
	private TextView name;
	private TextView description;
	private Gallery gallery;
	private Mole mole;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mole);

		gallery = (Gallery) findViewById(R.id.MoleViewGallery);
		name = (TextView) findViewById(R.id.MoleViewName);
		description = (TextView) findViewById(R.id.MoleViewDescription);

		editDescriptionButton = (ImageButton) findViewById(R.id.MoleViewEditButton);
		editDescriptionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressEditButton();
			}
		});

		addPictureButton = (ImageButton) findViewById(R.id.MoleViewAddPictureButton);
		addPictureButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddPictureButton();
			}
		});

		Intent intent = getIntent();
		long id = intent.getExtras().getLong(Intent.EXTRA_SUBJECT);
		mole = MoleController.getMoleFromId(id);

		update(mole);

	}

	protected void pressAddPictureButton() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_PICTURE_REQUEST);
	}

	protected void pressEditButton() {
		long id = mole.getId();
		Intent intent = new Intent(this, NewMoleViewActivity.class);
		intent.putExtra("id", id);
		startActivityForResult(intent, EDIT_MOLE_REQUEST);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EDIT_MOLE_REQUEST && resultCode == RESULT_OK) {
			update(mole);
			setResult(Activity.RESULT_OK);
		}
		if (requestCode == CAMERA_PICTURE_REQUEST && resultCode == RESULT_OK) {
			Bitmap newPicture = (Bitmap) data.getExtras().get("data");
			PictureController newPictureController = new PictureController(
					newPicture);
			MoleFinderApplication.getListPictureController().insertPicture(
					newPictureController);
			Intent intent = new Intent(this, EditImageViewActivity.class);
			intent.putExtra("id", newPictureController.getPicture().getId());
			startActivityForResult(intent, EDIT_PIC_REQUEST);
			update(mole);
		}

	}

	public void update(Mole model) {
		name.setText(mole.getName());
		description.setText(mole.getDescription());
		updateGallery();
	}

	protected void updateGallery() {
		// tutorial for this part:
		// http://developer.android.com/guide/tutorials/views/hello-gallery.html
		gallery.setAdapter(null); // TBI
	}
}
