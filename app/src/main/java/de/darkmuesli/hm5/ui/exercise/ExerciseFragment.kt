package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.darkmuesli.hm5.R
import de.darkmuesli.hm5.databinding.ExerciseFragmentBinding

class ExerciseFragment : Fragment() {

    private var _binding: ExerciseFragmentBinding? = null
    private val binding get() = _binding!!

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.currentExercise.observe(viewLifecycleOwner) { exercise ->
            binding.exerciseTextView.text =
                exercise?.name ?: context?.getString(R.string.no_exercise_found_error)
        }

        exerciseViewModel.exerciseLocked.observe(viewLifecycleOwner) { locked ->
            binding.exerciseUnlockedIcon.visibility = if (locked) View.INVISIBLE else View.VISIBLE
            binding.exerciseLockedIcon.visibility = if (locked) View.VISIBLE else View.INVISIBLE

        }

        binding.exerciseCard.setOnClickListener {
            exerciseViewModel.exerciseLocked.value = exerciseViewModel.exerciseLocked.value?.not()
        }

        binding.exerciseListIcon.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ExerciseListFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}