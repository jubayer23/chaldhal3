package com.android.chaldhal.ui.listeners

import android.view.View
import com.android.chaldhal.data.models.Result

interface HomeDataListener {
    fun onItemClick(position: Int, item: Result?, view: View)
}