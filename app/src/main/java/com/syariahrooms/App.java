package com.syariahrooms;

import android.app.Application;

import com.syariahrooms.data.Repository;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        repository = new Repository(getApplicationContext());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Comfortaa-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }

    public Repository getRepository() {
        if (repository == null) repository = new Repository(getApplicationContext());
        return repository;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        repository.destroy();
    }
}
