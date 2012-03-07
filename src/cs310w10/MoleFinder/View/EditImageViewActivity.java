package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class EditImageViewActivity extends Activity {
	private ImageView image;
	private Button submitButton;
	private Spinner dateSpinner;
	private EditText notesField;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_image);

		image = (ImageView) findViewById(R.id.EditImageViewImage);
		// TODO: load from intent

		submitButton = (Button) findViewById(R.id.EditImageViewSubmitButton);
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
}
