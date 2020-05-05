package com.example.myrealmapp.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
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

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Realm realm;
    private RealmResults<Books> bookRealmResults;
    private BookAdapter bookAdapter;
    private List<Books> filteredBookList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        getAllBooks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bookAdapter != null){
            bookAdapter.notifyDataSetChanged();
        }
    }

    private void getAllBooks(){
        RealmResults<Books> results = realm.where(Books.class).findAll();

        recyclerView = findViewById(R.id.my_book_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        bookAdapter = new BookAdapter(MainActivity.this, realm, results);
        recyclerView.setAdapter(bookAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
  //      final MenuItem menuTtem = menu.findItem(R.id.search_bar);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuTtem);
//        search(searchView);
        return true;
    }


    // Barra de busqueda

//    private void showAllPersons() {
//        bookRealmResults = realm.where(Books.class).findAll();
//        setAdapter(colleagueResult);
//        bookAdapter.notifyDataSetChanged();
//    }
//    private void setAdapter(RealmResults<Books> bookRealmResults) {
//
//        bookAdapter = new BookAdapter(this, realm.where(BookAdapter.class).findAllSortedAsync("id"),this);
//        recyclerView.setAdapter(adapter);
//        bookAdapter.notifyDataSetChanged();
//    }

//    private List<Books> filter(List<Books> booksList, String query) {
//        query = query.toLowerCase();
//
//        final List<Books> filteredBookList = new ArrayList<>();
//
//        for (Books book : booksList) {
//            final String text = book.getBookName().toLowerCase();
//            if (text.contains(query)) {
//                filteredBookList.add(book);
//            }
//        }
//        return filteredBookList;
//    }
//
//
//    private void search(SearchView searchView) {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                filteredBookList = filter(bookRealmResults, newText);
//
//                if (filteredBookList.size() > 0) {
//                    return true;
//                } else {
//                    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//            }
//        });
//    }


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
