package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object ExpandedFairytaleRenderers {

    fun drawCastleTurret(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Tall cylinder stone tower
        canvas.drawRect(cx - 140f * f, 380f * f, cx + 140f * f, 860f * f, stroke)
        // Pointed cone roof
        val roof = Path().apply {
            moveTo(cx, 160f * f)
            lineTo(cx + 180f * f, 380f * f)
            lineTo(cx - 180f * f, 380f * f)
            close()
        }
        canvas.drawPath(roof, stroke)
        // Flag at roof peak
        canvas.drawLine(cx, 160f * f, cx, 100f * f, stroke)
        val flag = Path().apply { moveTo(cx, 100f * f); lineTo(cx + 80f * f, 125f * f); lineTo(cx, 150f * f); close() }
        canvas.drawPath(flag, stroke)
        // Arched window
        val window = Path().apply {
            moveTo(cx - 50f * f, 560f * f)
            lineTo(cx - 50f * f, 480f * f)
            cubicTo(cx - 50f * f, 440f * f, cx + 50f * f, 440f * f, cx + 50f * f, 480f * f)
            lineTo(cx + 50f * f, 560f * f)
            close()
        }
        canvas.drawPath(window, stroke)
        // Stone brick patterns
        canvas.drawLine(cx - 100f * f, 660f * f, cx + 100f * f, 660f * f, fine)
        canvas.drawLine(cx - 100f * f, 740f * f, cx + 100f * f, 740f * f, fine)
    }

    fun drawMagicMirror(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Ornate vintage hand mirror handle
        canvas.drawRoundRect(RectF(cx - 25f * f, cy + 240f * f, cx + 25f * f, 880f * f), 15f * f, 15f * f, stroke)
        canvas.drawCircle(cx, 880f * f, 30f * f, stroke) // Handle pommel
        // Ornate outer frame
        canvas.drawOval(RectF(cx - 220f * f, cy - 260f * f, cx + 220f * f, cy + 260f * f), stroke)
        // Inner glass oval
        canvas.drawOval(RectF(cx - 170f * f, cy - 210f * f, cx + 170f * f, cy + 210f * f), stroke)
        // Magic smiling face in the mirror
        canvas.drawCircle(cx - 50f * f, cy - 40f * f, 16f * f, stroke)
        canvas.drawCircle(cx + 50f * f, cy - 40f * f, 16f * f, stroke)
        val smile = Path().apply { moveTo(cx - 40f * f, cy + 40f * f); quadTo(cx, cy + 80f * f, cx + 40f * f, cy + 40f * f) }
        canvas.drawPath(smile, stroke)
        // Sparkles
        drawSparkle(canvas, cx + 120f * f, cy - 140f * f, 25f * f, stroke)
    }

    fun drawEnchantedRose(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Glass bell jar dome
        val jar = Path().apply {
            moveTo(cx - 180f * f, 780f * f)
            lineTo(cx - 180f * f, 400f * f)
            cubicTo(cx - 180f * f, 200f * f, cx + 180f * f, 200f * f, cx + 180f * f, 400f * f)
            lineTo(cx + 180f * f, 780f * f)
            close()
        }
        canvas.drawPath(jar, stroke)
        // Wooden base
        canvas.drawRoundRect(RectF(cx - 240f * f, 780f * f, cx + 240f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Rose floating inside jar
        canvas.drawCircle(cx, 440f * f, 60f * f, stroke)
        canvas.drawLine(cx, 500f * f, cx, 740f * f, stroke) // Stem
        // Fallen petal on base
        canvas.drawOval(RectF(cx - 80f * f, 740f * f, cx - 20f * f, 770f * f), stroke)
        drawSparkle(canvas, cx + 80f * f, 420f * f, 20f * f, fine)
    }

    fun drawStorybook(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Open fairytale storybook
        val book = Path().apply {
            moveTo(cx, 760f * f)
            cubicTo(cx - 160f * f, 780f * f, cx - 320f * f, 760f * f, cx - 380f * f, 700f * f)
            lineTo(cx - 380f * f, 420f * f)
            cubicTo(cx - 320f * f, 480f * f, cx - 160f * f, 500f * f, cx, 460f * f)
            cubicTo(cx + 160f * f, 500f * f, cx + 320f * f, 480f * f, cx + 380f * f, 420f * f)
            lineTo(cx + 380f * f, 700f * f)
            cubicTo(cx + 320f * f, 760f * f, cx + 160f * f, 780f * f, cx, 760f * f)
            close()
        }
        canvas.drawPath(book, stroke)
        canvas.drawLine(cx, 460f * f, cx, 760f * f, stroke)
        // Little castle emerging from book pages
        canvas.drawRect(cx - 60f * f, 280f * f, cx + 60f * f, 440f * f, stroke)
        // Turret roof
        val roof = Path().apply { moveTo(cx, 180f * f); lineTo(cx + 80f * f, 280f * f); lineTo(cx - 80f * f, 280f * f); close() }
        canvas.drawPath(roof, stroke)
        drawSparkle(canvas, cx + 140f * f, 320f * f, 25f * f, stroke)
    }

    fun drawKnightHelmet(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 500f * f
        // Round steel helmet dome
        val dome = Path().apply {
            moveTo(cx - 180f * f, cy + 160f * f)
            lineTo(cx - 180f * f, cy - 60f * f)
            cubicTo(cx - 180f * f, cy - 260f * f, cx + 180f * f, cy - 260f * f, cx + 180f * f, cy - 60f * f)
            lineTo(cx + 180f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(dome, stroke)
        // Movable visor plate in center
        canvas.drawRoundRect(RectF(cx - 160f * f, cy - 40f * f, cx + 160f * f, cy + 80f * f), 20f * f, 20f * f, stroke)
        // Horizontal visor eye slit
        canvas.drawLine(cx - 120f * f, cy + 20f * f, cx + 120f * f, cy + 20f * f, stroke)
        // Visor pivot bolts
        canvas.drawCircle(cx - 140f * f, cy + 20f * f, 12f * f, stroke)
        canvas.drawCircle(cx + 140f * f, cy + 20f * f, 12f * f, stroke)
        // Flowing knight plume feather on top
        val plume = Path().apply {
            moveTo(cx, cy - 240f * f)
            cubicTo(cx - 60f * f, cy - 360f * f, cx + 160f * f, cy - 440f * f, cx + 240f * f, cy - 380f * f)
            cubicTo(cx + 200f * f, cy - 320f * f, cx + 100f * f, cy - 280f * f, cx, cy - 240f * f)
        }
        canvas.drawPath(plume, stroke)
    }

    fun drawSwordInStone(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Great anvil / stone block at base
        val stone = Path().apply {
            moveTo(240f * f, 860f * f)
            lineTo(300f * f, 640f * f)
            lineTo(724f * f, 640f * f)
            lineTo(784f * f, 860f * f)
            close()
        }
        canvas.drawPath(stone, stroke)
        // Golden sword blade plunging into rock
        val blade = Path().apply {
            moveTo(cx - 25f * f, 640f * f)
            lineTo(cx - 25f * f, 380f * f)
            lineTo(cx + 25f * f, 380f * f)
            lineTo(cx + 25f * f, 640f * f)
            close()
        }
        canvas.drawPath(blade, stroke)
        canvas.drawLine(cx, 380f * f, cx, 640f * f, fine) // Fuller ridge
        // Crossguard hilt
        canvas.drawRoundRect(RectF(cx - 120f * f, 350f * f, cx + 120f * f, 380f * f), 8f * f, 8f * f, stroke)
        // Grip & Pommel
        canvas.drawRect(cx - 15f * f, 240f * f, cx + 15f * f, 350f * f, stroke)
        canvas.drawCircle(cx, 220f * f, 25f * f, stroke)
        drawSparkle(canvas, cx + 80f * f, 440f * f, 25f * f, stroke)
    }

    fun drawGingerbreadHouse(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // House walls
        canvas.drawRect(cx - 200f * f, 480f * f, cx + 200f * f, 840f * f, stroke)
        // Pointed roof with icing drip scallops
        val roof = Path().apply {
            moveTo(cx, 220f * f)
            lineTo(cx + 260f * f, 480f * f)
            lineTo(cx - 260f * f, 480f * f)
            close()
        }
        canvas.drawPath(roof, stroke)
        // Candy door
        canvas.drawRoundRect(RectF(cx - 60f * f, 640f * f, cx + 60f * f, 840f * f), 30f * f, 30f * f, stroke)
        // Peppermint candy canes on sides
        canvas.drawCircle(cx - 120f * f, 600f * f, 35f * f, stroke)
        canvas.drawCircle(cx + 120f * f, 600f * f, 35f * f, stroke)
        // Gumdrop chimney
        canvas.drawRect(cx + 100f * f, 260f * f, cx + 160f * f, 360f * f, stroke)
    }

    fun drawMagicBeanstalk(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Thick twisting beanstalk reaching skyward
        val stalk = Path().apply {
            moveTo(cx - 60f * f, 880f * f)
            cubicTo(cx - 140f * f, 680f * f, cx + 140f * f, 480f * f, cx - 40f * f, 240f * f)
            lineTo(cx + 40f * f, 240f * f)
            cubicTo(cx + 200f * f, 480f * f, cx - 60f * f, 680f * f, cx + 60f * f, 880f * f)
            close()
        }
        canvas.drawPath(stalk, stroke)
        // Giant climbing leaves
        val leaf1 = Path().apply { moveTo(cx - 80f * f, 620f * f); cubicTo(cx - 240f * f, 560f * f, cx - 260f * f, 680f * f, cx - 80f * f, 700f * f); close() }
        val leaf2 = Path().apply { moveTo(cx + 80f * f, 460f * f); cubicTo(cx + 240f * f, 400f * f, cx + 260f * f, 520f * f, cx + 80f * f, 540f * f); close() }
        canvas.drawPath(leaf1, stroke); canvas.drawPath(leaf2, stroke)
        // Clouds at top
        canvas.drawCircle(cx - 100f * f, 180f * f, 80f * f, stroke)
        canvas.drawCircle(cx + 80f * f, 180f * f, 80f * f, stroke)
    }

    fun drawRoyalBanner(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Flagpole rod across top
        canvas.drawLine(cx - 240f * f, 220f * f, cx + 240f * f, 220f * f, stroke)
        canvas.drawCircle(cx - 240f * f, 220f * f, 20f * f, stroke)
        canvas.drawCircle(cx + 240f * f, 220f * f, 20f * f, stroke)
        // Hanging medieval swallowtail pennant
        val banner = Path().apply {
            moveTo(cx - 200f * f, 220f * f)
            lineTo(cx + 200f * f, 220f * f)
            lineTo(cx + 200f * f, 740f * f)
            lineTo(cx, 640f * f) // Swallowtail notch
            lineTo(cx - 200f * f, 740f * f)
            close()
        }
        canvas.drawPath(banner, stroke)
        // Royal lion crest / crown in center
        canvas.drawCircle(cx, 420f * f, 80f * f, stroke)
        drawStar(canvas, cx, 420f * f, 45f * f, stroke)
    }

    fun drawRoyalQuill(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Inkwell pot on right
        canvas.drawRoundRect(RectF(cx + 60f * f, 660f * f, cx + 240f * f, 840f * f), 20f * f, 20f * f, stroke)
        canvas.drawOval(RectF(cx + 100f * f, 620f * f, cx + 200f * f, 660f * f), stroke) // Inkwell neck
        // Long graceful feather quill pen dipping in
        val quill = Path().apply {
            moveTo(cx + 150f * f, 640f * f) // Nib
            lineTo(cx - 40f * f, 440f * f)
            cubicTo(cx - 200f * f, 280f * f, cx - 180f * f, 120f * f, cx - 260f * f, 80f * f) // Feather tip
            cubicTo(cx - 200f * f, 180f * f, cx - 120f * f, 340f * f, cx - 20f * f, 460f * f)
            close()
        }
        canvas.drawPath(quill, stroke)
        // Feather vane shaft
        canvas.drawLine(cx + 150f * f, 640f * f, cx - 260f * f, 80f * f, stroke)
    }
}
