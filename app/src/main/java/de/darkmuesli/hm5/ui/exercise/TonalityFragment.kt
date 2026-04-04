package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.darkmuesli.hm5.R
import de.darkmuesli.hm5.databinding.TonalityFragmentBinding

class TonalityFragment : Fragment() {

    private var _binding: TonalityFragmentBinding? = null
    private val binding get() = _binding!!

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TonalityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.currentTonality.observe(viewLifecycleOwner) { tonality ->
            binding.tonalityTextView.text = tonality
        }

        exerciseViewModel.tonalityLocked.observe(viewLifecycleOwner) { locked ->
            binding.tonalityUnlockedIcon.visibility = if (locked) View.INVISIBLE else View.VISIBLE
            binding.tonalityLockedIcon.visibility = if (locked) View.VISIBLE else View.INVISIBLE
        }

        binding.tonalityCard.setOnClickListener {
            exerciseViewModel.tonalityLocked.value = exerciseViewModel.tonalityLocked.value?.not()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}