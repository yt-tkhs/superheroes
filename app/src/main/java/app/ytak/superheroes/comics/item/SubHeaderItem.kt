package app.ytak.superheroes.comics.item

import androidx.annotation.StringRes
import app.ytak.superheroes.R
import app.ytak.superheroes.databinding.ComicsSubHeaderItemBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class SubHeaderItem(@StringRes private val textRes: Int) : Item<GroupieViewHolder>(textRes.toLong()) {

    override fun getLayout(): Int = R.layout.comics_sub_header_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        ComicsSubHeaderItemBinding.bind(viewHolder.itemView).run {
            subHeaderText.setText(textRes)
        }
    }
}
