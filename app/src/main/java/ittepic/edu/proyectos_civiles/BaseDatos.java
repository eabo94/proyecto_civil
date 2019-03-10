package ittepic.edu.proyectos_civiles;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROYECTOS(IDPROYECTO INTEGER PRIMARY KEY AUTOINCREMENT,"+"DESCRIPCION VARCHAR(200)NOT NULL,"+"UBICACION VARCHAR(200),"+"FECHA DATE,"+"PRESUPUESTO FLOAT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList llenar_lista(){
        String mensaje;
        ArrayList <String> lista=new ArrayList<>();
        SQLiteDatabase database=this.getWritableDatabase();
        String  sql="SELECT * FROM PROYECTOS";
        Cursor c=database.rawQuery(sql,null);
        if (c.moveToFirst()){
            do {
                lista.add(c.getString(1));

            }while (c.moveToNext());
        }else {
            mensaje="no se tienen proyectos capturados";
            lista.add(mensaje);
        }

        return lista;
    }
    public  String[] buscar_P(String buscar){
        String [] datos= new String[6];
        SQLiteDatabase database= this.getWritableDatabase();
        String  sql="SELECT * FROM PROYECTOS WHERE IDPROYECTO ='"+buscar+"'";
        Cursor c= database.rawQuery(sql,null);
        if (c.moveToFirst()){
         for (int i=0;i<=4;i++){
             datos[i]=c.getString(i);

         }datos[5]="Datos encontrados";

        }else{
            datos[5]="No se encontraron datos de "+buscar;

        }
        return datos;

    }

    public String eliminarPro(String id) {
        String mensaje="";
        SQLiteDatabase database=this.getWritableDatabase();
        int catidad= database.delete("PROYECTOS","IDPROYECTO='"+id+"'",null);
        if (catidad!=0){

            mensaje="Eliminado correctamente";

        }else {
            mensaje="No se pudo eliminar";
        }
        database.close();
        return  mensaje;

    }
   /* public void actualiza(String buscar, String descripcion, String ubicacion, String fecha, String preupuesto){
        SQLiteDatabase db= getWritableDatabase();
        if (db!=null){
            db.execSQL("UPDATE PROYECTOS SET DESCRIPCION='"+descripcion+"',UBICACION'"+ubicacion+"',FECHA'"+fecha+"',PRESUPUESTO='"+preupuesto+"'WHERE IDPROYECTO ='"+buscar+"'");
            db.close();
        }
    }*/


}




































