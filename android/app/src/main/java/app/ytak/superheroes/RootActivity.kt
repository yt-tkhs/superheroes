package app.ytak.superheroes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.ytak.superheroes.common.extfun.*
import app.ytak.superheroes.core.AppDuration
import app.ytak.superheroes.databinding.MainActivityBinding
import app.ytak.superheroes.features.comics.ComicsViewModel
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : AppCompatActivity() {

    private val binding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val comicsViewModel by viewModel<ComicsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setEdgeToEdgeSystemUiFlags(true)
        setContentView(binding.root)
        lifecycle.addObserver(comicsViewModel)

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.destination_comics,
                R.id.destination_series,
                R.id.destination_characters -> {
                    if (binding.bottomNavigationView.visibility == View.VISIBLE)
                        return@addOnDestinationChangedListener

                    binding.bottomNavigationView.doOnPreDraw {
                        binding.bottomNavigationView
                            .animateTranslationY(
                                it.measuredHeight.toFloat(),
                                0f
                            )
                            .setListenerExt(
                                onAnimationStart = { binding.bottomNavigationView.toVisible() },
                                onAnimationEnd = { binding.bottomNavigationView.isEnabled = true }
                            )
                            .setInterpolatorExt(FastOutSlowInInterpolator())
                            .setDuration(AppDuration.NORMAL)
                            .start()
                    }
                }
                else -> {
                    if (binding.bottomNavigationView.visibility == View.INVISIBLE)
                        return@addOnDestinationChangedListener

                    binding.bottomNavigationView.doOnPreDraw {
                        binding.bottomNavigationView
                            .animateTranslationY(
                                0f,
                                it.measuredHeight.toFloat()
                            )
                            .setListenerExt(
                                onAnimationStart = { binding.bottomNavigationView.isEnabled = false },
                                onAnimationEnd = { binding.bottomNavigationView.toInvisible() }
                            )
                            .setInterpolatorExt(FastOutSlowInInterpolator())
                            .setDuration(AppDuration.NORMAL)
                            .start()
                    }
                }
            }
        }
    }
}
