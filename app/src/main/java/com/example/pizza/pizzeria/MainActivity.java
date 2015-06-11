package com.example.pizza.pizzeria;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText clave, cliente, direccion, telefono, pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clave = (EditText) findViewById(R.id.clave);
        cliente = (EditText) findViewById(R.id.cliente);
        direccion = (EditText) findViewById(R.id.direccion);
        telefono = (EditText) findViewById(R.id.telefono);
        pedido = (EditText) findViewById(R.id.pedido);
    }

    public void registrar (View v) {
        Datos admin = new Datos(this, "pizzeria", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave =  clave.getText().toString();
        String Cliente = cliente.getText().toString();
        String Direccion = direccion.getText().toString();
        String Telefono = telefono.getText().toString();
        String Pedido = pedido.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("clave", Clave);
        registro.put("cliente", Cliente);
        registro.put("direccion", Direccion);
        registro.put("telefono", Telefono);
        registro.put("pedido", Pedido);

        bd.insert("pizzeria", null, registro);
        bd.close();

        clave.setText("");
        cliente.setText("");
        direccion.setText("");
        telefono.setText("");
        pedido.setText("");

        Toast.makeText(this, "Se agrego un nuevo pedido al registro", Toast.LENGTH_SHORT).show();
    }

    public void buscar (View v) {

        Datos admin = new Datos(this, "pizzeria", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave = clave.getText().toString();

        Cursor fila = bd.rawQuery("select clave, cliente, direccion, telefono, pedido from pizzeria where clave=" + Clave, null);
        if (fila.moveToFirst()){
            clave.setText(fila.getString(0));
            cliente.setText(fila.getString(1));
            direccion.setText(fila.getString(2));
            telefono.setText(fila.getString(3));
            pedido.setText(fila.getString(4));
        }else {
            Toast.makeText(this, "No existe el pedido", Toast.LENGTH_SHORT).show();
        }bd.close();
    }



    public void eliminar (View v) {

        Datos admin = new Datos(this, "pizzeria", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave = clave.getText().toString();

        int cant = bd.delete("pizzeria", "clave=" + Clave, null);
        bd.close();

        clave.setText("");
        cliente.setText("");
        direccion.setText("");
        telefono.setText("");
        pedido.setText("");

        if (cant==1) {
            Toast.makeText(this, "Se elimino el pedido", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No existe el pedido", Toast.LENGTH_SHORT).show();
        }
    }


    public void modificar (View v) {
        Datos admin = new Datos(this, "pizzeria", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Clave =  clave.getText().toString();
        String Cliente = cliente.getText().toString();
        String Direccion = direccion.getText().toString();
        String Telefono = telefono.getText().toString();
        String Pedido = pedido.getText().toString();

        ContentValues registro= new ContentValues();

        registro.put("clave", Clave);
        registro.put("cliente", Cliente);
        registro.put("direccion", Direccion);
        registro.put("telefono", Telefono);
        registro.put("pedido", Pedido);

        int cant = bd.update("pizzeria", registro, "clave=" +  Clave, null);
        bd.close();

        if (cant==1){
            Toast.makeText(this, "Se modifico el pedido", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No existe el pedido", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar (View v) {
        clave.setText("");
        cliente.setText("");
        direccion.setText("");
        telefono.setText("");
        pedido.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.cerrar) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
