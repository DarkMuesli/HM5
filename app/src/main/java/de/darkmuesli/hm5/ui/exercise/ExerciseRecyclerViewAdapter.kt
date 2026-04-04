package de.darkmuesli.hm5.ui.exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.darkmuesli.hm5.databinding.ExerciceRvRowBinding

class ExerciseRecyclerViewAdapter(var data: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    private var switchClickListener: ItemSwitchClickListener? = null
    private var removeIconClickListener: ItemRemoveIconClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ExerciceRvRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ExerciceRvRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.exerciseSwitch.setOnClickListener {
                switchClickListener?.onItemSwitchClick(it, adapterPosition)
            }
            binding.removeIcon.setOnClickListener {
                removeIconClickListener?.onItemRemoveIconClick(it, adapterPosition)

            }
        }

        fun bind(exercise: Exercise) {
            binding.exerciseSwitch.text = exercise.name
            binding.exerciseSwitch.isChecked = exercise.active
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    fun setSwitchClickListener(listener: ItemSwitchClickListener) {
        switchClickListener = listener
    }

    fun setRemoveIconClickListener(listener: ItemRemoveIconClickListener) {
        removeIconClickListener = listener
    }

    interface ItemSwitchClickListener {
        fun onItemSwitchClick(view: View, position: Int)
    }

    interface ItemRemoveIconClickListener {
        fun onItemRemoveIconClick(view: View, position: Int)
    }
}
