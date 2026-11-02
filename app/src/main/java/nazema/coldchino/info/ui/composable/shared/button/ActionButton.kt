package nazema.coldchino.info.ui.composable.shared.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nazema.coldchino.info.ui.composable.shared.button.content.ActionButtonContent
import nazema.coldchino.info.ui.composable.shared.button.shell.ActionButtonShell

@Composable
fun ActionButton(
    item: ActionButtonItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ActionButtonShell(
        item = item,
        onClick = onClick,
        modifier = modifier,
    ) { contentColor ->
        ActionButtonContent(item = item, contentColor = contentColor)
    }
}
