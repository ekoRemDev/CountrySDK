package com.riontech.xtensolution.sample.view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.riontech.xtensolution.sample.R;
import com.xtensolutions.country.model.Country;


/**
 * Created by Riontech
 * Vaghela Mithun R.
 * vaghela.mithun@gmail.com,
 * 9727206702
 * on 02-May-18.
 */

public class CountryDetailsView {
    private static final String TAG = CountryDetailsView.class.getSimpleName();

    public void setUpDetailsView(Country country, View view){
        try {
            TextView txtAreaInSqKm = view.findViewById(R.id.txtLabelAreaInSqKmValue);
            TextView txtCapital = view.findViewById(R.id.txtLabelCapitalValue);
            TextView txtContinent = view.findViewById(R.id.txtLabelContinentValue);
            TextView txtContinentName = view.findViewById(R.id.txtLabelContinentNameValue);
            TextView txtCountryCode = view.findViewById(R.id.txtLabelCountryCodeValue);
            TextView txtCurrency = view.findViewById(R.id.txtLabelCurrencyValue);
            TextView txtPopulation = view.findViewById(R.id.txtLabelPopulationValue);
            TextView txtLanguages = view.findViewById(R.id.txtLabelLanguagesValue);
            TextView txtDialCode = view.findViewById(R.id.txtLabelDialCodeValue);

            txtAreaInSqKm.setText(country.getAreaInSqKm());
            txtCapital.setText(country.getCapital());
            txtContinent.setText(country.getContinent());
            txtContinentName.setText(country.getContinentName());
            txtCountryCode.setText(country.getCountryCode());
            txtCurrency.setText(country.getCurrencyCode());
            txtDialCode.setText(country.getDialCode());
            txtLanguages.setText(country.getLanguages());
            txtPopulation.setText(country.getPopulation());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
