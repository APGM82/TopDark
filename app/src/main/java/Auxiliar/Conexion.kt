package Auxiliar

import Conexion.AdminSQLIteConexion
import Modelo.*
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    var nombreBD = "administracion.db3"

    fun cambiarBD(nombreBD:String){
        Conexion.nombreBD = nombreBD
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

    //AQUI CAMBIO LA EXPERIENCIA DEL PILOTO
    fun modExpPiloto(contexto:AppCompatActivity, nombre:String, p: Pilotos):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("experiencia", p.experiencia)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }

    //AQUI CAMBIO LA CONTRASEÑA DEL PILOTO
    fun modPasswd(contexto:AppCompatActivity, nombre:String, p: Pilotos):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("password", p.password)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA FOTO DEL PILOTO
    fun modFoto(contexto:AppCompatActivity, nombre:String, p: Pilotos):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("foto", p.foto)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }

    //AQUI DEVUELVO 1 PILOTO EN SU BÚSQUEDA POR NOMBRE
    fun buscarPiloto(contexto: AppCompatActivity, nombre:String): Pilotos? {
        var p: Pilotos? = null
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select * from pilotos where nombre='${nombre}'",
            null
        )
        if (fila.moveToFirst()) {
            p = Pilotos(fila.getString(0), fila.getInt(1), fila.getInt(2),fila.getString(3),fila.getString(4))
        }
        bd.close()
        return p
    }
    //AQUI DEVUELVO 1 PILOTO EN SU BÚSQUEDA POR NOMBRE Y CONTRASEÑA
    fun buscarPilotoContraseña(contexto: AppCompatActivity, nombre:String, password:String): Pilotos? {
        var p: Pilotos? = null
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select nombre,edad from pilotos where (nombre='${nombre}') and (password='${password}')",
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
        val fila = bd.rawQuery("select nombre,edad,experiencia,password,foto from pilotos order by experiencia desc" , null)
        while (fila.moveToNext()) {
            var p: Pilotos = Pilotos(fila.getString(0),fila.getInt(1),fila.getInt(2),fila.getString(3),fila.getString(4))
            pilotos.add(p)
        }
        bd.close()
        return pilotos
    }

    //AQUI AÑADO 1 NAVE
    fun addNave(contexto: AppCompatActivity, n: Naves){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("matricula", n.matricula)
        registro.put("tipo",n.tipo)
        registro.put("carga", n.carga)
        registro.put("pasajeros", n.pasajeros)
        registro.put("foto", n.foto)

        bd.insert("naves", null, registro)
        bd.close()
    }

    //AQUI DEVUELVO 1 NAVE EN SU BÚSQUEDA POR NOMBRE (MATRICULA)
    fun buscarNaves(contexto: AppCompatActivity, matricula:String): Naves? {
        var n: Naves? = null
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select matricula,tipo, carga, pasajeros, foto from naves where matricula='${matricula}'",
            null
        )
        if (fila.moveToFirst()) {
            n = Naves(matricula, fila.getString(1),fila.getInt(2),fila.getInt(3),fila.getString(4))
        }
        bd.close()
        return n
    }

    //AQUI SACO TODAS LAS NAVES
    fun obtenerNaves(contexto: AppCompatActivity):ArrayList<Naves>{
        var naves:ArrayList<Naves> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select matricula,tipo,carga,pasajeros,foto from naves", null)
        while (fila.moveToNext()) {
            var n: Naves = Naves(fila.getString(0),fila.getString(1),fila.getInt(2),fila.getInt(3),fila.getString(4))
            naves.add(n)
        }
        bd.close()
        return naves
    }

    //AQUI SACO TODAS LAS NAVES TIPO
    fun obtenerNavesTipo(contexto: AppCompatActivity, tipo:String):ArrayList<Naves>{
        var naves:ArrayList<Naves> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select matricula,tipo,carga,pasajeros,foto from naves where tipo='${tipo}' ", null)
        while (fila.moveToNext()) {
            var n: Naves = Naves(fila.getString(0),fila.getString(1),fila.getInt(2),fila.getInt(3),fila.getString(4))
            naves.add(n)
        }
        bd.close()
        return naves
    }

    //AQUI AÑADO 1 MISION de vuelo
    fun addMisionVuelo(contexto: AppCompatActivity, m: MisionVuelo){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()

        registro.put("id",m.id)
        registro.put("duracion", m.duracion)
        registro.put("asignacionp",m.asignacionP)
        registro.put("asignacionn", m.asignacionN)
        registro.put("completada", m.completada)

        bd.insert("misionesvuelo", null, registro)
        bd.close()
    }
    //AQUI AÑADO 1 MISION de Combate
    fun addMisionCombate(contexto: AppCompatActivity, m: MisionCombate){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()

        registro.put("id",m.id)
        registro.put("cazas", m.cazas)
        registro.put("asignacionp",m.asignacionP)
        registro.put("asignacionn", m.asignacionN)
        registro.put("completada", m.completada)

        bd.insert("misionescombate", null, registro)
        bd.close()
    }
    //AQUI AÑADO 1 MISION de Bombardeo
    fun addMisionBombardeo(contexto: AppCompatActivity, m: MisionBombardeo){
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()

        registro.put("id",m.id)
        registro.put("objetivos", m.objetivos)
        registro.put("asignacionp",m.asignacionP)
        registro.put("asignacionn", m.asignacionN)
        registro.put("completada", m.completada)

        bd.insert("misionesbombardeo", null, registro)
        bd.close()
    }
    //TODAS LAS MISIONES DE VUELO
    fun obtenerMisionesVuelo(contexto: AppCompatActivity):ArrayList<MisionVuelo>{
        var misionesVuelo:ArrayList<MisionVuelo> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select id,duracion,asignacionp, asignacionn,completada from misionesvuelo", null)
        while (fila.moveToNext()) {
            var m: MisionVuelo = MisionVuelo(fila.getInt(0),fila.getInt(1),fila.getString(2),fila.getString(3),fila.getInt(4))
            misionesVuelo.add(m)
        }
        bd.close()
        return misionesVuelo
    }
    //TODAS LAS MISIONES DE COMBATE
    fun obtenerMisionesCombate(contexto: AppCompatActivity):ArrayList<MisionCombate>{
        var misionesCombate:ArrayList<MisionCombate> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select id,cazas,asignacionp, asignacionn,completada from misionescombate", null)
        while (fila.moveToNext()) {
            var m: MisionCombate = MisionCombate(fila.getInt(0),fila.getInt(1),fila.getString(2),fila.getString(3),fila.getInt(4))
            misionesCombate.add(m)
        }
        bd.close()
        return misionesCombate
    }
    //TODAS LAS MISIONES DE BOMBARDEO
    fun obtenerMisionesBombardeo(contexto: AppCompatActivity):ArrayList<MisionBombardeo>{
        var misionesBombardeo:ArrayList<MisionBombardeo> = ArrayList(1)

        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery("select id, objetivos, asignacionp, asignacionn, completada from misionesbombardeo", null)
        while (fila.moveToNext()) {
            var m: MisionBombardeo = MisionBombardeo(fila.getInt(0),fila.getInt(1),fila.getString(2),fila.getString(3),fila.getInt(4))
            misionesBombardeo.add(m)
        }
        bd.close()
        return misionesBombardeo
    }
    //AQUI CAMBIO LA ASIGNACION PILOTO DE LAS MISIONES DE VUELO
    fun modAsignacionesVueloPiloto(contexto:AppCompatActivity, id:Int, m: MisionVuelo):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionp", m.asignacionP)
        val cant = bd.update("misionesvuelo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA ASIGNACION NAVE DE LAS MISIONES DE VUELO
    fun modAsignacionesVueloNave(contexto:AppCompatActivity, id:Int, m: MisionVuelo):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionn",m.asignacionN)
        val cant = bd.update("misionesvuelo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA ASIGNACION PILOTO DE LAS MISIONES DE BOMBARDEO
    fun modAsignacionesBombardeoPiloto(contexto:AppCompatActivity, id:Int, m: MisionBombardeo):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionp", m.asignacionP)
        val cant = bd.update("misionesbombardeo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA ASIGNACION NAVE DE LAS MISIONES DE BOMBARDEO
    fun modAsignacionesBombardeoNave(contexto:AppCompatActivity, id:Int, m: MisionBombardeo):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionn",m.asignacionN)
        val cant = bd.update("misionesbombardeo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO LA ASIGNACION PILOTO DE LAS MISIONES DE COMBATE
    fun modAsignacionesCombatePiloto(contexto:AppCompatActivity, id:Int, m: MisionCombate):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionp", m.asignacionP)
        val cant = bd.update("misionescombate", registro, "id='${id}'", null)
        bd.close()
        return cant
    }


    //AQUI CAMBIO LA ASIGNACION NAVE DE LAS MISIONES DE COMBATE
    fun modAsignacionesCombateNave(contexto:AppCompatActivity, id:Int, m: MisionCombate):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("asignacionn",m.asignacionN)
        val cant = bd.update("misionescombate", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO EL VALOR "COMPLETADA" DE LA MISION DE COMBATE
    fun modCompletadaCombate(contexto:AppCompatActivity, id:Int, m: MisionCombate):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("completada",m.completada)
        val cant = bd.update("misionescombate", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO EL VALOR "COMPLETADA" DE LA MISION DE BOMBARDEO
    fun modCompletadaBombardeo(contexto:AppCompatActivity, id:Int, m: MisionBombardeo):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("completada",m.completada)
        val cant = bd.update("misionesbombardeo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }
    //AQUI CAMBIO EL VALOR "COMPLETADA" DE LA MISION DE VUELO
    fun modCompletadaVuelo(contexto:AppCompatActivity, id:Int, m: MisionVuelo, t:Int):Int {
        val admin = AdminSQLIteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("completada",t)
        val cant = bd.update("misionesvuelo", registro, "id='${id}'", null)
        bd.close()
        return cant
    }

}