package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Model.Picture;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainViewActivity extends ViewActivity<Picture> {
	private Button addMoleButton;
	private Button viewMolesButton;
	private Button searchLocationButton;

	@Override
	protected void setViews() {
		setContentView(R.layout.main);

		addMoleButton = (Button) findViewById(R.id.MainViewAddMoleButton);
		viewMolesButton = (Button) findViewById(R.id.MainViewViewMolesButton);
		searchLocationButton = (Button) findViewById(R.id.MainViewSearchLocationButton);

	}

	@Override
	protected void addListeners() {
		addMoleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressAddMoleButton();
			}
		});

		viewMolesButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressViewMolesButton();
			}
		});

		searchLocationButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pressSearchLocationButton();
			}
		});
	}

	protected void pressViewMolesButton() {
		Intent intent = new Intent(this, ListMoleViewActivity.class);
		startActivity(intent);
	}

	protected void pressSearchLocationButton() {
		Intent intent = new Intent(this, SearchViewActivity.class);
		startActivity(intent);
	}

	public void pressAddMoleButton() {
		Intent intent = new Intent(this, NewMoleViewActivity.class);
		startActivity(intent);
	}

	public void update(Picture model) {
		// doesn't do anything since the main view doesn't actually display any
		// models
	}

	@Override
	protected void updateSelf() {
		// TODO Auto-generated method stub
		
	}

}