package de.darkmuesli.hm5

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import de.darkmuesli.hm5.databinding.ActivityMainBinding
import de.darkmuesli.hm5.ui.exercise.ExerciseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exerciseCard.setOnClickListener {
            startActivity(Intent(this, ExerciseActivity::class.java))
        }
    }

}