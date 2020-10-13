package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.tonality_fragment.*

class TonalityFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.tonality_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.currentTonality.observe(viewLifecycleOwner, { tonality ->
            tonalityTextView.text = tonality
        })

        exerciseViewModel.tonalityLocked.observe(viewLifecycleOwner) { locked ->
            if (locked) {
                tonalityUnlockedIcon.visibility = View.INVISIBLE
                tonalityLockedIcon.visibility = View.VISIBLE
            } else {
                tonalityUnlockedIcon.visibility = View.VISIBLE
                tonalityLockedIcon.visibility = View.INVISIBLE
            }
        }

        tonalityCard.setOnClickListener {
            exerciseViewModel.tonalityLocked.value = exerciseViewModel.tonalityLocked.value?.not()
        }
    }
}