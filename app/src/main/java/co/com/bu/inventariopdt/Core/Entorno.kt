package co.com.bu.inventariopdt.Core

abstract class Entorno(var container: String,var palet:String?=null) {


   companion object {
        @Volatile
        private var INSTANCE: Entorno?= null
        fun getInstance() =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: INSTANCE!!
            }
    }
}