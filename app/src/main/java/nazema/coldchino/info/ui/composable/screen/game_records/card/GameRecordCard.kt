package nazema.coldchino.info.ui.composable.screen.game_records.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import nazema.coldchino.info.R
import nazema.coldchino.info.ui.composable.screen.game_records.card.GameRecordCardDefaults
import nazema.coldchino.info.ui.composable.screen.game_records.card.GameRecordCardItem
import nazema.coldchino.info.ui.theme.cardBorderStroke

@Composable
fun GameRecordCard(
    item: GameRecordCardItem,
    modifier: Modifier = Modifier,
) {
    val shape = GameRecordCardDefaults.shape
    val border = cardBorderStroke()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = shape)
            .let { base -> if (border != null) base.border(border, shape) else base }
            .padding(GameRecordCardDefaults.padding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(
                R.string.game_record_card_score_format,
                item.puzzleTitle,
                item.formattedTime
            ),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = item.formattedDate,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
