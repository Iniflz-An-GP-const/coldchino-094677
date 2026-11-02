package nazema.coldchino.info.di

import androidx.room.Room
import nazema.coldchino.info.data.database.NMDNOAppDatabase
import nazema.coldchino.info.data.repository.GameRecordRepository
import nazema.coldchino.info.data.repository.PuzzleRepository
import org.koin.dsl.module

private const val DB_NAME = "nmdno_puzzle_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = NMDNOAppDatabase::class.java,
            name = DB_NAME
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single { get<NMDNOAppDatabase>().gameRecordDao() }

    single {
        GameRecordRepository(
            gameRecordDao = get(),
            ioDispatcher = get(DispatchersQualifiers.IO)
        )
    }

    single { PuzzleRepository() }

}