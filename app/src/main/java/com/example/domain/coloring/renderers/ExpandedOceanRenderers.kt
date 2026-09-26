package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawBubble
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye

object ExpandedOceanRenderers {

    fun drawHammerheadShark(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // T-shaped wide hammerhead
        canvas.drawRoundRect(RectF(cx - 260f * f, cy - 240f * f, cx + 260f * f, cy - 140f * f), 40f * f, 40f * f, stroke)
        // Eyes on extreme tips
        drawCuteEye(canvas, cx - 220f * f, cy - 190f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 220f * f, cy - 190f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        // Body narrowing back
        val body = Path().apply {
            moveTo(cx - 100f * f, cy - 140f * f)
            cubicTo(cx - 120f * f, cy + 60f * f, cx - 40f * f, cy + 240f * f, cx, cy + 320f * f) // Tail fin
            lineTo(cx - 60f * f, cy + 400f * f)
            lineTo(cx, cy + 340f * f)
            lineTo(cx + 60f * f, cy + 400f * f)
            cubicTo(cx + 40f * f, cy + 240f * f, cx + 120f * f, cy + 60f * f, cx + 100f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Pectoral fins
        val finL = Path().apply { moveTo(cx - 100f * f, cy); lineTo(cx - 260f * f, cy + 120f * f); lineTo(cx - 80f * f, cy + 120f * f); close() }
        val finR = Path().apply { moveTo(cx + 100f * f, cy); lineTo(cx + 260f * f, cy + 120f * f); lineTo(cx + 80f * f, cy + 120f * f); close() }
        canvas.drawPath(finL, stroke); canvas.drawPath(finR, stroke)
        drawBubble(canvas, 200f * f, 240f * f, 25f * f, stroke, fine)
    }

    fun drawMantaRay(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Broad kite-like wings
        val ray = Path().apply {
            moveTo(cx, cy - 180f * f)
            cubicTo(cx + 160f * f, cy - 160f * f, cx + 380f * f, cy - 40f * f, cx + 420f * f, cy + 40f * f) // Right wing tip
            cubicTo(cx + 340f * f, cy + 120f * f, cx + 160f * f, cy + 160f * f, cx, cy + 180f * f) // Tail base
            cubicTo(cx - 160f * f, cy + 160f * f, cx - 340f * f, cy + 120f * f, cx - 420f * f, cy + 40f * f) // Left wing tip
            cubicTo(cx - 380f * f, cy - 40f * f, cx - 160f * f, cy - 160f * f, cx, cy - 180f * f)
            close()
        }
        canvas.drawPath(ray, stroke)
        // Cephalic horn flaps at mouth
        canvas.drawRoundRect(RectF(cx - 80f * f, cy - 220f * f, cx - 30f * f, cy - 160f * f), 15f * f, 15f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 30f * f, cy - 220f * f, cx + 80f * f, cy - 160f * f), 15f * f, 15f * f, stroke)
        // Eyes
        drawCuteEye(canvas, cx - 90f * f, cy - 130f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 90f * f, cy - 130f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        // Long slender whip tail
        val tail = Path().apply {
            moveTo(cx, cy + 180f * f)
            cubicTo(cx + 40f * f, cy + 280f * f, cx - 30f * f, cy + 360f * f, cx, cy + 460f * f)
        }
        canvas.drawPath(tail, stroke)
    }

    fun drawArcticWalrus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Big round walrus body
        canvas.drawOval(RectF(cx - 240f * f, 420f * f, cx + 240f * f, 840f * f), stroke)
        // Head
        canvas.drawCircle(cx, 360f * f, 140f * f, stroke)
        drawCuteEye(canvas, cx - 60f * f, 320f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, 320f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        // Whisker muzzle pads
        canvas.drawCircle(cx - 45f * f, 400f * f, 45f * f, stroke)
        canvas.drawCircle(cx + 45f * f, 400f * f, 45f * f, stroke)
        canvas.drawCircle(cx, 370f * f, 15f * f, eyeFill) // Nose
        // Long ivory tusks
        val tuskL = Path().apply { moveTo(cx - 50f * f, 430f * f); lineTo(cx - 60f * f, 580f * f); lineTo(cx - 30f * f, 430f * f); close() }
        val tuskR = Path().apply { moveTo(cx + 30f * f, 430f * f); lineTo(cx + 60f * f, 580f * f); lineTo(cx + 50f * f, 430f * f); close() }
        canvas.drawPath(tuskL, stroke); canvas.drawPath(tuskR, stroke)
        // Ice floe
        canvas.drawRoundRect(RectF(160f * f, 820f * f, 860f * f, 880f * f), 20f * f, 20f * f, stroke)
    }

    fun drawReefLobster(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f
        // Segmented abdomen & tail fan
        for (i in 0..4) {
            val y = (cy + 20f + i * 50f) * f
            canvas.drawRoundRect(RectF(cx - (80f - i * 8f) * f, y, cx + (80f - i * 8f) * f, y + 45f * f), 15f * f, 15f * f, stroke)
        }
        // Tail fan
        val fan = Path().apply {
            moveTo(cx, cy + 260f * f)
            lineTo(cx - 80f * f, cy + 340f * f)
            lineTo(cx + 80f * f, cy + 340f * f)
            close()
        }
        canvas.drawPath(fan, stroke)
        // Carapace head
        canvas.drawRoundRect(RectF(cx - 100f * f, cy - 140f * f, cx + 100f * f, cy + 20f * f), 40f * f, 40f * f, stroke)
        drawCuteEye(canvas, cx - 40f * f, cy - 100f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, cy - 100f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        // Big claws
        val clawL = Path().apply { moveTo(cx - 80f * f, cy - 60f * f); lineTo(cx - 240f * f, cy - 160f * f); lineTo(cx - 180f * f, cy - 260f * f); close() }
        val clawR = Path().apply { moveTo(cx + 80f * f, cy - 60f * f); lineTo(cx + 240f * f, cy - 160f * f); lineTo(cx + 180f * f, cy - 260f * f); close() }
        canvas.drawPath(clawL, stroke); canvas.drawPath(clawR, stroke)
    }

    fun drawCoralReef(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Sea floor
        canvas.drawLine(80f * f, 860f * f, 944f * f, 860f * f, stroke)
        // Branching staghorn coral
        val coral = Path().apply {
            moveTo(240f * f, 860f * f)
            lineTo(260f * f, 620f * f)
            lineTo(180f * f, 500f * f)
            lineTo(220f * f, 480f * f)
            lineTo(280f * f, 580f * f)
            lineTo(340f * f, 460f * f)
            lineTo(380f * f, 480f * f)
            lineTo(320f * f, 640f * f)
            lineTo(340f * f, 860f * f)
            close()
        }
        canvas.drawPath(coral, stroke)
        // Brain coral dome
        canvas.drawArc(RectF(480f * f, 660f * f, 800f * f, 860f * f), 180f, 180f, true, stroke)
        // Little fish swimming above
        val fish = Path().apply {
            moveTo(600f * f, 400f * f)
            cubicTo(660f * f, 360f * f, 720f * f, 380f * f, 760f * f, 400f * f)
            lineTo(800f * f, 360f * f); lineTo(800f * f, 440f * f); lineTo(760f * f, 400f * f)
            cubicTo(720f * f, 420f * f, 660f * f, 440f * f, 600f * f, 400f * f)
            close()
        }
        canvas.drawPath(fish, stroke)
        drawCuteEye(canvas, 640f * f, 395f * f, 8f * f, stroke, eyeFill, eyeHighlight)
    }

    fun drawSeaOtter(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Floating on water (horizontal body)
        canvas.drawRoundRect(RectF(cx - 240f * f, cy - 80f * f, cx + 240f * f, cy + 120f * f), 80f * f, 80f * f, stroke)
        // Otter head on left
        canvas.drawCircle(cx - 160f * f, cy, 90f * f, stroke)
        // Small ears
        canvas.drawCircle(cx - 230f * f, cy - 70f * f, 20f * f, stroke)
        canvas.drawCircle(cx - 110f * f, cy - 70f * f, 20f * f, stroke)
        drawCuteEye(canvas, cx - 180f * f, cy - 15f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx - 130f * f, cy - 15f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(cx - 155f * f, cy + 15f * f, 10f * f, eyeFill)
        // Paws holding a clam on its belly
        canvas.drawCircle(cx + 40f * f, cy + 10f * f, 35f * f, stroke)
        // Tail floating on right
        val tail = Path().apply { moveTo(cx + 220f * f, cy + 20f * f); lineTo(cx + 340f * f, cy + 60f * f); lineTo(cx + 220f * f, cy + 80f * f) }
        canvas.drawPath(tail, stroke)
        // Water surface
        canvas.drawLine(100f * f, cy + 80f * f, 924f * f, cy + 80f * f, fine)
    }

    fun drawSandyStingray(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Diamond shaped ray body
        val ray = Path().apply {
            moveTo(cx, cy - 180f * f) // Snout
            lineTo(cx + 280f * f, cy) // Right wing
            lineTo(cx, cy + 180f * f) // Tail start
            lineTo(cx - 280f * f, cy) // Left wing
            close()
        }
        canvas.drawPath(ray, stroke)
        drawCuteEye(canvas, cx - 40f * f, cy - 60f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, cy - 60f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        // Tail with stinger barb
        val tail = Path().apply {
            moveTo(cx, cy + 180f * f)
            cubicTo(cx - 20f * f, cy + 280f * f, cx + 30f * f, cy + 340f * f, cx, cy + 420f * f)
        }
        canvas.drawPath(tail, stroke)
        // Sandy dots on bottom
        for (i in 0..5) {
            canvas.drawCircle((200f + i * 120f) * f, 840f * f, 10f * f, fine)
        }
    }

    fun drawSwordfish(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cy = 512f * f
        // Long sharp sword bill
        canvas.drawLine(720f * f, cy, 960f * f, cy, stroke)
        // Sleek torpedo body
        val body = Path().apply {
            moveTo(720f * f, cy)
            cubicTo(640f * f, cy - 100f * f, 440f * f, cy - 100f * f, 240f * f, cy - 20f * f)
            // Crescent tail
            lineTo(140f * f, cy - 120f * f); lineTo(180f * f, cy); lineTo(140f * f, cy + 120f * f)
            lineTo(240f * f, cy + 20f * f)
            cubicTo(440f * f, cy + 100f * f, 640f * f, cy + 100f * f, 720f * f, cy)
            close()
        }
        canvas.drawPath(body, stroke)
        drawCuteEye(canvas, 660f * f, cy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        // Tall dorsal sail fin
        val sail = Path().apply {
            moveTo(480f * f, cy - 90f * f)
            cubicTo(500f * f, cy - 240f * f, 560f * f, cy - 280f * f, 580f * f, cy - 260f * f)
            lineTo(560f * f, cy - 80f * f)
            close()
        }
        canvas.drawPath(sail, stroke)
    }

    fun drawGiantSquid(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Torpedo mantle mantle with fin flaps
        val mantle = Path().apply {
            moveTo(cx, 160f * f)
            lineTo(cx + 80f * f, 220f * f); lineTo(cx + 60f * f, 300f * f)
            cubicTo(cx + 120f * f, 380f * f, cx + 100f * f, 500f * f, cx + 80f * f, 540f * f)
            lineTo(cx - 80f * f, 540f * f)
            cubicTo(cx - 100f * f, 500f * f, cx - 120f * f, 380f * f, cx - 60f * f, 300f * f)
            lineTo(cx - 80f * f, 220f * f)
            close()
        }
        canvas.drawPath(mantle, stroke)
        // Huge intelligent eyes
        drawCuteEye(canvas, cx - 50f * f, 500f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 50f * f, 500f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        // Wavy tentacles hanging down
        for (i in -3..3) {
            val tx = cx + i * 25f * f
            val tentacle = Path().apply {
                moveTo(tx, 540f * f)
                cubicTo(tx + 40f * f, 660f * f, tx - 40f * f, 760f * f, tx + 10f * f, 880f * f)
            }
            canvas.drawPath(tentacle, stroke)
        }
    }

    fun drawPearlClam(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f
        // Lower shell half
        val lower = Path().apply {
            moveTo(cx - 240f * f, cy)
            cubicTo(cx - 220f * f, cy + 240f * f, cx + 220f * f, cy + 240f * f, cx + 240f * f, cy)
            close()
        }
        canvas.drawPath(lower, stroke)
        // Upper shell half tilted back
        val upper = Path().apply {
            moveTo(cx - 240f * f, cy)
            cubicTo(cx - 220f * f, cy - 240f * f, cx + 220f * f, cy - 240f * f, cx + 240f * f, cy)
            close()
        }
        canvas.drawPath(upper, stroke)
        // Ridges on shell
        for (i in -2..2) {
            val a = cx + i * 70f * f
            canvas.drawLine(cx, cy - 200f * f, a, cy, fine)
        }
        // Giant glowing round pearl in center
        canvas.drawCircle(cx, cy + 20f * f, 70f * f, stroke)
        canvas.drawCircle(cx - 20f * f, cy, 20f * f, eyeHighlight) // Luster shine
        drawBubble(canvas, cx + 180f * f, cy - 200f * f, 25f * f, stroke, fine)
    }
}
