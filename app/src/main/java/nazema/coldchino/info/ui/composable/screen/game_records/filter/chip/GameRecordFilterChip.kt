package nazema.coldchino.info.ui.composable.screen.game_records.filter.chip

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nazema.coldchino.info.ui.composable.screen.game_records.filter.GameRecordFilterItem
import nazema.coldchino.info.ui.theme.LocalDimens

@Composable
fun GameRecordFilterChip(
    item: GameRecordFilterItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    FilterChip(
        selected = item.selected,
        onClick = onClick,
        label = { Text(item.label) },
        leadingIcon = if (item.selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                )
            }
        } else {
            null
        },
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        modifier = modifier,
    )
}
