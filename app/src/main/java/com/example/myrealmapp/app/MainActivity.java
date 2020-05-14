package com.example.myrealmapp.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myrealmapp.R;
import com.example.myrealmapp.entity.Books;
import com.example.myrealmapp.ui.BookAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Realm realm;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    private MutableLiveData<String> queryResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryResult = new MutableLiveData<>();
        searchView = findViewById(R.id.searchView);
        realm = Realm.getDefaultInstance();

        // La Lista de books
        getAllBooks();

        // La barra de busqueda
        search();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (bookAdapter != null){
            bookAdapter.notifyDataSetChanged();
        }
    }

    private void getAllBooks(){

        // Aqui enctramos todos los resultado de book.
        RealmResults<Books> results = realm.where(Books.class).findAll();

        recyclerView = findViewById(R.id.my_book_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        bookAdapter = new BookAdapter(MainActivity.this, realm, results);
        recyclerView.setAdapter(bookAdapter);



        queryResult.observe(this, new Observer<String>() {
            @Override
            public void onChanged(final String query) {

                // TODO cambiar esto en la version 2
                RealmResults<Books> results = realm.where(Books.class).contains("bookName", query).findAll();
                bookAdapter = new BookAdapter(MainActivity.this, realm, results);
                recyclerView.setAdapter(bookAdapter);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    // Barra de busqueda

    private void search() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryResult.setValue(newText);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.setting_menu:
                Toast.makeText(MainActivity.this, "Setting Menu", Toast.LENGTH_LONG).show();
                break;

            case R.id.insert_menu:
                startActivity(new Intent(MainActivity.this, AddActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
