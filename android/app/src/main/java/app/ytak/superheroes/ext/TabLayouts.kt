package app.ytak.superheroes.ext

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun TabLayout.setupWithViewPager2(
    viewPager: ViewPager2,
    tabConfigurationStrategy: (tab: TabLayout.Tab, position: Int) -> Unit = { _, _ -> /* no op */ }
) {
    TabLayoutMediator(this, viewPager, tabConfigurationStrategy).attach()
}
