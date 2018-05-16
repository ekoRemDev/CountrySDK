package com.riontech.xtensolution.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.riontech.xtensolution.sample.R;
import com.riontech.xtensolution.sample.view.CountryDetailsView;
import com.xtensolutions.country.model.Country;

import java.util.Locale;

/**
 * Created by Riontech
 * Vaghela Mithun R.
 * vaghela.mithun@gmail.com,
 * 9727206702
 * on 28-Apr-18.
 */

public class CountryDetailsActivity extends AppCompatActivity {
    private static final String COUNTRY = "country";
    private static final String TAG = CountryDetailsActivity.class.getSimpleName();
    private CountryDetailsView countryDetailsView;
    private View rlRootView;

    public static void startDetailActivity(Context context, Country country) {
        Intent intent = new Intent(context, CountryDetailsActivity.class);
        intent.putExtra(COUNTRY, country);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        rlRootView = findViewById(R.id.rootView);
        Country country = (Country) getIntent().getSerializableExtra(COUNTRY);
        setUpToolbarView(country);

        countryDetailsView = new CountryDetailsView();
        countryDetailsView.setUpDetailsView(country, rlRootView);

    }

    private void setUpToolbarView(Country country) {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle(country.getCountryName());

            ImageView imgFlag = findViewById(R.id.image);
            imgFlag.setImageResource(country.getLargeFlag(this));
//            Glide.with(this).load(country.getLargeFlag(this)).into(imgFlag);

            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
