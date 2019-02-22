package com.common.bundleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt

/**
 * Created by Kirill Stoianov on 21/02/2019.
 */
class BundleViewImpl(context: Context, attributeSet: AttributeSet?, def: Int) : View(context, attributeSet, def) {

    var bgColor: Int = Color.GRAY
        set(value) {
            field = value
            bgPaint.color = value
            invalidate()
        }

    var borderColor: Int = Color.WHITE
        set(value) {
            field = value
            borderPaint.color = value
            invalidate()
        }

    var tooltipColor: Int = Color.BLACK
        set(value) {
            field = value
            tooltipPaint.color = value
            invalidate()
        }

    var tooltipTextColor: Int = Color.WHITE
        set(value) {
            field = value
            tooltipTextPaint.color = value
            invalidate()
        }


    var borderWith: Float = 3f
        set(value) {
            field = value
            invalidate()
        }

    var cornerRadius: Float = 36f
        set(value) {
            field = value
            invalidate()
        }

    //[START TEXT's]
    var tooltipText: String? = null
        set(value) {
            field = value
            invalidate()
        }

    var middleText: String? = null
        set(value) {
            field = value
            invalidate()
        }

    var bottomText: String? = null
        set(value) {
            field = value
            invalidate()
        }

    var priceText: String? = null
        set(value) {
            field = value
            invalidate()
        }
    //[END TEXT's]

    //[START TEXT SIZE's]

    var tooltipTextSize: Int = 36
        set(value) {
            field = value
            invalidate()
        }

    var topLableTextSize: Int = 32
        set(value) {
            field = value
            invalidate()
        }

    var topLableTrialTextSize: Int = 18
        set(value) {
            field = value
            invalidate()
        }

    var bottomLabelTextSize: Int = 18
        set(value) {
            field = value
            invalidate()
        }

    var bottomLabelTrialTextSize: Int = 32
        set(value) {
            field = value
            invalidate()
        }

    var priceTextSize: Int = 42
        set(value) {
            field = value
            invalidate()
        }
    //[END TEXT SIZE's]

    var showTooltip: Boolean = false
        set(value) {
            field = value
            requestLayout()
        }

    //[START PAINT's]
    private val bgPaint by lazy {
        Paint().apply {
            color = bgColor
            isAntiAlias = true
        }
    }

    private val borderPaint by lazy {
        Paint().apply {
            color = borderColor
            isAntiAlias = true
        }
    }

    private val tooltipPaint by lazy {
        Paint().apply {
            color = tooltipColor
            isAntiAlias = true
        }
    }

    private val tooltipTextPaint by lazy {
        TextPaint().apply {
            color = tooltipTextColor
            textSize = tooltipTextSize.toFloat()
        }
    }
    //[END PAINT's]

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBorder(canvas)
        drawBackground(canvas)
        drawSeparator(canvas)
        drawTooltip(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //calculate new with if tooltip enabled
        val newHeight: Int = if (showTooltip) {
            measuredHeight + calculateTooltipHeight().roundToInt()
        } else {
            measuredHeight
        }

        setMeasuredDimension(measuredWidth, newHeight)
    }

    //[DRAW METHODS]

    private fun drawBackground(canvas: Canvas) {
        val backgroundPath = DrawingUtil.getRoundRect(
            borderWith, borderWith, width - borderWith, height - borderWith, cornerRadius, cornerRadius,
            true, true, true, true
        )

        canvas.drawPath(backgroundPath, bgPaint)
    }

    private fun drawBorder(canvas: Canvas) {
        val borderPath = DrawingUtil.getRoundRect(
            0f, 0f, width.toFloat(), height.toFloat(), cornerRadius, cornerRadius,
            true, true, true, true
        )
        canvas.drawPath(borderPath, borderPaint)
    }

    private fun drawSeparator(canvas: Canvas) {
        val startY: Float = height - (priceTextSize * 2.8f)
        val stopY: Float = height - (priceTextSize * 2.8f) + borderWith

        canvas.drawLine(borderWith, startY, width - borderWith, stopY, borderPaint)
    }

    private fun drawTooltip(canvas: Canvas) {
        if (!showTooltip && tooltipText != null && tooltipText?.isNotEmpty() == true) return
        val tooltipRect = DrawingUtil.getRoundRect(
            borderWith,
            borderWith,
            width - borderWith,
            calculateTooltipHeight(),
            cornerRadius,
            cornerRadius,
            bl = false,
            br = false
        )
        canvas.drawPath(tooltipRect, tooltipPaint)

        tooltipText?.apply {
            val bounds = Rect()
            tooltipPaint.getTextBounds(this, 0, this.length, bounds)
            val textX: Float = (width / 2f) - (bounds.width() / 2f)
            val textY: Float = (height / 2f) - (bounds.height()/2f)
            canvas.drawText(this, 0, this.length, textX, textY, tooltipTextPaint)
        }
    }


    //[UTIL METHODS]

    private fun calculateTooltipHeight(): Float {
        return tooltipTextSize * 3f
    }
}


//        context.theme.obtainStyledAttributes(attributeSet, R.styleable.DashedProgressBar, 0, 0)
//            .apply {
//                try {
//                    dashColor = getColor(R.styleable.DashedProgressBar_dashColor, Color.CYAN)
//                    dashBackgroundColor = getColor(R.styleable.DashedProgressBar_dashBackgroundColor, Color.GRAY)
//                    maxDashCount = getInteger(R.styleable.DashedProgressBar_maxDashCount, 0)
//                    currentDashCount = getInteger(R.styleable.DashedProgressBar_currentDashCount, 0)
//                } finally {
//                    recycle()
//                }
//            }