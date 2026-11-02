package nazema.coldchino.info.ui.composable.screen.puzzle_setup.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import nazema.coldchino.info.data.model.Puzzle
import nazema.coldchino.info.ui.composable.screen.puzzle_setup.overlay.GridOverlay
import nazema.coldchino.info.ui.composable.shared.puzzle.rememberPuzzleThumbnail
import nazema.coldchino.info.ui.theme.LocalDimens

private const val PageImageMaxDimension = 900

@Composable
fun PuzzlePage(
    puzzle: Puzzle,
    gridSize: Int,
    modifier: Modifier = Modifier,
) {
    val picture = rememberPuzzleThumbnail(puzzle, PageImageMaxDimension)
    val shape = RoundedCornerShape(LocalDimens.current.cardCornerRadius)
    val ratio = picture?.let { it.width.toFloat() / it.height } ?: 1f

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(ratio)
                .clip(shape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            picture?.let {
                Image(
                    bitmap = it,
                    contentDescription = puzzle.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            GridOverlay(gridSize = gridSize, modifier = Modifier.fillMaxSize())
        }
    }
}
