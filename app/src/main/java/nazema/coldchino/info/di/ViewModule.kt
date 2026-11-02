package nazema.coldchino.info.di

import android.content.Context
import nazema.coldchino.info.ui.viewmodel.GameRecordViewModel
import nazema.coldchino.info.ui.viewmodel.GameViewModel
import nazema.coldchino.info.ui.viewmodel.PuzzleSetupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        PuzzleSetupViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
        )
    }

    viewModel {
        GameViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
            resources = get<Context>().resources,
            ioDispatcher = get(DispatchersQualifiers.IO),
        )
    }

    viewModel {
        GameRecordViewModel(
            puzzleRepository = get(),
            gameRecordRepository = get(),
        )
    }
}