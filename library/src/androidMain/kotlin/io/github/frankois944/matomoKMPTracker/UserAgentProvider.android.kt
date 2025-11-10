@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package io.github.frankois944.matomoKMPTracker

import android.content.Context
import android.os.Build
import android.webkit.WebSettings
import io.github.frankois944.matomoKMPTracker.context.ContextObject
import io.ktor.http.encodeURLParameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Locale

internal actual object UserAgentProvider {
    fun getHttpAgent(): String? =
        runBlocking {
            val context = ContextObject.context?.get()
            requireNotNull(context) { "NativeContext must be not null" }
            withContext(Dispatchers.Default) {
                WebSettings.getDefaultUserAgent(context)
            }
        }

    fun getJVMVersion(): String? = getSystemProperty("java.vm.version")

    fun getSystemProperty(key: String): String? = System.getProperty(key)

    fun getRelease(): String = Build.VERSION.RELEASE

    fun getModel(): String = Build.MODEL

    fun getBuildId(): String = Build.ID

    actual fun getUserAgent(): String {
        val httpAgent: String? = getHttpAgent()
        val context = ContextObject.context?.get()
        if (httpAgent == null || httpAgent.startsWith("Apache-HttpClient/UNAVAILABLE (java")) {
            val dalvik = getJVMVersion() ?: "0.0.0"
            val android = getRelease()
            val model = getModel()
            val build = getBuildId()
            return String.format(
                Locale.US,
                "Dalvik/%s (Linux; U; Android %s; %s Build/%s) %s/%s",
                dalvik,
                android,
                model,
                build,
            )
        }
        return httpAgent.let {
            String.format(
                Locale.US,
                "%s %s/%s",
                it,
                if (context != null) context.applicationInfo?.name else "Unknown-App",
                if (context != null) "${context.versionName()}.${context.versionCode()}" else "Unknown-Version",
            )
        }
    }

    internal fun Context.versionName(): String? =
        if (Build.VERSION.SDK_INT >= 33) {
            packageManager
                .getPackageInfo(
                    packageName,
                    android.content.pm.PackageManager.PackageInfoFlags
                        .of(0),
                ).versionName
        } else {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionName
        }

    internal fun Context.versionCode(): String? =
        if (Build.VERSION.SDK_INT >= 33) {
            packageManager
                .getPackageInfo(
                    packageName,
                    android.content.pm.PackageManager.PackageInfoFlags
                        .of(0),
                ).longVersionCode
                .toString()
        } else {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, 0).versionCode.toString()
        }

    actual fun getClientHint(): String =
        (
            "{" +
                "\"versionNum\": \"42\"" +
                "," +
                "\"versionBuild\": \"1\"" +
                "}"
        )
}
