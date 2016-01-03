package com.example.manuel.doctorpaciente.models.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manuel on 16/12/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "doctor_paciente.sqlite";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DoctorDataSource.CREATE_TABLE);
        db.execSQL(PacienteDataSource.CREATE_TABLE);
        db.execSQL(Doc_Pa_DataSource.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}
