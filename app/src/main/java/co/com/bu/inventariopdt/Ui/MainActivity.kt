package co.com.bu.inventariopdt.Ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.R

class MainActivity : AppCompatActivity() {

    //Declaramos los atributos relacionados a los widgets visuales
    private var etUsuario: EditText? = null
    private var etContrasena: EditText? = null
    private var btIniciar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Relacionamos cada atributo declarado anteriormente con el ID del widget
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btIniciar = findViewById(R.id.btIniciar)


    }

    fun Iniciar(view: View) {
        var usuario = etUsuario?.text.toString()
        var contrasena = etContrasena?.text.toString()
        val intent = Intent(applicationContext,ContainerActivity::class.java)
        startActivity(intent)
        /*if (!usuario.toString().isNullOrEmpty() || contrasena.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Digite campos vacios", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(applicationContext,ContainerActivity::class.java)
            startActivity(intent)
        }*/

    }


}
