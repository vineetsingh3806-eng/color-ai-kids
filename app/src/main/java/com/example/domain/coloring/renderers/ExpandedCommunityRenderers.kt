package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawHappySmile

object ExpandedCommunityRenderers {

    fun drawPoliceOfficer(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f
        // Head
        canvas.drawCircle(cx, cy, 110f * f, stroke)
        drawCuteEye(canvas, cx - 45f * f, cy - 20f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 20f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, cy + 30f * f, 40f * f, stroke)
        // Peaked officer cap
        val cap = Path().apply {
            moveTo(cx - 130f * f, cy - 80f * f)
            cubicTo(cx - 130f * f, cy - 220f * f, cx + 130f * f, cy - 220f * f, cx + 130f * f, cy - 80f * f)
            close()
        }
        canvas.drawPath(cap, stroke)
        // Cap visor bill
        canvas.drawOval(RectF(cx - 140f * f, cy - 90f * f, cx + 140f * f, cy - 50f * f), stroke)
        // Badge star on cap
        canvas.drawCircle(cx, cy - 140f * f, 18f * f, stroke)
        // Uniform shirt & tie
        canvas.drawRoundRect(RectF(cx - 140f * f, cy + 110f * f, cx + 140f * f, 860f * f), 40f * f, 40f * f, stroke)
        // Stop sign hand on right
        val sign = Path().apply {
            val sx = cx + 220f * f
            val sy = cy + 180f * f
            moveTo(sx - 40f * f, sy - 60f * f)
            lineTo(sx + 40f * f, sy - 60f * f)
            lineTo(sx + 60f * f, sy)
            lineTo(sx + 40f * f, sy + 60f * f)
            lineTo(sx - 40f * f, sy + 60f * f)
            lineTo(sx - 60f * f, sy)
            close()
        }
        canvas.drawPath(sign, stroke)
    }

    fun drawSchoolTeacher(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 420f * f
        // Teacher head with round glasses
        canvas.drawCircle(cx, 360f * f, 100f * f, stroke)
        // Glasses
        canvas.drawCircle(cx - 40f * f, 350f * f, 25f * f, stroke)
        canvas.drawCircle(cx + 40f * f, 350f * f, 25f * f, stroke)
        canvas.drawLine(cx - 15f * f, 350f * f, cx + 15f * f, 350f * f, stroke)
        drawCuteEye(canvas, cx - 40f * f, 350f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, 350f * f, 10f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, 400f * f, 35f * f, stroke)
        // Body
        canvas.drawRoundRect(RectF(cx - 120f * f, 460f * f, cx + 120f * f, 860f * f), 30f * f, 30f * f, stroke)
        // Blackboard on right
        canvas.drawRoundRect(RectF(600f * f, 220f * f, 880f * f, 620f * f), 15f * f, 15f * f, stroke)
        // ABC on board
        canvas.drawLine(660f * f, 320f * f, 680f * f, 260f * f, stroke)
        canvas.drawLine(680f * f, 260f * f, 700f * f, 320f * f, stroke)
        canvas.drawLine(670f * f, 300f * f, 690f * f, 300f * f, stroke)
        // Pointer stick
        canvas.drawLine(cx + 100f * f, 520f * f, 660f * f, 340f * f, stroke)
    }

    fun drawMailCarrier(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 440f * f
        // Head with postal sun hat
        canvas.drawCircle(cx, 380f * f, 100f * f, stroke)
        canvas.drawOval(RectF(cx - 140f * f, 280f * f, cx + 140f * f, 330f * f), stroke) // Hat brim
        drawCuteEye(canvas, cx - 35f * f, 370f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, 370f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, 410f * f, 35f * f, stroke)
        // Mailbag satchel over shoulder
        canvas.drawRoundRect(RectF(cx - 120f * f, 480f * f, cx + 100f * f, 860f * f), 30f * f, 30f * f, stroke)
        canvas.drawRoundRect(RectF(cx - 160f * f, 580f * f, cx - 60f * f, 740f * f), 15f * f, 15f * f, stroke) // Bag
        // Big blue mailbox on right
        val mailbox = Path().apply {
            moveTo(680f * f, 860f * f)
            lineTo(680f * f, 480f * f)
            cubicTo(680f * f, 380f * f, 840f * f, 380f * f, 840f * f, 480f * f)
            lineTo(840f * f, 860f * f)
            close()
        }
        canvas.drawPath(mailbox, stroke)
        // Letter slot
        canvas.drawRoundRect(RectF(710f * f, 480f * f, 810f * f, 520f * f), 6f * f, 6f * f, stroke)
    }

    fun drawGreenFarmer(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Wide straw hat brim & cone
        canvas.drawOval(RectF(cx - 200f * f, 260f * f, cx + 200f * f, 330f * f), stroke)
        canvas.drawArc(RectF(cx - 100f * f, 180f * f, cx + 100f * f, 300f * f), 180f, 180f, true, stroke)
        // Head
        canvas.drawCircle(cx, 370f * f, 100f * f, stroke)
        drawCuteEye(canvas, cx - 40f * f, 360f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, 360f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawHappySmile(canvas, cx, 400f * f, 35f * f, stroke)
        // Overalls body
        canvas.drawRoundRect(RectF(cx - 130f * f, 470f * f, cx + 130f * f, 860f * f), 30f * f, 30f * f, stroke)
        // Pitchfork on right
        canvas.drawLine(cx + 200f * f, 260f * f, cx + 200f * f, 860f * f, stroke)
        // 3 prongs
        canvas.drawLine(cx + 170f * f, 260f * f, cx + 170f * f, 180f * f, stroke)
        canvas.drawLine(cx + 200f * f, 260f * f, cx + 200f * f, 170f * f, stroke)
        canvas.drawLine(cx + 230f * f, 260f * f, cx + 230f * f, 180f * f, stroke)
        canvas.drawLine(cx + 170f * f, 260f * f, cx + 230f * f, 260f * f, stroke)
    }

    fun drawCommunityHospital(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Modern multi-story clinic building
        canvas.drawRoundRect(RectF(cx - 260f * f, 340f * f, cx + 260f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Red cross sign on top roof parapet
        val cross = Path().apply {
            moveTo(cx - 20f * f, 220f * f); lineTo(cx + 20f * f, 220f * f); lineTo(cx + 20f * f, 260f * f)
            lineTo(cx + 60f * f, 260f * f); lineTo(cx + 60f * f, 300f * f); lineTo(cx + 20f * f, 300f * f)
            lineTo(cx + 20f * f, 340f * f); lineTo(cx - 20f * f, 340f * f); lineTo(cx - 20f * f, 300f * f)
            lineTo(cx - 60f * f, 300f * f); lineTo(cx - 60f * f, 260f * f); lineTo(cx - 20f * f, 260f * f)
            close()
        }
        canvas.drawPath(cross, stroke)
        // Grid of hospital windows
        for (row in 0..2) {
            for (col in -2..2) {
                val wx = (cx + col * 85f) * f
                val wy = (400f + row * 90f) * f
                canvas.drawRect(wx - 25f * f, wy, wx + 25f * f, wy + 55f * f, stroke)
            }
        }
        // Emergency entrance double doors
        canvas.drawRoundRect(RectF(cx - 60f * f, 700f * f, cx + 60f * f, 860f * f), 10f * f, 10f * f, stroke)
    }

    fun drawFireStation(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Brick station building
        canvas.drawRoundRect(RectF(cx - 280f * f, 340f * f, cx + 280f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Lookout tower with bell
        canvas.drawRect(cx + 140f * f, 180f * f, cx + 240f * f, 340f * f, stroke)
        // Two large arched truck garage doors
        val door1 = Path().apply {
            moveTo(cx - 240f * f, 860f * f)
            lineTo(cx - 240f * f, 580f * f)
            cubicTo(cx - 240f * f, 500f * f, cx - 60f * f, 500f * f, cx - 60f * f, 580f * f)
            lineTo(cx - 60f * f, 860f * f)
        }
        val door2 = Path().apply {
            moveTo(cx - 20f * f, 860f * f)
            lineTo(cx - 20f * f, 580f * f)
            cubicTo(cx - 20f * f, 500f * f, cx + 160f * f, 500f * f, cx + 160f * f, 580f * f)
            lineTo(cx + 160f * f, 860f * f)
        }
        canvas.drawPath(door1, stroke); canvas.drawPath(door2, stroke)
    }

    fun drawTownLibrary(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Classical triangular pediment roof
        val pediment = Path().apply {
            moveTo(cx - 320f * f, 360f * f)
            lineTo(cx, 180f * f)
            lineTo(cx + 320f * f, 360f * f)
            close()
        }
        canvas.drawPath(pediment, stroke)
        // Open book emblem in pediment
        canvas.drawCircle(cx, 280f * f, 35f * f, stroke)
        // 4 Classical columns
        for (i in -2..1) {
            val colX = (cx + (i + 0.5f) * 140f) * f
            canvas.drawRect(colX - 25f * f, 360f * f, colX + 25f * f, 740f * f, stroke)
        }
        // Grand library steps
        for (i in 0..2) {
            val sy = (740f + i * 40f) * f
            val inset = (20f - i * 20f) * f
            canvas.drawRect((140f - inset) * f, sy, (884f + inset) * f, sy + 40f * f, stroke)
        }
    }

    fun drawCityAirport(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        // Control tower on left
        canvas.drawRect(200f * f, 320f * f, 300f * f, 780f * f, stroke)
        // Octagonal cab at top of tower
        canvas.drawRoundRect(RectF(160f * f, 220f * f, 340f * f, 320f * f), 15f * f, 15f * f, stroke)
        // Radar dome
        canvas.drawCircle(250f * f, 180f * f, 35f * f, stroke)
        // Terminal concourse building
        canvas.drawRoundRect(RectF(300f * f, 560f * f, 880f * f, 780f * f), 20f * f, 20f * f, stroke)
        // Airplane on runway
        val plane = Path().apply {
            val px = 620f * f
            val py = 460f * f
            moveTo(px - 140f * f, py)
            cubicTo(px - 140f * f, py - 40f * f, px + 140f * f, py - 40f * f, px + 180f * f, py)
            lineTo(px - 80f * f, py + 30f * f)
            close()
        }
        canvas.drawPath(plane, stroke)
        // Runway strip
        canvas.drawLine(100f * f, 820f * f, 924f * f, 820f * f, stroke)
    }

    fun drawCountryBarn(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 460f * f
        // Barn walls
        canvas.drawRect(cx - 200f * f, 460f * f, cx + 200f * f, 860f * f, stroke)
        // Gambrel roof
        val roof = Path().apply {
            moveTo(cx - 240f * f, 460f * f)
            lineTo(cx - 180f * f, 300f * f)
            lineTo(cx, 220f * f)
            lineTo(cx + 180f * f, 300f * f)
            lineTo(cx + 240f * f, 460f * f)
            close()
        }
        canvas.drawPath(roof, stroke)
        // Double barn doors with X braces
        canvas.drawRect(cx - 80f * f, 640f * f, cx + 80f * f, 860f * f, stroke)
        canvas.drawLine(cx - 80f * f, 640f * f, cx + 80f * f, 860f * f, stroke)
        canvas.drawLine(cx + 80f * f, 640f * f, cx - 80f * f, 860f * f, stroke)
        // Tall silo on right
        canvas.drawRect(cx + 220f * f, 360f * f, cx + 340f * f, 860f * f, stroke)
        canvas.drawArc(RectF(cx + 220f * f, 280f * f, cx + 340f * f, 440f * f), 180f, 180f, true, stroke)
    }

    fun drawPostOffice(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        // Post office building
        canvas.drawRoundRect(RectF(cx - 260f * f, 380f * f, cx + 260f * f, 860f * f), 20f * f, 20f * f, stroke)
        // Roof parapet
        canvas.drawRect(cx - 280f * f, 320f * f, cx + 280f * f, 380f * f, stroke)
        // Letter icon in center of parapet
        canvas.drawRect(cx - 50f * f, 260f * f, cx + 50f * f, 320f * f, stroke)
        canvas.drawLine(cx - 50f * f, 260f * f, cx, 290f * f, stroke)
        canvas.drawLine(cx + 50f * f, 260f * f, cx, 290f * f, stroke)
        // Entrance door & steps
        canvas.drawRoundRect(RectF(cx - 60f * f, 640f * f, cx + 60f * f, 860f * f), 10f * f, 10f * f, stroke)
        // Service counter windows
        canvas.drawRect(cx - 200f * f, 440f * f, cx - 100f * f, 560f * f, stroke)
        canvas.drawRect(cx + 100f * f, 440f * f, cx + 200f * f, 560f * f, stroke)
    }
}
