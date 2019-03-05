package co.com.bu.inventariopdt.Ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.Core.Contract.InventarioContract
import co.com.bu.inventariopdt.Core.Helpers.InventarioDbHelper
import co.com.bu.inventariopdt.R
import java.lang.Exception

class CajaActivity : AppCompatActivity() {

    private var etCaja: EditText? = null
    private var nro_container: String = ""
    private var nro_palet: String = ""
    private var sharedPreferences: SharedPreferences? = null;
    private var recursos : Resources ?= null
    private val BD = "GUARDANDOBASEDEDATOS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)
        recursos = this.resources
        etCaja = findViewById(R.id.etCaja)

        //Capturo el valor enviado desde la activity anterior
        nro_container = intent.getStringExtra("nro_container")
        nro_palet = intent.getStringExtra("nro_palet")

        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_caja, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        if (id == R.id.finalizar) {
            GuardarRegistro()
        }
        if (id == R.id.logout) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        return true
    }

    private fun GuardarRegistro() {
        val editor = sharedPreferences?.edit()
        editor?.putString("estado_palet", "0")
        editor?.apply()
        val intent = Intent(applicationContext, PaletActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    fun Registrar(view: View) {
        var caja = etCaja?.text.toString()
        if(caja.isNullOrEmpty()){
            Toast.makeText(applicationContext,recursos?.getString(R.string.etCodigoCaja),Toast.LENGTH_SHORT).show()
        }else{
            GuardarBaseDatosSqlite(nro_container,nro_palet,caja)
            etCaja?.text = null
        }
    }

    fun GuardarBaseDatosSqlite(NroContainer: String, NroPaler: String, NroCaja: String) {
        val dbHelper = InventarioDbHelper(applicationContext)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(InventarioContract.InventarioEntry.NROCONTAINER, NroContainer)
            put(InventarioContract.InventarioEntry.NROPALET, NroPaler)
            put(InventarioContract.InventarioEntry.NROCAJA, NroCaja)
        }
        try{
            val newRowId = db?.insert(InventarioContract.InventarioEntry.TABLE_NAME, null, values)
            Toast.makeText(applicationContext, recursos?.getString(R.string.registroexitoso),Toast.LENGTH_SHORT).show()
        }catch (Ex:Exception){
            Toast.makeText(applicationContext,Ex.message,Toast.LENGTH_LONG).show()
            Log.e(BD,Ex.message)
        }
    }
}
