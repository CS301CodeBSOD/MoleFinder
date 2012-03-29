package cs310w10.MoleFinder.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Model.Picture;

public class EditImageViewActivity extends ViewActivity<Picture> {
	
	private static final int DATE_DIALOG_ID = 0;

	private ImageView image;
	private ImageButton submitButton;
	private Button dateButton;
	private EditText notesField;
	private Picture picture;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_image);

		image = (ImageView) findViewById(R.id.EditImageViewImage);

		submitButton = (ImageButton) findViewById(R.id.EditImageViewSubmitButton);

		dateButton = (Button) findViewById(R.id.EditImageViewDateButton);

		notesField = (EditText) findViewById(R.id.EditImageViewNotesField);
		update(picture);
	}

	
	private void addButtonListeners() {
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

		// TODO: apply fields and go back to the mole you were just on.
	}

	public void update(Picture model) {
		Bundle extras = getIntent().getExtras();
		if (extras.containsKey("id")) {
			picture = MoleFinderApplication.getListPictureController()
					.getPictureById(extras.getInt("id"));
			if (picture != null) {
				String path = picture.getImageData().getPath();
				Bitmap imagebitmap = BitmapFactory.decodeFile(path);
				image.setImageBitmap(imagebitmap);
				if (picture.getDate() != null) {
					displayDate(picture.getDate());
				} else {
					picture.setDate(new Date());
				}
			}
		}

	}

	private void displayDate(Date date) {
		DateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy");
		String text = df.format(date);
		dateButton.setText(text);
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(picture.getDate());
			return new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {

						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {

							Date date = picture.getDate();
							date.setYear(year);
							date.setMonth(monthOfYear);
							date.setDate(dayOfMonth);
							picture.setDate(date);
							displayDate(date);
						}

					}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)
							, calendar.get(Calendar.DAY_OF_MONTH));
		}
		return null;
	}
}
