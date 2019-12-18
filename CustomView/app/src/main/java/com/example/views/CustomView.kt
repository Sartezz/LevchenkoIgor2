package com.example.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Nullable
import com.example.customview.R
import kotlin.math.pow

class CustomView : View {
    private lateinit var rect: Rect
    private lateinit var rectPaint: Paint
    private var squareColor: Int = Color.GREEN
    private var squareSize: Int = 200

    private lateinit var circlePaint: Paint
    private var circleX: Float = 0F
    private var circleY: Float = 0F
    private var radius: Float = 0F

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }


    override fun onDraw(canvas: Canvas?) {
        rect.left = 50
        rect.top = 50
        rect.right = rect.left + squareSize
        rect.bottom = rect.top + squareSize


        if (circleX == 0F || circleY == 0F) {
            circleX = (width / 2).toFloat()
            circleY = (height / 2).toFloat()
        }

        canvas?.drawRect(rect, rectPaint)
        canvas?.drawCircle(circleX, circleY, radius, circlePaint)

        super.onDraw(canvas)
    }

    private fun init(@Nullable set: AttributeSet?) {
        if (set == null) return

        rect = Rect()
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)


        val ta: TypedArray = context.obtainStyledAttributes(set, R.styleable.CustomView)
        squareColor = ta.getColor(R.styleable.CustomView_square_color, Color.GREEN)
        rectPaint.color = squareColor
        squareSize = ta.getDimensionPixelSize(R.styleable.CustomView_square_size, 200)

        circlePaint.color = ta.getColor(R.styleable.CustomView_circle_color, Color.GREEN)
        radius = ta.getDimensionPixelSize(R.styleable.CustomView_circle_radius, 100).toFloat()

        ta.recycle()
    }

    fun swapColor() {
        if (rectPaint.color == Color.RED)
            rectPaint.color = squareColor
        else rectPaint.color = Color.RED

        postInvalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val value: Boolean = super.onTouchEvent(event)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("AAAA", "Kek")
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                Log.d("AAA", "Kek")
                val x: Float = event.x
                val y: Float = event.y

                val dx: Double = ((x - circleX).toDouble()).pow(2.0)
                val dy: Double = ((y - circleY).toDouble()).pow(2.0)

                if (dx + dy < (radius.toDouble().pow(2.0))) {
                    circleX = x
                    circleY = y
                    postInvalidate()
                    return true
                }
                return value
            }
        }
        return value
    }
}