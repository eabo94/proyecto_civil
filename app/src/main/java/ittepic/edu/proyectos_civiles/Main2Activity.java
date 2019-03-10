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

public class Main2Activity extends AppCompatActivity {

    BaseDatos base;
    EditText descripcion,ubicacion,fecha,presupuesto;
    Button insertar,reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base = new BaseDatos(this,"civil",null,1);
        setContentView(R.layout.activity_main2);
        descripcion=findViewById(R.id.descripcionI);
        ubicacion=findViewById(R.id.ubicacion2);
        fecha=findViewById(R.id.fechaI);
        reg=findViewById(R.id.regresar);
        presupuesto=findViewById(R.id.presupuestoI);
        insertar=findViewById(R.id.inserta);

    insertar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            insertarPro();
        }
    });
    reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(v.getContext(),MainActivity.class);
            startActivityForResult(intent,0);

        }
    });

    }
    public void insertarPro(){
        try {
            SQLiteDatabase inser = base.getWritableDatabase();
            String SQL="INSERT INTO PROYECTOS VALUES(NULL,'%1','%2','%3','%4')";

            SQL=SQL.replace("%1",descripcion.getText().toString());
            SQL=SQL.replace("%2",ubicacion.getText().toString());
            SQL=SQL.replace("%3",fecha.getText().toString());
            SQL=SQL.replace("%4",presupuesto.getText().toString());


            inser.execSQL(SQL);
            inser.close();
            descripcion.setText("");
            ubicacion.setText("");
            fecha.setText("");
            presupuesto.setText("");
            mensaje("Exito","Se pudo insertar");


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
