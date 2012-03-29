package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Model.Mole;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class SearchViewActivity extends ViewActivity<Mole> {
	private EditText textInput;
	private ListView resultsList;


	@Override
	protected void setViews() {
		setContentView(R.layout.search);

		textInput = (EditText) findViewById(R.id.SearchViewTextInput);
		resultsList = (ListView) findViewById(R.id.SearchViewResultsList);
		
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub
		
	}

	public void update(Mole model) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateSelf() {
		// TODO Auto-generated method stub
		
	}

}
