package app.ytak.superheroes.features.comicdetail.item

import app.ytak.superheroes.data.model.TextObject
import app.ytak.superheroes.features.comicdetail.R
import app.ytak.superheroes.features.comicdetail.databinding.ComicDescriptionItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class DescriptionItem(private val textObject: TextObject) :
    Item<GroupieViewHolder>("DescriptionItem".hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.comic_description_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicDescriptionItemBinding.bind(viewHolder.itemView).run {
            descriptionText.text = textObject.text
        }
    }
}
