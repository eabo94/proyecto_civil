package ittepic.edu.proyectos_civiles;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;

public class Main3Activity extends AppCompatActivity {
     EditText idodes;
     Button consultar,cerrar,eliminar;

     BaseDatos base;
     TextView descripcion,ubica,fecha,presu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        consultar=findViewById(R.id.consultar);
        base= new BaseDatos(this,"civil",null,1);
        descripcion=findViewById(R.id.desc);
        ubica=findViewById(R.id.ubicar);
        cerrar=findViewById(R.id.cerrar);
        fecha=findViewById(R.id.fecha);
        presu=findViewById(R.id.presu);
        eliminar=findViewById(R.id.eliminar);

        idodes=findViewById(R.id.Idodes);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDatos base= new BaseDatos(getApplicationContext(),"civil",null,1);
                String  buscar= idodes.getText().toString();
                String[] datos;
               datos =base.buscar_P(buscar);
               descripcion.setText(datos[1]);
               ubica.setText(datos[2]);
               fecha.setText(datos[3]);
               presu.setText(datos[4]);
              // datosrecuperados.setText(datos[2]);
              // datosrecuperados.setText(datos[3]);
               //datosrecuperados.setText(datos[4]);
               Toast.makeText(getApplicationContext(),datos[5],Toast.LENGTH_SHORT).show();



            }
        });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),MainActivity.class);
                startActivityForResult(intent,0);
            }
        });
    eliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String id= idodes.getText().toString();
            String  mensaje= base.eliminarPro(id);
            descripcion.setText("");
            ubica.setText("");
            fecha.setText("");
            presu.setText("");
            idodes.setText("");
            Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
        }
    });

    }

}
