package nazema.coldchino.info.ui.composable.screen.puzzle_setup.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nazema.coldchino.info.R
import nazema.coldchino.info.data.model.Puzzle
import nazema.coldchino.info.ui.composable.screen.puzzle_setup.picker.PuzzlePickerTile
import nazema.coldchino.info.ui.theme.LocalDimens

private const val ColumnCount = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PuzzlePickerDialog(
    puzzles: List<Puzzle>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val dimens = LocalDimens.current

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(horizontal = dimens.screenPadding),
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            Text(
                text = stringResource(R.string.puzzle_picker_title),
                style = MaterialTheme.typography.titleLarge,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(ColumnCount),
                verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
                horizontalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
                modifier = Modifier.padding(bottom = dimens.contentSpacing),
            ) {
                itemsIndexed(puzzles, key = { _, puzzle -> puzzle.id }) { index, puzzle ->
                    PuzzlePickerTile(
                        puzzle = puzzle,
                        isSelected = index == selectedIndex,
                        onClick = { onSelect(index) },
                    )
                }
            }
        }
    }
}
