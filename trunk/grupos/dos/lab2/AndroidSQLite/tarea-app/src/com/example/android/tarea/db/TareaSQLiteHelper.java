package com.example.android.tarea.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class TareaSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE TAREA (codigo INTEGER primary key autoincrement, titulo TEXT, descripcion TEXT)";
 
    public TareaSQLiteHelper(Context contexto, String nombre,
                               CursorFactory factory, int version) {
    	
        super(contexto, nombre, factory, version);
    }
    /**
     * Se ejecuta cuandola BD es creada por primera vez
     * 
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        
        db.execSQL(sqlCreate);
    }
 
    /**      
     * Se invoca a este metodo cuando la BD necesita ser actualizada.
     * Este metodo deberia ser usado para borrar, agregar tablas o hacer cualquier
     * otra cosa que se necesite para alterar el schema de la bd     
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
 
        //Eliminamos versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS TAREA");
        
        //Creamos la tabla TAREA
        db.execSQL(sqlCreate);
    }
    

}
