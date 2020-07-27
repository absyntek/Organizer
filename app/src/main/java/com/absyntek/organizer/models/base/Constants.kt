package com.absyntek.organizer.models.base

import com.absyntek.organizer.R

enum class BoxSizeEnum(val stringResource: Int) {
    XXL(R.string.xxl),
    XL(R.string.xl),
    L(R.string.l),
    M(R.string.m),
    S(R.string.s),
    XS(R.string.xs)
}

enum class BoxUtilityEnum(val stringResource: Int){
    DRY(R.string.dry),
    BLOOM(R.string.bloom),
    GROW(R.string.grow),
    ALL(R.string.all)
}

enum class SystemKindEnum{
    CUTTING,
    SEEDS,
    GROW,
    BLOOM
}