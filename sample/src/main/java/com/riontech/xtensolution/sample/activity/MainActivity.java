package com.riontech.xtensolution.sample.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.view.View;
import android.widget.ImageView;

import com.riontech.xtensolution.sample.R;
import com.riontech.xtensolution.sample.adapter.CountryAdapter;
import com.xtensolutions.country.model.Country;
import com.xtensolutions.country.utils.CountryUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtList:
                CountryListActivity.startListActivity(MainActivity.this);
                break;

            case R.id.txtDialog:
                CountryDialogActivity.startDialogActivity(MainActivity.this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbarView();
        findViewById(R.id.txtList).setOnClickListener(this);
        findViewById(R.id.txtDialog).setOnClickListener(this);
    }

    private void setUpToolbarView() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
