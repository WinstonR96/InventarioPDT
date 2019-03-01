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

class ContainerActivity : AppCompatActivity() {


    private var etContainer : EditText ?= null
    private var sharedPreferences: SharedPreferences?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        etContainer = findViewById(R.id.etContainer)
        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        RespuestaEstado()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_container,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId

        if(id==R.id.logout){
            val intent = Intent(applicationContext,MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        if(id==R.id.guardar){
            GuardarConteo()
        }

        if(id==R.id.reporte){
            GenerarReporte()
        }
        return true
    }

    private fun GenerarReporte() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun GuardarConteo() {
        val editor = sharedPreferences?.edit()
        editor?.putString("container", "0")
        editor?.apply()
        //TODO: implementar la logica de guardar el conteo en BD
    }


    fun RespuestaEstado(){
        var estado = sharedPreferences?.getString("container", " ")
        var container = sharedPreferences?.getString("Ncontainer"," ")
        if(estado.equals("1")) {
            IrPalet(container)
        }
    }

    fun Palet(view: View){
        var container = etContainer?.text.toString()
        if(container.isNullOrEmpty()){
            Toast.makeText(applicationContext,"Ingrese un numero de container",Toast.LENGTH_SHORT).show()
        }else{
            //Entorno.getInstance()?.setEstadoContainerInicializado(sharedPreferences)
            val editor = sharedPreferences?.edit()
            editor?.putString("container", "1")
            editor?.putString("Ncontainer",container)
            editor?.apply()
            IrPalet(container)
        }
    }

    fun IrPalet(data:String ?= null){
        val intent = Intent(applicationContext, PaletActivity::class.java)
        if(!data.isNullOrEmpty())
            intent.putExtra("container",data)
        startActivity(intent)
    }






}
