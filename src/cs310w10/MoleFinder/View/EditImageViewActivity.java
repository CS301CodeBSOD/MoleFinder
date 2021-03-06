package cs310w10.MoleFinder.View;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Picture;

/**
 * @author Claire Semple
 * @author Bing Pan
 * @description This is the view which allows the user to manipulate the
 *              contents of a mole object.
 * @param <V>
 */
public class EditImageViewActivity extends ViewActivity<Picture> {

	private static final int DATE_DIALOG_ID = 0;

	private ImageView image;
	private ImageButton submitButton;
	private Button dateButton;
	private EditText notesField;

	@Override
	protected void setViews() {
		setContentView(R.layout.edit_image);
		image = (ImageView) findViewById(R.id.EditImageViewImage);
		submitButton = (ImageButton) findViewById(R.id.EditImageViewSubmitButton);
		dateButton = (Button) findViewById(R.id.EditImageViewDateButton);
		notesField = (EditText) findViewById(R.id.EditImageViewNotesField);
	}

	@Override
	protected void addListeners() {
		dateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		submitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSubmitButton();
			}
		});
	}

	public void pressSubmitButton() {
		picture.setDescription(notesField.getText().toString());
		PictureController controller =
				new PictureController(picture, this);
		controller.editPicture(
				picture.getDate().getTimeInMillis(),
				picture.getDescription(),
				picture.getImageData());
		setResult(RESULT_OK);
		finish();
	}

	@Override
	protected void updateSelf() {
		update(picture);
	}

	public void update(Picture model) {

		if (model != null) {
			picture = model;
		}
		if (picture != null) {
			String path = picture.getImageData().getPath();
			Bitmap imagebitmap = BitmapFactory.decodeFile(path);
			image.setImageBitmap(imagebitmap);

			notesField.setText(picture.getDescription());

			if (picture.getDate() == null) {

				picture.setDate(Calendar.getInstance());
			}
			dateButton.setText(new PictureController(picture, this)
					.getDateAsString());
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Calendar date = picture.getDate();
							date.set(year, monthOfYear, dayOfMonth);
							picture.setDate(date);
							update(picture);
						}

					}, picture.getDate().get(Calendar.YEAR), picture.getDate()
							.get(Calendar.MONTH), picture.getDate().get(
							Calendar.DAY_OF_MONTH));
		}
		return null;
	}
}
