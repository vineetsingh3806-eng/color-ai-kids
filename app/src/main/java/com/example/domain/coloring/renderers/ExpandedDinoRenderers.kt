package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass

object ExpandedDinoRenderers {

    fun drawVelociraptor(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Slender predatory head with snout
        val head = Path().apply {
            moveTo(360f * f, 240f * f)
            cubicTo(420f * f, 180f * f, 620f * f, 200f * f, 740f * f, 270f * f)
            cubicTo(760f * f, 300f * f, 730f * f, 340f * f, 660f * f, 350f * f)
            lineTo(520f * f, 350f * f)
            cubicTo(480f * f, 400f * f, 400f * f, 420f * f, 350f * f, 360f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, 520f * f, 270f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Sharp teeth
        val teeth = Path().apply {
            moveTo(560f * f, 350f * f)
            lineTo(580f * f, 380f * f); lineTo(600f * f, 350f * f)
            lineTo(620f * f, 380f * f); lineTo(640f * f, 350f * f)
        }
        canvas.drawPath(teeth, fine)

        // Agile body with long tail
        val body = Path().apply {
            moveTo(380f * f, 370f * f)
            cubicTo(320f * f, 440f * f, 260f * f, 520f * f, 320f * f, 660f * f)
            cubicTo(200f * f, 660f * f, 120f * f, 560f * f, 80f * f, 480f * f)
            cubicTo(100f * f, 640f * f, 220f * f, 740f * f, 360f * f, 720f * f)
            cubicTo(460f * f, 760f * f, 560f * f, 700f * f, 580f * f, 600f * f)
            cubicTo(600f * f, 480f * f, 500f * f, 400f * f, 420f * f, 370f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Legs with sickle claw
        val leg = Path().apply {
            moveTo(440f * f, 640f * f)
            lineTo(460f * f, 780f * f)
            lineTo(540f * f, 860f * f)
            lineTo(500f * f, 860f * f)
            cubicTo(450f * f, 820f * f, 420f * f, 750f * f, 400f * f, 670f * f)
            close()
        }
        canvas.drawPath(leg, stroke)
        // Sickle claw
        val claw = Path().apply {
            moveTo(540f * f, 860f * f)
            cubicTo(560f * f, 820f * f, 580f * f, 810f * f, 560f * f, 780f * f)
            cubicTo(540f * f, 810f * f, 520f * f, 830f * f, 500f * f, 860f * f)
        }
        canvas.drawPath(claw, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawDiplodocus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Very long horizontal neck and whip-like tail
        val dino = Path().apply {
            moveTo(60f * f, 620f * f) // Tip of tail
            cubicTo(160f * f, 580f * f, 260f * f, 520f * f, 360f * f, 520f * f) // Back
            cubicTo(500f * f, 440f * f, 650f * f, 300f * f, 760f * f, 220f * f) // Long neck
            cubicTo(820f * f, 180f * f, 880f * f, 220f * f, 860f * f, 280f * f) // Head
            cubicTo(820f * f, 320f * f, 740f * f, 360f * f, 660f * f, 440f * f) // Neck bottom
            cubicTo(580f * f, 540f * f, 560f * f, 640f * f, 540f * f, 720f * f) // Chest
            // Front leg
            lineTo(560f * f, 860f * f); lineTo(500f * f, 860f * f); lineTo(480f * f, 720f * f)
            // Belly
            cubicTo(440f * f, 740f * f, 380f * f, 740f * f, 360f * f, 720f * f)
            // Back leg
            lineTo(370f * f, 860f * f); lineTo(310f * f, 860f * f); lineTo(300f * f, 680f * f)
            // Tail bottom
            cubicTo(220f * f, 700f * f, 120f * f, 680f * f, 60f * f, 620f * f)
            close()
        }
        canvas.drawPath(dino, stroke)
        drawCuteEye(canvas, 820f * f, 220f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        // Cute back spots
        for (i in 0..4) {
            canvas.drawCircle((340f + i * 50f) * f, (560f + i * 15f) * f, 18f * f, fine)
        }
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawPachycephalosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Dome head
        val dome = Path().apply {
            moveTo(480f * f, 280f * f)
            cubicTo(480f * f, 120f * f, 720f * f, 120f * f, 720f * f, 280f * f)
            close()
        }
        canvas.drawPath(dome, stroke)
        // Head studs
        for (i in 0..5) {
            canvas.drawCircle((500f + i * 40f) * f, 280f * f, 12f * f, stroke)
        }
        // Snout and body
        val body = Path().apply {
            moveTo(680f * f, 280f * f)
            cubicTo(740f * f, 300f * f, 760f * f, 360f * f, 720f * f, 400f * f)
            lineTo(600f * f, 400f * f)
            cubicTo(560f * f, 480f * f, 480f * f, 520f * f, 460f * f, 620f * f)
            cubicTo(360f * f, 660f * f, 220f * f, 640f * f, 140f * f, 560f * f) // Tail
            cubicTo(180f * f, 720f * f, 320f * f, 780f * f, 440f * f, 760f * f)
            cubicTo(520f * f, 780f * f, 600f * f, 720f * f, 620f * f, 620f * f)
            cubicTo(620f * f, 520f * f, 540f * f, 420f * f, 480f * f, 280f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        drawCuteEye(canvas, 620f * f, 320f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Sturdy Legs
        canvas.drawRoundRect(RectF(480f * f, 720f * f, 540f * f, 860f * f), 15f * f, 15f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawAllosaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Allosaurus with eye crests
        val head = Path().apply {
            moveTo(420f * f, 260f * f)
            lineTo(460f * f, 190f * f); lineTo(490f * f, 240f * f) // Head crest
            cubicTo(560f * f, 220f * f, 680f * f, 240f * f, 760f * f, 320f * f)
            cubicTo(740f * f, 380f * f, 660f * f, 400f * f, 560f * f, 390f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, 540f * f, 280f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Body and powerful legs
        val body = Path().apply {
            moveTo(440f * f, 390f * f)
            cubicTo(380f * f, 460f * f, 300f * f, 540f * f, 360f * f, 680f * f)
            cubicTo(240f * f, 700f * f, 140f * f, 660f * f, 80f * f, 580f * f)
            cubicTo(120f * f, 760f * f, 280f * f, 800f * f, 420f * f, 760f * f)
            cubicTo(520f * f, 800f * f, 620f * f, 740f * f, 620f * f, 600f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Leg
        canvas.drawRoundRect(RectF(460f * f, 680f * f, 540f * f, 860f * f), 20f * f, 20f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawPlesiosaur(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Marine long-neck dinosaur swimming
        val plesio = Path().apply {
            moveTo(480f * f, 140f * f) // Small head
            cubicTo(560f * f, 120f * f, 600f * f, 180f * f, 540f * f, 220f * f)
            cubicTo(460f * f, 280f * f, 420f * f, 420f * f, 460f * f, 540f * f) // Graceful curving neck
            cubicTo(540f * f, 520f * f, 660f * f, 560f * f, 720f * f, 660f * f) // Round body
            cubicTo(820f * f, 700f * f, 900f * f, 720f * f, 940f * f, 760f * f) // Short tail
            cubicTo(860f * f, 780f * f, 760f * f, 780f * f, 680f * f, 760f * f)
            cubicTo(560f * f, 800f * f, 440f * f, 760f * f, 360f * f, 680f * f)
            cubicTo(320f * f, 560f * f, 360f * f, 380f * f, 420f * f, 240f * f)
            close()
        }
        canvas.drawPath(plesio, stroke)
        drawCuteEye(canvas, 520f * f, 160f * f, 18f * f, stroke, eyeFill, eyeHighlight)

        // 4 Flippers / Paddles
        val flipper1 = Path().apply {
            moveTo(420f * f, 680f * f)
            cubicTo(340f * f, 760f * f, 260f * f, 820f * f, 220f * f, 840f * f)
            cubicTo(260f * f, 780f * f, 320f * f, 720f * f, 400f * f, 660f * f)
            close()
        }
        canvas.drawPath(flipper1, stroke)
        val flipper2 = Path().apply {
            moveTo(640f * f, 720f * f)
            cubicTo(680f * f, 820f * f, 720f * f, 860f * f, 780f * f, 880f * f)
            cubicTo(740f * f, 820f * f, 700f * f, 760f * f, 680f * f, 700f * f)
            close()
        }
        canvas.drawPath(flipper2, stroke)
        // Ocean ripples
        for (i in 0..3) {
            val y = (780f + i * 35f) * f
            canvas.drawLine(100f * f, y, 924f * f, y, fine)
        }
    }

    fun drawArchaeopteryx(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Ancient feathered dino bird
        val body = Path().apply {
            moveTo(460f * f, 320f * f)
            cubicTo(520f * f, 260f * f, 620f * f, 260f * f, 640f * f, 340f * f) // Head
            lineTo(720f * f, 350f * f); lineTo(640f * f, 380f * f) // Beak with teeth
            cubicTo(620f * f, 460f * f, 560f * f, 540f * f, 480f * f, 600f * f) // Belly
            cubicTo(380f * f, 680f * f, 260f * f, 740f * f, 160f * f, 760f * f) // Long feathered tail
            cubicTo(260f * f, 680f * f, 340f * f, 580f * f, 380f * f, 460f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        drawCuteEye(canvas, 560f * f, 320f * f, 20f * f, stroke, eyeFill, eyeHighlight)

        // Broad feathered wings
        val wing = Path().apply {
            moveTo(440f * f, 420f * f)
            cubicTo(500f * f, 320f * f, 680f * f, 280f * f, 780f * f, 240f * f)
            cubicTo(740f * f, 360f * f, 640f * f, 480f * f, 480f * f, 520f * f)
            close()
        }
        canvas.drawPath(wing, stroke)
        // Tree branch
        canvas.drawRoundRect(RectF(180f * f, 780f * f, 840f * f, 840f * f), 20f * f, 20f * f, stroke)
    }

    fun drawCarnotaurus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Bull-horned dinosaur head
        val head = Path().apply {
            moveTo(400f * f, 280f * f)
            lineTo(440f * f, 160f * f); lineTo(480f * f, 260f * f) // Sharp brow horn
            cubicTo(560f * f, 240f * f, 680f * f, 260f * f, 740f * f, 340f * f)
            cubicTo(720f * f, 420f * f, 620f * f, 440f * f, 520f * f, 420f * f)
            close()
        }
        canvas.drawPath(head, stroke)
        drawCuteEye(canvas, 520f * f, 310f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Sturdy running body
        val body = Path().apply {
            moveTo(420f * f, 420f * f)
            cubicTo(340f * f, 480f * f, 280f * f, 560f * f, 340f * f, 680f * f)
            cubicTo(220f * f, 700f * f, 120f * f, 640f * f, 60f * f, 560f * f) // Tail
            cubicTo(100f * f, 740f * f, 260f * f, 800f * f, 400f * f, 760f * f)
            cubicTo(500f * f, 800f * f, 600f * f, 740f * f, 620f * f, 600f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Running legs
        canvas.drawRoundRect(RectF(460f * f, 660f * f, 540f * f, 860f * f), 20f * f, 20f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawIguanodon(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Friendly herbivore with prominent thumb spike
        val dino = Path().apply {
            moveTo(420f * f, 300f * f)
            cubicTo(480f * f, 220f * f, 600f * f, 220f * f, 680f * f, 300f * f) // Snout
            cubicTo(700f * f, 360f * f, 660f * f, 420f * f, 580f * f, 440f * f)
            cubicTo(560f * f, 520f * f, 520f * f, 600f * f, 540f * f, 700f * f) // Chest
            cubicTo(480f * f, 760f * f, 380f * f, 780f * f, 300f * f, 720f * f) // Body
            cubicTo(200f * f, 720f * f, 120f * f, 660f * f, 80f * f, 600f * f) // Tail
            cubicTo(140f * f, 780f * f, 280f * f, 820f * f, 420f * f, 780f * f)
            close()
        }
        canvas.drawPath(dino, stroke)
        drawCuteEye(canvas, 560f * f, 300f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        // Thumb spike hand
        val hand = Path().apply {
            moveTo(540f * f, 520f * f)
            lineTo(620f * f, 500f * f)
            lineTo(660f * f, 460f * f) // Thumb spike pointing up!
            lineTo(640f * f, 520f * f)
            lineTo(560f * f, 560f * f)
            close()
        }
        canvas.drawPath(hand, stroke)
        // Legs
        canvas.drawRoundRect(RectF(400f * f, 720f * f, 480f * f, 860f * f), 20f * f, 20f * f, stroke)
        drawGroundGrass(canvas, s, stroke)
    }

    fun drawDinoFossil(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Large fossil rock outline
        val rock = Path().apply {
            moveTo(160f * f, 300f * f)
            cubicTo(220f * f, 160f * f, 800f * f, 160f * f, 860f * f, 320f * f)
            cubicTo(920f * f, 480f * f, 900f * f, 740f * f, 820f * f, 840f * f)
            cubicTo(700f * f, 900f * f, 300f * f, 920f * f, 180f * f, 820f * f)
            cubicTo(120f * f, 700f * f, 100f * f, 440f * f, 160f * f, 300f * f)
            close()
        }
        canvas.drawPath(rock, stroke)
        // Dino skull inside rock
        val skull = Path().apply {
            moveTo(360f * f, 420f * f)
            cubicTo(440f * f, 360f * f, 620f * f, 360f * f, 720f * f, 440f * f)
            lineTo(720f * f, 520f * f); lineTo(580f * f, 520f * f)
            cubicTo(540f * f, 580f * f, 420f * f, 600f * f, 360f * f, 520f * f)
            close()
        }
        canvas.drawPath(skull, stroke)
        // Big eye socket hole
        canvas.drawCircle(540f * f, 440f * f, 36f * f, stroke)
        // Teeth
        for (i in 0..4) {
            val tx = (600f + i * 22f) * f
            canvas.drawLine(tx, 520f * f, tx, 550f * f, stroke)
        }
        // Spine vertebrae
        for (i in 0..4) {
            canvas.drawRoundRect(RectF((280f + i * 50f) * f, 640f * f, (320f + i * 50f) * f, 700f * f), 8f * f, 8f * f, stroke)
        }
    }

    fun drawBabySauropod(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Cute baby longneck sitting in leafy nest
        val nest = Path().apply {
            moveTo(200f * f, 720f * f)
            cubicTo(320f * f, 860f * f, 700f * f, 860f * f, 820f * f, 720f * f)
            cubicTo(740f * f, 700f * f, 640f * f, 720f * f, 512f * f, 710f * f)
            cubicTo(380f * f, 720f * f, 280f * f, 700f * f, 200f * f, 720f * f)
            close()
        }
        canvas.drawPath(nest, stroke)

        // Cute baby body & head
        val body = Path().apply {
            moveTo(420f * f, 700f * f)
            cubicTo(360f * f, 640f * f, 380f * f, 520f * f, 440f * f, 460f * f) // Back
            cubicTo(460f * f, 360f * f, 440f * f, 260f * f, 480f * f, 200f * f) // Neck
            cubicTo(520f * f, 160f * f, 620f * f, 160f * f, 640f * f, 220f * f) // Head
            cubicTo(640f * f, 280f * f, 560f * f, 320f * f, 540f * f, 420f * f) // Front neck
            cubicTo(600f * f, 480f * f, 640f * f, 580f * f, 600f * f, 700f * f) // Chest
            close()
        }
        canvas.drawPath(body, stroke)
        drawCuteEye(canvas, 560f * f, 220f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Cheerful smile
        val smile = Path().apply {
            moveTo(580f * f, 260f * f)
            quadTo(600f * f, 280f * f, 620f * f, 260f * f)
        }
        canvas.drawPath(smile, fine)
        // Fern leaf in mouth
        val leaf = Path().apply {
            moveTo(620f * f, 260f * f)
            cubicTo(680f * f, 240f * f, 740f * f, 280f * f, 780f * f, 260f * f)
            cubicTo(740f * f, 300f * f, 680f * f, 280f * f, 620f * f, 260f * f)
        }
        canvas.drawPath(leaf, stroke)
    }
}
