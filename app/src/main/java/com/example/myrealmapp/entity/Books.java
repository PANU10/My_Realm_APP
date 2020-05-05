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
    @Required
    private Double bookPrice;


    public Books() {
    }

    public Books(String bookId, String bookName, String bookAutor, Double bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAutor = bookAutor;
        this.bookPrice = bookPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAutor() {
        return bookAutor;
    }

    public void setBookAutor(String bookAutor) {
        this.bookAutor = bookAutor;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
