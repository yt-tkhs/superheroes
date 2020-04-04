package app.ytak.superheroes.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class WrapContentViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    init {
        addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                requestLayout()
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val child: View? = getChildAt(currentItem)
        val newHeightMeasureSpec = if (child != null) {
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            MeasureSpec.makeMeasureSpec(child.measuredHeight, MeasureSpec.EXACTLY)
        } else {
            (0 until childCount)
                .map { index ->
                    val child2: View = getChildAt(index)
                    child2.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
                    child2.measuredHeight
                }
                .max()
                ?.let { maxHeight -> MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY) }
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec ?: heightMeasureSpec)
    }
}
