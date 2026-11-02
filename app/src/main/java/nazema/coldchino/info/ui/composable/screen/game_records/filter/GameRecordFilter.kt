package nazema.coldchino.info.ui.composable.screen.game_records.filter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nazema.coldchino.info.ui.composable.screen.game_records.filter.GameRecordFilterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameRecordFilter(
    items: List<GameRecordFilterItem>,
    onSelect: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier.fillMaxWidth()) {
        items.forEachIndexed { index, item ->
            SegmentedButton(
                selected = item.selected,
                onClick = { onSelect(index) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = items.size),
                icon = {
                    if (item.icon != null) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                        )
                    } else {
                        SegmentedButtonDefaults.Icon(active = item.selected)
                    }
                },
                label = { Text(text = item.label) },
            )
        }
    }
}
