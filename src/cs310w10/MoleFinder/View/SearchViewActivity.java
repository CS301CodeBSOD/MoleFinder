package cs310w10.MoleFinder.View;

<<<<<<< HEAD
import cs310w10.MoleFinder.Model.Mole;
import android.app.Activity;
=======
import java.util.ArrayList;

import cs310w10.MoleFinder.Controller.ListMoleController;
import cs310w10.MoleFinder.Controller.MoleController;
import cs310w10.MoleFinder.Controller.MoleFinderApplication;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Mole;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchViewActivity extends ViewActivity<ArrayList<Mole>> implements
        OnItemClickListener
{

    private ImageButton searchButton;
    private ListView    resultsListView;
    ListMoleController  lController;
    SimpleAdapter       adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        lController = MoleFinderApplication.getListMoleController();
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        handleIntent(intent);

    }

    @Override
    protected void setViews()
    {

        setContentView(R.layout.search);
        resultsListView = (ListView) findViewById(R.id.SearchViewResultsList);
        searchButton = (ImageButton) findViewById(R.id.SearchViewSearchButton);
    }

    @Override
    protected void addListeners()
    {

        searchButton.setOnClickListener(new OnClickListener()
        {

            public void onClick(View v)
            {

                pressSearchButton();
            }
        });

        resultsListView.setOnItemClickListener(this);

    }

    private void handleIntent(Intent intent)
    {

        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    @Override
    protected void onNewIntent(Intent intent)
    {

        setIntent(intent);
        handleIntent(intent);
    }

    private void doMySearch(String query)
    {

        ArrayList<Mole> moleList = lController.getMoles();
        ArrayList<Mole> results = new ArrayList<Mole>();
        for (Mole mole : moleList)
        {
            if (mole.getLocation().toLowerCase().indexOf(query.toLowerCase()) != -1)
            {
                results.add(mole);
            }
        }

        update(results);

    }

    public void update(ArrayList<Mole> model)
    {

        adapter = new SimpleAdapter(this, new MoleMapListAdapter(model),
                R.layout.mole_list_item, new String[] { "name",
                /* "description", */
                "id", "location" }, new int[] { R.id.MoleListItemName,
                /* R.id.MoleListItemDescription, */
                R.id.MoleListItemId, R.id.MoleListItemLocation });
        resultsListView.setAdapter(adapter);
        // registerForContextMenu(resultsListView);

    }

    private void pressSearchButton()
    {

        onSearchRequested();
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id)
    {

        launchViewMole((int) id);
    }

    @Override
    protected void updateSelf()
    {

        // TODO Auto-generated method stub

    }
}
