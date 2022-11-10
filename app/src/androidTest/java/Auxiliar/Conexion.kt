package Auxiliar

import Conexion.AdminSQLIteConexion
import Modelo.Pilotos
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    var nombreBD = "administracion.db3"

    fun cambiarBD(nombreBD:String){
        this.nombreBD = nombreBD
    }

    //AQUI AÑADO 1 PILOTO
    fun addPiloto(contexto: AppCompatActivity, p: Pilotos){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre", p.nombre)
        registro.put("edad",p.edad)
        registro.put("experiencia", p.experiencia)
        registro.put("password", p.password)
        registro.put("foto", p.foto)

        bd.insert("pilotos", null, registro)
        bd.close()
    }

    //AQUI CAMBIO LA FOTO DEL PILOTO
    fun modFotoPiloto(contexto:AppCompatActivity, nombre:String, p:Pilotos):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("foto", p.foto)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA EXPERIENCIA DEL PILOTO
    fun modExpPiloto(contexto:AppCompatActivity, nombre:String, p:Pilotos):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("experiencia", p.experiencia)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }
    //AQUI DEVUELVO 1 PILOTO EN SU BÚSQUEDA POR NOMBRE
    fun buscarPiloto(contexto: AppCompatActivity, nombre:String):Pilotos? {
        var p:Pilotos? = null
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select nombre,edad from pilotos where nombre='${nombre}'",
            null
        )
        if (fila.moveToFirst()) {
            p = Pilotos(nombre, fila.getInt(0), fila.getInt(1),fila.getString(2),fila.getString(3))
        }
        bd.close()
        return p
    }

    //AQUI SACO TODOS LOS PILOTOS
    fun obtenerPilotos(contexto: AppCompatActivity):ArrayList<Pilotos>{
        var pilotos:ArrayList<Pilotos> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select nombre,edad,experiencia,password,foto from pilotos", null)
        while (fila.moveToNext()) {
            var p:Pilotos = Pilotos(fila.getString(0),fila.getInt(1),fila.getInt(2),fila.getString(3),fila.getString(4))
            pilotos.add(p)
        }
        bd.close()
        return pilotos
    }

}