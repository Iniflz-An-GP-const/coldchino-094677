package nazema.coldchino.info.ui.composable.screen.splash.cover

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import nazema.coldchino.info.ui.composable.screen.splash.cover.CoverCornerSize
import nazema.coldchino.info.ui.composable.screen.splash.cover.CoverGap
import nazema.coldchino.info.ui.composable.screen.splash.cover.forEachCoverCell
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val FALL_DURATION_MS = 1400
private const val REVEALED_AT = 0.5f

private const val SPREAD = 0.55f

private const val ROW_BIAS = 0.35f
private const val MIN_TURN_DEGREES = 20f
private const val MAX_TURN_DEGREES = 50f

@Composable
fun SplashCover(
    onRevealed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coverColor = MaterialTheme.colorScheme.primaryContainer
    val fall = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                delay((FALL_DURATION_MS * REVEALED_AT).toLong().milliseconds)
                onRevealed()
            }
            fall.animateTo(1f, tween(durationMillis = FALL_DURATION_MS, easing = LinearEasing))
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val gap = CoverGap.toPx()
        val corner = CornerRadius(CoverCornerSize.toPx())

        forEachCoverCell { row, column, rows, center, cellSize ->
            val scatter = scatterOf(row, column)
            val rowShare = row.toFloat() / rows
            val startAt = SPREAD * ((1f - ROW_BIAS) * scatter + ROW_BIAS * (1f - rowShare))
            val progress = ((fall.value - startAt) / (1f - SPREAD)).coerceIn(0f, 1f)
            if (progress >= 1f) return@forEachCoverCell

            val distance = (size.height - center.y + cellSize) * progress * progress
            val turn = MIN_TURN_DEGREES + (MAX_TURN_DEGREES - MIN_TURN_DEGREES) * scatter
            val direction = if ((row + column) % 2 == 0) 1f else -1f
            val side = cellSize - gap

            translate(top = distance) {
                rotate(degrees = direction * turn * progress, pivot = center) {
                    drawRoundRect(
                        color = coverColor,
                        topLeft = Offset(center.x - side / 2f, center.y - side / 2f),
                        size = Size(side, side),
                        cornerRadius = corner,
                    )
                }
            }
        }
    }
}

private fun scatterOf(row: Int, column: Int): Float {
    val hash = (row * 73856093) xor (column * 19349663)
    return (hash and 0xFFFF) / 65536f
}
