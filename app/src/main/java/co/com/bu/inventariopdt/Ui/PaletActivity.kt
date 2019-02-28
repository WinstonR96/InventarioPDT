package co.com.bu.inventariopdt.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.Core.Entorno
import co.com.bu.inventariopdt.R

class PaletActivity : AppCompatActivity() {

    private var etPalet: EditText ?= null
    private var container:String = ""
    private var palet:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palet)
        etPalet = findViewById(R.id.etPalet)

        //Capturo el valor enviado desde la activity anterior
        container= intent.getStringExtra("container")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_palet,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId

        return true
    }

    fun Caja(view:View){
        if(etPalet?.text.toString().trim().isNullOrEmpty()){
            Toast.makeText(applicationContext,"Digite numero de palet", Toast.LENGTH_SHORT).show()
        }else{
            palet = etPalet?.text.toString()
        }
        val intent = Intent(applicationContext,CajaActivity::class.java)
        intent.putExtra("container",container)
        intent.putExtra("palet",palet)
        startActivity(intent)
    }
}
