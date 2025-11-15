package io.github.frankois944.matomoKMPTracker.context

import android.content.Context
import java.lang.ref.WeakReference

public object ContextObject {
    public var context: WeakReference<Context>? = null
}
