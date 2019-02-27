package co.com.bu.inventariopdt.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import co.com.bu.inventariopdt.Core.Entorno
import co.com.bu.inventariopdt.R

class ContainerActivity : AppCompatActivity() {

    private var etContainer : EditText ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

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
            startActivity(intent)
        }
        return true
    }

    fun Palet(view: View){
        //Entorno.getInstance().container = etContainer?.text.toString()
        val intent = Intent(applicationContext, PaletActivity::class.java)
        startActivity(intent)
    }
}
