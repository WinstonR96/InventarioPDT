package co.com.bu.inventariopdt.Core.Contract

import android.provider.BaseColumns

object InventarioContract {
    object InventarioEntry : BaseColumns{
        const val TABLE_NAME = "inventario"
        const val NROCONTAINER = "NroContenedor"
        const val NROPALET = "NroPalet"
        const val NROCAJA = "NroCaja"
    }
}