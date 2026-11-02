package nazema.coldchino.info.ui.composable.shared.puzzle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import nazema.coldchino.info.data.model.Puzzle
import nazema.coldchino.info.di.DispatchersQualifiers
import nazema.coldchino.info.utils.THUMBNAIL_MAX_DIMENSION
import nazema.coldchino.info.utils.loadPuzzleBitmap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject
import androidx.compose.ui.platform.LocalResources

@Composable
fun rememberPuzzleThumbnail(
    puzzle: Puzzle,
    maxDimension: Int = THUMBNAIL_MAX_DIMENSION,
): ImageBitmap? {
    val resources = LocalResources.current
    val ioDispatcher: CoroutineDispatcher = koinInject(DispatchersQualifiers.IO)

    val thumbnail by produceState<ImageBitmap?>(initialValue = null, puzzle, maxDimension) {
        value = withContext(ioDispatcher) {
            loadPuzzleBitmap(resources, puzzle, maxDimension).asImageBitmap()
        }
    }
    return thumbnail
}
