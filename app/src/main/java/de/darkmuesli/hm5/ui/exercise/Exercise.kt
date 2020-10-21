package de.darkmuesli.hm5.ui.exercise

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(var name: String, var active: Boolean = true)