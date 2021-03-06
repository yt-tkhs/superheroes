package app.ytak.superheroes.features.comics

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import app.ytak.superheroes.common.extfun.bottomNavigationHeight
import app.ytak.superheroes.common.extfun.fadeIn
import app.ytak.superheroes.common.extfun.observeNotNull
import app.ytak.superheroes.core.*
import app.ytak.superheroes.features.comics.databinding.ComicsFragmentBinding
import app.ytak.superheroes.features.comics.item.ComicItem
import app.ytak.superheroes.features.comics.item.SearchBarItem
import app.ytak.superheroes.features.comics.item.SubHeaderItem
import com.google.android.material.transition.Hold
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import dev.chrisbanes.insetter.doOnApplyWindowInsets
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class ComicsFragment : Fragment(R.layout.comics_fragment) {

    companion object {
        private const val KEY_LAYOUT_MANAGER = "layout_manager"
    }

    private var binding by autoCleared<ComicsFragmentBinding>()
    private val viewModel by sharedViewModel<ComicsViewModel>()
    private val viewPagerAdapter by lazy { ComicsCarouselPagerAdapter(childFragmentManager, false) }
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold().apply {
            duration = AppDuration.SLOW
        }
    }

    @UseExperimental(ExperimentalStdlibApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        requireView().doOnPreDraw { startPostponedEnterTransition() }
        binding = ComicsFragmentBinding.bind(view)
        binding.recyclerView.run {
            outlineProvider = LeftTopRoundOutlineProvider(32)
            clipToOutline = true
            layoutManager = GridLayoutManager(requireContext(), 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        when (groupAdapter.getItem(position)) {
                            is SearchBarItem,
                            is SubHeaderItem -> 3
                            is ComicItem -> 1
                            else -> 1
                        }
                }
            }
            adapter = groupAdapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.recyclerView.doOnApplyWindowInsets { _, insets, _ ->
            binding.recyclerView.updatePaddingRelative(
                bottom = requireContext().bottomNavigationHeight + insets.systemWindowInsets.bottom
            )
        }

        viewModel.thisWeekComics.observeNotNull(viewLifecycleOwner) { state ->
            when (state) {
                is AsyncState.Ready,
                is AsyncState.Loading -> {
                    binding.viewPager.adapter = ComicsCarouselPagerAdapter(childFragmentManager, true)
                }
                is AsyncState.Success -> {
                    binding.viewPager.adapter = ComicsCarouselPagerAdapter(childFragmentManager, false).apply {
                        update(state.data)
                    }
                    binding.tabLayout.fadeIn()
                }
            }
        }

        viewModel.thisMonthComics.observeNotNull(viewLifecycleOwner) { state ->
            when (state) {
                is AsyncListState.Ready -> {
                    Timber.d("Ready")
                }
                is AsyncListState.Loading -> {
                    Timber.d("Loading: ${state.data.size}")
                }
                is AsyncListState.Success -> {
                    Timber.d("Success: ${state.data.size}")
                    groupAdapter.updateAsync(buildList<Item<*>> {
                        add(SearchBarItem())
                        add(SubHeaderItem(R.string.comics_sub_header_releasing_this_month))
                        addAll(state.data.map { comic ->
                            ComicItem(comic) { view, _ ->
                                findNavController().navigate(
                                    ComicsFragmentDirections.toComicDetail(comic.id.value),
                                    FragmentNavigatorExtras(view.root to view.root.transitionName)
                                )
                            }
                        })
                    })
                }
                is AsyncListState.Failure -> {
                    Timber.d("Failure: ${state.data.size}")
                }
            }
        }
    }
}
