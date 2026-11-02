package nazema.coldchino.info.ui.composable.screen.game.result.appear

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

private const val DURATION_MS = 380
private val RiseDistance = 40.dp

@Composable
fun Modifier.solvedAppear(): Modifier {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(durationMillis = DURATION_MS, easing = FastOutSlowInEasing))
    }

    return graphicsLayer {
        translationY = (1f - progress.value) * RiseDistance.toPx()
        alpha = progress.value
    }
}
