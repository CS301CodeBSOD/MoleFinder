package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Mole;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class SearchViewActivity extends ViewActivity<Mole> {
	private EditText textInput;
	private ListView resultsList;

	@Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                if (intent.hasExtra("moleId")) {
                        int id = extras.getInt("moleId");
                        MoleController controller = new MoleController(this);
                        controller.getMoleFromId(id);
                        mole = controller.getMole();
                }
                if (intent.hasExtra("pictureId")) {
                        int id = extras.getInt("pictureId");
                        picture = PictureController.getPictureFromId(id);
                }
                setViews();
                addListeners();
                updateSelf();
        }
	
	@Override
	protected void setViews() {
		setContentView(R.layout.search);
		
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
