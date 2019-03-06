package co.com.bu.inventariopdt.Core.Helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import co.com.bu.inventariopdt.Core.Contract.InventarioContract

class InventarioDbHelper(contexto:Context) : SQLiteOpenHelper(contexto, DATABASE_NAME,null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${InventarioContract.InventarioEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${InventarioContract.InventarioEntry.NROCONTAINER} TEXT," +
                "${InventarioContract.InventarioEntry.NROPALET} TEXT,"+
                "${InventarioContract.InventarioEntry.NROCAJA} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${InventarioContract.InventarioEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun getInventario() : Cursor{
        var db : SQLiteDatabase = this.readableDatabase
        var res: Cursor = db.rawQuery("select * from ${InventarioContract.InventarioEntry.TABLE_NAME}",null)
        return res;
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Inventario.db"
    }
}