package de.darkmuesli.hm5.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.darkmuesli.hm5.R

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseRepository = ExerciseRepository(application.resources)

    val exerciseLocked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val tonalityLocked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val exercises: MutableLiveData<MutableList<Exercise>> by lazy {
        MutableLiveData<MutableList<Exercise>>(
            exerciseRepository.getExercises()
        )
    }

    val tonalities: LiveData<List<String>> by lazy {
        MutableLiveData(
            application.resources.getStringArray(R.array.tonalities).toList()
        )
    }

    val currentExercise: MutableLiveData<Exercise?> by lazy {
        MutableLiveData<Exercise?>(
            exercises.value?.filter { it.active }?.randomOrNull()
        )
    }

    val currentTonality: MutableLiveData<String> by lazy {
        MutableLiveData<String>(
            tonalities.value?.randomOrNull()
        )
    }

    fun randomizeExercise() {
        if (exerciseLocked.value != true)
            currentExercise.value = exercises.value?.filter { it.active }?.randomOrNull()
    }

    fun randomizeTonality() {
        if (tonalityLocked.value != true)
            currentTonality.value = tonalities.value?.randomOrNull()
    }

    fun addExercise(exercise: Exercise) {
        exercises.value = exercises.value?.apply { add(exercise) }
        currentExercise.value = exercise
    }

}