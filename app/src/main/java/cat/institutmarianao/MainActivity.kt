package cat.institutmarianao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAct = findViewById<Button>(R.id.button)
        val btnClear = findViewById<Button>(R.id.button2)
        val campNom = findViewById<EditText>(R.id.nomText)
        val campEdat = findViewById<EditText>(R.id.edadText)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val missatge = result.data?.getStringExtra("missatge")
                Toast.makeText(this, missatge, Toast.LENGTH_LONG).show()
            }
        }

        fun checkForm(): Boolean {
            var valid = true
            if (campNom.text.toString().trim().isEmpty()) {
                campNom.error = "Nom obligatori"
                valid = false
            }
            if (campEdat.text.toString().trim().isEmpty()) {
                campEdat.error = "Edat obligatoria"
                valid = false
            }
            return valid
        }

        fun enviarDatos() {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("nom", campNom.text.toString().trim())
            intent.putExtra("edat", campEdat.text.toString().toIntOrNull())
            launcher.launch(intent)
        }

        btnAct.setOnClickListener {
            if (checkForm()) {
                enviarDatos()
            } else {
                Toast.makeText(this, "Error en el formulario", Toast.LENGTH_SHORT).show()
            }
        }

        btnClear.setOnClickListener {
            campNom.text.clear()
            campEdat.text.clear()
            campNom.error = null
            campEdat.error = null
        }
    }
}