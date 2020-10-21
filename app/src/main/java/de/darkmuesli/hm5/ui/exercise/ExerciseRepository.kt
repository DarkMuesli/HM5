package de.darkmuesli.hm5.ui.exercise

import android.content.Context
import android.util.Log
import de.darkmuesli.hm5.R
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ExerciseRepository(val context: Context) {

    private val filename = "exercises.json"

    fun getExercises(): MutableList<Exercise> {
        if (filename in context.fileList()) {
            context.openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold("") { acc, next ->
                    acc + next
                }
            }.also {
                return if (it.isEmpty() || it.isBlank())
                    mutableListOf()
                else
                    Json.decodeFromString(it)
            }
        } else
            return context.resources.getStringArray(R.array.exercises)
                .map { Exercise(it) }
                .toMutableList()
    }

    fun saveExercises(list: List<Exercise>) {
        Log.i("I/O", "Saving Exercise List")
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(Json.encodeToString(list).toByteArray())
        }
    }


}
