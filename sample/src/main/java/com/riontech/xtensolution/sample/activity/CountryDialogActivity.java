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
import com.xtensolutions.country.dialog.CountryDialog;
import com.xtensolutions.country.model.Country;
import com.xtensolutions.country.utils.CountryUtils;

import java.util.Locale;

/**
 * Created by Riontech
 * Vaghela Mithun R.
 * vaghela.mithun@gmail.com,
 * 9727206702
 * on 28-Apr-18.
 */

public class CountryDialogActivity extends AppCompatActivity{
    private static final String COUNTRY = "country";
    private static final String TAG = CountryDialogActivity.class.getSimpleName();
    private CountryDetailsView countryDetailsView;
    private View rlRootView;

    public static void startDialogActivity(Context context) {
        Intent intent = new Intent(context, CountryDialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        rlRootView = findViewById(R.id.rootView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.country_dialog_sample));

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        countryDetailsView = new CountryDetailsView();
        Country country = CountryUtils.getCountryByLocale(Locale.getDefault());
        setHeaderView(country);

        countryDetailsView.setUpDetailsView(country, rlRootView);
    }

    private void setHeaderView(Country country) {
        try {
            ImageView imvFlag = findViewById(R.id.imgFlag);
            TextView txtCountryName = findViewById(R.id.txtCountryName);

            try {
                imvFlag.setImageResource(country.getSmallFlag(imvFlag.getContext()));
                txtCountryName.setText(country.getCountryName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            findViewById(R.id.rlDialogHeader).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CountryDialog dialog = new CountryDialog();
                    dialog.setOnCountryClickListener(countryClickListener);
                    dialog.show(getSupportFragmentManager(), "Select Country");
                }
            });
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

    private CountryDialog.OnCountryClickListener countryClickListener = new CountryDialog.OnCountryClickListener() {
        @Override
        public void onCountrySelected(Country country) {
            setHeaderView(country);
            countryDetailsView.setUpDetailsView(country, rlRootView);
        }
    };
}
