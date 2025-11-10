package io.github.frankois944.matomokmptracker.sample

import io.github.frankois944.matomoKMPTracker.DefaultMatomoTrackerLogger
import io.github.frankois944.matomoKMPTracker.LogLevel
import io.github.frankois944.matomoKMPTracker.NativeContext
import io.github.frankois944.matomoKMPTracker.Tracker

object MatomoTracker {

    suspend fun create(context: NativeContext? = null) : Tracker {
        current = Tracker.create(
            url = "https://matomo.spmforkmp.eu/matomo.php",
            siteId = 6,
            context = context
        ).also {
            it.logger = DefaultMatomoTrackerLogger(minLevel = LogLevel.Verbose)
        }
        return current
    }
    lateinit var current: Tracker
}