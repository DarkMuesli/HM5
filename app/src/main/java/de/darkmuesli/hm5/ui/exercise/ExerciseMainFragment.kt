package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.darkmuesli.hm5.databinding.FragmentExerciseMainBinding

class ExerciseMainFragment : Fragment() {

    private var _binding: FragmentExerciseMainBinding? = null
    private val binding get() = _binding!!

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mixButton.setOnClickListener {
            exerciseViewModel.randomizeExercise()
            exerciseViewModel.randomizeTonality()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}