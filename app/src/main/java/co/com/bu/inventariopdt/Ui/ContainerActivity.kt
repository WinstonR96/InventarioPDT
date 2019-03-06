package co.com.bu.inventariopdt.Ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.R
import android.os.Environment;
import co.com.bu.inventariopdt.Core.Helpers.InventarioDbHelper
import jxl.WorkbookSettings
import java.io.File
import java.lang.Exception
import java.util.*
import jxl.write.WritableWorkbook
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet


class ContainerActivity : AppCompatActivity() {

    private var etContainer : EditText ?= null
    private var sharedPreferences: SharedPreferences?= null;
    private var recursos: Resources?=null
    private var directorio: String ?= null
    private var TAG_FILE = "FILE"
    private var FileName = "inventario.xls";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        sharedPreferences = getSharedPreferences("info", Context.MODE_PRIVATE)
        ValidarEstadoTransaccion()
        recursos = this.resources
        directorio = Environment.getExternalStorageDirectory().path+"/Reportes/"
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
        var DbHelper = InventarioDbHelper(applicationContext)
        val cursor : Cursor = DbHelper.getInventario()
        var sd : File = File(directorio)
        if(!sd.exists()){
            sd.mkdirs()
        }
        try{
            var file:File = File(sd,FileName)
            var workbookSettings: WorkbookSettings = WorkbookSettings()
            var local : Locale = Locale("en","EN")
            workbookSettings.locale = local
            var workbook: WritableWorkbook
            workbook = Workbook.createWorkbook(file,workbookSettings)
            var sheetWritableSheet : WritableSheet  = workbook.createSheet("InventarioLista", 0);
            sheetWritableSheet.addCell(Label(0, 0, "NroContenedor"));
            sheetWritableSheet.addCell(Label(1, 0, "NroPalet"));
            sheetWritableSheet.addCell(Label(2, 0, "NroCaja"));

            if(cursor.moveToFirst()){
                do {
                    var NroContenedor = cursor.getString(cursor.getColumnIndex("NroContenedor"))
                    var NroPalet = cursor.getString(cursor.getColumnIndex("NroPalet"))
                    var NroCaja = cursor.getString(cursor.getColumnIndex("NroCaja"))
                    var i = cursor.position + 1
                    sheetWritableSheet.addCell(Label(0, i, NroContenedor));
                    sheetWritableSheet.addCell(Label(1, i, NroPalet));
                    sheetWritableSheet.addCell(Label(2, i, NroCaja));
                }while (cursor.moveToNext())
            }

            cursor.close()
            workbook.write()
            workbook.close()
            Toast.makeText(applicationContext, "Excel generado", Toast.LENGTH_SHORT).show();
        }catch (e:Exception){
            Toast.makeText(applicationContext,e.message,Toast.LENGTH_LONG).show()
        }

    }
}


