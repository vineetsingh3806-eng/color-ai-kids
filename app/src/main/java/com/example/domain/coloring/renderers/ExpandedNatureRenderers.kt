package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass

object ExpandedNatureRenderers {

    fun drawGardenRose(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // Layered concentric rose petals
        canvas.drawCircle(cx, cy, 70f * f, stroke)
        // Center spiral
        val spiral = Path().apply {
            moveTo(cx - 20f * f, cy)
            cubicTo(cx - 20f * f, cy - 30f * f, cx + 20f * f, cy - 30f * f, cx + 20f * f, cy)
            cubicTo(cx + 20f * f, cy + 20f * f, cx - 10f * f, cy + 20f * f, cx, cy)
        }
        canvas.drawPath(spiral, fine)
        // Outer petals (overlapping arches)
        for (i in 0..5) {
            val a = i * Math.PI / 3
            val px = (cx + Math.cos(a) * 110f * f).toFloat()
            val py = (cy + Math.sin(a) * 110f * f).toFloat()
            canvas.drawCircle(px, py, 75f * f, stroke)
        }
        // Thorny stem
        canvas.drawLine(cx, cy + 180f * f, cx, 860f * f, stroke)
        // Rose leaves
        val leafL = Path().apply {
            moveTo(cx, 600f * f)
            cubicTo(cx - 140f * f, 560f * f, cx - 160f * f, 660f * f, cx, 700f * f)
            close()
        }
        val leafR = Path().apply {
            moveTo(cx, 680f * f)
            cubicTo(cx + 140f * f, 640f * f, cx + 160f * f, 740f * f, cx, 780f * f)
            close()
        }
        canvas.drawPath(leafL, stroke); canvas.drawPath(leafR, stroke)
    }

    fun drawSpringTulip(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f
        // Tulip cup (3 points at top)
        val tulip = Path().apply {
            moveTo(cx - 140f * f, cy - 140f * f) // Left petal tip
            lineTo(cx - 60f * f, cy - 60f * f)
            lineTo(cx, cy - 160f * f) // Center petal tip
            lineTo(cx + 60f * f, cy - 60f * f)
            lineTo(cx + 140f * f, cy - 140f * f) // Right petal tip
            cubicTo(cx + 200f * f, cy + 80f * f, cx - 200f * f, cy + 80f * f, cx - 140f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(tulip, stroke)
        // Stem
        canvas.drawLine(cx, cy + 80f * f, cx, 860f * f, stroke)
        // Long smooth leaves curving up
        val leafL = Path().apply {
            moveTo(cx, 840f * f)
            cubicTo(cx - 180f * f, 720f * f, cx - 160f * f, 540f * f, cx - 100f * f, 440f * f)
            cubicTo(cx - 120f * f, 600f * f, cx - 80f * f, 740f * f, cx, 840f * f)
            close()
        }
        canvas.drawPath(leafL, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawSunflower(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // 12 radiant petals around center
        for (i in 0..11) {
            val a = i * Math.PI / 6
            val px = (cx + Math.cos(a) * 160f * f).toFloat()
            val py = (cy + Math.sin(a) * 160f * f).toFloat()
            val petal = Path().apply {
                moveTo(cx, cy)
                lineTo((cx + Math.cos(a - 0.2) * 140f * f).toFloat(), (cy + Math.sin(a - 0.2) * 140f * f).toFloat())
                lineTo((cx + Math.cos(a) * 220f * f).toFloat(), (cy + Math.sin(a) * 220f * f).toFloat())
                lineTo((cx + Math.cos(a + 0.2) * 140f * f).toFloat(), (cy + Math.sin(a + 0.2) * 140f * f).toFloat())
                close()
            }
            canvas.drawPath(petal, stroke)
        }
        // Big round seeded center
        canvas.drawCircle(cx, cy, 110f * f, stroke)
        // Seed grid dots
        for (row in -2..2) {
            for (col in -2..2) {
                if (row * row + col * col <= 6) {
                    canvas.drawCircle((cx + col * 35f) * f, (cy + row * 35f) * f, 6f * f, fine)
                }
            }
        }
        // Stem
        canvas.drawLine(cx, cy + 220f * f, cx, 860f * f, stroke)
    }

    fun drawPineConifer(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Tiered evergreen pine tree
        for (i in 0..3) {
            val top = (200f + i * 130f) * f
            val bot = (380f + i * 130f) * f
            val w = (100f + i * 80f) * f
            val tier = Path().apply {
                moveTo(cx, top)
                lineTo(cx + w, bot)
                lineTo(cx - w, bot)
                close()
            }
            canvas.drawPath(tier, stroke)
        }
        // Trunk
        canvas.drawRect(cx - 40f * f, 770f * f, cx + 40f * f, 880f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawWaterfall(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Mountain cliffs on left and right
        val cliffL = Path().apply { moveTo(60f * f, 200f * f); lineTo(360f * f, 300f * f); lineTo(340f * f, 860f * f); lineTo(60f * f, 860f * f); close() }
        val cliffR = Path().apply { moveTo(964f * f, 200f * f); lineTo(660f * f, 300f * f); lineTo(680f * f, 860f * f); lineTo(964f * f, 860f * f); close() }
        canvas.drawPath(cliffL, stroke); canvas.drawPath(cliffR, stroke)
        // Waterfall cascades falling between cliffs
        for (i in 0..4) {
            val x = (380f + i * 65f) * f
            canvas.drawLine(x, 280f * f, x, 780f * f, stroke)
        }
        // Splash foam pool at bottom
        for (i in 0..5) {
            val fx = (300f + i * 80f) * f
            canvas.drawCircle(fx, 780f * f, 25f * f, stroke)
        }
    }

    fun drawWoodlandStream(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Winding river banks
        val bankL = Path().apply {
            moveTo(480f * f, 260f * f)
            cubicTo(400f * f, 420f * f, 300f * f, 620f * f, 140f * f, 880f * f)
        }
        val bankR = Path().apply {
            moveTo(540f * f, 260f * f)
            cubicTo(620f * f, 420f * f, 720f * f, 620f * f, 880f * f, 880f * f)
        }
        canvas.drawPath(bankL, stroke); canvas.drawPath(bankR, stroke)
        // River stones
        canvas.drawOval(RectF(380f * f, 680f * f, 460f * f, 740f * f), stroke)
        canvas.drawOval(RectF(560f * f, 720f * f, 640f * f, 780f * f), stroke)
        // Trees on sides
        canvas.drawCircle(220f * f, 400f * f, 70f * f, stroke)
        canvas.drawCircle(800f * f, 400f * f, 70f * f, stroke)
    }

    fun drawAutumnLeaves(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // 3 Distinct autumn leaves falling
        // Maple leaf in center
        val maple = Path().apply {
            val mx = 512f * f
            val my = 460f * f
            moveTo(mx, my - 160f * f)
            lineTo(mx + 60f * f, my - 60f * f); lineTo(mx + 140f * f, my - 80f * f)
            lineTo(mx + 100f * f, my); lineTo(mx + 140f * f, my + 60f * f)
            lineTo(mx + 40f * f, my + 80f * f); lineTo(mx, my + 140f * f)
            lineTo(mx - 40f * f, my + 80f * f); lineTo(mx - 140f * f, my + 60f * f)
            lineTo(mx - 100f * f, my); lineTo(mx - 140f * f, my - 80f * f)
            lineTo(mx - 60f * f, my - 60f * f)
            close()
        }
        canvas.drawPath(maple, stroke)
        canvas.drawLine(512f * f, 460f * f, 512f * f, 660f * f, stroke) // Stem
        // Oak leaf on left
        canvas.drawOval(RectF(160f * f, 240f * f, 320f * f, 480f * f), stroke)
        // Elm leaf on right
        canvas.drawOval(RectF(700f * f, 540f * f, 860f * f, 780f * f), stroke)
    }

    fun drawOakAcorn(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Acorn textured cap
        val cap = Path().apply {
            moveTo(cx - 180f * f, cy - 40f * f)
            cubicTo(cx - 180f * f, cy - 200f * f, cx + 180f * f, cy - 200f * f, cx + 180f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(cap, stroke)
        // Stem at top of cap
        canvas.drawRoundRect(RectF(cx - 20f * f, cy - 260f * f, cx + 20f * f, cy - 180f * f), 8f * f, 8f * f, stroke)
        // Cap crosshatch
        for (i in -2..2) {
            canvas.drawLine(cx + i * 50f * f, cy - 170f * f, cx + i * 70f * f, cy - 50f * f, fine)
        }
        // Smooth acorn nut body
        val nut = Path().apply {
            moveTo(cx - 170f * f, cy - 40f * f)
            cubicTo(cx - 170f * f, cy + 220f * f, cx - 60f * f, cy + 300f * f, cx, cy + 320f * f) // Pointed tip
            cubicTo(cx + 60f * f, cy + 300f * f, cx + 170f * f, cy + 220f * f, cx + 170f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(nut, stroke)
    }

    fun drawDaisyMeadow(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // 3 Daisies in a sunny field
        val centers = listOf(300f to 560f, 512f to 420f, 720f to 560f)
        for ((cx, cy) in centers) {
            val px = cx * f
            val py = cy * f
            // 8 petals
            for (i in 0..7) {
                val a = i * Math.PI / 4
                val petal = Path().apply {
                    val ox = (px + Math.cos(a) * 65f * f).toFloat()
                    val oy = (py + Math.sin(a) * 65f * f).toFloat()
                    addCircle(ox, oy, 25f * f, Path.Direction.CW)
                }
                canvas.drawPath(petal, stroke)
            }
            // Yellow center
            canvas.drawCircle(px, py, 40f * f, stroke)
            // Stem down to ground
            canvas.drawLine(px, py + 80f * f, px, 860f * f, stroke)
        }
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawMountainPeak(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Central majestic mountain peak
        val peak = Path().apply {
            moveTo(cx, 240f * f) // Summit
            lineTo(cx + 380f * f, 860f * f)
            lineTo(cx - 380f * f, 860f * f)
            close()
        }
        canvas.drawPath(peak, stroke)
        // Snow cap zig-zag
        val snow = Path().apply {
            moveTo(cx - 140f * f, 470f * f)
            lineTo(cx - 70f * f, 500f * f); lineTo(cx, 460f * f)
            lineTo(cx + 70f * f, 510f * f); lineTo(cx + 140f * f, 470f * f)
        }
        canvas.drawPath(snow, stroke)
        // Ridge line
        canvas.drawLine(cx, 240f * f, cx - 60f * f, 860f * f, stroke)
        // Sun behind peak
        canvas.drawCircle(cx + 260f * f, 300f * f, 80f * f, stroke)
    }
}
