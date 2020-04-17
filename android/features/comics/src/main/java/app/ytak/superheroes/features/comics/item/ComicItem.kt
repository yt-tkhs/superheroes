package app.ytak.superheroes.features.comics.item

import app.ytak.superheroes.common.extfun.dp
import app.ytak.superheroes.common.extfun.load
import app.ytak.superheroes.core.RoundOutlineProvider
import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.features.comics.R
import app.ytak.superheroes.features.comics.databinding.ComicsComicItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ComicItem(
    private val comic: Comic,
    private val onClick: ((Comic) -> Unit)? = null
) : Item<GroupieViewHolder>(comic.id.longHashCode) {

    override fun getLayout(): Int = R.layout.comics_comic_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicsComicItemBinding.bind(viewHolder.itemView).run {
            thumbnailImage.outlineProvider = RoundOutlineProvider(4.dp)
            thumbnailImage.clipToOutline = true
            thumbnailImage.load(comic.thumbnail) {
                crossfade(true)
                placeholder(R.color.black)
            }
            titleText.text = comic.title
            onClick?.let { thumbnailImage.setOnClickListener { onClick.invoke(comic) } }
        }
    }

    override fun equals(other: Any?): Boolean =
        other is ComicItem && comic == other.comic

    override fun hashCode(): Int = comic.hashCode()
}
