package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCloud
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundWaves
import com.example.domain.coloring.renderers.DrawingUtils.drawWheel

object VehicleRenderers {

    fun drawRaceCar(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Sleek Race Car Body
        val body = Path().apply {
            moveTo(140f * f, 660f * f)
            lineTo(180f * f, 560f * f)
            cubicTo(260f * f, 520f * f, 380f * f, 480f * f, 480f * f, 460f * f)
            lineTo(560f * f, 460f * f)
            cubicTo(680f * f, 480f * f, 800f * f, 580f * f, 880f * f, 640f * f)
            lineTo(880f * f, 700f * f)
            lineTo(140f * f, 700f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Rear Wing / Spoiler
        val spoiler = Path().apply {
            moveTo(120f * f, 480f * f)
            lineTo(220f * f, 480f * f)
            lineTo(200f * f, 540f * f)
            lineTo(140f * f, 540f * f)
            close()
        }
        canvas.drawPath(spoiler, stroke)
        canvas.drawLine(150f * f, 540f * f, 150f * f, 620f * f, stroke)
        canvas.drawLine(190f * f, 540f * f, 190f * f, 620f * f, stroke)

        // Driver Cockpit & Helmet
        canvas.drawArc(RectF(440f * f, 400f * f, 580f * f, 520f * f), 180f, 180f, true, stroke)
        canvas.drawCircle(512f * f, 410f * f, 32f * f, stroke) // Helmet
        val visor = RectF(510f * f, 400f * f, 545f * f, 425f * f)
        canvas.drawRoundRect(visor, 8f * f, 8f * f, eyeFill)

        // Racing Number #1 Circle
        canvas.drawCircle(400f * f, 590f * f, 45f * f, stroke)
        val numOne = Path().apply {
            moveTo(390f * f, 570f * f)
            lineTo(405f * f, 555f * f)
            lineTo(405f * f, 620f * f)
            moveTo(385f * f, 620f * f)
            lineTo(425f * f, 620f * f)
        }
        canvas.drawPath(numOne, stroke)

        // Big Racing Wheels
        drawWheel(canvas, 280f * f, 700f * f, 75f * f, stroke)
        drawWheel(canvas, 740f * f, 700f * f, 75f * f, stroke)

        // Speed Lines on Ground
        canvas.drawLine(80f * f, 785f * f, 380f * f, 785f * f, stroke)
        canvas.drawLine(440f * f, 785f * f, 940f * f, 785f * f, stroke)
        canvas.drawLine(180f * f, 825f * f, 840f * f, 825f * f, fine)
    }

    fun drawFireTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Main Truck Box Body
        val truckBody = Path().apply {
            moveTo(140f * f, 420f * f)
            lineTo(620f * f, 420f * f)
            lineTo(620f * f, 480f * f)
            lineTo(840f * f, 480f * f)
            cubicTo(880f * f, 480f * f, 900f * f, 520f * f, 900f * f, 560f * f)
            lineTo(900f * f, 700f * f)
            lineTo(140f * f, 700f * f)
            close()
        }
        canvas.drawPath(truckBody, stroke)

        // Cabin Windshield & Window
        val window = Path().apply {
            moveTo(650f * f, 510f * f)
            lineTo(820f * f, 510f * f)
            lineTo(850f * f, 580f * f)
            lineTo(650f * f, 580f * f)
            close()
        }
        canvas.drawPath(window, stroke)

        // Roof Siren Light
        val siren = RectF(720f * f, 430f * f, 770f * f, 480f * f)
        canvas.drawRoundRect(siren, 14f * f, 14f * f, stroke)

        // Big Ladder on Roof
        val ladderTop = 330f * f
        val ladderBottom = 380f * f
        canvas.drawLine(160f * f, ladderTop, 600f * f, ladderTop, stroke)
        canvas.drawLine(160f * f, ladderBottom, 600f * f, ladderBottom, stroke)
        for (x in 200..560 step 45) {
            canvas.drawLine(x * f, ladderTop, x * f, ladderBottom, stroke)
        }

        // Water Hose Reel
        canvas.drawCircle(320f * f, 560f * f, 55f * f, stroke)
        canvas.drawCircle(320f * f, 560f * f, 35f * f, stroke)
        canvas.drawCircle(320f * f, 560f * f, 15f * f, stroke)

        // Equipment Compartment Doors
        canvas.drawRect(RectF(430f * f, 500f * f, 570f * f, 640f * f), stroke)
        canvas.drawLine(430f * f, 570f * f, 570f * f, 570f * f, fine)

        // Front Grille & Headlight
        canvas.drawRoundRect(RectF(860f * f, 620f * f, 900f * f, 660f * f), 10f * f, 10f * f, stroke)

        // Three Heavy Truck Wheels
        drawWheel(canvas, 240f * f, 710f * f, 65f * f, stroke)
        drawWheel(canvas, 420f * f, 710f * f, 65f * f, stroke)
        drawWheel(canvas, 760f * f, 710f * f, 65f * f, stroke)

        // Ground line
        canvas.drawLine(80f * f, 785f * f, 940f * f, 785f * f, stroke)
    }

    fun drawPoliceCar(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Police Car Body
        val body = Path().apply {
            moveTo(140f * f, 660f * f)
            lineTo(180f * f, 560f * f)
            lineTo(320f * f, 540f * f)
            lineTo(420f * f, 420f * f)
            lineTo(660f * f, 420f * f)
            lineTo(760f * f, 540f * f)
            lineTo(880f * f, 570f * f)
            cubicTo(900f * f, 600f * f, 900f * f, 660f * f, 880f * f, 680f * f)
            lineTo(140f * f, 680f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Cabin Windows
        val winF = Path().apply {
            moveTo(550f * f, 440f * f)
            lineTo(650f * f, 440f * f)
            lineTo(730f * f, 530f * f)
            lineTo(550f * f, 530f * f)
            close()
        }
        canvas.drawPath(winF, stroke)

        val winB = Path().apply {
            moveTo(440f * f, 440f * f)
            lineTo(530f * f, 440f * f)
            lineTo(530f * f, 530f * f)
            lineTo(360f * f, 530f * f)
            close()
        }
        canvas.drawPath(winB, stroke)

        // Flashing Roof Light Bar
        val lightBar = RectF(500f * f, 380f * f, 600f * f, 420f * f)
        canvas.drawRoundRect(lightBar, 12f * f, 12f * f, stroke)
        canvas.drawLine(550f * f, 380f * f, 550f * f, 420f * f, stroke)

        // Star Door Badge
        DrawingUtils.drawStar(canvas, 540f * f, 610f * f, 34f * f, stroke)

        // Wheels
        drawWheel(canvas, 280f * f, 690f * f, 65f * f, stroke)
        drawWheel(canvas, 740f * f, 690f * f, 65f * f, stroke)

        // Ground line
        canvas.drawLine(80f * f, 765f * f, 940f * f, 765f * f, stroke)
    }

    fun drawMonsterTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // High Truck Cabin & Bed
        val truck = Path().apply {
            moveTo(240f * f, 380f * f)
            lineTo(520f * f, 380f * f)
            lineTo(640f * f, 460f * f)
            lineTo(780f * f, 470f * f)
            cubicTo(800f * f, 500f * f, 800f * f, 540f * f, 780f * f, 570f * f)
            lineTo(220f * f, 570f * f)
            lineTo(220f * f, 460f * f)
            close()
        }
        canvas.drawPath(truck, stroke)

        // Roll Cage on bed
        val rollBar = Path().apply {
            moveTo(280f * f, 380f * f)
            lineTo(320f * f, 320f * f)
            lineTo(500f * f, 320f * f)
            lineTo(520f * f, 380f * f)
        }
        canvas.drawPath(rollBar, stroke)

        // Window
        val window = Path().apply {
            moveTo(360f * f, 400f * f)
            lineTo(500f * f, 400f * f)
            lineTo(590f * f, 460f * f)
            lineTo(360f * f, 460f * f)
            close()
        }
        canvas.drawPath(window, stroke)

        // Cool Flame Decal on side
        val flame = Path().apply {
            moveTo(400f * f, 520f * f)
            cubicTo(480f * f, 500f * f, 540f * f, 540f * f, 620f * f, 520f * f)
            lineTo(560f * f, 545f * f)
            lineTo(680f * f, 540f * f)
            lineTo(400f * f, 555f * f)
            close()
        }
        canvas.drawPath(flame, stroke)

        // Heavy Lifted Suspension Springs
        for (cx in listOf(320f, 700f)) {
            val spring = Path().apply {
                moveTo(cx * f, 570f * f)
                lineTo((cx - 20f) * f, 600f * f)
                lineTo((cx + 20f) * f, 630f * f)
                lineTo((cx - 20f) * f, 660f * f)
                lineTo(cx * f, 690f * f)
            }
            canvas.drawPath(spring, stroke)
        }

        // ENORMOUS Monster Wheels with deep treads
        val wheelL = RectF(180f * f, 560f * f, 440f * f, 820f * f)
        canvas.drawOval(wheelL, stroke)
        canvas.drawCircle(310f * f, 690f * f, 60f * f, stroke)
        canvas.drawCircle(310f * f, 690f * f, 25f * f, eyeFill)

        val wheelR = RectF(580f * f, 560f * f, 840f * f, 820f * f)
        canvas.drawOval(wheelR, stroke)
        canvas.drawCircle(710f * f, 690f * f, 60f * f, stroke)
        canvas.drawCircle(710f * f, 690f * f, 25f * f, eyeFill)

        // Ground dirt ramp
        val dirt = Path().apply {
            moveTo(80f * f, 830f * f)
            quadTo(512f * f, 800f * f, 940f * f, 830f * f)
        }
        canvas.drawPath(dirt, stroke)
    }

    fun drawSchoolBus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Classic Bus Body
        val bus = Path().apply {
            moveTo(140f * f, 380f * f)
            lineTo(720f * f, 380f * f)
            lineTo(720f * f, 460f * f)
            lineTo(880f * f, 480f * f)
            cubicTo(900f * f, 520f * f, 900f * f, 600f * f, 880f * f, 660f * f)
            lineTo(140f * f, 660f * f)
            close()
        }
        canvas.drawPath(bus, stroke)

        // Passenger Windows (4 square windows)
        for (i in 0..3) {
            val wx = (180 + i * 110).toFloat()
            canvas.drawRoundRect(RectF(wx * f, 420f * f, (wx + 85) * f, 510f * f), 12f * f, 12f * f, stroke)
        }

        // Driver Large Windshield
        canvas.drawRoundRect(RectF(660f * f, 420f * f, 760f * f, 530f * f), 12f * f, 12f * f, stroke)

        // Stop Sign Octagon on side
        val sign = Path().apply {
            moveTo(130f * f, 540f * f)
            lineTo(150f * f, 520f * f)
            lineTo(180f * f, 520f * f)
            lineTo(200f * f, 540f * f)
            lineTo(200f * f, 570f * f)
            lineTo(180f * f, 590f * f)
            lineTo(150f * f, 590f * f)
            lineTo(130f * f, 570f * f)
            close()
        }
        canvas.drawPath(sign, stroke)

        // Big Rub Rails (Black stripes on school bus)
        canvas.drawLine(140f * f, 560f * f, 720f * f, 560f * f, stroke)
        canvas.drawLine(140f * f, 610f * f, 720f * f, 610f * f, stroke)

        // Front bumper & grille
        canvas.drawRoundRect(RectF(860f * f, 580f * f, 895f * f, 640f * f), 10f * f, 10f * f, stroke)

        // Wheels
        drawWheel(canvas, 280f * f, 670f * f, 65f * f, stroke)
        drawWheel(canvas, 740f * f, 670f * f, 65f * f, stroke)

        canvas.drawLine(80f * f, 745f * f, 940f * f, 745f * f, stroke)
    }

    fun drawTractor(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Tractor Hood & Engine
        val hood = Path().apply {
            moveTo(440f * f, 440f * f)
            lineTo(780f * f, 440f * f)
            cubicTo(820f * f, 440f * f, 840f * f, 480f * f, 840f * f, 540f * f)
            lineTo(840f * f, 660f * f)
            lineTo(440f * f, 660f * f)
            close()
        }
        canvas.drawPath(hood, stroke)

        // Driver Cabin / Roll Bar
        val cab = Path().apply {
            moveTo(240f * f, 600f * f)
            lineTo(240f * f, 340f * f)
            lineTo(460f * f, 340f * f)
            lineTo(460f * f, 440f * f)
        }
        canvas.drawPath(cab, stroke)

        // Steering Wheel
        canvas.drawLine(440f * f, 440f * f, 410f * f, 400f * f, stroke)
        canvas.drawCircle(400f * f, 390f * f, 20f * f, stroke)

        // Vertical Exhaust Pipe puffing smoke
        canvas.drawLine(720f * f, 440f * f, 720f * f, 300f * f, stroke)
        canvas.drawLine(735f * f, 440f * f, 735f * f, 300f * f, stroke)
        // Smoke rings
        canvas.drawCircle(728f * f, 250f * f, 22f * f, fine)
        canvas.drawCircle(750f * f, 190f * f, 30f * f, fine)

        // Giant Rear Wheel
        drawWheel(canvas, 320f * f, 660f * f, 110f * f, stroke)
        // Smaller Front Wheel
        drawWheel(canvas, 740f * f, 700f * f, 65f * f, stroke)

        // Soil furrow ground
        val ground = Path().apply {
            moveTo(80f * f, 780f * f)
            var x = 80f * f
            while (x < 940f * f) {
                quadTo((x + 25f) * f, 765f * f, (x + 50f) * f, 780f * f)
                x += 50f
            }
        }
        canvas.drawPath(ground, stroke)
    }

    fun drawAirplane(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Aerodynamic Fuselage
        val body = Path().apply {
            moveTo(cx - 360f * f, cy) // tail
            lineTo(cx - 340f * f, cy - 140f * f) // vertical stabilizer
            lineTo(cx - 280f * f, cy - 140f * f)
            lineTo(cx - 240f * f, cy - 40f * f)
            lineTo(cx + 260f * f, cy - 40f * f)
            cubicTo(cx + 360f * f, cy - 40f * f, cx + 420f * f, cy, cx + 420f * f, cy + 30f * f)
            cubicTo(cx + 420f * f, cy + 60f * f, cx + 360f * f, cy + 80f * f, cx + 260f * f, cy + 80f * f)
            lineTo(cx - 360f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Pilot Cockpit Window
        val cockpit = Path().apply {
            moveTo(cx + 280f * f, cy - 30f * f)
            lineTo(cx + 350f * f, cy - 20f * f)
            lineTo(cx + 340f * f, cy + 10f * f)
            lineTo(cx + 280f * f, cy + 10f * f)
            close()
        }
        canvas.drawPath(cockpit, stroke)

        // Passenger Windows (Round portholes)
        for (i in 0..5) {
            canvas.drawCircle((cx - 160f + i * 70f) * f, cy + 10f * f, 14f * f, stroke)
        }

        // Swept Wing
        val wing = Path().apply {
            moveTo(cx, cy + 40f * f)
            lineTo(cx - 120f * f, cy + 240f * f)
            lineTo(cx - 40f * f, cy + 240f * f)
            lineTo(cx + 100f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(wing, stroke)

        // Jet Engine under wing
        val engine = RectF(cx - 60f * f, cy + 160f * f, cx + 40f * f, cy + 210f * f)
        canvas.drawRoundRect(engine, 16f * f, 16f * f, stroke)

        // Fluffy Clouds around
        drawCloud(canvas, 200f * f, 240f * f, 200f * f, 80f * f, stroke)
        drawCloud(canvas, 800f * f, 260f * f, 220f * f, 85f * f, stroke)
        drawCloud(canvas, 300f * f, 820f * f, 260f * f, 90f * f, stroke)
    }

    fun drawHelicopter(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 460f * f
        val cy = 480f * f

        // Bubble Cabin
        val cabin = Path().apply {
            moveTo(cx - 160f * f, cy + 120f * f)
            cubicTo(cx - 240f * f, cy + 80f * f, cx - 240f * f, cy - 80f * f, cx - 120f * f, cy - 120f * f)
            cubicTo(cx, cy - 140f * f, cx + 180f * f, cy - 120f * f, cx + 220f * f, cy)
            cubicTo(cx + 240f * f, cy + 80f * f, cx + 160f * f, cy + 120f * f, cx, cy + 130f * f)
            close()
        }
        canvas.drawPath(cabin, stroke)

        // Large Bubble Windshield
        val window = Path().apply {
            moveTo(cx + 40f * f, cy - 110f * f)
            cubicTo(cx + 160f * f, cy - 90f * f, cx + 200f * f, cy, cx + 180f * f, cy + 60f * f)
            lineTo(cx + 40f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(window, stroke)

        // Rotor Mast & Top Spinning Blades
        canvas.drawRect(RectF(cx - 20f * f, cy - 180f * f, cx + 20f * f, cy - 120f * f), stroke)
        val rotor = RectF(140f * f, cy - 200f * f, 780f * f, cy - 170f * f)
        canvas.drawRoundRect(rotor, 14f * f, 14f * f, stroke)

        // Tail Boom & Small Tail Rotor
        val tailBoom = Path().apply {
            moveTo(cx - 160f * f, cy)
            lineTo(cx - 360f * f, cy - 20f * f)
            lineTo(cx - 360f * f, cy - 80f * f)
            lineTo(cx - 340f * f, cy - 80f * f)
            lineTo(cx - 340f * f, cy)
            close()
        }
        canvas.drawPath(tailBoom, stroke)
        // Tail rotor blades
        canvas.drawLine((cx - 350f) * f, cy - 120f * f, (cx - 350f) * f, cy - 20f * f, stroke)

        // Landing Skids
        canvas.drawLine((cx - 80f) * f, cy + 130f * f, (cx - 80f) * f, cy + 200f * f, stroke)
        canvas.drawLine((cx + 100f) * f, cy + 130f * f, (cx + 100f) * f, cy + 200f * f, stroke)
        val skid = Path().apply {
            moveTo((cx - 180f) * f, cy + 200f * f)
            lineTo((cx + 200f) * f, cy + 200f * f)
            quadTo((cx + 240f) * f, cy + 200f * f, (cx + 240f) * f, cy + 160f * f)
        }
        canvas.drawPath(skid, stroke)

        drawCloud(canvas, 780f * f, 660f * f, 220f * f, 90f * f, stroke)
    }

    fun drawTrain(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Boiler Cylinder
        val boiler = Path().apply {
            moveTo(380f * f, 440f * f)
            lineTo(820f * f, 440f * f)
            cubicTo(860f * f, 440f * f, 880f * f, 480f * f, 880f * f, 560f * f)
            lineTo(880f * f, 660f * f)
            lineTo(380f * f, 660f * f)
            close()
        }
        canvas.drawPath(boiler, stroke)

        // Driver Cabin at back
        val cab = Path().apply {
            moveTo(180f * f, 340f * f)
            lineTo(380f * f, 340f * f)
            lineTo(380f * f, 660f * f)
            lineTo(180f * f, 660f * f)
            close()
        }
        canvas.drawPath(cab, stroke)
        // Cabin Window
        canvas.drawRoundRect(RectF(220f * f, 380f * f, 320f * f, 480f * f), 14f * f, 14f * f, stroke)

        // Smokestack Chimney
        val chimney = Path().apply {
            moveTo(740f * f, 440f * f)
            lineTo(720f * f, 300f * f)
            lineTo(780f * f, 300f * f)
            lineTo(760f * f, 440f * f)
            close()
        }
        canvas.drawPath(chimney, stroke)

        // Puffy circular clouds of steam
        canvas.drawCircle(680f * f, 240f * f, 30f * f, fine)
        canvas.drawCircle(600f * f, 190f * f, 40f * f, fine)
        canvas.drawCircle(490f * f, 150f * f, 50f * f, fine)

        // Front Cowcatcher Grill (wedge on front)
        val cowcatcher = Path().apply {
            moveTo(880f * f, 640f * f)
            lineTo(940f * f, 700f * f)
            lineTo(880f * f, 700f * f)
            close()
        }
        canvas.drawPath(cowcatcher, stroke)

        // Heavy Train Wheels
        drawWheel(canvas, 280f * f, 690f * f, 75f * f, stroke)
        drawWheel(canvas, 480f * f, 700f * f, 65f * f, stroke)
        drawWheel(canvas, 640f * f, 700f * f, 65f * f, stroke)
        drawWheel(canvas, 800f * f, 700f * f, 65f * f, stroke)

        // Connecting Rod between wheels
        canvas.drawRect(RectF(460f * f, 690f * f, 820f * f, 715f * f), stroke)

        // Railway Track
        canvas.drawLine(80f * f, 775f * f, 940f * f, 775f * f, stroke)
        canvas.drawLine(80f * f, 800f * f, 940f * f, 800f * f, stroke)
        for (x in 120..900 step 45) {
            canvas.drawLine(x * f, 775f * f, x * f, 815f * f, fine)
        }
    }

    fun drawSubmarine(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 500f * f

        // Main Submarine Oval Hull
        val hull = RectF(cx - 320f * f, cy - 140f * f, cx + 260f * f, cy + 140f * f)
        canvas.drawRoundRect(hull, 140f * f, 140f * f, stroke)

        // Conning Tower / Periscope Sail
        val tower = Path().apply {
            moveTo(cx - 80f * f, cy - 140f * f)
            lineTo(cx - 60f * f, cy - 240f * f)
            lineTo(cx + 60f * f, cy - 240f * f)
            lineTo(cx + 80f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(tower, stroke)

        // Periscope on top
        val periscope = Path().apply {
            moveTo(cx, cy - 240f * f)
            lineTo(cx, cy - 320f * f)
            lineTo(cx + 60f * f, cy - 320f * f)
            lineTo(cx + 60f * f, cy - 290f * f)
            lineTo(cx + 20f * f, cy - 290f * f)
            lineTo(cx + 20f * f, cy - 240f * f)
            close()
        }
        canvas.drawPath(periscope, stroke)

        // 3 Big Porthole Windows
        for (i in -1..1) {
            val px = cx + (i * 120f) * f
            canvas.drawCircle(px, cy, 45f * f, stroke)
            canvas.drawCircle(px, cy, 35f * f, stroke)
        }

        // Back Propeller
        val prop = Path().apply {
            moveTo(cx - 320f * f, cy)
            lineTo(cx - 360f * f, cy - 60f * f)
            lineTo(cx - 370f * f, cy)
            lineTo(cx - 360f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(prop, stroke)

        // Air Bubbles floating upward
        canvas.drawCircle(cx + 340f * f, cy - 120f * f, 24f * f, fine)
        canvas.drawCircle(cx + 380f * f, cy - 190f * f, 32f * f, fine)
        canvas.drawCircle(cx + 350f * f, cy - 260f * f, 20f * f, fine)

        drawGroundWaves(canvas, s, stroke)
    }

    fun drawSailboat(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Boat Hull
        val hull = Path().apply {
            moveTo(cx - 300f * f, 620f * f)
            lineTo(cx + 340f * f, 620f * f)
            cubicTo(cx + 280f * f, 720f * f, cx - 220f * f, 720f * f, cx - 260f * f, 620f * f)
            close()
        }
        canvas.drawPath(hull, stroke)

        // Center Mast
        canvas.drawRect(RectF(cx - 10f * f, 180f * f, cx + 10f * f, 620f * f), stroke)

        // Fluttering Pennant Flag on Mast Top
        val flag = Path().apply {
            moveTo(cx + 10f * f, 180f * f)
            lineTo(cx + 90f * f, 210f * f)
            lineTo(cx + 10f * f, 240f * f)
            close()
        }
        canvas.drawPath(flag, stroke)

        // Big Main Triangular Sail
        val mainSail = Path().apply {
            moveTo(cx - 20f * f, 230f * f)
            cubicTo(cx - 120f * f, 380f * f, cx - 180f * f, 500f * f, cx - 260f * f, 580f * f)
            lineTo(cx - 20f * f, 580f * f)
            close()
        }
        canvas.drawPath(mainSail, stroke)

        // Front Jib Sail
        val jibSail = Path().apply {
            moveTo(cx + 20f * f, 250f * f)
            lineTo(cx + 260f * f, 580f * f)
            cubicTo(cx + 140f * f, 580f * f, cx + 60f * f, 540f * f, cx + 20f * f, 580f * f)
            close()
        }
        canvas.drawPath(jibSail, stroke)

        // Life Ring on hull
        canvas.drawCircle(cx, 660f * f, 25f * f, stroke)
        canvas.drawCircle(cx, 660f * f, 12f * f, stroke)

        drawGroundWaves(canvas, s, stroke)
    }

    fun drawSteamTrain(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        drawTrain(canvas, s, stroke, fine, eyeFill, eyeHighlight)
    }

    fun drawBulldozer(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Track base
        val trackRect = RectF(220f * f, 660f * f, 800f * f, 800f * f)
        canvas.drawRoundRect(trackRect, 70f * f, 70f * f, stroke)
        // Wheels inside track
        for (i in 0..4) {
            val wx = (290f + i * 115f) * f
            DrawingUtils.drawWheel(canvas, wx, 730f * f, 45f * f, stroke)
        }

        // Cab
        val cab = Path().apply {
            moveTo(480f * f, 660f * f)
            lineTo(480f * f, 400f * f)
            lineTo(720f * f, 400f * f)
            lineTo(760f * f, 660f * f)
            close()
        }
        canvas.drawPath(cab, stroke)

        // Cab window
        val win = RectF(520f * f, 430f * f, 680f * f, 540f * f)
        canvas.drawRoundRect(win, 12f * f, 12f * f, stroke)

        // Engine hood
        val hood = RectF(280f * f, 500f * f, 480f * f, 660f * f)
        canvas.drawRoundRect(hood, 16f * f, 16f * f, stroke)

        // Exhaust pipe with puff
        val pipe = RectF(340f * f, 420f * f, 370f * f, 500f * f)
        canvas.drawRect(pipe, stroke)
        canvas.drawCircle(355f * f, 380f * f, 22f * f, stroke)

        // Giant Blade on front
        val blade = Path().apply {
            moveTo(140f * f, 520f * f)
            cubicTo(120f * f, 620f * f, 120f * f, 740f * f, 200f * f, 820f * f)
            lineTo(260f * f, 820f * f)
            cubicTo(200f * f, 740f * f, 200f * f, 620f * f, 200f * f, 520f * f)
            close()
        }
        canvas.drawPath(blade, stroke)
        // Hydraulic arm
        canvas.drawLine(200f * f, 680f * f, 380f * f, 640f * f, stroke)

        DrawingUtils.drawGroundGrass(canvas, s, stroke)
    }

    fun drawExcavator(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Heavy track base
        val trackRect = RectF(220f * f, 680f * f, 780f * f, 820f * f)
        canvas.drawRoundRect(trackRect, 65f * f, 65f * f, stroke)
        for (i in 0..4) {
            val wx = (280f + i * 110f) * f
            DrawingUtils.drawWheel(canvas, wx, 750f * f, 42f * f, stroke)
        }

        // Rotating Cab Body
        val cab = RectF(360f * f, 460f * f, 720f * f, 680f * f)
        canvas.drawRoundRect(cab, 20f * f, 20f * f, stroke)

        // Large Operator Window
        val win = RectF(500f * f, 490f * f, 680f * f, 600f * f)
        canvas.drawRoundRect(win, 12f * f, 12f * f, stroke)

        // Jointed Excavator Boom & Dipper Arm
        val boom1 = Path().apply {
            moveTo(540f * f, 500f * f)
            lineTo(780f * f, 260f * f)
            lineTo(830f * f, 290f * f)
            lineTo(580f * f, 540f * f)
            close()
        }
        canvas.drawPath(boom1, stroke)
        canvas.drawCircle(805f * f, 275f * f, 20f * f, stroke) // hinge

        // Dipper arm
        val boom2 = Path().apply {
            moveTo(805f * f, 275f * f)
            lineTo(920f * f, 520f * f)
            lineTo(880f * f, 540f * f)
            lineTo(785f * f, 295f * f)
            close()
        }
        canvas.drawPath(boom2, stroke)

        // Excavator Tooth Bucket
        val bucket = Path().apply {
            moveTo(880f * f, 540f * f)
            lineTo(950f * f, 620f * f)
            lineTo(970f * f, 660f * f)
            lineTo(880f * f, 680f * f)
            lineTo(850f * f, 580f * f)
            close()
        }
        canvas.drawPath(bucket, stroke)

        DrawingUtils.drawGroundGrass(canvas, s, stroke)
    }

    fun drawDumpTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f

        // Huge Wheels
        DrawingUtils.drawWheel(canvas, 340f * f, 740f * f, 70f * f, stroke)
        DrawingUtils.drawWheel(canvas, 740f * f, 740f * f, 70f * f, stroke)

        // Truck Chassis
        val chassis = RectF(220f * f, 640f * f, 840f * f, 700f * f)
        canvas.drawRoundRect(chassis, 12f * f, 12f * f, stroke)

        // Driver Cab
        val cab = Path().apply {
            moveTo(600f * f, 640f * f)
            lineTo(600f * f, 420f * f)
            lineTo(760f * f, 420f * f)
            lineTo(820f * f, 530f * f)
            lineTo(820f * f, 640f * f)
            close()
        }
        canvas.drawPath(cab, stroke)

        // Cab window
        val win = Path().apply {
            moveTo(640f * f, 450f * f)
            lineTo(740f * f, 450f * f)
            lineTo(780f * f, 530f * f)
            lineTo(640f * f, 530f * f)
            close()
        }
        canvas.drawPath(win, stroke)

        // Tilted Dump Bed
        val bed = Path().apply {
            moveTo(180f * f, 420f * f)
            lineTo(560f * f, 420f * f)
            lineTo(580f * f, 630f * f)
            lineTo(220f * f, 630f * f)
            close()
        }
        canvas.drawPath(bed, stroke)

        // Rocks overflowing in dump bed
        canvas.drawCircle(280f * f, 380f * f, 40f * f, stroke)
        canvas.drawCircle(350f * f, 360f * f, 45f * f, stroke)
        canvas.drawCircle(430f * f, 370f * f, 42f * f, stroke)
        canvas.drawCircle(490f * f, 390f * f, 36f * f, stroke)

        DrawingUtils.drawGroundGrass(canvas, s, stroke)
    }
}
