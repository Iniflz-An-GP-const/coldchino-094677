package nazema.coldchino.info.ui.composable.screen.game.hint

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import nazema.coldchino.info.R
import nazema.coldchino.info.ui.theme.LocalDimens

private val FrameWidth = 2.dp

@Composable
fun HintDialog(
    image: ImageBitmap,
    title: String,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cardCornerRadius)

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = shape,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.border(
                width = FrameWidth,
                color = MaterialTheme.colorScheme.primary,
                shape = shape
            ),
        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = onDismiss)
                    .padding(dimens.itemSpacing),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            ) {
                Image(
                    bitmap = image,
                    contentDescription = stringResource(R.string.game_preview_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.clip(RoundedCornerShape(dimens.cornerRadius)),
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
