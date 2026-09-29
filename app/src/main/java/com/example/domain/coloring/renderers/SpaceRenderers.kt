package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object SpaceRenderers {

    fun drawRocket(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Rocket Main Fuselage
        val rocket = Path().apply {
            moveTo(cx, cy - 320f * f) // nose tip
            cubicTo(cx + 80f * f, cy - 240f * f, cx + 120f * f, cy - 80f * f, cx + 120f * f, cy + 140f * f)
            lineTo(cx - 120f * f, cy + 140f * f)
            cubicTo(cx - 120f * f, cy - 80f * f, cx - 80f * f, cy - 240f * f, cx, cy - 320f * f)
            close()
        }
        canvas.drawPath(rocket, stroke)

        // Nosecone line
        val noseLine = Path().apply {
            moveTo(cx - 65f * f, cy - 180f * f)
            quadTo(cx, cy - 160f * f, cx + 65f * f, cy - 180f * f)
        }
        canvas.drawPath(noseLine, stroke)

        // Round Porthole Window
        canvas.drawCircle(cx, cy - 40f * f, 65f * f, stroke)
        canvas.drawCircle(cx, cy - 40f * f, 45f * f, stroke)
        // Cute smiling astronaut face inside porthole
        drawCuteEye(canvas, cx - 18f * f, cy - 45f * f, 8f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 18f * f, cy - 45f * f, 8f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 15f * f, cy - 25f * f)
            quadTo(cx, cy - 15f * f, cx + 15f * f, cy - 25f * f)
        }
        canvas.drawPath(smile, stroke)

        // Fins on sides
        val finL = Path().apply {
            moveTo(cx - 120f * f, cy + 40f * f)
            cubicTo(cx - 240f * f, cy + 80f * f, cx - 260f * f, cy + 200f * f, cx - 120f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(finL, stroke)

        val finR = Path().apply {
            moveTo(cx + 120f * f, cy + 40f * f)
            cubicTo(cx + 240f * f, cy + 80f * f, cx + 260f * f, cy + 200f * f, cx + 120f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(finR, stroke)

        // Exhaust Blast Flame
        val flameOuter = Path().apply {
            moveTo(cx - 80f * f, cy + 140f * f)
            lineTo(cx - 100f * f, cy + 260f * f)
            lineTo(cx - 40f * f, cy + 220f * f)
            lineTo(cx, cy + 340f * f) // longest middle flame tip
            lineTo(cx + 40f * f, cy + 220f * f)
            lineTo(cx + 100f * f, cy + 260f * f)
            lineTo(cx + 80f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(flameOuter, stroke)

        // Smaller inner flame for coloring
        val flameInner = Path().apply {
            moveTo(cx - 40f * f, cy + 140f * f)
            lineTo(cx - 50f * f, cy + 200f * f)
            lineTo(cx, cy + 250f * f)
            lineTo(cx + 50f * f, cy + 200f * f)
            lineTo(cx + 40f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(flameInner, stroke)

        // Orbiting Stars & Little Planets
        drawStar(canvas, cx - 280f * f, cy - 200f * f, 30f * f, stroke)
        drawStar(canvas, cx + 280f * f, cy - 160f * f, 35f * f, stroke)
        drawStar(canvas, cx + 320f * f, cy + 80f * f, 25f * f, stroke)
        drawSparkle(canvas, cx - 260f * f, cy + 80f * f, 20f * f, stroke)
    }

    fun drawAstronaut(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f

        // Life Support Backpack
        canvas.drawRoundRect(RectF(cx - 210f * f, cy - 180f * f, cx + 210f * f, cy + 240f * f), 40f * f, 40f * f, stroke)

        // Large Bubble Helmet
        canvas.drawCircle(cx, cy - 120f * f, 160f * f, stroke)

        // Visor Oval
        val visor = RectF(cx - 110f * f, cy - 180f * f, cx + 110f * f, cy - 60f * f)
        canvas.drawRoundRect(visor, 50f * f, 50f * f, stroke)

        // Cute Face inside Visor
        drawCuteEye(canvas, cx - 45f * f, cy - 125f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 125f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 25f * f, cy - 90f * f)
            quadTo(cx, cy - 70f * f, cx + 25f * f, cy - 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Spacesuit Body
        val body = Path().apply {
            moveTo(cx - 120f * f, cy + 30f * f)
            cubicTo(cx - 140f * f, cy + 140f * f, cx - 140f * f, cy + 220f * f, cx - 100f * f, cy + 260f * f)
            lineTo(cx + 100f * f, cy + 260f * f)
            cubicTo(cx + 140f * f, cy + 220f * f, cx + 140f * f, cy + 140f * f, cx + 120f * f, cy + 30f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Chest Control Panel & Buttons
        canvas.drawRoundRect(RectF(cx - 50f * f, cy + 80f * f, cx + 50f * f, cy + 180f * f), 14f * f, 14f * f, stroke)
        canvas.drawCircle(cx - 20f * f, cy + 115f * f, 12f * f, stroke)
        canvas.drawCircle(cx + 20f * f, cy + 115f * f, 12f * f, stroke)
        canvas.drawRect(RectF(cx - 30f * f, cy + 145f * f, cx + 30f * f, cy + 160f * f), stroke)

        // Floating Puffy Arms
        val armL = Path().apply {
            moveTo(cx - 120f * f, cy + 50f * f)
            cubicTo(cx - 240f * f, cy + 20f * f, cx - 260f * f, cy - 40f * f, cx - 240f * f, cy - 80f * f)
            cubicTo(cx - 210f * f, cy - 90f * f, cx - 180f * f, cy - 40f * f, cx - 130f * f, cy - 10f * f)
        }
        canvas.drawPath(armL, stroke)
        canvas.drawCircle(cx - 240f * f, cy - 80f * f, 24f * f, stroke) // Glove

        val armR = Path().apply {
            moveTo(cx + 120f * f, cy + 50f * f)
            cubicTo(cx + 240f * f, cy + 80f * f, cx + 280f * f, cy + 140f * f, cx + 240f * f, cy + 180f * f)
            cubicTo(cx + 200f * f, cy + 190f * f, cx + 180f * f, cy + 140f * f, cx + 120f * f, cy + 100f * f)
        }
        canvas.drawPath(armR, stroke)
        canvas.drawCircle(cx + 240f * f, cy + 180f * f, 24f * f, stroke)

        // Puffy Legs & Big Boots
        canvas.drawRoundRect(RectF(cx - 110f * f, cy + 260f * f, cx - 20f * f, cy + 420f * f), 24f * f, 24f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 20f * f, cy + 260f * f, cx + 110f * f, cy + 420f * f), 24f * f, 24f * f, stroke)

        // Stars around
        drawStar(canvas, cx - 320f * f, cy - 240f * f, 25f * f, stroke)
        drawStar(canvas, cx + 320f * f, cy - 220f * f, 30f * f, stroke)
    }

    fun drawPlanet(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Wide Concentric Saturn Rings (Back side)
        val ringBack = RectF(cx - 420f * f, cy - 120f * f, cx + 420f * f, cy + 120f * f)
        canvas.drawArc(ringBack, 180f, 180f, false, stroke)

        // Saturn Sphere Body
        canvas.drawCircle(cx, cy, 210f * f, stroke)

        // Surface Stripes for coloring
        val stripe1 = Path().apply {
            moveTo(cx - 190f * f, cy - 80f * f)
            quadTo(cx, cy - 30f * f, cx + 190f * f, cy - 80f * f)
        }
        canvas.drawPath(stripe1, fine)

        val stripe2 = Path().apply {
            moveTo(cx - 205f * f, cy + 80f * f)
            quadTo(cx, cy + 130f * f, cx + 205f * f, cy + 80f * f)
        }
        canvas.drawPath(stripe2, fine)

        // Cute Planet Face
        drawCuteEye(canvas, cx - 65f * f, cy - 10f * f, 28f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 65f * f, cy - 10f * f, 28f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 35f * f, cy + 45f * f)
            quadTo(cx, cy + 75f * f, cx + 35f * f, cy + 45f * f)
        }
        canvas.drawPath(smile, stroke)

        // Wide Concentric Saturn Rings (Front side)
        canvas.drawArc(ringBack, 0f, 180f, false, stroke)
        val ringOuter = RectF(cx - 460f * f, cy - 145f * f, cx + 460f * f, cy + 145f * f)
        canvas.drawArc(ringOuter, 0f, 180f, false, stroke)

        // Mini Orbiting Moons
        canvas.drawCircle(cx - 340f * f, cy - 240f * f, 35f * f, stroke)
        canvas.drawCircle(cx + 360f * f, cy + 240f * f, 25f * f, stroke)

        // Stars
        drawStar(canvas, cx + 300f * f, cy - 260f * f, 28f * f, stroke)
        drawStar(canvas, cx - 280f * f, cy + 280f * f, 32f * f, stroke)
    }

    fun drawAlien(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Antenna with glowing star / bulb tip
        canvas.drawLine(cx, cy - 160f * f, cx, cy - 280f * f, stroke)
        canvas.drawCircle(cx, cy - 300f * f, 30f * f, stroke)
        drawSparkle(canvas, cx, cy - 300f * f, 16f * f, stroke)

        // Cute Pear-shaped Alien Head
        val head = Path().apply {
            moveTo(cx - 90f * f, cy - 160f * f)
            cubicTo(cx - 200f * f, cy - 100f * f, cx - 180f * f, cy + 60f * f, cx - 120f * f, cy + 120f * f)
            cubicTo(cx - 60f * f, cy + 150f * f, cx + 60f * f, cy + 150f * f, cx + 120f * f, cy + 120f * f)
            cubicTo(cx + 180f * f, cy + 60f * f, cx + 200f * f, cy - 100f * f, cx + 90f * f, cy - 160f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Three Cute Alien Eyes
        drawCuteEye(canvas, cx - 80f * f, cy - 30f * f, 28f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx, cy - 70f * f, 34f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 80f * f, cy - 30f * f, 28f * f, stroke, eyeFill, eyeHighlight)

        // Friendly Alien Smile with teeth
        val mouth = Path().apply {
            moveTo(cx - 50f * f, cy + 60f * f)
            quadTo(cx, cy + 110f * f, cx + 50f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(mouth, stroke)

        // Cute little body
        val body = Path().apply {
            moveTo(cx - 80f * f, cy + 140f * f)
            cubicTo(cx - 120f * f, cy + 240f * f, cx - 100f * f, cy + 340f * f, cx - 60f * f, cy + 380f * f)
            lineTo(cx + 60f * f, cy + 380f * f)
            cubicTo(cx + 100f * f, cy + 340f * f, cx + 120f * f, cy + 240f * f, cx + 80f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Waving 3-fingered hands
        val armL = Path().apply {
            moveTo(cx - 90f * f, cy + 180f * f)
            cubicTo(cx - 180f * f, cy + 140f * f, cx - 220f * f, cy + 80f * f, cx - 240f * f, cy + 40f * f)
        }
        canvas.drawPath(armL, stroke)
        canvas.drawCircle(cx - 240f * f, cy + 40f * f, 20f * f, stroke)

        val armR = Path().apply {
            moveTo(cx + 90f * f, cy + 180f * f)
            cubicTo(cx + 180f * f, cy + 200f * f, cx + 220f * f, cy + 240f * f, cx + 200f * f, cy + 280f * f)
        }
        canvas.drawPath(armR, stroke)
        canvas.drawCircle(cx + 200f * f, cy + 280f * f, 20f * f, stroke)

        // Cute Round Alien Feet
        canvas.drawRoundRect(RectF(cx - 90f * f, cy + 360f * f, cx - 10f * f, cy + 420f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 10f * f, cy + 360f * f, cx + 90f * f, cy + 420f * f), 20f * f, 20f * f, stroke)

        drawStar(canvas, cx + 300f * f, cy - 180f * f, 28f * f, stroke)
    }

    fun drawSpaceship(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 420f * f

        // Glass Cockpit Dome
        val dome = RectF(cx - 180f * f, cy - 200f * f, cx + 180f * f, cy + 40f * f)
        canvas.drawArc(dome, 180f, 180f, true, stroke)

        // Cute Pilot peeking inside dome
        canvas.drawCircle(cx, cy - 70f * f, 45f * f, stroke)
        drawCuteEye(canvas, cx - 16f * f, cy - 75f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 16f * f, cy - 75f * f, 10f * f, stroke, eyeFill, eyeHighlight)

        // Main Flying Saucer Disc
        val disc = RectF(cx - 380f * f, cy - 40f * f, cx + 380f * f, cy + 120f * f)
        canvas.drawOval(disc, stroke)

        // Rim Glow Lights (6 round lights)
        for (i in -2..2) {
            val lx = cx + (i * 120f) * f
            canvas.drawCircle(lx, cy + 40f * f, 18f * f, stroke)
        }

        // Tractor Beam cone coming down
        val beam = Path().apply {
            moveTo(cx - 140f * f, cy + 110f * f)
            lineTo(cx - 320f * f, 880f * f)
            lineTo(cx + 320f * f, 880f * f)
            lineTo(cx + 140f * f, cy + 110f * f)
            close()
        }
        canvas.drawPath(beam, stroke)

        // Rings inside tractor beam
        val beamRing = RectF(cx - 240f * f, 740f * f, cx + 240f * f, 800f * f)
        canvas.drawOval(beamRing, fine)

        // Little floating cow or flower in beam
        DrawingUtils.drawHeart(canvas, cx, 660f * f, 40f * f, stroke)

        drawStar(canvas, cx - 320f * f, cy - 180f * f, 30f * f, stroke)
        drawStar(canvas, cx + 320f * f, cy - 160f * f, 26f * f, stroke)
    }

    fun drawMoonLanding(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Lunar Surface with craters
        val ground = Path().apply {
            moveTo(60f * f, 740f * f)
            cubicTo(300f * f, 700f * f, 700f * f, 700f * f, 960f * f, 740f * f)
            lineTo(960f * f, 940f * f)
            lineTo(60f * f, 940f * f)
            close()
        }
        canvas.drawPath(ground, stroke)

        // Craters on moon ground
        canvas.drawOval(RectF(160f * f, 780f * f, 300f * f, 840f * f), stroke)
        canvas.drawOval(RectF(660f * f, 760f * f, 840f * f, 830f * f), stroke)
        canvas.drawOval(RectF(400f * f, 840f * f, 560f * f, 890f * f), stroke)

        // Apollo Lunar Lander (LEM)
        // Upper Ascent Stage (faceted cabin)
        val cabin = Path().apply {
            moveTo(cx - 100f * f, 420f * f)
            lineTo(cx - 140f * f, 340f * f)
            lineTo(cx - 80f * f, 260f * f)
            lineTo(cx + 80f * f, 260f * f)
            lineTo(cx + 140f * f, 340f * f)
            lineTo(cx + 100f * f, 420f * f)
            close()
        }
        canvas.drawPath(cabin, stroke)

        // Triangular Cockpit Windows
        canvas.drawRect(RectF(cx - 70f * f, 300f * f, cx - 20f * f, 340f * f), stroke)
        canvas.drawRect(RectF(cx + 20f * f, 300f * f, cx + 70f * f, 340f * f), stroke)

        // Lower Descent Stage (Octagonal gold foil base)
        canvas.drawRect(RectF(cx - 130f * f, 420f * f, cx + 130f * f, 540f * f), stroke)

        // 4 Landing Struts / Legs with round footpads
        val legL = Path().apply {
            moveTo(cx - 130f * f, 480f * f)
            lineTo(cx - 280f * f, 720f * f)
        }
        canvas.drawPath(legL, stroke)
        canvas.drawRoundRect(RectF(cx - 310f * f, 715f * f, cx - 250f * f, 735f * f), 8f * f, 8f * f, stroke)

        val legR = Path().apply {
            moveTo(cx + 130f * f, 480f * f)
            lineTo(cx + 280f * f, 720f * f)
        }
        canvas.drawPath(legR, stroke)
        canvas.drawRoundRect(RectF(cx + 250f * f, 715f * f, cx + 310f * f, 735f * f), 8f * f, 8f * f, stroke)

        // Astronaut Planted Flag
        canvas.drawLine(cx + 340f * f, 730f * f, cx + 340f * f, 560f * f, stroke)
        val flag = Path().apply {
            moveTo(cx + 340f * f, 560f * f)
            lineTo(cx + 440f * f, 560f * f)
            lineTo(cx + 440f * f, 620f * f)
            lineTo(cx + 340f * f, 620f * f)
            close()
        }
        canvas.drawPath(flag, stroke)

        // Earth in the starry black sky
        canvas.drawCircle(180f * f, 220f * f, 70f * f, stroke)
        canvas.drawArc(RectF(120f * f, 160f * f, 220f * f, 260f * f), 0f, 180f, false, fine)

        drawStar(canvas, 820f * f, 180f * f, 25f * f, stroke)
    }

    fun drawSpaceShuttle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Space Shuttle Fuselage
        val shuttle = Path().apply {
            moveTo(cx, cy - 300f * f) // nose
            cubicTo(cx + 70f * f, cy - 200f * f, cx + 90f * f, cy - 50f * f, cx + 90f * f, cy + 180f * f)
            lineTo(cx - 90f * f, cy + 180f * f)
            cubicTo(cx - 90f * f, cy - 50f * f, cx - 70f * f, cy - 200f * f, cx, cy - 300f * f)
            close()
        }
        canvas.drawPath(shuttle, stroke)

        // Black Nose Cap
        val noseCap = Path().apply {
            moveTo(cx - 35f * f, cy - 230f * f)
            quadTo(cx, cy - 210f * f, cx + 35f * f, cy - 230f * f)
            lineTo(cx, cy - 300f * f)
            close()
        }
        canvas.drawPath(noseCap, stroke)

        // Cockpit Windows (W-shape shuttle windows)
        for (i in -1..1) {
            canvas.drawRect(RectF((cx + i * 35f - 12f) * f, cy - 180f * f, (cx + i * 35f + 12f) * f, cy - 155f * f), stroke)
        }

        // Delta Wings
        val wingL = Path().apply {
            moveTo(cx - 90f * f, cy - 20f * f)
            lineTo(cx - 320f * f, cy + 180f * f)
            lineTo(cx - 90f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(wingL, stroke)

        val wingR = Path().apply {
            moveTo(cx + 90f * f, cy - 20f * f)
            lineTo(cx + 320f * f, cy + 180f * f)
            lineTo(cx + 90f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(wingR, stroke)

        // Vertical Tail Fin
        val fin = Path().apply {
            moveTo(cx - 15f * f, cy + 80f * f)
            lineTo(cx - 15f * f, cy - 60f * f)
            lineTo(cx + 15f * f, cy - 60f * f)
            lineTo(cx + 15f * f, cy + 80f * f)
            close()
        }
        canvas.drawPath(fin, stroke)

        // Rocket Engines at back
        for (i in -1..1) {
            canvas.drawRoundRect(RectF((cx + i * 50f - 18f) * f, cy + 180f * f, (cx + i * 50f + 18f) * f, cy + 240f * f), 8f * f, 8f * f, stroke)
        }

        // Stars
        drawStar(canvas, cx - 280f * f, cy - 180f * f, 30f * f, stroke)
        drawStar(canvas, cx + 280f * f, cy - 160f * f, 25f * f, stroke)
    }

    fun drawShootingStar(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 360f * f
        val cy = 400f * f

        // Trailing Swoosh Cosmic Tail
        val tail1 = Path().apply {
            moveTo(cx + 100f * f, cy - 50f * f)
            cubicTo(cx + 300f * f, cy - 100f * f, cx + 450f * f, cy + 100f * f, cx + 560f * f, cy + 300f * f)
            lineTo(cx + 510f * f, cy + 330f * f)
            cubicTo(cx + 400f * f, cy + 150f * f, cx + 250f * f, cy + 50f * f, cx + 50f * f, cy + 100f * f)
            close()
        }
        canvas.drawPath(tail1, stroke)

        val tail2 = Path().apply {
            moveTo(cx + 70f * f, cy + 60f * f)
            cubicTo(cx + 250f * f, cy + 150f * f, cx + 380f * f, cy + 300f * f, cx + 460f * f, cy + 460f * f)
            lineTo(cx + 420f * f, cy + 480f * f)
            cubicTo(cx + 340f * f, cy + 320f * f, cx + 200f * f, cy + 200f * f, cx + 30f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(tail2, stroke)

        // Giant Main 5-Pointed Star
        drawStar(canvas, cx, cy, 180f * f, stroke)

        // Cute Face on Star
        drawCuteEye(canvas, cx - 45f * f, cy - 20f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 20f * f, 26f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 30f * f, cy + 35f * f)
            quadTo(cx, cy + 65f * f, cx + 30f * f, cy + 35f * f)
        }
        canvas.drawPath(smile, stroke)

        // Rosy Cheeks
        canvas.drawCircle(cx - 70f * f, cy + 25f * f, 14f * f, fine)
        canvas.drawCircle(cx + 70f * f, cy + 25f * f, 14f * f, fine)

        // Mini sparkles surrounding
        drawSparkle(canvas, cx - 180f * f, cy - 140f * f, 25f * f, stroke)
        drawSparkle(canvas, cx - 140f * f, cy + 200f * f, 30f * f, stroke)
        drawSparkle(canvas, cx + 220f * f, cy - 180f * f, 22f * f, stroke)
    }

    fun drawSunSpace(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f

        // Radiating Triangular Sun Rays (12 big rays)
        val rays = 12
        val innerR = 210f * f
        val outerR = 340f * f
        val rayPath = Path()
        for (i in 0 until (rays * 2)) {
            val angle = i * Math.PI / rays
            val r = if (i % 2 == 0) outerR else innerR
            val px = (cx + Math.cos(angle) * r).toFloat()
            val py = (cy + Math.sin(angle) * r).toFloat()
            if (i == 0) rayPath.moveTo(px, py) else rayPath.lineTo(px, py)
        }
        rayPath.close()
        canvas.drawPath(rayPath, stroke)

        // Center Sun Circle
        canvas.drawCircle(cx, cy, innerR, stroke)

        // Cute Big Sun Eyes
        drawCuteEye(canvas, cx - 70f * f, cy - 40f * f, 34f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 70f * f, cy - 40f * f, 34f * f, stroke, eyeFill, eyeHighlight)

        // Rosy Cheeks
        canvas.drawCircle(cx - 110f * f, cy + 30f * f, 22f * f, fine)
        canvas.drawCircle(cx + 110f * f, cy + 30f * f, 22f * f, fine)

        // Big Happy Open Smile
        val smile = Path().apply {
            moveTo(cx - 50f * f, cy + 40f * f)
            quadTo(cx, cy + 120f * f, cx + 50f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(smile, stroke)

        // Sunglasses (cute accessory)
        drawSparkle(canvas, 140f * f, 160f * f, 30f * f, stroke)
        drawSparkle(canvas, 880f * f, 840f * f, 30f * f, stroke)
    }

    fun drawSpaceRover(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Rover Equipment Chassis Box
        val chassis = RectF(cx - 240f * f, cy - 100f * f, cx + 220f * f, cy + 60f * f)
        canvas.drawRoundRect(chassis, 24f * f, 24f * f, stroke)

        // Camera Mast / Head
        canvas.drawLine(cx - 160f * f, cy - 100f * f, cx - 160f * f, cy - 240f * f, stroke)
        val cameraHead = RectF(cx - 200f * f, cy - 280f * f, cx - 100f * f, cy - 230f * f)
        canvas.drawRoundRect(cameraHead, 12f * f, 12f * f, stroke)
        // Camera Eye lens
        drawCuteEye(canvas, cx - 150f * f, cy - 255f * f, 16f * f, stroke, eyeFill, eyeHighlight)

        // Solar Panels on top
        canvas.drawRect(RectF(cx - 60f * f, cy - 160f * f, cx + 180f * f, cy - 110f * f), stroke)
        canvas.drawLine(cx + 20f * f, cy - 160f * f, cx + 20f * f, cy - 110f * f, fine)
        canvas.drawLine(cx + 100f * f, cy - 160f * f, cx + 100f * f, cy - 110f * f, fine)

        // High Gain Dish Antenna
        val dish = RectF(cx + 120f * f, cy - 260f * f, cx + 220f * f, cy - 170f * f)
        canvas.drawArc(dish, 140f, 180f, false, stroke)
        canvas.drawLine(cx + 170f * f, cy - 215f * f, cx + 170f * f, cy - 160f * f, stroke)

        // Rocker-Bogie Suspension Legs
        canvas.drawLine(cx - 180f * f, cy + 60f * f, cx - 220f * f, cy + 180f * f, stroke)
        canvas.drawLine(cx, cy + 60f * f, cx, cy + 180f * f, stroke)
        canvas.drawLine(cx + 180f * f, cy + 60f * f, cx + 220f * f, cy + 180f * f, stroke)

        // 3 Large Rover Wheels with Grip Cleats
        DrawingUtils.drawWheel(canvas, cx - 220f * f, cy + 180f * f, 55f * f, stroke)
        DrawingUtils.drawWheel(canvas, cx, cy + 180f * f, 55f * f, stroke)
        DrawingUtils.drawWheel(canvas, cx + 220f * f, cy + 180f * f, 55f * f, stroke)

        // Martian Rocky Ground
        val ground = Path().apply {
            moveTo(60f * f, 770f * f)
            cubicTo(300f * f, 750f * f, 700f * f, 750f * f, 960f * f, 770f * f)
        }
        canvas.drawPath(ground, stroke)

        // Rocks
        canvas.drawRoundRect(RectF(140f * f, 790f * f, 220f * f, 830f * f), 14f * f, 14f * f, stroke)
        canvas.drawRoundRect(RectF(780f * f, 780f * f, 880f * f, 830f * f), 16f * f, 16f * f, stroke)
    }

    fun drawSaturn(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        drawPlanet(canvas, s, stroke, fine, eyeFill, eyeHighlight)
    }

    fun drawUfo(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        drawSpaceship(canvas, s, stroke, fine, eyeFill, eyeHighlight)
    }

    fun drawSolarSystem(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f

        // Smiling Central Sun
        canvas.drawCircle(cx, cy, 110f * f, stroke)
        drawCuteEye(canvas, cx - 40f * f, cy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, cy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val sunSmile = Path().apply {
            moveTo(cx - 30f * f, cy + 20f * f)
            quadTo(cx, cy + 50f * f, cx + 30f * f, cy + 20f * f)
        }
        canvas.drawPath(sunSmile, stroke)

        // Sun Rays
        for (i in 0 until 8) {
            val angle = i * Math.PI / 4.0
            val r1 = 125f * f
            val r2 = 160f * f
            val x1 = (cx + Math.cos(angle) * r1).toFloat()
            val y1 = (cy + Math.sin(angle) * r1).toFloat()
            val x2 = (cx + Math.cos(angle) * r2).toFloat()
            val y2 = (cy + Math.sin(angle) * r2).toFloat()
            canvas.drawLine(x1, y1, x2, y2, stroke)
        }

        // 3 Elliptical Orbit Rings
        val orbit1 = RectF(cx - 240f * f, cy - 240f * f, cx + 240f * f, cy + 240f * f)
        canvas.drawOval(orbit1, fine)
        val orbit2 = RectF(cx - 340f * f, cy - 340f * f, cx + 340f * f, cy + 340f * f)
        canvas.drawOval(orbit2, fine)
        val orbit3 = RectF(cx - 430f * f, cy - 430f * f, cx + 430f * f, cy + 430f * f)
        canvas.drawOval(orbit3, fine)

        // Orbiting cute planets
        canvas.drawCircle(cx + 240f * f, cy, 32f * f, stroke) // Inner planet
        canvas.drawCircle(cx - 240f * f, cy - 240f * f, 45f * f, stroke) // Earth with moon
        canvas.drawCircle(cx - 275f * f, cy - 275f * f, 12f * f, stroke) // moon
        canvas.drawCircle(cx, cy + 340f * f, 55f * f, stroke) // Ringed outer planet
        val ring = RectF(cx - 80f * f, cy + 325f * f, cx + 80f * f, cy + 355f * f)
        canvas.drawOval(ring, stroke)

        // Stars
        DrawingUtils.drawStar(canvas, 180f * f, 200f * f, 28f * f, stroke)
        DrawingUtils.drawStar(canvas, 820f * f, 220f * f, 24f * f, stroke)
        DrawingUtils.drawStar(canvas, 840f * f, 800f * f, 30f * f, stroke)
    }

    fun drawSpaceStation(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 512f * f

        // Central Habitation Core Module
        val coreRect = RectF(cx - 80f * f, cy - 140f * f, cx + 80f * f, cy + 140f * f)
        canvas.drawRoundRect(coreRect, 40f * f, 40f * f, stroke)

        // Round Observation Cupola Window
        canvas.drawCircle(cx, cy, 45f * f, stroke)
        canvas.drawCircle(cx, cy, 32f * f, stroke)
        drawCuteEye(canvas, cx, cy - 5f * f, 12f * f, stroke, eyeFill, eyeHighlight)

        // Central Truss Spanning Left to Right
        val truss = RectF(cx - 360f * f, cy - 20f * f, cx + 360f * f, cy + 20f * f)
        canvas.drawRect(truss, stroke)

        // Massive Left Solar Array Wings
        val panelL1 = RectF(cx - 440f * f, cy - 220f * f, cx - 260f * f, cy - 40f * f)
        canvas.drawRect(panelL1, stroke)
        for (i in 1..3) {
            val px = (cx - 440f + i * 45f) * f
            canvas.drawLine(px, cy - 220f * f, px, cy - 40f * f, fine)
        }
        val panelL2 = RectF(cx - 440f * f, cy + 40f * f, cx - 260f * f, cy + 220f * f)
        canvas.drawRect(panelL2, stroke)
        for (i in 1..3) {
            val px = (cx - 440f + i * 45f) * f
            canvas.drawLine(px, cy + 40f * f, px, cy + 220f * f, fine)
        }

        // Massive Right Solar Array Wings
        val panelR1 = RectF(cx + 260f * f, cy - 220f * f, cx + 440f * f, cy - 40f * f)
        canvas.drawRect(panelR1, stroke)
        for (i in 1..3) {
            val px = (cx + 260f + i * 45f) * f
            canvas.drawLine(px, cy - 220f * f, px, cy - 40f * f, fine)
        }
        val panelR2 = RectF(cx + 260f * f, cy + 40f * f, cx + 440f * f, cy + 220f * f)
        canvas.drawRect(panelR2, stroke)
        for (i in 1..3) {
            val px = (cx + 260f + i * 45f) * f
            canvas.drawLine(px, cy + 40f * f, px, cy + 220f * f, fine)
        }

        // Docking Node & Attached Capsule
        canvas.drawCircle(cx, cy - 180f * f, 35f * f, stroke)

        // Earth Curve in Background
        val earthRect = RectF(cx - 500f * f, 780f * f, cx + 500f * f, 1400f * f)
        canvas.drawOval(earthRect, stroke)

        // Stars
        DrawingUtils.drawStar(canvas, 140f * f, 140f * f, 22f * f, stroke)
        DrawingUtils.drawStar(canvas, 880f * f, 120f * f, 25f * f, stroke)
    }
}
