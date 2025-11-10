package io.github.frankois944.matomokmptracker.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform