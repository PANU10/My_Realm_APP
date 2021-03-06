package com.example.myrealmapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myrealmapp.R;
import com.example.myrealmapp.app.EditActivity;
import com.example.myrealmapp.entity.Books;

import io.realm.Realm;
import io.realm.RealmResults;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.Holders>{

    private Context context;
    private Realm realm;
    private RealmResults<Books> realmResults;
    private LayoutInflater inflater;

    public BookAdapter(Context context, Realm realm, RealmResults<Books> realmResults) {
        this.context = context;
        this.realm = realm;
        this.realmResults = realmResults;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public Holders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_list_layout, parent, false);
        Holders holders = new Holders(view);

        return holders;
    }

    @Override
    public void onBindViewHolder(@NonNull Holders holder, int position) {
        Books books = realmResults.get(position);
        holder.getBooks(books, position);
        holder.setListener();
    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

    public class Holders extends RecyclerView.ViewHolder{

        private int position;
        private TextView book_name, author_name, author_surname;
        private ImageView editImage, deleteImageBook;

        public Holders(@NonNull View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.book_name_text_view);
            author_name = itemView.findViewById(R.id.author_name_text_view);
            author_surname = itemView.findViewById(R.id.author_surname_text_view);
            editImage = itemView.findViewById(R.id.edit_image_view);
            deleteImageBook = itemView.findViewById(R.id.delete_image_view);

        }

        public void getBooks(Books books, int position) {

            this.position = position;
            String name = books.getBookName();
            String authorName = books.getBookAutorName();
            String autorSurname = books.getBookAutroSurname();

            book_name.setText(name);
            author_name.setText("NameAuthor -> " + authorName);
            author_surname.setText("SurnameAuthor -> " + autorSurname);
        }

        public void setListener() {

            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);

                }
            });


            // Eliminar Books

            deleteImageBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realmResults.deleteFromRealm(position);

                            Toast.makeText(context,"Delete Record Successfully",Toast.LENGTH_SHORT).show();

                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, realmResults.size());
                        }
                    });
                }
            });

        }
    }
}
