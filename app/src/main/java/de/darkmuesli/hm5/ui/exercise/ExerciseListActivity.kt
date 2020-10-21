package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.activity_exercise_list.*


class ExerciseListActivity : AppCompatActivity(),
    ExerciseRecyclerViewAdapter.ItemSwitchClickListener,
    ExerciseRecyclerViewAdapter.ItemRemoveIconClickListener {

    private val exerciseViewModel: ExerciseViewModel by viewModels()
    private lateinit var adapter: ExerciseRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        adapter = ExerciseRecyclerViewAdapter(
            this,
            exerciseViewModel.exercises.value.orEmpty().toMutableList()
        )
        val mLayoutManager = LinearLayoutManager(this)
        exerciseRecyclerView.layoutManager = mLayoutManager

        exerciseRecyclerView.addItemDecoration(
            DividerItemDecoration(
                exerciseRecyclerView.context,
                mLayoutManager.orientation
            )
        )

        adapter.setSwitchClickListener(this)
        adapter.setRemoveIconClickListener(this)
        exerciseRecyclerView.adapter = adapter
        exerciseViewModel.exercises.observe(this) {
            adapter.data.clear()
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    fun onAddFABClick(view: View) {
        val builder = AlertDialog.Builder(this).apply { setTitle("Add Exercise") }
        val input = EditText(this).apply { inputType = InputType.TYPE_CLASS_TEXT }
        with(builder) {
            setView(input)
            setPositiveButton("OK") { _, _ ->
                exerciseViewModel.addExercise(Exercise(input.text.toString()))
            }
            setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
    }

    override fun onItemSwitchClick(view: View, position: Int) =
        exerciseViewModel.toggleExercise(position)

    override fun onItemRemoveIconClick(view: View, position: Int) {
        exerciseViewModel.removeExercise(position)
        Log.w("Muh", "Remuhving")
    }
}