package nazema.coldchino.info.ui.composable.screen.puzzle_setup.level_selector

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import nazema.coldchino.info.data.model.PuzzleLevel
import nazema.coldchino.info.ui.composable.shared.level.LevelGridGlyph
import nazema.coldchino.info.ui.composable.shared.level.colors
import nazema.coldchino.info.ui.theme.LocalDimens

private val GlyphSize = 32.dp
private val SelectedBorderWidth = 2.dp

@Composable
fun LevelSelector(
    levels: List<PuzzleLevel>,
    selected: PuzzleLevel,
    onSelect: (PuzzleLevel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cornerRadius)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing + dimens.tightSpacing),
    ) {
        levels.forEach { level ->
            val colors = level.colors()
            val isSelected = level == selected

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(shape)
                    .background(
                        color = if (isSelected) colors.container else MaterialTheme.colorScheme.surfaceContainer,
                        shape = shape,
                    )
                    .border(
                        width = SelectedBorderWidth,
                        color = if (isSelected) colors.accent else MaterialTheme.colorScheme.surfaceContainer,
                        shape = shape,
                    )
                    .clickable { onSelect(level) }
                    .padding(vertical = dimens.itemSpacing),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            ) {
                LevelGridGlyph(
                    gridSize = level.gridSize,
                    color = if (isSelected) colors.accent else MaterialTheme.colorScheme.outline,
                    size = GlyphSize,
                )
                Text(
                    text = stringResource(level.titleRes),
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isSelected) colors.onContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
