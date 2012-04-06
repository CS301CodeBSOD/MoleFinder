package cs310w10.MoleFinder.View;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Model.Mole;

public class ListMoleViewActivity extends ViewActivity<ArrayList<Mole>>
		implements OnItemClickListener {
	ImageButton trashButton;
	ImageButton addButton;
	ListView moleListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<Mole> moleList = MoleFinderApplication
				.getListMoleController()
				.getMoles();

		SimpleAdapter adapter = new SimpleAdapter(
				this,
				new MoleMapListAdapter(moleList),
				R.layout.mole_list_item,
				new String[] { "name",
						/* "description", */
						"id",
						"location" },
				new int[] {
						R.id.MoleListItemName,
						/* R.id.MoleListItemDescription, */
						R.id.MoleListItemId,
						R.id.MoleListItemLocation }
				);

		moleListView.setAdapter(adapter);
		registerForContextMenu(moleListView);
	}

	@Override
	protected void setViews() {
		setContentView(R.layout.list_mole);

		trashButton = (ImageButton) findViewById(R.id.ListMoleViewTrashButton);
		addButton = (ImageButton) findViewById(R.id.ListMoleViewAddButton);
		moleListView = (ListView) findViewById(R.id.ListMoleViewMoleList);
	}

	@Override
	protected void addListeners() {
		addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddButton();
			}
		});

		trashButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressTrashButton();
			}
		});
		moleListView.setOnItemClickListener(this);
	}

	protected void pressTrashButton() {
		// TODO: Add confirmation dialog
		ListMoleController.deleteAllMoles();
		Toast.makeText(getBaseContext(), "Database purged", Toast.LENGTH_SHORT)
				.show();
	}

	protected void pressAddButton() {
		this.mole = null;
		this.picture = null;
		this.launchEditMole();
	}

	public void update(ArrayList<Mole> moles) {

		// moleListView.setAdapter(new MoleAdapter(moles.get(),
		// getApplicationContext()));

	}

	@Override
	protected void updateSelf() {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			ArrayList<Mole> moleList = MoleFinderApplication
					.getListMoleController()
					.getMoles();
			update(moleList);
		}

	}

	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		int moleId = -1;
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, String> item = (HashMap<String, String>) moleListView
					.getItemAtPosition(position);
			moleId = Integer.parseInt(item.get("id"));
		} catch (Exception e) {
			Toast.makeText(this, "Problem detecting item", Toast.LENGTH_SHORT)
					.show();
		}
		launchViewMole(moleId);
	}
}
