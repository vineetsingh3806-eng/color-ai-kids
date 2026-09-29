package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object ExpandedSpaceRenderers {

    fun drawBlackHole(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Central dark event horizon with cute face
        canvas.drawCircle(cx, cy, 120f * f, eyeFill)
        canvas.drawCircle(cx - 40f * f, cy - 20f * f, 18f * f, eyeHighlight)
        canvas.drawCircle(cx + 40f * f, cy - 20f * f, 18f * f, eyeHighlight)
        // Glowing accretion disk rings
        for (i in 1..4) {
            val rx = (160f + i * 55f) * f
            val ry = (60f + i * 25f) * f
            canvas.drawOval(RectF(cx - rx, cy - ry, cx + rx, cy + ry), stroke)
        }
        // Swirling light rays
        for (i in 0..7) {
            val a = i * Math.PI / 4
            val x1 = (cx + Math.cos(a) * 320f * f).toFloat()
            val y1 = (cy + Math.sin(a) * 200f * f).toFloat()
            canvas.drawLine(cx, cy, x1, y1, fine)
        }
        drawSparkle(canvas, 200f * f, 220f * f, 25f * f, stroke)
        drawSparkle(canvas, 820f * f, 780f * f, 25f * f, stroke)
    }

    fun drawSpaceTelescope(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Diagonal telescope barrel
        val barrel = Path().apply {
            moveTo(320f * f, 640f * f)
            lineTo(640f * f, 320f * f)
            lineTo(720f * f, 400f * f)
            lineTo(400f * f, 720f * f)
            close()
        }
        canvas.drawPath(barrel, stroke)
        // Open mirror lens cover
        canvas.drawOval(RectF(600f * f, 280f * f, 760f * f, 440f * f), stroke)
        // Solar panel wings (left and right)
        canvas.drawRect(180f * f, 460f * f, 340f * f, 560f * f, stroke)
        canvas.drawLine(260f * f, 460f * f, 260f * f, 560f * f, fine)
        canvas.drawRect(680f * f, 460f * f, 840f * f, 560f * f, stroke)
        canvas.drawLine(760f * f, 460f * f, 760f * f, 560f * f, fine)
        // Distant stars
        drawStar(canvas, 200f * f, 200f * f, 35f * f, stroke)
        drawStar(canvas, 820f * f, 200f * f, 35f * f, stroke)
    }

    fun drawComet(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Comet icy nucleus head
        val hx = 320f * f
        val hy = 680f * f
        canvas.drawCircle(hx, hy, 120f * f, stroke)
        drawCuteEye(canvas, hx - 35f * f, hy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, hx + 35f * f, hy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        // Smiling mouth
        val smile = Path().apply {
            moveTo(hx - 30f * f, hy + 30f * f); quadTo(hx, hy + 65f * f, hx + 30f * f, hy + 30f * f)
        }
        canvas.drawPath(smile, stroke)
        // Long blazing tail streaking to top-right
        for (i in 0..4) {
            val tail = Path().apply {
                val offset = (i - 2) * 40f * f
                moveTo(hx + 80f * f + offset, hy - 80f * f - offset)
                cubicTo(500f * f + offset, 440f * f, 660f * f + offset, 300f * f, 900f * f + offset, 160f * f)
            }
            canvas.drawPath(tail, stroke)
        }
        // Sparkles around tail
        drawSparkle(canvas, 600f * f, 320f * f, 20f * f, fine)
        drawSparkle(canvas, 750f * f, 440f * f, 25f * f, fine)
    }

    fun drawMeteorShower(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // 3 Shooting meteors flying diagonally
        val starts = listOf(300f to 300f, 560f to 420f, 380f to 680f)
        for ((x, y) in starts) {
            val px = x * f
            val py = y * f
            canvas.drawCircle(px, py, 45f * f, stroke)
            // Fire streak tails
            val t1 = Path().apply { moveTo(px, py); lineTo(px + 280f * f, py - 200f * f) }
            val t2 = Path().apply { moveTo(px, py); lineTo(px + 240f * f, py - 160f * f) }
            canvas.drawPath(t1, stroke); canvas.drawPath(t2, fine)
        }
        // Stars in background
        drawStar(canvas, 180f * f, 180f * f, 30f * f, stroke)
        drawStar(canvas, 800f * f, 750f * f, 30f * f, stroke)
    }

    fun drawAlienMothership(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // Dome cockpit
        val dome = Path().apply {
            moveTo(cx - 140f * f, cy)
            cubicTo(cx - 140f * f, cy - 200f * f, cx + 140f * f, cy - 200f * f, cx + 140f * f, cy)
            close()
        }
        canvas.drawPath(dome, stroke)
        // Cute alien inside dome
        canvas.drawCircle(cx, cy - 70f * f, 45f * f, stroke)
        drawCuteEye(canvas, cx, cy - 70f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        // Main saucer body
        canvas.drawOval(RectF(cx - 360f * f, cy - 60f * f, cx + 360f * f, cy + 120f * f), stroke)
        // Glowing hull port lights
        for (i in -3..3) {
            canvas.drawCircle(cx + i * 85f * f, cy + 30f * f, 18f * f, stroke)
        }
        // Tractor beam pouring down
        val beam = Path().apply {
            moveTo(cx - 100f * f, cy + 120f * f)
            lineTo(cx - 260f * f, 880f * f)
            lineTo(cx + 260f * f, 880f * f)
            lineTo(cx + 100f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(beam, stroke)
    }

    fun drawAirlockModule(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Cylindrical space module
        canvas.drawRoundRect(RectF(cx - 220f * f, cy - 180f * f, cx + 220f * f, cy + 180f * f), 40f * f, 40f * f, stroke)
        // Round hatch door in center
        canvas.drawCircle(cx, cy, 120f * f, stroke)
        canvas.drawCircle(cx, cy, 80f * f, stroke)
        // Wheel lock in center
        canvas.drawCircle(cx, cy, 30f * f, stroke)
        for (i in 0..3) {
            val a = i * Math.PI / 2
            canvas.drawLine(cx, cy, (cx + Math.cos(a) * 80f * f).toFloat(), (cy + Math.sin(a) * 80f * f).toFloat(), stroke)
        }
        // Outer space helmet peering in
        drawSparkle(canvas, 180f * f, 220f * f, 25f * f, stroke)
        drawSparkle(canvas, 820f * f, 820f * f, 25f * f, stroke)
    }

    fun drawCosmicNebula(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Billowing cloud shapes of cosmic gas
        for (i in 0..2) {
            val neb = Path().apply {
                val r = (240f + i * 70f) * f
                moveTo(cx - r, cy)
                cubicTo(cx - r, cy - r * 0.8f, cx, cy - r * 1.2f, cx + r * 0.6f, cy - r * 0.6f)
                cubicTo(cx + r * 1.2f, cy, cx + r, cy + r * 0.8f, cx, cy + r)
                cubicTo(cx - r * 0.8f, cy + r * 0.8f, cx - r, cy + r * 0.4f, cx - r, cy)
                close()
            }
            canvas.drawPath(neb, stroke)
        }
        // Newborn stars glowing in the nebula
        drawStar(canvas, cx, cy, 50f * f, stroke)
        drawStar(canvas, cx - 180f * f, cy - 120f * f, 30f * f, stroke)
        drawStar(canvas, cx + 180f * f, cy + 120f * f, 30f * f, stroke)
        drawSparkle(canvas, cx + 120f * f, cy - 180f * f, 25f * f, stroke)
    }

    fun drawSolarEclipse(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Radiant solar corona flaring outward
        for (i in 0..15) {
            val a = i * Math.PI / 8
            val r1 = 180f * f
            val r2 = (240f + (i % 2) * 60f) * f
            val x1 = (cx + Math.cos(a) * r1).toFloat()
            val y1 = (cy + Math.sin(a) * r1).toFloat()
            val x2 = (cx + Math.cos(a) * r2).toFloat()
            val y2 = (cy + Math.sin(a) * r2).toFloat()
            canvas.drawLine(x1, y1, x2, y2, stroke)
        }
        // Diamond ring flash at top-right
        drawStar(canvas, cx + 140f * f, cy - 140f * f, 40f * f, stroke)
        // Dark moon disk blocking the sun
        canvas.drawCircle(cx, cy, 180f * f, eyeFill)
        // White border ring
        canvas.drawCircle(cx, cy, 180f * f, stroke)
    }

    fun drawSpiralGalaxy(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Bright galactic center
        canvas.drawCircle(cx, cy, 60f * f, stroke)
        drawSparkle(canvas, cx, cy, 30f * f, stroke)
        // 2 Sweeping spiral arms
        val arm1 = Path().apply {
            moveTo(cx, cy - 60f * f)
            cubicTo(cx + 200f * f, cy - 160f * f, cx + 360f * f, cy + 40f * f, cx + 240f * f, cy + 280f * f)
            cubicTo(cx + 120f * f, cy + 380f * f, cx - 180f * f, cy + 340f * f, cx - 340f * f, cy + 180f * f)
        }
        canvas.drawPath(arm1, stroke)
        val arm2 = Path().apply {
            moveTo(cx, cy + 60f * f)
            cubicTo(cx - 200f * f, cy + 160f * f, cx - 360f * f, cy - 40f * f, cx - 240f * f, cy - 280f * f)
            cubicTo(cx - 120f * f, cy - 380f * f, cx + 180f * f, cy - 340f * f, cx + 340f * f, cy - 180f * f)
        }
        canvas.drawPath(arm2, stroke)
        // Scattered star clusters
        drawStar(canvas, cx - 260f * f, cy - 120f * f, 22f * f, stroke)
        drawStar(canvas, cx + 260f * f, cy + 120f * f, 22f * f, stroke)
    }

    fun drawSatelliteOrbit(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Central Earth globe
        canvas.drawCircle(cx, cy, 140f * f, stroke)
        // Continents on earth
        val continent = Path().apply {
            moveTo(cx - 60f * f, cy - 80f * f)
            cubicTo(cx + 20f * f, cy - 120f * f, cx + 80f * f, cy - 40f * f, cx + 40f * f, cy + 40f * f)
            cubicTo(cx - 40f * f, cy + 80f * f, cx - 80f * f, cy + 20f * f, cx - 60f * f, cy - 80f * f)
            close()
        }
        canvas.drawPath(continent, fine)
        // Elliptical orbital path
        canvas.drawOval(RectF(cx - 360f * f, cy - 140f * f, cx + 360f * f, cy + 140f * f), fine)
        // Satellite on orbit (top-right)
        val sx = cx + 240f * f
        val sy = cy - 100f * f
        canvas.drawRect(sx - 25f * f, sy - 25f * f, sx + 25f * f, sy + 25f * f, stroke)
        // Solar panels
        canvas.drawRect(sx - 85f * f, sy - 15f * f, sx - 25f * f, sy + 15f * f, stroke)
        canvas.drawRect(sx + 25f * f, sy - 15f * f, sx + 85f * f, sy + 15f * f, stroke)
    }
}
