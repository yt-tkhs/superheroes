package app.ytak.superheroes.comics.item

import app.ytak.superheroes.R
import app.ytak.superheroes.databinding.ComicsSearchBarItemBinding
import app.ytak.superheroes.util.RoundOutlineProvider
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class SearchBarItem() : Item<GroupieViewHolder>("SearchBarItem".hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.comics_search_bar_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicsSearchBarItemBinding.bind(viewHolder.itemView).run {
            searchView.outlineProvider = RoundOutlineProvider(48)
            searchView.clipToOutline = true
        }
    }
}
