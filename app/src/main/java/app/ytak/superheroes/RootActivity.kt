package app.ytak.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.ytak.superheroes.comics.ComicsViewModel
import app.ytak.superheroes.data.api.OrderBy
import app.ytak.superheroes.data.api.comic.ComicApi
import app.ytak.superheroes.data.api.comic.query.DateDescriptor
import app.ytak.superheroes.databinding.MainActivityBinding
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RootActivity : AppCompatActivity() {

    private val binding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val comicsViewModel by viewModel<ComicsViewModel>()
    private val comicApi: ComicApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setEdgeToEdgeSystemUiFlags(true)
        setContentView(binding.root)
        lifecycle.addObserver(comicsViewModel)

        binding.bottomNavigationView.setupWithNavController(navController)

        GlobalScope.launch {
            val response = comicApi.fetchComics(
                dateDescriptor = DateDescriptor.ThisWeek,
                limit = 10,
                orderBy = OrderBy(ComicApi.OrderByAttribute.OnsaleDate).desc()
            )
            Timber.d(response.data.results.joinToString("\n") { "${it.thumbnail.path}.${it.thumbnail.extension}" })
        }
    }
}
