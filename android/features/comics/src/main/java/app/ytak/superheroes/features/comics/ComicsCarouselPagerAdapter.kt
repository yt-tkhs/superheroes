package app.ytak.superheroes.features.comics

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import app.ytak.superheroes.data.dto.ResponseComic
import app.ytak.superheroes.data.model.Comic

class ComicsCarouselPagerAdapter(
    fm: FragmentManager,
    private val isLoading: Boolean
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val comics = mutableListOf<Comic>()

    override fun getItem(position: Int): Fragment = if (isLoading) {
        ComicsCarouselLoadingFragment.newInstance()
    } else {
        ComicsCarouselComicFragment.newInstance(comics[position].id)
    }

    override fun getCount(): Int = if (isLoading) 1 else comics.size

    fun update(comics: List<Comic>) {
        if (isLoading) return
        this.comics.clear()
        this.comics.addAll(comics)
        notifyDataSetChanged()
    }
}
