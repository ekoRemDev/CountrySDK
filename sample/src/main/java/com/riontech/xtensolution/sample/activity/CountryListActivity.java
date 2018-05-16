package com.riontech.xtensolution.sample.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.riontech.xtensolution.sample.R;
import com.riontech.xtensolution.sample.adapter.CountryAdapter;
import com.xtensolutions.country.model.Country;
import com.xtensolutions.country.utils.CountryUtils;

import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {

    private static final String TAG = CountryListActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private CountryAdapter adapter;

    private enum CURRENT_VIEW_MODE {LIST, GRID}

    private CURRENT_VIEW_MODE mode = CURRENT_VIEW_MODE.LIST;

    public static void startListActivity(Context context){
        context.startActivity(new Intent(context, CountryListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setUpToolbarView();
        recyclerView = findViewById(R.id.recyclerView);
        mode = CURRENT_VIEW_MODE.LIST;
        refreshRecyclerViewOnModeChange();
    }

    private void setUpToolbarView() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(getResources().getString(R.string.country_list_sample));
            setSupportActionBar(toolbar);

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (mode) {
            case GRID:
                menu.findItem(R.id.action_view_list).setVisible(false);
                menu.findItem(R.id.action_view_grid).setVisible(true);
                break;
            case LIST:
                menu.findItem(R.id.action_view_grid).setVisible(false);
                menu.findItem(R.id.action_view_list).setVisible(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_view_list:
                mode = CURRENT_VIEW_MODE.GRID;
                break;

            case R.id.action_view_grid:
                mode = CURRENT_VIEW_MODE.LIST;
                break;

            case android.R.id.home:
                onBackPressed();
                break;

        }
        invalidateOptionsMenu();
        refreshRecyclerViewOnModeChange();
        return super.onOptionsItemSelected(item);
    }

    private void refreshRecyclerViewOnModeChange() {
        CountryAdapter.VIEW_TYPE type = CountryAdapter.VIEW_TYPE.LIST;
        int divider = LinearLayoutManager.HORIZONTAL;
        switch (mode) {
            case GRID:
                type = CountryAdapter.VIEW_TYPE.GRID;
                divider = LinearLayoutManager.VERTICAL;
                manager = new GridLayoutManager(this, 3);
                break;
            case LIST:
                type = CountryAdapter.VIEW_TYPE.LIST;
                divider = LinearLayoutManager.HORIZONTAL;
                manager = new LinearLayoutManager(this);
                break;
        }

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, divider));
        ArrayList<Country> countries = CountryUtils.getAllCountry(this);
        adapter = new CountryAdapter(this, countries, type);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
