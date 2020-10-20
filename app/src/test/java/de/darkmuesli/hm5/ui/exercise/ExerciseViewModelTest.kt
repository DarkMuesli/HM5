package de.darkmuesli.hm5.ui.exercise

import android.app.Application
import android.content.res.Resources
import androidx.test.filters.SmallTest
import de.darkmuesli.hm5.R
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@SmallTest
class ExerciseViewModelTest {

    val app = mock(Application::class.java)
    val resources = mock(Resources::class.java)
    val tonalityList: Array<String> = listOf("A", "B", "C").toTypedArray()
    val exerciseList: Array<String> = listOf("foo", "bar", "spam").toTypedArray()

    @Before
    fun setUp() {


        `when`(app.resources).thenReturn(resources)

        `when`(app.resources.getStringArray(R.array.tonalities)).thenReturn(tonalityList)
        `when`(app.resources.getStringArray(R.array.exercises)).thenReturn(exerciseList)
    }

    @Test
    fun exercisesShouldNotBeNullOrEmptyOnStartup() {
        val exerciseViewModel = ExerciseViewModel(app)
        assert(!exerciseViewModel.exercises.value.isNullOrEmpty())
    }

    @Test
    fun tonalitiesShouldNotBeNullOrEmptyOnStartup() {
        val exerciseViewModel = ExerciseViewModel(app)
        assert(!exerciseViewModel.tonalities.value.isNullOrEmpty())
    }
}