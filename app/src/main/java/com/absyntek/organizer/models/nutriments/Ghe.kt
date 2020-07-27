package com.absyntek.organizer.models.nutriments

import com.absyntek.organizer.models.base.GrowingPart

class TriPart{
    class Grow: GrowingPart(
        name = "TriPart Grow (T.A. - GHE)",
        firstRoot = 0.5,
        firstLeaves = 1.0,
        growing = 1.8,
        preFlowering = 2.0,
        flowering = 0.8
    )

    class Micro: GrowingPart(
        name = "TriPart Micro (T.A. - GHE)",
        firstRoot = 0.5,
        firstLeaves = 1.0,
        growing = 1.2,
        preFlowering = 2.0,
        flowering = 1.6
    )

    class Bloom: GrowingPart(
        name = "TriPart Bloom (T.A. - GHE)",
        firstRoot = 0.5,
        firstLeaves = 1.0,
        growing = 0.6,
        preFlowering = 1.5,
        flowering = 2.4
    )

    class FinalPart : GrowingPart(
        name = "Final Part (T.A. - GHE)",
        ripening = 5.0
    )

    class FlashClean : GrowingPart(
        name = "Final Part (T.A. - GHE)",
        flush = 2.0
    )
}