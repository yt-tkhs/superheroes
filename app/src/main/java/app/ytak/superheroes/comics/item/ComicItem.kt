package app.ytak.superheroes.comics.item

import app.ytak.superheroes.R
import app.ytak.superheroes.data.dto.Comic
import app.ytak.superheroes.databinding.ComicsComicItemBinding
import app.ytak.superheroes.ext.dp
import app.ytak.superheroes.util.RoundOutlineProvider
import coil.api.load
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ComicItem(private val comic: Comic) : Item<GroupieViewHolder>(comic.id.toLong()) {

    override fun getLayout(): Int = R.layout.comics_comic_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicsComicItemBinding.bind(viewHolder.itemView).run {
            thumbnailImage.outlineProvider = RoundOutlineProvider(4.dp)
            thumbnailImage.clipToOutline = true
            thumbnailImage.load("${comic.thumbnail.path}.${comic.thumbnail.extension}") {
                crossfade(true)
                placeholder(R.color.black)
            }
            titleText.text = comic.title
        }
    }
}
