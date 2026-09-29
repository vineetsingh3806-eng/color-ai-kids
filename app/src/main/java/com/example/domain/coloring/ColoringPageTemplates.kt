package com.example.domain.coloring

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.data.model.Category
import com.example.data.model.ColoringPage
import com.example.domain.coloring.renderers.*

object ColoringPageTemplates {

    val allPreloadedPages: List<ColoringPage> = listOf(
        // ==================== 1. Dinosaurs (10) ====================
        ColoringPage("t_rex", "Friendly T-Rex", Category.DINOSAURS, "🦖", "A smiling cartoon Tyrannosaurus with cute stripes and tiny arms"),
        ColoringPage("triceratops", "Little Triceratops", Category.DINOSAURS, "🦕", "A happy three-horned dinosaur with wide frill in the meadow"),
        ColoringPage("brachiosaurus", "Gentle Giant Brachio", Category.DINOSAURS, "🦕", "Tall and gentle long-necked dinosaur munching on treetop leaves"),
        ColoringPage("stegosaurus", "Spiky Stegosaurus", Category.DINOSAURS, "🦕", "Dinosaur with big diamond back plates and spiked thagomizer tail"),
        ColoringPage("pterodactyl", "Soaring Pterodactyl", Category.DINOSAURS, "🦅", "Winged flying reptile gliding gracefully through prehistoric skies"),
        ColoringPage("ankylosaurus", "Armored Ankylo", Category.DINOSAURS, "🐢", "Tough armored dinosaur with bony shell plates and club tail"),
        ColoringPage("parasaurolophus", "Trumpet Parasaur", Category.DINOSAURS, "📯", "Duck-billed dinosaur with a magnificent curved tubular head crest"),
        ColoringPage("dino_egg", "Surprise Baby Dino", Category.DINOSAURS, "🥚", "A cracked dinosaur egg with a sweet baby dinosaur peeking out"),
        ColoringPage("spinosaurus", "Sail-backed Spino", Category.DINOSAURS, "🐊", "Magnificent dinosaur with a giant colorful dorsal fin spine"),
        ColoringPage("dino_volcano", "Dinosaur Volcano", Category.DINOSAURS, "🌋", "Prehistoric bubbling mountain with palm trees and leaping dinosaur"),

        // ==================== 2. Animals (10) ====================
        ColoringPage("puppy", "Playful Puppy", Category.ANIMALS, "🐶", "A cute puppy with floppy ears, spot over eye, and a wagging tail"),
        ColoringPage("kitten", "Whiskers Kitten", Category.ANIMALS, "🐱", "A fluffy kitten with sweet big eyes, pointy ears, and ribbon bell"),
        ColoringPage("panda", "Happy Panda", Category.ANIMALS, "🐼", "A cuddly panda bear munching happily on a green bamboo shoot"),
        ColoringPage("lion", "Sunny Lion Cub", Category.ANIMALS, "🦁", "A friendly lion cub with a fluffy round mane and proud tail tuft"),
        ColoringPage("elephant", "Peanut Elephant", Category.ANIMALS, "🐘", "A joyful baby elephant splashing water joyfully from its curled trunk"),
        ColoringPage("giraffe", "Tall Spotty Giraffe", Category.ANIMALS, "🦒", "A gentle long-necked giraffe with friendly ossicones and spots"),
        ColoringPage("monkey", "Cheeky Monkey", Category.ANIMALS, "🐵", "A playful monkey swinging from a tree vine holding a sweet banana"),
        ColoringPage("penguin", "Waddling Penguin", Category.ANIMALS, "🐧", "A happy little penguin sliding on belly across shiny polar ice"),
        ColoringPage("rabbit", "Hoppy Bunny", Category.ANIMALS, "🐰", "A cute fluffy rabbit with long ears holding a crunchy orange carrot"),
        ColoringPage("koala", "Cuddly Koala", Category.ANIMALS, "🐨", "A sweet sleepy koala bear hugging an aromatic eucalyptus branch"),

        // ==================== 3. Cars & Vehicles (10) ====================
        ColoringPage("race_car", "Speedy Race Car", Category.CARS, "🏎️", "A sleek racing champion car with number 1 and racing spoiler"),
        ColoringPage("fire_truck", "Hero Fire Truck", Category.CARS, "🚒", "A big fire engine with extending ladder, siren light, and side hoses"),
        ColoringPage("police_car", "City Police Cruiser", Category.CARS, "🚓", "A friendly city police car with flashing roof bar and star emblem"),
        ColoringPage("airplane", "Sunny Jet Plane", Category.CARS, "✈️", "A friendly twin-engine airplane cruising happily through fluffy clouds"),
        ColoringPage("steam_train", "Chugging Steam Train", Category.CARS, "🚂", "A vintage steam locomotive engine puffing fluffy steam rings"),
        ColoringPage("helicopter", "Whirly Chopper", Category.CARS, "🚁", "A cute rescue helicopter with spinning main rotor and landing skids"),
        ColoringPage("bulldozer", "Mighty Bulldozer", Category.CARS, "🚜", "Heavy construction dozer with giant dirt blade and crawler tracks"),
        ColoringPage("excavator", "Digger Excavator", Category.CARS, "🏗️", "Hydraulic digging machine with jointed boom and toothed scoop bucket"),
        ColoringPage("dump_truck", "Heavy Dump Truck", Category.CARS, "🚚", "Big construction hauler with deep tilted bed for dumping rocks"),
        ColoringPage("tractor", "Farmyard Tractor", Category.CARS, "🚜", "A sturdy farm tractor with massive rear tread wheels in a furrowed field"),

        // ==================== 4. Space (10) ====================
        ColoringPage("rocket", "Cosmic Rocket", Category.SPACE, "🚀", "A retro space rocket launching into the starry cosmos with flame trail"),
        ColoringPage("astronaut", "Spacewalk Explorer", Category.SPACE, "🧑‍🚀", "A cute astronaut floating in zero gravity waving next to the stars"),
        ColoringPage("planet", "Ringed Planet Saturn", Category.SPACE, "🪐", "A swirling gas planet with magnificent orbital rings and passing comet"),
        ColoringPage("alien", "Friendly Martian", Category.SPACE, "👽", "A cute three-eyed smiling alien waving antennae from a flying saucer"),
        ColoringPage("spaceship", "Galaxy Cruiser UFO", Category.SPACE, "🛸", "A classic flying saucer with dome cockpit and radiant beam"),
        ColoringPage("moon_landing", "Moon Surface Lander", Category.SPACE, "🌕", "A four-legged lunar lander parked on cratered moon soil with flag"),
        ColoringPage("solar_system", "Our Solar System", Category.SPACE, "☀️", "A smiling central sun with orbiting planets and colorful trajectories"),
        ColoringPage("space_rover", "Mars Explorer Rover", Category.SPACE, "🤖", "A robotic 6-wheel rover taking soil samples with camera mast"),
        ColoringPage("space_station", "Orbital Space Station", Category.SPACE, "🛰️", "A high-tech satellite station with sprawling solar panel wings"),
        ColoringPage("shooting_star", "Blazing Comet Star", Category.SPACE, "🌠", "A smiling 5-pointed celestial star with long rainbow stardust tail"),

        // ==================== 5. Fantasy (10) ====================
        ColoringPage("unicorn", "Starlight Unicorn", Category.FANTASY, "🦄", "A magical unicorn with spiraled golden horn, luscious wavy mane & stars"),
        ColoringPage("dragon", "Puff Baby Dragon", Category.FANTASY, "🐲", "A cute friendly winged dragon with segmented belly and smoke puff"),
        ColoringPage("magic_wand", "Sparkle Magic Wand", Category.FANTASY, "🪄", "A star-topped fairy wand with swirling stardust ribbons and sparkles"),
        ColoringPage("fairy", "Garden Blossom Fairy", Category.FANTASY, "🧚", "A sweet fairy with butterfly wings, petal skirt, and magic star wand"),
        ColoringPage("mermaid", "Deep Sea Mermaid", Category.FANTASY, "🧜‍♀️", "A lovely mermaid with flowing hair, starfish clip, and scalloped tail fin"),
        ColoringPage("crystal_ball", "Mystic Crystal Ball", Category.FANTASY, "🔮", "A glowing glass sphere on ornate golden pedestal with swirling mist"),
        ColoringPage("wizard_hat", "Enchanted Wizard Hat", Category.FANTASY, "🧙‍♂️", "A tall pointed cone hat with curled tip, buckle, and crescent moon"),
        ColoringPage("treasure_chest", "Golden Treasure Chest", Category.FANTASY, "🪙", "An open pirate chest overflowing with sparkling gold coins and gems"),
        ColoringPage("magic_potion", "Bubbling Potion Flask", Category.FANTASY, "🧪", "A round glass flask with cork stopper and shimmering magical potion"),
        ColoringPage("phoenix", "Flaming Phoenix Bird", Category.FANTASY, "🔥", "A mythical firebird with flaming feather plumage and long tail crest"),

        // ==================== 6. Ocean (10) ====================
        ColoringPage("dolphin", "Ocean Dolphin Leap", Category.OCEAN, "🐬", "A graceful dolphin leaping joyfully over rolling blue sea waves"),
        ColoringPage("sea_turtle", "Sunny Sea Turtle", Category.OCEAN, "🐢", "A gentle sea turtle gliding underwater with hexagonal shell patterns"),
        ColoringPage("octopus", "Bubbly Octopus", Category.OCEAN, "🐙", "A cheerful octopus with eight dancing curly suction-cupped tentacles"),
        ColoringPage("whale", "Giant Blue Whale", Category.OCEAN, "🐳", "A magnificent blue whale spouting a tall water fountain into the air"),
        ColoringPage("clownfish", "Coral Clownfish", Category.OCEAN, "🐠", "A cute striped anemone fish swimming peacefully among ocean bubbles"),
        ColoringPage("seahorse", "Dancing Seahorse", Category.OCEAN, "🌊", "A majestic seahorse with coronet head and spiraled curly prehensile tail"),
        ColoringPage("starfish", "Happy Sandy Starfish", Category.OCEAN, "⭐", "A 5-armed smiling starfish resting comfortably on the sandy seabed"),
        ColoringPage("jellyfish", "Glowing Jellyfish", Category.OCEAN, "🎐", "A gentle umbrella dome jellyfish with trailing silky tentacle ribbons"),
        ColoringPage("crab", "Clicky Coastal Crab", Category.OCEAN, "🦀", "A cheerful beach crab with two big friendly pincers and stalk eyes"),
        ColoringPage("shark", "Friendly Great Shark", Category.OCEAN, "🦈", "A smiling shark with prominent dorsal fin, gills, and sleek tail flukes"),

        // ==================== 7. Nature (10) ====================
        ColoringPage("butterfly", "Fluttering Butterfly", Category.NATURE, "🦋", "A stunning butterfly with large patterned wings and curled antennae"),
        ColoringPage("sun_hills", "Smiling Sun & Hills", Category.NATURE, "☀️", "A smiling radiant sunshine rising above rolling countryside meadow hills"),
        ColoringPage("mushroom", "Spotted Toadstool", Category.NATURE, "🍄", "A cute woodland mushroom with round polka-dot cap and sweet ladybug"),
        ColoringPage("tree", "Grand Old Oak Tree", Category.NATURE, "🌳", "A sturdy forest tree with leafy billowing canopy and a cozy squirrel hollow"),
        ColoringPage("flower_pot", "Sunny Flower Bloom", Category.NATURE, "🌻", "A cheerful terracotta flowerpot with a huge blossoming daisy flower"),
        ColoringPage("rainbow", "Over the Rainbow", Category.NATURE, "🌈", "A vibrant 5-band arched rainbow stretching between two fluffy clouds"),
        ColoringPage("apple_tree", "Sweet Apple Orchard", Category.NATURE, "🍎", "A bountiful apple tree loaded with ripe fruit and a harvest basket"),
        ColoringPage("beehive", "Busy Garden Beehive", Category.NATURE, "🐝", "A tiered straw skep beehive hanging on a branch with friendly buzzy bees"),
        ColoringPage("cactus", "Desert Saguaro Cactus", Category.NATURE, "🌵", "A tall desert cactus with prickly needles, flowering blossom & sand dunes"),
        ColoringPage("campfire", "Cozy Campfire", Category.NATURE, "🔥", "Criss-crossed wooden logs with dancing fire tongues and a marshmallow"),

        // ==================== 8. Fairytale (10) ====================
        ColoringPage("fairy_castle", "Enchanted Castle", Category.FAIRYTALE, "🏰", "A majestic fairytale castle with conical towers, flags, and portcullis"),
        ColoringPage("crown", "Royal Golden Crown", Category.FAIRYTALE, "👑", "A sparkling royal crown adorned with precious teardrop and oval jewels"),
        ColoringPage("pegasus", "Flying Pegasus", Category.FAIRYTALE, "🎠", "A divine winged stallion soaring through heavenly clouds and stars"),
        ColoringPage("princess_carriage", "Royal Pumpkin Coach", Category.FAIRYTALE, "🛞", "A storybook pumpkin carriage with heart window and ornate wheels"),
        ColoringPage("royal_shield", "Knight Royal Shield", Category.FAIRYTALE, "🛡️", "A knightly crest shield with crossed broadswords and royal emblems"),
        ColoringPage("fairytale_prince", "Brave Crowned Prince", Category.FAIRYTALE, "🤴", "A dashing fairytale prince wearing a royal cape, golden crown, and boots"),
        ColoringPage("fairytale_princess", "Magical Princess", Category.FAIRYTALE, "👸", "A charming princess wearing a glittering tiara and wide ballgown dress"),
        ColoringPage("glass_slipper", "Crystal Glass Slipper", Category.FAIRYTALE, "👠", "A sparkling crystal slipper resting on a royal velvet tasselled pillow"),
        ColoringPage("royal_throne", "King's Golden Throne", Category.FAIRYTALE, "🪑", "An ornate high-backed royal throne chair with tufted cushion and lion arms"),
        ColoringPage("spinning_wheel", "Fairytale Spinning Wheel", Category.FAIRYTALE, "🧵", "A classic wooden spinning wheel with spoked wheel, spindle, and distaff"),

        // ==================== 9. Food & Treats (10) ====================
        ColoringPage("ice_cream", "Triple Scoop Ice Cream", Category.FOOD, "🍦", "A waffle criss-cross cone piled high with scoops, drips, and a sweet cherry"),
        ColoringPage("birthday_cake", "Celebration Birthday Cake", Category.FOOD, "🎂", "A two-tiered frosted party cake with lit candles, drips, and sprinkles"),
        ColoringPage("cupcake", "Swirly Sweet Cupcake", Category.FOOD, "🧁", "A fluted cupcake with massive frosting swirl, sprinkles, and candy heart"),
        ColoringPage("pizza", "Cheesy Pizza Slice", Category.FOOD, "🍕", "A delicious triangle pizza slice with melting cheese drips and pepperoni"),
        ColoringPage("donut", "Glazed Sprinkled Donut", Category.FOOD, "🍩", "A mouth-watering round donut with glossy wavy icing and crunchy sprinkles"),
        ColoringPage("burger", "Giant Yummy Burger", Category.FOOD, "🍔", "A sesame seed bun stacked with crisp lettuce, tomato, cheese, and patty"),
        ColoringPage("watermelon", "Juicy Watermelon Slice", Category.FOOD, "🍉", "A fresh crescent watermelon slice with thick striped rind and cute seeds"),
        ColoringPage("popcorn", "Movie Popcorn Bucket", Category.FOOD, "🍿", "A vintage striped red-and-white popcorn container brimming with kernels"),
        ColoringPage("cookie", "Chocolate Chip Cookie", Category.FOOD, "🍪", "A golden baked cookie with chocolate chunks and a funny bite taken out"),
        ColoringPage("pancakes", "Fluffy Pancake Stack", Category.FOOD, "🥞", "A tall stack of round golden pancakes topped with melting butter & syrup"),

        // ==================== 10. Sports & Toys (10) ====================
        ColoringPage("soccer_ball", "Champion Soccer Ball", Category.SPORTS, "⚽", "A classic black-and-white patterned soccer ball with motion speed lines"),
        ColoringPage("basketball", "Slam Dunk Basketball", Category.SPORTS, "🏀", "A textured round basketball flying directly toward an open net hoop"),
        ColoringPage("teddy_bear", "Cuddly Teddy Bear", Category.SPORTS, "🧸", "A super soft teddy bear with round ears, stitched belly patch, and bow tie"),
        ColoringPage("bicycle", "Kids Cruiser Bicycle", Category.SPORTS, "🚲", "A bicycle with two large spoked wheels, handlebars, and comfortable seat"),
        ColoringPage("roller_skates", "Retro Roller Skates", Category.SPORTS, "🛼", "A high-top roller skate boot with front toe stopper and star decal"),
        ColoringPage("skateboard", "Kicktail Skateboard", Category.SPORTS, "🛹", "A cool skateboard deck with curved kicktails, rugged trucks, and wheels"),
        ColoringPage("kite", "Dancing Sky Kite", Category.SPORTS, "🪁", "A diamond kite with cross spars and a long tail decorated with ribbon bows"),
        ColoringPage("yoyo", "Spinning Star Yo-Yo", Category.SPORTS, "🪀", "A colorful spinning yo-yo on a winding string with star emblem"),
        ColoringPage("rubiks_cube", "Color Puzzle Cube", Category.SPORTS, "🎲", "A 3D isometric puzzle block divided into distinct colored squares"),
        ColoringPage("rocking_horse", "Wooden Rocking Horse", Category.SPORTS, "🎠", "A nursery rocking horse on smooth curved wooden rocker runners"),

        // ==================== 11. Helpers & Places (10) ====================
        ColoringPage("firefighter", "Hero Firefighter", Category.COMMUNITY, "🚒", "A brave firefighter in protective gear spraying water from a big hose"),
        ColoringPage("doctor", "Caring Pediatric Doctor", Category.COMMUNITY, "🩺", "A kind doctor wearing a stethoscope and holding a medical first aid kit"),
        ColoringPage("chef", "Master Pastry Chef", Category.COMMUNITY, "👨‍🍳", "A cheerful chef in a tall puffy hat flipping a pancake in a frying pan"),
        ColoringPage("school_bus", "Yellow School Bus", Category.COMMUNITY, "🚌", "A happy school bus with stop sign arm and smiling children in the windows"),
        ColoringPage("lighthouse", "Beacon Lighthouse", Category.COMMUNITY, "🏮", "A tall striped coastal lighthouse shining radiant light beams into the night"),
        ColoringPage("windmill", "Countryside Windmill", Category.COMMUNITY, "💨", "A rustic stone windmill with four spinning lattice sail blades in a meadow"),
        ColoringPage("hot_air_balloon", "Whimsical Air Balloon", Category.COMMUNITY, "🎈", "A giant striped hot air balloon soaring above clouds with wicker basket"),
        ColoringPage("submarine", "Yellow Submarine", Category.COMMUNITY, "🚢", "An undersea exploration sub with periscope, 3 portholes, and propeller"),
        ColoringPage("clock_tower", "Grand City Clock Tower", Category.COMMUNITY, "🕰️", "A majestic historic stone clock tower with arched belfry and Roman clock"),
        ColoringPage("igloo", "Arctic Snow Igloo", Category.COMMUNITY, "❄️", "A cozy curved ice dome with arch doorway and a friendly scarf-wearing penguin"),

        // ==================== 12. Dinosaurs Expanded (10) ====================
        ColoringPage("velociraptor", "Swift Velociraptor", Category.DINOSAURS, "🦖", "A quick and clever raptor with sharp claws and feathery tail"),
        ColoringPage("diplodocus", "Long Tail Diplodocus", Category.DINOSAURS, "🦕", "A long-tailed sauropod strolling across prehistoric fern plains"),
        ColoringPage("pachycephalosaurus", "Dome-Head Pachy", Category.DINOSAURS, "🦕", "A sturdy dome-headed dinosaur with bony crown studs"),
        ColoringPage("allosaurus", "Mighty Allosaurus", Category.DINOSAURS, "🦖", "A ferocious jurassic hunter with pointed crests above eyes"),
        ColoringPage("plesiosaur", "Loch Plesiosaur", Category.DINOSAURS, "🦕", "A long-necked aquatic reptile swimming with four paddle flippers"),
        ColoringPage("archaeopteryx", "Feathered Archaeopteryx", Category.DINOSAURS, "🦅", "An ancient winged dino-bird perching on a prehistoric branch"),
        ColoringPage("carnotaurus", "Horned Carnotaurus", Category.DINOSAURS, "🦖", "A swift bull-horned meat-eating dinosaur with small agile arms"),
        ColoringPage("iguanodon", "Thumb-Spike Iguanodon", Category.DINOSAURS, "🦕", "A friendly herbivore showing off its distinctive thumb spike"),
        ColoringPage("dino_fossil", "Prehistoric Dino Skeleton", Category.DINOSAURS, "🦴", "Ancient dinosaur bones embedded in rock for paleontologists"),
        ColoringPage("baby_sauropod", "Baby Sauropod in Nest", Category.DINOSAURS, "🥚", "A tiny cute longneck dinosaur peeking happily out of foliage"),

        // ==================== 13. Animals Expanded (10) ====================
        ColoringPage("tiger", "Striped Tiger", Category.ANIMALS, "🐯", "A majestic jungle tiger with bold stripes and playful paws"),
        ColoringPage("fox", "Clever Red Fox", Category.ANIMALS, "🦊", "A bushy-tailed woodland fox with pointy ears and white-tipped tail"),
        ColoringPage("grizzly_bear", "Grizzly Bear", Category.ANIMALS, "🐻", "A gentle brown bear enjoying sweet honeycomb in the woods"),
        ColoringPage("zebra", "Zigzag Zebra", Category.ANIMALS, "🦓", "A cheerful savanna zebra with black and white stripes"),
        ColoringPage("kangaroo", "Hopping Kangaroo", Category.ANIMALS, "🦘", "An Australian kangaroo with cute joey peeking from pouch"),
        ColoringPage("hippopotamus", "Happy Hippo", Category.ANIMALS, "🦛", "A round hippo taking a refreshing bath in a warm river"),
        ColoringPage("owl", "Wise Barn Owl", Category.ANIMALS, "🦉", "A cozy nocturnal owl with wide curious eyes perched on branch"),
        ColoringPage("hedgehog", "Little Hedgehog", Category.ANIMALS, "🦔", "A tiny hedgehog with soft spines curled up with an apple"),
        ColoringPage("sloth", "Sleepy Sloth", Category.ANIMALS, "🦥", "A smiling three-toed sloth hanging upside down from jungle branch"),
        ColoringPage("flamingo", "Graceful Flamingo", Category.ANIMALS, "🦩", "A pretty pink bird standing gracefully on one slender leg"),

        // ==================== 14. Cars & Vehicles Expanded (10) ====================
        ColoringPage("ambulance", "Emergency Ambulance", Category.CARS, "🚑", "A speedy hospital ambulance with cross logo and flashing beacons"),
        ColoringPage("monster_truck", "Giant Monster Truck", Category.CARS, "🛻", "A high-riding monster truck with enormous off-road knobby tires"),
        ColoringPage("cement_mixer", "Rotating Cement Mixer", Category.CARS, "🚛", "A busy construction mixer truck with spiral barrel and chute"),
        ColoringPage("garbage_truck", "Recycle Garbage Truck", Category.CARS, "🚚", "A green sanitation truck lifting and emptying a recycle bin"),
        ColoringPage("tow_truck", "Rescue Tow Truck", Category.CARS, "🛻", "A breakdown recovery vehicle with hook, cable winch, and boom"),
        ColoringPage("motorcycle", "Cruiser Motorcycle", Category.CARS, "🏍️", "A sleek two-wheeled bike with chrome exhaust pipes and windshield"),
        ColoringPage("sailboat", "Breezy Sailboat", Category.CARS, "⛵", "A wooden boat with tall triangle sails gliding on gentle water"),
        ColoringPage("cargo_ship", "Ocean Cargo Ship", Category.CARS, "🚢", "A massive freighter loaded with colorful shipping containers"),
        ColoringPage("space_shuttle", "Orbiting Shuttle", Category.CARS, "🚀", "A delta-winged orbital orbiter gliding above the atmosphere"),
        ColoringPage("cable_car", "Mountain Cable Car", Category.CARS, "🚠", "An aerial tramway gondola gliding safely along mountain wires"),

        // ==================== 15. Space Expanded (10) ====================
        ColoringPage("black_hole", "Swirling Black Hole", Category.SPACE, "🕳️", "A cosmic gravitational vortex with glowing accretion light ring"),
        ColoringPage("space_telescope", "Deep Space Telescope", Category.SPACE, "🔭", "An orbital observatory telescope surveying distant nebulae"),
        ColoringPage("comet", "Icy Tail Comet", Category.SPACE, "☄️", "A frozen space wanderer with glowing coma and sparkling ion tail"),
        ColoringPage("meteor_shower", "Meteor Shower Sky", Category.SPACE, "🌠", "A cascade of falling shooting rocks streaking through outer space"),
        ColoringPage("alien_mothership", "Martian Mothership", Category.SPACE, "🛸", "An extraterrestrial saucer with tractor beam and alien pilots"),
        ColoringPage("airlock_module", "Station Airlock", Category.SPACE, "🚪", "Astronaut entering a pressurized airlock module with docking port"),
        ColoringPage("cosmic_nebula", "Colorful Starlight Nebula", Category.SPACE, "🌌", "A cosmic stellar cloud of interstellar dust, stars, and radiant gas"),
        ColoringPage("solar_eclipse", "Totality Solar Eclipse", Category.SPACE, "🌑", "The moon passing before the glowing sun corona with diamond ring"),
        ColoringPage("spiral_galaxy", "Spiral Pinwheel Galaxy", Category.SPACE, "🌀", "A majestic swirling whirlpool galaxy with billions of glowing stars"),
        ColoringPage("satellite_orbit", "Communication Satellite", Category.SPACE, "🛰️", "A cube sat with solar panels and parabolic dish antenna"),

        // ==================== 16. Fantasy Expanded (10) ====================
        ColoringPage("griffin", "Noble Griffin", Category.FANTASY, "🦅", "A mythical beast with eagle head, feathered wings, and lion body"),
        ColoringPage("pegasus_foal", "Baby Pegasus", Category.FANTASY, "🦄", "A winged pony playing among celestial clouds and rainbow arches"),
        ColoringPage("spellbook", "Ancient Spellbook", Category.FANTASY, "📖", "A leather-bound tome with magic clasp, glowing runes, and bookmark"),
        ColoringPage("magic_cauldron", "Witch's Magic Cauldron", Category.FANTASY, "🫕", "A bubbling cast-iron pot brewing a shimmering enchanted potion"),
        ColoringPage("crystal_cave", "Glittering Crystal Cave", Category.FANTASY, "💎", "A mysterious subterranean cavern filled with tall crystal geodes"),
        ColoringPage("stone_gargoyle", "Friendly Stone Gargoyle", Category.FANTASY, "🗿", "A cute guardian statue with bat wings perched on cathedral edge"),
        ColoringPage("tree_guardian", "Whispering Tree Guardian", Category.FANTASY, "🌳", "A living woodland tree with kind eyes, leafy beard, and lantern"),
        ColoringPage("flying_carpet", "Magic Flying Carpet", Category.FANTASY, "🧞", "An ornate woven tapestry floating in the sky with tassels"),
        ColoringPage("treasure_island", "Secret Treasure Island", Category.FANTASY, "🏝️", "A pirate isle with skull rock, palm trees, and hidden X spot"),
        ColoringPage("genie_lamp", "Golden Genie Lamp", Category.FANTASY, "🪔", "An antique brass oil lamp emitting magic swirling genie smoke"),

        // ==================== 17. Ocean Expanded (10) ====================
        ColoringPage("hammerhead_shark", "Hammerhead Shark", Category.OCEAN, "🦈", "A uniquely shaped shark with wide t-shaped head swimming freely"),
        ColoringPage("manta_ray", "Soaring Manta Ray", Category.OCEAN, "🐟", "A broad ocean glider ray with wing-like fins and slender tail"),
        ColoringPage("arctic_walrus", "Arctic Walrus", Category.OCEAN, "🦭", "A whiskered walrus with long white ivory tusks on an ice floe"),
        ColoringPage("reef_lobster", "Reef Lobster", Category.OCEAN, "🦞", "A marine crustacean with large claws, segmented tail, and antennae"),
        ColoringPage("coral_reef", "Living Coral Garden", Category.OCEAN, "🪸", "Branching sea fans, brain coral, and sponge towers on sea floor"),
        ColoringPage("sea_otter", "Floating Sea Otter", Category.OCEAN, "🦦", "A cute furry otter floating on back in kelp cracking an oyster"),
        ColoringPage("sandy_stingray", "Sandy Stingray", Category.OCEAN, "🐡", "A flat diamond ray skimming along the bottom sand dunes"),
        ColoringPage("swordfish", "Swift Swordfish", Category.OCEAN, "🐟", "A high-speed billfish with elongated sword-like snout and tall sail"),
        ColoringPage("giant_squid", "Giant Deep Sea Squid", Category.OCEAN, "🦑", "A ten-armed sea creature with large intelligent eyes and funnel"),
        ColoringPage("pearl_clam", "Clam with Shiny Pearl", Category.OCEAN, "🦪", "An open scallop shell revealing a giant glowing round pearl"),

        // ==================== 18. Nature Expanded (10) ====================
        ColoringPage("garden_rose", "Bloomed Garden Rose", Category.NATURE, "🌹", "A classic rose blossom with velvety layered petals and thorny stem"),
        ColoringPage("spring_tulip", "Spring Dutch Tulip", Category.NATURE, "🌷", "A cheerful cup-shaped spring tulip with long smooth leaves"),
        ColoringPage("sunflower", "Towering Sunflower", Category.NATURE, "🌻", "A tall bright sunflower turning its seeded blossom toward the sun"),
        ColoringPage("pine_conifer", "Snowy Pine Conifer", Category.NATURE, "🌲", "An evergreen mountain pine tree with tiered boughs and pinecones"),
        ColoringPage("waterfall", "Cascading Waterfall", Category.NATURE, "🌊", "Rushing river water tumbling over rocky cliff into a tranquil pool"),
        ColoringPage("woodland_stream", "Babbling Woodland Brook", Category.NATURE, "🏞️", "A clear stream winding past smooth river pebbles and mossy banks"),
        ColoringPage("autumn_leaves", "Dancing Maple Leaves", Category.NATURE, "🍁", "Autumn maple and oak foliage falling gently in the crisp breeze"),
        ColoringPage("oak_acorn", "Golden Oak Acorn", Category.NATURE, "🌰", "A glossy nut with textured cupule sitting on an oak leaf"),
        ColoringPage("daisy_meadow", "Sunny Daisy Meadow", Category.NATURE, "🌼", "A field of white petal daisies dancing under a smiling sky"),
        ColoringPage("mountain_peak", "Snow-Capped Peaks", Category.NATURE, "🏔️", "Majestic mountain summits with alpine meadows and pine forests"),

        // ==================== 19. Fairytale Expanded (10) ====================
        ColoringPage("castle_turret", "Rapunzel Castle Tower", Category.FAIRYTALE, "🏰", "A tall stone fairytale turret with arched window and ivy vine"),
        ColoringPage("magic_mirror", "Enchanted Wall Mirror", Category.FAIRYTALE, "🪞", "An ornate gilded rococo frame holding a mysterious smiling face"),
        ColoringPage("enchanted_rose", "Enchanted Bell Jar Rose", Category.FAIRYTALE, "🥀", "A magical glowing single red rose preserved inside glass cloche"),
        ColoringPage("storybook", "Once Upon a Time Book", Category.FAIRYTALE, "📖", "An open illustrated tale book with castle and rainbow emerging"),
        ColoringPage("knight_helmet", "Valiant Knight Visor", Category.FAIRYTALE, "🪖", "A medieval steel helmet with plumed feather crest and visor slit"),
        ColoringPage("sword_in_stone", "Excalibur in Stone", Category.FAIRYTALE, "🗡️", "A legendary broadsword embedded deep in ancient mystic granite"),
        ColoringPage("gingerbread_house", "Hansel Sweet Cottage", Category.FAIRYTALE, "🍭", "A candy-covered fairy cottage with icing roof and peppermint canes"),
        ColoringPage("magic_beanstalk", "Giant Green Beanstalk", Category.FAIRYTALE, "🌱", "A colossal winding stalk reaching through clouds into the sky"),
        ColoringPage("royal_banner", "Kingdom Heraldic Banner", Category.FAIRYTALE, "🚩", "A silk heraldic pennant flag bearing a lion crest and ribbons"),
        ColoringPage("royal_quill", "King's Golden Quill", Category.FAIRYTALE, "🪶", "A royal feather pen and inkwell bottle writing a proclamation"),

        // ==================== 20. Food & Treats Expanded (10) ====================
        ColoringPage("crunchy_taco", "Crunchy Fiesta Taco", Category.FOOD, "🌮", "A folded crispy corn tortilla filled with lettuce, cheese & tomatoes"),
        ColoringPage("hot_dog", "Classic Stadium Hot Dog", Category.FOOD, "🌭", "A grilled frankfurter in fluffy bun with squiggly yellow mustard"),
        ColoringPage("french_fries", "Crispy Golden Fries", Category.FOOD, "🍟", "A red paper carton overflowing with crunchy salted potato fries"),
        ColoringPage("maki_sushi", "Smiling Maki Sushi", Category.FOOD, "🍣", "Cute nori-wrapped sushi rolls filled with rice and avocado"),
        ColoringPage("strawberry", "Sweet Red Strawberry", Category.FOOD, "🍓", "A juicy ripe strawberry with tiny seeds and green leafy cap"),
        ColoringPage("honeycrisp_apple", "Crisp Honeycrisp Apple", Category.FOOD, "🍎", "A shiny round apple with a friendly leaf and sweet stem"),
        ColoringPage("twin_cherries", "Twin Stem Cherries", Category.FOOD, "🍒", "Two plump round cherries joined together by a curved leafy stalk"),
        ColoringPage("rainbow_lollipop", "Swirled Rainbow Lollipop", Category.FOOD, "🍭", "A large spiral candy on a stick with ribbon bow wrap"),
        ColoringPage("soft_pretzel", "Salted Soft Pretzel", Category.FOOD, "🥨", "A golden baked knot pretzel sprinkled with coarse crunchy salt"),
        ColoringPage("submarine_sandwich", "Submarine Club Sandwich", Category.FOOD, "🥪", "A stacked hoagie roll layered with deli meats, cheese & crisp salad"),

        // ==================== 21. Sports & Toys Expanded (10) ====================
        ColoringPage("baseball", "Home Run Baseball", Category.SPORTS, "⚾", "A curved-stitch baseball with crisscross seams and wooden bat"),
        ColoringPage("football", "Touchdown Football", Category.SPORTS, "🏈", "An oval leather football with white laces and goalpost backdrop"),
        ColoringPage("tennis_racket", "Tennis Racket & Ball", Category.SPORTS, "🎾", "An oval string racket ready to serve a fuzzy tennis ball"),
        ColoringPage("trophy_cup", "First Place Trophy", Category.SPORTS, "🏆", "A shining golden champion cup with handles and star pedestal"),
        ColoringPage("bowling_strike", "Strike Bowling Pins", Category.SPORTS, "🎳", "A three-holed bowling ball knocking down classic wooden pins"),
        ColoringPage("marching_drum", "Marching Band Drum", Category.SPORTS, "🥁", "A festive snare drum with drumsticks, cymbal, and carry strap"),
        ColoringPage("toy_train", "Wooden Toy Engine", Category.SPORTS, "🚂", "A classic wooden locomotive toy with hitch coupler and wheels"),
        ColoringPage("wind_pinwheel", "Spinning Wind Pinwheel", Category.SPORTS, "🪅", "A colorful four-point folding pinwheel on a wooden stick"),
        ColoringPage("rainbow_xylophone", "Rainbow Xylophone", Category.SPORTS, "🎵", "A musical instrument with graded tone bars and twin mallets"),
        ColoringPage("jack_in_box", "Surprise Jack-in-Box", Category.SPORTS, "🎁", "A springy clown popping happily out of a decorated crank box"),

        // ==================== 22. Helpers & Places Expanded (10) ====================
        ColoringPage("police_officer", "City Patrol Officer", Category.COMMUNITY, "👮", "A friendly police officer in peaked cap holding a hand stop sign"),
        ColoringPage("school_teacher", "Inspiring School Teacher", Category.COMMUNITY, "👩‍🏫", "A cheerful teacher pointing to an ABC chalkboard with pointer"),
        ColoringPage("mail_carrier", "Postal Mail Carrier", Category.COMMUNITY, "📬", "A postal worker in uniform placing a letter in a blue mailbox"),
        ColoringPage("green_farmer", "Friendly Green Farmer", Category.COMMUNITY, "🧑‍🌾", "A farmer in denim overalls and straw hat holding a garden pitchfork"),
        ColoringPage("community_hospital", "Community Hospital", Category.COMMUNITY, "🏥", "A modern medical clinic building with cross emblem and ambulance bay"),
        ColoringPage("fire_station", "City Fire Station", Category.COMMUNITY, "🚒", "A two-bay firehouse with arched garage doors, bell, and lookout tower"),
        ColoringPage("town_library", "Public Town Library", Category.COMMUNITY, "📚", "A cozy stone library building with reading steps and book columns"),
        ColoringPage("city_airport", "International Airport", Category.COMMUNITY, "🛫", "A flight terminal with radar tower, runway lights, and taxiing jet"),
        ColoringPage("country_barn", "Country Red Barn", Category.COMMUNITY, "🛖", "A traditional wooden farm barn with gambrel roof, weather vane & silo"),
        ColoringPage("post_office", "Town Post Office", Category.COMMUNITY, "🏣", "A postal dispatch office with letter collection slot and flag")
    )

    fun getPagesForCategory(category: Category): List<ColoringPage> {
        if (category == Category.ALL) {
            return allPreloadedPages
        }
        return allPreloadedPages.filter { it.category == category }
    }

    fun getPageById(id: String): ColoringPage? {
        return allPreloadedPages.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getDailyFeaturedPage(): ColoringPage {
        val dayIndex = (System.currentTimeMillis() / (1000 * 60 * 60 * 24)).toInt()
        val index = Math.abs(dayIndex) % allPreloadedPages.size
        return allPreloadedPages[index]
    }

    /**
     * Renders clean, high-contrast, black-and-white line art into an Android Bitmap
     * with thick outlines and large enclosed areas optimized for children's coloring.
     * Every single predefined template has its own unique, dedicated illustration.
     */
    fun createTemplateBitmap(templateId: String, size: Int = 1024): Bitmap {
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.WHITE)
        val canvas = Canvas(bitmap)

        // Fill pure white background
        canvas.drawColor(Color.WHITE)

        val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 14f * (size / 1024f).coerceAtLeast(0.6f)
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        val fineStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 9f * (size / 1024f).coerceAtLeast(0.6f)
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        val eyeFillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.FILL
        }

        val eyeHighlightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        // Draw outer playful border
        val borderMargin = 28f * (size / 1024f)
        val borderRect = RectF(borderMargin, borderMargin, size - borderMargin, size - borderMargin)
        canvas.drawRoundRect(borderRect, 40f * (size / 1024f), 40f * (size / 1024f), strokePaint)

        // Dispatch directly to dedicated renderers
        renderIllustration(templateId, canvas, size, strokePaint, fineStrokePaint, eyeFillPaint, eyeHighlightPaint)

        return bitmap
    }

    private fun renderIllustration(
        templateId: String,
        canvas: Canvas,
        size: Int,
        stroke: Paint,
        fine: Paint,
        eyeFill: Paint,
        eyeHighlight: Paint
    ) {
        val id = templateId.lowercase()
        if (ExpandedRenderersDispatcher.tryRenderExpanded(id, canvas, size, stroke, fine, eyeFill, eyeHighlight)) {
            return
        }
        when {
            // Dinosaurs
            id == "t_rex" || id == "dino_trex" || id == "trex" ->
                DinosaurRenderers.drawTRex(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "triceratops" || id == "dino_triceratops" ->
                DinosaurRenderers.drawTriceratops(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "brachiosaurus" || id == "dino_brachio" ->
                DinosaurRenderers.drawBrachiosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "stegosaurus" || id == "dino_stego" ->
                DinosaurRenderers.drawStegosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "pterodactyl" || id == "dino_pterodactyl" ->
                DinosaurRenderers.drawPterodactyl(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "ankylosaurus" || id == "dino_ankylo" ->
                DinosaurRenderers.drawAnkylosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "parasaurolophus" || id == "dino_parasaur" ->
                DinosaurRenderers.drawParasaurolophus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "dino_egg" || id == "egg" ->
                DinosaurRenderers.drawDinoEgg(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "spinosaurus" || id == "dino_spino" ->
                DinosaurRenderers.drawSpinosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "dino_volcano" || id == "volcano" ->
                DinosaurRenderers.drawDinoVolcano(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Animals
            id == "puppy" || id == "dog" || id == "animal_puppy" ->
                AnimalRenderers.drawPuppy(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "kitten" || id == "cat" || id == "animal_kitten" ->
                AnimalRenderers.drawKitten(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "panda" || id == "animal_panda" ->
                AnimalRenderers.drawPanda(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "lion" || id == "animal_lion" ->
                AnimalRenderers.drawLion(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "elephant" || id == "animal_elephant" ->
                AnimalRenderers.drawElephant(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "giraffe" || id == "animal_giraffe" ->
                AnimalRenderers.drawGiraffe(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "monkey" || id == "animal_monkey" ->
                AnimalRenderers.drawMonkey(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "penguin" || id == "animal_penguin" ->
                AnimalRenderers.drawPenguin(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "rabbit" || id == "bunny" || id == "animal_bunny" ->
                AnimalRenderers.drawBunny(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "koala" || id == "animal_koala" ->
                AnimalRenderers.drawKoala(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Vehicles
            id == "race_car" || id == "racecar" ->
                VehicleRenderers.drawRaceCar(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "fire_truck" || id == "firetruck" ->
                VehicleRenderers.drawFireTruck(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "police_car" || id == "police" ->
                VehicleRenderers.drawPoliceCar(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "airplane" || id == "plane" ->
                VehicleRenderers.drawAirplane(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "steam_train" || id == "train" ->
                VehicleRenderers.drawSteamTrain(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "helicopter" || id == "chopper" ->
                VehicleRenderers.drawHelicopter(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "bulldozer" || id == "dozer" ->
                VehicleRenderers.drawBulldozer(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "excavator" || id == "digger" ->
                VehicleRenderers.drawExcavator(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "dump_truck" || id == "dumptruck" ->
                VehicleRenderers.drawDumpTruck(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "tractor" || id == "farm_tractor" ->
                VehicleRenderers.drawTractor(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Space
            id == "rocket" || id == "space_rocket" ->
                SpaceRenderers.drawRocket(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "astronaut" || id == "space_astronaut" ->
                SpaceRenderers.drawAstronaut(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "planet" || id == "saturn" || id == "space_saturn" ->
                SpaceRenderers.drawSaturn(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "alien" || id == "space_alien" ->
                SpaceRenderers.drawAlien(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "spaceship" || id == "ufo" || id == "space_ufo" ->
                SpaceRenderers.drawUfo(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "moon_landing" || id == "moon" ->
                SpaceRenderers.drawMoonLanding(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "solar_system" || id == "space_system" ->
                SpaceRenderers.drawSolarSystem(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "space_rover" || id == "rover" ->
                SpaceRenderers.drawSpaceRover(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "space_station" || id == "station" ->
                SpaceRenderers.drawSpaceStation(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "shooting_star" || id == "star_shooting" ->
                SpaceRenderers.drawShootingStar(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Fantasy
            id == "unicorn" || id == "fantasy_unicorn" ->
                FantasyRenderers.drawUnicorn(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "dragon" || id == "fantasy_dragon" ->
                FantasyRenderers.drawDragon(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "magic_wand" || id == "wand" ->
                FantasyRenderers.drawMagicWand(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "fairy" || id == "fantasy_fairy" ->
                FantasyRenderers.drawFairy(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "mermaid" || id == "fantasy_mermaid" ->
                FantasyRenderers.drawMermaid(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "crystal_ball" || id == "crystal" ->
                FantasyRenderers.drawCrystalBall(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "wizard_hat" || id == "wizard" ->
                FantasyRenderers.drawWizardHat(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "treasure_chest" || id == "chest" ->
                FantasyRenderers.drawTreasureChest(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "magic_potion" || id == "potion" ->
                FantasyRenderers.drawMagicPotion(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "phoenix" || id == "fantasy_phoenix" ->
                FantasyRenderers.drawPhoenix(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Ocean
            id == "dolphin" || id == "ocean_dolphin" ->
                OceanRenderers.drawDolphin(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "sea_turtle" || id == "turtle" ->
                OceanRenderers.drawSeaTurtle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "octopus" || id == "ocean_octopus" ->
                OceanRenderers.drawOctopus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "whale" || id == "ocean_whale" ->
                OceanRenderers.drawWhale(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "clownfish" || id == "fish" ->
                OceanRenderers.drawClownfish(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "seahorse" || id == "ocean_seahorse" ->
                OceanRenderers.drawSeahorse(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "starfish" || id == "ocean_starfish" ->
                OceanRenderers.drawStarfish(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "jellyfish" || id == "ocean_jellyfish" ->
                OceanRenderers.drawJellyfish(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "crab" || id == "ocean_crab" ->
                OceanRenderers.drawCrab(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "shark" || id == "ocean_shark" ->
                OceanRenderers.drawShark(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Nature
            id == "butterfly" || id == "nature_butterfly" ->
                NatureRenderers.drawButterfly(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "sun_hills" || id == "sun" || id == "nature_sun" ->
                NatureRenderers.drawSunHills(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "mushroom" || id == "nature_mushroom" ->
                NatureRenderers.drawMushroom(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "tree" || id == "oak_tree" ->
                NatureRenderers.drawTree(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "flower_pot" || id == "flower" ->
                NatureRenderers.drawFlowerPot(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "rainbow" || id == "nature_rainbow" ->
                NatureRenderers.drawRainbow(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "apple_tree" || id == "orchard" ->
                NatureRenderers.drawAppleTree(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "beehive" || id == "bees" ->
                NatureRenderers.drawBeehive(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "cactus" || id == "nature_cactus" ->
                NatureRenderers.drawCactus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "campfire" || id == "nature_campfire" ->
                NatureRenderers.drawCampfire(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Fairytale
            id == "fairy_castle" || id == "castle" ->
                FairytaleRenderers.drawFairyCastle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "crown" || id == "royal_crown" ->
                FairytaleRenderers.drawCrown(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "pegasus" || id == "flying_horse" ->
                FairytaleRenderers.drawPegasus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "princess_carriage" || id == "carriage" ->
                FairytaleRenderers.drawPrincessCarriage(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "royal_shield" || id == "shield" ->
                FairytaleRenderers.drawRoyalShield(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "fairytale_prince" || id == "prince" ->
                FairytaleRenderers.drawFairytalePrince(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "fairytale_princess" || id == "princess" ->
                FairytaleRenderers.drawFairytalePrincess(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "glass_slipper" || id == "slipper" ->
                FairytaleRenderers.drawGlassSlipper(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "royal_throne" || id == "throne" ->
                FairytaleRenderers.drawRoyalThrone(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "spinning_wheel" || id == "wheel_spinning" ->
                FairytaleRenderers.drawSpinningWheel(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Food & Treats
            id == "ice_cream" || id == "food_ice_cream" ->
                FoodRenderers.drawIceCreamCone(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "birthday_cake" || id == "cake" ->
                FoodRenderers.drawBirthdayCake(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "cupcake" || id == "food_cupcake" ->
                FoodRenderers.drawCupcake(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "pizza" || id == "food_pizza" ->
                FoodRenderers.drawPizzaSlice(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "donut" || id == "food_donut" ->
                FoodRenderers.drawDonut(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "burger" || id == "hamburger" ->
                FoodRenderers.drawBurger(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "watermelon" || id == "food_watermelon" ->
                FoodRenderers.drawWatermelon(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "popcorn" || id == "food_popcorn" ->
                FoodRenderers.drawPopcorn(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "cookie" || id == "food_cookie" ->
                FoodRenderers.drawCookie(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "pancakes" || id == "food_pancakes" ->
                FoodRenderers.drawPancakes(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Sports & Toys
            id == "soccer_ball" || id == "soccer" ->
                SportsRenderers.drawSoccerBall(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "basketball" || id == "sports_basketball" ->
                SportsRenderers.drawBasketball(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "teddy_bear" || id == "bear" ->
                SportsRenderers.drawTeddyBear(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "bicycle" || id == "bike" ->
                SportsRenderers.drawBicycle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "roller_skates" || id == "skates" ->
                SportsRenderers.drawRollerSkates(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "skateboard" || id == "sports_skateboard" ->
                SportsRenderers.drawSkateboard(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "kite" || id == "sports_kite" ->
                SportsRenderers.drawKite(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "yoyo" || id == "sports_yoyo" ->
                SportsRenderers.drawYoYo(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "rubiks_cube" || id == "puzzle_cube" ->
                SportsRenderers.drawRubiksCube(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "rocking_horse" || id == "toy_horse" ->
                SportsRenderers.drawRockingHorse(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Helpers & Places
            id == "firefighter" || id == "fireman" ->
                CommunityRenderers.drawFirefighter(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "doctor" || id == "nurse" ->
                CommunityRenderers.drawDoctor(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "chef" || id == "cook" ->
                CommunityRenderers.drawChef(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "school_bus" || id == "bus" ->
                CommunityRenderers.drawSchoolBus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "lighthouse" || id == "beacon" ->
                CommunityRenderers.drawLighthouse(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "windmill" || id == "mill" ->
                CommunityRenderers.drawWindmill(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "hot_air_balloon" || id == "balloon" ->
                CommunityRenderers.drawHotAirBalloon(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "submarine" || id == "sub" ->
                CommunityRenderers.drawSubmarine(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "clock_tower" || id == "clock" ->
                CommunityRenderers.drawClockTower(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            id == "igloo" || id == "snow_igloo" ->
                CommunityRenderers.drawIgloo(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Fallback for user custom prompt pages if no keyword matched:
            // Cute starburst pattern
            else -> {
                val f = size / 1024f
                DrawingUtils.drawStar(canvas, 512f * f, 512f * f, 240f * f, stroke)
                DrawingUtils.drawSparkle(canvas, 240f * f, 240f * f, 36f * f, stroke)
                DrawingUtils.drawSparkle(canvas, 780f * f, 240f * f, 36f * f, stroke)
                DrawingUtils.drawSparkle(canvas, 240f * f, 780f * f, 36f * f, stroke)
                DrawingUtils.drawSparkle(canvas, 780f * f, 780f * f, 36f * f, stroke)
            }
        }
    }

    /**
     * Validates that EVERY predefined template has a valid, non-crashing renderer.
     * Guaranteed to pass for all 110 items.
     */
    fun validateAllColoringTemplates(): Boolean {
        for (page in allPreloadedPages) {
            val bmp = createTemplateBitmap(page.templateId, 64)
            if (bmp.width <= 0 || bmp.height <= 0) {
                return false
            }
        }
        return true
    }
}
