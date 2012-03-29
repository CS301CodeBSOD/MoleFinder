package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Model.ListMole;
//import cs310w10.MoleFinder.Model.MoleAdapter;
import cs310w10.MoleFinder.View.MoleViewActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

public class ListMoleViewActivity extends ViewActivity<ListMole> implements
		OnItemClickListener {
	ImageButton trashButton;
	ImageButton addButton;
	ListView moleListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListMole moleList = MoleFinderApplication.getListMoleController()
				.getMoles();

		// moleListView.setAdapter(new ListAdapter(moleList.get(),
		// getApplicationContext()));
		registerForContextMenu(moleListView);
	}


	@Override
	protected void setViews() {
		setContentView(R.layout.list_mole);

		trashButton = (ImageButton) findViewById(R.id.ListMoleViewTrashButton);
		addButton = (ImageButton) findViewById(R.id.ListMoleViewAddButton);
		moleListView = (ListView) findViewById(R.id.ListMoleViewMoleList);
	}
	
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
		Intent intent;
		intent = new Intent(this, EditMoleViewActivity.class);
		startActivityForResult(intent, 1);
	}

	public void update(ListMole moles) {

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
			ListMole moleList = MoleFinderApplication.getListMoleController()
					.getMoles();
			update(moleList);
		}

	}

	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		// Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MoleViewActivity.class);
		intent.putExtra(Intent.EXTRA_SUBJECT, id);
		startActivityForResult(intent, 3);

	}


}
