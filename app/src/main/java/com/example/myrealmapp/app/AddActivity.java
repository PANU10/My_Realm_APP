package com.example.myrealmapp.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myrealmapp.R;
import com.example.myrealmapp.entity.Books;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class AddActivity extends AppCompatActivity {

    private EditText bookName, authorName, authorSurname;
    private Realm myRealm;
    private RealmAsyncTask realmAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bookName = findViewById(R.id.book_name_edit_text);
        authorName = findViewById(R.id.author_name_edit_text);
        authorSurname = findViewById(R.id.author_surname_edit_text);

        myRealm = Realm.getDefaultInstance();
    }

    private void insertRecords(){

        final String id = UUID.randomUUID().toString();
        final String book_name = bookName.getText().toString().trim();
        final String author_name = authorName.getText().toString().trim();
        final String author_surname = authorSurname.getText().toString().trim();

        if (book_name.isEmpty()) {
           Toast.makeText(AddActivity.this, "Enter book name ... " , Toast.LENGTH_LONG).show();
           return;
        }

        if (author_name.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter autor name ... " , Toast.LENGTH_LONG).show();
            return;
        }
        if (author_surname.isEmpty()) {
            Toast.makeText(AddActivity.this, "Enter autor surname ... " , Toast.LENGTH_LONG).show();
            return;
        }

        realmAsyncTask = myRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Books books = realm.createObject(Books.class, id);
                books.setBookName(book_name);
                books.setBookAutorName(author_name);
                books.setBookAutroSurname(author_surname);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(AddActivity.this, "Insert Record Successfully" , Toast.LENGTH_LONG).show();
                bookName.setText("");
                authorName.setText("");
                authorSurname.setText("");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                Toast.makeText(AddActivity.this, "Error Is Insert Record ..."
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addBooks(View view) {
        insertRecords();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (realmAsyncTask != null && realmAsyncTask.isCancelled()) {
            realmAsyncTask.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealm.close();
    }


}
