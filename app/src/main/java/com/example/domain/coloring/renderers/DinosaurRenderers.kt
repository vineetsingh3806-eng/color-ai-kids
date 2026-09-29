package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass

object DinosaurRenderers {

    fun drawTRex(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Head and Snout
        val headPath = Path().apply {
            moveTo(320f * f, 220f * f)
            cubicTo(360f * f, 140f * f, 560f * f, 140f * f, 680f * f, 200f * f)
            cubicTo(740f * f, 230f * f, 780f * f, 300f * f, 760f * f, 380f * f)
            lineTo(560f * f, 400f * f) // mouth line
            cubicTo(540f * f, 440f * f, 480f * f, 470f * f, 430f * f, 460f * f)
            cubicTo(370f * f, 450f * f, 340f * f, 380f * f, 320f * f, 320f * f)
            close()
        }
        canvas.drawPath(headPath, stroke)

        // Cute teeth
        val teethPath = Path().apply {
            moveTo(600f * f, 400f * f)
            lineTo(620f * f, 430f * f)
            lineTo(640f * f, 400f * f)
            lineTo(660f * f, 430f * f)
            lineTo(680f * f, 400f * f)
        }
        canvas.drawPath(teethPath, stroke)

        // Friendly Eye
        drawCuteEye(canvas, 540f * f, 240f * f, 36f * f, stroke, eyeFill, eyeHighlight)
        canvas.drawCircle(720f * f, 270f * f, 10f * f, eyeFill) // Nostril

        // Body and Tail
        val bodyPath = Path().apply {
            moveTo(340f * f, 400f * f)
            cubicTo(300f * f, 470f * f, 280f * f, 580f * f, 340f * f, 680f * f)
            cubicTo(260f * f, 720f * f, 160f * f, 700f * f, 100f * f, 640f * f)
            cubicTo(120f * f, 750f * f, 220f * f, 810f * f, 320f * f, 780f * f)
            cubicTo(400f * f, 840f * f, 500f * f, 830f * f, 580f * f, 760f * f)
            cubicTo(600f * f, 650f * f, 580f * f, 520f * f, 460f * f, 470f * f)
            close()
        }
        canvas.drawPath(bodyPath, stroke)

        // Short Dino Arms
        val armPath = Path().apply {
            moveTo(520f * f, 510f * f)
            cubicTo(570f * f, 510f * f, 610f * f, 540f * f, 600f * f, 580f * f)
            lineTo(560f * f, 585f * f)
            lineTo(550f * f, 560f * f)
            close()
        }
        canvas.drawPath(armPath, stroke)

        // Big Chunky Legs
        val legLeft = Path().apply {
            moveTo(360f * f, 690f * f)
            cubicTo(340f * f, 760f * f, 330f * f, 840f * f, 340f * f, 900f * f)
            lineTo(450f * f, 900f * f)
            cubicTo(440f * f, 830f * f, 430f * f, 750f * f, 460f * f, 710f * f)
            close()
        }
        canvas.drawPath(legLeft, stroke)

        val legRight = Path().apply {
            moveTo(480f * f, 710f * f)
            cubicTo(490f * f, 770f * f, 500f * f, 840f * f, 510f * f, 900f * f)
            lineTo(610f * f, 900f * f)
            cubicTo(600f * f, 830f * f, 580f * f, 750f * f, 560f * f, 710f * f)
            close()
        }
        canvas.drawPath(legRight, stroke)

        // Back Spikes
        val spikes = listOf(280f to 230f, 250f to 330f, 230f to 440f, 210f to 560f, 150f to 660f)
        for ((sx, sy) in spikes) {
            val spikePath = Path().apply {
                moveTo(sx * f, sy * f)
                lineTo((sx - 35f) * f, (sy - 20f) * f)
                lineTo((sx + 10f) * f, (sy + 25f) * f)
                close()
            }
            canvas.drawPath(spikePath, stroke)
        }

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawTriceratops(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Neck Frill (Large scalloped crest)
        val frillPath = Path().apply {
            moveTo(360f * f, 440f * f)
            cubicTo(320f * f, 320f * f, 340f * f, 220f * f, 440f * f, 180f * f)
            cubicTo(500f * f, 150f * f, 600f * f, 160f * f, 670f * f, 220f * f)
            cubicTo(730f * f, 270f * f, 750f * f, 360f * f, 720f * f, 460f * f)
            close()
        }
        canvas.drawPath(frillPath, stroke)

        // Frill spots/circles for coloring
        canvas.drawCircle(460f * f, 240f * f, 30f * f, stroke)
        canvas.drawCircle(550f * f, 220f * f, 34f * f, stroke)
        canvas.drawCircle(640f * f, 270f * f, 28f * f, stroke)

        // Triceratops Head
        val headPath = Path().apply {
            moveTo(420f * f, 400f * f)
            cubicTo(450f * f, 360f * f, 620f * f, 370f * f, 660f * f, 420f * f)
            cubicTo(700f * f, 480f * f, 710f * f, 560f * f, 660f * f, 610f * f)
            cubicTo(600f * f, 660f * f, 470f * f, 650f * f, 420f * f, 580f * f)
            close()
        }
        canvas.drawPath(headPath, stroke)

        // Brow Horns (Left and Right)
        val hornL = Path().apply {
            moveTo(460f * f, 380f * f)
            lineTo(440f * f, 240f * f)
            lineTo(490f * f, 370f * f)
            close()
        }
        canvas.drawPath(hornL, stroke)

        val hornR = Path().apply {
            moveTo(590f * f, 370f * f)
            lineTo(640f * f, 240f * f)
            lineTo(620f * f, 380f * f)
            close()
        }
        canvas.drawPath(hornR, stroke)

        // Nose Horn
        val noseHorn = Path().apply {
            moveTo(520f * f, 540f * f)
            lineTo(540f * f, 470f * f)
            lineTo(560f * f, 540f * f)
            close()
        }
        canvas.drawPath(noseHorn, stroke)

        // Friendly Eye
        drawCuteEye(canvas, 480f * f, 460f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, 600f * f, 460f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Smile
        val mouth = Path().apply {
            moveTo(500f * f, 600f * f)
            quadTo(540f * f, 630f * f, 580f * f, 600f * f)
        }
        canvas.drawPath(mouth, stroke)

        // Body behind
        val bodyPath = Path().apply {
            moveTo(360f * f, 520f * f)
            cubicTo(260f * f, 540f * f, 180f * f, 620f * f, 160f * f, 720f * f)
            cubicTo(150f * f, 820f * f, 240f * f, 860f * f, 360f * f, 860f * f)
            lineTo(720f * f, 860f * f)
            cubicTo(820f * f, 850f * f, 880f * f, 780f * f, 860f * f, 680f * f)
            cubicTo(840f * f, 580f * f, 760f * f, 520f * f, 680f * f, 520f * f)
            close()
        }
        canvas.drawPath(bodyPath, stroke)

        // Legs
        canvas.drawRoundRect(RectF(260f * f, 760f * f, 360f * f, 900f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(400f * f, 780f * f, 500f * f, 900f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(600f * f, 780f * f, 700f * f, 900f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(740f * f, 760f * f, 840f * f, 900f * f), 30f * f, 30f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBrachiosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Long curving neck & head
        val neckHeadPath = Path().apply {
            moveTo(600f * f, 700f * f)
            cubicTo(620f * f, 500f * f, 580f * f, 340f * f, 520f * f, 220f * f)
            cubicTo(500f * f, 180f * f, 420f * f, 170f * f, 370f * f, 210f * f)
            cubicTo(340f * f, 240f * f, 340f * f, 280f * f, 380f * f, 300f * f)
            cubicTo(440f * f, 320f * f, 480f * f, 420f * f, 500f * f, 560f * f)
            lineTo(480f * f, 720f * f)
            close()
        }
        canvas.drawPath(neckHeadPath, stroke)

        // Cute Eye on Head
        drawCuteEye(canvas, 420f * f, 230f * f, 20f * f, stroke, eyeFill, eyeHighlight)
        // Friendly smile
        val smile = Path().apply {
            moveTo(360f * f, 265f * f)
            quadTo(390f * f, 285f * f, 415f * f, 265f * f)
        }
        canvas.drawPath(smile, stroke)

        // Body and Tail
        val bodyPath = Path().apply {
            moveTo(480f * f, 660f * f)
            cubicTo(400f * f, 670f * f, 300f * f, 700f * f, 240f * f, 760f * f)
            cubicTo(170f * f, 800f * f, 120f * f, 820f * f, 80f * f, 810f * f)
            cubicTo(120f * f, 850f * f, 180f * f, 860f * f, 260f * f, 830f * f)
            cubicTo(320f * f, 860f * f, 420f * f, 870f * f, 540f * f, 840f * f)
            cubicTo(640f * f, 830f * f, 700f * f, 780f * f, 680f * f, 700f * f)
            close()
        }
        canvas.drawPath(bodyPath, stroke)

        // Four pillar legs
        canvas.drawRoundRect(RectF(320f * f, 780f * f, 410f * f, 910f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(430f * f, 800f * f, 510f * f, 910f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(550f * f, 800f * f, 630f * f, 910f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(640f * f, 780f * f, 720f * f, 910f * f), 24f * f, 24f * f, stroke)

        // Prehistoric palm tree on the right
        val trunk = Path().apply {
            moveTo(840f * f, 900f * f)
            cubicTo(845f * f, 700f * f, 820f * f, 500f * f, 860f * f, 350f * f)
            lineTo(885f * f, 350f * f)
            cubicTo(870f * f, 500f * f, 885f * f, 700f * f, 880f * f, 900f * f)
            close()
        }
        canvas.drawPath(trunk, stroke)

        // Palm leaves
        for (angle in listOf(20f, 70f, 130f, 190f, 250f)) {
            val leaf = Path().apply {
                moveTo(870f * f, 350f * f)
                quadTo((870f + 120f * Math.cos(Math.toRadians(angle.toDouble())).toFloat()) * f,
                    (350f + 120f * Math.sin(Math.toRadians(angle.toDouble())).toFloat()) * f,
                    (870f + 180f * Math.cos(Math.toRadians(angle.toDouble())).toFloat()) * f,
                    (350f + 160f * Math.sin(Math.toRadians(angle.toDouble())).toFloat()) * f)
            }
            canvas.drawPath(leaf, stroke)
        }

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawDinoEgg(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 600f * f

        // Bottom Egg Shell (Cracked half)
        val eggBottom = Path().apply {
            moveTo(260f * f, cy)
            cubicTo(240f * f, 780f * f, 360f * f, 880f * f, cx, 880f * f)
            cubicTo(664f * f, 880f * f, 784f * f, 780f * f, 764f * f, cy)
            // Jagged cracked rim
            lineTo(700f * f, cy + 40f * f)
            lineTo(640f * f, cy - 20f * f)
            lineTo(570f * f, cy + 30f * f)
            lineTo(512f * f, cy - 25f * f)
            lineTo(450f * f, cy + 30f * f)
            lineTo(380f * f, cy - 15f * f)
            lineTo(320f * f, cy + 35f * f)
            close()
        }
        canvas.drawPath(eggBottom, stroke)

        // Big decorative polka dots on shell
        canvas.drawCircle(380f * f, 740f * f, 36f * f, stroke)
        canvas.drawCircle(520f * f, 780f * f, 44f * f, stroke)
        canvas.drawCircle(650f * f, 720f * f, 38f * f, stroke)

        // Cute baby dino head emerging
        val dinoHead = Path().apply {
            moveTo(360f * f, cy - 20f * f)
            cubicTo(340f * f, 380f * f, 400f * f, 290f * f, cx, 290f * f)
            cubicTo(624f * f, 290f * f, 684f * f, 380f * f, 664f * f, cy - 20f * f)
            close()
        }
        canvas.drawPath(dinoHead, stroke)

        // Big curious baby eyes
        drawCuteEye(canvas, cx - 75f * f, 410f * f, 34f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 75f * f, 410f * f, 34f * f, stroke, eyeFill, eyeHighlight)

        // Cute little snout and open smile
        val babySmile = Path().apply {
            moveTo(cx - 30f * f, 490f * f)
            quadTo(cx, 530f * f, cx + 30f * f, 490f * f)
        }
        canvas.drawPath(babySmile, stroke)

        // Egg shell piece as a little hat on baby's head
        val hatShell = Path().apply {
            moveTo(430f * f, 310f * f)
            cubicTo(450f * f, 220f * f, 574f * f, 220f * f, 594f * f, 310f * f)
            lineTo(560f * f, 290f * f)
            lineTo(520f * f, 320f * f)
            lineTo(480f * f, 290f * f)
            close()
        }
        canvas.drawPath(hatShell, stroke)

        // Baby dino paws hanging over rim
        canvas.drawRoundRect(RectF(330f * f, cy + 10f * f, 410f * f, cy + 80f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(614f * f, cy + 10f * f, 694f * f, cy + 80f * f), 24f * f, 24f * f, stroke)

        // Nest twigs below
        val nest = Path().apply {
            moveTo(200f * f, 870f * f)
            lineTo(824f * f, 870f * f)
            lineTo(780f * f, 920f * f)
            lineTo(240f * f, 920f * f)
            close()
        }
        canvas.drawPath(nest, stroke)
    }

    fun drawDinoVolcano(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Volcano Cone
        val volcano = Path().apply {
            moveTo(140f * f, 880f * f)
            lineTo(420f * f, 380f * f)
            cubicTo(460f * f, 410f * f, 564f * f, 410f * f, 604f * f, 380f * f)
            lineTo(884f * f, 880f * f)
            close()
        }
        canvas.drawPath(volcano, stroke)

        // Lava stream down front
        val lava1 = Path().apply {
            moveTo(470f * f, 400f * f)
            cubicTo(460f * f, 500f * f, 500f * f, 580f * f, 480f * f, 680f * f)
            cubicTo(460f * f, 760f * f, 490f * f, 820f * f, 470f * f, 880f * f)
        }
        canvas.drawPath(lava1, stroke)

        val lava2 = Path().apply {
            moveTo(540f * f, 400f * f)
            cubicTo(560f * f, 520f * f, 520f * f, 620f * f, 550f * f, 740f * f)
            cubicTo(570f * f, 800f * f, 550f * f, 840f * f, 560f * f, 880f * f)
        }
        canvas.drawPath(lava2, stroke)

        // Smoke & Lava bubbles bursting from crater
        canvas.drawCircle(512f * f, 310f * f, 40f * f, stroke)
        canvas.drawCircle(440f * f, 240f * f, 32f * f, stroke)
        canvas.drawCircle(570f * f, 220f * f, 36f * f, stroke)
        canvas.drawCircle(490f * f, 160f * f, 48f * f, stroke)

        // Cute mini friendly dinosaur standing on left ridge
        canvas.drawCircle(220f * f, 720f * f, 36f * f, stroke)
        drawCuteEye(canvas, 235f * f, 715f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        val miniBody = Path().apply {
            moveTo(180f * f, 740f * f)
            cubicTo(150f * f, 780f * f, 170f * f, 840f * f, 230f * f, 840f * f)
            lineTo(260f * f, 840f * f)
            cubicTo(280f * f, 800f * f, 260f * f, 750f * f, 220f * f, 740f * f)
            close()
        }
        canvas.drawPath(miniBody, stroke)

        // Flying Pterodactyl in the sky
        val ptero = Path().apply {
            moveTo(700f * f, 200f * f)
            lineTo(780f * f, 150f * f)
            lineTo(750f * f, 210f * f)
            lineTo(830f * f, 200f * f)
            lineTo(740f * f, 240f * f)
            close()
        }
        canvas.drawPath(ptero, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawStegosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Plates along the back (Large double-row rounded plates)
        val plateX = listOf(300f, 380f, 470f, 570f, 660f, 740f)
        for (px in plateX) {
            val plate = Path().apply {
                moveTo((px - 35f) * f, 460f * f)
                cubicTo((px - 30f) * f, 360f * f, (px + 30f) * f, 360f * f, (px + 35f) * f, 460f * f)
                close()
            }
            canvas.drawPath(plate, stroke)
        }

        // Stegosaurus Body
        val bodyPath = Path().apply {
            moveTo(240f * f, 560f * f) // neck
            cubicTo(320f * f, 440f * f, 660f * f, 440f * f, 760f * f, 560f * f) // back arch
            cubicTo(820f * f, 620f * f, 890f * f, 680f * f, 920f * f, 690f * f) // tail top
            lineTo(890f * f, 730f * f) // tail bottom
            cubicTo(820f * f, 730f * f, 760f * f, 760f * f, 700f * f, 760f * f) // back thigh
            cubicTo(600f * f, 790f * f, 380f * f, 790f * f, 300f * f, 740f * f) // belly
            close()
        }
        canvas.drawPath(bodyPath, stroke)

        // Tail Spikes (Thagomizer)
        for ((tx, ty) in listOf(900f to 670f, 930f to 680f, 910f to 730f, 940f to 740f)) {
            val spike = Path().apply {
                moveTo((tx - 10f) * f, ty * f)
                lineTo((tx + 40f) * f, (ty - 10f) * f)
                lineTo(tx * f, (ty + 15f) * f)
                close()
            }
            canvas.drawPath(spike, stroke)
        }

        // Low Head
        val head = Path().apply {
            moveTo(250f * f, 550f * f)
            cubicTo(200f * f, 540f * f, 120f * f, 580f * f, 140f * f, 640f * f)
            cubicTo(160f * f, 680f * f, 240f * f, 660f * f, 270f * f, 620f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Eye & Smile
        drawCuteEye(canvas, 190f * f, 600f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(145f * f, 640f * f)
            quadTo(170f * f, 660f * f, 195f * f, 640f * f)
        }
        canvas.drawPath(smile, stroke)

        // Sturdy Legs
        canvas.drawRoundRect(RectF(280f * f, 720f * f, 370f * f, 890f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(400f * f, 740f * f, 480f * f, 890f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(580f * f, 740f * f, 660f * f, 890f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(690f * f, 720f * f, 780f * f, 890f * f), 24f * f, 24f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawPterodactyl(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Body & Head with long crest
        val head = Path().apply {
            moveTo(cx, cy - 80f * f)
            cubicTo(cx - 30f * f, cy - 180f * f, cx - 50f * f, cy - 260f * f, cx - 120f * f, cy - 320f * f) // crest tip
            cubicTo(cx - 60f * f, cy - 240f * f, cx + 10f * f, cy - 200f * f, cx + 30f * f, cy - 140f * f)
            lineTo(cx + 180f * f, cy - 90f * f) // beak tip
            lineTo(cx + 40f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Friendly Eye
        drawCuteEye(canvas, cx + 20f * f, cy - 100f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Body
        val body = Path().apply {
            moveTo(cx - 30f * f, cy - 40f * f)
            cubicTo(cx - 50f * f, cy + 60f * f, cx - 30f * f, cy + 180f * f, cx, cy + 240f * f)
            cubicTo(cx + 30f * f, cy + 180f * f, cx + 50f * f, cy + 60f * f, cx + 30f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Left Wing (Wide and curved)
        val wingL = Path().apply {
            moveTo(cx - 20f * f, cy - 20f * f)
            cubicTo(cx - 150f * f, cy - 120f * f, cx - 300f * f, cy - 180f * f, cx - 440f * f, cy - 160f * f)
            cubicTo(cx - 360f * f, cy, cx - 240f * f, cy + 80f * f, cx - 20f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(wingL, stroke)

        // Right Wing
        val wingR = Path().apply {
            moveTo(cx + 20f * f, cy - 20f * f)
            cubicTo(cx + 150f * f, cy - 120f * f, cx + 300f * f, cy - 180f * f, cx + 440f * f, cy - 160f * f)
            cubicTo(cx + 360f * f, cy, cx + 240f * f, cy + 80f * f, cx + 20f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(wingR, stroke)

        // Cute feet claws
        canvas.drawCircle(cx - 25f * f, cy + 260f * f, 16f * f, stroke)
        canvas.drawCircle(cx + 25f * f, cy + 260f * f, 16f * f, stroke)

        // Fluffy clouds below
        DrawingUtils.drawCloud(canvas, 240f * f, 820f * f, 240f * f, 90f * f, stroke)
        DrawingUtils.drawCloud(canvas, 760f * f, 780f * f, 260f * f, 100f * f, stroke)
    }

    fun drawAnkylosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Wide Armored Body
        val body = Path().apply {
            moveTo(240f * f, 560f * f)
            cubicTo(300f * f, 420f * f, 680f * f, 420f * f, 740f * f, 560f * f)
            lineTo(860f * f, 640f * f) // tail
            lineTo(850f * f, 680f * f)
            cubicTo(720f * f, 760f * f, 300f * f, 760f * f, 220f * f, 660f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Hexagonal / Rounded Armor Plates on Back
        val plates = listOf(
            340f to 500f, 440f to 480f, 540f to 480f, 640f to 500f,
            380f to 570f, 490f to 560f, 600f to 570f
        )
        for ((px, py) in plates) {
            canvas.drawCircle(px * f, py * f, 28f * f, stroke)
        }

        // Heavy Club Tail
        val club = RectF(840f * f, 620f * f, 940f * f, 700f * f)
        canvas.drawRoundRect(club, 36f * f, 36f * f, stroke)

        // Sturdy Head with cheek spikes
        val head = Path().apply {
            moveTo(240f * f, 560f * f)
            lineTo(140f * f, 600f * f)
            lineTo(130f * f, 670f * f)
            lineTo(220f * f, 660f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        drawCuteEye(canvas, 180f * f, 620f * f, 18f * f, stroke, eyeFill, eyeHighlight)

        // Four short chunky legs
        canvas.drawRoundRect(RectF(280f * f, 720f * f, 360f * f, 880f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(390f * f, 730f * f, 470f * f, 880f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(570f * f, 730f * f, 650f * f, 880f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(680f * f, 720f * f, 760f * f, 880f * f), 24f * f, 24f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBabyRaptor(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Sleek Head & Snout
        val head = Path().apply {
            moveTo(400f * f, 260f * f)
            cubicTo(450f * f, 200f * f, 600f * f, 220f * f, 680f * f, 280f * f)
            lineTo(540f * f, 340f * f)
            cubicTo(480f * f, 360f * f, 420f * f, 340f * f, 400f * f, 260f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Cute Feather crest on head
        val crest = Path().apply {
            moveTo(420f * f, 230f * f)
            lineTo(350f * f, 170f * f)
            lineTo(430f * f, 200f * f)
            lineTo(380f * f, 130f * f)
            lineTo(460f * f, 180f * f)
            close()
        }
        canvas.drawPath(crest, stroke)

        // Big Baby Eye
        drawCuteEye(canvas, 500f * f, 270f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Agile Body & Long Tail
        val body = Path().apply {
            moveTo(410f * f, 340f * f)
            cubicTo(360f * f, 440f * f, 300f * f, 500f * f, 200f * f, 480f * f)
            cubicTo(120f * f, 460f * f, 80f * f, 420f * f, 60f * f, 390f * f) // tail tip
            cubicTo(90f * f, 480f * f, 180f * f, 560f * f, 280f * f, 580f * f)
            cubicTo(380f * f, 640f * f, 480f * f, 620f * f, 530f * f, 540f * f)
            cubicTo(560f * f, 460f * f, 520f * f, 380f * f, 460f * f, 350f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Running Legs with famous sickle claw
        val leg = Path().apply {
            moveTo(380f * f, 560f * f)
            cubicTo(370f * f, 660f * f, 350f * f, 760f * f, 360f * f, 860f * f)
            lineTo(440f * f, 860f * f)
            cubicTo(430f * f, 780f * f, 440f * f, 680f * f, 460f * f, 580f * f)
            close()
        }
        canvas.drawPath(leg, stroke)

        // Curved Sickle Claw
        val claw = Path().apply {
            moveTo(360f * f, 840f * f)
            cubicTo(330f * f, 810f * f, 320f * f, 780f * f, 340f * f, 760f * f)
            lineTo(370f * f, 820f * f)
            close()
        }
        canvas.drawPath(claw, stroke)

        // Front Arms
        val arms = Path().apply {
            moveTo(480f * f, 440f * f)
            lineTo(560f * f, 480f * f)
            lineTo(540f * f, 520f * f)
            lineTo(470f * f, 480f * f)
            close()
        }
        canvas.drawPath(arms, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawDinoFootprint(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Giant Three-Toed Dino Footprint
        val print = Path().apply {
            // Middle Toe
            moveTo(cx - 50f * f, cy - 240f * f)
            lineTo(cx, cy - 320f * f)
            lineTo(cx + 50f * f, cy - 240f * f)
            lineTo(cx + 70f * f, cy - 100f * f)
            // Right Toe
            lineTo(cx + 200f * f, cy - 180f * f)
            lineTo(cx + 260f * f, cy - 120f * f)
            lineTo(cx + 170f * f, cy)
            // Right Heel curve
            cubicTo(cx + 180f * f, cy + 180f * f, cx + 80f * f, cy + 280f * f, cx, cy + 280f * f)
            // Left Heel curve
            cubicTo(cx - 80f * f, cy + 280f * f, cx - 180f * f, cy + 180f * f, cx - 170f * f, cy)
            // Left Toe
            lineTo(cx - 260f * f, cy - 120f * f)
            lineTo(cx - 200f * f, cy - 180f * f)
            lineTo(cx - 70f * f, cy - 100f * f)
            close()
        }
        canvas.drawPath(print, stroke)

        // Stepping stone cracks around
        canvas.drawCircle(cx, cy + 40f * f, 60f * f, fine)

        // Prehistoric Ferns on sides
        for (side in listOf(-1, 1)) {
            val fernX = (cx + side * 340f * f)
            val stem = Path().apply {
                moveTo(fernX, 860f * f)
                cubicTo(fernX + side * 40f * f, 660f * f, fernX, 500f * f, fernX + side * 30f * f, 420f * f)
            }
            canvas.drawPath(stem, stroke)
            for (i in 1..4) {
                val fy = (840f - i * 90f) * f
                canvas.drawCircle(fernX + side * 40f * f, fy, 22f * f, stroke)
            }
        }

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawFossilBone(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Giant Prehistoric Bone
        val bone = Path().apply {
            moveTo(cx - 240f * f, cy - 40f * f)
            // Left double knob
            cubicTo(cx - 320f * f, cy - 120f * f, cx - 400f * f, cy - 60f * f, cx - 380f * f, cy - 10f * f)
            cubicTo(cx - 410f * f, cy + 40f * f, cx - 340f * f, cy + 120f * f, cx - 260f * f, cy + 60f * f)
            lineTo(cx + 240f * f, cy + 50f * f)
            // Right double knob
            cubicTo(cx + 320f * f, cy + 120f * f, cx + 400f * f, cy + 60f * f, cx + 380f * f, cy + 10f * f)
            cubicTo(cx + 410f * f, cy - 40f * f, cx + 340f * f, cy - 120f * f, cx + 260f * f, cy - 50f * f)
            close()
        }
        canvas.drawPath(bone, stroke)

        // Cute smile on the bone
        drawCuteEye(canvas, cx - 50f * f, cy - 15f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 50f * f, cy - 15f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 30f * f, cy + 15f * f)
            quadTo(cx, cy + 35f * f, cx + 30f * f, cy + 15f * f)
        }
        canvas.drawPath(smile, stroke)

        // Ammonite spiral shell fossil beside it
        val ammonite = Path().apply {
            moveTo(cx - 160f * f, 740f * f)
            cubicTo(cx - 120f * f, 660f * f, cx - 20f * f, 660f * f, cx, 740f * f)
            cubicTo(cx + 20f * f, 820f * f, cx - 80f * f, 880f * f, cx - 180f * f, 860f * f)
            cubicTo(cx - 260f * f, 840f * f, cx - 260f * f, 720f * f, cx - 180f * f, 660f * f)
        }
        canvas.drawPath(ammonite, stroke)

        // Archeology brush
        val brushHandle = RectF(660f * f, 700f * f, 840f * f, 740f * f)
        canvas.drawRoundRect(brushHandle, 16f * f, 16f * f, stroke)
        val bristles = RectF(600f * f, 690f * f, 660f * f, 750f * f)
        canvas.drawRoundRect(bristles, 10f * f, 10f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawParasaurolophus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Parasaurolophus with iconic long backward tubular crest
        val crest = Path().apply {
            moveTo(360f * f, 280f * f)
            cubicTo(320f * f, 220f * f, 140f * f, 120f * f, 120f * f, 170f * f)
            cubicTo(110f * f, 210f * f, 220f * f, 300f * f, 320f * f, 330f * f)
            close()
        }
        canvas.drawPath(crest, stroke)

        // Head and duck-bill beak
        val head = Path().apply {
            moveTo(320f * f, 310f * f)
            cubicTo(350f * f, 240f * f, 440f * f, 240f * f, 480f * f, 300f * f)
            cubicTo(540f * f, 320f * f, 580f * f, 360f * f, 560f * f, 400f * f)
            lineTo(440f * f, 410f * f)
            cubicTo(400f * f, 440f * f, 340f * f, 420f * f, 320f * f, 360f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, 420f * f, 320f * f, 26f * f, stroke, eyeFill, eyeHighlight)

        // Body and Tail
        val body = Path().apply {
            moveTo(340f * f, 400f * f)
            cubicTo(280f * f, 500f * f, 260f * f, 620f * f, 320f * f, 720f * f)
            cubicTo(240f * f, 760f * f, 140f * f, 740f * f, 80f * f, 680f * f)
            cubicTo(100f * f, 790f * f, 220f * f, 840f * f, 320f * f, 800f * f)
            cubicTo(420f * f, 840f * f, 520f * f, 830f * f, 580f * f, 760f * f)
            cubicTo(600f * f, 650f * f, 580f * f, 500f * f, 440f * f, 420f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Legs
        val leg1 = RectF(360f * f, 720f * f, 440f * f, 860f * f)
        canvas.drawRoundRect(leg1, 30f * f, 30f * f, stroke)
        val leg2 = RectF(480f * f, 720f * f, 560f * f, 860f * f)
        canvas.drawRoundRect(leg2, 30f * f, 30f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawSpinosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Giant dramatic fan sail on back
        val sail = Path().apply {
            moveTo(280f * f, 500f * f)
            cubicTo(320f * f, 240f * f, 520f * f, 180f * f, 680f * f, 500f * f)
            close()
        }
        canvas.drawPath(sail, stroke)

        // Spine ribs inside sail
        for (i in 1..5) {
            val sx = (280f + i * 65f) * f
            canvas.drawLine(sx, 500f * f, sx, (220f + Math.abs(i - 3) * 60f) * f, fine)
        }

        // Long Crocodile-like Snout Head
        val head = Path().apply {
            moveTo(640f * f, 460f * f)
            cubicTo(680f * f, 430f * f, 760f * f, 430f * f, 840f * f, 450f * f)
            cubicTo(880f * f, 470f * f, 880f * f, 510f * f, 830f * f, 530f * f)
            lineTo(680f * f, 540f * f)
            cubicTo(640f * f, 560f * f, 600f * f, 530f * f, 600f * f, 490f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, 700f * f, 470f * f, 24f * f, stroke, eyeFill, eyeHighlight)

        // Crocodile teeth
        val teeth = Path().apply {
            moveTo(720f * f, 535f * f)
            lineTo(735f * f, 515f * f)
            lineTo(750f * f, 535f * f)
            lineTo(765f * f, 515f * f)
            lineTo(780f * f, 535f * f)
        }
        canvas.drawPath(teeth, stroke)

        // Spino Body and Long Tail
        val body = Path().apply {
            moveTo(280f * f, 500f * f)
            cubicTo(180f * f, 500f * f, 100f * f, 460f * f, 40f * f, 420f * f)
            cubicTo(80f * f, 540f * f, 160f * f, 660f * f, 280f * f, 680f * f)
            cubicTo(400f * f, 720f * f, 560f * f, 700f * f, 660f * f, 620f * f)
            lineTo(680f * f, 500f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Powerful back legs
        val leg = RectF(380f * f, 640f * f, 480f * f, 860f * f)
        canvas.drawRoundRect(leg, 30f * f, 30f * f, stroke)
        val leg2 = RectF(520f * f, 640f * f, 610f * f, 860f * f)
        canvas.drawRoundRect(leg2, 30f * f, 30f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }
}
