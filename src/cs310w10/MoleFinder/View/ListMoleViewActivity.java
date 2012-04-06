package cs310w10.MoleFinder.View;

import java.util.ArrayList;

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
        implements OnItemClickListener
{

    ImageButton        trashButton;
    ImageButton        addButton;
    ListView           moleListView;
    ListMoleController lController;
    SimpleAdapter      adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        lController = MoleFinderApplication.getListMoleController();
        setAdapter();
    }

    private void setAdapter()
    {

        ArrayList<Mole> moleList = lController.getMoles();

        adapter = new SimpleAdapter(this, new MoleMapListAdapter(moleList),
                R.layout.mole_list_item, new String[] { "name",
                /* "description", */
                "id", "location" }, new int[] { R.id.MoleListItemName,
                /* R.id.MoleListItemDescription, */
                R.id.MoleListItemId, R.id.MoleListItemLocation });
        moleListView.setAdapter(adapter);
        registerForContextMenu(moleListView);

    }

    @Override
    protected void setViews()
    {

        setContentView(R.layout.list_mole);

        trashButton = (ImageButton) findViewById(R.id.ListMoleViewTrashButton);
        addButton = (ImageButton) findViewById(R.id.ListMoleViewAddButton);
        moleListView = (ListView) findViewById(R.id.ListMoleViewMoleList);
    }

    @Override
    protected void addListeners()
    {

        addButton.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {

                pressAddButton();
            }
        });

        trashButton.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {

                pressTrashButton();
            }
        });
        moleListView.setOnItemClickListener(this);
    }

    protected void pressTrashButton()
    {

        // TODO: Add confirmation dialog
        lController.deleteAllMoles();
        Toast.makeText(getBaseContext(), "Database purged", Toast.LENGTH_SHORT)
                .show();
        setAdapter();
    }

    protected void pressAddButton()
    {

        this.mole = null;
        this.picture = null;
        this.launchEditMole();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            setAdapter();
        }

    }

    @Override
    public void onResume()
    {

        super.onResume();
        setAdapter();
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id)
    {

        // Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        launchViewMole((int) id);
    }

    @Override
    protected void updateSelf()
    {

    }

    public void update(ArrayList<Mole> mole)
    {

    }

}
