package com.android.chaldhal.ui.userlist

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.chaldhal.R
import com.android.chaldhal.databinding.RvHomeItemBinding
import com.android.chaldhal.ui.listeners.HomeDataListener
import com.android.chaldhal.data.models.Result
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class HomeDataAdapter(
    private val listener: HomeDataListener,
) :
    PagingDataAdapter<Result, RecyclerView.ViewHolder>(Companion) {


    inner class ViewHolder(private val itemBinding: RvHomeItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = getItem(position)
            itemBinding.item = item
            itemBinding.executePendingBindings()


            val inputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            val outputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH)
            val date: LocalDate = LocalDate.parse(item?.dob?.date, inputFormatter)
            val formattedDate: String = outputFormatter.format(date)
            itemBinding.date.text = formattedDate





            if (!item?.name?.first.isNullOrEmpty() && !item?.name?.last.isNullOrEmpty()) {

                itemBinding.userNameHint.text =
                    item?.name?.first?.substring(0, 1) + item?.name?.last?.substring(0, 1)
                itemBinding.firstName.text = item?.name?.first + " " + item?.name?.last

            } else {
                itemBinding.userNameHint.text = item?.name?.first?.substring(0, 1)
            }

            itemBinding.rootView.setOnClickListener {
                listener.onItemClick(position, item, it)
            }
        }
    }


    companion object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_home_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ViewHolder).bind(position)
        setFadeAnimation(holder.itemView, position)

    }


    private var mLastPosition = -1

    private fun setFadeAnimation(view: View, position: Int) {
//        if (position > mLastPosition) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500
        view.startAnimation(anim)
//            mLastPosition = position
//        }
    }

}