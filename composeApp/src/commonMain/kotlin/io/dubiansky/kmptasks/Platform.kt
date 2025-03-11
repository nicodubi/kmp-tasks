package io.dubiansky.kmptasks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform