package com.example.myrealmapp.database;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        // Aplicar la configuración por defecto
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("books.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
