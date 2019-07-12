package com.common.util

import android.graphics.Path

/**
 * Created by Kirill Stoianov on 2019-07-12.
 */
class CanvasShapeUtil {

    private fun setCircle(x: Float, y: Float, radius: Float, dir: Path.Direction): Path {
        val path = Path()
        path.reset()
        path.addCircle(x, y, radius, dir)
        return path
    }

    private fun setPolygon(x: Float, y: Float, radius: Float, numOfPt: Int): Path {
        val path = Path()
        val section = 2.0 * Math.PI / numOfPt

        path.reset()
        path.moveTo(
            (x + radius * Math.cos(0.0)).toFloat(),
            (y + radius * Math.sin(0.0)).toFloat()
        )

        for (i in 1 until numOfPt) {
            path.lineTo(
                (x + radius * Math.cos(section * i)).toFloat(),
                (y + radius * Math.sin(section * i)).toFloat()
            )
        }

        path.close()
        return path
    }

    private fun setStar(x: Float, y: Float, radius: Float, innerRadius: Float, numOfPt: Int): Path {
        val path = Path()

        val section = 2.0 * Math.PI / numOfPt

        path.reset()
        path.moveTo(
            (x + radius * Math.cos(0.0)).toFloat(),
            (y + radius * Math.sin(0.0)).toFloat()
        )
        path.lineTo(
            (x + innerRadius * Math.cos(0 + section / 2.0)).toFloat(),
            (y + innerRadius * Math.sin(0 + section / 2.0)).toFloat()
        )

        for (i in 1 until numOfPt) {
            path.lineTo(
                (x + radius * Math.cos(section * i)).toFloat(),
                (y + radius * Math.sin(section * i)).toFloat()
            )
            path.lineTo(
                (x + innerRadius * Math.cos(section * i + section / 2.0)).toFloat(),
                (y + innerRadius * Math.sin(section * i + section / 2.0)).toFloat()
            )
        }

        path.close()
        return path
    }
}