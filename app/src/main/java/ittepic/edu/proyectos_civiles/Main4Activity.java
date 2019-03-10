package ittepic.edu.proyectos_civiles;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText des,ubi,fecha,preu,idbuscar;
    BaseDatos baseDatos;
    Button guardar, cerrarAct, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        des=findViewById(R.id.descripcion);
        ubi=findViewById(R.id.ubicacion);
        fecha=findViewById(R.id.fecha);
        preu=findViewById(R.id.presupuesto);
        idbuscar=findViewById(R.id.buscar);
        guardar=findViewById(R.id.guardar);
        cerrarAct=findViewById(R.id.Cerrar);
        buscar=findViewById(R.id.btnbusca);
        baseDatos= new BaseDatos(this,"civil",null,1);
    buscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseDatos base= new BaseDatos(getApplicationContext(),"civil",null,1);
            String buscar= idbuscar.getText().toString();
            String [] datos;
            datos=base.buscar_P(buscar);
            des.setText(datos[1]);
            ubi.setText(datos[2]);
            fecha.setText(datos[3]);
            preu.setText(datos[4]);
            Toast.makeText(getApplicationContext(),datos[5],Toast.LENGTH_SHORT).show();

        }
    });
    cerrarAct.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),MainActivity.class);
            startActivityForResult(intent,0);
        }
    });

    /*guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // BaseDatos base= new BaseDatos(getApplicationContext(),"civil",null,1);
            baseDatos.actualiza(idbuscar.getText().toString(),des.getText().toString(),ubi.getText().toString(),fecha.getText().toString(),preu.getText().toString());
            Toast.makeText(getApplicationContext(),"Se ha modificado correctamente",Toast.LENGTH_SHORT).show();
        }
    });*/
    guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            actualizaPro();
        }
    });

    }


    public void actualizaPro(){
        try {

            SQLiteDatabase inser = baseDatos.getWritableDatabase();
            String SQL="UPDATE PROYECTOS SET DESCRIPCION='%1',UBICACION='%2',FECHA='%3',PRESUPUESTO='%4'WHERE IDPROYECTO ='%5'";

            SQL=SQL.replace("%1",des.getText().toString());
            SQL=SQL.replace("%2",ubi.getText().toString());
            SQL=SQL.replace("%3",fecha.getText().toString());
            SQL=SQL.replace("%4",preu.getText().toString());
            SQL=SQL.replace("%5",idbuscar.getText().toString());


            inser.execSQL(SQL);
            inser.close();
            des.setText("");
            ubi.setText("");
            fecha.setText("");
            preu.setText("");
            idbuscar.setText("");
            mensaje("Exito","Se actualizo correctamente");


        }catch (SQLiteException e){
            mensaje("Error de insercion",e.getMessage());

        }
    }

    private void mensaje(String titulo, String Mensaje) {
        AlertDialog.Builder a=new AlertDialog.Builder(this);
        a.setTitle(titulo).setMessage(Mensaje).setPositiveButton("Aceptar",null);
        a.show();
    }
}
