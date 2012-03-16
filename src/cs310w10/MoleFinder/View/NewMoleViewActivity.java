package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Model.Mole;
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

public class NewMoleViewActivity extends Activity implements ViewActivity<Mole> {
	private ImageButton submitButton;
	private EditText nameEdit;
	private EditText descriptionEdit;
	private Spinner locationSpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_mole);

		submitButton = (ImageButton) findViewById(R.id.NewMoleViewSubmitButton);
		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSubmitButton();
			}
		});

		nameEdit = (EditText) findViewById(R.id.NewMoleViewNameEdit);
		descriptionEdit = (EditText) findViewById(R.id.NewMoleViewDescriptionEdit);

		locationSpinner = (Spinner) findViewById(R.id.NewMoleViewLocationSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.area_names, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		locationSpinner.setAdapter(adapter);

	}

	public void pressSubmitButton() {
	    String name = nameEdit.getText().toString().trim();
		String description = descriptionEdit.getText().toString().trim();
		String location = locationSpinner.getSelectedItem().toString();

		ListMoleController listMoleController = MoleFinderApplication
				.getListMoleController();

		int id = listMoleController.addMole(name, description, location);
		if (id == -1){
		    Toast.makeText(getBaseContext(), "Invalid or already in use mole name", Toast.LENGTH_LONG).show();

		}else{
		    Intent intent = new Intent(this, MoleViewActivity.class);
		    intent.putExtra(Intent.EXTRA_SUBJECT, id);
		    startActivity(intent);
		}
	}

	public void update(Mole mole) {
		// TODO Auto-generated method stub

	}
}
