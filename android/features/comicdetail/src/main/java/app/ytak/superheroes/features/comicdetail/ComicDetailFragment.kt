package app.ytak.superheroes.features.comicdetail

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.navArgs
import app.ytak.superheroes.common.extfun.applyMargin
import app.ytak.superheroes.common.extfun.load
import app.ytak.superheroes.common.extfun.observeNotNull
import app.ytak.superheroes.core.AppDuration
import app.ytak.superheroes.core.AsyncState
import app.ytak.superheroes.core.LeftTopRoundOutlineProvider
import app.ytak.superheroes.core.autoCleared
import app.ytak.superheroes.data.model.Id
import app.ytak.superheroes.features.comicdetail.databinding.ComicDetailFragmentBinding
import app.ytak.superheroes.features.comicdetail.item.DescriptionItem
import app.ytak.superheroes.features.comicdetail.item.LinkItem
import app.ytak.superheroes.features.comicdetail.item.TitleItem
import coil.transform.BlurTransformation
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dev.chrisbanes.insetter.doOnApplyWindowInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ComicDetailFragment : Fragment(R.layout.comic_detail_fragment) {

    private val args by navArgs<ComicDetailFragmentArgs>()
    private val comicId by lazy { Id.Comic(args.comicId) }
    private val viewModel by viewModel<ComicDetailViewModel>()

    private var binding by autoCleared<ComicDetailFragmentBinding>()
    private val groupAdapter by autoCleared<GroupAdapter<GroupieViewHolder>> { GroupAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = AppDuration.SLOW
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            pathMotion = MaterialArcMotion()
            interpolator = FastOutSlowInInterpolator()
            startViewId = R.id.thumbnailImage
            endViewId = R.id.rootLayout
            scrimColor = ResourcesCompat.getColor(resources, R.color.black, requireContext().theme)
            fadeProgressThresholds = MaterialContainerTransform.ProgressThresholds(0f, 0.5f)
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = AppDuration.SLOW
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            pathMotion = MaterialArcMotion()
            interpolator = FastOutSlowInInterpolator()
            startViewId = R.id.rootLayout
            endViewId = R.id.thumbnailImage
        }
    }

    @UseExperimental(ExperimentalStdlibApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        binding = ComicDetailFragmentBinding.bind(view)
        binding.rootLayout.transitionName = "comic_detail_${comicId.value}"
        binding.recyclerView.run {
            outlineProvider = LeftTopRoundOutlineProvider(32)
            clipToOutline = true
            adapter = groupAdapter
        }

        binding.thumbnailImage.doOnApplyWindowInsets { _, insets, initialState ->
            binding.thumbnailImage.applyMargin(
                top = initialState.margins.top + insets.systemWindowInsets.top,
                bottom = initialState.margins.bottom + resources.getDimensionPixelSize(R.dimen.radius_comics_surface)
            )
        }

        viewModel.comic.observeNotNull(viewLifecycleOwner) { state ->
            val comic = (state as? AsyncState.Success)?.data?.getOrNull() ?: return@observeNotNull
            binding.headerBackgroundImage.load(comic.thumbnail) {
                crossfade(true)
                transformations(BlurTransformation(requireContext()))
            }
            binding.thumbnailImage.load(comic.thumbnail) {
                crossfade(true)
                listener(onSuccess = { _, _ -> startPostponedEnterTransition() })
            }
            groupAdapter.updateAsync(buildList {
                add(TitleItem(comic))
                add(
                    LinkItem(
                        comic = comic,
                        onClickViewDetail = {
                            Timber.d("onClickViewDetail: ${it.url}")
                        },
                        onClickBuyNow = {
                            Timber.d("onClickBuyNow: ${it.url}")
                        }
                    )
                )
                comic.textObjects.firstOrNull()?.let {
                    add(DescriptionItem(it))
                }
            })
        }

        viewModel.getComic(comicId)
    }
}
