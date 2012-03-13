package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import cs310w10.MoleFinder.Model.ListMole;

public class ListMoleViewActivity extends Activity implements
		ViewActivity<ListMole> {
	ImageButton trashButton;
	ImageButton addButton;
	ListView moleList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_mole);

		trashButton = (ImageButton) findViewById(R.id.ListMoleViewTrashButton);
		trashButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressTrashButton();
			}
		});

		addButton = (ImageButton) findViewById(R.id.ListMoleViewAddButton);
		addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddButton();
			}
		});

		moleList = (ListView) findViewById(R.id.ListMoleViewMoleList);
	}

	protected void pressTrashButton() {
		// TODO: implement deleting!
	}

	protected void pressAddButton() {
		Intent intent;
		intent = new Intent(this, NewMoleViewActivity.class);
		startActivity(intent);
	}

	public void update(ListMole model) {
		// TODO Auto-generated method stub

	}
}
