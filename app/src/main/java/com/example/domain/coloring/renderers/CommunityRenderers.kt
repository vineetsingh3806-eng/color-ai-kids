package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawBubble
import com.example.domain.coloring.renderers.DrawingUtils.drawCloud
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundWaves
import com.example.domain.coloring.renderers.DrawingUtils.drawWheel

object CommunityRenderers {

    fun drawFirefighter(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Firefighter Helmet (Wide brim, high crown, shield badge)
        val helmetBrim = RectF(cx - 150f * f, cy - 180f * f, cx + 150f * f, cy - 120f * f)
        canvas.drawRoundRect(helmetBrim, 20f * f, 20f * f, stroke)
        val helmetDome = Path().apply {
            moveTo(cx - 110f * f, cy - 150f * f)
            cubicTo(cx - 110f * f, cy - 280f * f, cx + 110f * f, cy - 280f * f, cx + 110f * f, cy - 150f * f)
            close()
        }
        canvas.drawPath(helmetDome, stroke)
        // Fire Shield Badge on helmet
        val badge = Path().apply {
            moveTo(cx, cy - 250f * f)
            lineTo(cx + 25f * f, cy - 230f * f)
            lineTo(cx + 18f * f, cy - 180f * f)
            lineTo(cx, cy - 160f * f)
            lineTo(cx - 18f * f, cy - 180f * f)
            lineTo(cx - 25f * f, cy - 230f * f)
            close()
        }
        canvas.drawPath(badge, stroke)

        // Face & Cute Eyes
        canvas.drawCircle(cx, cy - 80f * f, 65f * f, stroke)
        drawCuteEye(canvas, cx - 25f * f, cy - 80f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 80f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 16f * f, cy - 50f * f)
            quadTo(cx, cy - 35f * f, cx + 16f * f, cy - 50f * f)
        }
        canvas.drawPath(smile, stroke)

        // Firefighter Coat
        val coat = RectF(cx - 90f * f, cy - 10f * f, cx + 90f * f, cy + 240f * f)
        canvas.drawRoundRect(coat, 20f * f, 20f * f, stroke)
        // Reflective Stripe
        canvas.drawRect(RectF(cx - 90f * f, cy + 100f * f, cx + 90f * f, cy + 140f * f), fine)

        // Fire Hose held in hands
        val hose = Path().apply {
            moveTo(cx - 220f * f, cy + 340f * f)
            cubicTo(cx - 120f * f, cy + 260f * f, cx - 40f * f, cy + 180f * f, cx + 60f * f, cy + 160f * f)
            lineTo(cx + 140f * f, cy + 120f * f) // brass nozzle
        }
        canvas.drawPath(hose, stroke)

        // Water Spray from nozzle
        val water = Path().apply {
            moveTo(cx + 140f * f, cy + 120f * f)
            cubicTo(cx + 220f * f, cy + 80f * f, cx + 260f * f, cy + 20f * f, cx + 360f * f, cy - 20f * f)
            moveTo(cx + 140f * f, cy + 120f * f)
            cubicTo(cx + 240f * f, cy + 100f * f, cx + 300f * f, cy + 60f * f, cx + 380f * f, cy + 60f * f)
        }
        canvas.drawPath(water, stroke)
        canvas.drawCircle(cx + 360f * f, cy - 20f * f, 14f * f, stroke)
        canvas.drawCircle(cx + 380f * f, cy + 60f * f, 16f * f, stroke)

        // Boots
        canvas.drawRoundRect(RectF(cx - 70f * f, cy + 240f * f, cx - 15f * f, cy + 440f * f), 16f * f, 16f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 15f * f, cy + 240f * f, cx + 70f * f, cy + 440f * f), 16f * f, 16f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawDoctor(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Head Mirror on forehead
        canvas.drawCircle(cx - 30f * f, cy - 160f * f, 24f * f, stroke)
        canvas.drawCircle(cx - 30f * f, cy - 160f * f, 10f * f, fine)

        // Doctor Head & Hair
        canvas.drawCircle(cx, cy - 100f * f, 65f * f, stroke)
        drawCuteEye(canvas, cx - 25f * f, cy - 100f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 100f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 16f * f, cy - 70f * f)
            quadTo(cx, cy - 55f * f, cx + 16f * f, cy - 70f * f)
        }
        canvas.drawPath(smile, stroke)

        // Doctor White Coat
        val coat = RectF(cx - 90f * f, cy - 30f * f, cx + 90f * f, cy + 240f * f)
        canvas.drawRoundRect(coat, 20f * f, 20f * f, stroke)
        // Lapels
        canvas.drawLine(cx - 40f * f, cy - 30f * f, cx - 20f * f, cy + 60f * f, stroke)
        canvas.drawLine(cx + 40f * f, cy - 30f * f, cx + 20f * f, cy + 60f * f, stroke)

        // Stethoscope around neck
        val steth = Path().apply {
            moveTo(cx - 50f * f, cy - 30f * f)
            cubicTo(cx - 40f * f, cy + 80f * f, cx + 40f * f, cy + 80f * f, cx + 50f * f, cy - 30f * f)
        }
        canvas.drawPath(steth, stroke)
        // Stethoscope chest piece bell
        canvas.drawCircle(cx, cy + 100f * f, 16f * f, stroke)

        // Medical Kit Bag held in hand
        val medKit = RectF(cx + 120f * f, cy + 120f * f, cx + 240f * f, cy + 220f * f)
        canvas.drawRoundRect(medKit, 14f * f, 14f * f, stroke)
        // Red Cross Symbol on bag
        canvas.drawLine(cx + 180f * f, cy + 145f * f, cx + 180f * f, cy + 195f * f, stroke)
        canvas.drawLine(cx + 155f * f, cy + 170f * f, cx + 205f * f, cy + 170f * f, stroke)

        // Pants & Shoes
        canvas.drawLine(cx - 35f * f, cy + 240f * f, cx - 35f * f, cy + 440f * f, stroke)
        canvas.drawLine(cx + 35f * f, cy + 240f * f, cx + 35f * f, cy + 440f * f, stroke)
    }

    fun drawChef(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f

        // Tall Puffy White Chef Hat (Toque Blanche)
        val hatPuffs = Path().apply {
            moveTo(cx - 100f * f, cy - 140f * f)
            cubicTo(cx - 160f * f, cy - 220f * f, cx - 120f * f, cy - 320f * f, cx - 60f * f, cy - 320f * f)
            cubicTo(cx - 40f * f, cy - 380f * f, cx + 40f * f, cy - 380f * f, cx + 60f * f, cy - 320f * f)
            cubicTo(cx + 120f * f, cy - 320f * f, cx + 160f * f, cy - 220f * f, cx + 100f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(hatPuffs, stroke)
        // Hat headband band
        canvas.drawRect(RectF(cx - 90f * f, cy - 150f * f, cx + 90f * f, cy - 110f * f), stroke)

        // Chef Head
        canvas.drawCircle(cx, cy - 50f * f, 65f * f, stroke)
        drawCuteEye(canvas, cx - 25f * f, cy - 50f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 50f * f, 14f * f, stroke, eyeFill, eyeHighlight)

        // Mustache & Smile
        val stache = Path().apply {
            moveTo(cx, cy - 25f * f)
            quadTo(cx - 30f * f, cy - 35f * f, cx - 40f * f, cy - 15f * f)
            moveTo(cx, cy - 25f * f)
            quadTo(cx + 30f * f, cy - 35f * f, cx + 40f * f, cy - 15f * f)
        }
        canvas.drawPath(stache, stroke)

        // Chef Double-Breasted Jacket
        val jacket = RectF(cx - 90f * f, cy + 20f * f, cx + 90f * f, cy + 260f * f)
        canvas.drawRoundRect(jacket, 18f * f, 18f * f, stroke)
        // Double row of buttons
        for (y in 70..210 step 50) {
            canvas.drawCircle(cx - 30f * f, (cy + y) * f, 8f * f, stroke)
            canvas.drawCircle(cx + 30f * f, (cy + y) * f, 8f * f, stroke)
        }

        // Frying Pan flipping a pancake
        canvas.drawRoundRect(RectF(cx + 100f * f, cy + 120f * f, cx + 240f * f, cy + 170f * f), 14f * f, 14f * f, stroke)
        canvas.drawLine(cx + 240f * f, cy + 145f * f, cx + 320f * f, cy + 145f * f, stroke) // handle
        // Pancake flipping in the air
        canvas.drawOval(RectF(cx + 130f * f, cy + 20f * f, cx + 210f * f, cy + 70f * f), stroke)
    }

    fun drawSchoolBus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Long Bus Body
        val bus = Path().apply {
            moveTo(cx - 400f * f, cy - 180f * f)
            lineTo(cx + 240f * f, cy - 180f * f)
            cubicTo(cx + 320f * f, cy - 180f * f, cx + 340f * f, cy - 60f * f, cx + 340f * f, cy - 20f * f)
            lineTo(cx + 420f * f, cy - 20f * f) // hood front
            cubicTo(cx + 430f * f, cy - 20f * f, cx + 430f * f, cy + 160f * f, cx + 420f * f, cy + 160f * f)
            lineTo(cx - 400f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(bus, stroke)

        // Front Hood Seam & Headlight
        canvas.drawLine(cx + 340f * f, cy - 20f * f, cx + 340f * f, cy + 160f * f, fine)
        canvas.drawCircle(cx + 410f * f, cy + 80f * f, 18f * f, stroke)

        // Front Windshield
        val windshield = RectF(cx + 260f * f, cy - 150f * f, cx + 330f * f, cy - 30f * f)
        canvas.drawRoundRect(windshield, 10f * f, 10f * f, stroke)

        // 4 Passenger Windows with Kids
        for (i in 0..3) {
            val wx = cx - 380f * f + (i * 150f) * f
            val win = RectF(wx, cy - 150f * f, wx + 110f * f, cy - 30f * f)
            canvas.drawRoundRect(win, 10f * f, 10f * f, stroke)
            // Cute peeking kid head in window
            canvas.drawCircle(wx + 55f * f, cy - 65f * f, 22f * f, fine)
        }

        // Octagonal Stop Sign Arm
        val stopSign = Path().apply {
            val sx = cx - 220f * f
            val sy = cy + 40f * f
            val r = 32f * f
            for (i in 0 until 8) {
                val angle = i * Math.PI / 4
                val px = (sx + Math.cos(angle) * r).toFloat()
                val py = (sy + Math.sin(angle) * r).toFloat()
                if (i == 0) moveTo(px, py) else lineTo(px, py)
            }
            close()
        }
        canvas.drawPath(stopSign, stroke)

        // Wheels
        drawWheel(canvas, cx - 240f * f, cy + 160f * f, 75f * f, stroke)
        drawWheel(canvas, cx + 240f * f, cy + 160f * f, 75f * f, stroke)

        // Road Ground
        canvas.drawLine(60f * f, cy + 240f * f, 964f * f, cy + 240f * f, stroke)
    }

    fun drawLighthouse(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Tapered Lighthouse Stone Tower
        val tower = Path().apply {
            moveTo(cx - 90f * f, cy - 180f * f)
            lineTo(cx + 90f * f, cy - 180f * f)
            lineTo(cx + 170f * f, cy + 340f * f)
            lineTo(cx - 170f * f, cy + 340f * f)
            close()
        }
        canvas.drawPath(tower, stroke)

        // Bold Candy Spiral / Horizontal Stripes
        canvas.drawLine(cx - 110f * f, cy - 60f * f, cx + 110f * f, cy - 60f * f, stroke)
        canvas.drawLine(cx - 130f * f, cy + 70f * f, cx + 130f * f, cy + 70f * f, stroke)
        canvas.drawLine(cx - 150f * f, cy + 200f * f, cx + 150f * f, cy + 200f * f, stroke)

        // Balcony Gallery Platform
        val balcony = RectF(cx - 120f * f, cy - 210f * f, cx + 120f * f, cy - 180f * f)
        canvas.drawRoundRect(balcony, 8f * f, 8f * f, stroke)

        // Glass Lantern Room (Dome & Light)
        val lantern = RectF(cx - 70f * f, cy - 300f * f, cx + 70f * f, cy - 210f * f)
        canvas.drawRoundRect(lantern, 10f * f, 10f * f, stroke)
        val dome = Path().apply {
            moveTo(cx - 80f * f, cy - 300f * f)
            cubicTo(cx - 80f * f, cy - 390f * f, cx + 80f * f, cy - 390f * f, cx + 80f * f, cy - 300f * f)
            close()
        }
        canvas.drawPath(dome, stroke)

        // Radiant Light Beams projecting left & right
        val beamL = Path().apply {
            moveTo(cx - 70f * f, cy - 255f * f)
            lineTo(60f * f, cy - 420f * f)
            lineTo(60f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(beamL, fine)

        val beamR = Path().apply {
            moveTo(cx + 70f * f, cy - 255f * f)
            lineTo(960f * f, cy - 420f * f)
            lineTo(960f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(beamR, fine)

        // Rocky Island & Waves
        val rocks = Path().apply {
            moveTo(cx - 240f * f, cy + 340f * f)
            cubicTo(cx - 200f * f, cy + 280f * f, cx + 200f * f, cy + 280f * f, cx + 240f * f, cy + 340f * f)
            lineTo(cx + 280f * f, cy + 420f * f)
            lineTo(cx - 280f * f, cy + 420f * f)
            close()
        }
        canvas.drawPath(rocks, stroke)
        drawGroundWaves(canvas, s, stroke)
    }

    fun drawWindmill(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Tapered Mill Tower
        val tower = Path().apply {
            moveTo(cx - 100f * f, cy - 140f * f)
            lineTo(cx + 100f * f, cy - 140f * f)
            lineTo(cx + 180f * f, cy + 360f * f)
            lineTo(cx - 180f * f, cy + 360f * f)
            close()
        }
        canvas.drawPath(tower, stroke)

        // Cap / Dome on Windmill
        val dome = Path().apply {
            moveTo(cx - 110f * f, cy - 140f * f)
            cubicTo(cx - 110f * f, cy - 240f * f, cx + 110f * f, cy - 240f * f, cx + 110f * f, cy - 140f * f)
            close()
        }
        canvas.drawPath(dome, stroke)

        // Central Hub of Blades
        val hubY = cy - 140f * f
        canvas.drawCircle(cx, hubY, 32f * f, stroke)

        // 4 Spinning Lattice Sail Blades
        for (i in 0 until 4) {
            val angle = i * Math.PI / 2 + Math.PI / 4
            val blade = Path().apply {
                val cos = Math.cos(angle).toFloat()
                val sin = Math.sin(angle).toFloat()
                val perpCos = -sin
                val perpSin = cos
                val startX = cx + cos * 40f * f
                val startY = hubY + sin * 40f * f
                val endX = cx + cos * 340f * f
                val endY = hubY + sin * 340f * f
                moveTo(startX, startY)
                lineTo(startX + perpCos * 40f * f, startY + perpSin * 40f * f)
                lineTo(endX + perpCos * 50f * f, endY + perpSin * 50f * f)
                lineTo(endX, endY)
                close()
            }
            canvas.drawPath(blade, stroke)
        }

        // Arched Wooden Door at base
        val door = RectF(cx - 40f * f, cy + 240f * f, cx + 40f * f, cy + 360f * f)
        canvas.drawRoundRect(door, 20f * f, 20f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawHotAirBalloon(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Giant Bulbous Balloon Envelope
        val balloon = Path().apply {
            moveTo(cx - 80f * f, cy + 220f * f)
            cubicTo(cx - 360f * f, cy + 120f * f, cx - 360f * f, cy - 280f * f, cx, cy - 280f * f)
            cubicTo(cx + 360f * f, cy - 280f * f, cx + 360f * f, cy + 120f * f, cx + 80f * f, cy + 220f * f)
            close()
        }
        canvas.drawPath(balloon, stroke)

        // Striped Gores (Long curved lines dividing patterns)
        val goreL = Path().apply {
            moveTo(cx, cy - 280f * f)
            cubicTo(cx - 180f * f, cy - 140f * f, cx - 180f * f, cy + 100f * f, cx - 30f * f, cy + 220f * f)
        }
        canvas.drawPath(goreL, stroke)

        val goreR = Path().apply {
            moveTo(cx, cy - 280f * f)
            cubicTo(cx + 180f * f, cy - 140f * f, cx + 180f * f, cy + 100f * f, cx + 30f * f, cy + 220f * f)
        }
        canvas.drawPath(goreR, stroke)

        // Cute Face in Center Stripe
        drawCuteEye(canvas, cx - 35f * f, cy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, cy - 20f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 20f * f, cy + 25f * f)
            quadTo(cx, cy + 45f * f, cx + 20f * f, cy + 25f * f)
        }
        canvas.drawPath(smile, stroke)

        // Ropes connecting to basket
        canvas.drawLine(cx - 60f * f, cy + 220f * f, cx - 50f * f, cy + 300f * f, stroke)
        canvas.drawLine(cx + 60f * f, cy + 220f * f, cx + 50f * f, cy + 300f * f, stroke)

        // Wicker Passenger Basket
        val basket = RectF(cx - 80f * f, cy + 300f * f, cx + 80f * f, cy + 420f * f)
        canvas.drawRoundRect(basket, 14f * f, 14f * f, stroke)
        // Wicker weave grid
        canvas.drawLine(cx - 80f * f, cy + 360f * f, cx + 80f * f, cy + 360f * f, fine)
        canvas.drawLine(cx, cy + 300f * f, cx, cy + 420f * f, fine)

        // Floating Clouds
        drawCloud(canvas, 180f * f, 240f * f, 220f * f, 80f * f, stroke)
        drawCloud(canvas, 820f * f, 320f * f, 240f * f, 90f * f, stroke)
    }

    fun drawSubmarine(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Round Submarine Hull (Capsule shape)
        val hull = RectF(cx - 300f * f, cy - 140f * f, cx + 220f * f, cy + 140f * f)
        canvas.drawRoundRect(hull, 140f * f, 140f * f, stroke)

        // Conning Tower / Periscope Turret on Top
        val tower = RectF(cx - 100f * f, cy - 240f * f, cx + 20f * f, cy - 140f * f)
        canvas.drawRoundRect(tower, 16f * f, 16f * f, stroke)

        // Periscope Pipe with Eye Piece
        val periscope = Path().apply {
            moveTo(cx - 40f * f, cy - 240f * f)
            lineTo(cx - 40f * f, cy - 340f * f)
            lineTo(cx + 30f * f, cy - 340f * f)
            lineTo(cx + 30f * f, cy - 310f * f)
            lineTo(cx - 10f * f, cy - 310f * f)
            lineTo(cx - 10f * f, cy - 240f * f)
            close()
        }
        canvas.drawPath(periscope, stroke)

        // 3 Big Round Portholes
        for (i in -1..1) {
            val px = cx - 50f * f + (i * 120f) * f
            canvas.drawCircle(px, cy, 45f * f, stroke)
            canvas.drawCircle(px, cy, 32f * f, fine)
        }

        // Propeller at the back
        val propStem = RectF(cx - 340f * f, cy - 30f * f, cx - 300f * f, cy + 30f * f)
        canvas.drawRect(propStem, stroke)
        val blade1 = Path().apply {
            moveTo(cx - 340f * f, cy)
            cubicTo(cx - 420f * f, cy - 80f * f, cx - 380f * f, cy - 120f * f, cx - 340f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(blade1, stroke)
        val blade2 = Path().apply {
            moveTo(cx - 340f * f, cy)
            cubicTo(cx - 420f * f, cy + 80f * f, cx - 380f * f, cy + 120f * f, cx - 340f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(blade2, stroke)

        // Underwater Bubbles
        drawBubble(canvas, cx + 320f * f, cy - 80f * f, 28f * f, stroke, fine)
        drawBubble(canvas, cx + 380f * f, cy - 160f * f, 38f * f, stroke, fine)
    }

    fun drawClockTower(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Majestic Stone Tower Body
        val tower = RectF(cx - 140f * f, 340f * f, cx + 140f * f, 880f * f)
        canvas.drawRect(tower, stroke)

        // Pyramidal Steeple Roof
        val roof = Path().apply {
            moveTo(cx - 170f * f, 340f * f)
            lineTo(cx, 120f * f)
            lineTo(cx + 170f * f, 340f * f)
            close()
        }
        canvas.drawPath(roof, stroke)

        // Weather Vane on Peak
        canvas.drawLine(cx, 120f * f, cx, 60f * f, stroke)
        canvas.drawLine(cx - 30f * f, 75f * f, cx + 30f * f, 75f * f, stroke)

        // Belfry Arched Windows
        val belfryL = RectF(cx - 100f * f, 240f * f, cx - 30f * f, 330f * f)
        canvas.drawRoundRect(belfryL, 16f * f, 16f * f, stroke)
        val belfryR = RectF(cx + 30f * f, 240f * f, cx + 100f * f, 330f * f)
        canvas.drawRoundRect(belfryR, 16f * f, 16f * f, stroke)

        // Grand Circular Clock Face
        val clockY = 480f * f
        canvas.drawCircle(cx, clockY, 100f * f, stroke)
        canvas.drawCircle(cx, clockY, 85f * f, fine)
        canvas.drawCircle(cx, clockY, 12f * f, eyeFill) // center pin

        // Clock Hands (Showing 3:00)
        canvas.drawLine(cx, clockY, cx, clockY - 60f * f, stroke) // minute hand (up)
        canvas.drawLine(cx, clockY, cx + 45f * f, clockY, stroke) // hour hand (right)

        // Roman Numeral / Hour marks
        for (i in 0 until 12) {
            val angle = i * Math.PI / 6
            val x1 = (cx + Math.cos(angle) * 72f * f).toFloat()
            val y1 = (clockY + Math.sin(angle) * 72f * f).toFloat()
            val x2 = (cx + Math.cos(angle) * 82f * f).toFloat()
            val y2 = (clockY + Math.sin(angle) * 82f * f).toFloat()
            canvas.drawLine(x1, y1, x2, y2, fine)
        }

        // Stone Base Arched Entrance
        val door = RectF(cx - 50f * f, 740f * f, cx + 50f * f, 880f * f)
        canvas.drawRoundRect(door, 25f * f, 25f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawIgloo(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 440f * f
        val cy = 560f * f

        // Main Dome of Igloo
        val dome = Path().apply {
            moveTo(cx - 260f * f, cy + 180f * f)
            cubicTo(cx - 280f * f, cy - 200f * f, cx + 240f * f, cy - 200f * f, cx + 240f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(dome, stroke)

        // Arched Tunnel Entrance
        val tunnel = Path().apply {
            moveTo(cx + 80f * f, cy + 180f * f)
            cubicTo(cx + 80f * f, cy + 40f * f, cx + 260f * f, cy + 40f * f, cx + 260f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(tunnel, stroke)
        // Entrance opening hole
        val doorHole = Path().apply {
            moveTo(cx + 120f * f, cy + 180f * f)
            cubicTo(cx + 120f * f, cy + 80f * f, cx + 230f * f, cy + 80f * f, cx + 230f * f, cy + 180f * f)
            close()
        }
        canvas.drawPath(doorHole, eyeFill)

        // Ice Block Seam Lines
        for (y in -60..120 step 60) {
            val arc = RectF((cx - 240f) * f, (cy + y - 80f) * f, (cx + 200f) * f, (cy + y + 80f) * f)
            canvas.drawArc(arc, 200f, 140f, false, fine)
        }

        // Cute Penguin Friend next to igloo
        val px = 820f * f
        val py = cy + 60f * f
        // Penguin Body
        val penguin = RectF(px - 60f * f, py - 90f * f, px + 60f * f, py + 120f * f)
        canvas.drawRoundRect(penguin, 50f * f, 50f * f, stroke)
        // White belly
        val pBelly = RectF(px - 35f * f, py - 30f * f, px + 35f * f, py + 100f * f)
        canvas.drawRoundRect(pBelly, 30f * f, 30f * f, fine)
        // Penguin Eyes & Beak
        drawCuteEye(canvas, px - 18f * f, py - 50f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, px + 18f * f, py - 50f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        val beak = Path().apply {
            moveTo(px - 10f * f, py - 35f * f)
            lineTo(px, py - 20f * f)
            lineTo(px + 10f * f, py - 35f * f)
            close()
        }
        canvas.drawPath(beak, stroke)
        // Cozy Winter Scarf
        canvas.drawRoundRect(RectF(px - 45f * f, py - 20f * f, px + 45f * f, py + 5f * f), 8f * f, 8f * f, stroke)

        // Snow Ground & Falling Snowflakes
        canvas.drawLine(60f * f, cy + 180f * f, 960f * f, cy + 180f * f, stroke)
        canvas.drawCircle(220f * f, 240f * f, 12f * f, stroke)
        canvas.drawCircle(360f * f, 180f * f, 16f * f, stroke)
        canvas.drawCircle(720f * f, 220f * f, 14f * f, stroke)
    }
}
