package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object ExpandedFantasyRenderers {

    fun drawGriffin(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Eagle head with hooked beak
        val head = Path().apply {
            moveTo(cx - 100f * f, 320f * f)
            cubicTo(cx - 80f * f, 220f * f, cx + 40f * f, 200f * f, cx + 80f * f, 260f * f)
            lineTo(cx + 160f * f, 300f * f) // Beak tip
            lineTo(cx + 80f * f, 350f * f)
            cubicTo(cx + 40f * f, 440f * f, cx - 60f * f, 460f * f, cx - 100f * f, 400f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, cx + 20f * f, 270f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        // Feather crest
        canvas.drawLine(cx - 40f * f, 210f * f, cx - 80f * f, 140f * f, stroke)
        // Lion body & wings
        val body = Path().apply {
            moveTo(cx - 80f * f, 420f * f)
            cubicTo(cx - 200f * f, 460f * f, cx - 240f * f, 620f * f, cx - 180f * f, 740f * f)
            lineTo(cx + 120f * f, 740f * f)
            cubicTo(cx + 160f * f, 640f * f, cx + 120f * f, 500f * f, cx - 40f * f, 460f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Big eagle wings
        val wing = Path().apply {
            moveTo(cx - 60f * f, 460f * f)
            cubicTo(cx - 20f * f, 340f * f, cx + 180f * f, 280f * f, cx + 280f * f, 240f * f)
            cubicTo(cx + 220f * f, 400f * f, cx + 100f * f, 540f * f, cx, 560f * f)
            close()
        }
        canvas.drawPath(wing, stroke)
        // Legs with talons
        canvas.drawRoundRect(RectF(cx - 160f * f, 720f * f, cx - 80f * f, 860f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 40f * f, 720f * f, cx + 120f * f, 860f * f), 20f * f, 20f * f, stroke)
    }

    fun drawPegasusFoal(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 480f * f
        // Cute baby pony head
        canvas.drawCircle(cx, 320f * f, 120f * f, stroke)
        // Pointy pony ears
        val ear = Path().apply { moveTo(cx - 60f * f, 220f * f); lineTo(cx - 70f * f, 120f * f); lineTo(cx - 20f * f, 200f * f); close() }
        canvas.drawPath(ear, stroke)
        drawCuteEye(canvas, cx + 20f * f, 300f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Cute snout
        canvas.drawOval(RectF(cx + 80f * f, 310f * f, cx + 160f * f, 390f * f), stroke)
        // Body
        canvas.drawRoundRect(RectF(cx - 160f * f, 440f * f, cx + 140f * f, 720f * f), 60f * f, 60f * f, stroke)
        // Feathered wings
        val wing = Path().apply {
            moveTo(cx - 20f * f, 460f * f)
            cubicTo(cx + 120f * f, 360f * f, cx + 260f * f, 380f * f, cx + 320f * f, 320f * f)
            cubicTo(cx + 260f * f, 460f * f, cx + 160f * f, 560f * f, cx + 40f * f, 560f * f)
            close()
        }
        canvas.drawPath(wing, stroke)
        // Pony legs
        canvas.drawRoundRect(RectF(cx - 130f * f, 680f * f, cx - 70f * f, 860f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 60f * f, 680f * f, cx + 120f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Cloud beneath
        drawSparkle(canvas, cx + 260f * f, 780f * f, 25f * f, stroke)
    }

    fun drawSpellbook(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Open spellbook spine & pages
        val book = Path().apply {
            // Left page
            moveTo(cx, 740f * f)
            cubicTo(cx - 140f * f, 760f * f, cx - 300f * f, 740f * f, cx - 360f * f, 680f * f)
            lineTo(cx - 360f * f, 320f * f)
            cubicTo(cx - 300f * f, 380f * f, cx - 140f * f, 400f * f, cx, 360f * f)
            // Right page
            cubicTo(cx + 140f * f, 400f * f, cx + 300f * f, 380f * f, cx + 360f * f, 320f * f)
            lineTo(cx + 360f * f, 680f * f)
            cubicTo(cx + 300f * f, 740f * f, cx + 140f * f, 760f * f, cx, 740f * f)
            close()
        }
        canvas.drawPath(book, stroke)
        // Center spine line
        canvas.drawLine(cx, 360f * f, cx, 740f * f, stroke)
        // Magic symbol on left page (Moon & Star)
        canvas.drawCircle(cx - 180f * f, 500f * f, 50f * f, stroke)
        drawStar(canvas, cx + 180f * f, 500f * f, 50f * f, stroke)
        // Magic sparkles rising from pages
        drawSparkle(canvas, cx, 240f * f, 35f * f, stroke)
        drawSparkle(canvas, cx - 140f * f, 260f * f, 20f * f, fine)
        drawSparkle(canvas, cx + 140f * f, 260f * f, 20f * f, fine)
    }

    fun drawMagicCauldron(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f
        // Cauldron pot rim
        canvas.drawOval(RectF(cx - 240f * f, cy - 140f * f, cx + 240f * f, cy - 60f * f), stroke)
        // Round cauldron belly
        val pot = Path().apply {
            moveTo(cx - 240f * f, cy - 100f * f)
            cubicTo(cx - 320f * f, cy + 80f * f, cx - 220f * f, cy + 240f * f, cx, cy + 240f * f)
            cubicTo(cx + 220f * f, cy + 240f * f, cx + 320f * f, cy + 80f * f, cx + 240f * f, cy - 100f * f)
            close()
        }
        canvas.drawPath(pot, stroke)
        // Cauldron legs
        canvas.drawRoundRect(RectF(cx - 200f * f, cy + 220f * f, cx - 150f * f, cy + 280f * f), 10f * f, 10f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 150f * f, cy + 220f * f, cx + 200f * f, cy + 280f * f), 10f * f, 10f * f, stroke)
        // Bubbling potion foam & bubbles rising
        for (i in 0..4) {
            val bx = (cx - 140f + i * 70f) * f
            val by = (cy - 160f - (i % 2) * 50f) * f
            canvas.drawCircle(bx, by, 30f * f, stroke)
        }
        // Floating magic sparkles
        drawSparkle(canvas, cx - 60f * f, cy - 280f * f, 30f * f, stroke)
        drawSparkle(canvas, cx + 80f * f, cy - 320f * f, 25f * f, stroke)
    }

    fun drawCrystalCave(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Cave archway
        val cave = Path().apply {
            moveTo(140f * f, 860f * f)
            cubicTo(160f * f, 240f * f, 860f * f, 240f * f, 880f * f, 860f * f)
            close()
        }
        canvas.drawPath(cave, stroke)
        // Giant crystal cluster rising from floor
        drawPrism(canvas, 360f * f, 840f * f, 70f * f, 320f * f, stroke)
        drawPrism(canvas, 512f * f, 840f * f, 90f * f, 420f * f, stroke)
        drawPrism(canvas, 660f * f, 840f * f, 70f * f, 300f * f, stroke)
        // Stalactites hanging from cave ceiling
        drawPrism(canvas, 300f * f, 320f * f, 50f * f, -160f * f, fine)
        drawPrism(canvas, 700f * f, 340f * f, 50f * f, -160f * f, fine)
        // Sparkles
        drawSparkle(canvas, 512f * f, 360f * f, 30f * f, stroke)
    }

    private fun drawPrism(canvas: Canvas, cx: Float, base: Float, w: Float, h: Float, stroke: Paint) {
        val tip = base - h
        val p = Path().apply {
            moveTo(cx - w, base)
            lineTo(cx, tip)
            lineTo(cx + w, base)
            close()
        }
        canvas.drawPath(p, stroke)
        canvas.drawLine(cx, tip, cx, base, stroke)
    }

    fun drawStoneGargoyle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Bat wings
        val wingL = Path().apply {
            moveTo(cx - 80f * f, 440f * f)
            lineTo(cx - 320f * f, 300f * f)
            cubicTo(cx - 240f * f, 440f * f, cx - 200f * f, 560f * f, cx - 80f * f, 600f * f)
            close()
        }
        val wingR = Path().apply {
            moveTo(cx + 80f * f, 440f * f)
            lineTo(cx + 320f * f, 300f * f)
            cubicTo(cx + 240f * f, 440f * f, cx + 200f * f, 560f * f, cx + 80f * f, 600f * f)
            close()
        }
        canvas.drawPath(wingL, stroke); canvas.drawPath(wingR, stroke)
        // Gargoyle cute head with horns
        canvas.drawCircle(cx, 400f * f, 120f * f, stroke)
        // Small horns
        val hornL = Path().apply { moveTo(cx - 80f * f, 300f * f); lineTo(cx - 100f * f, 200f * f); lineTo(cx - 40f * f, 280f * f); close() }
        val hornR = Path().apply { moveTo(cx + 80f * f, 300f * f); lineTo(cx + 100f * f, 200f * f); lineTo(cx + 40f * f, 280f * f); close() }
        canvas.drawPath(hornL, stroke); canvas.drawPath(hornR, stroke)
        drawCuteEye(canvas, cx - 45f * f, 380f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, 380f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        // Stone pedestal
        canvas.drawRect(cx - 160f * f, 720f * f, cx + 160f * f, 860f * f, stroke)
    }

    fun drawTreeGuardian(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Great oak trunk body
        val trunk = Path().apply {
            moveTo(cx - 160f * f, 860f * f)
            cubicTo(cx - 120f * f, 640f * f, cx - 140f * f, 480f * f, cx - 180f * f, 340f * f)
            cubicTo(cx, 320f * f, cx, 320f * f, cx + 180f * f, 340f * f)
            cubicTo(cx + 140f * f, 480f * f, cx + 120f * f, 640f * f, cx + 160f * f, 860f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)
        // Friendly wise eyes on trunk
        drawCuteEye(canvas, cx - 55f * f, 500f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 55f * f, 500f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Mossy smile
        val smile = Path().apply { moveTo(cx - 40f * f, 580f * f); quadTo(cx, 620f * f, cx + 40f * f, 580f * f) }
        canvas.drawPath(smile, stroke)
        // Giant leafy canopy crown
        canvas.drawCircle(cx, 260f * f, 180f * f, stroke)
        canvas.drawCircle(cx - 160f * f, 300f * f, 120f * f, stroke)
        canvas.drawCircle(cx + 160f * f, 300f * f, 120f * f, stroke)
    }

    fun drawFlyingCarpet(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Wavy carpet surface
        val carpet = Path().apply {
            moveTo(cx - 320f * f, cy - 60f * f)
            cubicTo(cx - 160f * f, cy - 180f * f, cx + 160f * f, cy + 60f * f, cx + 320f * f, cy - 60f * f)
            lineTo(cx + 280f * f, cy + 120f * f)
            cubicTo(cx + 120f * f, cy + 240f * f, cx - 200f * f, cy, cx - 360f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(carpet, stroke)
        // Tassels on edges
        for (i in 0..4) {
            val tx = (cx - 340f + i * 20f) * f
            canvas.drawLine(tx, cy + 80f * f, tx - 15f * f, cy + 140f * f, fine)
        }
        // Arabesque pattern in center
        canvas.drawCircle(cx, cy + 10f * f, 60f * f, fine)
        drawStar(canvas, cx, cy + 10f * f, 35f * f, stroke)
        // Puffy clouds below carpet
        drawSparkle(canvas, cx - 220f * f, cy - 220f * f, 25f * f, stroke)
        drawSparkle(canvas, cx + 220f * f, cy - 200f * f, 25f * f, stroke)
    }

    fun drawTreasureIsland(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Island mound
        val island = Path().apply {
            moveTo(140f * f, 760f * f)
            cubicTo(260f * f, 620f * f, 760f * f, 620f * f, 880f * f, 760f * f)
            close()
        }
        canvas.drawPath(island, stroke)
        // Palm tree on island
        val trunk = Path().apply {
            moveTo(cx, 700f * f)
            cubicTo(cx - 40f * f, 540f * f, cx - 60f * f, 420f * f, cx - 20f * f, 340f * f)
            lineTo(cx + 20f * f, 340f * f)
            cubicTo(cx - 20f * f, 420f * f, cx, 540f * f, cx + 40f * f, 700f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)
        // Palm fronds
        for (i in 0..4) {
            val a = i * Math.PI / 4 + Math.PI / 8
            val fx = (cx + Math.cos(a) * 180f * f).toFloat()
            val fy = (340f * f - Math.sin(a) * 140f * f).toFloat()
            val leaf = Path().apply {
                moveTo(cx, 340f * f)
                quadTo((cx + fx) / 2, fy - 40f * f, fx, fy)
                quadTo((cx + fx) / 2, fy + 40f * f, cx, 340f * f)
            }
            canvas.drawPath(leaf, stroke)
        }
        // X marks the spot
        canvas.drawLine(cx + 140f * f, 680f * f, cx + 200f * f, 740f * f, stroke)
        canvas.drawLine(cx + 200f * f, 680f * f, cx + 140f * f, 740f * f, stroke)
        // Ocean ripples
        canvas.drawLine(100f * f, 780f * f, 924f * f, 780f * f, fine)
    }

    fun drawGenieLamp(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 600f * f
        // Lamp body
        val lamp = Path().apply {
            moveTo(cx - 160f * f, cy)
            cubicTo(cx - 200f * f, cy + 100f * f, cx + 100f * f, cy + 120f * f, cx + 180f * f, cy) // Belly
            lineTo(cx + 320f * f, cy - 80f * f) // Long spout
            lineTo(cx + 300f * f, cy - 120f * f)
            cubicTo(cx + 180f * f, cy - 60f * f, cx + 60f * f, cy - 40f * f, cx - 40f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(lamp, stroke)
        // Lamp handle (curved on left)
        val handle = Path().apply {
            moveTo(cx - 140f * f, cy - 20f * f)
            cubicTo(cx - 280f * f, cy - 120f * f, cx - 280f * f, cy + 60f * f, cx - 140f * f, cy + 40f * f)
        }
        canvas.drawPath(handle, stroke)
        // Lamp base
        canvas.drawOval(RectF(cx - 100f * f, cy + 90f * f, cx + 60f * f, cy + 140f * f), stroke)
        // Magic genie smoke billowing out of spout
        val smoke = Path().apply {
            moveTo(cx + 310f * f, cy - 100f * f)
            cubicTo(cx + 340f * f, cy - 240f * f, cx + 160f * f, cy - 280f * f, cx + 220f * f, cy - 400f * f)
            cubicTo(cx + 260f * f, cy - 480f * f, cx + 120f * f, cy - 540f * f, cx + 60f * f, cy - 460f * f)
        }
        canvas.drawPath(smoke, stroke)
        drawSparkle(canvas, cx + 120f * f, cy - 380f * f, 25f * f, stroke)
        drawSparkle(canvas, cx + 260f * f, cy - 460f * f, 20f * f, stroke)
    }
}
