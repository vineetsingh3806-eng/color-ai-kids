package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint

object ExpandedRenderersDispatcher {

    fun tryRenderExpanded(
        id: String,
        canvas: Canvas,
        size: Int,
        stroke: Paint,
        fine: Paint,
        eyeFill: Paint,
        eyeHighlight: Paint
    ): Boolean {
        when (id) {
            // Dinosaurs (10 new)
            "velociraptor" -> ExpandedDinoRenderers.drawVelociraptor(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "diplodocus" -> ExpandedDinoRenderers.drawDiplodocus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "pachycephalosaurus" -> ExpandedDinoRenderers.drawPachycephalosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "allosaurus" -> ExpandedDinoRenderers.drawAllosaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "plesiosaur" -> ExpandedDinoRenderers.drawPlesiosaur(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "archaeopteryx" -> ExpandedDinoRenderers.drawArchaeopteryx(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "carnotaurus" -> ExpandedDinoRenderers.drawCarnotaurus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "iguanodon" -> ExpandedDinoRenderers.drawIguanodon(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "dino_fossil" -> ExpandedDinoRenderers.drawDinoFossil(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "baby_sauropod" -> ExpandedDinoRenderers.drawBabySauropod(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Animals (10 new)
            "tiger" -> ExpandedAnimalRenderers.drawTiger(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "fox" -> ExpandedAnimalRenderers.drawFox(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "grizzly_bear" -> ExpandedAnimalRenderers.drawGrizzlyBear(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "zebra" -> ExpandedAnimalRenderers.drawZebra(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "kangaroo" -> ExpandedAnimalRenderers.drawKangaroo(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "hippopotamus" -> ExpandedAnimalRenderers.drawHippopotamus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "owl" -> ExpandedAnimalRenderers.drawOwl(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "hedgehog" -> ExpandedAnimalRenderers.drawHedgehog(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sloth" -> ExpandedAnimalRenderers.drawSloth(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "flamingo" -> ExpandedAnimalRenderers.drawFlamingo(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Cars & Vehicles (10 new)
            "ambulance" -> ExpandedVehicleRenderers.drawAmbulance(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "monster_truck" -> ExpandedVehicleRenderers.drawMonsterTruck(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "cement_mixer" -> ExpandedVehicleRenderers.drawCementMixer(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "garbage_truck" -> ExpandedVehicleRenderers.drawGarbageTruck(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "tow_truck" -> ExpandedVehicleRenderers.drawTowTruck(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "motorcycle" -> ExpandedVehicleRenderers.drawMotorcycle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sailboat" -> ExpandedVehicleRenderers.drawSailboat(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "cargo_ship" -> ExpandedVehicleRenderers.drawCargoShip(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "space_shuttle" -> ExpandedVehicleRenderers.drawSpaceShuttle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "cable_car" -> ExpandedVehicleRenderers.drawCableCar(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Space (10 new)
            "black_hole" -> ExpandedSpaceRenderers.drawBlackHole(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "space_telescope" -> ExpandedSpaceRenderers.drawSpaceTelescope(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "comet" -> ExpandedSpaceRenderers.drawComet(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "meteor_shower" -> ExpandedSpaceRenderers.drawMeteorShower(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "alien_mothership" -> ExpandedSpaceRenderers.drawAlienMothership(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "airlock_module" -> ExpandedSpaceRenderers.drawAirlockModule(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "cosmic_nebula" -> ExpandedSpaceRenderers.drawCosmicNebula(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "solar_eclipse" -> ExpandedSpaceRenderers.drawSolarEclipse(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "spiral_galaxy" -> ExpandedSpaceRenderers.drawSpiralGalaxy(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "satellite_orbit" -> ExpandedSpaceRenderers.drawSatelliteOrbit(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Fantasy (10 new)
            "griffin" -> ExpandedFantasyRenderers.drawGriffin(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "pegasus_foal" -> ExpandedFantasyRenderers.drawPegasusFoal(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "spellbook" -> ExpandedFantasyRenderers.drawSpellbook(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "magic_cauldron" -> ExpandedFantasyRenderers.drawMagicCauldron(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "crystal_cave" -> ExpandedFantasyRenderers.drawCrystalCave(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "stone_gargoyle" -> ExpandedFantasyRenderers.drawStoneGargoyle(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "tree_guardian" -> ExpandedFantasyRenderers.drawTreeGuardian(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "flying_carpet" -> ExpandedFantasyRenderers.drawFlyingCarpet(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "treasure_island" -> ExpandedFantasyRenderers.drawTreasureIsland(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "genie_lamp" -> ExpandedFantasyRenderers.drawGenieLamp(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Ocean (10 new)
            "hammerhead_shark" -> ExpandedOceanRenderers.drawHammerheadShark(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "manta_ray" -> ExpandedOceanRenderers.drawMantaRay(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "arctic_walrus" -> ExpandedOceanRenderers.drawArcticWalrus(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "reef_lobster" -> ExpandedOceanRenderers.drawReefLobster(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "coral_reef" -> ExpandedOceanRenderers.drawCoralReef(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sea_otter" -> ExpandedOceanRenderers.drawSeaOtter(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sandy_stingray" -> ExpandedOceanRenderers.drawSandyStingray(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "swordfish" -> ExpandedOceanRenderers.drawSwordfish(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "giant_squid" -> ExpandedOceanRenderers.drawGiantSquid(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "pearl_clam" -> ExpandedOceanRenderers.drawPearlClam(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Nature (10 new)
            "garden_rose" -> ExpandedNatureRenderers.drawGardenRose(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "spring_tulip" -> ExpandedNatureRenderers.drawSpringTulip(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sunflower" -> ExpandedNatureRenderers.drawSunflower(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "pine_conifer" -> ExpandedNatureRenderers.drawPineConifer(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "waterfall" -> ExpandedNatureRenderers.drawWaterfall(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "woodland_stream" -> ExpandedNatureRenderers.drawWoodlandStream(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "autumn_leaves" -> ExpandedNatureRenderers.drawAutumnLeaves(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "oak_acorn" -> ExpandedNatureRenderers.drawOakAcorn(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "daisy_meadow" -> ExpandedNatureRenderers.drawDaisyMeadow(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "mountain_peak" -> ExpandedNatureRenderers.drawMountainPeak(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Fairytale (10 new)
            "castle_turret" -> ExpandedFairytaleRenderers.drawCastleTurret(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "magic_mirror" -> ExpandedFairytaleRenderers.drawMagicMirror(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "enchanted_rose" -> ExpandedFairytaleRenderers.drawEnchantedRose(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "storybook" -> ExpandedFairytaleRenderers.drawStorybook(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "knight_helmet" -> ExpandedFairytaleRenderers.drawKnightHelmet(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "sword_in_stone" -> ExpandedFairytaleRenderers.drawSwordInStone(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "gingerbread_house" -> ExpandedFairytaleRenderers.drawGingerbreadHouse(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "magic_beanstalk" -> ExpandedFairytaleRenderers.drawMagicBeanstalk(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "royal_banner" -> ExpandedFairytaleRenderers.drawRoyalBanner(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "royal_quill" -> ExpandedFairytaleRenderers.drawRoyalQuill(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Food (10 new)
            "crunchy_taco" -> ExpandedFoodRenderers.drawCrunchyTaco(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "hot_dog" -> ExpandedFoodRenderers.drawHotDog(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "french_fries" -> ExpandedFoodRenderers.drawFrenchFries(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "maki_sushi" -> ExpandedFoodRenderers.drawMakiSushi(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "strawberry" -> ExpandedFoodRenderers.drawStrawberry(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "honeycrisp_apple" -> ExpandedFoodRenderers.drawHoneycrispApple(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "twin_cherries" -> ExpandedFoodRenderers.drawTwinCherries(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "rainbow_lollipop" -> ExpandedFoodRenderers.drawRainbowLollipop(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "soft_pretzel" -> ExpandedFoodRenderers.drawSoftPretzel(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "submarine_sandwich" -> ExpandedFoodRenderers.drawSubmarineSandwich(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Sports (10 new)
            "baseball" -> ExpandedSportsRenderers.drawBaseball(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "football" -> ExpandedSportsRenderers.drawFootball(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "tennis_racket" -> ExpandedSportsRenderers.drawTennisRacket(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "trophy_cup" -> ExpandedSportsRenderers.drawTrophyCup(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "bowling_strike" -> ExpandedSportsRenderers.drawBowlingStrike(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "marching_drum" -> ExpandedSportsRenderers.drawMarchingDrum(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "toy_train" -> ExpandedSportsRenderers.drawToyTrain(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "wind_pinwheel" -> ExpandedSportsRenderers.drawWindPinwheel(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "rainbow_xylophone" -> ExpandedSportsRenderers.drawRainbowXylophone(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "jack_in_box" -> ExpandedSportsRenderers.drawJackInBox(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            // Community (10 new)
            "police_officer" -> ExpandedCommunityRenderers.drawPoliceOfficer(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "school_teacher" -> ExpandedCommunityRenderers.drawSchoolTeacher(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "mail_carrier" -> ExpandedCommunityRenderers.drawMailCarrier(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "green_farmer" -> ExpandedCommunityRenderers.drawGreenFarmer(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "community_hospital" -> ExpandedCommunityRenderers.drawCommunityHospital(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "fire_station" -> ExpandedCommunityRenderers.drawFireStation(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "town_library" -> ExpandedCommunityRenderers.drawTownLibrary(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "city_airport" -> ExpandedCommunityRenderers.drawCityAirport(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "country_barn" -> ExpandedCommunityRenderers.drawCountryBarn(canvas, size, stroke, fine, eyeFill, eyeHighlight)
            "post_office" -> ExpandedCommunityRenderers.drawPostOffice(canvas, size, stroke, fine, eyeFill, eyeHighlight)

            else -> return false
        }
        return true
    }
}
