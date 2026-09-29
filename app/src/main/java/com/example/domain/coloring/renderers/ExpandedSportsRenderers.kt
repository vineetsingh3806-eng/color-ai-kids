package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawHappySmile
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object ExpandedSportsRenderers {

    fun drawBaseball(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Round baseball
        canvas.drawCircle(cx, cy, 220f * f, stroke)
        // Red curved seam arcs (left and right)
        val seamL = Path().apply {
            moveTo(cx - 110f * f, cy - 190f * f)
            cubicTo(cx - 30f * f, cy - 100f * f, cx - 30f * f, cy + 100f * f, cx - 110f * f, cy + 190f * f)
        }
        val seamR = Path().apply {
            moveTo(cx + 110f * f, cy - 190f * f)
            cubicTo(cx + 30f * f, cy - 100f * f, cx + 30f * f, cy + 100f * f, cx + 110f * f, cy + 190f * f)
        }
        canvas.drawPath(seamL, stroke); canvas.drawPath(seamR, stroke)
        // Stitch tick marks
        for (i in -3..3) {
            val y = cy + i * 50f * f
            canvas.drawLine(cx - 80f * f, y, cx - 50f * f, y, fine)
            canvas.drawLine(cx + 50f * f, y, cx + 80f * f, y, fine)
        }
    }

    fun drawFootball(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Pointed oval football shape
        val ball = Path().apply {
            moveTo(cx - 320f * f, cy)
            cubicTo(cx - 160f * f, cy - 200f * f, cx + 160f * f, cy - 200f * f, cx + 320f * f, cy)
            cubicTo(cx + 160f * f, cy + 200f * f, cx - 160f * f, cy + 200f * f, cx - 320f * f, cy)
            close()
        }
        canvas.drawPath(ball, stroke)
        // White end stripes
        canvas.drawLine(cx - 200f * f, cy - 120f * f, cx - 200f * f, cy + 120f * f, stroke)
        canvas.drawLine(cx + 200f * f, cy - 120f * f, cx + 200f * f, cy + 120f * f, stroke)
        // Center laces
        canvas.drawLine(cx - 80f * f, cy, cx + 80f * f, cy, stroke)
        for (i in -2..2) {
            val lx = cx + i * 30f * f
            canvas.drawLine(lx, cy - 25f * f, lx, cy + 25f * f, stroke)
        }
    }

    fun drawTennisRacket(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 460f * f
        // Oval racket head
        canvas.drawOval(RectF(cx - 160f * f, 140f * f, cx + 160f * f, 560f * f), stroke)
        // Strings grid
        for (i in -2..2) {
            val x = (cx + i * 45f) * f
            canvas.drawLine(x, 180f * f, x, 520f * f, fine)
        }
        for (i in -3..3) {
            val y = (350f + i * 45f) * f
            canvas.drawLine(cx - 130f * f, y, cx + 130f * f, y, fine)
        }
        // Handle shaft & grip
        canvas.drawRoundRect(RectF(cx - 20f * f, 560f * f, cx + 20f * f, 880f * f), 10f * f, 10f * f, stroke)
        // Fuzzy tennis ball next to racket
        canvas.drawCircle(720f * f, 400f * f, 90f * f, stroke)
        val ballSeam = Path().apply {
            moveTo(680f * f, 320f * f)
            cubicTo(760f * f, 360f * f, 760f * f, 440f * f, 680f * f, 480f * f)
        }
        canvas.drawPath(ballSeam, stroke)
    }

    fun drawTrophyCup(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Trophy goblet cup
        val cup = Path().apply {
            moveTo(cx - 180f * f, 220f * f)
            lineTo(cx + 180f * f, 220f * f)
            cubicTo(cx + 180f * f, 440f * f, cx + 100f * f, 540f * f, cx + 40f * f, 580f * f)
            lineTo(cx + 40f * f, 680f * f)
            lineTo(cx - 40f * f, 680f * f)
            lineTo(cx - 40f * f, 580f * f)
            cubicTo(cx - 100f * f, 540f * f, cx - 180f * f, 440f * f, cx - 180f * f, 220f * f)
            close()
        }
        canvas.drawPath(cup, stroke)
        // Handles (left and right C-loops)
        val handleL = Path().apply {
            moveTo(cx - 180f * f, 260f * f)
            cubicTo(cx - 320f * f, 260f * f, cx - 320f * f, 480f * f, cx - 140f * f, 480f * f)
        }
        val handleR = Path().apply {
            moveTo(cx + 180f * f, 260f * f)
            cubicTo(cx + 320f * f, 260f * f, cx + 320f * f, 480f * f, cx + 140f * f, 480f * f)
        }
        canvas.drawPath(handleL, stroke); canvas.drawPath(handleR, stroke)
        // Trophy base pedestal
        canvas.drawRoundRect(RectF(cx - 140f * f, 680f * f, cx + 140f * f, 840f * f), 15f * f, 15f * f, stroke)
        // #1 Star on cup
        drawStar(canvas, cx, 360f * f, 50f * f, stroke)
    }

    fun drawBowlingStrike(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Bowling pin in center
        val px = 400f * f
        val pin = Path().apply {
            moveTo(px - 35f * f, 300f * f)
            cubicTo(px - 50f * f, 240f * f, px + 50f * f, 240f * f, px + 35f * f, 300f * f) // Head
            cubicTo(px + 20f * f, 360f * f, px + 60f * f, 460f * f, px + 70f * f, 620f * f) // Belly
            lineTo(px - 70f * f, 620f * f)
            cubicTo(px - 60f * f, 460f * f, px - 20f * f, 360f * f, px - 35f * f, 300f * f)
            close()
        }
        canvas.drawPath(pin, stroke)
        // Pin neck stripes
        canvas.drawLine(px - 22f * f, 330f * f, px + 22f * f, 330f * f, stroke)
        canvas.drawLine(px - 25f * f, 360f * f, px + 25f * f, 360f * f, stroke)
        // Bowling ball rolling in on right
        val bx = 680f * f
        val by = 680f * f
        canvas.drawCircle(bx, by, 140f * f, stroke)
        // 3 finger holes
        canvas.drawCircle(bx - 30f * f, by - 40f * f, 18f * f, eyeFill)
        canvas.drawCircle(bx + 30f * f, by - 40f * f, 18f * f, eyeFill)
        canvas.drawCircle(bx, by + 30f * f, 18f * f, eyeFill)
    }

    fun drawMarchingDrum(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Top drum head skin oval
        canvas.drawOval(RectF(cx - 220f * f, cy - 140f * f, cx + 220f * f, cy - 40f * f), stroke)
        // Drum cylinder body
        canvas.drawRect(cx - 220f * f, cy - 90f * f, cx + 220f * f, cy + 180f * f, stroke)
        // Bottom rim oval
        canvas.drawOval(RectF(cx - 220f * f, cy + 130f * f, cx + 220f * f, cy + 230f * f), stroke)
        // Zigzag tension ropes on drum side
        val ropes = Path().apply {
            moveTo(cx - 220f * f, cy - 90f * f)
            var x = cx - 220f * f
            while (x < cx + 220f * f) {
                lineTo(x + 55f * f, cy + 180f * f)
                lineTo(x + 110f * f, cy - 90f * f)
                x += 110f * f
            }
        }
        canvas.drawPath(ropes, fine)
        // Drumsticks crossed on top
        canvas.drawLine(cx - 180f * f, cy - 240f * f, cx + 80f * f, cy - 70f * f, stroke)
        canvas.drawCircle(cx - 180f * f, cy - 240f * f, 18f * f, stroke)
        canvas.drawLine(cx + 180f * f, cy - 240f * f, cx - 80f * f, cy - 70f * f, stroke)
        canvas.drawCircle(cx + 180f * f, cy - 240f * f, 18f * f, stroke)
    }

    fun drawToyTrain(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Wooden train engine
        // Boiler horizontal cylinder
        canvas.drawRoundRect(RectF(160f * f, 440f * f, 560f * f, 660f * f), 30f * f, 30f * f, stroke)
        // Cab box on right
        canvas.drawRoundRect(RectF(560f * f, 320f * f, 820f * f, 660f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(620f * f, 360f * f, 740f * f, 480f * f), 10f * f, 10f * f, stroke) // Cab window
        // Smokestack funnel
        val funnel = Path().apply {
            moveTo(260f * f, 440f * f)
            lineTo(240f * f, 320f * f); lineTo(320f * f, 320f * f); lineTo(300f * f, 440f * f)
            close()
        }
        canvas.drawPath(funnel, stroke)
        // Wheels
        canvas.drawCircle(280f * f, 720f * f, 65f * f, stroke)
        canvas.drawCircle(460f * f, 720f * f, 65f * f, stroke)
        canvas.drawCircle(700f * f, 720f * f, 75f * f, stroke)
        // Connecting rod
        canvas.drawLine(280f * f, 720f * f, 460f * f, 720f * f, stroke)
    }

    fun drawWindPinwheel(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f
        // Wooden stick
        canvas.drawRoundRect(RectF(cx - 15f * f, cy, cx + 15f * f, 880f * f), 8f * f, 8f * f, stroke)
        // 4 Pinwheel folding triangular blades
        for (i in 0..3) {
            val a = i * Math.PI / 2
            val blade = Path().apply {
                moveTo(cx, cy)
                val x1 = (cx + Math.cos(a) * 200f * f).toFloat()
                val y1 = (cy + Math.sin(a) * 200f * f).toFloat()
                val x2 = (cx + Math.cos(a + Math.PI / 4) * 220f * f).toFloat()
                val y2 = (cy + Math.sin(a + Math.PI / 4) * 220f * f).toFloat()
                lineTo(x1, y1)
                lineTo(x2, y2)
                close()
            }
            canvas.drawPath(blade, stroke)
        }
        // Center pin button
        canvas.drawCircle(cx, cy, 25f * f, stroke)
    }

    fun drawRainbowXylophone(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // 6 Graded tone bars from longest (left) to shortest (right)
        for (i in 0..5) {
            val left = (160f + i * 110f) * f
            val top = (300f + i * 35f) * f
            val bot = (760f - i * 35f) * f
            canvas.drawRoundRect(RectF(left, top, left + 85f * f, bot), 15f * f, 15f * f, stroke)
            // Mounting screws
            canvas.drawCircle(left + 42.5f * f, top + 30f * f, 8f * f, fine)
            canvas.drawCircle(left + 42.5f * f, bot - 30f * f, 8f * f, fine)
        }
        // Mallet stick across
        canvas.drawLine(300f * f, 220f * f, 660f * f, 380f * f, stroke)
        canvas.drawCircle(300f * f, 220f * f, 30f * f, stroke)
    }

    fun drawJackInBox(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Box at bottom
        canvas.drawRoundRect(RectF(cx - 180f * f, 560f * f, cx + 180f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Hand crank on right
        val crank = Path().apply { moveTo(cx + 180f * f, 700f * f); lineTo(cx + 260f * f, 700f * f); lineTo(cx + 260f * f, 640f * f) }
        canvas.drawPath(crank, stroke)
        canvas.drawCircle(cx + 260f * f, 640f * f, 15f * f, stroke)
        // Spring coil
        val spring = Path().apply {
            moveTo(cx, 560f * f)
            var y = 560f * f
            var dir = 1
            while (y > 420f * f) {
                lineTo(cx + dir * 60f * f, y - 25f * f)
                y -= 25f * f
                dir = -dir
            }
            lineTo(cx, 400f * f)
        }
        canvas.drawPath(spring, stroke)
        // Clown head popping out
        canvas.drawCircle(cx, 320f * f, 90f * f, stroke)
        drawCuteEye(canvas, cx - 35f * f, 300f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, 300f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(cx, 340f * f, 18f * f, eyeFill) // Clown nose
        drawHappySmile(canvas, cx, 370f * f, 40f * f, stroke)
        // Jester hat
        val hat = Path().apply {
            moveTo(cx - 70f * f, 260f * f)
            lineTo(cx - 120f * f, 140f * f)
            lineTo(cx, 240f * f)
            lineTo(cx + 120f * f, 140f * f)
            lineTo(cx + 70f * f, 260f * f)
            close()
        }
        canvas.drawPath(hat, stroke)
    }
}
