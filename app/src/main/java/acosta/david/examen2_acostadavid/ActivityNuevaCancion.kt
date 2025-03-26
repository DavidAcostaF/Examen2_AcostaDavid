package acosta.david.examen2_acostadavid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityNuevaCancion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_cancion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val nombre = findViewById<EditText>(R.id.etNombre)
        val artista = findViewById<EditText>(R.id.etArtista)
        val duracion = findViewById<EditText>(R.id.etDuracion)
        val album = findViewById<EditText>(R.id.etAlbum)
        btnGuardar.setOnClickListener({
            if (nombre.text.toString().isNotEmpty() && artista.text.toString().isNotEmpty() && duracion.text.toString().isNotEmpty() && album.text.toString().isNotEmpty()) {
                MainActivity.listaCanciones.add(Cancion(nombre.text.toString(), artista.text.toString(), duracion.text.toString(), album.text.toString()))
                finish()
            }else{
                Toast.makeText(this, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}