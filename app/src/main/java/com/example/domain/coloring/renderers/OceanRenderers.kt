package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawBubble
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundWaves

object OceanRenderers {

    fun drawDolphin(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Arched Leaping Dolphin Body
        val dolphin = Path().apply {
            moveTo(cx + 280f * f, cy - 40f * f) // beak tip
            cubicTo(cx + 200f * f, cy - 180f * f, cx, cy - 240f * f, cx - 180f * f, cy - 140f * f)
            cubicTo(cx - 300f * f, cy - 80f * f, cx - 380f * f, cy + 80f * f, cx - 400f * f, cy + 180f * f) // tail base
            lineTo(cx - 360f * f, cy + 180f * f)
            cubicTo(cx - 320f * f, cy + 60f * f, cx - 220f * f, cy + 40f * f, cx - 100f * f, cy - 20f * f)
            cubicTo(cx + 40f * f, cy - 60f * f, cx + 180f * f, cy + 20f * f, cx + 280f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(dolphin, stroke)

        // Dorsal Fin
        val dorsal = Path().apply {
            moveTo(cx - 60f * f, cy - 220f * f)
            cubicTo(cx - 40f * f, cy - 320f * f, cx + 20f * f, cy - 300f * f, cx + 40f * f, cy - 200f * f)
            close()
        }
        canvas.drawPath(dorsal, stroke)

        // Pectoral Flipper
        val flipper = Path().apply {
            moveTo(cx + 60f * f, cy - 40f * f)
            cubicTo(cx + 40f * f, cy + 80f * f, cx + 120f * f, cy + 80f * f, cx + 140f * f, cy - 20f * f)
            close()
        }
        canvas.drawPath(flipper, stroke)

        // Tail Flukes
        val tail = Path().apply {
            moveTo(cx - 400f * f, cy + 180f * f)
            cubicTo(cx - 440f * f, cy + 120f * f, cx - 480f * f, cy + 160f * f, cx - 440f * f, cy + 240f * f)
            cubicTo(cx - 410f * f, cy + 220f * f, cx - 380f * f, cy + 220f * f, cx - 360f * f, cy + 240f * f)
            cubicTo(cx - 330f * f, cy + 160f * f, cx - 370f * f, cy + 120f * f, cx - 400f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(tail, stroke)

        // Cute Eye & Smile
        drawCuteEye(canvas, cx + 180f * f, cy - 90f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx + 210f * f, cy - 50f * f)
            quadTo(cx + 240f * f, cy - 40f * f, cx + 270f * f, cy - 55f * f)
        }
        canvas.drawPath(smile, stroke)

        // Ocean Waves below
        drawGroundWaves(canvas, s, stroke)
        // Water drops splash
        canvas.drawCircle(cx - 240f * f, cy + 280f * f, 14f * f, stroke)
        canvas.drawCircle(cx - 180f * f, cy + 320f * f, 18f * f, stroke)
    }

    fun drawSeaTurtle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Front Flippers (Long graceful swimming flippers)
        val flipperL = Path().apply {
            moveTo(cx - 140f * f, cy - 100f * f)
            cubicTo(cx - 320f * f, cy - 220f * f, cx - 440f * f, cy - 160f * f, cx - 400f * f, cy - 60f * f)
            cubicTo(cx - 340f * f, cy + 20f * f, cx - 220f * f, cy + 20f * f, cx - 160f * f, cy)
            close()
        }
        canvas.drawPath(flipperL, stroke)

        val flipperR = Path().apply {
            moveTo(cx + 140f * f, cy - 100f * f)
            cubicTo(cx + 320f * f, cy - 220f * f, cx + 440f * f, cy - 160f * f, cx + 400f * f, cy - 60f * f)
            cubicTo(cx + 340f * f, cy + 20f * f, cx + 220f * f, cy + 20f * f, cx + 160f * f, cy)
            close()
        }
        canvas.drawPath(flipperR, stroke)

        // Turtle Shell (Big Oval)
        val shell = RectF(cx - 220f * f, cy - 160f * f, cx + 220f * f, cy + 240f * f)
        canvas.drawOval(shell, stroke)

        // Hexagonal Shell Patterns for coloring
        canvas.drawCircle(cx, cy + 20f * f, 70f * f, stroke)
        for (i in 0 until 5) {
            val angle = i * (Math.PI * 2 / 5) - Math.PI / 2
            val px = (cx + Math.cos(angle) * 120f * f).toFloat()
            val py = (cy + 20f * f + Math.sin(angle) * 110f * f).toFloat()
            canvas.drawCircle(px, py, 45f * f, fine)
        }

        // Cute Turtle Head peeking at top
        val head = Path().apply {
            moveTo(cx - 60f * f, cy - 150f * f)
            cubicTo(cx - 70f * f, cy - 280f * f, cx + 70f * f, cy - 280f * f, cx + 60f * f, cy - 150f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Eyes on head
        drawCuteEye(canvas, cx - 35f * f, cy - 220f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, cy - 220f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 20f * f, cy - 180f * f)
            quadTo(cx, cy - 165f * f, cx + 20f * f, cy - 180f * f)
        }
        canvas.drawPath(smile, stroke)

        // Rear Flippers & Little Tail
        canvas.drawRoundRect(RectF(cx - 180f * f, cy + 200f * f, cx - 90f * f, cy + 320f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 90f * f, cy + 200f * f, cx + 180f * f, cy + 320f * f), 30f * f, 30f * f, stroke)

        drawBubble(canvas, cx - 300f * f, cy - 260f * f, 24f * f, stroke, fine)
        drawBubble(canvas, cx + 280f * f, cy - 280f * f, 32f * f, stroke, fine)
    }

    fun drawOctopus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Bulbous Round Octopus Head
        val head = Path().apply {
            moveTo(cx - 160f * f, cy + 80f * f)
            cubicTo(cx - 240f * f, cy - 100f * f, cx - 180f * f, cy - 260f * f, cx, cy - 260f * f)
            cubicTo(cx + 180f * f, cy - 260f * f, cx + 240f * f, cy - 100f * f, cx + 160f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Big Cute Eyes
        drawCuteEye(canvas, cx - 65f * f, cy - 60f * f, 32f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 65f * f, cy - 60f * f, 32f * f, stroke, eyeFill, eyeHighlight)

        // Rosy Cheeks
        canvas.drawCircle(cx - 105f * f, cy + 5f * f, 18f * f, fine)
        canvas.drawCircle(cx + 105f * f, cy + 5f * f, 18f * f, fine)

        // Sweet O-shaped Mouth / Smile
        canvas.drawCircle(cx, cy + 15f * f, 18f * f, stroke)

        // 8 Curling Tentacles
        val tentacleX = listOf(-180f, -120f, -60f, -20f, 20f, 60f, 120f, 180f)
        for ((idx, tx) in tentacleX.withIndex()) {
            val tentacle = Path().apply {
                val startX = cx + tx * f
                val endX = cx + (tx * 1.8f) * f
                val curlX = cx + (tx * 2.2f + (if (idx % 2 == 0) -30f else 30f)) * f
                moveTo(startX, cy + 80f * f)
                cubicTo(startX, cy + 220f * f, endX, cy + 340f * f, curlX, cy + 440f * f)
                cubicTo(curlX + 30f * f, cy + 440f * f, endX + 40f * f, cy + 320f * f, startX + 35f * f, cy + 80f * f)
            }
            canvas.drawPath(tentacle, stroke)
            // Suction cup circles
            canvas.drawCircle(cx + (tx * 1.5f) * f, cy + 300f * f, 12f * f, fine)
            canvas.drawCircle(cx + (tx * 1.7f) * f, cy + 380f * f, 10f * f, fine)
        }

        drawBubble(canvas, cx - 280f * f, cy - 180f * f, 26f * f, stroke, fine)
        drawBubble(canvas, cx + 280f * f, cy - 160f * f, 34f * f, stroke, fine)
    }

    fun drawWhale(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 480f * f
        val cy = 480f * f

        // Giant Curving Whale Body
        val whale = Path().apply {
            moveTo(cx + 340f * f, cy + 40f * f) // mouth corner
            cubicTo(cx + 360f * f, cy - 180f * f, cx + 180f * f, cy - 240f * f, cx, cy - 220f * f)
            cubicTo(cx - 240f * f, cy - 200f * f, cx - 360f * f, cy - 80f * f, cx - 420f * f, cy - 140f * f) // tail up
            // Tail Fluke
            lineTo(cx - 460f * f, cy - 200f * f)
            lineTo(cx - 430f * f, cy - 130f * f)
            lineTo(cx - 460f * f, cy - 80f * f)
            lineTo(cx - 390f * f, cy - 80f * f)
            cubicTo(cx - 320f * f, cy + 120f * f, cx - 140f * f, cy + 220f * f, cx + 120f * f, cy + 220f * f)
            cubicTo(cx + 280f * f, cy + 220f * f, cx + 380f * f, cy + 140f * f, cx + 340f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(whale, stroke)

        // Whale Belly Ridges (White lower jaw lines)
        val belly = Path().apply {
            moveTo(cx + 280f * f, cy + 100f * f)
            cubicTo(cx + 180f * f, cy + 180f * f, cx, cy + 180f * f, cx - 180f * f, cy + 120f * f)
        }
        canvas.drawPath(belly, stroke)
        canvas.drawLine(cx + 200f * f, cy + 125f * f, cx + 220f * f, cy + 180f * f, fine)
        canvas.drawLine(cx + 100f * f, cy + 140f * f, cx + 110f * f, cy + 200f * f, fine)
        canvas.drawLine(cx, cy + 140f * f, cx, cy + 195f * f, fine)

        // Water Spout Burst from Blowhole
        val spout = Path().apply {
            moveTo(cx + 60f * f, cy - 220f * f)
            cubicTo(cx + 40f * f, cy - 340f * f, cx - 40f * f, cy - 420f * f, cx - 100f * f, cy - 380f * f)
            moveTo(cx + 60f * f, cy - 220f * f)
            cubicTo(cx + 80f * f, cy - 360f * f, cx + 160f * f, cy - 420f * f, cx + 200f * f, cy - 380f * f)
        }
        canvas.drawPath(spout, stroke)
        canvas.drawCircle(cx - 100f * f, cy - 380f * f, 18f * f, stroke)
        canvas.drawCircle(cx + 200f * f, cy - 380f * f, 18f * f, stroke)

        // Cute Eye & Smile
        drawCuteEye(canvas, cx + 240f * f, cy - 60f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx + 260f * f, cy + 30f * f)
            quadTo(cx + 300f * f, cy + 50f * f, cx + 330f * f, cy + 30f * f)
        }
        canvas.drawPath(smile, stroke)

        // Pectoral Flipper
        val flipper = Path().apply {
            moveTo(cx + 80f * f, cy + 100f * f)
            cubicTo(cx + 60f * f, cy + 180f * f, cx - 40f * f, cy + 240f * f, cx - 80f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(flipper, stroke)

        drawGroundWaves(canvas, s, stroke)
    }

    fun drawClownfish(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Fish Oval Body & Tail Fin
        val fish = Path().apply {
            moveTo(cx + 280f * f, cy) // lips
            cubicTo(cx + 220f * f, cy - 180f * f, cx - 80f * f, cy - 180f * f, cx - 220f * f, cy - 60f * f)
            // Tail fin
            lineTo(cx - 360f * f, cy - 140f * f)
            cubicTo(cx - 320f * f, cy, cx - 320f * f, cy, cx - 360f * f, cy + 140f * f)
            lineTo(cx - 220f * f, cy + 60f * f)
            cubicTo(cx - 80f * f, cy + 180f * f, cx + 220f * f, cy + 180f * f, cx + 280f * f, cy)
            close()
        }
        canvas.drawPath(fish, stroke)

        // Bold Vertical Stripes (Classic Clownfish Bands)
        val band1 = Path().apply {
            moveTo(cx + 120f * f, cy - 150f * f)
            cubicTo(cx + 160f * f, cy, cx + 160f * f, cy, cx + 120f * f, cy + 150f * f)
            lineTo(cx + 60f * f, cy + 160f * f)
            cubicTo(cx + 100f * f, cy, cx + 100f * f, cy, cx + 60f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(band1, stroke)

        val band2 = Path().apply {
            moveTo(cx - 80f * f, cy - 140f * f)
            cubicTo(cx - 50f * f, cy, cx - 50f * f, cy, cx - 80f * f, cy + 140f * f)
            lineTo(cx - 130f * f, cy + 120f * f)
            cubicTo(cx - 100f * f, cy, cx - 100f * f, cy, cx - 130f * f, cy - 120f * f)
            close()
        }
        canvas.drawPath(band2, stroke)

        // Dorsal & Pectoral Fins
        val dorsal = Path().apply {
            moveTo(cx + 40f * f, cy - 160f * f)
            cubicTo(cx, cy - 240f * f, cx - 120f * f, cy - 240f * f, cx - 160f * f, cy - 120f * f)
            close()
        }
        canvas.drawPath(dorsal, stroke)

        val pectoral = Path().apply {
            moveTo(cx + 80f * f, cy + 20f * f)
            cubicTo(cx + 20f * f, cy + 80f * f, cx + 40f * f, cy + 140f * f, cx + 100f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(pectoral, stroke)

        // Big Anime Fish Eye
        drawCuteEye(canvas, cx + 190f * f, cy - 30f * f, 28f * f, stroke, eyeFill, eyeHighlight)

        // Fish Lips
        val lips = Path().apply {
            moveTo(cx + 270f * f, cy - 20f * f)
            quadTo(cx + 310f * f, cy, cx + 270f * f, cy + 20f * f)
        }
        canvas.drawPath(lips, stroke)

        drawBubble(canvas, cx + 340f * f, cy - 60f * f, 22f * f, stroke, fine)
        drawBubble(canvas, cx + 380f * f, cy - 140f * f, 32f * f, stroke, fine)
    }

    fun drawSeahorse(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Coronet Crown on Head
        val crown = Path().apply {
            moveTo(cx - 30f * f, cy - 260f * f)
            lineTo(cx - 50f * f, cy - 330f * f)
            lineTo(cx - 20f * f, cy - 300f * f)
            lineTo(cx, cy - 340f * f)
            lineTo(cx + 20f * f, cy - 300f * f)
            lineTo(cx + 40f * f, cy - 330f * f)
            lineTo(cx + 20f * f, cy - 260f * f)
            close()
        }
        canvas.drawPath(crown, stroke)

        // Seahorse Head & Tubular Snout
        val head = Path().apply {
            moveTo(cx - 40f * f, cy - 260f * f)
            cubicTo(cx - 100f * f, cy - 220f * f, cx - 100f * f, cy - 140f * f, cx - 40f * f, cy - 100f * f)
            lineTo(cx - 180f * f, cy - 120f * f) // snout
            lineTo(cx - 180f * f, cy - 80f * f)
            lineTo(cx - 20f * f, cy - 70f * f)
            cubicTo(cx + 40f * f, cy - 70f * f, cx + 60f * f, cy - 180f * f, cx + 20f * f, cy - 260f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Cute Eye
        drawCuteEye(canvas, cx - 20f * f, cy - 160f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Arched Neck, Puffy Belly, and Spiraled Tail
        val body = Path().apply {
            moveTo(cx - 20f * f, cy - 70f * f)
            cubicTo(cx - 120f * f, cy + 40f * f, cx - 140f * f, cy + 200f * f, cx - 20f * f, cy + 260f * f)
            // Curled Tail Spiral
            cubicTo(cx + 60f * f, cy + 300f * f, cx + 120f * f, cy + 380f * f, cx + 60f * f, cy + 440f * f)
            cubicTo(cx, cy + 480f * f, cx - 60f * f, cy + 440f * f, cx - 20f * f, cy + 400f * f)
            cubicTo(cx + 20f * f, cy + 380f * f, cx + 20f * f, cy + 360f * f, cx, cy + 360f * f)
            // Back up
            cubicTo(cx + 40f * f, cy + 220f * f, cx + 80f * f, cy + 80f * f, cx + 20f * f, cy - 70f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Dorsal Fin on back
        val dorsal = Path().apply {
            moveTo(cx + 50f * f, cy + 40f * f)
            cubicTo(cx + 140f * f, cy + 20f * f, cx + 140f * f, cy + 140f * f, cx + 40f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(dorsal, stroke)

        // Armored Segments on Belly
        for (y in 20..200 step 45) {
            canvas.drawLine((cx - 80f + y * 0.3f) * f, (cy + y) * f, (cx + 30f) * f, (cy + y) * f, fine)
        }

        drawBubble(canvas, cx + 240f * f, cy - 200f * f, 24f * f, stroke, fine)
    }

    fun drawStarfish(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Plump 5-Armed Starfish
        val starfish = Path()
        val arms = 5
        val outerR = 260f * f
        val innerR = 120f * f
        for (i in 0 until (arms * 2)) {
            val angle = i * Math.PI / arms - Math.PI / 2
            val r = if (i % 2 == 0) outerR else innerR
            val px = (cx + Math.cos(angle) * r).toFloat()
            val py = (cy + Math.sin(angle) * r).toFloat()
            if (i == 0) starfish.moveTo(px, py) else starfish.lineTo(px, py)
        }
        starfish.close()
        canvas.drawPath(starfish, stroke)

        // Cute Big Eyes
        drawCuteEye(canvas, cx - 60f * f, cy - 30f * f, 30f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy - 30f * f, 30f * f, stroke, eyeFill, eyeHighlight)

        // Rosy Cheeks
        canvas.drawCircle(cx - 100f * f, cy + 25f * f, 20f * f, fine)
        canvas.drawCircle(cx + 100f * f, cy + 25f * f, 20f * f, fine)

        // Wide Happy Smile
        val smile = Path().apply {
            moveTo(cx - 40f * f, cy + 35f * f)
            quadTo(cx, cy + 85f * f, cx + 40f * f, cy + 35f * f)
        }
        canvas.drawPath(smile, stroke)

        // Decorative Suction Dots along arms
        for (i in 0 until arms) {
            val angle = i * Math.PI * 2 / arms - Math.PI / 2
            val px1 = (cx + Math.cos(angle) * 180f * f).toFloat()
            val py1 = (cy + Math.sin(angle) * 180f * f).toFloat()
            canvas.drawCircle(px1, py1, 14f * f, fine)
            val px2 = (cx + Math.cos(angle) * 220f * f).toFloat()
            val py2 = (cy + Math.sin(angle) * 220f * f).toFloat()
            canvas.drawCircle(px2, py2, 10f * f, fine)
        }

        // Ocean Floor Sand & Shells
        drawGroundWaves(canvas, s, stroke)
        canvas.drawCircle(180f * f, 840f * f, 26f * f, stroke)
        canvas.drawCircle(840f * f, 840f * f, 30f * f, stroke)
    }

    fun drawJellyfish(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 360f * f

        // Bell-shaped Mushroom Umbrella Dome
        val bell = Path().apply {
            moveTo(cx - 240f * f, cy + 60f * f)
            cubicTo(cx - 260f * f, cy - 180f * f, cx - 180f * f, cy - 240f * f, cx, cy - 240f * f)
            cubicTo(cx + 180f * f, cy - 240f * f, cx + 260f * f, cy - 180f * f, cx + 240f * f, cy + 60f * f)
            // Scalloped Ruffled Rim
            var x = cx + 240f * f
            while (x > cx - 240f * f) {
                quadTo(x - 30f * f, cy + 90f * f, x - 60f * f, cy + 60f * f)
                x -= 60f * f
            }
            close()
        }
        canvas.drawPath(bell, stroke)

        // Cute Face on Bell
        drawCuteEye(canvas, cx - 70f * f, cy - 40f * f, 30f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 40f * f, 30f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 35f * f, cy + 20f * f)
            quadTo(cx, cy + 60f * f, cx + 35f * f, cy + 20f * f)
        }
        canvas.drawPath(smile, stroke)

        // Undulating Flowing Tentacles
        for (i in -3..3) {
            val tx = cx + (i * 55f) * f
            val tentacle = Path().apply {
                moveTo(tx, cy + 75f * f)
                cubicTo(tx - 40f * f, cy + 200f * f, tx + 40f * f, cy + 340f * f, tx - 20f * f, cy + 500f * f)
            }
            canvas.drawPath(tentacle, stroke)
        }

        drawBubble(canvas, cx - 280f * f, cy - 160f * f, 25f * f, stroke, fine)
        drawBubble(canvas, cx + 300f * f, cy - 120f * f, 35f * f, stroke, fine)
    }

    fun drawCrab(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Round Oval Crab Shell (Carapace)
        val shell = RectF(cx - 220f * f, cy - 120f * f, cx + 220f * f, cy + 120f * f)
        canvas.drawOval(shell, stroke)

        // Stalk Eyes popping up from top
        canvas.drawLine(cx - 60f * f, cy - 110f * f, cx - 60f * f, cy - 180f * f, stroke)
        drawCuteEye(canvas, cx - 60f * f, cy - 200f * f, 32f * f, stroke, eyeFill, eyeHighlight)

        canvas.drawLine(cx + 60f * f, cy - 110f * f, cx + 60f * f, cy - 180f * f, stroke)
        drawCuteEye(canvas, cx + 60f * f, cy - 200f * f, 32f * f, stroke, eyeFill, eyeHighlight)

        // Cheerful Smile
        val smile = Path().apply {
            moveTo(cx - 50f * f, cy + 20f * f)
            quadTo(cx, cy + 70f * f, cx + 50f * f, cy + 20f * f)
        }
        canvas.drawPath(smile, stroke)

        // Big Snapping Pincers (Claws)
        val armL = Path().apply {
            moveTo(cx - 190f * f, cy - 40f * f)
            cubicTo(cx - 280f * f, cy - 140f * f, cx - 340f * f, cy - 100f * f, cx - 320f * f, cy - 40f * f)
        }
        canvas.drawPath(armL, stroke)
        // Left Claw Pincers
        val clawL = Path().apply {
            moveTo(cx - 320f * f, cy - 40f * f)
            cubicTo(cx - 440f * f, cy - 140f * f, cx - 380f * f, cy - 220f * f, cx - 300f * f, cy - 180f * f)
            lineTo(cx - 320f * f, cy - 120f * f)
            lineTo(cx - 240f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(clawL, stroke)

        val armR = Path().apply {
            moveTo(cx + 190f * f, cy - 40f * f)
            cubicTo(cx + 280f * f, cy - 140f * f, cx + 340f * f, cy - 100f * f, cx + 320f * f, cy - 40f * f)
        }
        canvas.drawPath(armR, stroke)
        // Right Claw Pincers
        val clawR = Path().apply {
            moveTo(cx + 320f * f, cy - 40f * f)
            cubicTo(cx + 440f * f, cy - 140f * f, cx + 380f * f, cy - 220f * f, cx + 300f * f, cy - 180f * f)
            lineTo(cx + 320f * f, cy - 120f * f)
            lineTo(cx + 240f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(clawR, stroke)

        // 6 Walking Legs
        for (side in listOf(-1, 1)) {
            for (i in 0..2) {
                val leg = Path().apply {
                    val startX = cx + side * (160f + i * 20f) * f
                    val startY = cy + (40f + i * 25f) * f
                    moveTo(startX, startY)
                    cubicTo(startX + side * 60f * f, startY + 40f * f, startX + side * 80f * f, startY + 120f * f, startX + side * 60f * f, startY + 180f * f)
                }
                canvas.drawPath(leg, stroke)
            }
        }

        drawGroundWaves(canvas, s, stroke)
    }

    fun drawShark(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 480f * f
        val cy = 480f * f

        // Great White Shark Torpedo Body
        val shark = Path().apply {
            moveTo(cx + 360f * f, cy) // snout
            cubicTo(cx + 260f * f, cy - 140f * f, cx + 60f * f, cy - 180f * f, cx - 140f * f, cy - 120f * f)
            cubicTo(cx - 280f * f, cy - 80f * f, cx - 380f * f, cy - 40f * f, cx - 420f * f, cy - 120f * f) // tail top
            lineTo(cx - 400f * f, cy) // tail notch
            lineTo(cx - 440f * f, cy + 100f * f) // tail bottom
            cubicTo(cx - 360f * f, cy + 60f * f, cx - 220f * f, cy + 100f * f, cx - 80f * f, cy + 140f * f)
            cubicTo(cx + 80f * f, cy + 180f * f, cx + 260f * f, cy + 140f * f, cx + 360f * f, cy)
            close()
        }
        canvas.drawPath(shark, stroke)

        // Iconic Triangular Dorsal Fin
        val dorsal = Path().apply {
            moveTo(cx - 40f * f, cy - 160f * f)
            lineTo(cx + 20f * f, cy - 320f * f)
            cubicTo(cx + 30f * f, cy - 240f * f, cx + 80f * f, cy - 180f * f, cx + 120f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(dorsal, stroke)

        // Pectoral Fin
        val pectoral = Path().apply {
            moveTo(cx + 120f * f, cy + 60f * f)
            cubicTo(cx + 80f * f, cy + 220f * f, cx - 20f * f, cy + 220f * f, cx, cy + 120f * f)
            close()
        }
        canvas.drawPath(pectoral, stroke)

        // Cute Friendly Eye
        drawCuteEye(canvas, cx + 240f * f, cy - 40f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Friendly Smile with cute little teeth
        val mouth = Path().apply {
            moveTo(cx + 220f * f, cy + 40f * f)
            quadTo(cx + 280f * f, cy + 80f * f, cx + 320f * f, cy + 30f * f)
        }
        canvas.drawPath(mouth, stroke)
        // Teeth
        for (tx in listOf(250f, 270f, 290f)) {
            val tooth = Path().apply {
                moveTo((cx + tx) * f, (cy + 45f) * f)
                lineTo((cx + tx + 8f) * f, (cy + 60f) * f)
                lineTo((cx + tx + 16f) * f, (cy + 45f) * f)
                close()
            }
            canvas.drawPath(tooth, stroke)
        }

        // Gills
        for (gx in listOf(150f, 170f, 190f)) {
            canvas.drawLine((cx + gx) * f, (cy - 10f) * f, (cx + gx - 10f) * f, (cy + 40f) * f, fine)
        }

        drawGroundWaves(canvas, s, stroke)
    }
}
