package acosta.david.examen2_acostadavid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetalleCancion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_cancion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var cancion = intent.getSerializableExtra("cancion") as? Cancion
        var tvTitulo = findViewById<TextView>(R.id.tvTitulo)
        var tvAlbum = findViewById<TextView>(R.id.tvAlbum)
        var tvArtista = findViewById<TextView>(R.id.tvArtista)
        var tvDuracion = findViewById<TextView>(R.id.tvDuracion)
        var btnPlay = findViewById<Button>(R.id.btnPlay)
        var btnEliminar = findViewById<Button>(R.id.btnEliminar)
        var btnBack = findViewById<ImageView>(R.id.btnBack)
        tvTitulo.text = cancion?.nombre
        tvAlbum.text = "${tvAlbum.text}: ${cancion?.album}"
        tvArtista.text = cancion?.nombre_artista
        tvDuracion.text = cancion?.duracion
        btnPlay.text= "${btnPlay.text} ${cancion?.nombre}"

        btnEliminar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("¿Eliminar canción?")
                .setMessage("¿Seguro que deseas eliminar \"${cancion?.nombre}\"?")
                .setPositiveButton("Sí") { _, _ ->
                    MainActivity.listaCanciones.remove(cancion)
                    Toast.makeText(this, "Canción eliminada", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Cancelar", null)
                .show()


    }
        btnBack.setOnClickListener({
            finish()
        })

        btnPlay.setOnClickListener({
            var indent = Intent(this, ActivityReproductor::class.java)
            indent.putExtra("cancion", cancion)
            startActivity(indent)
        })
    }
}