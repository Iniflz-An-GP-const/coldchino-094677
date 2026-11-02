package nazema.coldchino.info.ui.composable.screen.game.result.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import nazema.coldchino.info.R
import nazema.coldchino.info.ui.composable.screen.game.result.SolvedComparison
import nazema.coldchino.info.ui.composable.screen.game.result.SolvedInfo
import nazema.coldchino.info.ui.theme.LocalDimens

@Composable
fun SolvedContent(
    info: SolvedInfo,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
    ) {
        Text(
            text = stringResource(R.string.game_solved_message, info.timeText),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )

        when (val comparison = info.comparison) {
            SolvedComparison.First -> Unit

            is SolvedComparison.NewRecord -> {
                Text(
                    text = stringResource(R.string.game_solved_new_record),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = stringResource(
                        R.string.game_solved_previous_best,
                        comparison.previousBestText
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            is SolvedComparison.NotBest -> {
                Text(
                    text = stringResource(R.string.game_solved_best, comparison.bestText),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
