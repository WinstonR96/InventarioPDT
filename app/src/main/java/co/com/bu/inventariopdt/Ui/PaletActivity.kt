package co.com.bu.inventariopdt.Ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.R

class PaletActivity : AppCompatActivity() {

    private var etPalet: EditText ?= null
    private var nro_Container:String = ""
    private var nro_palet:String=""
    private var sharedPreferences: SharedPreferences?= null
    private var recursos: Resources?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_palet)

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        ValidarEstadoTransaccion()

        recursos = this.resources
        etPalet = findViewById(R.id.etPalet)


        //Capturo el valor enviado desde la activity anterior
        nro_Container= intent.getStringExtra("nro_container")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_palet,menu)
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

    private fun ValidarEstadoTransaccion(){
        var estadoPalet= sharedPreferences?.getString("estado_palet", " ")
        var nro_container = sharedPreferences?.getString("nro_container"," ")
        var nro_palet = sharedPreferences?.getString("nro_palet", " ")
        if(estadoPalet.equals("1")) {
            IrCaja(nro_container,nro_palet)
        }
    }

    private fun IrCaja(nro_container: String?, nro_palet:String?) {
        val intent = Intent(applicationContext,CajaActivity::class.java)
        intent.putExtra("nro_container",nro_container)
        intent.putExtra("nro_palet",nro_palet)
        startActivity(intent)
        finish()
    }

    private fun GuardarRegistro() {
        val editor = sharedPreferences?.edit()
        editor?.putString("estado_palet", "0")
        editor?.putString("estado_container", "0")
        editor?.apply()
        val intent = Intent(applicationContext, ContainerActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun Caja(view:View){
        nro_palet = etPalet?.text.toString().trim()
        if(nro_palet.isNullOrEmpty()){
            Toast.makeText(applicationContext,recursos?.getString(R.string.vacio), Toast.LENGTH_SHORT).show()
        }else{
            val editor = sharedPreferences?.edit()
            editor?.putString("estado_palet", "1")
            editor?.putString("nro_palet",nro_palet)
            editor?.apply()
            IrCaja(nro_Container,nro_palet)
        }
    }
}
