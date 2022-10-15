package mx.edu.utez.conversion
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import mx.edu.utez.conversion.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val formatDecimalFormat = DecimalFormat("#.00")
        val convers: Spinner = binding.conversion
        ArrayAdapter.createFromResource(
            this,
            R.array.conver,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            convers.adapter = adapter
        }
        val conver2: Spinner = binding.conversion2
        ArrayAdapter.createFromResource(
            this,
            R.array.conver2,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            conver2.adapter = adapter
        }
        val enviadobtn = binding.btnE
        enviadobtn.setOnClickListener {
            val opcion = convers.selectedItem.toString()
            val opcion2 = conver2.selectedItem.toString()
            val dine = binding.dine.text.toString()
            var resultado = binding.resultado
            if (dine.isNotEmpty()){
                when (opcion){
                    in "EU" ->{
                        if (opcion2 == "USD") {
                            var conver = 0.98 * dine.toDouble()
                            resultado.text = "La aquivalencia es: $$conver"
                        }else if (opcion2 == "MXN") {
                            var conver = dine.toDouble() * 19.56
                            resultado.text = "La aquivalencia es: $$conver"
                        }else{
                            resultado.text = "La aquivalencia es: €$dine"
                        }
                    }
                    in "USD" ->{
                        if (opcion2 == "EU") {
                            var conver = 1.02 * dine.toDouble()
                            conver=formatDecimalFormat.format(conver).toDouble()
                            resultado.text = "La aquivalencia es: €$conver"
                        }else if (opcion2 == "MXN") {
                            var conver = dine.toDouble() * 19.97
                            resultado.text = "La aquivalencia es: $$conver"
                        }else{
                            resultado.text = "La aquivalencia es: $$dine"
                        }
                    }
                    in "MXN" ->{
                        if (opcion2 == "EU") {
                            var conver = 0.051 * dine.toDouble()
                            resultado.text = "La aquivalencia es: €$conver"
                        }else if (opcion2 == "USD") {
                            var conver = dine.toDouble() * 0.050
                            resultado.text = "La aquivalencia es: $$conver"
                        }else{
                            resultado.text = "La aquivalencia es: $$dine"
                        }
                    }
                }
            }else{
                resultado.text = "CAMPOS VACIOS!!!!"
            }
        }
    }
}