package de.darkmuesli.hm5

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import de.darkmuesli.hm5.ui.exercise.ExerciseViewModel

class ExerciseActivity : AppCompatActivity() {

    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise_activity)
    }

    fun onButtonPress(view: View?) {
        exerciseViewModel.randomizeExercise()
        exerciseViewModel.randomizeTonality()
    }
}