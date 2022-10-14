package com.bignerdranch.android.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "BoxDrawingView"
private const val BOXEN_KEY = "BOXEN_KEY"
private const val STATE_KEY = "STATE_KEY"

class BoxDrawingView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

    private var currentBox: Box? = null
    private var boxen: ArrayList<Box> = mutableListOf<Box>() as ArrayList<Box>
    private val boxPaint = Paint().apply {
        color = 0x22ff0000.toInt()
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPaint(backgroundPaint)

        boxen.forEach { box ->
            canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current = PointF(event.x, event.y)
        var action = ""
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"
                currentBox = Box(current).also {
                    boxen.add(it)
                }
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"
                updateCurrentBox(current)
                currentBox = null
            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"
                updateCurrentBox(current)
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
                currentBox = null
            }
        }

        Log.i(TAG, "$action at x=${current.x}, y=${current.y}")

        return true
    }

    private fun updateCurrentBox(current: PointF) {
        currentBox?.let {
            it.end = current
            invalidate()
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val state = super.onSaveInstanceState()
        val savedState = Bundle().apply {
            putParcelableArrayList(BOXEN_KEY, boxen)
            putParcelable(STATE_KEY, state)
        }
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        boxen = (state as Bundle?)?.getParcelableArrayList(BOXEN_KEY) ?: arrayListOf()
        super.onRestoreInstanceState((state as Bundle).getParcelable(STATE_KEY))
    }

    private fun rotateBoxes(boxen: ArrayList<Box>) {
        boxen.forEach {

        }
    }

}