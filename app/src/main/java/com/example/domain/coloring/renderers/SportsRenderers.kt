package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCloud
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawHeart
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object SportsRenderers {

    fun drawSoccerBall(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Main Ball Sphere
        canvas.drawCircle(cx, cy, 240f * f, stroke)

        // Center Black Pentagon
        val pentagon = Path().apply {
            val r = 70f * f
            for (i in 0 until 5) {
                val angle = i * Math.PI * 2 / 5 - Math.PI / 2
                val px = (cx + Math.cos(angle) * r).toFloat()
                val py = (cy + Math.sin(angle) * r).toFloat()
                if (i == 0) moveTo(px, py) else lineTo(px, py)
            }
            close()
        }
        canvas.drawPath(pentagon, stroke)

        // Spokes connecting to surrounding hexagons
        for (i in 0 until 5) {
            val angle = i * Math.PI * 2 / 5 - Math.PI / 2
            val x1 = (cx + Math.cos(angle) * 70f * f).toFloat()
            val y1 = (cy + Math.sin(angle) * 70f * f).toFloat()
            val x2 = (cx + Math.cos(angle) * 150f * f).toFloat()
            val y2 = (cy + Math.sin(angle) * 150f * f).toFloat()
            canvas.drawLine(x1, y1, x2, y2, stroke)

            // Outer connecting lines to ball edge
            val nextAngle = (i + 1) * Math.PI * 2 / 5 - Math.PI / 2
            val xNext = (cx + Math.cos(nextAngle) * 150f * f).toFloat()
            val yNext = (cy + Math.sin(nextAngle) * 150f * f).toFloat()
            canvas.drawLine(x2, y2, xNext, yNext, stroke)
            val edgeX = (cx + Math.cos(angle) * 240f * f).toFloat()
            val edgeY = (cy + Math.sin(angle) * 240f * f).toFloat()
            canvas.drawLine(x2, y2, edgeX, edgeY, stroke)
        }

        // Speed Lines swooshing
        canvas.drawLine(cx - 320f * f, cy - 80f * f, cx - 260f * f, cy - 80f * f, stroke)
        canvas.drawLine(cx - 360f * f, cy, cx - 270f * f, cy, stroke)
        canvas.drawLine(cx - 310f * f, cy + 80f * f, cx - 260f * f, cy + 80f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawBasketball(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 420f * f
        val cy = 520f * f

        // Basketball Sphere
        canvas.drawCircle(cx, cy, 210f * f, stroke)

        // Center Horizontal & Vertical Seam Lines
        canvas.drawLine(cx - 210f * f, cy, cx + 210f * f, cy, stroke)
        canvas.drawLine(cx, cy - 210f * f, cx, cy + 210f * f, stroke)

        // Curved Side Seams
        val seamL = RectF(cx - 310f * f, cy - 210f * f, cx - 110f * f, cy + 210f * f)
        canvas.drawArc(seamL, 270f, 180f, false, stroke)
        val seamR = RectF(cx + 110f * f, cy - 210f * f, cx + 310f * f, cy + 210f * f)
        canvas.drawArc(seamR, 90f, 180f, false, stroke)

        // Basketball Backboard & Rim on Right
        val backboard = RectF(740f * f, 140f * f, 780f * f, 440f * f)
        canvas.drawRoundRect(backboard, 10f * f, 10f * f, stroke)
        // Rim
        canvas.drawRect(RectF(600f * f, 320f * f, 740f * f, 345f * f), stroke)
        // Net
        val net = Path().apply {
            moveTo(610f * f, 345f * f)
            lineTo(640f * f, 480f * f)
            lineTo(700f * f, 480f * f)
            lineTo(730f * f, 345f * f)
            close()
        }
        canvas.drawPath(net, stroke)
        canvas.drawLine(640f * f, 345f * f, 670f * f, 480f * f, fine)
        canvas.drawLine(700f * f, 345f * f, 670f * f, 480f * f, fine)
    }

    fun drawTeddyBear(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Bear Round Ears
        canvas.drawCircle(cx - 120f * f, cy - 180f * f, 50f * f, stroke)
        canvas.drawCircle(cx - 120f * f, cy - 180f * f, 28f * f, fine)
        canvas.drawCircle(cx + 120f * f, cy - 180f * f, 50f * f, stroke)
        canvas.drawCircle(cx + 120f * f, cy - 180f * f, 28f * f, fine)

        // Bear Big Head
        canvas.drawCircle(cx, cy - 80f * f, 140f * f, stroke)

        // Snout Muzzle
        canvas.drawOval(RectF(cx - 60f * f, cy - 60f * f, cx + 60f * f, cy + 10f * f), stroke)
        // Heart Nose
        drawHeart(canvas, cx, cy - 40f * f, 24f * f, stroke)
        val smile = Path().apply {
            moveTo(cx, cy - 25f * f)
            lineTo(cx, cy - 10f * f)
            moveTo(cx - 20f * f, cy)
            quadTo(cx, cy + 15f * f, cx + 20f * f, cy)
        }
        canvas.drawPath(smile, stroke)

        // Cute Glass Button Eyes
        drawCuteEye(canvas, cx - 50f * f, cy - 90f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 50f * f, cy - 90f * f, 22f * f, stroke, eyeFill, eyeHighlight)

        // Bow Tie around neck
        val bow = Path().apply {
            moveTo(cx, cy + 60f * f)
            lineTo(cx - 60f * f, cy + 30f * f)
            lineTo(cx - 60f * f, cy + 90f * f)
            lineTo(cx, cy + 60f * f)
            lineTo(cx + 60f * f, cy + 30f * f)
            lineTo(cx + 60f * f, cy + 90f * f)
            close()
        }
        canvas.drawPath(bow, stroke)
        canvas.drawCircle(cx, cy + 60f * f, 14f * f, stroke)

        // Bear Chubby Body
        canvas.drawCircle(cx, cy + 220f * f, 160f * f, stroke)
        // Stitched Patch on Belly
        canvas.drawCircle(cx, cy + 220f * f, 90f * f, fine)

        // Paws (Arms)
        canvas.drawCircle(cx - 150f * f, cy + 160f * f, 45f * f, stroke)
        canvas.drawCircle(cx + 150f * f, cy + 160f * f, 45f * f, stroke)

        // Foot Pads (Legs)
        canvas.drawCircle(cx - 110f * f, cy + 360f * f, 55f * f, stroke)
        canvas.drawCircle(cx - 110f * f, cy + 360f * f, 30f * f, fine)
        canvas.drawCircle(cx + 110f * f, cy + 360f * f, 55f * f, stroke)
        canvas.drawCircle(cx + 110f * f, cy + 360f * f, 30f * f, fine)
    }

    fun drawBicycle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 540f * f

        // Front & Back Big Spoked Wheels
        val wheelBackX = cx - 220f * f
        val wheelFrontX = cx + 220f * f
        val wheelY = cy + 140f * f
        val wheelR = 140f * f

        canvas.drawCircle(wheelBackX, wheelY, wheelR, stroke)
        canvas.drawCircle(wheelBackX, wheelY, 20f * f, stroke)
        canvas.drawCircle(wheelFrontX, wheelY, wheelR, stroke)
        canvas.drawCircle(wheelFrontX, wheelY, 20f * f, stroke)

        // Spokes on Wheels
        for (i in 0 until 8) {
            val angle = i * Math.PI / 4
            canvas.drawLine(
                wheelBackX, wheelY,
                (wheelBackX + Math.cos(angle) * wheelR).toFloat(),
                (wheelY + Math.sin(angle) * wheelR).toFloat(),
                fine
            )
            canvas.drawLine(
                wheelFrontX, wheelY,
                (wheelFrontX + Math.cos(angle) * wheelR).toFloat(),
                (wheelY + Math.sin(angle) * wheelR).toFloat(),
                fine
            )
        }

        // Diamond Bicycle Frame
        val pedalX = cx - 40f * f
        val pedalY = wheelY
        val seatX = cx - 90f * f
        val seatY = cy - 80f * f
        val handleX = cx + 140f * f
        val handleY = cy - 140f * f

        canvas.drawLine(wheelBackX, wheelY, pedalX, pedalY, stroke) // chainstay
        canvas.drawLine(wheelBackX, wheelY, seatX, seatY, stroke) // seatstay
        canvas.drawLine(pedalX, pedalY, seatX, seatY, stroke) // seat tube
        canvas.drawLine(seatX, seatY, handleX, handleY, stroke) // top tube
        canvas.drawLine(pedalX, pedalY, handleX, handleY, stroke) // down tube
        canvas.drawLine(handleX, handleY, wheelFrontX, wheelY, stroke) // fork

        // Handlebars with Streamers
        canvas.drawLine(handleX, handleY, handleX + 20f * f, handleY - 60f * f, stroke)
        canvas.drawRoundRect(RectF(handleX - 40f * f, handleY - 70f * f, handleX + 40f * f, handleY - 50f * f), 10f * f, 10f * f, stroke)

        // Bicycle Seat Saddle
        val saddle = Path().apply {
            moveTo(seatX - 60f * f, seatY - 20f * f)
            lineTo(seatX + 40f * f, seatY - 20f * f)
            lineTo(seatX + 20f * f, seatY)
            lineTo(seatX - 40f * f, seatY)
            close()
        }
        canvas.drawPath(saddle, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawRollerSkates(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // High-top Boot Body
        val boot = Path().apply {
            moveTo(cx - 160f * f, cy - 240f * f) // top collar
            lineTo(cx + 60f * f, cy - 240f * f)
            lineTo(cx + 60f * f, cy + 20f * f)
            cubicTo(cx + 60f * f, cy + 100f * f, cx + 180f * f, cy + 120f * f, cx + 220f * f, cy + 140f * f) // toe box
            lineTo(cx + 220f * f, cy + 190f * f) // sole toe
            lineTo(cx - 160f * f, cy + 190f * f) // sole heel
            close()
        }
        canvas.drawPath(boot, stroke)

        // Thick Sole Plate
        val sole = RectF(cx - 170f * f, cy + 190f * f, cx + 230f * f, cy + 220f * f)
        canvas.drawRoundRect(sole, 12f * f, 12f * f, stroke)

        // Front Toe Stopper
        canvas.drawCircle(cx + 220f * f, cy + 260f * f, 24f * f, stroke)

        // 2 Roller Wheels
        canvas.drawCircle(cx - 90f * f, cy + 290f * f, 60f * f, stroke)
        canvas.drawCircle(cx - 90f * f, cy + 290f * f, 24f * f, stroke)
        canvas.drawCircle(cx + 120f * f, cy + 290f * f, 60f * f, stroke)
        canvas.drawCircle(cx + 120f * f, cy + 290f * f, 24f * f, stroke)

        // Laces Criss-Cross
        for (y in -180..60 step 50) {
            canvas.drawLine((cx - 30f) * f, (cy + y) * f, (cx + 30f) * f, (cy + y + 20f) * f, stroke)
            canvas.drawLine((cx + 30f) * f, (cy + y) * f, (cx - 30f) * f, (cy + y + 20f) * f, stroke)
        }

        // Star Emblem on Boot Ankle
        drawStar(canvas, cx - 80f * f, cy - 100f * f, 40f * f, stroke)
    }

    fun drawSkateboard(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Curved Skateboard Deck (Kicktail on both ends)
        val deck = Path().apply {
            moveTo(cx - 380f * f, cy - 40f * f) // nose kick
            cubicTo(cx - 280f * f, cy + 40f * f, cx + 280f * f, cy + 40f * f, cx + 380f * f, cy - 40f * f) // tail kick
            lineTo(cx + 380f * f, cy)
            cubicTo(cx + 280f * f, cy + 80f * f, cx - 280f * f, cy + 80f * f, cx - 380f * f, cy)
            close()
        }
        canvas.drawPath(deck, stroke)

        // Skateboard Trucks (Axles)
        val truckL = RectF(cx - 240f * f, cy + 60f * f, cx - 180f * f, cy + 120f * f)
        canvas.drawRoundRect(truckL, 10f * f, 10f * f, stroke)
        val truckR = RectF(cx + 180f * f, cy + 60f * f, cx + 240f * f, cy + 120f * f)
        canvas.drawRoundRect(truckR, 10f * f, 10f * f, stroke)

        // 4 Skateboard Wheels (2 visible in profile)
        canvas.drawCircle(cx - 210f * f, cy + 160f * f, 50f * f, stroke)
        canvas.drawCircle(cx - 210f * f, cy + 160f * f, 20f * f, stroke)
        canvas.drawCircle(cx + 210f * f, cy + 160f * f, 50f * f, stroke)
        canvas.drawCircle(cx + 210f * f, cy + 160f * f, 20f * f, stroke)

        // Cool Flame Graphic on deck bottom
        val flame = Path().apply {
            moveTo(cx - 100f * f, cy + 50f * f)
            quadTo(cx - 40f * f, cy + 20f * f, cx, cy + 45f * f)
            quadTo(cx + 40f * f, cy + 15f * f, cx + 100f * f, cy + 50f * f)
        }
        canvas.drawPath(flame, fine)

        // Motion speed lines
        canvas.drawLine(cx - 420f * f, cy - 20f * f, cx - 360f * f, cy - 20f * f, stroke)
        canvas.drawLine(cx - 440f * f, cy + 30f * f, cx - 380f * f, cy + 30f * f, stroke)
    }

    fun drawKite(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Classic Diamond Kite
        val kite = Path().apply {
            moveTo(cx, cy - 260f * f) // top peak
            lineTo(cx + 220f * f, cy - 40f * f) // right corner
            lineTo(cx, cy + 220f * f) // bottom tail
            lineTo(cx - 220f * f, cy - 40f * f) // left corner
            close()
        }
        canvas.drawPath(kite, stroke)

        // Internal Cross Spars
        canvas.drawLine(cx, cy - 260f * f, cx, cy + 220f * f, stroke)
        canvas.drawLine(cx - 220f * f, cy - 40f * f, cx + 220f * f, cy - 40f * f, stroke)

        // Cute Face on Kite
        drawCuteEye(canvas, cx - 60f * f, cy - 70f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy - 70f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 25f * f, cy - 30f * f)
            quadTo(cx, cy - 10f * f, cx + 25f * f, cy - 30f * f)
        }
        canvas.drawPath(smile, stroke)

        // Long Fluttering Kite Tail with Bows
        val tail = Path().apply {
            moveTo(cx, cy + 220f * f)
            cubicTo(cx - 160f * f, cy + 340f * f, cx + 180f * f, cy + 440f * f, cx - 80f * f, cy + 540f * f)
        }
        canvas.drawPath(tail, stroke)

        // Ribbon Bows on Tail
        val bowPositions = listOf(
            cx - 60f to cy + 290f,
            cx + 80f to cy + 380f,
            cx + 60f to cy + 460f,
            cx - 60f to cy + 520f
        )
        for ((bx, by) in bowPositions) {
            val bow = Path().apply {
                moveTo(bx * f, by * f)
                lineTo((bx - 20f) * f, (by - 15f) * f)
                lineTo((bx - 20f) * f, (by + 15f) * f)
                lineTo(bx * f, by * f)
                lineTo((bx + 20f) * f, (by - 15f) * f)
                lineTo((bx + 20f) * f, (by + 15f) * f)
                close()
            }
            canvas.drawPath(bow, stroke)
        }

        // Fluffy Sky Clouds
        drawCloud(canvas, 180f * f, 220f * f, 220f * f, 80f * f, stroke)
        drawCloud(canvas, 820f * f, 280f * f, 240f * f, 90f * f, stroke)
    }

    fun drawYoYo(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f

        // String coming down from top finger loop
        canvas.drawCircle(cx - 120f * f, 140f * f, 18f * f, stroke) // finger loop
        canvas.drawLine(cx - 120f * f, 160f * f, cx, cy - 200f * f, stroke)

        // Front Half Yo-Yo Disc
        canvas.drawCircle(cx, cy, 220f * f, stroke)

        // Inner Recessed Disc Circle
        canvas.drawCircle(cx, cy, 140f * f, stroke)

        // Big Star Logo in Center
        drawStar(canvas, cx, cy, 80f * f, stroke)

        // Yo-Yo Gap Rim (Side angle view)
        val rimGap = RectF(cx - 240f * f, cy - 220f * f, cx - 180f * f, cy + 220f * f)
        canvas.drawOval(rimGap, stroke)

        // Spinning Whirly Lines
        val whirly = Path().apply {
            moveTo(cx + 260f * f, cy - 40f * f)
            quadTo(cx + 300f * f, cy, cx + 260f * f, cy + 40f * f)
        }
        canvas.drawPath(whirly, stroke)
    }

    fun drawRubiksCube(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Isometric 3D Puzzle Cube
        val size = 180f * f
        // Top Face (Diamond)
        val topFace = Path().apply {
            moveTo(cx, cy - size * 1.5f)
            lineTo(cx + size * 1.3f, cy - size * 0.75f)
            lineTo(cx, cy)
            lineTo(cx - size * 1.3f, cy - size * 0.75f)
            close()
        }
        canvas.drawPath(topFace, stroke)

        // Left Face
        val leftFace = Path().apply {
            moveTo(cx - size * 1.3f, cy - size * 0.75f)
            lineTo(cx, cy)
            lineTo(cx, cy + size * 1.5f)
            lineTo(cx - size * 1.3f, cy + size * 0.75f)
            close()
        }
        canvas.drawPath(leftFace, stroke)

        // Right Face
        val rightFace = Path().apply {
            moveTo(cx, cy)
            lineTo(cx + size * 1.3f, cy - size * 0.75f)
            lineTo(cx + size * 1.3f, cy + size * 0.75f)
            lineTo(cx, cy + size * 1.5f)
            close()
        }
        canvas.drawPath(rightFace, stroke)

        // Grid lines dividing each face into 3x3 squares
        // Top face grids
        for (i in 1..2) {
            val t = i / 3f
            canvas.drawLine(
                cx - size * 1.3f * (1 - t), cy - size * 0.75f - size * 0.75f * t,
                cx + size * 1.3f * t, cy - size * 0.75f * (1 - t),
                fine
            )
            canvas.drawLine(
                cx + size * 1.3f * (1 - t), cy - size * 0.75f - size * 0.75f * t,
                cx - size * 1.3f * t, cy - size * 0.75f * (1 - t),
                fine
            )
        }
        // Left & Right face grids
        for (i in 1..2) {
            val t = i / 3f
            canvas.drawLine(cx - size * 1.3f * (1 - t), cy - size * 0.75f * (1 - t), cx - size * 1.3f * (1 - t), cy + size * 0.75f + size * 0.75f * t, fine)
            canvas.drawLine(cx - size * 1.3f, cy - size * 0.75f + size * 1.5f * t, cx, cy + size * 1.5f * t, fine)
            canvas.drawLine(cx + size * 1.3f * (1 - t), cy - size * 0.75f * (1 - t), cx + size * 1.3f * (1 - t), cy + size * 0.75f + size * 0.75f * t, fine)
            canvas.drawLine(cx, cy + size * 1.5f * t, cx + size * 1.3f, cy - size * 0.75f + size * 1.5f * t, fine)
        }
    }

    fun drawRockingHorse(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Curved Wooden Rockers at bottom
        val rocker = Path().apply {
            moveTo(cx - 360f * f, cy + 280f * f)
            cubicTo(cx - 180f * f, cy + 420f * f, cx + 180f * f, cy + 420f * f, cx + 360f * f, cy + 280f * f)
            lineTo(cx + 360f * f, cy + 320f * f)
            cubicTo(cx + 180f * f, cy + 460f * f, cx - 180f * f, cy + 460f * f, cx - 360f * f, cy + 320f * f)
            close()
        }
        canvas.drawPath(rocker, stroke)

        // Horse Body & Neck
        val horse = Path().apply {
            moveTo(cx - 240f * f, cy - 140f * f) // muzzle
            lineTo(cx - 160f * f, cy - 120f * f)
            cubicTo(cx - 100f * f, cy - 20f * f, cx - 40f * f, cy + 60f * f, cx, cy + 60f * f) // neck
            lineTo(cx + 180f * f, cy + 60f * f) // back
            cubicTo(cx + 260f * f, cy + 80f * f, cx + 280f * f, cy + 180f * f, cx + 240f * f, cy + 240f * f) // rump
            lineTo(cx - 160f * f, cy + 240f * f) // belly
            cubicTo(cx - 220f * f, cy + 140f * f, cx - 220f * f, cy + 40f * f, cx - 180f * f, cy - 60f * f)
            lineTo(cx - 220f * f, cy - 180f * f) // head top
            close()
        }
        canvas.drawPath(horse, stroke)

        // Yarn Mane
        val mane = Path().apply {
            moveTo(cx - 180f * f, cy - 160f * f)
            for (i in 0..5) {
                quadTo((cx - 130f + i * 20f) * f, (cy - 120f + i * 30f) * f, (cx - 160f + i * 20f) * f, (cy - 80f + i * 30f) * f)
            }
        }
        canvas.drawPath(mane, stroke)

        // Saddle
        val saddle = RectF(cx + 40f * f, cy + 40f * f, cx + 140f * f, cy + 120f * f)
        canvas.drawRoundRect(saddle, 18f * f, 18f * f, stroke)

        // Cute Eye
        drawCuteEye(canvas, cx - 160f * f, cy - 130f * f, 16f * f, stroke, eyeFill, eyeHighlight)

        // Sturdy Legs connecting to Rocker
        canvas.drawLine(cx - 140f * f, cy + 240f * f, cx - 220f * f, cy + 380f * f, stroke)
        canvas.drawLine(cx + 180f * f, cy + 240f * f, cx + 240f * f, cy + 380f * f, stroke)
    }
}
