package acosta.david.examen2_acostadavid

import java.io.Serializable

data class Cancion(val nombre:String , val nombre_artista:String, val duracion:String, val album:String,val color: Int = generarColor()):
    Serializable {
    companion object {
        private val colores = listOf(
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7,
            R.color.color8,
            R.color.color9,
            R.color.color10,
            R.color.color11,
            R.color.color12,
            R.color.color13,
            R.color.color14,
            R.color.color15,
        )

        fun generarColor(): Int {
            return colores.random()
        }
    }
}
