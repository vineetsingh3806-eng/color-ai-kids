package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawHappySmile

object ExpandedAnimalRenderers {

    fun drawTiger(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Round tiger head
        canvas.drawCircle(cx, cy, 180f * f, stroke)
        // Round ears
        canvas.drawCircle(cx - 150f * f, cy - 140f * f, 50f * f, stroke)
        canvas.drawCircle(cx - 150f * f, cy - 140f * f, 30f * f, fine)
        canvas.drawCircle(cx + 150f * f, cy - 140f * f, 50f * f, stroke)
        canvas.drawCircle(cx + 150f * f, cy - 140f * f, 30f * f, fine)
        // Eyes
        drawCuteEye(canvas, cx - 70f * f, cy - 30f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 30f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Snout & Nose
        val nose = Path().apply {
            moveTo(cx - 30f * f, cy + 40f * f)
            lineTo(cx + 30f * f, cy + 40f * f)
            lineTo(cx, cy + 70f * f)
            close()
        }
        canvas.drawPath(nose, stroke)
        drawHappySmile(canvas, cx, cy + 80f * f, 60f * f, stroke)
        // Tiger stripes on cheeks and forehead
        val stripe1 = Path().apply { moveTo(cx, cy - 170f * f); lineTo(cx, cy - 110f * f) }
        val stripe2 = Path().apply { moveTo(cx - 40f * f, cy - 160f * f); lineTo(cx - 20f * f, cy - 110f * f) }
        val stripe3 = Path().apply { moveTo(cx + 40f * f, cy - 160f * f); lineTo(cx + 20f * f, cy - 110f * f) }
        canvas.drawPath(stripe1, stroke); canvas.drawPath(stripe2, stroke); canvas.drawPath(stripe3, stroke)
        // Body & Paws
        canvas.drawRoundRect(RectF(cx - 140f * f, cy + 150f * f, cx + 140f * f, 860f * f), 60f * f, 60f * f, stroke)
        // Front paws
        canvas.drawCircle(cx - 70f * f, 840f * f, 45f * f, stroke)
        canvas.drawCircle(cx + 70f * f, 840f * f, 45f * f, stroke)
    }

    fun drawFox(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Triangular fox head with cheeks
        val head = Path().apply {
            moveTo(cx, 260f * f)
            lineTo(cx - 160f * f, 200f * f) // Left ear tip
            lineTo(cx - 100f * f, 360f * f)
            cubicTo(cx - 220f * f, 440f * f, cx - 180f * f, 540f * f, cx, 600f * f) // Pointy snout
            cubicTo(cx + 180f * f, 540f * f, cx + 220f * f, 440f * f, cx + 100f * f, 360f * f)
            lineTo(cx + 160f * f, 200f * f) // Right ear tip
            close()
        }
        canvas.drawPath(head, stroke)
        // Eyes
        drawCuteEye(canvas, cx - 70f * f, cyToX(420f, f), 20f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cyToX(420f, f), 20f * f, stroke, eyeFill, eyeHighlight)
        // Dark nose
        canvas.drawCircle(cx, 580f * f, 18f * f, eyeFill)
        // Bushy tail curled to side
        val tail = Path().apply {
            moveTo(cx + 120f * f, 660f * f)
            cubicTo(cx + 340f * f, 620f * f, cx + 380f * f, 780f * f, cx + 260f * f, 860f * f)
            cubicTo(cx + 180f * f, 900f * f, cx + 80f * f, 840f * f, cx + 60f * f, 760f * f)
            close()
        }
        canvas.drawPath(tail, stroke)
        // Fox body sitting
        canvas.drawRoundRect(RectF(cx - 120f * f, 580f * f, cx + 120f * f, 860f * f), 40f * f, 40f * f, stroke)
    }

    fun drawGrizzlyBear(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // Big round bear head
        canvas.drawCircle(cx, cy, 200f * f, stroke)
        // Round ears
        canvas.drawCircle(cx - 160f * f, cy - 160f * f, 55f * f, stroke)
        canvas.drawCircle(cx + 160f * f, cy - 160f * f, 55f * f, stroke)
        // Eyes
        drawCuteEye(canvas, cx - 70f * f, cy - 40f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 40f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Snout oval
        canvas.drawOval(RectF(cx - 80f * f, cy + 20f * f, cx + 80f * f, cy + 140f * f), stroke)
        canvas.drawOval(RectF(cx - 35f * f, cy + 35f * f, cx + 35f * f, cy + 85f * f), eyeFill)
        drawHappySmile(canvas, cx, cy + 105f * f, 50f * f, stroke)
        // Big bear body & honey pot
        canvas.drawRoundRect(RectF(cx - 180f * f, cy + 160f * f, cx + 180f * f, 860f * f), 80f * f, 80f * f, stroke)
        // Honey pot
        val pot = Path().apply {
            moveTo(cx - 60f * f, 700f * f)
            lineTo(cx + 60f * f, 700f * f)
            cubicTo(cx + 80f * f, 760f * f, cx + 80f * f, 820f * f, cx + 60f * f, 840f * f)
            lineTo(cx - 60f * f, 840f * f)
            cubicTo(cx - 80f * f, 820f * f, cx - 80f * f, 760f * f, cx - 60f * f, 700f * f)
            close()
        }
        canvas.drawPath(pot, stroke)
    }

    fun drawZebra(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f
        // Zebra head
        val head = Path().apply {
            moveTo(cx - 100f * f, cy - 140f * f)
            cubicTo(cx - 80f * f, cy - 240f * f, cx + 80f * f, cy - 240f * f, cx + 100f * f, cy - 140f * f)
            cubicTo(cx + 140f * f, cy + 40f * f, cx + 120f * f, cy + 180f * f, cx, cy + 220f * f)
            cubicTo(cx - 120f * f, cy + 180f * f, cx - 140f * f, cy + 40f * f, cx - 100f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        // Ears
        canvas.drawOval(RectF(cx - 130f * f, cy - 260f * f, cx - 70f * f, cy - 160f * f), stroke)
        canvas.drawOval(RectF(cx + 70f * f, cy - 260f * f, cx + 130f * f, cy - 160f * f), stroke)
        // Mane
        for (i in 0..5) {
            val y = (cy - 240f + i * 25f)
            canvas.drawLine(cx, y, cx, y - 30f * f, stroke)
        }
        // Eyes
        drawCuteEye(canvas, cx - 60f * f, cy, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy, 22f * f, stroke, eyeFill, eyeHighlight)
        // Black muzzle
        canvas.drawOval(RectF(cx - 70f * f, cy + 150f * f, cx + 70f * f, cy + 220f * f), stroke)
        // Bold stripes
        canvas.drawLine(cx - 90f * f, cy - 60f * f, cx - 30f * f, cy - 40f * f, stroke)
        canvas.drawLine(cx + 90f * f, cy - 60f * f, cx + 30f * f, cy - 40f * f, stroke)
        canvas.drawLine(cx - 100f * f, cy + 40f * f, cx - 40f * f, cy + 50f * f, stroke)
        canvas.drawLine(cx + 100f * f, cy + 40f * f, cx + 40f * f, cy + 50f * f, stroke)
        // Body
        canvas.drawRoundRect(RectF(cx - 130f * f, cy + 220f * f, cx + 130f * f, 860f * f), 50f * f, 50f * f, stroke)
    }

    fun drawKangaroo(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 460f * f
        // Tall ears
        canvas.drawOval(RectF(cx - 70f * f, 140f * f, cx - 20f * f, 280f * f), stroke)
        canvas.drawOval(RectF(cx + 30f * f, 140f * f, cx + 80f * f, 280f * f), stroke)
        // Head
        canvas.drawOval(RectF(cx - 70f * f, 260f * f, cx + 90f * f, 420f * f), stroke)
        drawCuteEye(canvas, cx - 20f * f, 320f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, 320f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(cx + 12f * f, 380f * f, 14f * f, eyeFill)
        // Body & Pouch with Joey
        canvas.drawRoundRect(RectF(cx - 120f * f, 400f * f, cx + 160f * f, 840f * f), 60f * f, 60f * f, stroke)
        // Pouch curved line
        val pouch = Path().apply {
            moveTo(cx - 60f * f, 620f * f)
            cubicTo(cx - 40f * f, 740f * f, cx + 80f * f, 740f * f, cx + 100f * f, 620f * f)
        }
        canvas.drawPath(pouch, stroke)
        // Baby Joey peeking out
        canvas.drawCircle(cx + 20f * f, 590f * f, 36f * f, stroke)
        drawCuteEye(canvas, cx + 12f * f, 585f * f, 8f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 28f * f, 585f * f, 8f * f, stroke, eyeFill, eyeHighlight)
        // Big feet
        canvas.drawRoundRect(RectF(cx - 100f * f, 820f * f, cx + 220f * f, 880f * f), 25f * f, 25f * f, stroke)
    }

    fun drawHippopotamus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f
        // Round ears on top
        canvas.drawCircle(cx - 140f * f, cy - 200f * f, 35f * f, stroke)
        canvas.drawCircle(cx + 140f * f, cy - 200f * f, 35f * f, stroke)
        // Hippo head
        canvas.drawCircle(cx, cy - 60f * f, 160f * f, stroke)
        drawCuteEye(canvas, cx - 70f * f, cy - 120f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 120f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Giant wide snout
        canvas.drawRoundRect(RectF(cx - 200f * f, cy, cx + 200f * f, cy + 220f * f), 80f * f, 80f * f, stroke)
        // Big round nostrils
        canvas.drawCircle(cx - 70f * f, cy + 80f * f, 24f * f, stroke)
        canvas.drawCircle(cx + 70f * f, cy + 80f * f, 24f * f, stroke)
        drawHappySmile(canvas, cx, cy + 140f * f, 120f * f, stroke)
        // Big chubby body
        canvas.drawRoundRect(RectF(cx - 220f * f, cy + 200f * f, cx + 220f * f, 860f * f), 90f * f, 90f * f, stroke)
    }

    fun drawOwl(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f
        // Owl body & head oval
        canvas.drawOval(RectF(cx - 180f * f, cy - 240f * f, cx + 180f * f, cy + 260f * f), stroke)
        // Ear tufts
        val earL = Path().apply { moveTo(cx - 140f * f, cy - 200f * f); lineTo(cx - 160f * f, cy - 300f * f); lineTo(cx - 70f * f, cy - 230f * f) }
        val earR = Path().apply { moveTo(cx + 140f * f, cy - 200f * f); lineTo(cx + 160f * f, cy - 300f * f); lineTo(cx + 70f * f, cy - 230f * f) }
        canvas.drawPath(earL, stroke); canvas.drawPath(earR, stroke)
        // Big round eye discs
        canvas.drawCircle(cx - 70f * f, cy - 90f * f, 60f * f, stroke)
        canvas.drawCircle(cx + 70f * f, cy - 90f * f, 60f * f, stroke)
        drawCuteEye(canvas, cx - 70f * f, cy - 90f * f, 32f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 90f * f, 32f * f, stroke, eyeFill, eyeHighlight)
        // Triangle beak
        val beak = Path().apply { moveTo(cx - 20f * f, cy - 50f * f); lineTo(cx + 20f * f, cy - 50f * f); lineTo(cx, cy - 10f * f); close() }
        canvas.drawPath(beak, eyeFill)
        // Belly feather scallops
        for (row in 0..2) {
            for (col in -1..1) {
                canvas.drawArc(RectF((cx + col * 60f - 25f) * f, (cy + 60f + row * 45f) * f, (cx + col * 60f + 25f) * f, (cy + 95f + row * 45f) * f), 0f, 180f, false, fine)
            }
        }
        // Perch branch
        canvas.drawRoundRect(RectF(140f * f, 720f * f, 884f * f, 770f * f), 15f * f, 15f * f, stroke)
    }

    fun drawHedgehog(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Hedgehog spiky back
        val spikes = Path().apply {
            moveTo(240f * f, 680f * f)
            var a = Math.PI
            while (a >= 0) {
                val r1 = 280f * f
                val r2 = 340f * f
                val px1 = (cx + Math.cos(a) * r1).toFloat()
                val py1 = (600f * f + Math.sin(a) * -r1).toFloat()
                val px2 = (cx + Math.cos(a - 0.15) * r2).toFloat()
                val py2 = (600f * f + Math.sin(a - 0.15) * -r2).toFloat()
                lineTo(px1, py1)
                lineTo(px2, py2)
                a -= 0.3
            }
            lineTo(780f * f, 680f * f)
            close()
        }
        canvas.drawPath(spikes, stroke)
        // Cute face poking forward
        val face = Path().apply {
            moveTo(640f * f, 560f * f)
            lineTo(840f * f, 640f * f) // Snout tip
            lineTo(720f * f, 720f * f)
            close()
        }
        canvas.drawPath(face, stroke)
        drawCuteEye(canvas, 700f * f, 620f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(840f * f, 640f * f, 12f * f, eyeFill)
        // Apple on its back
        canvas.drawCircle(440f * f, 380f * f, 60f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawSloth(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Jungle tree branch at top
        canvas.drawRoundRect(RectF(100f * f, 220f * f, 924f * f, 290f * f), 25f * f, 25f * f, stroke)
        // Sloth hanging body
        val body = Path().apply {
            moveTo(300f * f, 290f * f)
            cubicTo(260f * f, 440f * f, 340f * f, 640f * f, 512f * f, 640f * f)
            cubicTo(680f * f, 640f * f, 760f * f, 440f * f, 720f * f, 290f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Sloth round head
        canvas.drawCircle(cx, 440f * f, 110f * f, stroke)
        // Eye mask patches
        canvas.drawOval(RectF(cx - 85f * f, 410f * f, cx - 25f * f, 470f * f), fine)
        canvas.drawOval(RectF(cx + 25f * f, 410f * f, cx + 85f * f, 470f * f), fine)
        drawCuteEye(canvas, cx - 55f * f, 440f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 55f * f, 440f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(cx, 480f * f, 12f * f, eyeFill)
        drawHappySmile(canvas, cx, 500f * f, 40f * f, stroke)
        // 3 Claws on arms gripping branch
        for (i in 0..2) {
            canvas.drawRoundRect(RectF((310f + i * 20f) * f, 200f * f, (325f + i * 20f) * f, 260f * f), 6f * f, 6f * f, stroke)
            canvas.drawRoundRect(RectF((660f + i * 20f) * f, 200f * f, (675f + i * 20f) * f, 260f * f), 6f * f, 6f * f, stroke)
        }
    }

    fun drawFlamingo(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // S-shaped graceful neck and body
        val flamingo = Path().apply {
            moveTo(cx + 120f * f, 260f * f) // Small head
            cubicTo(cx + 80f * f, 200f * f, cx - 40f * f, 220f * f, cx - 40f * f, 300f * f)
            cubicTo(cx - 40f * f, 380f * f, cx + 40f * f, 440f * f, cx + 20f * f, 520f * f) // S-neck
            cubicTo(cx - 80f * f, 520f * f, cx - 180f * f, 580f * f, cx - 180f * f, 660f * f) // Body
            cubicTo(cx - 180f * f, 740f * f, cx - 60f * f, 780f * f, cx + 60f * f, 740f * f)
            cubicTo(cx + 160f * f, 700f * f, cx + 180f * f, 580f * f, cx + 80f * f, 540f * f)
            cubicTo(cx + 120f * f, 460f * f, cx + 40f * f, 380f * f, cx + 40f * f, 320f * f)
            close()
        }
        canvas.drawPath(flamingo, stroke)
        drawCuteEye(canvas, cx - 10f * f, 270f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        // Down-curved beak
        val beak = Path().apply {
            moveTo(cx - 40f * f, 280f * f)
            lineTo(cx - 100f * f, 330f * f)
            lineTo(cx - 60f * f, 350f * f)
            close()
        }
        canvas.drawPath(beak, eyeFill)
        // Long slender legs (one standing, one bent)
        canvas.drawLine(cx - 20f * f, 760f * f, cx - 20f * f, 900f * f, stroke)
        val bentLeg = Path().apply {
            moveTo(cx + 20f * f, 760f * f)
            lineTo(cx + 70f * f, 820f * f)
            lineTo(cx - 10f * f, 820f * f)
        }
        canvas.drawPath(bentLeg, stroke)
        // Water ripples
        canvas.drawLine(cx - 120f * f, 900f * f, cx + 120f * f, 900f * f, fine)
    }

    private fun cyToX(v: Float, f: Float): Float = v * f
}
