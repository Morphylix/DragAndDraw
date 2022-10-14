package com.bignerdranch.android.draganddraw

import android.graphics.PointF
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Box(val start: PointF): Parcelable {
    var end: PointF = start

    val left: Float
    get() = Math.min(start.x, end.x)

    val right: Float
    get() = Math.max(start.x, end.x)

    val top: Float
    get() = Math.min(start.y, end.y)

    val bottom: Float
    get() = Math.max(start.y, end.y)

    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }
}
