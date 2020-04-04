package app.ytak.superheroes.common.extfun

import android.content.Context

val Context.bottomNavigationHeight: Int
    get() = resources.getDimensionPixelSize(R.dimen.bottom_navigation_height)
