@file:OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)

package io.github.frankois944.matomoKMPTracker.core

import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi

@Serializable
public class Event(
    public val dateCreatedInSecond: Long,
    public val dateCreatedOfNanoSecond: Long,
    public val uuid: String,
    public val siteId: Int,
    public var visitor: Visitor?,
    public val isCustomAction: Boolean,
    public val date: Long,
    public val url: String?,
    public val actionName: List<String>,
    public val language: String?,
    public var isNewSession: Boolean,
    public val referer: String?,
    public val screenResolution: Size,
    public val eventCategory: String?,
    public val eventAction: String?,
    public val eventName: String?,
    public val eventValue: Float?,
    public val campaignName: String?,
    public val campaignKeyword: String?,
    public val searchQuery: String?,
    public val searchCategory: String?,
    public val searchResultsCount: Int?,
    public val dimensions: List<CustomDimension>,
    public val customTrackingParameters: Map<String, String>,
    public val contentName: String?,
    public val contentPiece: String?,
    public val contentTarget: String?,
    public val contentInteraction: String?,
    public val goalId: Int?,
    public val revenue: Float?,
    public val orderId: String?,
    public val orderItems: List<OrderItem>,
    public val orderRevenue: Float?,
    public val orderSubTotal: Float?,
    public val orderTax: Float?,
    public val orderShippingCost: Float?,
    public val orderDiscount: Float?,
    public val isPing: Boolean,
) {
    public companion object
}
