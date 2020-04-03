package app.ytak.superheroes.util

import android.content.res.Resources
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.Dimension

class LeftTopRoundOutlineProvider(@Dimension(unit = Dimension.DP) cornerRadiusDp: Int) : ViewOutlineProvider() {

    private val cornerRadiusPx by lazy {
        Resources.getSystem().displayMetrics.density * cornerRadiusDp
    }

    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(
            0,
            0,
            (view.width + cornerRadiusPx).toInt(),
            (view.height + cornerRadiusPx).toInt(),
            cornerRadiusPx
        )
    }
}
