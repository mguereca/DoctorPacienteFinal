package com.example.manuel.doctorpaciente.models.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.doctorpaciente.models.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 16/12/2015.
 */
public class DoctorDataSource {

    public static final String TABLE_NAME = "doctores";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String PATERNO = "paterno";
    public static final String MATERNO = "materno";
    public static final String ESPECIALIDAD = "especialidad";
    public static final String CONSULTORIO = "consultorio";

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String CREATE_TABLE = "create table "+ TABLE_NAME + " (" + ID + " integer primary key autoincrement,"
            + NOMBRE + " TEXT NOT NULL," + PATERNO + " TEXT NOT NULL,"
            + MATERNO + " TEXT NOT NULL," + ESPECIALIDAD + " TEXT NOT NULL,"
            + CONSULTORIO + " TEXT NOT NULL)";


    public DoctorDataSource(Context context){

        dbHelper = new DBHelper(context);
        //database = dbHelper.getWritableDatabase();
    }

    public List<Doctor> mostrarDoctores(){
        List<Doctor> doctores = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO,ESPECIALIDAD,CONSULTORIO};
        Cursor cursor = database.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(0));
                doctor.setNombre(cursor.getString(1));
                doctor.setPaterno(cursor.getString(2));
                doctor.setMaterno(cursor.getString(3));
                doctor.setEspecialidad(cursor.getString(4));
                doctor.setConsultorio(cursor.getString(5));
                doctores.add(doctor); //ArrayList

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return doctores;
    }

    //Para el spinner

    public List<Doctor> spinnerDoctores(){
        List<Doctor> doctores = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO};
        Cursor cursor = database.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Doctor doctor = new Doctor();
                doctor.setId(cursor.getInt(0));
                doctor.setNombre_completo(cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                doctores.add(doctor); //ArrayList

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return doctores;

    }

    public void insertarDoctor(Doctor doctor){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,doctor.getNombre());
        valores.put(PATERNO,doctor.getPaterno());
        valores.put(MATERNO,doctor.getMaterno());
        valores.put(ESPECIALIDAD, doctor.getEspecialidad());
        valores.put(CONSULTORIO, doctor.getConsultorio());
        database.insert(TABLE_NAME, null, valores);
        database.close();
    }

    public int obtenerID(List<Doctor> doctores,int posicion){
        return doctores.get(posicion).getId();
    }

    public void eliminarDoctor(int id){
        database = dbHelper.getWritableDatabase();
        database.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
        database.close();
    }


    public Cursor obtenerDoctor(int id){
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,NOMBRE,PATERNO,MATERNO,ESPECIALIDAD,CONSULTORIO};
        Cursor cursor = database.query(TABLE_NAME,columnas,"ID=?",
                new String[]{String.valueOf(id)},null,null,null);
        return cursor;
    }

    public String nombreDoctor(int id){
        String nombre = new String();
        database = dbHelper.getWritableDatabase();
        String[] columnas = new String[]{NOMBRE,PATERNO,MATERNO};
        Cursor cursor = database.query(TABLE_NAME,columnas,"ID=?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor.moveToFirst()){
            nombre = cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2);
        }
        cursor.close();
        return nombre;
    }

    public void actualizarDoctor(Doctor doctor,int id){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(NOMBRE,doctor.getNombre());
        valores.put(PATERNO,doctor.getPaterno());
        valores.put(MATERNO,doctor.getMaterno());
        valores.put(ESPECIALIDAD, doctor.getEspecialidad());
        valores.put(CONSULTORIO,doctor.getConsultorio());
        database.update(TABLE_NAME, valores, "ID=?", new String[]{String.valueOf(id)});
        database.close();
    }


}
