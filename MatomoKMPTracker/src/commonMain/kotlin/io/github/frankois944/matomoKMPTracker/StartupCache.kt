package io.github.frankois944.matomoKMPTracker

import io.github.frankois944.matomoKMPTracker.core.Event
import io.github.frankois944.matomoKMPTracker.core.Visitor
import io.github.frankois944.matomoKMPTracker.core.queue.Queue
import io.github.frankois944.matomoKMPTracker.preferences.UserPreferences
import io.github.frankois944.matomoKMPTracker.utils.ConcurrentMutableList
import kotlin.jvm.JvmInline

@JvmInline
internal value class UserId(
    val value: String?,
)

@JvmInline
internal value class IsOptOut(
    val value: Boolean,
)

@JvmInline
internal value class AdUserDataEnabled(
    val value: Boolean?,
)

@JvmInline
internal value class AdPersonalizationEnabled(
    val value: Boolean?,
)

internal class StartupCache(
    val events: ConcurrentMutableList<Event> = ConcurrentMutableList(),
    var userId: UserId? = null,
    var adUserDataEnabled: AdUserDataEnabled? = null,
    var adPersonalizationEnabled: AdPersonalizationEnabled? = null,
    var isOptOut: IsOptOut? = null,
) {
    suspend fun addEvent(event: Event) {
        events.add(event)
    }

    suspend fun flush(
        queue: Queue,
        userPreferences: UserPreferences,
    ) {
        userId?.let { userId ->
            userPreferences.setUserId(userId.value)
        }
        isOptOut?.let { isOptOut ->
            userPreferences.setOptOut(isOptOut.value)
        }
        events.getAll().forEach { event ->
            if (event.visitor == null) {
                event.visitor = Visitor.current(userPreferences)
            }
        }
        queue.enqueue(events.getAll())
    }
}
