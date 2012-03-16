package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Model.Mole;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.TextView;

public class MoleViewActivity extends Activity implements ViewActivity<Mole> {
	private ImageButton editDescriptionButton;
	private ImageButton addPictureButton;
	private TextView name;
	private TextView description;
	private Gallery gallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mole);

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

		name = (TextView) findViewById(R.id.MoleViewName);
		name.setText("NAME GOES HERE"); // TBI : get mole details from intent

		description = (TextView) findViewById(R.id.MoleViewDescription);
		description.setText("DESCRIPTION GOES HERE"); // TBI

		// tutorial for this part:
		// http://developer.android.com/guide/tutorials/views/hello-gallery.html
		gallery = (Gallery) findViewById(R.id.MoleViewGallery);
		gallery.setAdapter(null); // TBI

	}

	protected void pressAddPictureButton() {
		// TBI : launch picture-get intent
	}

	protected void pressEditButton() {
		// TBI : edit description field
	}

	public void update(Mole model) {
		// TODO Auto-generated method stub

	}
}
