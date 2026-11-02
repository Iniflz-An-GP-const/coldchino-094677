package nazema.coldchino.info.data.repository

import nazema.coldchino.info.R
import nazema.coldchino.info.data.model.Puzzle

class PuzzleRepository {
    private val puzzles: List<Puzzle> = listOf(
        
    )

    fun getPuzzles(): List<Puzzle> = puzzles

    fun findById(id: Int): Puzzle? = puzzles.firstOrNull { it.id == id }
}