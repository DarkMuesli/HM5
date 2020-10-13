package de.darkmuesli.hm5.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.darkmuesli.hm5.R

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    val exerciseLocked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val tonalityLocked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val exercises: MutableLiveData<MutableList<String>> by lazy {
        MutableLiveData<MutableList<String>>(
            application.resources.getStringArray(R.array.exercises).toMutableList()
        )
    }

    val tonalities: LiveData<List<String>> by lazy {
        MutableLiveData(
            application.resources.getStringArray(R.array.tonalities).toList()
        )
    }

    val currentExercise: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            exercises.value?.randomOrNull()
        )
    }

    val currentTonality: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            tonalities.value?.randomOrNull()
        )
    }

    fun randomizeExercise() {
        if (exerciseLocked.value != true)
            currentExercise.value = exercises.value?.randomOrNull()
    }

    fun randomizeTonality() {
        if (tonalityLocked.value != true)
            currentTonality.value = tonalities.value?.randomOrNull()
    }

    fun addExercise(exercise: String) {
        exercises.value = exercises.value?.apply { add(exercise) }
        currentExercise.value = exercise
    }

}