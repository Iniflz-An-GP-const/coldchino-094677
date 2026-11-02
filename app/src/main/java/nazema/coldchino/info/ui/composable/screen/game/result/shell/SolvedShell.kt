package nazema.coldchino.info.ui.composable.screen.game.result.shell

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nazema.coldchino.info.R
import nazema.coldchino.info.ui.composable.screen.game.result.SolvedInfo
import nazema.coldchino.info.ui.theme.LocalDimens

private val PictureMaxHeight = 200.dp

@Composable
fun SolvedShell(
    info: SolvedInfo,
    content: @Composable (SolvedInfo) -> Unit,
    actions: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val pictureRatio = info.picture.width.toFloat() / info.picture.height

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(dimens.cardPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = PictureMaxHeight),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    bitmap = info.picture,
                    contentDescription = stringResource(R.string.game_solved_picture_description),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .aspectRatio(pictureRatio)
                        .clip(RoundedCornerShape(dimens.cornerRadius)),
                )
            }

            Text(
                text = info.title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )

            content(info)

            actions()
        }
    }
}
