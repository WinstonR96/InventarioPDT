package co.com.bu.inventariopdt.Ui

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import co.com.bu.inventariopdt.Core.Entorno
import co.com.bu.inventariopdt.R

class CajaActivity : AppCompatActivity() {

    private var container:String = ""
    private var palet:String=""
    private var sharedPreferences: SharedPreferences ?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)

        //Capturo el valor enviado desde la activity anterior
        container= intent.getStringExtra("container")
        palet= intent.getStringExtra("palet")

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_caja,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id==R.id.finalizar){
            Entorno.getInstance()!!.setEstadoCajaFinalizado(sharedPreferences)
        }
        return true
    }

    fun Registrar(view: View){
        Toast.makeText(applicationContext,"Guardando",Toast.LENGTH_SHORT).show()

    }
}
