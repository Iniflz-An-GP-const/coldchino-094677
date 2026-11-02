package nazema.coldchino.info.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("game_records")
data class GameRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    /** Id of the [nazema.coldchino.info.data.model.Puzzle] that was assembled. */
    @ColumnInfo(name = "puzzle_id") val puzzleId: Int,
    /** Name of the [nazema.coldchino.info.data.model.PuzzleLevel] constant. */
    val level: String,
    /** Time to assemble, in seconds. */
    @ColumnInfo(name = "completion_time") val completionTime: Long,
    val timestamp: LocalDateTime,
)