package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object FantasyRenderers {

    fun drawUnicorn(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 480f * f
        val cy = 460f * f

        // Spiraled Magical Horn
        val horn = Path().apply {
            moveTo(cx + 40f * f, cy - 160f * f)
            lineTo(cx + 120f * f, cy - 360f * f)
            lineTo(cx + 90f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(horn, stroke)
        // Horn spirals
        canvas.drawLine((cx + 55f) * f, (cy - 200f) * f, (cx + 100f) * f, (cy - 220f) * f, fine)
        canvas.drawLine((cx + 70f) * f, (cy - 250f) * f, (cx + 110f) * f, (cy - 270f) * f, fine)

        // Pointy Ears
        val ear = Path().apply {
            moveTo(cx - 20f * f, cy - 160f * f)
            lineTo(cx - 50f * f, cy - 250f * f)
            lineTo(cx + 20f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(ear, stroke)

        // Flowing Unicorn Mane (Wavy luscious locks)
        val mane = Path().apply {
            moveTo(cx - 50f * f, cy - 140f * f)
            cubicTo(cx - 180f * f, cy - 120f * f, cx - 220f * f, cy + 40f * f, cx - 180f * f, cy + 120f * f)
            cubicTo(cx - 240f * f, cy + 180f * f, cx - 200f * f, cy + 300f * f, cx - 120f * f, cy + 320f * f)
            cubicTo(cx - 60f * f, cy + 320f * f, cx - 40f * f, cy + 220f * f, cx - 40f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(mane, stroke)

        // Unicorn Head
        val head = Path().apply {
            moveTo(cx - 20f * f, cy - 160f * f)
            cubicTo(cx + 70f * f, cy - 140f * f, cx + 160f * f, cy - 80f * f, cx + 220f * f, cy - 20f * f)
            cubicTo(cx + 260f * f, cy + 20f * f, cx + 240f * f, cy + 90f * f, cx + 180f * f, cy + 90f * f)
            lineTo(cx + 90f * f, cy + 80f * f)
            cubicTo(cx + 40f * f, cy + 140f * f, cx - 20f * f, cy + 200f * f, cx - 40f * f, cy + 260f * f)
            lineTo(cx - 120f * f, cy + 240f * f)
            cubicTo(cx - 100f * f, cy + 100f * f, cx - 80f * f, cy - 40f * f, cx - 20f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Cute Friendly Eye with Lashes
        drawCuteEye(canvas, cx + 80f * f, cy - 20f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawLine((cx + 75f) * f, (cy - 48f) * f, (cx + 65f) * f, (cy - 65f) * f, stroke)
        canvas.drawLine((cx + 95f) * f, (cy - 45f) * f, (cx + 95f) * f, (cy - 65f) * f, stroke)

        // Cute Nostril & Smile
        canvas.drawCircle(cx + 200f * f, cy + 40f * f, 8f * f, eyeFill)
        val smile = Path().apply {
            moveTo(cx + 170f * f, cy + 65f * f)
            quadTo(cx + 195f * f, cy + 75f * f, cx + 210f * f, cy + 60f * f)
        }
        canvas.drawPath(smile, stroke)

        // Sparkles and stars around unicorn
        drawSparkle(canvas, cx + 200f * f, cy - 260f * f, 30f * f, stroke)
        drawStar(canvas, cx - 240f * f, cy - 140f * f, 24f * f, stroke)
        drawStar(canvas, cx + 280f * f, cy + 180f * f, 22f * f, stroke)
    }

    fun drawDragon(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Tiny Horns on head
        val hornL = Path().apply {
            moveTo(cx - 80f * f, cy - 180f * f)
            lineTo(cx - 120f * f, cy - 260f * f)
            lineTo(cx - 40f * f, cy - 190f * f)
            close()
        }
        canvas.drawPath(hornL, stroke)

        val hornR = Path().apply {
            moveTo(cx + 40f * f, cy - 190f * f)
            lineTo(cx + 120f * f, cy - 260f * f)
            lineTo(cx + 80f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(hornR, stroke)

        // Dragon Round Head
        val head = Path().apply {
            moveTo(cx - 80f * f, cy - 180f * f)
            cubicTo(cx - 160f * f, cy - 140f * f, cx - 180f * f, cy - 20f * f, cx - 120f * f, cy + 40f * f)
            cubicTo(cx - 40f * f, cy + 80f * f, cx + 40f * f, cy + 80f * f, cx + 120f * f, cy + 40f * f)
            cubicTo(cx + 180f * f, cy - 20f * f, cx + 160f * f, cy - 140f * f, cx + 80f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Cute Big Eyes
        drawCuteEye(canvas, cx - 60f * f, cy - 60f * f, 30f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy - 60f * f, 30f * f, stroke, eyeFill, eyeHighlight)

        // Dragon Nostrils with little puff of smoke
        canvas.drawCircle(cx - 20f * f, cy + 10f * f, 9f * f, eyeFill)
        canvas.drawCircle(cx + 20f * f, cy + 10f * f, 9f * f, eyeFill)
        canvas.drawCircle(cx, cy - 20f * f, 12f * f, fine) // smoke puff

        // Friendly Smile
        val mouth = Path().apply {
            moveTo(cx - 40f * f, cy + 40f * f)
            quadTo(cx, cy + 70f * f, cx + 40f * f, cy + 40f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Dragon Wings (Bat-like with scalloped bottom)
        val wingL = Path().apply {
            moveTo(cx - 100f * f, cy + 80f * f)
            cubicTo(cx - 220f * f, cy - 20f * f, cx - 340f * f, cy - 40f * f, cx - 380f * f, cy - 10f * f)
            lineTo(cx - 320f * f, cy + 120f * f)
            lineTo(cx - 240f * f, cy + 100f * f)
            lineTo(cx - 160f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(wingL, stroke)

        val wingR = Path().apply {
            moveTo(cx + 100f * f, cy + 80f * f)
            cubicTo(cx + 220f * f, cy - 20f * f, cx + 340f * f, cy - 40f * f, cx + 380f * f, cy - 10f * f)
            lineTo(cx + 320f * f, cy + 120f * f)
            lineTo(cx + 240f * f, cy + 100f * f)
            lineTo(cx + 160f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(wingR, stroke)

        // Round Pear Body & Segmented Belly
        val body = Path().apply {
            moveTo(cx - 100f * f, cy + 80f * f)
            cubicTo(cx - 180f * f, cy + 200f * f, cx - 180f * f, cy + 340f * f, cx - 100f * f, cy + 400f * f)
            lineTo(cx + 100f * f, cy + 400f * f)
            cubicTo(cx + 180f * f, cy + 340f * f, cx + 180f * f, cy + 200f * f, cx + 100f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Segmented Belly lines
        val belly = RectF(cx - 80f * f, cy + 160f * f, cx + 80f * f, cy + 380f * f)
        canvas.drawRoundRect(belly, 40f * f, 40f * f, stroke)
        canvas.drawLine(cx - 60f * f, cy + 230f * f, cx + 60f * f, cy + 230f * f, fine)
        canvas.drawLine(cx - 70f * f, cy + 290f * f, cx + 70f * f, cy + 290f * f, fine)
        canvas.drawLine(cx - 60f * f, cy + 340f * f, cx + 60f * f, cy + 340f * f, fine)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawMagicWand(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f

        // Wand Shaft (Angled diagonally)
        val wand = Path().apply {
            moveTo(cx - 240f * f, cy + 340f * f)
            lineTo(cx + 140f * f, cy - 80f * f)
            lineTo(cx + 170f * f, cy - 60f * f)
            lineTo(cx - 210f * f, cy + 360f * f)
            close()
        }
        canvas.drawPath(wand, stroke)

        // Large 5-Pointed Star Topper on Wand
        drawStar(canvas, cx + 180f * f, cy - 100f * f, 120f * f, stroke)

        // Cute face on star topper
        drawCuteEye(canvas, cx + 155f * f, cy - 110f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 205f * f, cy - 110f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx + 165f * f, cy - 80f * f)
            quadTo(cx + 180f * f, cy - 65f * f, cx + 195f * f, cy - 80f * f)
        }
        canvas.drawPath(smile, stroke)

        // Swirling Magic Trail Ribbon
        val ribbon = Path().apply {
            moveTo(cx + 180f * f, cy - 100f * f)
            cubicTo(cx + 340f * f, cy - 40f * f, cx + 320f * f, cy + 180f * f, cx + 120f * f, cy + 180f * f)
            cubicTo(cx - 40f * f, cy + 180f * f, cx - 80f * f, cy + 80f * f, cx + 40f * f, cy + 20f * f)
        }
        canvas.drawPath(ribbon, fine)

        // Sparkles and starbursts bursting from wand
        drawSparkle(canvas, cx + 320f * f, cy - 240f * f, 36f * f, stroke)
        drawSparkle(canvas, cx + 80f * f, cy - 260f * f, 28f * f, stroke)
        drawSparkle(canvas, cx + 360f * f, cy - 60f * f, 24f * f, stroke)
        drawStar(canvas, cx - 140f * f, cy - 80f * f, 26f * f, stroke)
        drawStar(canvas, cx - 80f * f, cy + 280f * f, 22f * f, stroke)
    }

    fun drawFairy(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f

        // Fluttery Gossamer Butterfly Wings
        val wingTL = Path().apply {
            moveTo(cx - 40f * f, cy)
            cubicTo(cx - 160f * f, cy - 160f * f, cx - 340f * f, cy - 180f * f, cx - 380f * f, cy - 80f * f)
            cubicTo(cx - 400f * f, cy + 20f * f, cx - 260f * f, cy + 120f * f, cx - 40f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(wingTL, stroke)

        val wingTR = Path().apply {
            moveTo(cx + 40f * f, cy)
            cubicTo(cx + 160f * f, cy - 160f * f, cx + 340f * f, cy - 180f * f, cx + 380f * f, cy - 80f * f)
            cubicTo(cx + 400f * f, cy + 20f * f, cx + 260f * f, cy + 120f * f, cx + 40f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(wingTR, stroke)

        // Fairy Head & Hair Bun
        canvas.drawCircle(cx, cy - 200f * f, 40f * f, stroke) // hair bun
        canvas.drawCircle(cx, cy - 120f * f, 70f * f, stroke) // face

        // Hair Fringe
        val hair = Path().apply {
            moveTo(cx - 65f * f, cy - 140f * f)
            quadTo(cx, cy - 90f * f, cx + 65f * f, cy - 140f * f)
        }
        canvas.drawPath(hair, fine)

        // Cute Face
        drawCuteEye(canvas, cx - 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 15f * f, cy - 90f * f)
            quadTo(cx, cy - 75f * f, cx + 15f * f, cy - 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Petal Dress
        val dress = Path().apply {
            moveTo(cx - 30f * f, cy - 50f * f)
            lineTo(cx - 90f * f, cy + 180f * f)
            quadTo(cx - 45f * f, cy + 220f * f, cx, cy + 180f * f)
            quadTo(cx + 45f * f, cy + 220f * f, cx + 90f * f, cy + 180f * f)
            lineTo(cx + 30f * f, cy - 50f * f)
            close()
        }
        canvas.drawPath(dress, stroke)

        // Legs
        canvas.drawLine(cx - 25f * f, cy + 190f * f, cx - 35f * f, cy + 360f * f, stroke)
        canvas.drawLine(cx + 25f * f, cy + 190f * f, cx + 35f * f, cy + 360f * f, stroke)

        // Little Star Wand in hand
        canvas.drawLine(cx + 60f * f, cy + 20f * f, cx + 160f * f, cy - 40f * f, stroke)
        drawStar(canvas, cx + 175f * f, cy - 55f * f, 24f * f, stroke)

        drawSparkle(canvas, cx - 200f * f, cy + 260f * f, 24f * f, stroke)
    }

    fun drawMermaid(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f

        // Long Flowing Hair
        val hair = Path().apply {
            moveTo(cx - 70f * f, cy - 160f * f)
            cubicTo(cx - 220f * f, cy - 80f * f, cx - 240f * f, cy + 180f * f, cx - 160f * f, cy + 320f * f)
            cubicTo(cx - 100f * f, cy + 340f * f, cx - 80f * f, cy + 240f * f, cx - 100f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(hair, stroke)

        // Mermaid Head
        canvas.drawCircle(cx, cy - 140f * f, 75f * f, stroke)

        // Starfish hair accessory
        drawStar(canvas, cx - 60f * f, cy - 180f * f, 22f * f, stroke)

        // Cute Face
        drawCuteEye(canvas, cx - 28f * f, cy - 140f * f, 15f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 28f * f, cy - 140f * f, 15f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 16f * f, cy - 110f * f)
            quadTo(cx, cy - 95f * f, cx + 16f * f, cy - 110f * f)
        }
        canvas.drawPath(smile, stroke)

        // Seashell Top
        canvas.drawCircle(cx - 25f * f, cy - 20f * f, 22f * f, stroke)
        canvas.drawCircle(cx + 25f * f, cy - 20f * f, 22f * f, stroke)

        // Curving Mermaid Fish Tail
        val tail = Path().apply {
            moveTo(cx - 45f * f, cy + 20f * f)
            cubicTo(cx - 60f * f, cy + 140f * f, cx + 80f * f, cy + 220f * f, cx + 60f * f, cy + 340f * f)
            lineTo(cx - 10f * f, cy + 340f * f)
            cubicTo(cx, cy + 220f * f, cx - 90f * f, cy + 140f * f, cx - 45f * f, cy + 20f * f)
            close()
        }
        canvas.drawPath(tail, stroke)

        // Tail Fin Flukes
        val finL = Path().apply {
            moveTo(cx + 35f * f, cy + 340f * f)
            cubicTo(cx - 60f * f, cy + 380f * f, cx - 80f * f, cy + 460f * f, cx, cy + 460f * f)
            cubicTo(cx + 20f * f, cy + 420f * f, cx + 30f * f, cy + 360f * f, cx + 35f * f, cy + 340f * f)
            close()
        }
        canvas.drawPath(finL, stroke)

        val finR = Path().apply {
            moveTo(cx + 35f * f, cy + 340f * f)
            cubicTo(cx + 120f * f, cy + 380f * f, cx + 140f * f, cy + 460f * f, cx + 60f * f, cy + 460f * f)
            cubicTo(cx + 45f * f, cy + 420f * f, cx + 40f * f, cy + 360f * f, cx + 35f * f, cy + 340f * f)
            close()
        }
        canvas.drawPath(finR, stroke)

        // Underwater Bubbles
        DrawingUtils.drawBubble(canvas, cx + 220f * f, cy - 100f * f, 28f * f, stroke, fine)
        DrawingUtils.drawBubble(canvas, cx + 260f * f, cy - 200f * f, 38f * f, stroke, fine)
        DrawingUtils.drawBubble(canvas, cx + 200f * f, cy - 280f * f, 20f * f, stroke, fine)
    }

    fun drawCrystalBall(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f

        // Giant Crystal Sphere
        canvas.drawCircle(cx, cy, 210f * f, stroke)

        // Glass Highlight crescent
        val highlight = RectF(cx - 170f * f, cy - 170f * f, cx + 50f * f, cy + 50f * f)
        canvas.drawArc(highlight, 190f, 80f, false, stroke)

        // Swirling magical galaxy inside ball
        val swirl = Path().apply {
            moveTo(cx - 100f * f, cy + 60f * f)
            cubicTo(cx - 40f * f, cy - 80f * f, cx + 80f * f, cy - 40f * f, cx + 60f * f, cy + 80f * f)
            cubicTo(cx + 40f * f, cy + 120f * f, cx - 20f * f, cy + 100f * f, cx, cy + 40f * f)
        }
        canvas.drawPath(swirl, fine)
        drawSparkle(canvas, cx + 20f * f, cy - 20f * f, 25f * f, stroke)

        // Ornate Golden Pedestal Stand
        val standTop = RectF(cx - 150f * f, cy + 180f * f, cx + 150f * f, cy + 240f * f)
        canvas.drawRoundRect(standTop, 20f * f, 20f * f, stroke)

        // Pedestal Stem & Base
        val standBase = Path().apply {
            moveTo(cx - 90f * f, cy + 240f * f)
            cubicTo(cx - 70f * f, cy + 320f * f, cx - 180f * f, cy + 400f * f, cx - 220f * f, cy + 440f * f)
            lineTo(cx + 220f * f, cy + 440f * f)
            cubicTo(cx + 180f * f, cy + 400f * f, cx + 70f * f, cy + 320f * f, cx + 90f * f, cy + 240f * f)
            close()
        }
        canvas.drawPath(standBase, stroke)

        // Mystical sparkles outside
        drawSparkle(canvas, cx - 280f * f, cy - 160f * f, 32f * f, stroke)
        drawSparkle(canvas, cx + 280f * f, cy - 140f * f, 36f * f, stroke)
        drawStar(canvas, cx + 320f * f, cy + 120f * f, 24f * f, stroke)
    }

    fun drawWizardHat(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Wide Curved Hat Brim
        val brim = RectF(cx - 360f * f, cy + 120f * f, cx + 360f * f, cy + 260f * f)
        canvas.drawOval(brim, stroke)

        // Pointy Wizard Hat Cone with curled floppy tip
        val cone = Path().apply {
            moveTo(cx - 180f * f, cy + 170f * f)
            cubicTo(cx - 160f * f, cy - 20f * f, cx - 60f * f, cy - 200f * f, cx + 100f * f, cy - 320f * f)
            // Curled tip
            cubicTo(cx + 160f * f, cy - 360f * f, cx + 220f * f, cy - 300f * f, cx + 160f * f, cy - 260f * f)
            cubicTo(cx + 100f * f, cy - 220f * f, cx + 80f * f, cy - 100f * f, cx + 180f * f, cy + 170f * f)
            close()
        }
        canvas.drawPath(cone, stroke)

        // Hat Band & Buckle
        val band = Path().apply {
            moveTo(cx - 170f * f, cy + 120f * f)
            quadTo(cx, cy + 160f * f, cx + 170f * f, cy + 120f * f)
            lineTo(cx + 180f * f, cy + 170f * f)
            quadTo(cx, cy + 210f * f, cx - 180f * f, cy + 170f * f)
            close()
        }
        canvas.drawPath(band, stroke)
        // Square Buckle
        canvas.drawRoundRect(RectF(cx - 40f * f, cy + 135f * f, cx + 40f * f, cy + 195f * f), 10f * f, 10f * f, stroke)

        // Stars and Moons on Hat Cone
        drawStar(canvas, cx - 40f * f, cy + 20f * f, 30f * f, stroke)
        drawStar(canvas, cx + 40f * f, cy - 120f * f, 24f * f, stroke)

        // Crescent Moon on Hat
        val moon = Path().apply {
            val mx = cx - 50f * f
            val my = cy - 80f * f
            val r = 35f * f
            moveTo(mx, my - r)
            cubicTo(mx + r, my - r, mx + r, my + r, mx, my + r)
            cubicTo(mx + r * 0.4f, my + r * 0.5f, mx + r * 0.4f, my - r * 0.5f, mx, my - r)
            close()
        }
        canvas.drawPath(moon, stroke)

        drawSparkle(canvas, cx + 280f * f, cy - 240f * f, 30f * f, stroke)
    }

    fun drawTreasureChest(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Chest Box Base
        val box = Path().apply {
            moveTo(cx - 280f * f, cy)
            lineTo(cx + 280f * f, cy)
            lineTo(cx + 240f * f, cy + 260f * f)
            lineTo(cx - 240f * f, cy + 260f * f)
            close()
        }
        canvas.drawPath(box, stroke)

        // Open Curved Chest Lid
        val lid = Path().apply {
            moveTo(cx - 300f * f, cy - 30f * f)
            lineTo(cx - 260f * f, cy - 180f * f)
            cubicTo(cx - 180f * f, cy - 260f * f, cx + 180f * f, cy - 260f * f, cx + 260f * f, cy - 180f * f)
            lineTo(cx + 300f * f, cy - 30f * f)
            close()
        }
        canvas.drawPath(lid, stroke)

        // Iron Corner Straps
        canvas.drawLine(cx - 160f * f, cy, cx - 140f * f, cy + 260f * f, stroke)
        canvas.drawLine(cx + 160f * f, cy, cx + 140f * f, cy + 260f * f, stroke)

        // Keyhole Lock
        canvas.drawCircle(cx, cy + 40f * f, 24f * f, stroke)
        canvas.drawRect(RectF(cx - 6f * f, cy + 40f * f, cx + 6f * f, cy + 65f * f), eyeFill)

        // Overflowing Gold Coins & Gems
        for (i in -4..4) {
            val coinX = cx + (i * 55f) * f
            val coinY = (cy - 30f + (if (i % 2 == 0) -20f else 10f)) * f
            canvas.drawCircle(coinX, coinY, 24f * f, stroke)
        }

        // Sparkling Diamond Gem
        val gem = Path().apply {
            val gx = cx - 60f * f
            val gy = cy - 80f * f
            moveTo(gx, gy - 30f * f)
            lineTo(gx + 35f * f, gy)
            lineTo(gx, gy + 40f * f)
            lineTo(gx - 35f * f, gy)
            close()
        }
        canvas.drawPath(gem, stroke)

        drawSparkle(canvas, cx + 180f * f, cy - 80f * f, 30f * f, stroke)
        drawSparkle(canvas, cx - 220f * f, cy - 120f * f, 26f * f, stroke)
    }

    fun drawMagicPotion(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Round Flask Body & Neck
        val bottle = Path().apply {
            moveTo(cx - 50f * f, cy - 240f * f)
            lineTo(cx - 50f * f, cy - 140f * f)
            cubicTo(cx - 240f * f, cy - 80f * f, cx - 260f * f, cy + 200f * f, cx, cy + 260f * f)
            cubicTo(cx + 260f * f, cy + 200f * f, cx + 240f * f, cy - 80f * f, cx + 50f * f, cy - 140f * f)
            lineTo(cx + 50f * f, cy - 240f * f)
            close()
        }
        canvas.drawPath(bottle, stroke)

        // Bottle Rim Lip
        val rim = RectF(cx - 70f * f, cy - 260f * f, cx + 70f * f, cy - 230f * f)
        canvas.drawRoundRect(rim, 12f * f, 12f * f, stroke)

        // Wooden Cork Stopper
        val cork = Path().apply {
            moveTo(cx - 45f * f, cy - 260f * f)
            lineTo(cx - 55f * f, cy - 330f * f)
            lineTo(cx + 55f * f, cy - 330f * f)
            lineTo(cx + 45f * f, cy - 260f * f)
            close()
        }
        canvas.drawPath(cork, stroke)

        // Liquid Level Inside (Half-filled wavy liquid)
        val liquid = Path().apply {
            moveTo(cx - 210f * f, cy + 40f * f)
            quadTo(cx - 100f * f, cy + 10f * f, cx, cy + 40f * f)
            quadTo(cx + 100f * f, cy + 70f * f, cx + 210f * f, cy + 40f * f)
            cubicTo(cx + 240f * f, cy + 180f * f, cx + 180f * f, cy + 250f * f, cx, cy + 260f * f)
            cubicTo(cx - 180f * f, cy + 250f * f, cx - 240f * f, cy + 180f * f, cx - 210f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(liquid, stroke)

        // Bubbles inside liquid
        canvas.drawCircle(cx - 80f * f, cy + 140f * f, 24f * f, fine)
        canvas.drawCircle(cx + 70f * f, cy + 110f * f, 30f * f, fine)
        canvas.drawCircle(cx, cy + 180f * f, 18f * f, fine)

        // Sparkles and stars floating out of bottle
        drawSparkle(canvas, cx - 180f * f, cy - 220f * f, 28f * f, stroke)
        drawSparkle(canvas, cx + 180f * f, cy - 200f * f, 32f * f, stroke)
    }

    fun drawPhoenix(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Flame Crest on head
        val crest = Path().apply {
            moveTo(cx - 30f * f, cy - 160f * f)
            cubicTo(cx - 60f * f, cy - 260f * f, cx - 20f * f, cy - 320f * f, cx, cy - 340f * f)
            cubicTo(cx + 40f * f, cy - 280f * f, cx + 50f * f, cy - 220f * f, cx + 30f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(crest, stroke)

        // Cute Bird Head & Beak
        canvas.drawCircle(cx, cy - 120f * f, 70f * f, stroke)
        val beak = Path().apply {
            moveTo(cx + 50f * f, cy - 140f * f)
            lineTo(cx + 120f * f, cy - 115f * f)
            lineTo(cx + 50f * f, cy - 95f * f)
            close()
        }
        canvas.drawPath(beak, stroke)

        // Eye
        drawCuteEye(canvas, cx + 15f * f, cy - 125f * f, 18f * f, stroke, eyeFill, eyeHighlight)

        // Flaming Wings (Feathers looking like leaping fire flames)
        val wingL = Path().apply {
            moveTo(cx - 50f * f, cy)
            cubicTo(cx - 200f * f, cy - 120f * f, cx - 360f * f, cy - 100f * f, cx - 400f * f, cy)
            lineTo(cx - 340f * f, cy + 120f * f)
            lineTo(cx - 250f * f, cy + 110f * f)
            lineTo(cx - 160f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(wingL, stroke)

        val wingR = Path().apply {
            moveTo(cx + 50f * f, cy)
            cubicTo(cx + 200f * f, cy - 120f * f, cx + 360f * f, cy - 100f * f, cx + 400f * f, cy)
            lineTo(cx + 340f * f, cy + 120f * f)
            lineTo(cx + 250f * f, cy + 110f * f)
            lineTo(cx + 160f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(wingR, stroke)

        // Bird Body
        val body = Path().apply {
            moveTo(cx - 50f * f, cy - 60f * f)
            cubicTo(cx - 90f * f, cy + 60f * f, cx - 80f * f, cy + 180f * f, cx, cy + 240f * f)
            cubicTo(cx + 80f * f, cy + 180f * f, cx + 90f * f, cy + 60f * f, cx + 50f * f, cy - 60f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Long Spectacular Flaming Tail Plumes
        for (i in -1..1) {
            val tailFeather = Path().apply {
                val tx = cx + (i * 50f) * f
                moveTo(cx, cy + 240f * f)
                cubicTo(tx - 40f * f, cy + 340f * f, tx + 60f * f, cy + 420f * f, tx, cy + 480f * f)
                cubicTo(tx - 60f * f, cy + 420f * f, tx + 40f * f, cy + 340f * f, cx, cy + 240f * f)
                close()
            }
            canvas.drawPath(tailFeather, stroke)
        }

        drawSparkle(canvas, cx - 280f * f, cy - 180f * f, 28f * f, stroke)
        drawSparkle(canvas, cx + 280f * f, cy - 180f * f, 28f * f, stroke)
    }
}
