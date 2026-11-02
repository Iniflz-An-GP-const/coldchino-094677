package nazema.coldchino.info.ui.composable.screen.game_records

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nazema.coldchino.info.R
import nazema.coldchino.info.data.model.PuzzleLevel
import nazema.coldchino.info.ui.composable.app_root.topbar.AppTopBar
import nazema.coldchino.info.ui.composable.screen.game_records.card.GameRecordCardItem
import nazema.coldchino.info.ui.composable.screen.game_records.card.GameRecordCard
import nazema.coldchino.info.ui.composable.screen.game_records.filter.GameRecordFilterItem
import nazema.coldchino.info.ui.composable.screen.game_records.filter.GameRecordFilter
import nazema.coldchino.info.ui.composable.shared.DataBasedContainer
import nazema.coldchino.info.ui.composable.shared.DataEmptyContent
import nazema.coldchino.info.ui.composable.shared.level.icon
import nazema.coldchino.info.ui.state.DataUiState
import nazema.coldchino.info.ui.theme.LocalDimens
import nazema.coldchino.info.ui.viewmodel.GameRecordViewModel
import nazema.coldchino.info.ui.viewmodel.RankedGameRecord
import nazema.coldchino.info.ui.viewmodel.RecordSortOption
import nazema.coldchino.info.utils.formatDuration
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

private val DateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")

@Composable
fun NMDNOGameRecordsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GameRecordViewModel = koinViewModel(),
) {
    val recordsState by viewModel.recordsState.collectAsState()
    val selectedLevel by viewModel.selectedLevelState.collectAsState()
    val selectedSort by viewModel.selectedSortState.collectAsState()
    val dimens = LocalDimens.current

    Column(modifier = modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(R.string.game_records_title),
            onNavigateBack = onBackClick,
        )

        GameRecordFilter(
            items = PuzzleLevel.entries.map { level ->
                GameRecordFilterItem(
                    label = stringResource(level.titleRes),
                    selected = level == selectedLevel,
                    icon = level.icon,
                )
            },
            onSelect = { index -> viewModel.onLevelSelected(PuzzleLevel.entries[index]) },
            modifier = Modifier.padding(
                horizontal = dimens.screenPadding,
                vertical = dimens.contentSpacing,
            ),
        )

        DataBasedContainer(
            modifier = Modifier.weight(1f),
            dataState = recordsState,
            dataPopulated = {
                val records = (recordsState as DataUiState.Populated).data

                Column(modifier = Modifier.fillMaxSize()) {
                    GameRecordFilter(
                        items = RecordSortOption.entries.map { sort ->
                            GameRecordFilterItem(
                                label = stringResource(sort.labelRes()),
                                selected = sort == selectedSort,
                                icon = sort.icon(),
                            )
                        },
                        onSelect = { index -> viewModel.onSortSelected(RecordSortOption.entries[index]) },
                        modifier = Modifier.padding(
                            start = dimens.screenPadding,
                            end = dimens.screenPadding,
                            bottom = dimens.contentSpacing,
                        ),
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(
                            start = dimens.screenPadding,
                            end = dimens.screenPadding,
                            bottom = dimens.contentSpacing,
                        ),
                        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
                    ) {
                        items(records, key = { it.record.id }) { ranked ->
                            GameRecordCard(item = ranked.toCardItem())
                        }
                    }
                }
            },
            dataEmpty = {
                DataEmptyContent(
                    primaryText = stringResource(R.string.game_records_empty),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

private fun RankedGameRecord.toCardItem() = GameRecordCardItem(
    rank = rank,
    puzzleTitle = puzzle?.title.orEmpty(),
    formattedTime = formatDuration(record.completionTime),
    formattedDate = record.timestamp.format(DateFormatter),
)

@StringRes
private fun RecordSortOption.labelRes(): Int = when (this) {
    RecordSortOption.TIME -> R.string.game_records_sort_time
    RecordSortOption.DATE -> R.string.game_records_sort_date
    RecordSortOption.PUZZLE -> R.string.game_records_sort_puzzle
}

private fun RecordSortOption.icon() = when (this) {
    RecordSortOption.TIME -> Icons.Filled.Timer
    RecordSortOption.DATE -> Icons.Filled.DateRange
    RecordSortOption.PUZZLE -> Icons.Filled.Extension
}
