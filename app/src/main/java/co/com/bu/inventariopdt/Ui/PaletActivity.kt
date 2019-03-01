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

class PaletActivity : AppCompatActivity() {

    private var etPalet: EditText ?= null
    private var container:String = ""
    private var nroContainer:String = ""
    private var palet:String=""
    private var sharedPreferences: SharedPreferences?= null;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palet)



        etPalet = findViewById(R.id.etPalet)

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)

        RespuestaEstado()

        //Capturo el valor enviado desde la activity anterior
        container= intent.getStringExtra("container")
        nroContainer= intent.getStringExtra("container")
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

    fun RespuestaEstado(){
        var estado = sharedPreferences?.getString("palet", " ")
        var container = sharedPreferences?.getString("Ncontainer"," ")
        var palet = sharedPreferences?.getString("Npalet"," ")
        if(estado.equals("1")) {
            IrCaja(container,palet)
        }
    }

    private fun IrCaja(container: String?, palet:String?) {
        val intent = Intent(applicationContext,CajaActivity::class.java)
        intent.putExtra("container",container)
        intent.putExtra("palet",palet)
        startActivity(intent)
    }

    private fun GuardarRegistro() {
        val editor = sharedPreferences?.edit()
        editor?.putString("palet", "0")
        editor?.putString("container", "0")
        editor?.apply()
        //TODO: implementar la logica de guardar el conteo en BD
    }

    fun Caja(view:View){
        palet = etPalet?.text.toString().trim()
        if(palet.isNullOrEmpty()){
            Toast.makeText(applicationContext,"Digite numero de palet", Toast.LENGTH_SHORT).show()
        }else{
            val editor = sharedPreferences?.edit()
            editor?.putString("palet", "1")
            editor?.putString("Ncontainer",container)
            editor?.putString("Npalet",palet)
            editor?.apply()
            IrCaja(nroContainer,palet)
        }
    }
}
