package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Model.ListMole;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.TextView;

public class MoleGalleryViewActivity extends ViewActivity<ListMole> {
	private ImageSwitcher imageSwitcher;
	private ImageButton editButton;
	private TextView dateText;
	private TextView notesText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mole_gallery);

		imageSwitcher = (ImageSwitcher) findViewById(R.id.MoleGalleryViewImageSwitcher);
		// TODO: make imageSwitcher work? Maybe this will help:
		// http://saigeethamn.blogspot.com/2010/05/image-switcher-view-android-developer.html

		editButton = (ImageButton) findViewById(R.id.MoleGalleryViewEditButton);
		editButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressEditButton();
			}
		});

		dateText = (TextView) findViewById(R.id.MoleGalleryViewDateText);
		// TODO: get date from image data
		dateText.setText("A DATE");

		notesText = (TextView) findViewById(R.id.MoleGalleryViewNotesText);
		// TODO: get notes from image data
		dateText.setText("SOME NOTES ABOUT THE MOLE");

	}

	protected void pressEditButton() {
		// TODO: transfer intent to EditImage
	}

	public void update(ListMole model) {
		// TODO Auto-generated method stub

	}
}
