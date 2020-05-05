package com.example.myrealmapp.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myrealmapp.R;
import com.example.myrealmapp.entity.Books;

import io.realm.Realm;
import io.realm.RealmResults;


public class EditActivity extends AppCompatActivity {

    private EditText book_name, author_name, book_price;
    private Realm myRealm;
    private Bundle bundle;
    private int position;
    private Books books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bundle = getIntent().getExtras();
        if (bundle != null)
            position = bundle.getInt("position");

        book_name = findViewById(R.id.edit_book_name_edit_text);
        author_name = findViewById(R.id.edit_author_name_edit_text);
        book_price = findViewById(R.id.edit_book_price_edit_text);

        myRealm = Realm.getDefaultInstance();

        RealmResults<Books> realmResults = myRealm.where(Books.class).findAll();
        books = realmResults.get(position);
        setupViews(books);
    }

    private void setupViews(Books books) {
        book_name.setText(books.getBookName());
        author_name.setText(books.getBookAutor());
        book_price.setText("" + books.getBookPrice());
    }


    private void updateBooks() {

        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                books.setBookName(book_name.getText().toString().trim());
                books.setBookAutor(author_name.getText().toString().trim());
                books.setBookPrice(Double.parseDouble(book_price.getText().toString().trim()));
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                Toast.makeText(EditActivity.this,
                        "Edit Record Successfully ...", Toast.LENGTH_SHORT);
            }
        });

    }

    public void editBooks(View view) {
        updateBooks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealm.close();
    }
}
