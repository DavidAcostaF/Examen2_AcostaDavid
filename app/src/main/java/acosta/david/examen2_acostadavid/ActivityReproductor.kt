package acosta.david.examen2_acostadavid

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityReproductor : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private lateinit var tvContador: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reproduccion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tvReproduciendo = findViewById<TextView>(R.id.tvReproduciendo)
        var btnStop = findViewById<TextView>(R.id.btnStop)
        tvContador = findViewById(R.id.tvContador)
        var cancion = intent.getSerializableExtra("cancion") as? Cancion

        tvReproduciendo.text = "Reproduciendo: ${cancion?.nombre}"
        val duracionMs = convertirDuracionAMilisegundos(cancion?.duracion.toString())
        iniciarContador(duracionMs)
        btnStop.setOnClickListener({
            timer?.cancel()
            finish()
        })
    }
    private fun convertirDuracionAMilisegundos(duracion: String): Long {
        val partes = duracion.split(":")
        val minutos = partes.getOrNull(0)?.toIntOrNull() ?: 0
        val segundos = partes.getOrNull(1)?.toIntOrNull() ?: 0
        return (minutos * 60 + segundos) * 1000L
    }

    private fun iniciarContador(duracionMs: Long) {
        var segundosPasados = 0L
        timer = object : CountDownTimer(duracionMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                segundosPasados++
                val minutos = segundosPasados / 60
                val segundos = segundosPasados % 60
                tvContador.text = String.format("Tiempo: %d:%02d", minutos, segundos)
            }
            override fun onFinish() {
                tvContador.text = "Tiempo: terminado"
                Toast.makeText(this@ActivityReproductor, "Repitiendo canción", Toast.LENGTH_SHORT).show()

                tvContador.postDelayed({
                    iniciarContador(duracionMs)
                }, 1000)
            }
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}