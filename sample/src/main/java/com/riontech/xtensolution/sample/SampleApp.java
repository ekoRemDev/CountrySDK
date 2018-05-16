package com.riontech.xtensolution.sample;

import android.app.Application;
import android.content.res.Resources;

import com.xtensolutions.country.CountrySDK;

/**
 * Created by Riontech
 * Vaghela Mithun R.
 * vaghela.mithun@gmail.com,
 * 9727206702
 * on 13-Mar-18.
 */

public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CountrySDK.initialize(this);
    }
}
