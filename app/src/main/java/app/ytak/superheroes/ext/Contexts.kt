package app.ytak.superheroes.ext

import android.content.Context
import app.ytak.superheroes.R

val Context.bottomNavigationHeight: Int
    get() = resources.getDimensionPixelSize(R.dimen.bottom_navigation_height)
