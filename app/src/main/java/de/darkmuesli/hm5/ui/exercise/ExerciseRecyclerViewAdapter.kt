package de.darkmuesli.hm5.ui.exercise

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.exercice_rv_row.view.*

class ExerciseRecyclerViewAdapter(val context: Context, var data: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    private var mClickListener: ItemClickListener? = null

    private val mInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        mInflater.inflate(
            R.layout.exercice_rv_row, parent, false
        )
    )

    inner class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.exercise_switch.setOnClickListener(this)
        }

        fun getMyItemView() = itemView

        override fun onClick(v: View) {
            if (mClickListener != null) {
                mClickListener?.onItemClick(v, adapterPosition)
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getMyItemView().exercise_switch.text = data[position].name
        holder.getMyItemView().exercise_switch.isChecked = data[position].active
    }

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    fun setClickListener(itemClickListener: ItemClickListener) {
        mClickListener = itemClickListener

        Log.w("MyTag", "itemClickListenet set")
    }


    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


}
