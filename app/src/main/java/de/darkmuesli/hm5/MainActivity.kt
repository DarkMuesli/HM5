package de.darkmuesli.hm5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonPress(view: View) {
        val tonalityText = findViewById<TextView>(R.id.tonalityTextView)
        val exerciseText = findViewById<TextView>(R.id.exerciseTextView)

        val tonalities = resources.getStringArray(R.array.tonalities)
        val exercises = resources.getStringArray(R.array.exercises)

        tonalityText.text = tonalities[abs(Random.nextInt() % tonalities.size)]
        exerciseText.text = exercises[abs(Random.nextInt() % exercises.size)]
    }
}