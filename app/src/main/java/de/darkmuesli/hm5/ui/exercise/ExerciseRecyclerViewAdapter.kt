package de.darkmuesli.hm5.ui.exercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.darkmuesli.hm5.R
import kotlinx.android.synthetic.main.exercice_rv_row.view.*

class ExerciseRecyclerViewAdapter(val context: Context, var data: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>() {

    private var mSwitchClickListener: ItemSwitchClickListener? = null
    private var mRemoveIconClickListener: ItemRemoveIconClickListener? = null

    private val mInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        mInflater.inflate(
            R.layout.exercice_rv_row, parent, false
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.exercise_switch.setOnClickListener {
                if (mSwitchClickListener != null)
                    mSwitchClickListener?.onItemSwitchClick(it, adapterPosition)
            }
            itemView.remove_icon.setOnClickListener {
                if (mRemoveIconClickListener != null)
                    mRemoveIconClickListener?.onItemRemoveIconClick(it, adapterPosition)

            }
        }

        fun getMyItemView() = itemView
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getMyItemView().exercise_switch.text = data[position].name
        holder.getMyItemView().exercise_switch.isChecked = data[position].active
    }

    override fun getItemCount() = data.size

    fun getItem(position: Int) = data[position]

    fun setSwitchClickListener(itemSwitchClickListener: ItemSwitchClickListener) {
        mSwitchClickListener = itemSwitchClickListener
    }

    fun setRemoveIconClickListener(itemRemoveIconClickListener: ItemRemoveIconClickListener) {
        mRemoveIconClickListener = itemRemoveIconClickListener
    }

    interface ItemSwitchClickListener {
        fun onItemSwitchClick(view: View, position: Int)
    }

    interface ItemRemoveIconClickListener {
        fun onItemRemoveIconClick(view: View, position: Int)
    }


}
