package app.ytak.superheroes.features.comics

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import app.ytak.superheroes.common.extfun.dp
import app.ytak.superheroes.common.extfun.observeNotNull
import app.ytak.superheroes.core.AsyncState
import app.ytak.superheroes.core.autoCleared
import app.ytak.superheroes.features.comics.databinding.ComicsCarouselComicFragmentBinding
import app.ytak.superheroes.core.RoundOutlineProvider
import coil.api.load
import coil.transform.BlurTransformation
import com.soywiz.klock.KlockLocale
import dev.chrisbanes.insetter.doOnApplyWindowInsets
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ComicsCarouselComicFragment : Fragment(R.layout.comics_carousel_comic_fragment) {

    companion object {
        private const val KEY_COMIC_ID = "comic_id"

        fun newInstance(comicId: Int) = ComicsCarouselComicFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_COMIC_ID, comicId)
            }
        }
    }

    private val comicId by lazy { requireNotNull(requireArguments().getInt(KEY_COMIC_ID)) }

    private val viewModel by sharedViewModel<ComicsViewModel>()
    private var binding by autoCleared<ComicsCarouselComicFragmentBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ComicsCarouselComicFragmentBinding.bind(view)

        binding.thumbnailImage.outlineProvider = RoundOutlineProvider(6.dp)
        binding.thumbnailImage.clipToOutline = true

        binding.thumbnailImage.doOnApplyWindowInsets { _, insets, initialState ->
            binding.thumbnailImage.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.systemWindowInsets.top + initialState.margins.top
                bottomMargin = resources.getDimensionPixelSize(R.dimen.radius_comics_surface) +
                        resources.getDimensionPixelSize(R.dimen.margin_x_large)
            }
        }

        viewModel.thisWeekComics.observeNotNull(viewLifecycleOwner) { state ->
            if (state !is AsyncState.Success) return@observeNotNull

            val comic = state.data.firstOrNull { it.id == comicId } ?: return@observeNotNull
            val imageUrl = "${comic.thumbnail.path}.${comic.thumbnail.extension}"
            binding.thumbnailImage.load(imageUrl) {
                crossfade(true)
            }
            binding.backgroundImage.load(imageUrl) {
                crossfade(true)
                transformations(BlurTransformation(requireContext()))
            }

            comic.dates.firstOrNull { it.type == "onsaleDate" }?.let { comicDate ->
                binding.onsaleDateText.visibility = View.VISIBLE
                binding.onsaleDateText.text = KlockLocale.default.formatDateLong.format(comicDate.date)
            } ?: run {
                binding.onsaleDateText.visibility = View.GONE
            }

            binding.titleText.text = comic.title
        }
    }
}
