package nazema.coldchino.info.ui.composable.shared.button.shell

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import nazema.coldchino.info.ui.composable.shared.button.ActionButtonDefaults
import nazema.coldchino.info.ui.composable.shared.button.ActionButtonItem
import nazema.coldchino.info.ui.composable.shared.modifier.press.pressFeedback
import nazema.coldchino.info.ui.theme.controlBorderStroke

@Composable
fun ActionButtonShell(
    item: ActionButtonItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (contentColor: Color) -> Unit,
) {
    val shape = ActionButtonDefaults.shape
    val interactionSource = remember { MutableInteractionSource() }
    val border = controlBorderStroke()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(ActionButtonDefaults.Height)
            .alpha(if (item.enabled) 1f else ActionButtonDefaults.DISABLED_ALPHA)
            .pressFeedback(interactionSource)
            .clip(shape)
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh, shape = shape)
            .let { base -> if (border != null) base.border(border, shape) else base }
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = item.enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = ActionButtonDefaults.horizontalPadding),
        contentAlignment = Alignment.Center,
    ) {
        content(ActionButtonDefaults.accentColor(item.emphasis))
    }
}
