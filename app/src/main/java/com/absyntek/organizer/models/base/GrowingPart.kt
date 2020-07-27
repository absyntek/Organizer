package com.absyntek.organizer.models.base

abstract class GrowingPart(
    val name: String,
    val dry: Double? = null,
    val cleaning: Double? = null,
    val flush: Double? = null,
    val ripening: Double? = null,
    val flowering: Double? = null,
    val preFlowering: Double? = null,
    val growing: Double? = null,
    val firstLeaves: Double? = null,
    val firstRoot: Double? = null,
    val seeds: Double? = null,
    val cut: Double? = null)