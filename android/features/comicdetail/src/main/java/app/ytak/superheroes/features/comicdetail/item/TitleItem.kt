package app.ytak.superheroes.features.comicdetail.item

import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.features.comicdetail.R
import app.ytak.superheroes.features.comicdetail.databinding.ComicTitleItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class TitleItem(private val comic: Comic) : Item<GroupieViewHolder>() {

    override fun getLayout(): Int = R.layout.comic_title_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicTitleItemBinding.bind(viewHolder.itemView).run {
            titleText.text = comic.title
            metaText.text =
                "$${comic.prices.first().price}・Onsale on ${comic.dates.first().date.format("MMMM dd, yyyy")}"
        }
    }
}
