package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass

object AnimalRenderers {

    fun drawPuppy(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Floppy Ears
        val earL = Path().apply {
            moveTo(330f * f, 240f * f)
            cubicTo(220f * f, 240f * f, 180f * f, 420f * f, 230f * f, 520f * f)
            cubicTo(270f * f, 560f * f, 340f * f, 480f * f, 340f * f, 380f * f)
            close()
        }
        canvas.drawPath(earL, stroke)

        val earR = Path().apply {
            moveTo(690f * f, 240f * f)
            cubicTo(800f * f, 240f * f, 840f * f, 420f * f, 790f * f, 520f * f)
            cubicTo(750f * f, 560f * f, 680f * f, 480f * f, 680f * f, 380f * f)
            close()
        }
        canvas.drawPath(earR, stroke)

        // Puppy Head
        canvas.drawRoundRect(RectF(290f * f, 180f * f, 730f * f, 540f * f), 180f * f, 160f * f, stroke)

        // Cute Puppy Eyes
        drawCuteEye(canvas, 420f * f, 330f * f, 34f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, 600f * f, 330f * f, 34f * f, stroke, eyeFill, eyeHighlight)

        // Nose & Snout
        val nosePath = Path().apply {
            moveTo(470f * f, 400f * f)
            cubicTo(512f * f, 390f * f, 512f * f, 390f * f, 554f * f, 400f * f)
            cubicTo(540f * f, 440f * f, 480f * f, 440f * f, 470f * f, 400f * f)
            close()
        }
        canvas.drawPath(nosePath, eyeFill)

        // Smiling Mouth
        val mouthPath = Path().apply {
            moveTo(512f * f, 430f * f)
            lineTo(512f * f, 460f * f)
            moveTo(512f * f, 460f * f)
            quadTo(460f * f, 500f * f, 430f * f, 460f * f)
            moveTo(512f * f, 460f * f)
            quadTo(564f * f, 500f * f, 594f * f, 460f * f)
        }
        canvas.drawPath(mouthPath, stroke)

        // Tongue
        val tonguePath = Path().apply {
            moveTo(480f * f, 470f * f)
            cubicTo(480f * f, 520f * f, 544f * f, 520f * f, 544f * f, 470f * f)
            close()
        }
        canvas.drawPath(tonguePath, fine)

        // Body
        val bodyPath = Path().apply {
            moveTo(380f * f, 540f * f)
            cubicTo(320f * f, 620f * f, 300f * f, 780f * f, 360f * f, 860f * f)
            lineTo(664f * f, 860f * f)
            cubicTo(724f * f, 780f * f, 704f * f, 620f * f, 644f * f, 540f * f)
            close()
        }
        canvas.drawPath(bodyPath, stroke)

        // Paws
        canvas.drawRoundRect(RectF(340f * f, 820f * f, 470f * f, 900f * f), 40f * f, 40f * f, stroke)
        canvas.drawRoundRect(RectF(554f * f, 820f * f, 684f * f, 900f * f), 40f * f, 40f * f, stroke)

        // Wagging Tail
        val tailPath = Path().apply {
            moveTo(680f * f, 720f * f)
            cubicTo(820f * f, 660f * f, 860f * f, 500f * f, 800f * f, 440f * f)
            cubicTo(780f * f, 480f * f, 760f * f, 620f * f, 670f * f, 760f * f)
            close()
        }
        canvas.drawPath(tailPath, stroke)

        // Collar with Charm
        val collarPath = Path().apply {
            moveTo(380f * f, 550f * f)
            quadTo(512f * f, 590f * f, 644f * f, 550f * f)
            lineTo(640f * f, 590f * f)
            quadTo(512f * f, 630f * f, 384f * f, 590f * f)
            close()
        }
        canvas.drawPath(collarPath, stroke)
        canvas.drawCircle(512f * f, 625f * f, 24f * f, stroke)
    }

    fun drawKitten(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Pointy Ears
        val earL = Path().apply {
            moveTo(320f * f, 300f * f)
            lineTo(260f * f, 140f * f)
            lineTo(420f * f, 220f * f)
            close()
        }
        canvas.drawPath(earL, stroke)
        // Inner ear
        val inEarL = Path().apply {
            moveTo(320f * f, 280f * f)
            lineTo(280f * f, 170f * f)
            lineTo(390f * f, 225f * f)
            close()
        }
        canvas.drawPath(inEarL, fine)

        val earR = Path().apply {
            moveTo(704f * f, 300f * f)
            lineTo(764f * f, 140f * f)
            lineTo(604f * f, 220f * f)
            close()
        }
        canvas.drawPath(earR, stroke)
        val inEarR = Path().apply {
            moveTo(704f * f, 280f * f)
            lineTo(744f * f, 170f * f)
            lineTo(634f * f, 225f * f)
            close()
        }
        canvas.drawPath(inEarR, fine)

        // Kitten Round Head
        canvas.drawCircle(512f * f, 360f * f, 190f * f, stroke)

        // Big Anime Eyes
        drawCuteEye(canvas, 420f * f, 340f * f, 36f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, 604f * f, 340f * f, 36f * f, stroke, eyeFill, eyeHighlight)

        // Little Triangle Nose
        val nose = Path().apply {
            moveTo(496f * f, 410f * f)
            lineTo(528f * f, 410f * f)
            lineTo(512f * f, 430f * f)
            close()
        }
        canvas.drawPath(nose, eyeFill)

        // Sweet Smile
        val mouth = Path().apply {
            moveTo(512f * f, 430f * f)
            quadTo(480f * f, 470f * f, 440f * f, 440f * f)
            moveTo(512f * f, 430f * f)
            quadTo(544f * f, 470f * f, 584f * f, 440f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Whiskers
        for (yOffset in listOf(-15f, 15f)) {
            canvas.drawLine(340f * f, (430f + yOffset) * f, 220f * f, (420f + yOffset * 2) * f, fine)
            canvas.drawLine(684f * f, (430f + yOffset) * f, 804f * f, (420f + yOffset * 2) * f, fine)
        }

        // Sitting Body
        val body = Path().apply {
            moveTo(390f * f, 530f * f)
            cubicTo(320f * f, 620f * f, 330f * f, 780f * f, 370f * f, 860f * f)
            lineTo(654f * f, 860f * f)
            cubicTo(694f * f, 780f * f, 704f * f, 620f * f, 634f * f, 530f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Front Paws
        canvas.drawRoundRect(RectF(430f * f, 760f * f, 510f * f, 880f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(514f * f, 760f * f, 594f * f, 880f * f), 30f * f, 30f * f, stroke)

        // Ball of yarn on the floor
        canvas.drawCircle(220f * f, 820f * f, 55f * f, stroke)
        val yarnLine = Path().apply {
            moveTo(220f * f, 770f * f)
            cubicTo(260f * f, 800f * f, 180f * f, 840f * f, 220f * f, 875f * f)
            moveTo(170f * f, 810f * f)
            cubicTo(220f * f, 830f * f, 250f * f, 800f * f, 275f * f, 830f * f)
            // String trailing to paw
            moveTo(270f * f, 840f * f)
            quadTo(350f * f, 880f * f, 430f * f, 840f * f)
        }
        canvas.drawPath(yarnLine, fine)
    }

    fun drawPanda(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Round Panda Ears
        canvas.drawCircle(320f * f, 240f * f, 65f * f, stroke)
        canvas.drawCircle(704f * f, 240f * f, 65f * f, stroke)

        // Large Head
        canvas.drawCircle(cx, 400f * f, 200f * f, stroke)

        // Black Eye Patches (Angled ovals)
        val patchL = RectF(370f * f, 330f * f, 470f * f, 450f * f)
        canvas.drawOval(patchL, stroke)
        val patchR = RectF(554f * f, 330f * f, 654f * f, 450f * f)
        canvas.drawOval(patchR, stroke)

        // Eyes inside patches
        drawCuteEye(canvas, 420f * f, 380f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, 604f * f, 380f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Round Panda Nose
        val nose = Path().apply {
            moveTo(480f * f, 460f * f)
            cubicTo(512f * f, 440f * f, 512f * f, 440f * f, 544f * f, 460f * f)
            cubicTo(528f * f, 490f * f, 496f * f, 490f * f, 480f * f, 460f * f)
            close()
        }
        canvas.drawPath(nose, eyeFill)

        // Smile
        val mouth = Path().apply {
            moveTo(512f * f, 485f * f)
            lineTo(512f * f, 515f * f)
            moveTo(512f * f, 515f * f)
            quadTo(460f * f, 550f * f, 440f * f, 510f * f)
            moveTo(512f * f, 515f * f)
            quadTo(564f * f, 550f * f, 584f * f, 510f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Round Body
        val body = Path().apply {
            moveTo(360f * f, 560f * f)
            cubicTo(260f * f, 640f * f, 280f * f, 840f * f, 360f * f, 880f * f)
            lineTo(664f * f, 880f * f)
            cubicTo(744f * f, 840f * f, 764f * f, 640f * f, 664f * f, 560f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Front Paws holding bamboo
        canvas.drawCircle(390f * f, 670f * f, 45f * f, stroke)
        canvas.drawCircle(634f * f, 670f * f, 45f * f, stroke)

        // Stalk of Bamboo
        val bamboo = Path().apply {
            moveTo(700f * f, 900f * f)
            lineTo(720f * f, 400f * f)
            lineTo(750f * f, 400f * f)
            lineTo(730f * f, 900f * f)
            close()
        }
        canvas.drawPath(bamboo, stroke)
        // Bamboo segments & leaves
        canvas.drawLine(712f * f, 550f * f, 742f * f, 550f * f, stroke)
        canvas.drawLine(716f * f, 700f * f, 736f * f, 700f * f, stroke)
        // Bamboo leaf
        val leaf = Path().apply {
            moveTo(740f * f, 480f * f)
            quadTo(820f * f, 440f * f, 860f * f, 460f * f)
            quadTo(800f * f, 500f * f, 740f * f, 490f * f)
            close()
        }
        canvas.drawPath(leaf, stroke)

        // Sitting Back Paws
        canvas.drawRoundRect(RectF(300f * f, 820f * f, 410f * f, 910f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(614f * f, 820f * f, 724f * f, 910f * f), 30f * f, 30f * f, stroke)
    }

    fun drawLion(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f

        // Fluffy Sun-burst Mane (scalloped circle)
        val mane = Path()
        val petals = 14
        val outerR = 270f * f
        val innerR = 210f * f
        for (i in 0 until (petals * 2)) {
            val angle = i * Math.PI / petals
            val r = if (i % 2 == 0) outerR else innerR
            val px = (cx + Math.cos(angle) * r).toFloat()
            val py = (cy + Math.sin(angle) * r).toFloat()
            if (i == 0) mane.moveTo(px, py) else mane.lineTo(px, py)
        }
        mane.close()
        canvas.drawPath(mane, stroke)

        // Cute Lion Face
        canvas.drawCircle(cx, cy, 150f * f, stroke)

        // Ears inside mane
        canvas.drawCircle(cx - 120f * f, cy - 130f * f, 36f * f, stroke)
        canvas.drawCircle(cx + 120f * f, cy - 130f * f, 36f * f, stroke)

        // Big Happy Eyes
        drawCuteEye(canvas, cx - 60f * f, cy - 30f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy - 30f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Lion Snout & Muzzle
        canvas.drawOval(RectF(cx - 70f * f, cy + 20f * f, cx + 70f * f, cy + 110f * f), stroke)
        // Heart-shaped nose
        val nose = Path().apply {
            moveTo(cx - 24f * f, cy + 30f * f)
            lineTo(cx + 24f * f, cy + 30f * f)
            lineTo(cx, cy + 60f * f)
            close()
        }
        canvas.drawPath(nose, eyeFill)

        // Lion Smile
        val mouth = Path().apply {
            moveTo(cx, cy + 60f * f)
            lineTo(cx, cy + 85f * f)
            moveTo(cx, cy + 85f * f)
            quadTo(cx - 30f * f, cy + 105f * f, cx - 50f * f, cy + 85f * f)
            moveTo(cx, cy + 85f * f)
            quadTo(cx + 30f * f, cy + 105f * f, cx + 50f * f, cy + 85f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Lion Body
        val body = Path().apply {
            moveTo(cx - 100f * f, cy + 230f * f)
            cubicTo(cx - 140f * f, 720f * f, cx - 120f * f, 820f * f, cx - 110f * f, 880f * f)
            lineTo(cx + 110f * f, 880f * f)
            cubicTo(cx + 120f * f, 820f * f, cx + 140f * f, 720f * f, cx + 100f * f, cy + 230f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Front Paws
        canvas.drawRoundRect(RectF(cx - 120f * f, 820f * f, cx - 20f * f, 900f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 20f * f, 820f * f, cx + 120f * f, 900f * f), 24f * f, 24f * f, stroke)

        // Tufted Tail
        val tail = Path().apply {
            moveTo(cx + 100f * f, 760f * f)
            cubicTo(cx + 260f * f, 740f * f, cx + 320f * f, 620f * f, cx + 290f * f, 540f * f)
        }
        canvas.drawPath(tail, stroke)
        // Fluffy tail tip
        canvas.drawCircle(cx + 290f * f, 540f * f, 30f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawElephant(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Giant Floppy Ears
        val earL = Path().apply {
            moveTo(360f * f, 260f * f)
            cubicTo(160f * f, 220f * f, 120f * f, 440f * f, 180f * f, 560f * f)
            cubicTo(240f * f, 660f * f, 340f * f, 600f * f, 370f * f, 500f * f)
            close()
        }
        canvas.drawPath(earL, stroke)

        val earR = Path().apply {
            moveTo(664f * f, 260f * f)
            cubicTo(864f * f, 220f * f, 904f * f, 440f * f, 844f * f, 560f * f)
            cubicTo(784f * f, 660f * f, 684f * f, 600f * f, 654f * f, 500f * f)
            close()
        }
        canvas.drawPath(earR, stroke)

        // Elephant Head
        canvas.drawCircle(cx, 380f * f, 170f * f, stroke)

        // Cute Friendly Eyes
        drawCuteEye(canvas, cx - 80f * f, 340f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 80f * f, 340f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Curved Long Trunk (swayed playfully upward)
        val trunk = Path().apply {
            moveTo(cx - 45f * f, 460f * f)
            cubicTo(cx - 50f * f, 580f * f, cx - 120f * f, 680f * f, cx - 80f * f, 740f * f)
            cubicTo(cx - 30f * f, 780f * f, cx + 40f * f, 760f * f, cx + 20f * f, 700f * f)
            cubicTo(cx, 650f * f, cx + 10f * f, 580f * f, cx + 45f * f, 460f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)

        // Happy water drops spraying from trunk
        canvas.drawCircle(cx - 100f * f, 700f * f, 14f * f, stroke)
        canvas.drawCircle(cx - 140f * f, 660f * f, 18f * f, stroke)
        canvas.drawCircle(cx - 110f * f, 610f * f, 12f * f, stroke)

        // Cute Tusks
        val tuskL = Path().apply {
            moveTo(cx - 55f * f, 490f * f)
            cubicTo(cx - 100f * f, 520f * f, cx - 120f * f, 500f * f, cx - 110f * f, 460f * f)
            cubicTo(cx - 85f * f, 470f * f, cx - 65f * f, 480f * f, cx - 55f * f, 490f * f)
            close()
        }
        canvas.drawPath(tuskL, stroke)

        val tuskR = Path().apply {
            moveTo(cx + 55f * f, 490f * f)
            cubicTo(cx + 100f * f, 520f * f, cx + 120f * f, 500f * f, cx + 110f * f, 460f * f)
            cubicTo(cx + 85f * f, 470f * f, cx + 65f * f, 480f * f, cx + 55f * f, 490f * f)
            close()
        }
        canvas.drawPath(tuskR, stroke)

        // Big Chunky Body
        val body = Path().apply {
            moveTo(360f * f, 520f * f)
            cubicTo(280f * f, 620f * f, 290f * f, 800f * f, 340f * f, 880f * f)
            lineTo(684f * f, 880f * f)
            cubicTo(734f * f, 800f * f, 744f * f, 620f * f, 664f * f, 520f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Chunky Feet
        canvas.drawRoundRect(RectF(340f * f, 820f * f, 480f * f, 900f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(544f * f, 820f * f, 684f * f, 900f * f), 30f * f, 30f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawRabbit(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Long Upright Ears
        val earL = Path().apply {
            moveTo(430f * f, 320f * f)
            cubicTo(360f * f, 200f * f, 350f * f, 60f * f, 420f * f, 80f * f)
            cubicTo(460f * f, 100f * f, 480f * f, 220f * f, 460f * f, 320f * f)
            close()
        }
        canvas.drawPath(earL, stroke)
        // Inner ear L
        val inEarL = Path().apply {
            moveTo(435f * f, 290f * f)
            cubicTo(395f * f, 200f * f, 385f * f, 100f * f, 425f * f, 110f * f)
            cubicTo(445f * f, 125f * f, 460f * f, 220f * f, 450f * f, 290f * f)
            close()
        }
        canvas.drawPath(inEarL, fine)

        val earR = Path().apply {
            moveTo(564f * f, 320f * f)
            cubicTo(544f * f, 220f * f, 564f * f, 100f * f, 604f * f, 80f * f)
            cubicTo(674f * f, 60f * f, 664f * f, 200f * f, 594f * f, 320f * f)
            close()
        }
        canvas.drawPath(earR, stroke)
        // Inner ear R
        val inEarR = Path().apply {
            moveTo(574f * f, 290f * f)
            cubicTo(564f * f, 220f * f, 579f * f, 125f * f, 599f * f, 110f * f)
            cubicTo(639f * f, 100f * f, 629f * f, 200f * f, 589f * f, 290f * f)
            close()
        }
        canvas.drawPath(inEarR, fine)

        // Bunny Round Head
        canvas.drawCircle(cx, 440f * f, 160f * f, stroke)

        // Cute Big Eyes
        drawCuteEye(canvas, cx - 65f * f, 420f * f, 28f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 65f * f, 420f * f, 28f * f, stroke, eyeFill, eyeHighlight)

        // Pink Triangle Nose
        val nose = Path().apply {
            moveTo(cx - 16f * f, 475f * f)
            lineTo(cx + 16f * f, 475f * f)
            lineTo(cx, 495f * f)
            close()
        }
        canvas.drawPath(nose, eyeFill)

        // Bunny Buck Teeth
        canvas.drawRect(RectF(cx - 16f * f, 520f * f, cx, 545f * f), stroke)
        canvas.drawRect(RectF(cx, 520f * f, cx + 16f * f, 545f * f), stroke)

        // Smile
        val mouth = Path().apply {
            moveTo(cx, 495f * f)
            lineTo(cx, 520f * f)
            moveTo(cx, 520f * f)
            quadTo(cx - 35f * f, 540f * f, cx - 45f * f, 515f * f)
            moveTo(cx, 520f * f)
            quadTo(cx + 35f * f, 540f * f, cx + 45f * f, 515f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Body
        val body = Path().apply {
            moveTo(cx - 100f * f, 580f * f)
            cubicTo(cx - 160f * f, 660f * f, cx - 180f * f, 780f * f, cx - 140f * f, 860f * f)
            lineTo(cx + 140f * f, 860f * f)
            cubicTo(cx + 180f * f, 780f * f, cx + 160f * f, 660f * f, cx + 100f * f, 580f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Big Crunchy Carrot held in paws
        val carrot = Path().apply {
            moveTo(cx - 40f * f, 680f * f)
            lineTo(cx + 90f * f, 640f * f)
            lineTo(cx - 10f * f, 840f * f)
            close()
        }
        canvas.drawPath(carrot, stroke)

        // Leafy top on carrot
        val carrotLeaves = Path().apply {
            moveTo(cx + 80f * f, 640f * f)
            lineTo(cx + 140f * f, 600f * f)
            lineTo(cx + 110f * f, 630f * f)
            lineTo(cx + 160f * f, 630f * f)
            lineTo(cx + 90f * f, 650f * f)
            close()
        }
        canvas.drawPath(carrotLeaves, stroke)

        // Front paws hugging carrot
        canvas.drawCircle(cx - 50f * f, 680f * f, 30f * f, stroke)
        canvas.drawCircle(cx + 50f * f, 680f * f, 30f * f, stroke)

        // Big oval feet on floor
        canvas.drawOval(RectF(cx - 180f * f, 830f * f, cx - 40f * f, 900f * f), stroke)
        canvas.drawOval(RectF(cx + 40f * f, 830f * f, cx + 180f * f, 900f * f), stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawMonkey(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f

        // Big Round Ears
        canvas.drawCircle(cx - 170f * f, cy - 40f * f, 55f * f, stroke)
        canvas.drawCircle(cx - 170f * f, cy - 40f * f, 35f * f, fine)
        canvas.drawCircle(cx + 170f * f, cy - 40f * f, 55f * f, stroke)
        canvas.drawCircle(cx + 170f * f, cy - 40f * f, 35f * f, fine)

        // Head Base
        canvas.drawCircle(cx, cy - 40f * f, 160f * f, stroke)

        // Heart-like Face Outline
        val faceMuzzle = Path().apply {
            moveTo(cx, cy - 10f * f)
            cubicTo(cx - 80f * f, cy - 80f * f, cx - 130f * f, cy, cx - 100f * f, cy + 80f * f)
            cubicTo(cx - 70f * f, cy + 140f * f, cx + 70f * f, cy + 140f * f, cx + 100f * f, cy + 80f * f)
            cubicTo(cx + 130f * f, cy, cx + 80f * f, cy - 80f * f, cx, cy - 10f * f)
            close()
        }
        canvas.drawPath(faceMuzzle, stroke)

        // Big Eyes
        drawCuteEye(canvas, cx - 50f * f, cy - 50f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 50f * f, cy - 50f * f, 24f * f, stroke, eyeFill, eyeHighlight)

        // Monkey Nose
        canvas.drawCircle(cx - 14f * f, cy + 25f * f, 8f * f, eyeFill)
        canvas.drawCircle(cx + 14f * f, cy + 25f * f, 8f * f, eyeFill)

        // Wide Cheeky Smile
        val mouth = Path().apply {
            moveTo(cx - 60f * f, cy + 60f * f)
            quadTo(cx, cy + 110f * f, cx + 60f * f, cy + 60f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Sitting Body
        val body = Path().apply {
            moveTo(cx - 90f * f, cy + 120f * f)
            cubicTo(cx - 140f * f, 680f * f, cx - 140f * f, 780f * f, cx - 90f * f, 860f * f)
            lineTo(cx + 90f * f, 860f * f)
            cubicTo(cx + 140f * f, 780f * f, cx + 140f * f, 680f * f, cx + 90f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Banana held in hand
        val banana = Path().apply {
            moveTo(cx - 120f * f, 640f * f)
            cubicTo(cx - 40f * f, 660f * f, cx + 40f * f, 620f * f, cx + 80f * f, 560f * f)
            cubicTo(cx + 50f * f, 650f * f, cx - 30f * f, 710f * f, cx - 120f * f, 670f * f)
            close()
        }
        canvas.drawPath(banana, stroke)

        // Long curly tail
        val tail = Path().apply {
            moveTo(cx - 90f * f, 780f * f)
            cubicTo(cx - 240f * f, 760f * f, cx - 280f * f, 620f * f, cx - 220f * f, 560f * f)
            cubicTo(cx - 180f * f, 520f * f, cx - 150f * f, 580f * f, cx - 190f * f, 600f * f)
        }
        canvas.drawPath(tail, stroke)

        // Monkey Feet
        canvas.drawRoundRect(RectF(cx - 140f * f, 820f * f, cx - 20f * f, 890f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 20f * f, 820f * f, cx + 140f * f, 890f * f), 24f * f, 24f * f, stroke)
    }

    fun drawGiraffe(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Horns / Ossicones with knobs
        canvas.drawLine(cx - 40f * f, 190f * f, cx - 40f * f, 130f * f, stroke)
        canvas.drawCircle(cx - 40f * f, 120f * f, 18f * f, stroke)
        canvas.drawLine(cx + 40f * f, 190f * f, cx + 40f * f, 130f * f, stroke)
        canvas.drawCircle(cx + 40f * f, 120f * f, 18f * f, stroke)

        // Cute Leaf-shaped Ears
        val earL = Path().apply {
            moveTo(cx - 70f * f, 220f * f)
            cubicTo(cx - 160f * f, 200f * f, cx - 170f * f, 260f * f, cx - 70f * f, 250f * f)
            close()
        }
        canvas.drawPath(earL, stroke)

        val earR = Path().apply {
            moveTo(cx + 70f * f, 220f * f)
            cubicTo(cx + 160f * f, 200f * f, cx + 170f * f, 260f * f, cx + 70f * f, 250f * f)
            close()
        }
        canvas.drawPath(earR, stroke)

        // Giraffe Head
        val head = Path().apply {
            moveTo(cx - 60f * f, 190f * f)
            cubicTo(cx - 75f * f, 260f * f, cx - 90f * f, 320f * f, cx - 80f * f, 380f * f)
            cubicTo(cx - 60f * f, 440f * f, cx + 60f * f, 440f * f, cx + 80f * f, 380f * f)
            cubicTo(cx + 90f * f, 320f * f, cx + 75f * f, 260f * f, cx + 60f * f, 190f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Friendly Eyes
        drawCuteEye(canvas, cx - 45f * f, 260f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, 260f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Big Cute Muzzle & Nostrils
        canvas.drawCircle(cx - 24f * f, 380f * f, 12f * f, eyeFill)
        canvas.drawCircle(cx + 24f * f, 380f * f, 12f * f, eyeFill)
        val smile = Path().apply {
            moveTo(cx - 35f * f, 410f * f)
            quadTo(cx, 430f * f, cx + 35f * f, 410f * f)
        }
        canvas.drawPath(smile, stroke)

        // Long Elegant Neck with spots
        val neck = Path().apply {
            moveTo(cx - 60f * f, 430f * f)
            lineTo(cx - 80f * f, 750f * f)
            lineTo(cx + 80f * f, 750f * f)
            lineTo(cx + 60f * f, 430f * f)
            close()
        }
        canvas.drawPath(neck, stroke)

        // Giraffe Spots on Neck
        canvas.drawRoundRect(RectF(cx - 50f * f, 470f * f, cx + 20f * f, 530f * f), 18f * f, 18f * f, stroke)
        canvas.drawRoundRect(RectF(cx - 10f * f, 550f * f, cx + 55f * f, 620f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(cx - 60f * f, 640f * f, cx + 15f * f, 710f * f), 22f * f, 22f * f, stroke)

        // Body below
        val body = Path().apply {
            moveTo(cx - 80f * f, 750f * f)
            cubicTo(cx - 200f * f, 770f * f, cx - 220f * f, 840f * f, cx - 200f * f, 900f * f)
            lineTo(cx + 200f * f, 900f * f)
            cubicTo(cx + 220f * f, 840f * f, cx + 200f * f, 770f * f, cx + 80f * f, 750f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBear(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Round Teddy Ears
        canvas.drawCircle(cx - 150f * f, 260f * f, 55f * f, stroke)
        canvas.drawCircle(cx - 150f * f, 260f * f, 30f * f, fine)
        canvas.drawCircle(cx + 150f * f, 260f * f, 55f * f, stroke)
        canvas.drawCircle(cx + 150f * f, 260f * f, 30f * f, fine)

        // Round Bear Head
        canvas.drawCircle(cx, 390f * f, 180f * f, stroke)

        // Friendly Eyes
        drawCuteEye(canvas, cx - 65f * f, 350f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 65f * f, 350f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Big Muzzle
        canvas.drawOval(RectF(cx - 75f * f, 400f * f, cx + 75f * f, 500f * f), stroke)
        val nose = Path().apply {
            moveTo(cx - 30f * f, 420f * f)
            cubicTo(cx, 410f * f, cx, 410f * f, cx + 30f * f, 420f * f)
            cubicTo(cx + 15f * f, 455f * f, cx - 15f * f, 455f * f, cx - 30f * f, 420f * f)
            close()
        }
        canvas.drawPath(nose, eyeFill)

        // Bear Smile
        val mouth = Path().apply {
            moveTo(cx, 455f * f)
            lineTo(cx, 480f * f)
            moveTo(cx, 480f * f)
            quadTo(cx - 30f * f, 505f * f, cx - 45f * f, 480f * f)
            moveTo(cx, 480f * f)
            quadTo(cx + 30f * f, 505f * f, cx + 45f * f, 480f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Body
        val body = Path().apply {
            moveTo(cx - 110f * f, 540f * f)
            cubicTo(cx - 180f * f, 620f * f, cx - 200f * f, 780f * f, cx - 140f * f, 860f * f)
            lineTo(cx + 140f * f, 860f * f)
            cubicTo(cx + 200f * f, 780f * f, cx + 180f * f, 620f * f, cx + 110f * f, 540f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Honey Pot held in paws
        val pot = Path().apply {
            moveTo(cx - 60f * f, 660f * f)
            lineTo(cx + 60f * f, 660f * f)
            cubicTo(cx + 90f * f, 720f * f, cx + 80f * f, 820f * f, cx + 50f * f, 840f * f)
            lineTo(cx - 50f * f, 840f * f)
            cubicTo(cx - 80f * f, 820f * f, cx - 90f * f, 720f * f, cx - 60f * f, 660f * f)
            close()
        }
        canvas.drawPath(pot, stroke)

        // Honey dripping from rim
        val honey = Path().apply {
            moveTo(cx - 40f * f, 660f * f)
            cubicTo(cx - 20f * f, 700f * f, cx, 700f * f, cx + 10f * f, 660f * f)
        }
        canvas.drawPath(honey, fine)

        // Big Bear Paws
        canvas.drawCircle(cx - 90f * f, 710f * f, 38f * f, stroke)
        canvas.drawCircle(cx + 90f * f, 710f * f, 38f * f, stroke)

        // Feet on floor
        canvas.drawRoundRect(RectF(cx - 170f * f, 820f * f, cx - 70f * f, 900f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 70f * f, 820f * f, cx + 170f * f, 900f * f), 30f * f, 30f * f, stroke)
    }

    fun drawKoala(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Huge Fluffy Ears with fur tufts
        canvas.drawCircle(cx - 170f * f, 290f * f, 70f * f, stroke)
        canvas.drawCircle(cx - 170f * f, 290f * f, 45f * f, fine)
        canvas.drawCircle(cx + 170f * f, 290f * f, 70f * f, stroke)
        canvas.drawCircle(cx + 170f * f, 290f * f, 45f * f, fine)

        // Koala Head
        canvas.drawCircle(cx, 400f * f, 170f * f, stroke)

        // Wide Eyes
        drawCuteEye(canvas, cx - 70f * f, 370f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, 370f * f, 24f * f, stroke, eyeFill, eyeHighlight)

        // Big Shiny Oval Koala Nose
        canvas.drawOval(RectF(cx - 45f * f, 380f * f, cx + 45f * f, 490f * f), eyeFill)

        // Sweet Smile below nose
        val smile = Path().apply {
            moveTo(cx - 30f * f, 515f * f)
            quadTo(cx, 540f * f, cx + 30f * f, 515f * f)
        }
        canvas.drawPath(smile, stroke)

        // Eucalyptus tree trunk being hugged
        val tree = Path().apply {
            moveTo(cx - 240f * f, 900f * f)
            lineTo(cx - 220f * f, 150f * f)
            lineTo(cx - 150f * f, 150f * f)
            lineTo(cx - 170f * f, 900f * f)
            close()
        }
        canvas.drawPath(tree, stroke)

        // Paws hugging the tree
        canvas.drawRoundRect(RectF(cx - 240f * f, 560f * f, cx - 120f * f, 620f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(cx - 240f * f, 740f * f, cx - 120f * f, 800f * f), 24f * f, 24f * f, stroke)

        // Koala Body
        val body = Path().apply {
            moveTo(cx - 100f * f, 540f * f)
            cubicTo(cx, 580f * f, cx + 160f * f, 660f * f, cx + 120f * f, 820f * f)
            cubicTo(cx + 80f * f, 880f * f, cx - 40f * f, 860f * f, cx - 110f * f, 820f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Eucalyptus Leaves
        val leaf = Path().apply {
            moveTo(cx - 200f * f, 280f * f)
            cubicTo(cx - 300f * f, 250f * f, cx - 340f * f, 320f * f, cx - 200f * f, 310f * f)
            close()
        }
        canvas.drawPath(leaf, stroke)
    }

    fun drawZebra(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Pointy Ears
        val earL = Path().apply {
            moveTo(cx - 70f * f, 220f * f)
            lineTo(cx - 110f * f, 120f * f)
            lineTo(cx - 30f * f, 180f * f)
            close()
        }
        canvas.drawPath(earL, stroke)

        val earR = Path().apply {
            moveTo(cx + 70f * f, 220f * f)
            lineTo(cx + 110f * f, 120f * f)
            lineTo(cx + 30f * f, 180f * f)
            close()
        }
        canvas.drawPath(earR, stroke)

        // Mohawk Mane between ears
        val mane = Path().apply {
            moveTo(cx - 30f * f, 170f * f)
            lineTo(cx - 20f * f, 100f * f)
            lineTo(cx, 130f * f)
            lineTo(cx + 20f * f, 100f * f)
            lineTo(cx + 30f * f, 170f * f)
            close()
        }
        canvas.drawPath(mane, stroke)

        // Zebra Head
        val head = Path().apply {
            moveTo(cx - 50f * f, 200f * f)
            cubicTo(cx - 80f * f, 300f * f, cx - 90f * f, 380f * f, cx - 70f * f, 460f * f)
            cubicTo(cx - 50f * f, 520f * f, cx + 50f * f, 520f * f, cx + 70f * f, 460f * f)
            cubicTo(cx + 90f * f, 380f * f, cx + 80f * f, 300f * f, cx + 50f * f, 200f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Bold Zebra Stripes on Face
        canvas.drawLine(cx - 75f * f, 320f * f, cx - 25f * f, 330f * f, stroke)
        canvas.drawLine(cx + 75f * f, 320f * f, cx + 25f * f, 330f * f, stroke)
        canvas.drawLine(cx - 80f * f, 380f * f, cx - 35f * f, 390f * f, stroke)
        canvas.drawLine(cx + 80f * f, 380f * f, cx + 35f * f, 390f * f, stroke)

        // Friendly Eyes
        drawCuteEye(canvas, cx - 45f * f, 280f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, 280f * f, 24f * f, stroke, eyeFill, eyeHighlight)

        // Dark Muzzle & Smiling Mouth
        val muzzle = Path().apply {
            moveTo(cx - 65f * f, 440f * f)
            quadTo(cx, 470f * f, cx + 65f * f, 440f * f)
            cubicTo(cx + 50f * f, 515f * f, cx - 50f * f, 515f * f, cx - 65f * f, 440f * f)
            close()
        }
        canvas.drawPath(muzzle, stroke)
        canvas.drawCircle(cx - 20f * f, 465f * f, 8f * f, eyeFill)
        canvas.drawCircle(cx + 20f * f, 465f * f, 8f * f, eyeFill)

        // Body with horizontal stripes
        val body = Path().apply {
            moveTo(cx - 70f * f, 510f * f)
            cubicTo(cx - 160f * f, 620f * f, cx - 180f * f, 780f * f, cx - 130f * f, 880f * f)
            lineTo(cx + 130f * f, 880f * f)
            cubicTo(cx + 180f * f, 780f * f, cx + 160f * f, 620f * f, cx + 70f * f, 510f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Body Stripes
        canvas.drawLine(cx - 120f * f, 600f * f, cx - 40f * f, 620f * f, stroke)
        canvas.drawLine(cx + 120f * f, 600f * f, cx + 40f * f, 620f * f, stroke)
        canvas.drawLine(cx - 140f * f, 700f * f, cx - 50f * f, 720f * f, stroke)
        canvas.drawLine(cx + 140f * f, 700f * f, cx + 50f * f, 720f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawFox(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Big Pointy Fox Ears
        val earL = Path().apply {
            moveTo(cx - 140f * f, 290f * f)
            lineTo(cx - 190f * f, 120f * f)
            lineTo(cx - 50f * f, 200f * f)
            close()
        }
        canvas.drawPath(earL, stroke)
        val inEarL = Path().apply {
            moveTo(cx - 135f * f, 260f * f)
            lineTo(cx - 175f * f, 145f * f)
            lineTo(cx - 75f * f, 205f * f)
            close()
        }
        canvas.drawPath(inEarL, fine)

        val earR = Path().apply {
            moveTo(cx + 140f * f, 290f * f)
            lineTo(cx + 190f * f, 120f * f)
            lineTo(cx + 50f * f, 200f * f)
            close()
        }
        canvas.drawPath(earR, stroke)
        val inEarR = Path().apply {
            moveTo(cx + 135f * f, 260f * f)
            lineTo(cx + 175f * f, 145f * f)
            lineTo(cx + 75f * f, 205f * f)
            close()
        }
        canvas.drawPath(inEarR, fine)

        // Fox Head with fluffy cheek tufts
        val head = Path().apply {
            moveTo(cx - 50f * f, 200f * f)
            cubicTo(cx, 185f * f, cx, 185f * f, cx + 50f * f, 200f * f)
            cubicTo(cx + 120f * f, 240f * f, cx + 190f * f, 320f * f, cx + 180f * f, 400f * f)
            lineTo(cx + 140f * f, 420f * f)
            lineTo(cx, 510f * f) // nose tip
            lineTo(cx - 140f * f, 420f * f)
            lineTo(cx - 180f * f, 400f * f)
            cubicTo(cx - 190f * f, 320f * f, cx - 120f * f, 240f * f, cx - 50f * f, 200f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // White cheek markings
        val cheek = Path().apply {
            moveTo(cx - 140f * f, 420f * f)
            quadTo(cx - 70f * f, 360f * f, cx, 490f * f)
            quadTo(cx + 70f * f, 360f * f, cx + 140f * f, 420f * f)
        }
        canvas.drawPath(cheek, fine)

        // Cute Fox Eyes
        drawCuteEye(canvas, cx - 60f * f, 330f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, 330f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Little Black Nose
        canvas.drawCircle(cx, 500f * f, 14f * f, eyeFill)

        // Sitting Body
        val body = Path().apply {
            moveTo(cx - 80f * f, 480f * f)
            cubicTo(cx - 140f * f, 580f * f, cx - 150f * f, 740f * f, cx - 110f * f, 840f * f)
            lineTo(cx + 110f * f, 840f * f)
            cubicTo(cx + 150f * f, 740f * f, cx + 140f * f, 580f * f, cx + 80f * f, 480f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Paws
        canvas.drawRoundRect(RectF(cx - 90f * f, 800f * f, cx - 10f * f, 870f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 10f * f, 800f * f, cx + 90f * f, 870f * f), 20f * f, 20f * f, stroke)

        // Giant Fluffy Tail wrapping around
        val tail = Path().apply {
            moveTo(cx + 80f * f, 780f * f)
            cubicTo(cx + 280f * f, 760f * f, cx + 340f * f, 580f * f, cx + 260f * f, 460f * f)
            cubicTo(cx + 200f * f, 380f * f, cx + 180f * f, 460f * f, cx + 160f * f, 540f * f)
            cubicTo(cx + 140f * f, 660f * f, cx + 120f * f, 760f * f, cx + 80f * f, 780f * f)
            close()
        }
        canvas.drawPath(tail, stroke)

        // White Tip on Tail
        val tailTip = Path().apply {
            moveTo(cx + 220f * f, 470f * f)
            lineTo(cx + 260f * f, 460f * f)
            lineTo(cx + 240f * f, 400f * f)
            close()
        }
        canvas.drawPath(tailTip, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBunny(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        drawRabbit(canvas, s, stroke, fine, eyeFill, eyeHighlight)
    }

    fun drawPenguin(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Penguin Oval Body
        val body = Path().apply {
            moveTo(cx, 180f * f)
            cubicTo(cx + 180f * f, 180f * f, cx + 220f * f, 400f * f, cx + 200f * f, 700f * f)
            cubicTo(cx + 180f * f, 840f * f, cx - 180f * f, 840f * f, cx - 200f * f, 700f * f)
            cubicTo(cx - 220f * f, 400f * f, cx - 180f * f, 180f * f, cx, 180f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // White Belly Patch
        val belly = Path().apply {
            moveTo(cx, 380f * f)
            cubicTo(cx + 130f * f, 380f * f, cx + 150f * f, 560f * f, cx + 120f * f, 760f * f)
            cubicTo(cx + 90f * f, 830f * f, cx - 90f * f, 830f * f, cx - 120f * f, 760f * f)
            cubicTo(cx - 150f * f, 560f * f, cx - 130f * f, 380f * f, cx, 380f * f)
            close()
        }
        canvas.drawPath(belly, stroke)

        // Cute Big Eyes
        drawCuteEye(canvas, cx - 60f * f, 290f * f, 28f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, 290f * f, 28f * f, stroke, eyeFill, eyeHighlight)

        // Triangle Beak
        val beak = Path().apply {
            moveTo(cx - 35f * f, 330f * f)
            lineTo(cx + 35f * f, 330f * f)
            lineTo(cx, 380f * f)
            close()
        }
        canvas.drawPath(beak, stroke)

        // Flippers (Wings)
        val flipperL = Path().apply {
            moveTo(cx - 180f * f, 420f * f)
            cubicTo(cx - 280f * f, 520f * f, cx - 280f * f, 660f * f, cx - 210f * f, 680f * f)
            cubicTo(cx - 190f * f, 640f * f, cx - 170f * f, 560f * f, cx - 160f * f, 480f * f)
            close()
        }
        canvas.drawPath(flipperL, stroke)

        val flipperR = Path().apply {
            moveTo(cx + 180f * f, 420f * f)
            cubicTo(cx + 280f * f, 520f * f, cx + 280f * f, 660f * f, cx + 210f * f, 680f * f)
            cubicTo(cx + 190f * f, 640f * f, cx + 170f * f, 560f * f, cx + 160f * f, 480f * f)
            close()
        }
        canvas.drawPath(flipperR, stroke)

        // Webbed Feet
        canvas.drawRoundRect(RectF(cx - 130f * f, 800f * f, cx - 20f * f, 860f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 20f * f, 800f * f, cx + 130f * f, 860f * f), 24f * f, 24f * f, stroke)

        // Ice ground
        val ice = Path().apply {
            moveTo(100f * f, 850f * f)
            lineTo(924f * f, 850f * f)
        }
        canvas.drawPath(ice, stroke)
    }
}
