package com.example.thirukkuralapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

// Data model
data class Kural(
    val Number: String,
    val Line1: String,
    val Line2: String,
    val Translation: String
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

    private fun loadKurals(): List<Kural> {
        val inputStream = assets.open("thirukkural.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<Kural>>() {}.type
        return Gson().fromJson(reader, type)
    }

    private fun showRandomKural() {
        val kural = kuralList.random()
        textNumber.text = "Kural #${kural.Number}"
        textLine1.text = kural.Line1
        textLine2.text = kural.Line2
        textTranslation.text = kural.Translation
    }
}
