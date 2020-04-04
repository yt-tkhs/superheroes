package app.ytak.superheroes.common.extfun

import android.content.res.Resources

val Int.dp get(): Int = (Resources.getSystem().displayMetrics.density * this).toInt()
