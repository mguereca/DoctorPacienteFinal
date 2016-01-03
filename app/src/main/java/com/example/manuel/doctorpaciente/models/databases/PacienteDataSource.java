package com.example.manuel.doctorpaciente.models.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.doctorpaciente.models.Paciente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 16/12/2015.
 */
public class PacienteDataSource {

    public static final String TABLE_NAME = "pacientes";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String PATERNO = "paterno";
    public static final String MATERNO = "materno";
    public static final String TELEFONO = "telefono";

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String CREATE_TABLE = "create table "+TABLE_NAME
            + " (" + ID + " integer primary key autoincrement,"
            + NOMBRE + " TEXT NOT NULL," + PATERNO + " TEXT NOT NULL,"
            + MATERNO + " TEXT NOT NULL,"
            + TELEFONO + " TEXT NOT NULL)";


    public PacienteDataSource(Context context){

        dbHelper = new DBHelper(context);

    }

    public List<Paciente> mostrarPacientes(){
        List<Paciente> pacientes = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO,TELEFONO};
        Cursor cursor = database.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Paciente paciente = new Paciente();
                paciente.setId(cursor.getInt(0));
                paciente.setNombre(cursor.getString(1));
                paciente.setPaterno(cursor.getString(2));
                paciente.setMaterno(cursor.getString(3));
                paciente.setTelefono(cursor.getString(4));
                pacientes.add(paciente);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return pacientes;
    }

    //Para el spinner

    public List<Paciente> spinnerPacientes(){
        List<Paciente> pacientes = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO};
        Cursor cursor = database.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Paciente paciente = new Paciente();
                paciente.setId(cursor.getInt(0));
                paciente.setNombre_completo(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                pacientes.add(paciente); //ArrayList

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return pacientes;

    }

    public void insertarPacientes(Paciente paciente){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,paciente.getNombre());
        valores.put(PATERNO,paciente.getPaterno());
        valores.put(MATERNO, paciente.getMaterno());
        valores.put(TELEFONO, paciente.getTelefono());
        database.insert(TABLE_NAME, null, valores);
        database.close();
    }

    public int obtenerID(List<Paciente> pacientes,int posicion){
        return pacientes.get(posicion).getId();
    }

    public void eliminarPaciente(int id){
        database = dbHelper.getWritableDatabase();
        database.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
        database.close();
    }

    //Ultima Clase

    public Cursor obtenerPaciente(int id){
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO,TELEFONO};
        Cursor cursor = database.query(TABLE_NAME,columnas,"ID=?",
                new String[]{String.valueOf(id)},null,null,null);
        return cursor;
    }

    public void actualizarPaciente(Paciente paciente,int id){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,paciente.getNombre());
        valores.put(PATERNO,paciente.getPaterno());
        valores.put(MATERNO,paciente.getMaterno());
        valores.put(TELEFONO, paciente.getTelefono());
        database.update(TABLE_NAME, valores, "ID=?", new String[]{String.valueOf(id)});
        database.close();
    }



}
