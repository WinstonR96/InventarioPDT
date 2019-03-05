package co.com.bu.inventariopdt.Ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.R

class CajaActivity : AppCompatActivity() {

    private var etCaja:EditText ?= null
    private var container:String = ""
    private var palet:String=""
    private var sharedPreferences: SharedPreferences ?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)

        etCaja = findViewById(R.id.etCaja)

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
        var id = item?.itemId
        if(id==R.id.finalizar){
            GuardarRegistro()
        }
        if(id==R.id.logout){
            val intent = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        return true
    }

    private fun GuardarRegistro() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun Registrar(view: View){
        var caja = etCaja?.text.toString()
        Toast.makeText(applicationContext,"Container: ${container}\nPalet: ${palet}\nCaja: ${caja}",Toast.LENGTH_LONG).show()

    }
}
