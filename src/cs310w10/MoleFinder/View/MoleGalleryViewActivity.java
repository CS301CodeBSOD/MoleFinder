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
	protected void setViews() {
		setContentView(R.layout.mole_gallery);

		imageSwitcher = (ImageSwitcher) findViewById(R.id.MoleGalleryViewImageSwitcher);
		editButton = (ImageButton) findViewById(R.id.MoleGalleryViewEditButton);
		dateText = (TextView) findViewById(R.id.MoleGalleryViewDateText);
		notesText = (TextView) findViewById(R.id.MoleGalleryViewNotesText);
	}

	@Override
	protected void addListeners() {
		editButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressEditButton();
			}
		});
	}
	protected void pressEditButton() {
		// TODO: transfer intent to EditImage
	}

	public void update(ListMole model) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateSelf() {
		// TODO: make imageSwitcher work? Maybe this will help:
		// http://saigeethamn.blogspot.com/2010/05/image-switcher-view-android-developer.html


		// TODO: get date from image data
		dateText.setText("A DATE");

		// TODO: get notes from image data
		dateText.setText("SOME NOTES ABOUT THE MOLE");

	}

}
