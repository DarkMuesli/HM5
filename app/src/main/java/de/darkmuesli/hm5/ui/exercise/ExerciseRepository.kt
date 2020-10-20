package de.darkmuesli.hm5.ui.exercise

import android.content.res.Resources

class ExerciseRepository(resources: Resources?) {
    fun getExercises(): MutableList<Exercise>? {
        val ex1 = Exercise("Mario Kadenz")
        val ex2 = Exercise("Bla")
        val ex3 = Exercise("Blub")

        return mutableListOf(ex1, ex2, ex3)
    }

}
