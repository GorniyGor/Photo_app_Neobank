package com.recan.photo_app.presentation

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView


object Utils {
    fun Context.toPx(dp: Int): Int {
        return dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    class GridItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            context.apply {
                outRect.top += toPx(20)
                outRect.bottom += toPx(20)
                outRect.left += toPx(20)
                outRect.right += toPx(20)
            }

        }
    }
}