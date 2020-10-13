package de.darkmuesli.hm5.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import de.darkmuesli.hm5.R
import kotlin.math.abs
import kotlin.random.Random

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    val exercises: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>(
            application.resources.getStringArray(R.array.exercises).toMutableList()
        )
    }

    val tonalities: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>(
            application.resources.getStringArray(R.array.tonalities).toList()
        )
    }

    val currentTonality: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            randomTonality()
        )
    }

    val currentExercise: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            randomExercise()
        )
    }

    private fun randomExercise() =
        exercises.value?.get(abs(Random.nextInt() % exercises.value.orEmpty().size)).orEmpty()

    private fun randomTonality() =
        tonalities.value?.get(abs(Random.nextInt() % tonalities.value.orEmpty().size)).orEmpty()

    fun randomizeTonality() {
        currentTonality.value = randomTonality()
    }

    fun randomizeExercise() {
        currentExercise.value = randomExercise()
    }

    fun addExercise(exercise: String) {
        exercises.value = exercises.value?.apply { add(exercise) }
        currentExercise.value = exercise
    }

}