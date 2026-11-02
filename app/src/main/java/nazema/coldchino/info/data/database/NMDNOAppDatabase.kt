package nazema.coldchino.info.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nazema.coldchino.info.data.dao.GameRecordDao
import nazema.coldchino.info.data.database.converter.Converters
import nazema.coldchino.info.data.entity.GameRecord

@Database(
    entities = [GameRecord::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NMDNOAppDatabase : RoomDatabase() {

    abstract fun gameRecordDao(): GameRecordDao
}