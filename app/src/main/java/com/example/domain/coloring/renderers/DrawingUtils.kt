package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

object DrawingUtils {

    fun drawCuteEye(
        canvas: Canvas,
        cx: Float,
        cy: Float,
        r: Float,
        stroke: Paint,
        eyeFill: Paint,
        eyeHighlight: Paint
    ) {
        canvas.drawCircle(cx, cy, r, stroke)
        canvas.drawCircle(cx + r * 0.1f, cy, r * 0.72f, eyeFill)
        canvas.drawCircle(cx + r * 0.35f, cy - r * 0.25f, r * 0.28f, eyeHighlight)
    }

    fun drawWinkingEye(
        canvas: Canvas,
        cx: Float,
        cy: Float,
        r: Float,
        stroke: Paint
    ) {
        val path = Path().apply {
            moveTo(cx - r, cy)
            quadTo(cx, cy - r * 0.9f, cx + r, cy)
        }
        canvas.drawPath(path, stroke)
    }

    fun drawHappySmile(
        canvas: Canvas,
        cx: Float,
        cy: Float,
        width: Float,
        stroke: Paint
    ) {
        val mouth = Path().apply {
            moveTo(cx - width / 2f, cy)
            quadTo(cx, cy + width * 0.6f, cx + width / 2f, cy)
        }
        canvas.drawPath(mouth, stroke)
    }

    fun drawWheel(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint) {
        canvas.drawCircle(cx, cy, r, stroke)
        canvas.drawCircle(cx, cy, r * 0.55f, stroke)
        canvas.drawCircle(cx, cy, r * 0.2f, stroke)
    }

    fun drawStar(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint) {
        val path = Path()
        val points = 5
        val innerR = r * 0.45f
        for (i in 0 until (points * 2)) {
            val angle = i * Math.PI / points - Math.PI / 2
            val currR = if (i % 2 == 0) r else innerR
            val x = (cx + Math.cos(angle) * currR).toFloat()
            val y = (cy + Math.sin(angle) * currR).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        canvas.drawPath(path, stroke)
    }

    fun drawFlower(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint) {
        // Center
        canvas.drawCircle(cx, cy, r * 0.45f, stroke)
        // 5 petals
        for (i in 0 until 5) {
            val angle = i * (Math.PI * 2 / 5)
            val px = (cx + Math.cos(angle) * (r * 0.8f)).toFloat()
            val py = (cy + Math.sin(angle) * (r * 0.8f)).toFloat()
            canvas.drawCircle(px, py, r * 0.45f, stroke)
        }
    }

    fun drawCloud(canvas: Canvas, cx: Float, cy: Float, width: Float, height: Float, stroke: Paint) {
        val path = Path().apply {
            val r = height / 2f
            moveTo(cx - width / 2f + r, cy + r)
            lineTo(cx + width / 2f - r, cy + r)
            cubicTo(cx + width / 2f, cy + r, cx + width / 2f + r, cy, cx + width / 2f - r * 0.2f, cy - r * 0.3f)
            cubicTo(cx + width / 2f, cy - r, cx + width * 0.2f, cy - r * 1.3f, cx, cy - r)
            cubicTo(cx - width * 0.2f, cy - r * 1.3f, cx - width / 2f, cy - r, cx - width / 2f + r * 0.2f, cy - r * 0.3f)
            cubicTo(cx - width / 2f - r, cy, cx - width / 2f, cy + r, cx - width / 2f + r, cy + r)
            close()
        }
        canvas.drawPath(path, stroke)
    }

    fun drawGroundGrass(canvas: Canvas, s: Int, stroke: Paint) {
        val f = s / 1024f
        val grassPath = Path().apply {
            moveTo(60f * f, 880f * f)
            var x = 60f * f
            while (x < 960f * f) {
                lineTo(x + 20f * f, 840f * f)
                lineTo(x + 40f * f, 880f * f)
                x += 40f * f
            }
            lineTo(960f * f, 880f * f)
        }
        canvas.drawPath(grassPath, stroke)
    }

    fun drawGroundWaves(canvas: Canvas, s: Int, stroke: Paint) {
        val f = s / 1024f
        val wavePath = Path().apply {
            moveTo(60f * f, 860f * f)
            var x = 60f * f
            val w = 150f * f
            while (x < 960f * f) {
                quadTo(x + w * 0.25f, 820f * f, x + w * 0.5f, 860f * f)
                quadTo(x + w * 0.75f, 900f * f, x + w, 860f * f)
                x += w
            }
        }
        canvas.drawPath(wavePath, stroke)
    }

    fun drawHeart(canvas: Canvas, cx: Float, cy: Float, size: Float, stroke: Paint) {
        val path = Path().apply {
            moveTo(cx, cy + size * 0.4f)
            cubicTo(cx - size * 0.6f, cy, cx - size * 0.6f, cy - size * 0.6f, cx, cy - size * 0.2f)
            cubicTo(cx + size * 0.6f, cy - size * 0.6f, cx + size * 0.6f, cy, cx, cy + size * 0.4f)
            close()
        }
        canvas.drawPath(path, stroke)
    }

    fun drawBubble(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint, fine: Paint) {
        canvas.drawCircle(cx, cy, r, stroke)
        val arcRect = RectF(cx - r * 0.7f, cy - r * 0.7f, cx + r * 0.3f, cy + r * 0.3f)
        canvas.drawArc(arcRect, 190f, 80f, false, fine)
    }

    fun drawSparkle(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint) {
        val path = Path().apply {
            moveTo(cx, cy - r)
            quadTo(cx, cy, cx + r, cy)
            quadTo(cx, cy, cx, cy + r)
            quadTo(cx, cy, cx - r, cy)
            quadTo(cx, cy, cx, cy - r)
            close()
        }
        canvas.drawPath(path, stroke)
    }
}
