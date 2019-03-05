package co.com.bu.inventariopdt.Ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import co.com.bu.inventariopdt.R


class MainActivity : AppCompatActivity() {

    //Declaramos los atributos relacionados a los widgets visuales
    private var etUsuario: EditText? = null
    private var etContrasena: EditText? = null
    private var permission: Int? = null
    private val TAG = "PERMISOS"
    private val EXTERNAL_WRITE_CODE = 101
    private val EXTERNAL_READ_CODE = 102
    private var recursos: Resources? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recursos = this.resources

        ComprobarPermisos()


        //Relacionamos cada atributo declarado anteriormente con el ID del widget
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContrasena)

    }

    //region Permisos
    fun ComprobarPermisos() {
        ComprobarPermisoEscritura()
        ComprobarPermisoLectura()

    }

    fun ComprobarPermisoEscritura() {
        permission = ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, recursos?.getString(R.string.noautorizado))
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(recursos?.getString(R.string.escritura))
                    builder.setTitle(recursos?.getString(R.string.requerido))
                    builder.setPositiveButton(recursos?.getString(R.string.ok)) { dialog, which ->
                        requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            EXTERNAL_WRITE_CODE
                        )
                    }
                    builder.setNegativeButton(recursos?.getString(R.string.cancelar)){dialog, which ->

                    }
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), EXTERNAL_WRITE_CODE)
                }
            }
        }
    }

    fun ComprobarPermisoLectura() {
        permission = ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, recursos?.getString(R.string.noautorizado))
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage(recursos?.getString(R.string.lectura))
                    builder.setTitle(recursos?.getString(R.string.requerido))
                    builder.setPositiveButton(recursos?.getString(R.string.ok)) { dialog, which ->
                        requestPermissions(
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            EXTERNAL_READ_CODE
                        )
                    }
                    builder.setNegativeButton(recursos?.getString(R.string.cancelar)){dialog, which ->

                    }
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), EXTERNAL_READ_CODE)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            EXTERNAL_WRITE_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, recursos?.getString(R.string.denegado))
                } else {
                    Log.i(TAG, recursos?.getString(R.string.concedido))
                }
            }
            EXTERNAL_READ_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, recursos?.getString(R.string.denegado))
                } else {
                    Log.i(TAG, recursos?.getString(R.string.concedido))
                }
            }
        }
    }
    //endregion

    fun Iniciar(view: View) {
        val usuario = etUsuario?.text.toString()
        val contrasena = etContrasena?.text.toString()
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, recursos?.getString(R.string.vacio), Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(applicationContext, ContainerActivity::class.java)
            startActivity(intent)
        }
    }
}
