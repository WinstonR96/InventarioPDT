package co.com.bu.inventariopdt.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import co.com.bu.inventariopdt.Core.Entorno
import co.com.bu.inventariopdt.R

class PaletActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palet)

        //Toast.makeText(applicationContext,Entorno.getInstance().container.toString(),Toast.LENGTH_SHORT).show()
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
        val intent = Intent(applicationContext,CajaActivity::class.java)
        startActivity(intent)
    }
}
