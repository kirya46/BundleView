package com.common.bundleview.Impl

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.common.util.DrawingUtil
import kotlin.math.roundToInt

/**
 * Created by Kirill Stoianov on 2/27/19.
 */
class KashaMotionBundleView(context: Context, attributeSet: AttributeSet?, def: Int) : View(context, attributeSet, def) {

    companion object {

        /**
         * Default alpha value for bundle separator line between
         * label and price when bundle not selected.
         */
        private const val DEFAULT_SEPARATOR_ALPHA = 127
    }

    var bgColor: Int = Color.GRAY
        set(value) {
            field = value
            bgPaint.color = value
            invalidate()
        }

    var bgColorSelected: Int = Color.GRAY
        set(value) {
            field = value
            bgPaintSelected.color = value
            invalidate()
        }

    var borderColor: Int = Color.TRANSPARENT
        set(value) {
            field = value
            borderPaint.color = value
            invalidate()
        }

    var borderColorSelected: Int = Color.WHITE
        set(value) {
            field = value
            borderPaintSelected.color = value
            invalidate()
        }

    var separatorColor: Int = Color.WHITE
        set(value) {
            field = value
            separatorDefaultPaint.color = value
            separatorDefaultPaint.alpha =
                DEFAULT_SEPARATOR_ALPHA
            invalidate()
        }

    var separatorSelectedColor: Int = Color.WHITE
        set(value) {
            field = value
            separatorSelectedPaint.color = value
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

    var middleLabelTextColor: Int = Color.WHITE
        set(value) {
            field = value
            middleLabelTextPaint.color = value
            invalidate()
        }

    var bottomLabelTextColor: Int = Color.WHITE
        set(value) {
            field = value
            bottomLabelTextPaint.color = value
            invalidate()
        }

    var priceTextColor: Int = Color.WHITE
        set(value) {
            field = value
            priceTextPaint.color = value
            invalidate()
        }

    var priceTextSelectedColor: Int = Color.WHITE
        set(value) {
            field = value
            invalidate()
        }

    var borderWith: Float = 2f
        set(value) {
            field = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    value,
                    context.resources.displayMetrics
            )
            borderPaint.strokeWidth = field
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

    var tooltipTextSize: Float = 12f
        set(value) {
            field = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    value,
                    context.resources.displayMetrics
            )
            tooltipTextPaint.textSize = field
            invalidate()
        }

    var middleLableTextSize: Float = 32f
        set(value) {
            field = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    value,
                    context.resources.displayMetrics
            )
            middleLabelTextPaint.textSize = field
            invalidate()
        }

    var bottomLabelTextSize: Float = 18f
        set(value) {
            field = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    value,
                    context.resources.displayMetrics
            )
            bottomLabelTextPaint.textSize = field
            invalidate()
        }


    var priceTextSize: Float = 14f
        set(value) {
            field = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    value,
                    context.resources.displayMetrics
            )
            priceTextPaint.textSize = field
            invalidate()
        }

    //[END TEXT SIZE's]


    //[START MARGINS]
    var bottomLableTopMargin: Float = 4f


    //[START PAINT's]
    private val bgPaint by lazy {
        Paint().apply {
            color = bgColor
            isAntiAlias = true
            //xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        }
    }

    private val bgPaintSelected by lazy {
        Paint().apply {
            color = bgColorSelected
            isAntiAlias = true
        }
    }

    private val borderPaint by lazy {
        Paint().apply {
            color = borderColor
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = borderWith
        }
    }

    private val borderPaintSelected by lazy {
        Paint().apply {
            color = borderColorSelected
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = borderWith
        }
    }

    private val separatorSelectedPaint by lazy {
        Paint().apply {
            color = separatorSelectedColor
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = borderWith
        }
    }

    private val separatorDefaultPaint by lazy {
        Paint().apply {
            color = separatorColor
            isAntiAlias = true
            alpha = DEFAULT_SEPARATOR_ALPHA
            style = Paint.Style.STROKE
            strokeWidth = borderWith
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
            textSize = tooltipTextSize
            typeface = Typeface.create("sans-serif-black", Typeface.BOLD)
            isAntiAlias = true
        }
    }

    private val middleLabelTextPaint by lazy {
        TextPaint().apply {
            color = middleLabelTextColor
            textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    middleLableTextSize,
                    context.resources.displayMetrics
            )
            typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
            isAntiAlias = true
        }
    }

    private val bottomLabelTextPaint by lazy {
        TextPaint().apply {
            color = bottomLabelTextColor
            textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    bottomLabelTextSize,
                    context.resources.displayMetrics
            )
            typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
            isAntiAlias = true
        }
    }

    private val priceTextPaint by lazy {
        TextPaint().apply {
            color = priceTextColor
            textSize = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    priceTextSize,
                    context.resources.displayMetrics
            )
            typeface = Typeface.create("sans-serif-black", Typeface.BOLD)
            isAntiAlias = true
        }
    }

    private val fitPriceTextSize by lazy {
        fitTextSize(priceText, priceTextPaint, 2f, priceTextSize)
    }

    private val fitToolTipTextSize by lazy {
        fitTextSize(tooltipText, tooltipTextPaint, 3f, tooltipTextSize)
    }

    private val fitMiddleTextSize by lazy {
        fitTextSize(middleText, middleLabelTextPaint, 3f, middleLableTextSize)
    }

    private val fitBottomTextSize by lazy {
        fitTextSize(bottomText, bottomLabelTextPaint, 5f, bottomLabelTextSize)
    }

    private fun fitTextSize(text: String?, textPaint: TextPaint, padding: Float, defaultSize: Float): Float {
        val bound = Rect()
        return if (text?.isNotEmpty() == true) {
            textPaint.getTextBounds(text, 0, text.length, bound)
            if (bound.width() > measuredWidth - 20) {
               getFitTextSize(textPaint, measuredWidth.toFloat(), text) - context.dpToPx(padding)
            } else {
                defaultSize
            }
        } else {
            defaultSize
        }
    }

    //[END PAINT's]


    private var showTooltip: Boolean = false


    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackground(canvas)
        drawSeparator(canvas)
        drawTooltip(canvas)
        drawMainText(canvas)
        drawPriceText(canvas)
        drawBorder(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //calculate new with if tooltip enabled
        val newHeight: Int = if (showTooltip) {
            measuredHeight + calculateTooltipHeight().roundToInt() / 2
        } else {
            measuredHeight
        }

        setMeasuredDimension(measuredWidth, newHeight)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        showTooltip = if (tooltipText == null || tooltipText?.isEmpty() == true) {
            false
        } else {
            selected
        }
        requestLayout()
    }

    //[DRAW METHODS]

    private fun drawBackground(canvas: Canvas) {

        val destinationPaint = if (isSelected) {
            bgPaintSelected
        } else {
            bgPaint
        }

        val backgroundPath = DrawingUtil.getRoundRect(
                borderWith, borderWith, width - borderWith, height - borderWith, cornerRadius, cornerRadius,
                true, true, true, true
        )

        canvas.drawPath(backgroundPath, destinationPaint)
    }

    private fun drawBorder(canvas: Canvas) {
        val destinationPaint = if (isSelected) {
            borderPaintSelected
        } else {
            borderPaint
        }

        val borderPath = DrawingUtil.getRoundRect(
                borderWith, borderWith, width.toFloat() - borderWith, height.toFloat() - borderWith, cornerRadius, cornerRadius)
        canvas.drawPath(borderPath, destinationPaint)
    }

    private fun drawSeparator(canvas: Canvas) {
        val lineYPosition: Float = height - calculatePriceContainerHeight()

        val currentPaint = if (isSelected) {
            separatorSelectedPaint
        } else {
            separatorDefaultPaint
        }

        canvas.drawLine(borderWith, lineYPosition, width - borderWith, lineYPosition, currentPaint)
    }

    private fun drawTooltip(canvas: Canvas) {
        if (!showTooltip || tooltipText == null || tooltipText?.isEmpty() == true) return

        //draw tooltip background
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

        //draw tooltip text
        tooltipText?.apply {
            val bounds = Rect()
            tooltipTextPaint.textSize = fitToolTipTextSize
            tooltipTextPaint.getTextBounds(this, 0, this.length, bounds)
            val textX: Float = (width / 2f) - (bounds.width() / 2f)
            val textY: Float = (calculateTooltipHeight() / 2) + (bounds.height() / 2f)
            canvas.drawText(this, 0, this.length, textX, textY, tooltipTextPaint)
        }
    }

    private fun drawMainText(canvas: Canvas) {

        val middleTextBounds = Rect()
        val bottomTextBounds = Rect()

        when {
            (middleText?.isNotEmpty() == true && bottomText?.isNotEmpty() == true) -> {
                middleLabelTextPaint.textSize = fitMiddleTextSize
                bottomLabelTextPaint.textSize = fitBottomTextSize

                middleLabelTextPaint.getTextBounds(middleText, 0, middleText?.length
                        ?: 0, middleTextBounds)
                bottomLabelTextPaint.getTextBounds(bottomText, 0, bottomText?.length
                        ?: 0, bottomTextBounds)

                val topMargin = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        bottomLableTopMargin,
                        context.resources.displayMetrics
                )

                var textX: Float = (width / 2f) - (middleTextBounds.width() / 2f)
                var textY: Float =
                        (height / 2f) - (middleTextBounds.height() / 2f) - (bottomTextBounds.height() / 2) - (topMargin / 2)

                if (showTooltip) textY += calculateTooltipHeight() / 2

                canvas.drawText(middleText!!, 0, middleText?.length
                        ?: 0, textX, textY, middleLabelTextPaint)

                textX = (width / 2f) - (bottomTextBounds.width() / 2f)
                textY += (middleTextBounds.height() / 2f) + (bottomTextBounds.height() / 2) + topMargin
                canvas.drawText(bottomText!!, 0, bottomText?.length
                        ?: 0, textX, textY, bottomLabelTextPaint)
            }

            (middleText?.isNotEmpty() == true) -> {
                middleLabelTextPaint.textSize = fitMiddleTextSize

                middleLabelTextPaint.getTextBounds(middleText, 0, middleText?.length
                        ?: 0, middleTextBounds)
                val textX: Float = (width / 2f) - (middleTextBounds.width() / 2f)
                var textY: Float = (height / 2f) - (middleTextBounds.height() / 2f)
                if (showTooltip) textY += calculateTooltipHeight() / 2
                canvas.drawText(middleText!!, 0, middleText?.length
                        ?: 0, textX, textY, middleLabelTextPaint)
            }

            (bottomText?.isNotEmpty() == true) -> {
                bottomLabelTextPaint.textSize = fitBottomTextSize

                bottomLabelTextPaint.getTextBounds(bottomText, 0, bottomText?.length
                        ?: 0, bottomTextBounds)
                val textX: Float = (width / 2f) - (bottomTextBounds.width() / 2f)
                var textY: Float = (height / 2f) - (bottomTextBounds.height() / 2f)
                if (showTooltip) textY += calculateTooltipHeight() / 2
                canvas.drawText(bottomText!!, 0, bottomText?.length
                        ?: 0, textX, textY, bottomLabelTextPaint)
            }
        }
    }


    private fun drawPriceText(canvas: Canvas) {
        priceText?.apply {
            val textBounds = Rect()

            if (isSelected) {
                priceTextPaint.color = priceTextSelectedColor
            } else {
                priceTextPaint.color = priceTextColor
            }

            priceTextPaint.textSize = fitPriceTextSize
            priceTextPaint.getTextBounds(this, 0, this.length, textBounds)

            val textX: Float = (width / 2f) - (textBounds.width() / 2f)
            val textY: Float = (height - (calculatePriceContainerHeight() / 2)) + (textBounds.height() / 2f)
            canvas.drawText(this, 0, this.length, textX, textY, priceTextPaint)
        }

    }

    //[UTIL METHODS]

    private fun calculateTooltipHeight(): Float {
        return tooltipTextSize * 3f
    }

    private fun calculatePriceContainerHeight(): Float {
        return priceTextSize * 2.8f
    }

    private fun getFitTextSize(paint: Paint, width: Float, text: String): Float {
        val nowWidth = paint.measureText(text)
        return if (nowWidth <= width) paint.textSize else width / nowWidth * paint.textSize

    }

    private fun Context.dpToPx(dp: Float) = (dp * resources.displayMetrics.density).toInt()
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