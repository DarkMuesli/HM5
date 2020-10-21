package de.darkmuesli.hm5.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.exercise_fragment.*

class ExerciseFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.exercise_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseViewModel.currentExercise.observe(viewLifecycleOwner) { exercise ->
            exerciseTextView.text =
                exercise?.name ?: context?.getString(R.string.no_exercise_found_error)
        }

        exerciseViewModel.exerciseLocked.observe(viewLifecycleOwner) { locked ->
            if (locked) {
                exerciseUnlockedIcon.visibility = View.INVISIBLE
                exerciseLockedIcon.visibility = View.VISIBLE
            } else {
                exerciseUnlockedIcon.visibility = View.VISIBLE
                exerciseLockedIcon.visibility = View.INVISIBLE
            }
        }

        exerciseCard.setOnClickListener {
            exerciseViewModel.exerciseLocked.value = exerciseViewModel.exerciseLocked.value?.not()
        }

        exerciseListIcon.setOnClickListener {
            startActivity(Intent(activity, ExerciseListActivity::class.java))
//            val builder = AlertDialog.Builder(activity).apply { setTitle("Add Exercise") }
//            val input = EditText(activity).apply { inputType = InputType.TYPE_CLASS_TEXT }
//            with(builder) {
//                setView(input)
//                setPositiveButton("OK") { _, _ ->
//                    exerciseViewModel.addExercise(Exercise(input.text.toString()))
//                }
//                setNegativeButton("Cancel") { dialog, _ ->
//                    dialog.cancel()
//                }
//                show()
//            }
        }

    }

}