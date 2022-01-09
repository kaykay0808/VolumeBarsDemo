package com.kay.volumebarsdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import kotlin.math.roundToInt

// https://developer.android.com/training/custom-views/create-view
// Custom view -> Creating a View Class
class VolumeView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @ColorInt
    private var color: Int
    private var numberOfLines: Int
    private var volumeLevel: Int

    // Dimensions
    private val sideMargin = resources.getDimensionPixelSize(R.dimen.volume_side_margin)
    private val betweenMargin = resources.getDimensionPixelSize(R.dimen.volume_between_margin)
    private val volumeBarHeight = resources.getDimensionPixelSize(R.dimen.volume_height)

    // Init the custom view's XML
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.VolumeView,
            0, 0
        ).apply {

            try {
                // Initiate the member variable here
                color = getColor(R.styleable.VolumeView_volumeColor, Color.CYAN)
                numberOfLines = getInteger(R.styleable.VolumeView_volumeLines, 10)
                volumeLevel = getInteger(R.styleable.VolumeView_volumeLines, 10)
            } finally {
                recycle()
            }
        }
        addChildren()
    }

    // (1) A children layout to the parent linearLayout
    private fun addChildren() {
        val firstColorIndex = numberOfLines - (numberOfLines * (volumeLevel/100f)).roundToInt()
        for (i in 0 until numberOfLines) {
            if(i < firstColorIndex) {
                addView(createRectangle(Color.GRAY))
            } else {
                addView(createRectangle(color))
            }
        }
        // Adding a textview under the volume bars
        val volumeInfo = TextView(context)
        volumeInfo.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        volumeInfo.text = resources.getString(R.string.volume_info, volumeLevel)
        volumeInfo.gravity = Gravity.CENTER
        addView(volumeInfo)
    }

    // (2)
    private fun createRectangle(@ColorInt color: Int): View {
        val view = View(context)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, volumeBarHeight)
        view.setBackgroundColor(color)
        val params = view.layoutParams as MarginLayoutParams
        params.setMargins(sideMargin, betweenMargin, sideMargin, betweenMargin)
        return view
    }

    // (3)
    fun updateNumberOfLines(lines: Int) {
        numberOfLines = lines
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }

    fun updateVolumeLevel(volume: Int) {
        volumeLevel = volume
        removeAllViewsInLayout()
        addChildren()
        invalidate()
    }
}
