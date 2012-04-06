package cs310w10.MoleFinder.View;

import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cs310w10.MoleFinder.Model.Picture;

public class MainViewActivity extends ViewActivity<Picture> {
	private Button addMoleButton;
	private Button viewMolesButton;
	private Button searchLocationButton;
	private Button settingsButton;
	private boolean passwordEnabled;
	private String password;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

	    super.onCreate(savedInstanceState);

	    getPrefs();
	    checkPassword();
	    
	}
	
	@Override
	protected void setViews() {
		setContentView(R.layout.main);

		addMoleButton = (Button) findViewById(R.id.MainViewAddMoleButton);
		viewMolesButton = (Button) findViewById(R.id.MainViewViewMolesButton);
		searchLocationButton = (Button) findViewById(R.id.MainViewSearchLocationButton);
		settingsButton = (Button) findViewById(R.id.MainViewSettingsButton);
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
		
		settingsButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                            pressSettingsButton();
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
		Intent intent = new Intent(this, EditMoleViewActivity.class);
		startActivity(intent);
	}
	
	public void pressSettingsButton() {
	    Intent intent = new Intent(this, SettingsViewActivity.class);
            startActivity(intent);
            
	}

	private void getPrefs(){
	    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
	    passwordEnabled = settings.getBoolean("checkboxPassword", false);
	    password = settings.getString("editPassword", "");
	   
	}
	
	private void checkPassword(){
	    if (passwordEnabled && !(password.equals(""))){	      
	      showDialog(0);	        
	    }
	    
	}
	
	protected Dialog onCreateDialog(int id) {
	    AlertDialog.Builder alert = new AlertDialog.Builder(this);

	    alert.setTitle("Enter your Password");
	    alert.setCancelable(false);

	    // Set an EditText view to get user input 
	    final EditText input = new EditText(this);
	    alert.setView(input);

	    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int whichButton) {
	      String value = input.getText().toString();
	          if (value.equals(password)){
	              dialog.cancel();
	          }else{
	              MainViewActivity.this.finish();
	          }
	      }
	    });

	    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int whichButton) {
	        MainViewActivity.this.finish();
	      }
	    });

	    return alert.show();
            
	}
	
	public void update(Picture model) {
		// doesn't do anything since the main view doesn't actually display any
		// models
	}

	@Override
	protected void updateSelf() {
		// doesn't do anything since the main view doesn't actually display any
		// models
	}

}