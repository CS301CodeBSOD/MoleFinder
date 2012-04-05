package cs310w10.MoleFinder.View;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Model.Mole;

public class EditMoleViewActivity extends ViewActivity<Mole> {
	private ImageButton submitButton;
	private EditText nameEdit;
	private EditText descriptionEdit;
	private Spinner locationSpinner;
	private Boolean editMode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (mole != null) {
			editMode = true;

			nameEdit.setText(mole.getName());
			descriptionEdit.setText(mole.getDescription());

		} else {
			editMode = false;
			mole = new Mole();
		}
	}

	@Override
	protected void setViews() {
		setContentView(R.layout.new_mole);

		submitButton = (ImageButton) findViewById(R.id.NewMoleViewSubmitButton);
		nameEdit = (EditText) findViewById(R.id.NewMoleViewNameEdit);
		descriptionEdit = (EditText) findViewById(R.id.NewMoleViewDescriptionEdit);

		locationSpinner = (Spinner) findViewById(R.id.NewMoleViewLocationSpinner);
		
	}

	@Override
	protected void addListeners() {
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSubmitButton();
			}
		});

	}

	public void pressSubmitButton() { // TODO: Tidy this
		String name = nameEdit.getText().toString().trim();
		String description = descriptionEdit.getText().toString().trim();
		String location = locationSpinner.getSelectedItem().toString();

		if (editMode) {
			Intent intent = getIntent();
			long id = intent.getExtras().getLong(Intent.EXTRA_SUBJECT);

			MoleController controller = new MoleController(this);
			controller.getMoleFromId(id);
			controller.editMole(name, description, location);
			setResult(Activity.RESULT_OK, intent);

			finish();
		} else {

			MoleController mController = new MoleController(this);
			mController.createMole(name, description, location);
			Mole mole = mController.getMole();
			int id = mole.getId();
			if (id == -1) {
				Toast.makeText(getBaseContext(),
						"Invalid or already in use mole name",
						Toast.LENGTH_LONG).show();

			} else {
				Intent intent = new Intent(this, MoleViewActivity.class);
				
				// TODO: get mole
				putMole(intent, id);
				startActivity(intent);

				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		}
	}

	public void update(Mole mole) {
		// List of tags
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.area_names, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationSpinner.setAdapter(adapter);

	}
	@Override
	protected void updateSelf() {
		update(mole);
	}
}
