package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

enum class Category(
    val id: String,
    val title: String,
    val emoji: String,
    val description: String,
    val tintColor: Color
) {
    ALL("all", "All Drawings", "🎨", "All 110+ coloring pages", SunshineYellow),
    ANIMALS("animals", "Animals", "🐶", "Puppies, kittens, pandas & more", PalePeach),
    DINOSAURS("dinosaurs", "Dinosaurs", "🦖", "T-Rex, Triceratops & friends", PaleMint),
    CARS("cars", "Cars & Trucks", "🚗", "Race cars, fire trucks & diggers", PaleBlue),
    SPACE("space", "Space", "🚀", "Rockets, planets & cute aliens", PaleLavender),
    FANTASY("fantasy", "Fantasy", "🧚", "Unicorns, dragons & magic", PalePeach),
    OCEAN("ocean", "Ocean", "🌊", "Dolphins, turtles & fish", PaleBlue),
    NATURE("nature", "Nature", "🌳", "Flowers, butterflies & forests", PaleMint),
    FAIRYTALE("fairytale", "Fairytale", "🏰", "Castles, crowns & carriages", PaleLavender),
    FOOD("food", "Food & Treats", "🍦", "Ice cream, pizza & sweet cakes", PalePeach),
    SPORTS("sports", "Sports & Toys", "⚽", "Soccer, skateboards & teddy bears", PaleBlue),
    COMMUNITY("community", "Helpers & Places", "🚒", "Firefighters, doctors & schools", PaleMint);

    companion object {
        fun fromId(id: String): Category = entries.find { it.id.equals(id, ignoreCase = true) } ?: ALL
    }
}
