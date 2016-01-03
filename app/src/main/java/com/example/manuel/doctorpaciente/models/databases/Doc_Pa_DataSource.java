package com.example.manuel.doctorpaciente.models.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manuel.doctorpaciente.models.DocPa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 16/12/2015.
 */
public class Doc_Pa_DataSource {

    public static final String TABLE_NAME = "doc_pa";
    public static final String ID = "id";
    public static final String ID_DOCTOR = "id_doctor";
    public static final String ID_PACIENTE = "id_paciente";
    public static final String CITA = "cita";

    public static final String TABLE_DOC = "doctores";
    public static final String TABLE_PA = "pacientes";

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String CREATE_TABLE = "create table " +TABLE_NAME
            + " (" + ID +" integer primary key autoincrement,"
            + ID_DOCTOR + " integer," + ID_PACIENTE + " integer,"
            + CITA + " datetime" + ")";

    public Doc_Pa_DataSource(Context context){

        dbHelper = new DBHelper(context);
    }

    public List<DocPa> mostrarCitas(){
        List<DocPa> citas = new ArrayList<>();
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,ID_DOCTOR,ID_PACIENTE,CITA};
        Cursor cursor = database.query(TABLE_NAME, columnas, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                DocPa docPa = new DocPa();
                docPa.setId(cursor.getInt(0));
                docPa.setId_doctor(Integer.parseInt(cursor.getString(1)));
                docPa.setId_paciente(Integer.parseInt(cursor.getString(2)));
                docPa.setCita(cursor.getString(3));
                citas.add(docPa); //ArrayList

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        //Ya estan las citas en el arrayList
        return citas;
    }

    public  List<DocPa> mostrarCitas2(){
        List<DocPa> citas = new ArrayList<>();
        database = dbHelper.getReadableDatabase();

        String sqlQuery = "select dp.id, dp.cita, d.nombre, d.paterno, d.materno, p.nombre, p.paterno, p.materno from "
                +TABLE_NAME+" dp, "+TABLE_DOC+" d, "+TABLE_PA+" p where d.id = dp."+ID_DOCTOR+" and p.id = dp."+ID_PACIENTE;
        Cursor cursor = database.rawQuery(sqlQuery,null);
        if (cursor.moveToFirst()){
            do {
                DocPa docPa = new DocPa();
                docPa.setId(cursor.getInt(0));
                docPa.setCita(cursor.getString(1));
                docPa.setNombre_doctor(cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4));
                docPa.setNombre_paciente(cursor.getString(5)+" "+cursor.getString(6)+" "+cursor.getString(7));
                citas.add(docPa); //ArrayList

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();


        return citas;
    }

    public void insertarCitas(DocPa docPa){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ID_DOCTOR,docPa.getId_doctor());
        valores.put(ID_PACIENTE,docPa.getId_paciente());
        valores.put(CITA,docPa.getCita());

        database.insert(TABLE_NAME, null, valores);
        database.close();
    }

    public int obtenerID(List<DocPa> docPas,int posicion){
        return docPas.get(posicion).getId();
    }

    public void eliminarCita(int id){
        database = dbHelper.getWritableDatabase();
        database.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
        database.close();
    }


    public Cursor obtenerCita(int id){
        database = dbHelper.getReadableDatabase();
        String[] columnas = new String[]{ID,ID_PACIENTE,ID_DOCTOR,CITA};
        Cursor cursor = database.query(TABLE_NAME,columnas,"ID=?",
                new String[]{String.valueOf(id)},null,null,null);
        return cursor;
    }

    public void actualizarCita(DocPa docPa,int id){
        database = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ID_DOCTOR,docPa.getId_doctor());
        valores.put(ID_PACIENTE,docPa.getId_paciente());
        valores.put(CITA,docPa.getCita());

        database.update(TABLE_NAME, valores, "ID=?", new String[]{String.valueOf(id)});
        database.close();
    }
}
