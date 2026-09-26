package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCloud
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawFlower
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle

object NatureRenderers {

    fun drawButterfly(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Butterfly Antennae with curled tips
        val antL = Path().apply {
            moveTo(cx - 15f * f, cy - 200f * f)
            cubicTo(cx - 40f * f, cy - 300f * f, cx - 100f * f, cy - 300f * f, cx - 80f * f, cy - 260f * f)
        }
        canvas.drawPath(antL, stroke)
        canvas.drawCircle(cx - 80f * f, cy - 260f * f, 14f * f, stroke)

        val antR = Path().apply {
            moveTo(cx + 15f * f, cy - 200f * f)
            cubicTo(cx + 40f * f, cy - 300f * f, cx + 100f * f, cy - 300f * f, cx + 80f * f, cy - 260f * f)
        }
        canvas.drawPath(antR, stroke)
        canvas.drawCircle(cx + 80f * f, cy - 260f * f, 14f * f, stroke)

        // Upper Wings (Large beautiful butterfly wings)
        val wingTL = Path().apply {
            moveTo(cx - 35f * f, cy - 80f * f)
            cubicTo(cx - 200f * f, cy - 280f * f, cx - 440f * f, cy - 240f * f, cx - 440f * f, cy - 60f * f)
            cubicTo(cx - 440f * f, cy + 40f * f, cx - 280f * f, cy + 80f * f, cx - 35f * f, cy + 20f * f)
            close()
        }
        canvas.drawPath(wingTL, stroke)

        val wingTR = Path().apply {
            moveTo(cx + 35f * f, cy - 80f * f)
            cubicTo(cx + 200f * f, cy - 280f * f, cx + 440f * f, cy - 240f * f, cx + 440f * f, cy - 60f * f)
            cubicTo(cx + 440f * f, cy + 40f * f, cx + 280f * f, cy + 80f * f, cx + 35f * f, cy + 20f * f)
            close()
        }
        canvas.drawPath(wingTR, stroke)

        // Lower Wings
        val wingBL = Path().apply {
            moveTo(cx - 30f * f, cy + 20f * f)
            cubicTo(cx - 320f * f, cy + 60f * f, cx - 340f * f, cy + 280f * f, cx - 180f * f, cy + 340f * f)
            cubicTo(cx - 100f * f, cy + 360f * f, cx - 60f * f, cy + 240f * f, cx - 20f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(wingBL, stroke)

        val wingBR = Path().apply {
            moveTo(cx + 30f * f, cy + 20f * f)
            cubicTo(cx + 320f * f, cy + 60f * f, cx + 340f * f, cy + 280f * f, cx + 180f * f, cy + 340f * f)
            cubicTo(cx + 100f * f, cy + 360f * f, cx + 60f * f, cy + 240f * f, cx + 20f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(wingBR, stroke)

        // Wing Coloring Spots (Circles inside wings)
        canvas.drawCircle(cx - 260f * f, cy - 80f * f, 50f * f, stroke)
        canvas.drawCircle(cx + 260f * f, cy - 80f * f, 50f * f, stroke)
        canvas.drawCircle(cx - 180f * f, cy + 200f * f, 35f * f, stroke)
        canvas.drawCircle(cx + 180f * f, cy + 200f * f, 35f * f, stroke)

        // Butterfly Slender Body & Head
        canvas.drawCircle(cx, cy - 160f * f, 35f * f, stroke) // head
        drawCuteEye(canvas, cx - 12f * f, cy - 165f * f, 8f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 12f * f, cy - 165f * f, 8f * f, stroke, eyeFill, eyeHighlight)

        val body = RectF(cx - 25f * f, cy - 130f * f, cx + 25f * f, cy + 180f * f)
        canvas.drawRoundRect(body, 25f * f, 25f * f, stroke)
    }

    fun drawSunHills(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Happy Sun in the sky corner
        canvas.drawCircle(220f * f, 240f * f, 90f * f, stroke)
        drawCuteEye(canvas, 190f * f, 220f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, 250f * f, 220f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val sunSmile = Path().apply {
            moveTo(200f * f, 260f * f)
            quadTo(220f * f, 280f * f, 240f * f, 260f * f)
        }
        canvas.drawPath(sunSmile, stroke)
        // Sun rays
        for (i in 0 until 8) {
            val angle = i * Math.PI / 4
            val x1 = (220 + Math.cos(angle) * 110).toFloat() * f
            val y1 = (240 + Math.sin(angle) * 110).toFloat() * f
            val x2 = (220 + Math.cos(angle) * 150).toFloat() * f
            val y2 = (240 + Math.sin(angle) * 150).toFloat() * f
            canvas.drawLine(x1, y1, x2, y2, stroke)
        }

        // Clouds in sky
        drawCloud(canvas, 720f * f, 220f * f, 260f * f, 90f * f, stroke)

        // Rolling Hills in Background
        val backHill = Path().apply {
            moveTo(60f * f, 620f * f)
            cubicTo(260f * f, 480f * f, 600f * f, 480f * f, 960f * f, 640f * f)
        }
        canvas.drawPath(backHill, stroke)

        // Rolling Hills in Foreground
        val foreHill = Path().apply {
            moveTo(60f * f, 740f * f)
            cubicTo(380f * f, 560f * f, 740f * f, 780f * f, 960f * f, 720f * f)
        }
        canvas.drawPath(foreHill, stroke)

        // Winding Country Path
        val path = Path().apply {
            moveTo(360f * f, 890f * f)
            cubicTo(420f * f, 760f * f, 480f * f, 700f * f, 500f * f, 620f * f)
            lineTo(540f * f, 620f * f)
            cubicTo(520f * f, 700f * f, 480f * f, 760f * f, 440f * f, 890f * f)
            close()
        }
        canvas.drawPath(path, stroke)

        // Cute Flowers on the hills
        drawFlower(canvas, 180f * f, 780f * f, 24f * f, stroke)
        drawFlower(canvas, 780f * f, 780f * f, 28f * f, stroke)
        drawFlower(canvas, 860f * f, 840f * f, 22f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawMushroom(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Big Rounded Mushroom Cap
        val cap = Path().apply {
            moveTo(cx - 320f * f, cy)
            cubicTo(cx - 340f * f, cy - 280f * f, cx - 180f * f, cy - 360f * f, cx, cy - 360f * f)
            cubicTo(cx + 180f * f, cy - 360f * f, cx + 340f * f, cy - 280f * f, cx + 320f * f, cy)
            quadTo(cx, cy + 60f * f, cx - 320f * f, cy)
            close()
        }
        canvas.drawPath(cap, stroke)

        // Big Polka Dots on Cap
        canvas.drawCircle(cx - 160f * f, cy - 180f * f, 45f * f, stroke)
        canvas.drawCircle(cx + 160f * f, cy - 180f * f, 45f * f, stroke)
        canvas.drawCircle(cx, cy - 260f * f, 55f * f, stroke)
        canvas.drawCircle(cx - 200f * f, cy - 40f * f, 35f * f, stroke)
        canvas.drawCircle(cx + 200f * f, cy - 40f * f, 35f * f, stroke)

        // Stout Friendly Mushroom Stem
        val stem = Path().apply {
            moveTo(cx - 140f * f, cy + 20f * f)
            cubicTo(cx - 180f * f, cy + 180f * f, cx - 160f * f, cy + 340f * f, cx - 180f * f, cy + 420f * f)
            lineTo(cx + 180f * f, cy + 420f * f)
            cubicTo(cx + 160f * f, cy + 340f * f, cx + 180f * f, cy + 180f * f, cx + 140f * f, cy + 20f * f)
            close()
        }
        canvas.drawPath(stem, stroke)

        // Cute Face on Mushroom Stem
        drawCuteEye(canvas, cx - 55f * f, cy + 160f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 55f * f, cy + 160f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        // Rosy Cheeks
        canvas.drawCircle(cx - 95f * f, cy + 210f * f, 16f * f, fine)
        canvas.drawCircle(cx + 95f * f, cy + 210f * f, 16f * f, fine)
        val smile = Path().apply {
            moveTo(cx - 35f * f, cy + 220f * f)
            quadTo(cx, cy + 260f * f, cx + 35f * f, cy + 220f * f)
        }
        canvas.drawPath(smile, stroke)

        // Little Ladybug on cap
        canvas.drawCircle(cx + 250f * f, cy - 120f * f, 24f * f, stroke)
        canvas.drawCircle(cx + 265f * f, cy - 130f * f, 12f * f, eyeFill) // ladybug head

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawTree(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Large Billowing Leafy Canopy (Cloud-like puffs)
        val canopy = Path().apply {
            val cy = 360f * f
            val r = 240f * f
            moveTo(cx - 240f * f, cy + 100f * f)
            cubicTo(cx - 380f * f, cy + 60f * f, cx - 380f * f, cy - 140f * f, cx - 260f * f, cy - 180f * f)
            cubicTo(cx - 280f * f, cy - 320f * f, cx - 100f * f, cy - 380f * f, cx, cy - 320f * f)
            cubicTo(cx + 100f * f, cy - 380f * f, cx + 280f * f, cy - 320f * f, cx + 260f * f, cy - 180f * f)
            cubicTo(cx + 380f * f, cy - 140f * f, cx + 380f * f, cy + 60f * f, cx + 240f * f, cy + 100f * f)
            close()
        }
        canvas.drawPath(canopy, stroke)

        // Thick Oak Tree Trunk & Roots
        val trunk = Path().apply {
            moveTo(cx - 80f * f, 440f * f)
            cubicTo(cx - 70f * f, 620f * f, cx - 140f * f, 780f * f, cx - 220f * f, 880f * f)
            lineTo(cx + 220f * f, 880f * f)
            cubicTo(cx + 140f * f, 780f * f, cx + 70f * f, 620f * f, cx + 80f * f, 440f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)

        // Cute Squirrel Hollow Hole in Trunk
        canvas.drawOval(RectF(cx - 40f * f, 580f * f, cx + 40f * f, 680f * f), stroke)
        // Little peeking eyes inside hole
        canvas.drawCircle(cx - 15f * f, 620f * f, 6f * f, eyeFill)
        canvas.drawCircle(cx + 15f * f, 620f * f, 6f * f, eyeFill)

        // Wood grain lines
        canvas.drawLine(cx - 40f * f, 720f * f, cx - 60f * f, 840f * f, fine)
        canvas.drawLine(cx + 40f * f, 720f * f, cx + 60f * f, 840f * f, fine)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawFlowerPot(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 620f * f

        // Flower Terracotta Pot Rim
        val rim = RectF(cx - 180f * f, cy - 60f * f, cx + 180f * f, cy)
        canvas.drawRoundRect(rim, 18f * f, 18f * f, stroke)

        // Flower Pot Body
        val pot = Path().apply {
            moveTo(cx - 150f * f, cy)
            lineTo(cx - 120f * f, cy + 240f * f)
            lineTo(cx + 120f * f, cy + 240f * f)
            lineTo(cx + 150f * f, cy)
            close()
        }
        canvas.drawPath(pot, stroke)

        // Cute Face on Pot
        drawCuteEye(canvas, cx - 45f * f, cy + 90f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy + 90f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val potSmile = Path().apply {
            moveTo(cx - 25f * f, cy + 140f * f)
            quadTo(cx, cy + 165f * f, cx + 25f * f, cy + 140f * f)
        }
        canvas.drawPath(potSmile, stroke)

        // Flower Stem
        canvas.drawLine(cx, cy - 60f * f, cx, 280f * f, stroke)

        // Big Leaves on Stem
        val leafL = Path().apply {
            moveTo(cx, 440f * f)
            cubicTo(cx - 120f * f, 400f * f, cx - 180f * f, 480f * f, cx - 80f * f, 500f * f)
            cubicTo(cx - 40f * f, 500f * f, cx - 20f * f, 460f * f, cx, 440f * f)
            close()
        }
        canvas.drawPath(leafL, stroke)

        val leafR = Path().apply {
            moveTo(cx, 380f * f)
            cubicTo(cx + 120f * f, 340f * f, cx + 180f * f, 420f * f, cx + 80f * f, 440f * f)
            cubicTo(cx + 40f * f, 440f * f, cx + 20f * f, 400f * f, cx, 380f * f)
            close()
        }
        canvas.drawPath(leafR, stroke)

        // Giant Blossom Flower Bloom
        drawFlower(canvas, cx, 260f * f, 110f * f, stroke)
        // Cute face in flower center
        drawCuteEye(canvas, cx - 22f * f, 255f * f, 12f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 22f * f, 255f * f, 12f * f, stroke, eyeFill, eyeHighlight)
        val flowerSmile = Path().apply {
            moveTo(cx - 12f * f, 280f * f)
            quadTo(cx, 295f * f, cx + 12f * f, 280f * f)
        }
        canvas.drawPath(flowerSmile, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawRainbow(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 600f * f

        // Fluffy Cloud Left
        drawCloud(canvas, 240f * f, 660f * f, 260f * f, 120f * f, stroke)
        // Fluffy Cloud Right
        drawCloud(canvas, 780f * f, 660f * f, 260f * f, 120f * f, stroke)

        // 5 Arched Rainbow Bands
        val radii = listOf(360f, 320f, 280f, 240f, 200f)
        for (r in radii) {
            val arc = RectF((cx - r) * f, (cy - r) * f, (cx + r) * f, (cy + r) * f)
            canvas.drawArc(arc, 180f, 180f, false, stroke)
        }

        // Cute smiling sun peeking over rainbow center
        canvas.drawArc(RectF((cx - 80f) * f, 160f * f, (cx + 80f) * f, 320f * f), 180f, 180f, false, stroke)
        drawCuteEye(canvas, cx - 30f * f, 215f * f, 12f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 30f * f, 215f * f, 12f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 15f * f, 235f * f)
            quadTo(cx, 250f * f, cx + 15f * f, 235f * f)
        }
        canvas.drawPath(smile, stroke)

        // Sparkles in the sky
        drawSparkle(canvas, 180f * f, 220f * f, 28f * f, stroke)
        drawSparkle(canvas, 840f * f, 240f * f, 32f * f, stroke)
    }

    fun drawAppleTree(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Leafy Canopy
        val canopy = Path().apply {
            val cy = 340f * f
            moveTo(cx - 240f * f, cy + 100f * f)
            cubicTo(cx - 380f * f, cy + 60f * f, cx - 380f * f, cy - 140f * f, cx - 260f * f, cy - 180f * f)
            cubicTo(cx - 280f * f, cy - 320f * f, cx - 100f * f, cy - 380f * f, cx, cy - 320f * f)
            cubicTo(cx + 100f * f, cy - 380f * f, cx + 280f * f, cy - 320f * f, cx + 260f * f, cy - 180f * f)
            cubicTo(cx + 380f * f, cy - 140f * f, cx + 380f * f, cy + 60f * f, cx + 240f * f, cy + 100f * f)
            close()
        }
        canvas.drawPath(canopy, stroke)

        // Apples hanging on tree
        val applePositions = listOf(
            cx - 180f to 260f, cx - 60f to 200f, cx + 80f to 220f,
            cx + 200f to 300f, cx - 100f to 360f, cx + 60f to 380f
        )
        for ((ax, ay) in applePositions) {
            canvas.drawCircle(ax * f, ay * f, 28f * f, stroke)
            // Little apple stem and leaf
            canvas.drawLine(ax * f, (ay - 28f) * f, (ax + 5f) * f, (ay - 42f) * f, fine)
        }

        // Tree Trunk
        val trunk = Path().apply {
            moveTo(cx - 70f * f, 420f * f)
            cubicTo(cx - 60f * f, 620f * f, cx - 120f * f, 780f * f, cx - 180f * f, 880f * f)
            lineTo(cx + 180f * f, 880f * f)
            cubicTo(cx + 120f * f, 780f * f, cx + 60f * f, 620f * f, cx + 70f * f, 420f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)

        // Apple Basket on ground
        val basket = Path().apply {
            moveTo(cx + 160f * f, 800f * f)
            lineTo(cx + 320f * f, 800f * f)
            lineTo(cx + 300f * f, 880f * f)
            lineTo(cx + 180f * f, 880f * f)
            close()
        }
        canvas.drawPath(basket, stroke)
        canvas.drawCircle(cx + 210f * f, 790f * f, 20f * f, stroke)
        canvas.drawCircle(cx + 250f * f, 785f * f, 20f * f, stroke)
        canvas.drawCircle(cx + 280f * f, 795f * f, 20f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBeehive(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Tree Branch holding hive
        val branch = Path().apply {
            moveTo(80f * f, cy - 260f * f)
            cubicTo(300f * f, cy - 240f * f, cx, cy - 260f * f, cx + 240f * f, cy - 220f * f)
            lineTo(cx + 240f * f, cy - 180f * f)
            cubicTo(cx, cy - 210f * f, 300f * f, cy - 200f * f, 80f * f, cy - 210f * f)
            close()
        }
        canvas.drawPath(branch, stroke)

        // Straw Skep Beehive (Tiered rounded dome)
        val tiers = listOf(
            RectF(cx - 90f * f, cy - 210f * f, cx + 90f * f, cy - 140f * f),
            RectF(cx - 150f * f, cy - 150f * f, cx + 150f * f, cy - 60f * f),
            RectF(cx - 190f * f, cy - 70f * f, cx + 190f * f, cy + 40f * f),
            RectF(cx - 180f * f, cy + 30f * f, cx + 180f * f, cy + 140f * f),
            RectF(cx - 130f * f, cy + 130f * f, cx + 130f * f, cy + 220f * f)
        )
        for (tier in tiers) {
            canvas.drawRoundRect(tier, 40f * f, 40f * f, stroke)
        }

        // Beehive Door Hole
        canvas.drawCircle(cx, cy + 60f * f, 32f * f, eyeFill)

        // Cute Busy Bees buzzing around
        val beePositions = listOf(
            cx - 240f to cy - 100f,
            cx + 260f to cy + 20f,
            cx - 220f to cy + 160f
        )
        for ((bx, by) in beePositions) {
            // Bee Body
            canvas.drawOval(RectF((bx - 25f) * f, (by - 18f) * f, (bx + 25f) * f, (by + 18f) * f), stroke)
            canvas.drawLine(bx * f, (by - 18f) * f, bx * f, (by + 18f) * f, stroke)
            // Bee Wings
            canvas.drawCircle((bx - 8f) * f, (by - 26f) * f, 12f * f, stroke)
            canvas.drawCircle((bx + 8f) * f, (by - 26f) * f, 12f * f, stroke)
        }

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawCactus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Main Center Saguaro Stem
        val stem = RectF(cx - 60f * f, cy - 280f * f, cx + 60f * f, cy + 340f * f)
        canvas.drawRoundRect(stem, 60f * f, 60f * f, stroke)

        // Left Curved Arm
        val armL = Path().apply {
            moveTo(cx - 60f * f, cy + 80f * f)
            lineTo(cx - 180f * f, cy + 80f * f)
            cubicTo(cx - 230f * f, cy + 80f * f, cx - 230f * f, cy - 60f * f, cx - 230f * f, cy - 120f * f)
            cubicTo(cx - 230f * f, cy - 160f * f, cx - 170f * f, cy - 160f * f, cx - 170f * f, cy - 120f * f)
            lineTo(cx - 170f * f, cy)
            lineTo(cx - 60f * f, cy)
            close()
        }
        canvas.drawPath(armL, stroke)

        // Right Curved Arm
        val armR = Path().apply {
            moveTo(cx + 60f * f, cy + 20f * f)
            lineTo(cx + 180f * f, cy + 20f * f)
            cubicTo(cx + 230f * f, cy + 20f * f, cx + 230f * f, cy - 120f * f, cx + 230f * f, cy - 180f * f)
            cubicTo(cx + 230f * f, cy - 220f * f, cx + 170f * f, cy - 220f * f, cx + 170f * f, cy - 180f * f)
            lineTo(cx + 170f * f, cy - 60f * f)
            lineTo(cx + 60f * f, cy - 60f * f)
            close()
        }
        canvas.drawPath(armR, stroke)

        // Cute Face on Cactus
        drawCuteEye(canvas, cx - 24f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 24f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 15f * f, cy - 90f * f)
            quadTo(cx, cy - 75f * f, cx + 15f * f, cy - 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Flower Blossom on top
        drawFlower(canvas, cx, cy - 300f * f, 24f * f, stroke)

        // Prickly Needle Spines
        val spinesY = listOf(-220f, -40f, 140f, 240f)
        for (sy in spinesY) {
            canvas.drawLine((cx - 80f) * f, (cy + sy) * f, (cx - 60f) * f, (cy + sy) * f, fine)
            canvas.drawLine((cx + 60f) * f, (cy + sy) * f, (cx + 80f) * f, (cy + sy) * f, fine)
        }

        // Desert Sand Dune
        val dune = Path().apply {
            moveTo(60f * f, 860f * f)
            cubicTo(320f * f, 800f * f, 680f * f, 880f * f, 960f * f, 840f * f)
        }
        canvas.drawPath(dune, stroke)
    }

    fun drawCampfire(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f

        // Criss-Cross Wooden Logs at bottom
        val logL = Path().apply {
            moveTo(cx - 260f * f, cy + 180f * f)
            lineTo(cx + 180f * f, cy + 300f * f)
            lineTo(cx + 150f * f, cy + 340f * f)
            lineTo(cx - 290f * f, cy + 220f * f)
            close()
        }
        canvas.drawPath(logL, stroke)

        val logR = Path().apply {
            moveTo(cx + 260f * f, cy + 180f * f)
            lineTo(cx - 180f * f, cy + 300f * f)
            lineTo(cx - 150f * f, cy + 340f * f)
            lineTo(cx + 290f * f, cy + 220f * f)
            close()
        }
        canvas.drawPath(logR, stroke)

        // Leaping Flame Tongues (Outer Flame)
        val flameOuter = Path().apply {
            moveTo(cx - 160f * f, cy + 200f * f)
            cubicTo(cx - 220f * f, cy + 80f * f, cx - 180f * f, cy - 80f * f, cx - 60f * f, cy - 140f * f)
            cubicTo(cx - 100f * f, cy - 200f * f, cx - 20f * f, cy - 320f * f, cx, cy - 360f * f) // main fire tip
            cubicTo(cx + 20f * f, cy - 240f * f, cx + 120f * f, cy - 180f * f, cx + 160f * f, cy - 120f * f)
            cubicTo(cx + 240f * f, cy - 40f * f, cx + 220f * f, cy + 100f * f, cx + 160f * f, cy + 200f * f)
            close()
        }
        canvas.drawPath(flameOuter, stroke)

        // Inner Flame for secondary color
        val flameInner = Path().apply {
            moveTo(cx - 90f * f, cy + 200f * f)
            cubicTo(cx - 120f * f, cy + 100f * f, cx - 40f * f, cy, cx, cy - 180f * f)
            cubicTo(cx + 40f * f, cy, cx + 120f * f, cy + 100f * f, cx + 90f * f, cy + 200f * f)
            close()
        }
        canvas.drawPath(flameInner, stroke)

        // Cute Face on Fire
        drawCuteEye(canvas, cx - 40f * f, cy + 40f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, cy + 40f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 24f * f, cy + 85f * f)
            quadTo(cx, cy + 115f * f, cx + 24f * f, cy + 85f * f)
        }
        canvas.drawPath(smile, stroke)

        // Marshmallow Roasting on Stick
        canvas.drawLine(cx + 120f * f, cy - 80f * f, cx + 380f * f, cy - 280f * f, stroke)
        val mallow = RectF(cx + 160f * f, cy - 150f * f, cx + 240f * f, cy - 90f * f)
        canvas.drawRoundRect(mallow, 14f * f, 14f * f, stroke)

        // Floating Sparks
        drawSparkle(canvas, cx - 180f * f, cy - 240f * f, 22f * f, stroke)
        drawSparkle(canvas, cx + 140f * f, cy - 260f * f, 26f * f, stroke)
    }
}
