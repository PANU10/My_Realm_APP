package com.example.myrealmapp.database;

import android.app.Application;

import com.example.myrealmapp.ui.Migration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        // Aplicar la configuración por defecto
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("books.realm")
                .schemaVersion(1)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
