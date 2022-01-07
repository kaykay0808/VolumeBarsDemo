package com.kay.volumebarsdemo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt

// https://developer.android.com/training/custom-views/create-view
// Custom view -> Creating a View Class
class VolumeView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    @ColorInt
    private val color: Int
    private var numberOfLines: Int
    private var volumeLevel: Int

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
        // testing
        addLines()
    }

    // (1) testing?
    private fun addLines() {
        for (i in 1..numberOfLines) {
            addView(createRectangle())
        }
    }

    private fun createRectangle(): View {
        val view = View(context)
        view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 20) // <- Height and width in xml
        view.setBackgroundColor(color)
        return view
    }
}
