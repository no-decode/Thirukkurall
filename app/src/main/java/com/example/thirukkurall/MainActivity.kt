package com.example.thirukkurall

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import com.example.thirukkurall.R

// Data model
data class Kural(
    val Number: Int,        // Kural Number
    val Line1: String,
    val Line2: String,
    val Translation: String, // English Translation
    val mk: String           // mk (another meaning or interpretation)
)

class MainActivity : AppCompatActivity() {

    private lateinit var textNumber: TextView
    private lateinit var textLine1: TextView
    private lateinit var textLine2: TextView
    private lateinit var textTranslation: TextView
    private lateinit var textKuralNumber: TextView   // Added for displaying Kural Number
    private lateinit var textMK: TextView            // Added for displaying mk Meaning
    private lateinit var buttonNext: Button

    private lateinit var kuralList: List<Kural>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        textNumber = findViewById(R.id.textNumber)
        textLine1 = findViewById(R.id.textLine1)
        textLine2 = findViewById(R.id.textLine2)
        textTranslation = findViewById(R.id.textTranslation)
        //textKuralNumber = findViewById(R.id.textKuralNumber)  // Initialized
        textMK = findViewById(R.id.textMK)                      // Initialized
        buttonNext = findViewById(R.id.buttonNext)

        kuralList = loadKurals()
        showRandomKural()

        buttonNext.setOnClickListener {
            showRandomKural()
        }
    }

    // Loading Kurals from JSON
    data class KuralResponse(val kural: List<Kural>)

    private fun loadKurals(): List<Kural> {
        val inputStream = assets.open("thirukkural.json")
        val reader = InputStreamReader(inputStream)
        val response = Gson().fromJson(reader, KuralResponse::class.java)
        return response.kural.filter { it.Number <= 1080 }
    }

    // Show a random Kural
    private fun showRandomKural() {
        val kural = kuralList.random()
        textNumber.text = "#${kural.Number}"
        textLine1.text = kural.Line1
        textLine2.text = kural.Line2
        textTranslation.text = kural.Translation
        //textKuralNumber.text = ""//""Kural Number: ${kural.Number}"  // Display Kural Number
        textMK.text = "${kural.mk}"                  // Display mk Meaning
    }
}

/*
package com.example.thirukkurall

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import com.example.thirukkurall.R

// Data model
/*
data class Kural(
    val Number: String,
    val Line1: String,
    val Line2: String,
    val Translation: String
)
*/
data class Kural(
    val Number: Int,        // Kural Number
    val Line1: String,
    val Line2: String,
    val Translation: String, // English Translation
    val mk: String           // mk (another meaning or interpretation)
)

class MainActivity : AppCompatActivity() {

    private lateinit var textNumber: TextView
    private lateinit var textLine1: TextView
    private lateinit var textLine2: TextView
    private lateinit var textTranslation: TextView
    private lateinit var buttonNext: Button

    private lateinit var kuralList: List<Kural>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textNumber = findViewById(R.id.textNumber)
        textLine1 = findViewById(R.id.textLine1)
        textLine2 = findViewById(R.id.textLine2)
        textTranslation = findViewById(R.id.textTranslation)
        buttonNext = findViewById(R.id.buttonNext)

        kuralList = loadKurals()
        showRandomKural()

        buttonNext.setOnClickListener {
            showRandomKural()
        }
    }
/*
    private fun loadKurals(): List<Kural> {
        val inputStream = assets.open("thirukkural.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Kural>>() {}.type
        return Gson().fromJson(reader, type)
    }
*/
data class KuralResponse(val kural: List<Kural>)

    private fun loadKurals(): List<Kural> {
        val inputStream = assets.open("thirukkural.json")
        val reader = InputStreamReader(inputStream)
        val response = Gson().fromJson(reader, KuralResponse::class.java)
        return response.kural
    }

    private fun showRandomKural() {
        val kural = kuralList.random()
        textNumber.text = "Kural #${kural.Number}"
        textLine1.text = kural.Line1
        textLine2.text = kural.Line2
        textTranslation.text = kural.Translation
    }
}
*/