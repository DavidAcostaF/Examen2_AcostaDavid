package acosta.david.examen2_acostadavid

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        var listaCanciones = mutableListOf<Cancion>()
    }
    private lateinit var adapter: CancionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val add_canciones = findViewById<Button>(R.id.btnAgregar)
        listaCanciones.addAll(
            listOf(
                Cancion("Delicate", "Taylor Swift", "3:52", "Reputation"),
                Cancion("Fearless", "Taylor Swift", "4:01", "Fearless"),
                Cancion("Riptide", "Vance Joy", "3:24", "Dream Your Life Away"),
                Cancion("Antología", "Shakira", "4:15", "Pies Descalzos"),
                Cancion("Stella", "All Time Low", "3:20", "Nothing Personal"),
                Cancion("In The End", "Linkin Park", "3:36", "Hybrid Theory"),
                Cancion("Bohemian Rhapsody", "Queen", "5:55", "A Night at the Opera"),
                Cancion("Wonderwall", "Oasis", "4:18", "(What's the Story) Morning Glory?"),
                Cancion("Someone Like You", "Adele", "4:45", "21"),
                Cancion("Counting Stars", "OneRepublic", "4:17", "Native"),
                Cancion("Happier", "Marshmello ft. Bastille", "3:34", "Happier"),
                Cancion("Rolling in the Deep", "Adele", "3:48", "21"),
                Cancion("Numb", "Linkin Park", "3:05", "Meteora"),
                Cancion("Sugar", "Maroon 5", "3:55", "V"),
                Cancion("Blinding Lights", "The Weeknd", "3:20", "After Hours"),
                Cancion("Photograph", "Ed Sheeran", "4:19", "x"),
                Cancion("Yellow", "Coldplay", "4:26", "Parachutes"),
                Cancion("Lose Yourself", "Eminem", "5:20", "8 Mile"),
                Cancion("Chandelier", "Sia", "3:36", "1000 Forms of Fear"),
                Cancion("Radioactive", "Imagine Dragons", "3:06", "Night Visions")
            )
        )

        adapter = CancionAdapter(this@MainActivity, listaCanciones)
        val gridView = findViewById<GridView>(R.id.gridCanciones)
        gridView.adapter = adapter

        add_canciones.setOnClickListener({
            val intent = Intent(this, ActivityNuevaCancion::class.java)
//            intent.putExtra("listaCanciones", listaCanciones)
//            setResult(RESULT_OK, intent)
            startActivity(intent)
            Log.d("DEBUG", "Número de canciones: ${listaCanciones.size}")

        })

    }

    class CancionAdapter(private val context: Context, private val cancionList: List<Cancion>,) : BaseAdapter() {

        override fun getCount(): Int {
            return cancionList.size
        }

        override fun getItem(position: Int): Any {
            return cancionList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val cancion = cancionList[position]
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.music_item, parent, false)
            val nombreCancion = view.findViewById<TextView>(R.id.tvTitulo)
            val nombreArtista = view.findViewById<TextView>(R.id.tvArtista)
            Log.d("DEBUG getView", "Número de canciones: ${listaCanciones.size}")

            nombreCancion.text = cancion.nombre
            nombreArtista.text = cancion.nombre_artista

            view.setOnClickListener({
                val intent = Intent(context, ActivityDetalleCancion::class.java)
                intent.putExtra("cancion", cancion)
                context.startActivity(intent)
            })
            val fondoColor = ContextCompat.getColor(context, cancion.color)
            view.setBackgroundColor(fondoColor)

            return view
        }

    }
    override fun onResume() {
        super.onResume()
        // los dos puntos se usan para referirse a si misma es como cuando instancias una actividad
        if(::adapter.isInitialized){
            adapter.notifyDataSetChanged()
        }
    }
}