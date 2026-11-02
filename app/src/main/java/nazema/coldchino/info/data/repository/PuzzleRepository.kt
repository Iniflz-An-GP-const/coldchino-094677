package nazema.coldchino.info.data.repository

import nazema.coldchino.info.R
import nazema.coldchino.info.data.model.Puzzle

class PuzzleRepository {
    private val puzzles: List<Puzzle> = listOf(
        Puzzle(
            id = 1,
            texts = mapOf(
                "en" to "Fruit Medley", "de" to "Obstmischung", "es" to "Frutas variadas",
                "fr" to "Fruits variés", "hi" to "फलों का मिश्रण", "id" to "Aneka Buah",
                "pt" to "Frutas variadas", "ar" to "فواكه مشكلة"
            ),
            resId = R.drawable.fruit_medley
        ),
        Puzzle(
            id = 2,
            texts = mapOf(
                "en" to "Garden Salad", "de" to "Gartensalat", "es" to "Ensalada fresca",
                "fr" to "Salade fraîche", "hi" to "ताज़ा सलाद", "id" to "Salad Segar",
                "pt" to "Salada fresca", "ar" to "سلطة طازجة"
            ),
            resId = R.drawable.garden_salad
        ),
        Puzzle(
            id = 3,
            texts = mapOf(
                "en" to "Pasta Plate", "de" to "Pastateller", "es" to "Plato de pasta",
                "fr" to "Plat de pâtes", "hi" to "पास्ता प्लेट", "id" to "Hidangan Pasta",
                "pt" to "Prato de massa", "ar" to "طبق معكرونة"
            ),
            resId = R.drawable.pasta_plate
        ),
        Puzzle(
            id = 4,
            texts = mapOf(
                "en" to "Coffee Beans", "de" to "Kaffeebohnen", "es" to "Granos de café",
                "fr" to "Grains de café", "hi" to "कॉफ़ी के दाने", "id" to "Biji Kopi",
                "pt" to "Grãos de café", "ar" to "حبوب القهوة"
            ),
            resId = R.drawable.coffee_beans
        ),
        Puzzle(
            id = 5,
            texts = mapOf(
                "en" to "Moroccan Spices", "de" to "Marokkanische Gewürze", "es" to "Especias marroquíes",
                "fr" to "Épices marocaines", "hi" to "मोरक्को के मसाले", "id" to "Rempah Maroko",
                "pt" to "Especiarias marroquinas", "ar" to "توابل مغربية"
            ),
            resId = R.drawable.moroccan_spices
        ),
        Puzzle(
            id = 6,
            texts = mapOf(
                "en" to "Pizza Ingredients", "de" to "Pizzazutaten", "es" to "Ingredientes de pizza",
                "fr" to "Ingrédients de pizza", "hi" to "पिज़्ज़ा सामग्री", "id" to "Bahan Piza",
                "pt" to "Ingredientes de pizza", "ar" to "مكونات البيتزا"
            ),
            resId = R.drawable.pizza_ingredients
        ),
        Puzzle(
            id = 7,
            texts = mapOf(
                "en" to "Colorful Macarons", "de" to "Bunte Macarons", "es" to "Macarons coloridos",
                "fr" to "Macarons colorés", "hi" to "रंगीन मैकरॉन", "id" to "Makaron Warna-warni",
                "pt" to "Macarons coloridos", "ar" to "ماكرون ملون"
            ),
            resId = R.drawable.colorful_macarons
        )
    )

    fun getPuzzles(): List<Puzzle> = puzzles

    fun findById(id: Int): Puzzle? = puzzles.firstOrNull { it.id == id }
}
