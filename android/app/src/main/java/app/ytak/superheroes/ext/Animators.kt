package app.ytak.superheroes.ext

import android.animation.*
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import app.ytak.superheroes.util.AppDuration

fun <T : Animator> T.setListenerExt(
    onAnimationStart: ((Animator) -> Unit)? = null,
    onAnimationEnd: ((Animator) -> Unit)? = null,
    onAnimationRepeat: ((Animator) -> Unit)? = null,
    onAnimationCancel: ((Animator) -> Unit)? = null
) = apply {

    addListener(object : Animator.AnimatorListener {

        override fun onAnimationStart(animator: Animator) {
            onAnimationStart?.invoke(animator)
        }

        override fun onAnimationEnd(animator: Animator) {
            onAnimationEnd?.invoke(animator)
        }

        override fun onAnimationRepeat(animator: Animator) {
            onAnimationRepeat?.invoke(animator)
        }

        override fun onAnimationCancel(animator: Animator) {
            onAnimationCancel?.invoke(animator)
        }
    })
}

fun <T : Animator> T.setInterpolatorExt(interpolator: TimeInterpolator) =
    apply { setInterpolator(interpolator) }

fun <T : Animator> T.setDurationExt(duration: Long) = apply { setDuration(duration) }

fun <T : Animator> T.setStartDelayExt(startDelay: Long) = apply { this.startDelay = startDelay }

fun ValueAnimator.addUpdateListenerExt(onUpdate: (ValueAnimator) -> Unit) =
    apply { addUpdateListener(onUpdate) }

fun AnimatorSet.playTogetherExt(vararg animator: Animator) = apply { playTogether(*animator) }

fun ObjectAnimator.setAutoCancelExt(autoCancel: Boolean) = apply { setAutoCancel(autoCancel) }

fun View.animateScaleX(from: Float, to: Float) =
    ObjectAnimator.ofFloat(this, View.SCALE_X, from, to)

fun View.animateScaleY(from: Float, to: Float) =
    ObjectAnimator.ofFloat(this, View.SCALE_Y, from, to)

fun View.animateAlpha(from: Float, to: Float) =
    ObjectAnimator.ofFloat(this, View.ALPHA, from, to)

fun View.animateTranslationY(from: Float, to: Float) =
    ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, from, to)

fun View.fadeOut(
    duration: Long = AppDuration.NORMAL,
    interpolator: TimeInterpolator = FastOutSlowInInterpolator(),
    onAnimationEnd: (() -> Unit)? = null,
    isForced: Boolean = false
) {
    if (!isForced && (isGone || isInvisible) && alpha == 0f) return

    (tag as? Animator)?.cancel()
    animateAlpha(alpha, 0f)
        .setDurationExt(duration)
        .setInterpolatorExt(interpolator)
        .setAutoCancelExt(true)
        .setListenerExt(onAnimationStart = {
            tag = it
        }, onAnimationEnd = {
            toGone()
            onAnimationEnd?.invoke()
            tag = null
        })
        .start()
}

fun View.fadeIn(
    duration: Long = AppDuration.NORMAL,
    interpolator: TimeInterpolator = FastOutSlowInInterpolator(),
    isForced: Boolean = false
) {
    if (!isForced && isVisible && alpha == 1f) return

    (tag as? Animator)?.cancel()
    animateAlpha(if (!isVisible) 0f else alpha, 1f)
        .setDurationExt(duration)
        .setInterpolatorExt(interpolator)
        .setAutoCancelExt(true)
        .setListenerExt(onAnimationStart = {
            toVisible()
            tag = it
        }, onAnimationEnd = {
            tag = null
        })
        .start()
}
