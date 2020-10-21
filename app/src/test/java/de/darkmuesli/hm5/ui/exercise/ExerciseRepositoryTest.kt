package de.darkmuesli.hm5.ui.exercise

import android.app.Application
import androidx.test.filters.SmallTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@SmallTest
class ExerciseRepositoryTest {

    val context = mock(Application::class.java)
    lateinit var exerciseRepository: ExerciseRepository

    @Before
    fun setUp() {
        exerciseRepository = ExerciseRepository(context)
    }

    @Test
    fun getExercises_shouldReturnListOfExercises() {
        `when`(context.fileList()).thenReturn(listOf("muh", "mäh").toTypedArray())
        assert(!exerciseRepository.getExercises().isNullOrEmpty())
    }
}