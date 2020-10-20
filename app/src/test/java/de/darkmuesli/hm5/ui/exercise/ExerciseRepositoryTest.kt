package de.darkmuesli.hm5.ui.exercise

import android.content.res.Resources
import androidx.test.filters.SmallTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@SmallTest
class ExerciseRepositoryTest {

    val resouces = mock(Resources::class.java)
    lateinit var exerciseRepository: ExerciseRepository

    @Before
    fun setUp() {
        exerciseRepository = ExerciseRepository(resouces)
    }

    @Test
    fun getExercises_shouldReturnListOfExercises() {
        assert(!exerciseRepository.getExercises().isNullOrEmpty())
    }
}