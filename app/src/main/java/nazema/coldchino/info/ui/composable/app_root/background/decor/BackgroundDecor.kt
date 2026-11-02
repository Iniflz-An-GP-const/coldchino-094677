package nazema.coldchino.info.ui.composable.app_root.background.decor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import nazema.coldchino.info.ui.composable.app_root.background.BackgroundMotion
import nazema.coldchino.info.ui.composable.app_root.background.BackgroundPoints
import nazema.coldchino.info.ui.composable.app_root.background.drawTiled
import kotlin.math.hypot

private const val CELL_DP = 92f
private const val LINE_ALPHA = 0.16f
private const val LINE_DP = 1.4f

@Composable
fun BackgroundDecor(
    motion: BackgroundMotion,
    points: BackgroundPoints,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colorScheme.primary
    val density = LocalDensity.current.density
    val cell = CELL_DP * density
    val seams = remember(cell) { seamsTile(cell) }

    Canvas(modifier = modifier.fillMaxSize()) {
        val lineColor = color.copy(alpha = LINE_ALPHA * motion.alpha())

        drawTiled(tile = cell * 2f, shift = motion.shift()) {
            drawPath(
                path = seams,
                color = lineColor,
                style = Stroke(
                    width = LINE_DP * density,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
            )
        }
    }
}

/**
 * One repeating tile of 2 x 2 cells. Each cell contributes its right and bottom edge (the left and
 * top ones belong to the neighbours); knobs alternate direction in a checkerboard.
 */
private fun seamsTile(cell: Float): Path = Path().apply {
    for (column in 0..1) {
        for (row in 0..1) {
            val direction = if ((column + row) % 2 == 0) 1f else -1f
            val left = column * cell
            val top = row * cell

            knobEdge(left + cell, top, left + cell, top + cell, direction)
            knobEdge(left, top + cell, left + cell, top + cell, direction)
        }
    }
}

private fun Path.knobEdge(x0: Float, y0: Float, x1: Float, y1: Float, direction: Float) {
    val dx = x1 - x0
    val dy = y1 - y0
    val length = hypot(dx, dy)
    val normalX = -dy / length * direction
    val normalY = dx / length * direction

    fun at(along: Float, across: Float) = Offset(
        x = x0 + dx * along + normalX * across * length,
        y = y0 + dy * along + normalY * across * length,
    )

    moveTo(x0, y0)
    lineTo(at(0.40f, 0f))
    cubicTo(at(0.40f, 0.08f), at(0.30f, 0.10f), at(0.30f, 0.20f))
    cubicTo(at(0.30f, 0.32f), at(0.70f, 0.32f), at(0.70f, 0.20f))
    cubicTo(at(0.70f, 0.10f), at(0.60f, 0.08f), at(0.60f, 0f))
    lineTo(x1, y1)
}

private fun Path.lineTo(point: Offset) = lineTo(point.x, point.y)

private fun Path.cubicTo(
    first: Offset,
    second: Offset,
    end: Offset,
) = cubicTo(first.x, first.y, second.x, second.y, end.x, end.y)
