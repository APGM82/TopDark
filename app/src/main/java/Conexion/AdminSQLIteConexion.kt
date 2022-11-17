package Conexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLIteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table pilotos(nombre text primary key, edad int, experiencia int, password text, foto text)")
        db.execSQL("create table naves(matricula text primary key, tipo text, carga int, pasajeros int, foto text)")
        db.execSQL("create table misionesvuelo( id int primary key, duracion int, asignacionp text, asignacionn text, completada int )")
        db.execSQL("create table misionesbombardeo(id int primary key, objetivos int, asignacionp text, asignacionn text, completada int )")
        db.execSQL("create table misionescombate(id int primary key, cazas int, asignacionp text, asignacionn text, completada int )")
        db.execSQL("insert into pilotos values('Vader',45,0,'ladooscuro',null)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}