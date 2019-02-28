package co.com.bu.inventariopdt.Core

import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.content.ContextCompat.startActivity

class Entorno{

   companion object {
        @Volatile
        private var INSTANCE: Entorno?= null
        fun getInstance() =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: INSTANCE
            }
    }

    //Metodo que me permite guardar los datos
    fun setEstadoCajaFinalizado(sharedPreferences:SharedPreferences?){
        val editor = sharedPreferences?.edit()
        editor?.putString("caja", "")
        editor?.apply()
    }

    fun setEstadoContainerFinalizado(sharedPreferences:SharedPreferences?){
        val editor = sharedPreferences?.edit()
        editor?.putString("container", "")
        editor?.apply()
    }

    fun setEstadoContainerInicializado(sharedPreferences:SharedPreferences?){
        val editor = sharedPreferences?.edit()
        editor?.putString("container", "1")
        editor?.apply()
    }

    fun obtenerEstados(sharedPreferences:SharedPreferences?, trigger: String):String{
        var estado:String = ""
        if(trigger.toLowerCase().equals("container")){
            if (sharedPreferences != null) {
                estado = sharedPreferences.getString("container", " ")
            }
        }
        if(trigger.toLowerCase().equals("caja")){
            if (sharedPreferences != null) {
                estado = sharedPreferences.getString("caja", " ")
            }
        }
        if(trigger.toLowerCase().equals("palet")){
            if (sharedPreferences != null) {
                estado = sharedPreferences.getString("palet", " ")
            }
        }
        return estado;

    }
}

