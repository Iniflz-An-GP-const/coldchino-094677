package nazema.coldchino.info.ui.theme.motion

import androidx.compose.animation.core.Spring
import nazema.coldchino.info.ui.theme.motion.Motion

val AppMotion = Motion(
    pageEffectStrength = 0.8f,
    settleDampingRatio = Spring.DampingRatioNoBouncy,
    settleStiffness = Spring.StiffnessMedium,
    crossfadeMillis = 150,
)
