package app.ytak.superheroes.common.extfun

import android.widget.ImageView
import coil.Coil
import coil.ImageLoader
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.request.RequestDisposable
import io.ktor.http.Url

inline fun ImageView.load(
    uri: Url,
    imageLoader: ImageLoader = Coil.loader(),
    builder: LoadRequestBuilder.() -> Unit = {}
): RequestDisposable {
    return load(uri.toString(), imageLoader, builder)
}
