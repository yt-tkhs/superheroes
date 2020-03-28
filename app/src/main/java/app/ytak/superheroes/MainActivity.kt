package app.ytak.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ytak.superheroes.data.api.ComicApi
import app.ytak.superheroes.data.api.query.DateDescriptor
import app.ytak.superheroes.data.api.query.OrderBy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val comicApi: ComicApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
