/**
 * 
 */
package cs310w10.MoleFinder.View;

import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

/**
 * @author ryalls
 * 
 */
public class SettingsViewActivity extends PreferenceActivity
{

    private CheckBoxPreference checkboxPassword;
    private EditTextPreference editPassword;
    private EditTextPreference editDeleteAllMoles;
    private boolean            passwordEnabled;
    private String             password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        checkboxPassword = (CheckBoxPreference) findPreference("checkboxPassword");
        editPassword = (EditTextPreference) findPreference("editPassword");
        editDeleteAllMoles = (EditTextPreference) findPreference("editDeleteAllMoles");

        checkboxPassword
                .setOnPreferenceChangeListener(new OnPreferenceChangeListener()
                {

                    public boolean onPreferenceChange(Preference preference,
                            Object newValue)
                    {

                        if (newValue.toString().equals("false"))
                        {
                            editPassword.setText("");
                        }

                        return true;
                    }
                });

        editDeleteAllMoles
                .setOnPreferenceChangeListener(new OnPreferenceChangeListener()
                {

                    public boolean onPreferenceChange(Preference preference,
                            Object newValue)
                    {

                        if (newValue.toString().equals(
                                "I want to delete all my moles!"))
                        {
                            MoleFinderApplication.getListMoleController()
                                    .deleteAllMoles();
                            Toast.makeText(getBaseContext(),
                                    "Mole data deleted.", Toast.LENGTH_LONG)
                                    .show();

                            return true;
                        }

                        return false;
                    }
                });
    }

}
