package com.example.pizza.pizzeria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Datos extends SQLiteOpenHelper {

    public Datos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pizzeria (clave integer primary key unique, cliente text, direccion text, telefono text, pedido text) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists pizzeria");
        db.execSQL("create table pizzeria (clave integer primary key unique, cliente text, direccion text, telefono text, pedido text) ");
    }
}