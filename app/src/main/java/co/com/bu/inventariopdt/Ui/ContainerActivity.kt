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
import android.os.Environment;
import com.ajts.androidmads.library.SQLiteToExcel


class ContainerActivity : AppCompatActivity() {

    private var etContainer : EditText ?= null
    private var sharedPreferences: SharedPreferences?= null;
    private var recursos: Resources?=null
    private var directorio: String ?= null
    private var sqliteToExcel: SQLiteToExcel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        ValidarEstadoTransaccion()
        recursos = this.resources
        directorio = Environment.getExternalStorageState()
        etContainer = findViewById(R.id.etContainer)
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

        if(id==R.id.reporte){
            GenerarReporte()
        }
        return true
    }


    fun ValidarEstadoTransaccion(){
        var estadoContainer = sharedPreferences?.getString("estado_container", " ")
        var container = sharedPreferences?.getString("nro_container"," ")
        if(estadoContainer.equals("1")) {
            IrPalet(container)
        }
    }

    fun Palet(view: View){
        var container = etContainer?.text.toString()
        if(container.isNullOrEmpty()){
            Toast.makeText(applicationContext,recursos?.getString(R.string.vacioContainer),Toast.LENGTH_SHORT).show()
        }else{
            val editor = sharedPreferences?.edit()
            editor?.putString("estado_container", "1")
            editor?.putString("nro_container",container)
            editor?.apply()
            etContainer?.text=null
            IrPalet(container)
        }
    }

    fun IrPalet(data:String ?= null){
        val intent = Intent(applicationContext, PaletActivity::class.java)
        if(!data.isNullOrEmpty())
            intent.putExtra("nro_container",data)
        startActivity(intent)
        finish()
    }

    private fun GenerarReporte() {
        sqliteToExcel = SQLiteToExcel(applicationContext,"inventario")
        sqliteToExcel!!.exportAllTables("inventario.xls", object : SQLiteToExcel.ExportListener {
            override fun onStart() {

            }

            override fun onCompleted(filePath: String) {
                Toast.makeText(applicationContext,filePath,Toast.LENGTH_LONG).show()
            }

            override fun onError(e: Exception) {
                Toast.makeText(applicationContext,e.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}
