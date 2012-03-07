package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewMoleViewActivity extends Activity {
	private Button submitButton;
	private EditText nameEdit;
	private EditText descriptionEdit;
	private Spinner locationSpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_mole);

		submitButton = (Button) findViewById(R.id.NewMoleViewSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSubmitButton();
			}
		});

		nameEdit = (EditText) findViewById(R.id.NewMoleViewNameEdit);
		descriptionEdit = (EditText) findViewById(R.id.NewMoleViewDescriptionEdit);
		locationSpinner = (Spinner) findViewById(R.id.NewMoleViewLocationSpinner);

	}

	public void pressSubmitButton() {

	}
}
