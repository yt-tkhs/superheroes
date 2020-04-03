package app.ytak.superheroes.comics

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import app.ytak.superheroes.R
import app.ytak.superheroes.databinding.ComicsCarouselLoadingFragmentBinding
import app.ytak.superheroes.util.autoCleared
import dev.chrisbanes.insetter.doOnApplyWindowInsets

class ComicsCarouselLoadingFragment : Fragment(R.layout.comics_carousel_loading_fragment) {

    companion object {
        fun newInstance() = ComicsCarouselLoadingFragment()
    }

    private var binding by autoCleared<ComicsCarouselLoadingFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ComicsCarouselLoadingFragmentBinding.bind(view)

        binding.thumbnailView.doOnApplyWindowInsets { _, insets, initialState ->
            binding.thumbnailView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.systemWindowInsets.top + initialState.margins.top
                bottomMargin = resources.getDimensionPixelSize(R.dimen.radius_comics_surface) +
                        resources.getDimensionPixelSize(R.dimen.margin_x_large)
            }
        }
    }
}
