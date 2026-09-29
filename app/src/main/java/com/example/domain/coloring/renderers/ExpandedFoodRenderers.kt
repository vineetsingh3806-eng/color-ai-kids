package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawHappySmile

object ExpandedFoodRenderers {

    fun drawCrunchyTaco(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Folded crispy taco shell (U-shape)
        val taco = Path().apply {
            moveTo(cx - 300f * f, cy + 40f * f)
            cubicTo(cx - 300f * f, cy + 280f * f, cx + 300f * f, cy + 280f * f, cx + 300f * f, cy + 40f * f)
            lineTo(cx + 260f * f, cy)
            cubicTo(cx + 240f * f, cy + 220f * f, cx - 240f * f, cy + 220f * f, cx - 260f * f, cy)
            close()
        }
        canvas.drawPath(taco, stroke)
        // Lettuce ruffles & fillings bursting from taco top
        for (i in -3..3) {
            val lx = cx + i * 75f * f
            canvas.drawCircle(lx, cy - 20f * f, 40f * f, stroke)
        }
        // Tomato slices
        canvas.drawCircle(cx - 100f * f, cy - 40f * f, 25f * f, stroke)
        canvas.drawCircle(cx + 100f * f, cy - 40f * f, 25f * f, stroke)
        // Cute smiling face on taco shell
        drawCuteEye(canvas, cx - 60f * f, cy + 120f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy + 120f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, cy + 160f * f, 50f * f, stroke)
    }

    fun drawHotDog(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Fluffy hot dog bun
        canvas.drawRoundRect(RectF(cx - 320f * f, cy - 100f * f, cx + 320f * f, cy + 160f * f), 80f * f, 80f * f, stroke)
        // Frankfurter sausage nestled in bun
        canvas.drawRoundRect(RectF(cx - 360f * f, cy - 60f * f, cx + 360f * f, cy + 60f * f), 60f * f, 60f * f, stroke)
        // Squiggly yellow mustard line
        val mustard = Path().apply {
            moveTo(cx - 300f * f, cy)
            var x = cx - 300f * f
            while (x < cx + 300f * f) {
                quadTo(x + 25f * f, cy - 35f * f, x + 50f * f, cy)
                quadTo(x + 75f * f, cy + 35f * f, x + 100f * f, cy)
                x += 100f * f
            }
        }
        canvas.drawPath(mustard, stroke)
    }

    fun drawFrenchFries(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Red fry carton cup
        val carton = Path().apply {
            moveTo(cx - 160f * f, 860f * f)
            lineTo(cx - 220f * f, 520f * f)
            cubicTo(cx - 100f * f, 560f * f, cx + 100f * f, 560f * f, cx + 220f * f, 520f * f)
            lineTo(cx + 160f * f, 860f * f)
            close()
        }
        canvas.drawPath(carton, stroke)
        // Golden fries sticking out of carton
        val fries = listOf(
            RectF(cx - 160f * f, 300f * f, cx - 120f * f, 560f * f),
            RectF(cx - 100f * f, 220f * f, cx - 60f * f, 560f * f),
            RectF(cx - 40f * f, 180f * f, cx, 560f * f),
            RectF(cx + 20f * f, 240f * f, cx + 60f * f, 560f * f),
            RectF(cx + 80f * f, 200f * f, cx + 120f * f, 560f * f),
            RectF(cx + 140f * f, 320f * f, cx + 180f * f, 560f * f)
        )
        for (rect in fries) {
            canvas.drawRoundRect(rect, 10f * f, 10f * f, stroke)
        }
        // Cute face on fry box
        drawCuteEye(canvas, cx - 60f * f, 660f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, 660f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, 720f * f, 50f * f, stroke)
    }

    fun drawMakiSushi(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Cylindrical sushi roll
        // Outer dark seaweed nori ring
        canvas.drawCircle(cx, cy, 220f * f, stroke)
        // Inner white rice ring
        canvas.drawCircle(cx, cy, 180f * f, stroke)
        // Center fillings (Salmon square, cucumber round, avocado slice)
        canvas.drawRoundRect(RectF(cx - 60f * f, cy - 60f * f, cx + 20f * f, cy + 20f * f), 15f * f, 15f * f, stroke)
        canvas.drawCircle(cx + 50f * f, cy + 40f * f, 30f * f, stroke)
        // Cute face on nori
        drawCuteEye(canvas, cx - 80f * f, cy + 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 80f * f, cy + 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, cy + 150f * f, 40f * f, stroke)
    }

    fun drawStrawberry(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Strawberry heart/triangle shape
        val berry = Path().apply {
            moveTo(cx, cy + 300f * f) // Pointed tip
            cubicTo(cx - 240f * f, cy + 140f * f, cx - 240f * f, cy - 160f * f, cx, cy - 160f * f)
            cubicTo(cx + 240f * f, cy - 160f * f, cx + 240f * f, cy + 140f * f, cx, cy + 300f * f)
            close()
        }
        canvas.drawPath(berry, stroke)
        // Green calyx leaves on top
        for (i in -2..2) {
            val a = (i * 30).toDouble() * Math.PI / 180
            val lx = (cx + Math.sin(a) * 140f * f).toFloat()
            val ly = (cy - 160f * f - Math.cos(a) * 60f * f).toFloat()
            canvas.drawLine(cx, cy - 160f * f, lx, ly, stroke)
        }
        // Stem
        canvas.drawRoundRect(RectF(cx - 15f * f, cy - 260f * f, cx + 15f * f, cy - 160f * f), 6f * f, 6f * f, stroke)
        // Tiny seed dots
        for (row in -2..2) {
            for (col in -2..2) {
                val sx = (cx + col * 60f) * f
                val sy = (cy + row * 60f) * f
                canvas.drawCircle(sx, sy, 8f * f, fine)
            }
        }
    }

    fun drawHoneycrispApple(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Apple body with dimple on top and bottom
        val apple = Path().apply {
            moveTo(cx, cy - 180f * f)
            cubicTo(cx + 140f * f, cy - 240f * f, cx + 240f * f, cy - 60f * f, cx + 220f * f, cy + 120f * f)
            cubicTo(cx + 200f * f, cy + 280f * f, cx + 60f * f, cy + 280f * f, cx, cy + 240f * f)
            cubicTo(cx - 60f * f, cy + 280f * f, cx - 200f * f, cy + 280f * f, cx - 220f * f, cy + 120f * f)
            cubicTo(cx - 240f * f, cy - 60f * f, cx - 140f * f, cy - 240f * f, cx, cy - 180f * f)
            close()
        }
        canvas.drawPath(apple, stroke)
        // Stem
        canvas.drawLine(cx, cy - 180f * f, cx + 20f * f, cy - 300f * f, stroke)
        // Green leaf
        val leaf = Path().apply {
            moveTo(cx + 20f * f, cy - 260f * f)
            cubicTo(cx + 120f * f, cy - 320f * f, cx + 140f * f, cy - 220f * f, cx + 20f * f, cy - 260f * f)
        }
        canvas.drawPath(leaf, stroke)
        // Cute face
        drawCuteEye(canvas, cx - 60f * f, cy, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy, 18f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, cy + 50f * f, 50f * f, stroke)
    }

    fun drawTwinCherries(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Two plump round cherries
        canvas.drawCircle(cx - 120f * f, 660f * f, 120f * f, stroke)
        canvas.drawCircle(cx + 120f * f, 660f * f, 120f * f, stroke)
        // Left cherry eyes & smile
        drawCuteEye(canvas, cx - 150f * f, 640f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx - 90f * f, 640f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        // Right cherry eyes & smile
        drawCuteEye(canvas, cx + 90f * f, 640f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 150f * f, 640f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        // Stems curving together to top joint
        val stemL = Path().apply { moveTo(cx - 120f * f, 540f * f); cubicTo(cx - 100f * f, 380f * f, cx - 20f * f, 240f * f, cx, 220f * f) }
        val stemR = Path().apply { moveTo(cx + 120f * f, 540f * f); cubicTo(cx + 100f * f, 380f * f, cx + 20f * f, 240f * f, cx, 220f * f) }
        canvas.drawPath(stemL, stroke); canvas.drawPath(stemR, stroke)
        // Joint leaf at top
        val leaf = Path().apply { moveTo(cx, 220f * f); cubicTo(cx + 100f * f, 180f * f, cx + 120f * f, 260f * f, cx, 220f * f) }
        canvas.drawPath(leaf, stroke)
    }

    fun drawRainbowLollipop(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // Stick
        canvas.drawRoundRect(RectF(cx - 15f * f, cy + 180f * f, cx + 15f * f, 880f * f), 10f * f, 10f * f, stroke)
        // Giant spiral candy round
        canvas.drawCircle(cx, cy, 200f * f, stroke)
        // Concentric spiral swirls
        for (i in 1..4) {
            canvas.drawCircle(cx, cy, (i * 45f) * f, fine)
        }
        // Ribbon bow wrap on stick
        val bowL = Path().apply { moveTo(cx, cy + 200f * f); lineTo(cx - 80f * f, cy + 160f * f); lineTo(cx - 80f * f, cy + 240f * f); close() }
        val bowR = Path().apply { moveTo(cx, cy + 200f * f); lineTo(cx + 80f * f, cy + 160f * f); lineTo(cx + 80f * f, cy + 240f * f); close() }
        canvas.drawPath(bowL, stroke); canvas.drawPath(bowR, stroke)
        canvas.drawCircle(cx, cy + 200f * f, 20f * f, stroke)
    }

    fun drawSoftPretzel(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Classic pretzel knot shape
        // Outer loop
        canvas.drawRoundRect(RectF(cx - 260f * f, cy - 200f * f, cx + 260f * f, cy + 200f * f), 120f * f, 120f * f, stroke)
        // Inner two bottom holes
        canvas.drawCircle(cx - 110f * f, cy + 40f * f, 60f * f, stroke)
        canvas.drawCircle(cx + 110f * f, cy + 40f * f, 60f * f, stroke)
        // Upper center hole
        canvas.drawCircle(cx, cy - 70f * f, 50f * f, stroke)
        // Salt crystals sprinkled around
        for (i in 0..8) {
            val a = i * Math.PI / 4.5
            val sx = (cx + Math.cos(a) * 200f * f).toFloat()
            val sy = (cy + Math.sin(a) * 150f * f).toFloat()
            canvas.drawRect(sx - 8f * f, sy - 8f * f, sx + 8f * f, sy + 8f * f, fine)
        }
    }

    fun drawSubmarineSandwich(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f
        // Top bread roll
        canvas.drawRoundRect(RectF(cx - 320f * f, cy - 160f * f, cx + 320f * f, cy - 40f * f), 60f * f, 60f * f, stroke)
        // Diagonal cuts on top bread
        for (i in -2..2) {
            val x = (cx + i * 90f) * f
            canvas.drawLine(x - 20f * f, cy - 140f * f, x + 20f * f, cy - 60f * f, fine)
        }
        // Fillings (cheese triangles, wavy lettuce, tomato discs, ham)
        for (i in -3..3) {
            val lx = (cx + i * 80f) * f
            canvas.drawCircle(lx, cy, 35f * f, stroke)
        }
        // Bottom bread roll
        canvas.drawRoundRect(RectF(cx - 320f * f, cy + 40f * f, cx + 320f * f, cy + 160f * f), 50f * f, 50f * f, stroke)
    }
}
