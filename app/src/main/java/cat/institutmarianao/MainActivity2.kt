package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val resulNom = findViewById<TextView>(R.id.resulNom)
        val resulEdat = findViewById<TextView>(R.id.resulEdat)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val switch1 = findViewById<SwitchCompat>(R.id.switch1)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        resulNom.text = intent.getStringExtra("nom")
        resulEdat.text = intent.getIntExtra("edat", 0).toString()

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val text = when (checkedId) {
                R.id.radio1 -> "Opción 1 seleccionada"
                R.id.radio2 -> "Opción 2 seleccionada"
                else -> "Ninguna opción"
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        switch1.setOnCheckedChangeListener { _, isChecked ->
            val text = if (isChecked) "Switch activado" else "Switch desactivado"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        btnVolver.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Selecciona una opció", Toast.LENGTH_SHORT).show()
            } else {
                val data = Intent()
                data.putExtra("missatge", "Tot ha anat bé")
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}