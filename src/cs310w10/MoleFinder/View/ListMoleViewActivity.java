package cs310w10.MoleFinder.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ListMoleViewActivity extends Activity {
	Button trashButton;
	Button addButton;
	ListView moleList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_mole);

		trashButton = (Button) findViewById(R.id.ListMoleViewTrashButton);
		trashButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressTrashButton();
			}
		});

		addButton = (Button) findViewById(R.id.ListMoleViewAddButton);
		addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddButton();
			}
		});

		moleList = (ListView) findViewById(R.id.ListMoleViewMoleList);
	}

	protected void pressTrashButton() {
		// TBI
	}

	protected void pressAddButton() {
		// TBI
	}
}
