package com.example.myrealmapp.ui;

import java.util.jar.Attributes;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        // TODO Version Antigua
//        public class Books extends RealmObject {
//
//            private String bookId;
//            private String bookName;
//            private String bookAutorName;
//            private Double bookPrice;


        // TODO Version Nueva


//        public class Books extends RealmObject {

//            private String bookId;
//            private String bookName;
//            private String bookAutorName;
//            private String bookAutroSurname;

        // En La version antigua no habia el field surname, En esta nueva version vamos a añadirlo y borrar le field bookprice.
        if (oldVersion == 0) {
            RealmObjectSchema booksSchema = schema.get("Books");
            booksSchema
                    .addField("bookAutroSurname", String.class, FieldAttribute.REQUIRED)
                    .removeField("bookPrice");
            oldVersion++;
        }
    }
}
