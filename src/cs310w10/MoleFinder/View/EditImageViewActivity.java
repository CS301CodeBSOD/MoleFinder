package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Model.Picture;

public class EditImageViewActivity extends Activity implements fView<Picture> {
	private ImageView image;
	private ImageButton submitButton;
	private Spinner dateSpinner;
	private EditText notesField;
	private Picture picture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_image);

		image = (ImageView) findViewById(R.id.EditImageViewImage);

		submitButton = (ImageButton) findViewById(R.id.EditImageViewSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSubmitButton();
			}
		});

		dateSpinner = (Spinner) findViewById(R.id.EditImageViewDateSpinner);
		// TODO: make datepicker dialog

		notesField = (EditText) findViewById(R.id.EditImageViewNotesField);

	}

	public void pressSubmitButton() {
		// TODO: grab fields and send them to our ListMoleController, then end
		// the intent
	}

	public void update(Picture model) {
		Bundle extras = getIntent().getExtras();
		if (extras.containsKey("id")) {
			picture = MoleFinderApplication.getListPictureController()
					.getPictureById(extras.getInt("id"));
			if (picture != null) {
				String path = picture.getImageData().getPath();
				Bitmap imagebitmap = BitmapFactory.decodeFile(path);
				image.setImageBitmap(imagebitmap);
			}
		}
	}
}
