package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawWheel

object ExpandedVehicleRenderers {

    fun drawAmbulance(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Ambulance body box & cab
        val body = Path().apply {
            moveTo(160f * f, 660f * f)
            lineTo(160f * f, 380f * f)
            cubicTo(160f * f, 360f * f, 180f * f, 340f * f, 200f * f, 340f * f)
            lineTo(640f * f, 340f * f)
            lineTo(720f * f, 440f * f) // Hood slope
            lineTo(840f * f, 460f * f)
            cubicTo(860f * f, 460f * f, 880f * f, 480f * f, 880f * f, 500f * f)
            lineTo(880f * f, 660f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Red cross on side
        val cross = Path().apply {
            moveTo(360f * f, 440f * f); lineTo(420f * f, 440f * f); lineTo(420f * f, 380f * f)
            lineTo(460f * f, 380f * f); lineTo(460f * f, 440f * f); lineTo(520f * f, 440f * f)
            lineTo(520f * f, 480f * f); lineTo(460f * f, 480f * f); lineTo(460f * f, 540f * f)
            lineTo(420f * f, 540f * f); lineTo(420f * f, 480f * f); lineTo(360f * f, 480f * f)
            close()
        }
        canvas.drawPath(cross, stroke)
        // Windows
        canvas.drawRoundRect(RectF(640f * f, 370f * f, 720f * f, 440f * f), 10f * f, 10f * f, stroke)
        // Siren on roof
        canvas.drawRoundRect(RectF(480f * f, 290f * f, 560f * f, 340f * f), 12f * f, 12f * f, stroke)
        // Wheels
        drawWheel(canvas, 320f * f, 680f * f, 70f * f, stroke)
        drawWheel(canvas, 740f * f, 680f * f, 70f * f, stroke)
        // Ground line
        canvas.drawLine(100f * f, 750f * f, 924f * f, 750f * f, stroke)
    }

    fun drawMonsterTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Raised truck body
        val truck = Path().apply {
            moveTo(240f * f, 500f * f)
            lineTo(240f * f, 360f * f)
            lineTo(440f * f, 360f * f)
            lineTo(540f * f, 280f * f) // High cab
            lineTo(700f * f, 280f * f)
            lineTo(740f * f, 380f * f)
            lineTo(840f * f, 380f * f)
            lineTo(840f * f, 500f * f)
            close()
        }
        canvas.drawPath(truck, stroke)
        // Window
        canvas.drawRoundRect(RectF(550f * f, 300f * f, 680f * f, 370f * f), 10f * f, 10f * f, stroke)
        // Roll cage bars
        canvas.drawLine(320f * f, 360f * f, 440f * f, 280f * f, stroke)
        canvas.drawLine(440f * f, 280f * f, 540f * f, 280f * f, stroke)
        // Giant Monster Truck Wheels with deep treads
        drawMonsterWheel(canvas, 320f * f, 640f * f, 130f * f, stroke)
        drawMonsterWheel(canvas, 720f * f, 640f * f, 130f * f, stroke)
        // Ground
        canvas.drawLine(100f * f, 780f * f, 924f * f, 780f * f, stroke)
    }

    private fun drawMonsterWheel(canvas: Canvas, cx: Float, cy: Float, r: Float, stroke: Paint) {
        canvas.drawCircle(cx, cy, r, stroke)
        canvas.drawCircle(cx, cy, r * 0.6f, stroke)
        canvas.drawCircle(cx, cy, r * 0.25f, stroke)
        // 8 outer tire tread lugs
        for (i in 0..7) {
            val angle = i * Math.PI / 4
            val x1 = (cx + Math.cos(angle) * r).toFloat()
            val y1 = (cy + Math.sin(angle) * r).toFloat()
            val x2 = (cx + Math.cos(angle) * (r + 20f)).toFloat()
            val y2 = (cy + Math.sin(angle) * (r + 20f)).toFloat()
            canvas.drawLine(x1, y1, x2, y2, stroke)
        }
    }

    fun drawCementMixer(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Cab on right
        canvas.drawRoundRect(RectF(640f * f, 400f * f, 840f * f, 680f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(670f * f, 430f * f, 760f * f, 520f * f), 10f * f, 10f * f, stroke) // Window
        // Chassis frame
        canvas.drawRect(200f * f, 620f * f, 840f * f, 680f * f, stroke)
        // Barrel mixer (tilted drum)
        val drum = Path().apply {
            moveTo(240f * f, 440f * f)
            lineTo(400f * f, 320f * f)
            lineTo(620f * f, 420f * f)
            lineTo(560f * f, 580f * f)
            lineTo(300f * f, 560f * f)
            close()
        }
        canvas.drawPath(drum, stroke)
        // Spiral stripes on drum
        canvas.drawLine(340f * f, 360f * f, 440f * f, 570f * f, fine)
        canvas.drawLine(460f * f, 340f * f, 540f * f, 540f * f, fine)
        // Discharge chute at back
        val chute = Path().apply {
            moveTo(240f * f, 480f * f); lineTo(160f * f, 560f * f); lineTo(180f * f, 580f * f); lineTo(250f * f, 510f * f)
        }
        canvas.drawPath(chute, stroke)
        // Wheels
        drawWheel(canvas, 300f * f, 720f * f, 65f * f, stroke)
        drawWheel(canvas, 450f * f, 720f * f, 65f * f, stroke)
        drawWheel(canvas, 760f * f, 720f * f, 65f * f, stroke)
    }

    fun drawGarbageTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Big box body
        canvas.drawRoundRect(RectF(160f * f, 320f * f, 640f * f, 680f * f), 20f * f, 20f * f, stroke)
        // Recycle symbol arrows on side
        canvas.drawCircle(400f * f, 480f * f, 70f * f, stroke)
        // Cab on front
        canvas.drawRoundRect(RectF(640f * f, 400f * f, 840f * f, 680f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(670f * f, 430f * f, 780f * f, 530f * f), 10f * f, 10f * f, stroke)
        // Lift mechanism at rear
        val lift = Path().apply {
            moveTo(160f * f, 460f * f); lineTo(100f * f, 520f * f); lineTo(100f * f, 640f * f)
        }
        canvas.drawPath(lift, stroke)
        // Wheels
        drawWheel(canvas, 300f * f, 720f * f, 65f * f, stroke)
        drawWheel(canvas, 480f * f, 720f * f, 65f * f, stroke)
        drawWheel(canvas, 750f * f, 720f * f, 65f * f, stroke)
    }

    fun drawTowTruck(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Front cab
        canvas.drawRoundRect(RectF(520f * f, 380f * f, 820f * f, 680f * f), 20f * f, 20f * f, stroke)
        canvas.drawRoundRect(RectF(600f * f, 410f * f, 740f * f, 510f * f), 10f * f, 10f * f, stroke)
        // Flatbed chassis
        canvas.drawRect(240f * f, 560f * f, 540f * f, 680f * f, stroke)
        // Diagonal tow crane boom
        val boom = Path().apply {
            moveTo(480f * f, 560f * f)
            lineTo(220f * f, 300f * f)
            lineTo(260f * f, 300f * f)
            lineTo(520f * f, 560f * f)
            close()
        }
        canvas.drawPath(boom, stroke)
        // Cable & tow hook
        canvas.drawLine(240f * f, 300f * f, 240f * f, 440f * f, stroke)
        val hook = Path().apply {
            moveTo(240f * f, 440f * f)
            cubicTo(240f * f, 500f * f, 180f * f, 500f * f, 180f * f, 460f * f)
        }
        canvas.drawPath(hook, stroke)
        // Wheels
        drawWheel(canvas, 340f * f, 720f * f, 65f * f, stroke)
        drawWheel(canvas, 720f * f, 720f * f, 65f * f, stroke)
    }

    fun drawMotorcycle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Two wheels
        drawWheel(canvas, 280f * f, 680f * f, 80f * f, stroke)
        drawWheel(canvas, 740f * f, 680f * f, 80f * f, stroke)
        // Frame connecting axles
        val frame = Path().apply {
            moveTo(280f * f, 680f * f)
            lineTo(460f * f, 660f * f) // Engine
            lineTo(620f * f, 440f * f) // Handlebar column
            lineTo(740f * f, 680f * f) // Front fork
        }
        canvas.drawPath(frame, stroke)
        // Fuel tank & seat
        val body = Path().apply {
            moveTo(400f * f, 500f * f)
            cubicTo(440f * f, 440f * f, 580f * f, 420f * f, 620f * f, 460f * f)
            lineTo(540f * f, 540f * f)
            close()
        }
        canvas.drawPath(body, stroke)
        // Handlebars
        canvas.drawLine(600f * f, 420f * f, 640f * f, 360f * f, stroke)
        // Round headlight
        canvas.drawCircle(680f * f, 440f * f, 25f * f, stroke)
    }

    fun drawSailboat(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Hull
        val hull = Path().apply {
            moveTo(200f * f, 660f * f)
            lineTo(824f * f, 660f * f)
            lineTo(740f * f, 780f * f)
            lineTo(280f * f, 780f * f)
            close()
        }
        canvas.drawPath(hull, stroke)
        // Mast
        canvas.drawLine(cx, 220f * f, cx, 660f * f, stroke)
        // Big main sail (triangle right)
        val mainSail = Path().apply {
            moveTo(cx + 20f * f, 240f * f)
            lineTo(cx + 260f * f, 620f * f)
            lineTo(cx + 20f * f, 620f * f)
            close()
        }
        canvas.drawPath(mainSail, stroke)
        // Jib sail (triangle left)
        val jibSail = Path().apply {
            moveTo(cx - 20f * f, 280f * f)
            lineTo(cx - 20f * f, 620f * f)
            lineTo(cx - 240f * f, 620f * f)
            close()
        }
        canvas.drawPath(jibSail, stroke)
        // Sea waves
        for (i in 0..2) {
            val y = (800f + i * 40f) * f
            canvas.drawLine(100f * f, y, 924f * f, y, fine)
        }
    }

    fun drawCargoShip(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Massive freighter hull
        val hull = Path().apply {
            moveTo(120f * f, 560f * f)
            lineTo(880f * f, 560f * f)
            lineTo(820f * f, 760f * f)
            lineTo(180f * f, 760f * f)
            close()
        }
        canvas.drawPath(hull, stroke)
        // Containers stacked on deck
        for (col in 0..3) {
            for (row in 0..1) {
                val left = (260f + col * 90f) * f
                val top = (540f - (row + 1) * 70f) * f
                canvas.drawRect(left, top, left + 80f * f, top + 60f * f, stroke)
            }
        }
        // Bridge tower at stern (left)
        canvas.drawRect(140f * f, 380f * f, 240f * f, 560f * f, stroke)
        canvas.drawRect(160f * f, 320f * f, 220f * f, 380f * f, stroke)
        // Radar mast
        canvas.drawLine(190f * f, 320f * f, 190f * f, 260f * f, stroke)
        // Water
        canvas.drawLine(60f * f, 780f * f, 960f * f, 780f * f, fine)
    }

    fun drawSpaceShuttle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Delta-wing shuttle fuselage
        val shuttle = Path().apply {
            moveTo(cx, 160f * f) // Nose
            cubicTo(cx + 80f * f, 280f * f, cx + 100f * f, 500f * f, cx + 100f * f, 760f * f)
            lineTo(cx + 340f * f, 760f * f) // Right delta wing tip
            lineTo(cx + 120f * f, 520f * f)
            lineTo(cx + 80f * f, 820f * f) // Tail base
            lineTo(cx - 80f * f, 820f * f)
            lineTo(cx - 120f * f, 520f * f)
            lineTo(cx - 340f * f, 760f * f) // Left delta wing tip
            lineTo(cx - 100f * f, 760f * f)
            cubicTo(cx - 100f * f, 500f * f, cx - 80f * f, 280f * f, cx, 160f * f)
            close()
        }
        canvas.drawPath(shuttle, stroke)
        // Cockpit windshield windows
        canvas.drawOval(RectF(cx - 50f * f, 260f * f, cx + 50f * f, 320f * f), stroke)
        // Vertical stabilizer fin in center
        canvas.drawLine(cx, 600f * f, cx, 820f * f, stroke)
        // Exhaust rocket nozzles
        canvas.drawCircle(cx - 40f * f, 850f * f, 25f * f, stroke)
        canvas.drawCircle(cx + 40f * f, 850f * f, 25f * f, stroke)
        canvas.drawCircle(cx, 870f * f, 25f * f, stroke)
    }

    fun drawCableCar(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f
        // Cable wire sloping across top
        canvas.drawLine(60f * f, 200f * f, 960f * f, 300f * f, stroke)
        // Suspension arm with wheels
        val arm = Path().apply {
            moveTo(cx, 250f * f)
            lineTo(cx, 380f * f)
        }
        canvas.drawPath(arm, stroke)
        canvas.drawCircle(cx - 30f * f, 250f * f, 20f * f, stroke)
        canvas.drawCircle(cx + 30f * f, 260f * f, 20f * f, stroke)
        // Gondola cabin
        canvas.drawRoundRect(RectF(cx - 200f * f, 380f * f, cx + 200f * f, 760f * f), 40f * f, 40f * f, stroke)
        // Panoramic windows
        for (i in -1..1) {
            val wx = cx + i * 110f * f
            canvas.drawRoundRect(RectF(wx - 40f * f, 440f * f, wx + 40f * f, 560f * f), 15f * f, 15f * f, stroke)
        }
        // Mountain peaks below
        val mountain = Path().apply {
            moveTo(100f * f, 900f * f)
            lineTo(300f * f, 780f * f); lineTo(500f * f, 900f * f)
            lineTo(700f * f, 800f * f); lineTo(920f * f, 900f * f)
        }
        canvas.drawPath(mountain, fine)
    }
}
