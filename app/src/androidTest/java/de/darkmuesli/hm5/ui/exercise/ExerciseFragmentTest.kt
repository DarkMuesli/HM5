package de.darkmuesli.hm5.ui.exercise

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest

import de.darkmuesli.hm5.R

import org.hamcrest.Matchers.not

import org.junit.Before
import org.junit.Test

@MediumTest
class ExerciseFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<ExerciseFragment>

    @Before
    fun setUp() {
        fragmentScenario = launchFragmentInContainer<ExerciseFragment>()
    }

    @Test
    fun unlockedShouldBeVisibleOnStartup() {
        onView(withId(R.id.exerciseUnlockedIcon)).check(matches(isDisplayed()))
    }

    @Test
    fun lockedShouldBeInvisibleOnStartup() {
        onView(withId(R.id.exerciseLockedIcon)).check(matches(not(isDisplayed())))
    }

    @Test
    fun clickOnCard_UnlockedShouldToggleState() {
        onView(withId(R.id.exerciseCard)).perform(click())
        onView(withId(R.id.exerciseUnlockedIcon)).check(matches(not(isDisplayed())))
        onView(withId(R.id.exerciseCard)).perform(click())
        onView(withId(R.id.exerciseUnlockedIcon)).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnCard_LockedShouldToggleState() {
        onView(withId(R.id.exerciseCard)).perform(click())
        onView(withId(R.id.exerciseLockedIcon)).check(matches(isDisplayed()))
        onView(withId(R.id.exerciseCard)).perform(click())
        onView(withId(R.id.exerciseLockedIcon)).check(matches(not(isDisplayed())))
    }

    //    Test bleeds into ViewModel...?
    @Test
    fun locksShouldPreserveVisibilityOnRecreate() {
        onView(withId(R.id.exerciseCard)).perform(click())
        fragmentScenario.recreate()
        onView(withId(R.id.exerciseLockedIcon)).check(matches(isDisplayed()))
        onView(withId(R.id.exerciseUnlockedIcon)).check(matches(not(isDisplayed())))
    }
}
