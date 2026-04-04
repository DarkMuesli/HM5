package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.darkmuesli.hm5.databinding.ActivityExerciseListBinding


class ExerciseListActivity : AppCompatActivity(),
    ExerciseRecyclerViewAdapter.ItemSwitchClickListener,
    ExerciseRecyclerViewAdapter.ItemRemoveIconClickListener {

    private lateinit var binding: ActivityExerciseListBinding
    private val exerciseViewModel: ExerciseViewModel by viewModels()
    private lateinit var adapter: ExerciseRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ExerciseRecyclerViewAdapter(
            exerciseViewModel.exercises.value.orEmpty().toMutableList()
        )
        val layoutManager = LinearLayoutManager(this)
        binding.exerciseRecyclerView.layoutManager = layoutManager
        binding.exerciseRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.exerciseRecyclerView.context,
                layoutManager.orientation
            )
        )
        binding.exerciseRecyclerView.adapter = adapter

        adapter.setSwitchClickListener(this)
        adapter.setRemoveIconClickListener(this)

        exerciseViewModel.exercises.observe(this) {
            adapter.data.clear()
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    fun onAddFABClick(view: View) {
        val input = EditText(this).apply { inputType = InputType.TYPE_CLASS_TEXT }
        AlertDialog.Builder(this)
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

    override fun onItemRemoveIconClick(view: View, position: Int) {
        exerciseViewModel.removeExercise(position)
    }
}