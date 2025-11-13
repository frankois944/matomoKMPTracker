@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package io.github.frankois944.matomoKMPTracker.database.factory

import app.cash.sqldelight.db.SqlDriver
import io.github.frankois944.matomoKMPTracker.schema.CacheDatabase

public expect class DriverFactory() {
    public suspend fun createDriver(dbName: String): SqlDriver
}

public suspend fun createDatabase(
    driverFactory: DriverFactory,
    dbName: String,
): CacheDatabase {
    val driver = driverFactory.createDriver(dbName)
    val database = CacheDatabase(driver)
    return database
}
