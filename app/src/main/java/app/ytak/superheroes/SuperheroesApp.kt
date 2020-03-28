package app.ytak.superheroes

import android.app.Application
import app.ytak.superheroes.core.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class SuperheroesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        initKoin {
            androidContext(this@SuperheroesApp)
        }
    }
}
