package app.ytak.superheroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import app.ytak.superheroes.databinding.MainActivityBinding
import app.ytak.superheroes.features.comics.ComicsViewModel
import dev.chrisbanes.insetter.setEdgeToEdgeSystemUiFlags
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : AppCompatActivity() {

    private val binding by lazy { MainActivityBinding.inflate(layoutInflater) }
    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val comicsViewModel by viewModel<ComicsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setEdgeToEdgeSystemUiFlags(true)
        setContentView(binding.root)
        lifecycle.addObserver(comicsViewModel)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
