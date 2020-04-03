package app.ytak.superheroes.ext

import android.content.res.Resources

val Int.dp get(): Int = (Resources.getSystem().displayMetrics.density * this).toInt()
