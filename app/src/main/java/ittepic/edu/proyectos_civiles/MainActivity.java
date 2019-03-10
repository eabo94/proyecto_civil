package ittepic.edu.proyectos_civiles;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BaseDatos base;
    ListView lista;
    ArrayList <String> arreglolista;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista= findViewById(R.id.lista);
        base= new BaseDatos(getApplicationContext(),"civil",null,1);
        arreglolista= base.llenar_lista();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arreglolista);
        lista.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pop,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent intent2= new Intent(this,Main2Activity.class);
                startActivity(intent2);
                break;
            case R.id.consultar:
                Intent intent3= new Intent(this,Main3Activity.class);
                startActivity(intent3);
                break;
            case R.id.actualizar:
                Intent intent4= new Intent(this,Main4Activity.class);
                startActivity(intent4);
                break;

        }


        return super.onOptionsItemSelected(item);
    }


}
































