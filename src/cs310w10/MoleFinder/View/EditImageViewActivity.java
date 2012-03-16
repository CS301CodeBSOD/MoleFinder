package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditImageViewActivity extends Activity implements
		ViewActivity<Picture> {
	private ImageView image;
	private ImageButton submitButton;
	private Spinner dateSpinner;
	private EditText notesField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_image);

		image = (ImageView) findViewById(R.id.EditImageViewImage);
		// TODO: load from intent

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
		// TODO: grab fields and send them to our ListMoleController
	}

	public void update(Picture model) {
		// TODO Auto-generated method stub

	}
}
