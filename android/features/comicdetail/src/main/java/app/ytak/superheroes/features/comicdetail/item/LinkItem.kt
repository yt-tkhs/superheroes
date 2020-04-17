package app.ytak.superheroes.features.comicdetail.item

import app.ytak.superheroes.common.extfun.toGone
import app.ytak.superheroes.common.extfun.toVisible
import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.data.model.TypeUrl
import app.ytak.superheroes.data.type.UrlType
import app.ytak.superheroes.features.comicdetail.R
import app.ytak.superheroes.features.comicdetail.databinding.ComicLinkItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class LinkItem(
    private val comic: Comic,
    private val onClickViewDetail: ((TypeUrl) -> Unit)? = null,
    private val onClickBuyNow: ((TypeUrl) -> Unit)? = null
) : Item<GroupieViewHolder>("LinkItem".hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.comic_link_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicLinkItemBinding.bind(viewHolder.itemView).run {
            comic.urls.firstOrNull { it.type == UrlType.Detail }?.let { url ->
                viewDetailButton.toVisible()
                viewDetailButton.setOnClickListener { onClickViewDetail?.invoke(url) }
            } ?: run {
                viewDetailButton.toGone()
            }
            comic.urls.firstOrNull { it.type == UrlType.Purchase }?.let { url ->
                buyNowButton.toVisible()
                buyNowButton.setOnClickListener { onClickBuyNow?.invoke(url) }
            } ?: run {
                buyNowButton.toGone()
            }
        }
    }
}
