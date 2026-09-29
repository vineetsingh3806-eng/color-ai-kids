package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCloud
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawGroundGrass
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle
import com.example.domain.coloring.renderers.DrawingUtils.drawStar

object FairytaleRenderers {

    fun drawFairyCastle(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f

        // Central High Tower
        val mainTower = RectF(cx - 100f * f, 320f * f, cx + 100f * f, 720f * f)
        canvas.drawRect(mainTower, stroke)

        // Main Tower Cone Roof
        val mainCone = Path().apply {
            moveTo(cx - 130f * f, 320f * f)
            lineTo(cx, 120f * f)
            lineTo(cx + 130f * f, 320f * f)
            close()
        }
        canvas.drawPath(mainCone, stroke)

        // Flag on Main Tower
        canvas.drawLine(cx, 120f * f, cx, 60f * f, stroke)
        val flagM = Path().apply {
            moveTo(cx, 60f * f)
            lineTo(cx + 60f * f, 80f * f)
            lineTo(cx, 100f * f)
            close()
        }
        canvas.drawPath(flagM, stroke)

        // Left Tower
        val towerL = RectF(cx - 280f * f, 440f * f, cx - 140f * f, 760f * f)
        canvas.drawRect(towerL, stroke)
        val coneL = Path().apply {
            moveTo(cx - 300f * f, 440f * f)
            lineTo(cx - 210f * f, 240f * f)
            lineTo(cx - 120f * f, 440f * f)
            close()
        }
        canvas.drawPath(coneL, stroke)

        // Right Tower
        val towerR = RectF(cx + 140f * f, 440f * f, cx + 280f * f, 760f * f)
        canvas.drawRect(towerR, stroke)
        val coneR = Path().apply {
            moveTo(cx + 120f * f, 440f * f)
            lineTo(cx + 210f * f, 240f * f)
            lineTo(cx + 300f * f, 440f * f)
            close()
        }
        canvas.drawPath(coneR, stroke)

        // Castle Wall Ramparts / Battlements
        for (i in -4..4) {
            val bx = cx + (i * 45f) * f
            canvas.drawRect(RectF(bx - 15f * f, 660f * f, bx + 15f * f, 690f * f), stroke)
        }

        // Arched Grand Portcullis Gate
        val gate = Path().apply {
            moveTo(cx - 60f * f, 880f * f)
            lineTo(cx - 60f * f, 740f * f)
            cubicTo(cx - 60f * f, 680f * f, cx + 60f * f, 680f * f, cx + 60f * f, 740f * f)
            lineTo(cx + 60f * f, 880f * f)
            close()
        }
        canvas.drawPath(gate, stroke)
        // Gate Portcullis Grids
        canvas.drawLine(cx, 700f * f, cx, 880f * f, fine)
        canvas.drawLine(cx - 30f * f, 720f * f, cx - 30f * f, 880f * f, fine)
        canvas.drawLine(cx + 30f * f, 720f * f, cx + 30f * f, 880f * f, fine)

        // Windows
        canvas.drawRoundRect(RectF(cx - 30f * f, 400f * f, cx + 30f * f, 480f * f), 15f * f, 15f * f, stroke)
        canvas.drawRoundRect(RectF(cx - 230f * f, 520f * f, cx - 190f * f, 580f * f), 12f * f, 12f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 190f * f, 520f * f, cx + 230f * f, 580f * f), 12f * f, 12f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawCrown(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Crown Base Band
        val baseBand = RectF(cx - 280f * f, cy + 120f * f, cx + 280f * f, cy + 220f * f)
        canvas.drawRoundRect(baseBand, 20f * f, 20f * f, stroke)

        // Royal Crown Peaks (5 peaks: middle highest)
        val peaks = Path().apply {
            moveTo(cx - 280f * f, cy + 120f * f)
            lineTo(cx - 260f * f, cy - 100f * f) // outer left peak
            lineTo(cx - 160f * f, cy + 20f * f)
            lineTo(cx - 100f * f, cy - 180f * f) // mid-left peak
            lineTo(cx - 20f * f, cy + 40f * f)
            lineTo(cx, cy - 240f * f) // center high peak
            lineTo(cx + 20f * f, cy + 40f * f)
            lineTo(cx + 100f * f, cy - 180f * f) // mid-right peak
            lineTo(cx + 160f * f, cy + 20f * f)
            lineTo(cx + 260f * f, cy - 100f * f) // outer right peak
            lineTo(cx + 280f * f, cy + 120f * f)
            close()
        }
        canvas.drawPath(peaks, stroke)

        // Pearl Jewels on the 5 tips
        canvas.drawCircle(cx - 260f * f, cy - 100f * f, 24f * f, stroke)
        canvas.drawCircle(cx - 100f * f, cy - 180f * f, 28f * f, stroke)
        canvas.drawCircle(cx, cy - 240f * f, 36f * f, stroke)
        canvas.drawCircle(cx + 100f * f, cy - 180f * f, 28f * f, stroke)
        canvas.drawCircle(cx + 260f * f, cy - 100f * f, 24f * f, stroke)

        // Large Diamond Gem in Center
        val gemCenter = Path().apply {
            moveTo(cx, cy - 60f * f)
            lineTo(cx + 40f * f, cy)
            lineTo(cx, cy + 60f * f)
            lineTo(cx - 40f * f, cy)
            close()
        }
        canvas.drawPath(gemCenter, stroke)

        // Oval Jewels on the base band
        for (i in -2..2) {
            val jx = cx + (i * 90f) * f
            canvas.drawOval(RectF(jx - 24f * f, cy + 145f * f, jx + 24f * f, cy + 195f * f), stroke)
        }

        // Royal Sparkles
        drawSparkle(canvas, cx - 280f * f, cy - 220f * f, 30f * f, stroke)
        drawSparkle(canvas, cx + 280f * f, cy - 200f * f, 34f * f, stroke)
    }

    fun drawPegasus(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 480f * f
        val cy = 460f * f

        // Feathered Wings Spread High
        val wing = Path().apply {
            moveTo(cx - 40f * f, cy - 60f * f)
            cubicTo(cx - 120f * f, cy - 260f * f, cx - 80f * f, cy - 380f * f, cx + 40f * f, cy - 400f * f)
            cubicTo(cx + 100f * f, cy - 380f * f, cx + 180f * f, cy - 320f * f, cx + 260f * f, cy - 240f * f)
            lineTo(cx + 200f * f, cy - 200f * f)
            lineTo(cx + 240f * f, cy - 140f * f)
            lineTo(cx + 160f * f, cy - 100f * f)
            lineTo(cx + 40f * f, cy - 40f * f)
            close()
        }
        canvas.drawPath(wing, stroke)

        // Wing Feathers detail
        canvas.drawLine(cx + 40f * f, cy - 300f * f, cx + 180f * f, cy - 220f * f, fine)
        canvas.drawLine(cx + 20f * f, cy - 200f * f, cx + 160f * f, cy - 140f * f, fine)

        // Pegasus Head & Mane
        val head = Path().apply {
            moveTo(cx - 100f * f, cy - 140f * f)
            cubicTo(cx - 80f * f, cy - 240f * f, cx - 180f * f, cy - 280f * f, cx - 260f * f, cy - 240f * f)
            cubicTo(cx - 300f * f, cy - 220f * f, cx - 300f * f, cy - 160f * f, cx - 240f * f, cy - 140f * f)
            lineTo(cx - 160f * f, cy - 130f * f)
            cubicTo(cx - 120f * f, cy - 60f * f, cx - 80f * f, cy + 20f * f, cx - 60f * f, cy + 80f * f)
            lineTo(cx - 140f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(head, stroke)

        // Friendly Eye
        drawCuteEye(canvas, cx - 190f * f, cy - 190f * f, 18f * f, stroke, eyeFill, eyeHighlight)

        // Pegasus Body & Galloping Legs
        val body = Path().apply {
            moveTo(cx - 80f * f, cy + 60f * f)
            cubicTo(cx, cy + 40f * f, cx + 140f * f, cy + 40f * f, cx + 220f * f, cy + 120f * f)
            cubicTo(cx + 280f * f, cy + 180f * f, cx + 260f * f, cy + 260f * f, cx + 200f * f, cy + 280f * f)
            lineTo(cx - 40f * f, cy + 280f * f)
            cubicTo(cx - 120f * f, cy + 240f * f, cx - 140f * f, cy + 140f * f, cx - 80f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(body, stroke)

        // Galloping Legs
        canvas.drawRoundRect(RectF(cx - 120f * f, cy + 260f * f, cx - 60f * f, cy + 420f * f), 18f * f, 18f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 120f * f, cy + 260f * f, cx + 180f * f, cy + 420f * f), 18f * f, 18f * f, stroke)

        // Fluffy Clouds below for flying
        drawCloud(canvas, 240f * f, 840f * f, 240f * f, 90f * f, stroke)
        drawCloud(canvas, 740f * f, 820f * f, 260f * f, 100f * f, stroke)
    }

    fun drawPrincessCarriage(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 460f * f

        // Pumpkin Carriage Round Body
        canvas.drawCircle(cx, cy, 210f * f, stroke)

        // Pumpkin Segments / Ribs
        val ribL = RectF(cx - 150f * f, cy - 210f * f, cx + 50f * f, cy + 210f * f)
        canvas.drawArc(ribL, 90f, 180f, false, fine)
        val ribR = RectF(cx - 50f * f, cy - 210f * f, cx + 150f * f, cy + 210f * f)
        canvas.drawArc(ribR, 270f, 180f, false, fine)

        // Crown on Carriage Roof
        val roofCrown = Path().apply {
            moveTo(cx - 40f * f, cy - 210f * f)
            lineTo(cx - 30f * f, cy - 260f * f)
            lineTo(cx, cy - 240f * f)
            lineTo(cx + 30f * f, cy - 260f * f)
            lineTo(cx + 40f * f, cy - 210f * f)
            close()
        }
        canvas.drawPath(roofCrown, stroke)

        // Heart-shaped Royal Window
        DrawingUtils.drawHeart(canvas, cx, cy - 30f * f, 110f * f, stroke)

        // Ornate Golden Wheels
        val wheelL = RectF(cx - 340f * f, cy + 120f * f, cx - 140f * f, cy + 320f * f)
        canvas.drawOval(wheelL, stroke)
        canvas.drawCircle(cx - 240f * f, cy + 220f * f, 35f * f, stroke)
        // Wheel spokes
        canvas.drawLine(cx - 240f * f, cy + 120f * f, cx - 240f * f, cy + 320f * f, fine)
        canvas.drawLine(cx - 340f * f, cy + 220f * f, cx - 140f * f, cy + 220f * f, fine)

        val wheelR = RectF(cx + 140f * f, cy + 120f * f, cx + 340f * f, cy + 320f * f)
        canvas.drawOval(wheelR, stroke)
        canvas.drawCircle(cx + 240f * f, cy + 220f * f, 35f * f, stroke)
        canvas.drawLine(cx + 240f * f, cy + 120f * f, cx + 240f * f, cy + 320f * f, fine)
        canvas.drawLine(cx + 140f * f, cy + 220f * f, cx + 340f * f, cy + 220f * f, fine)

        // Connecting chassis bar
        canvas.drawLine(cx - 240f * f, cy + 220f * f, cx + 240f * f, cy + 220f * f, stroke)

        drawSparkle(canvas, cx - 280f * f, cy - 160f * f, 28f * f, stroke)
        drawSparkle(canvas, cx + 280f * f, cy - 140f * f, 32f * f, stroke)
    }

    fun drawRoyalShield(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 500f * f

        // Crossed Knight Swords Behind Shield
        // Sword 1
        val sword1 = Path().apply {
            moveTo(cx - 280f * f, cy - 300f * f)
            lineTo(cx + 280f * f, cy + 280f * f)
        }
        canvas.drawPath(sword1, stroke)
        // Sword 2
        val sword2 = Path().apply {
            moveTo(cx + 280f * f, cy - 300f * f)
            lineTo(cx - 280f * f, cy + 280f * f)
        }
        canvas.drawPath(sword2, stroke)

        // Classic Heater Royal Shield
        val shield = Path().apply {
            moveTo(cx - 220f * f, cy - 240f * f)
            lineTo(cx + 220f * f, cy - 240f * f)
            cubicTo(cx + 220f * f, cy + 40f * f, cx + 180f * f, cy + 200f * f, cx, cy + 320f * f)
            cubicTo(cx - 180f * f, cy + 200f * f, cx - 220f * f, cy + 40f * f, cx - 220f * f, cy - 240f * f)
            close()
        }
        canvas.drawPath(shield, stroke)

        // Inner Shield Border
        val innerShield = Path().apply {
            moveTo(cx - 180f * f, cy - 200f * f)
            lineTo(cx + 180f * f, cy - 200f * f)
            cubicTo(cx + 180f * f, cy + 30f * f, cx + 140f * f, cy + 170f * f, cx, cy + 270f * f)
            cubicTo(cx - 140f * f, cy + 170f * f, cx - 180f * f, cy + 30f * f, cx - 180f * f, cy - 200f * f)
            close()
        }
        canvas.drawPath(innerShield, fine)

        // Quartered Cross dividing shield
        canvas.drawLine(cx, cy - 200f * f, cx, cy + 270f * f, stroke)
        canvas.drawLine(cx - 180f * f, cy, cx + 180f * f, cy, stroke)

        // Emblems in the 4 quarters: Lion, Crown, Star, Fleur
        drawStar(canvas, cx - 80f * f, cy - 100f * f, 36f * f, stroke)
        drawStar(canvas, cx + 80f * f, cy + 120f * f, 36f * f, stroke)
        canvas.drawCircle(cx + 80f * f, cy - 100f * f, 32f * f, stroke)
        canvas.drawCircle(cx - 80f * f, cy + 120f * f, 32f * f, stroke)
    }

    fun drawFairytalePrince(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Crown on Prince Head
        val crown = Path().apply {
            moveTo(cx - 45f * f, cy - 180f * f)
            lineTo(cx - 40f * f, cy - 230f * f)
            lineTo(cx - 20f * f, cy - 205f * f)
            lineTo(cx, cy - 240f * f)
            lineTo(cx + 20f * f, cy - 205f * f)
            lineTo(cx + 40f * f, cy - 230f * f)
            lineTo(cx + 45f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(crown, stroke)

        // Prince Head & Hair
        canvas.drawCircle(cx, cy - 120f * f, 70f * f, stroke)
        // Hair bangs
        val hair = Path().apply {
            moveTo(cx - 65f * f, cy - 140f * f)
            quadTo(cx - 20f * f, cy - 110f * f, cx + 65f * f, cy - 150f * f)
        }
        canvas.drawPath(hair, fine)

        // Cute Prince Face
        drawCuteEye(canvas, cx - 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 16f * f, cy - 90f * f)
            quadTo(cx, cy - 75f * f, cx + 16f * f, cy - 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Royal Cape behind
        val cape = Path().apply {
            moveTo(cx - 80f * f, cy - 30f * f)
            lineTo(cx - 180f * f, cy + 400f * f)
            lineTo(cx + 180f * f, cy + 400f * f)
            lineTo(cx + 80f * f, cy - 30f * f)
            close()
        }
        canvas.drawPath(cape, stroke)

        // Royal Tunic Jacket & Belt
        val tunic = RectF(cx - 70f * f, cy - 40f * f, cx + 70f * f, cy + 200f * f)
        canvas.drawRoundRect(tunic, 18f * f, 18f * f, stroke)
        // Belt with buckle
        canvas.drawRect(RectF(cx - 70f * f, cy + 130f * f, cx + 70f * f, cy + 170f * f), stroke)
        canvas.drawRoundRect(RectF(cx - 22f * f, cy + 125f * f, cx + 22f * f, cy + 175f * f), 6f * f, 6f * f, stroke)

        // Royal Boots
        canvas.drawRoundRect(RectF(cx - 65f * f, cy + 200f * f, cx - 10f * f, cy + 440f * f), 18f * f, 18f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 10f * f, cy + 200f * f, cx + 65f * f, cy + 440f * f), 18f * f, 18f * f, stroke)

        drawGroundGrass(canvas, s, stroke)
    }

    fun drawFairytalePrincess(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 400f * f

        // Royal Tiara
        val tiara = Path().apply {
            moveTo(cx - 35f * f, cy - 180f * f)
            lineTo(cx - 25f * f, cy - 220f * f)
            lineTo(cx, cy - 240f * f)
            lineTo(cx + 25f * f, cy - 220f * f)
            lineTo(cx + 35f * f, cy - 180f * f)
            close()
        }
        canvas.drawPath(tiara, stroke)

        // Long Wavy Hair
        val hair = Path().apply {
            moveTo(cx - 70f * f, cy - 140f * f)
            cubicTo(cx - 140f * f, cy, cx - 140f * f, cy + 200f * f, cx - 90f * f, cy + 280f * f)
            moveTo(cx + 70f * f, cy - 140f * f)
            cubicTo(cx + 140f * f, cy, cx + 140f * f, cy + 200f * f, cx + 90f * f, cy + 280f * f)
        }
        canvas.drawPath(hair, stroke)

        // Princess Head
        canvas.drawCircle(cx, cy - 120f * f, 70f * f, stroke)

        // Cute Eyes with lashes
        drawCuteEye(canvas, cx - 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 25f * f, cy - 120f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 16f * f, cy - 90f * f)
            quadTo(cx, cy - 75f * f, cx + 16f * f, cy - 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Princess Ballgown Dress (Giant Bell Skirt)
        val dress = Path().apply {
            moveTo(cx - 40f * f, cy - 40f * f) // bodice
            lineTo(cx + 40f * f, cy - 40f * f)
            lineTo(cx + 50f * f, cy + 60f * f)
            cubicTo(cx + 180f * f, cy + 120f * f, cx + 260f * f, cy + 280f * f, cx + 240f * f, cy + 440f * f)
            lineTo(cx - 240f * f, cy + 440f * f)
            cubicTo(cx - 260f * f, cy + 280f * f, cx - 180f * f, cy + 120f * f, cx - 50f * f, cy + 60f * f)
            close()
        }
        canvas.drawPath(dress, stroke)

        // Ruffles on Dress Hem
        val hem = Path().apply {
            moveTo(cx - 240f * f, cy + 440f * f)
            var x = cx - 240f * f
            while (x < cx + 240f * f) {
                quadTo(x + 30f * f, cy + 420f * f, x + 60f * f, cy + 440f * f)
                x += 60f * f
            }
        }
        canvas.drawPath(hem, stroke)

        // Sparkles around dress
        drawSparkle(canvas, cx - 220f * f, cy - 100f * f, 26f * f, stroke)
        drawSparkle(canvas, cx + 220f * f, cy - 80f * f, 28f * f, stroke)
    }

    fun drawGlassSlipper(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 440f * f

        // Royal Plush Velvet Pillow
        val pillow = Path().apply {
            moveTo(cx - 280f * f, cy + 160f * f)
            cubicTo(cx - 260f * f, cy + 60f * f, cx + 260f * f, cy + 60f * f, cx + 280f * f, cy + 160f * f)
            cubicTo(cx + 320f * f, cy + 260f * f, cx + 240f * f, cy + 340f * f, cx + 220f * f, cy + 360f * f)
            cubicTo(cx + 100f * f, cy + 390f * f, cx - 100f * f, cy + 390f * f, cx - 220f * f, cy + 360f * f)
            cubicTo(cx - 240f * f, cy + 340f * f, cx - 320f * f, cy + 260f * f, cx - 280f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(pillow, stroke)

        // Corner Tassels on Pillow
        for ((px, py) in listOf(cx - 280f to cy + 160f, cx + 280f to cy + 160f, cx - 220f to cy + 360f, cx + 220f to cy + 360f)) {
            canvas.drawCircle(px * f, py * f, 16f * f, stroke)
        }

        // Sparkling Crystal Glass Slipper Resting on Pillow
        val slipper = Path().apply {
            moveTo(cx - 180f * f, cy + 120f * f) // toe
            cubicTo(cx - 120f * f, cy + 120f * f, cx - 60f * f, cy + 60f * f, cx, cy - 20f * f)
            lineTo(cx + 120f * f, cy - 140f * f) // ankle collar
            cubicTo(cx + 150f * f, cy - 100f * f, cx + 160f * f, cy - 40f * f, cx + 140f * f, cy + 40f * f) // heel back
            lineTo(cx + 140f * f, cy + 140f * f) // stiletto heel
            lineTo(cx + 115f * f, cy + 140f * f)
            lineTo(cx + 115f * f, cy + 50f * f)
            cubicTo(cx + 60f * f, cy + 70f * f, cx - 80f * f, cy + 140f * f, cx - 180f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(slipper, stroke)

        // Heart Gem on Slipper Toe
        DrawingUtils.drawHeart(canvas, cx - 140f * f, cy + 100f * f, 30f * f, stroke)

        // Radiant Glass Sparkles
        drawSparkle(canvas, cx + 180f * f, cy - 180f * f, 36f * f, stroke)
        drawSparkle(canvas, cx - 140f * f, cy - 60f * f, 30f * f, stroke)
        drawSparkle(canvas, cx, cy - 160f * f, 24f * f, stroke)
    }

    fun drawRoyalThrone(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // High Ornate Throne Backrest
        val backrest = Path().apply {
            moveTo(cx - 160f * f, cy + 100f * f)
            lineTo(cx - 160f * f, cy - 260f * f)
            cubicTo(cx - 160f * f, cy - 360f * f, cx + 160f * f, cy - 360f * f, cx + 160f * f, cy - 260f * f)
            lineTo(cx + 160f * f, cy + 100f * f)
            close()
        }
        canvas.drawPath(backrest, stroke)

        // Top Arch Crest Crown on Throne
        drawCrown(canvas, (s * 0.4f).toInt(), stroke, fine, eyeFill, eyeHighlight)

        // Deep Tufted Diamond Pattern on Backrest
        canvas.drawLine(cx - 120f * f, cy - 220f * f, cx + 120f * f, cy + 20f * f, fine)
        canvas.drawLine(cx + 120f * f, cy - 220f * f, cx - 120f * f, cy + 20f * f, fine)

        // Plush Seat Cushion
        val seat = RectF(cx - 200f * f, cy + 80f * f, cx + 200f * f, cy + 180f * f)
        canvas.drawRoundRect(seat, 24f * f, 24f * f, stroke)

        // Heavy Lion-paw Armrests
        val armL = RectF(cx - 240f * f, cy, cx - 170f * f, cy + 160f * f)
        canvas.drawRoundRect(armL, 16f * f, 16f * f, stroke)
        val armR = RectF(cx + 170f * f, cy, cx + 240f * f, cy + 160f * f)
        canvas.drawRoundRect(armR, 16f * f, 16f * f, stroke)

        // Sturdy Golden Throne Legs
        canvas.drawRoundRect(RectF(cx - 200f * f, cy + 180f * f, cx - 130f * f, cy + 340f * f), 18f * f, 18f * f, stroke)
        canvas.drawRoundRect(RectF(cx + 130f * f, cy + 180f * f, cx + 200f * f, cy + 340f * f), 18f * f, 18f * f, stroke)

        // Royal Carpet Dais below
        val carpet = Path().apply {
            moveTo(cx - 320f * f, cy + 340f * f)
            lineTo(cx + 320f * f, cy + 340f * f)
            lineTo(cx + 360f * f, cy + 400f * f)
            lineTo(cx - 360f * f, cy + 400f * f)
            close()
        }
        canvas.drawPath(carpet, stroke)
    }

    fun drawSpinningWheel(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Large Wooden Spoked Flywheel
        canvas.drawCircle(cx - 100f * f, cy - 40f * f, 170f * f, stroke)
        canvas.drawCircle(cx - 100f * f, cy - 40f * f, 140f * f, fine)
        canvas.drawCircle(cx - 100f * f, cy - 40f * f, 24f * f, stroke)

        // 8 Spokes on wheel
        for (i in 0 until 8) {
            val angle = i * Math.PI / 4
            val x1 = (cx - 100f + Math.cos(angle) * 24f * f).toFloat()
            val y1 = (cy - 40f + Math.sin(angle) * 24f * f).toFloat()
            val x2 = (cx - 100f + Math.cos(angle) * 140f * f).toFloat()
            val y2 = (cy - 40f + Math.sin(angle) * 140f * f).toFloat()
            canvas.drawLine(x1, y1, x2, y2, fine)
        }

        // Slanted Wooden Bench Base
        val bench = Path().apply {
            moveTo(cx - 280f * f, cy + 180f * f)
            lineTo(cx + 260f * f, cy + 120f * f)
            lineTo(cx + 260f * f, cy + 160f * f)
            lineTo(cx - 280f * f, cy + 220f * f)
            close()
        }
        canvas.drawPath(bench, stroke)

        // Uprights supporting wheel hub
        canvas.drawLine(cx - 100f * f, cy - 40f * f, cx - 100f * f, cy + 200f * f, stroke)

        // Distaff with Spindle of Wool / Thread on right
        canvas.drawLine(cx + 180f * f, cy - 240f * f, cx + 180f * f, cy + 140f * f, stroke)
        // Fluffy bundle of wool on distaff
        val wool = RectF(cx + 140f * f, cy - 200f * f, cx + 220f * f, cy - 60f * f)
        canvas.drawRoundRect(wool, 30f * f, 30f * f, stroke)

        // Thread drive band
        canvas.drawLine(cx - 100f * f, cy - 210f * f, cx + 180f * f, cy - 40f * f, fine)

        // Legs on bench
        canvas.drawLine(cx - 220f * f, cy + 210f * f, cx - 240f * f, cy + 380f * f, stroke)
        canvas.drawLine(cx + 200f * f, cy + 150f * f, cx + 220f * f, cy + 380f * f, stroke)

        drawSparkle(canvas, cx + 240f * f, cy - 260f * f, 28f * f, stroke)
    }
}
