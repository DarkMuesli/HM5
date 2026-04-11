package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.darkmuesli.hm5.databinding.ActivityExerciseListBinding


class ExerciseListFragment : Fragment(),
    ExerciseRecyclerViewAdapter.ItemSwitchClickListener,
    ExerciseRecyclerViewAdapter.ItemRemoveIconClickListener {

    private var _binding: ActivityExerciseListBinding? = null
    private val binding get() = _binding!!
    private val exerciseViewModel: ExerciseViewModel by activityViewModels()
    private lateinit var adapter: ExerciseRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExerciseRecyclerViewAdapter(
            exerciseViewModel.exercises.value.orEmpty().toMutableList()
        )
        val layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseRecyclerView.layoutManager = layoutManager
        binding.exerciseRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )
        binding.exerciseRecyclerView.adapter = adapter

        adapter.setSwitchClickListener(this)
        adapter.setRemoveIconClickListener(this)

        exerciseViewModel.exercises.observe(viewLifecycleOwner) {
            adapter.data.clear()
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener { onAddFabClick() }
    }

    fun onAddFabClick() {
        val input = EditText(requireContext()).apply { inputType = InputType.TYPE_CLASS_TEXT }
        AlertDialog.Builder(requireContext())
            .setTitle("Add Exercise")
            .setView(input)
            .setPositiveButton("OK") { _, _ ->
                exerciseViewModel.addExercise(Exercise(input.text.toString()))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    override fun onItemSwitchClick(view: View, position: Int) =
        exerciseViewModel.toggleExercise(position)

    override fun onItemRemoveIconClick(view: View, position: Int) =
        exerciseViewModel.removeExercise(position)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}