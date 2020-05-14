package com.example.myrealmapp.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Books extends RealmObject {


    @PrimaryKey
    @Required
    private String bookId;
    @Required
    private String bookName;
    @Required
    private String bookAutor;

    // Migration version 1 : Añadir este campo y borrar el atributo bookprice
    @Required
    private String bookAutroSurname;


    // Migration version 2 : Juntar los dos campos NAME y SURNAME



    public Books() {
    }

    public Books(String bookId, String bookName, String bookAutorName, String bookAutroSurname) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAutor = bookAutorName;
        this.bookAutroSurname = bookAutroSurname;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAutorName() {
        return bookAutor;
    }

    public void setBookAutorName(String bookAutorName) {
        this.bookAutor = bookAutorName;
    }

    public String getBookAutroSurname() {
        return bookAutroSurname;
    }

    public void setBookAutroSurname(String bookAutroSurname) {
        this.bookAutroSurname = bookAutroSurname;
    }
}
