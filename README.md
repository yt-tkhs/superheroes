<img src="https://github.com/yt-tkhs/superheroes/blob/master/art/comics.webp" width="250" align="right" width="30%"/>
    
# Superheroes

Superheros is an experimental project for Kotlin multiplatform using [the Marvel Comics API](https://developer.marvel.com/).

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />

## Architecture

TBD

## Libraries

TBD

## How to Build

First, you need to use Android Studio 4.0.0-beta03 (or higher) to be able to build the app.

### API Key

You need to set followings key-values in `local.properties`.
The values are read at [here](https://github.com/yt-tkhs/superheroes/blob/master/shared/data/build.gradle.kts).

| Key | Value |
|:----|:------|
| `marvel.publicKey` | Public key for the Marvel Comics API  |
| `marvel.privateKey` | Private key for the Marvel Comics API |

Example:

```
marvel.publicKey=<YOUR_PUBLIC_KEY>
marvel.privateKey=<YOUR_PRIVATE_KEY>
```

If you don't have these keys yet, can get it from [Marvel developer portal](https://developer.marvel.com/documentation/getting_started).

## References

- This project is built based on [touchlab/KaMPKit](https://github.com/touchlab/KaMPKit).

- The Marvel Comics API documentation is here: [https://developer.marvel.com/docs](https://developer.marvel.com/docs).

## License

See [LICENSE](https://github.com/yt-tkhs/superheroes/blob/master/LICENSE).

```
Copyright 2020 Yuta Takahashi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
