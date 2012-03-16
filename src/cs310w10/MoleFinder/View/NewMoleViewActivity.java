package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Model.ListMole;
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
import android.widget.TextView;
import android.widget.Toast;

public class NewMoleViewActivity extends Activity implements fView<Mole> {
    private ImageButton submitButton;
    private EditText nameEdit;
    private EditText descriptionEdit;
    private Spinner locationSpinner;
    private Boolean editMode;
    private Mole mole = new Mole();

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

        if (getIntent().hasExtra(Intent.EXTRA_SUBJECT)){
            editMode = true;
            Intent intent = getIntent();
            long id = intent.getExtras().getLong(Intent.EXTRA_SUBJECT);

            mole = MoleController.getMoleFromId(id);

            nameEdit.setText(mole.getName()); 
            descriptionEdit.setText(mole.getDescription()); 

        }else{
            editMode = false;
        }
    }

    public void pressSubmitButton() {
        String name = nameEdit.getText().toString().trim();
        String description = descriptionEdit.getText().toString().trim();
        String location = locationSpinner.getSelectedItem().toString();

        if (editMode){
            Intent intent = getIntent();
            long id = intent.getExtras().getLong(Intent.EXTRA_SUBJECT);
            
            MoleController.editMole(name, description, location, id);
            setResult(Activity.RESULT_OK, intent);

            finish();
        }else{
             
            ListMoleController listMoleController = MoleFinderApplication
                .getListMoleController();

            long id = listMoleController.addMole(name, description, location);
            if (id == -1){
                Toast.makeText(getBaseContext(), "Invalid or already in use mole name", Toast.LENGTH_LONG).show();

            }else{
                Intent intent = new Intent(this, MoleViewActivity.class);
                intent.putExtra(Intent.EXTRA_SUBJECT, id);
                startActivity(intent);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }   
        }
    }



    public void update(Mole mole) {
        // TODO Auto-generated method stub

    }
}
