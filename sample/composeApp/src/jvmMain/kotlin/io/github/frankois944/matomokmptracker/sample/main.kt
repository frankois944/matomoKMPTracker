package io.github.frankois944.matomokmptracker.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.frankois944.matomoKMPTracker.DefaultMatomoTrackerLogger
import io.github.frankois944.matomoKMPTracker.LogLevel
import io.github.frankois944.matomoKMPTracker.Tracker
import kotlinx.coroutines.runBlocking

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MatomoKmpTrackerSample",
    ) {
        App()
    }
}