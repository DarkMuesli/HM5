package de.darkmuesli.hm5.ui.exercise

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.activity_exercise_list.*


class ExerciseListActivity : AppCompatActivity(), ExerciseRecyclerViewAdapter.ItemClickListener {

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

        adapter.setClickListener(this)
        exerciseRecyclerView.adapter = adapter
        exerciseViewModel.exercises.observe(this) {
            adapter.data.clear()
            adapter.data.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(view: View, position: Int) {
        exerciseViewModel.toggleExercise(position)
    }
}