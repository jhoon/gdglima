package com.example.android.tarea;


import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.android.tarea.db.TareaSQLiteHelper;
import com.example.android.tarea.domain.Tarea;

public class TareaActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        insertarTareasFake();
        listarTareas();
        
        actualizarTareasFake();
        listarTareas();
        
    }

	private void insertarTareasFake() {
		TareaSQLiteHelper tareaSqlite = new TareaSQLiteHelper(this, "DBTarea", null, 1); 
        SQLiteDatabase db = tareaSqlite.getWritableDatabase();
 
        //varificar si hemos abierto correctamente la base de datos
        if(db != null)
        {
        	tareaSqlite.onUpgrade(db, 1, 1);
        	
        	String titulo = "Charla de Android y SQLite ";
    		String descripcion = "buscar informacion\n Hacer resumen\n Preparar ejm\n ";
    		
    		StringBuilder query = new StringBuilder("");
    		query.append("INSERT INTO TAREA ( titulo, descripcion) VALUES (")
    		.append("'").append(titulo).append("','").append(descripcion).append("')");
    		
    		db.execSQL(query.toString());
    		
    		ContentValues rowTarea = new ContentValues();
    		rowTarea.put("titulo", "Pagar tarjeta");
    		rowTarea.put("descripcion", "Pagar tarjeta el 5 de mayo");
        	
    		db.insert("TAREA", null, rowTarea);
    		
    		
            //Cerramos la base de datos
            db.close();
        }
	}
	
	private void actualizarTareasFake() {
		TareaSQLiteHelper tareaSqlite = new TareaSQLiteHelper(this, "DBTarea", null, 1); 
        SQLiteDatabase db = tareaSqlite.getWritableDatabase();
 
        //varificar si hemos abierto correctamente la base de datos
        if(db != null)
        {
        	
    		ContentValues updateRegistro = new ContentValues();
    		updateRegistro.put("titulo", "Pagar tarjeta Visa");
    		updateRegistro.put("descripcion","Pagar tarjeta 3 de mayo");
    		   		
    		db.update("TAREA", updateRegistro, "codigo=? ", new String[] {"2"});
    		
            //Cerramos la base de datos
            db.close();
        }
	}

	private List<Tarea> listarTareas() {
		TareaSQLiteHelper tareaSqlite = new TareaSQLiteHelper(this, "DBTarea", null, 1); 
        SQLiteDatabase db = tareaSqlite.getWritableDatabase();
        
        String[] campos = new String[] {"codigo", "titulo","descripcion"};
                 
        Cursor c = db.query("Tarea", campos, null, null, null, null, null);
       
        List<Tarea>  tareas = new ArrayList<Tarea>();
        if (c.moveToFirst()) {
             
             do {
            	                   
                  Tarea tarea = new Tarea(c.getString(0), c.getString(1), c.getString(2));
                  tareas.add(tarea);
                  Log.w(TareaActivity.class.getName(),"codigo :"+tarea.getCodigo()+",titulo:"+tarea.getTitulo()+",descripcion:"+tarea.getDescripcion());
                  
             } while(c.moveToNext());
        }
        return tareas;
	}
}