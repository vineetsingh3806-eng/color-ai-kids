package com.example.domain.coloring.renderers

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.example.domain.coloring.renderers.DrawingUtils.drawCuteEye
import com.example.domain.coloring.renderers.DrawingUtils.drawHeart
import com.example.domain.coloring.renderers.DrawingUtils.drawSparkle

object FoodRenderers {

    fun drawIceCreamCone(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 520f * f

        // Waffle Cone (Triangle pointing down)
        val cone = Path().apply {
            moveTo(cx - 160f * f, cy + 40f * f)
            lineTo(cx, cy + 420f * f)
            lineTo(cx + 160f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(cone, stroke)

        // Waffle Criss-Cross Grid Pattern
        for (i in -2..2) {
            canvas.drawLine((cx - 120f + i * 50f) * f, (cy + 70f) * f, (cx + i * 25f) * f, (cy + 360f) * f, fine)
            canvas.drawLine((cx + 120f - i * 50f) * f, (cy + 70f) * f, (cx - i * 25f) * f, (cy + 360f) * f, fine)
        }

        // Bottom Ice Cream Scoop
        val scoop1 = Path().apply {
            moveTo(cx - 180f * f, cy + 50f * f)
            cubicTo(cx - 220f * f, cy - 80f * f, cx + 220f * f, cy - 80f * f, cx + 180f * f, cy + 50f * f)
            // Drips on bottom rim
            var x = cx + 180f * f
            while (x > cx - 180f * f) {
                quadTo(x - 30f * f, cy + 85f * f, x - 60f * f, cy + 50f * f)
                x -= 60f * f
            }
            close()
        }
        canvas.drawPath(scoop1, stroke)

        // Cute Face on Ice Cream Scoop
        drawCuteEye(canvas, cx - 45f * f, cy - 10f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 10f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 25f * f, cy + 25f * f)
            quadTo(cx, cy + 45f * f, cx + 25f * f, cy + 25f * f)
        }
        canvas.drawPath(smile, stroke)

        // Top Ice Cream Scoop
        val scoop2 = Path().apply {
            moveTo(cx - 150f * f, cy - 60f * f)
            cubicTo(cx - 180f * f, cy - 220f * f, cx + 180f * f, cy - 220f * f, cx + 150f * f, cy - 60f * f)
            close()
        }
        canvas.drawPath(scoop2, stroke)

        // Cherry on Top with Curving Stem
        canvas.drawCircle(cx, cy - 250f * f, 40f * f, stroke)
        val stem = Path().apply {
            moveTo(cx, cy - 290f * f)
            cubicTo(cx + 30f * f, cy - 360f * f, cx + 80f * f, cy - 380f * f, cx + 70f * f, cy - 400f * f)
        }
        canvas.drawPath(stem, stroke)

        drawSparkle(canvas, cx - 220f * f, cy - 220f * f, 28f * f, stroke)
        drawSparkle(canvas, cx + 220f * f, cy - 180f * f, 32f * f, stroke)
    }

    fun drawBirthdayCake(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 600f * f

        // Tier 1 (Bottom Cake Tier)
        val tier1 = RectF(cx - 280f * f, cy, cx + 280f * f, cy + 220f * f)
        canvas.drawRoundRect(tier1, 24f * f, 24f * f, stroke)
        // Frosting drip line
        val drip1 = Path().apply {
            moveTo(cx - 280f * f, cy + 50f * f)
            var x = cx - 280f * f
            while (x < cx + 280f * f) {
                quadTo(x + 35f * f, cy + 90f * f, x + 70f * f, cy + 50f * f)
                x += 70f * f
            }
        }
        canvas.drawPath(drip1, fine)

        // Tier 2 (Top Cake Tier)
        val tier2 = RectF(cx - 180f * f, cy - 200f * f, cx + 180f * f, cy)
        canvas.drawRoundRect(tier2, 20f * f, 20f * f, stroke)
        val drip2 = Path().apply {
            moveTo(cx - 180f * f, cy - 150f * f)
            var x = cx - 180f * f
            while (x < cx + 180f * f) {
                quadTo(x + 30f * f, cy - 120f * f, x + 60f * f, cy - 150f * f)
                x += 60f * f
            }
        }
        canvas.drawPath(drip2, fine)

        // 3 Lit Birthday Candles on Top
        for (i in -1..1) {
            val kx = cx + (i * 90f) * f
            // Candle stick
            canvas.drawRoundRect(RectF(kx - 14f * f, cy - 320f * f, kx + 14f * f, cy - 200f * f), 6f * f, 6f * f, stroke)
            // Wick
            canvas.drawLine(kx, cy - 320f * f, kx, cy - 345f * f, stroke)
            // Teardrop Candle Flame
            val flame = Path().apply {
                moveTo(kx, cy - 395f * f)
                cubicTo(kx + 20f * f, cy - 370f * f, kx + 16f * f, cy - 345f * f, kx, cy - 345f * f)
                cubicTo(kx - 16f * f, cy - 345f * f, kx - 20f * f, cy - 370f * f, kx, cy - 395f * f)
                close()
            }
            canvas.drawPath(flame, stroke)
        }

        // Cake Polka Dots & Strawberries
        canvas.drawCircle(cx - 160f * f, cy + 150f * f, 22f * f, stroke)
        canvas.drawCircle(cx, cy + 150f * f, 22f * f, stroke)
        canvas.drawCircle(cx + 160f * f, cy + 150f * f, 22f * f, stroke)

        // Cake Platter Plate
        val plate = RectF(cx - 340f * f, cy + 200f * f, cx + 340f * f, cy + 260f * f)
        canvas.drawOval(plate, stroke)
    }

    fun drawCupcake(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 540f * f

        // Fluted Cupcake Liner Base
        val liner = Path().apply {
            moveTo(cx - 200f * f, cy + 40f * f)
            lineTo(cx - 150f * f, cy + 340f * f)
            lineTo(cx + 150f * f, cy + 340f * f)
            lineTo(cx + 200f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(liner, stroke)

        // Liner Vertical Pleats
        for (i in -2..2) {
            val xTop = cx + (i * 70f) * f
            val xBot = cx + (i * 50f) * f
            canvas.drawLine(xTop, cy + 40f * f, xBot, cy + 340f * f, fine)
        }

        // Swirling Soft Frosting Mountain
        val frosting = Path().apply {
            moveTo(cx - 230f * f, cy + 40f * f)
            cubicTo(cx - 260f * f, cy - 40f * f, cx - 180f * f, cy - 100f * f, cx - 140f * f, cy - 80f * f)
            cubicTo(cx - 200f * f, cy - 180f * f, cx - 80f * f, cy - 240f * f, cx, cy - 280f * f) // top swirl
            cubicTo(cx + 80f * f, cy - 240f * f, cx + 200f * f, cy - 180f * f, cx + 140f * f, cy - 80f * f)
            cubicTo(cx + 180f * f, cy - 100f * f, cx + 260f * f, cy - 40f * f, cx + 230f * f, cy + 40f * f)
            close()
        }
        canvas.drawPath(frosting, stroke)

        // Heart Topper on Peak
        drawHeart(canvas, cx, cy - 320f * f, 60f * f, stroke)

        // Cute Face on Frosting
        drawCuteEye(canvas, cx - 45f * f, cy - 40f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 40f * f, 18f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 25f * f, cy - 5f * f)
            quadTo(cx, cy + 15f * f, cx + 25f * f, cy - 5f * f)
        }
        canvas.drawPath(smile, stroke)

        // Sprinkles (Small pill capsules)
        val sprinklePositions = listOf(
            cx - 100f to cy - 140f, cx + 80f to cy - 130f,
            cx - 40f to cy - 190f, cx + 50f to cy - 190f
        )
        for ((sx, sy) in sprinklePositions) {
            canvas.drawRoundRect(RectF(sx - 16f * f, sy - 6f * f, sx + 16f * f, sy + 6f * f), 6f * f, 6f * f, stroke)
        }
    }

    fun drawPizzaSlice(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Crust (Puffy top arc)
        val crust = RectF(cx - 260f * f, cy - 340f * f, cx + 260f * f, cy - 200f * f)
        canvas.drawRoundRect(crust, 35f * f, 35f * f, stroke)

        // Pizza Triangle Wedge
        val slice = Path().apply {
            moveTo(cx - 230f * f, cy - 220f * f)
            lineTo(cx, cy + 360f * f) // tip
            lineTo(cx + 230f * f, cy - 220f * f)
            close()
        }
        canvas.drawPath(slice, stroke)

        // Dripping Cheese at the tip
        val cheeseDrip = Path().apply {
            moveTo(cx - 30f * f, cy + 300f * f)
            cubicTo(cx - 40f * f, cy + 420f * f, cx + 40f * f, cy + 420f * f, cx + 30f * f, cy + 300f * f)
        }
        canvas.drawPath(cheeseDrip, stroke)

        // Round Pepperoni Slices
        val pepPositions = listOf(
            cx - 80f to cy - 120f,
            cx + 80f to cy - 110f,
            cx to cy - 10f,
            cx - 50f to cy + 110f,
            cx + 50f to cy + 130f
        )
        for ((px, py) in pepPositions) {
            canvas.drawCircle(px * f, py * f, 38f * f, stroke)
        }

        // Cute Face between pepperonis
        drawCuteEye(canvas, cx - 40f * f, cy - 20f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 40f * f, cy - 20f * f, 14f * f, stroke, eyeFill, eyeHighlight)
    }

    fun drawDonut(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Outer Donut Ring
        canvas.drawCircle(cx, cy, 260f * f, stroke)
        // Center Hole
        canvas.drawCircle(cx, cy, 90f * f, stroke)

        // Wavy Glaze / Frosting Boundary
        val glaze = Path().apply {
            val r = 220f * f
            val steps = 12
            for (i in 0 until steps) {
                val angle1 = i * (Math.PI * 2 / steps)
                val angle2 = (i + 1) * (Math.PI * 2 / steps)
                val midAngle = (angle1 + angle2) / 2
                val currentR = if (i % 2 == 0) r else r - 40f * f
                val x2 = (cx + Math.cos(angle2) * currentR).toFloat()
                val y2 = (cy + Math.sin(angle2) * currentR).toFloat()
                val mx = (cx + Math.cos(midAngle) * (currentR + 25f * f)).toFloat()
                val my = (cy + Math.sin(midAngle) * (currentR + 25f * f)).toFloat()
                if (i == 0) {
                    val x1 = (cx + Math.cos(angle1) * currentR).toFloat()
                    val y1 = (cy + Math.sin(angle1) * currentR).toFloat()
                    moveTo(x1, y1)
                }
                quadTo(mx, my, x2, y2)
            }
            close()
        }
        canvas.drawPath(glaze, stroke)

        // Cute Face in Top Center of Donut
        drawCuteEye(canvas, cx - 35f * f, cy - 140f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, cy - 140f * f, 16f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 20f * f, cy - 110f * f)
            quadTo(cx, cy - 90f * f, cx + 20f * f, cy - 110f * f)
        }
        canvas.drawPath(smile, stroke)

        // Sprinkles on the Donut
        val sprinkles = listOf(
            cx - 160f to cy - 40f, cx + 160f to cy - 50f,
            cx - 130f to cy + 120f, cx + 130f to cy + 110f,
            cx to cy + 180f
        )
        for ((sx, sy) in sprinkles) {
            canvas.drawRoundRect(RectF(sx - 18f * f, sy - 7f * f, sx + 18f * f, sy + 7f * f), 7f * f, 7f * f, stroke)
        }
    }

    fun drawBurger(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Top Sesame Bun (Dome)
        val topBun = Path().apply {
            moveTo(cx - 260f * f, cy - 60f * f)
            cubicTo(cx - 260f * f, cy - 300f * f, cx + 260f * f, cy - 300f * f, cx + 260f * f, cy - 60f * f)
            close()
        }
        canvas.drawPath(topBun, stroke)

        // Sesame Seeds on Top Bun
        val seeds = listOf(
            cx - 140f to cy - 160f, cx - 60f to cy - 220f,
            cx + 60f to cy - 220f, cx + 140f to cy - 160f,
            cx to cy - 140f
        )
        for ((sx, sy) in seeds) {
            canvas.drawOval(RectF(sx - 12f * f, sy - 6f * f, sx + 12f * f, sy + 6f * f), stroke)
        }

        // Wavy Lettuce Leaves
        val lettuce = Path().apply {
            moveTo(cx - 280f * f, cy - 40f * f)
            var x = cx - 280f * f
            while (x < cx + 280f * f) {
                quadTo(x + 35f * f, cy - 10f * f, x + 70f * f, cy - 40f * f)
                x += 70f * f
            }
            lineTo(cx + 280f * f, cy)
            lineTo(cx - 280f * f, cy)
            close()
        }
        canvas.drawPath(lettuce, stroke)

        // Sliced Tomato
        val tomato = RectF(cx - 240f * f, cy, cx + 240f * f, cy + 60f * f)
        canvas.drawRoundRect(tomato, 14f * f, 14f * f, stroke)

        // Melted Cheese Corner Triangle
        val cheese = Path().apply {
            moveTo(cx - 230f * f, cy + 60f * f)
            lineTo(cx - 40f * f, cy + 60f * f)
            lineTo(cx - 135f * f, cy + 140f * f)
            close()
        }
        canvas.drawPath(cheese, stroke)

        // Juicy Burger Patty
        val patty = RectF(cx - 260f * f, cy + 60f * f, cx + 260f * f, cy + 160f * f)
        canvas.drawRoundRect(patty, 25f * f, 25f * f, stroke)

        // Bottom Bun
        val botBun = Path().apply {
            moveTo(cx - 250f * f, cy + 160f * f)
            lineTo(cx + 250f * f, cy + 160f * f)
            cubicTo(cx + 250f * f, cy + 280f * f, cx - 250f * f, cy + 280f * f, cx - 250f * f, cy + 160f * f)
            close()
        }
        canvas.drawPath(botBun, stroke)
    }

    fun drawWatermelon(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Crescent Green Watermelon Outer Rind
        val outerRind = Path().apply {
            moveTo(cx - 340f * f, cy - 80f * f)
            lineTo(cx + 340f * f, cy - 80f * f)
            cubicTo(cx + 340f * f, cy + 380f * f, cx - 340f * f, cy + 380f * f, cx - 340f * f, cy - 80f * f)
            close()
        }
        canvas.drawPath(outerRind, stroke)

        // Inner White Rind Strip
        val innerRind = Path().apply {
            moveTo(cx - 310f * f, cy - 60f * f)
            cubicTo(cx - 310f * f, cy + 330f * f, cx + 310f * f, cy + 330f * f, cx + 310f * f, cy - 60f * f)
        }
        canvas.drawPath(innerRind, stroke)

        // Juicy Red Interior Line
        val pulp = Path().apply {
            moveTo(cx - 280f * f, cy - 40f * f)
            cubicTo(cx - 280f * f, cy + 280f * f, cx + 280f * f, cy + 280f * f, cx + 280f * f, cy - 40f * f)
        }
        canvas.drawPath(pulp, fine)

        // Cute Face in Center
        drawCuteEye(canvas, cx - 55f * f, cy + 40f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 55f * f, cy + 40f * f, 22f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 35f * f, cy + 90f * f)
            quadTo(cx, cy + 130f * f, cx + 35f * f, cy + 90f * f)
        }
        canvas.drawPath(smile, stroke)

        // Teardrop Seeds
        val seedPositions = listOf(
            cx - 160f to cy + 60f, cx + 160f to cy + 60f,
            cx - 90f to cy + 170f, cx + 90f to cy + 170f,
            cx to cy + 210f
        )
        for ((sx, sy) in seedPositions) {
            val seed = Path().apply {
                moveTo(sx, (sy - 18f) * f)
                cubicTo((sx + 12f) * f, sy * f, (sx + 8f) * f, (sy + 18f) * f, sx, (sy + 18f) * f)
                cubicTo((sx - 8f) * f, (sy + 18f) * f, (sx - 12f) * f, sy * f, sx, (sy - 18f) * f)
                close()
            }
            canvas.drawPath(seed, eyeFill)
        }
    }

    fun drawPopcorn(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f

        // Striped Popcorn Bucket
        val bucket = Path().apply {
            moveTo(cx - 200f * f, cy - 80f * f)
            lineTo(cx - 150f * f, cy + 300f * f)
            lineTo(cx + 150f * f, cy + 300f * f)
            lineTo(cx + 200f * f, cy - 80f * f)
            close()
        }
        canvas.drawPath(bucket, stroke)

        // Vertical Stripes on Bucket
        for (i in -2..2) {
            val topX = cx + (i * 70f) * f
            val botX = cx + (i * 50f) * f
            canvas.drawLine(topX, cy - 80f * f, botX, cy + 300f * f, stroke)
        }

        // Popcorn Kernels Overflowing at top (Billowing clouds of kernels)
        val kernels = listOf(
            cx - 140f to cy - 120f, cx - 60f to cy - 160f,
            cx + 60f to cy - 160f, cx + 140f to cy - 120f,
            cx to cy - 240f, cx - 80f to cy - 210f, cx + 80f to cy - 210f
        )
        for ((kx, ky) in kernels) {
            canvas.drawCircle(kx * f, ky * f, 45f * f, stroke)
        }

        // Cute Smile on Bucket Center
        drawCuteEye(canvas, cx - 35f * f, cy + 80f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 35f * f, cy + 80f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 18f * f, cy + 120f * f)
            quadTo(cx, cy + 140f * f, cx + 18f * f, cy + 120f * f)
        }
        canvas.drawPath(smile, stroke)
    }

    fun drawCookie(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 480f * f

        // Round Cookie with a Cute Bite taken out of top right
        val cookie = Path().apply {
            val r = 240f * f
            // Start after bite
            arcTo(RectF(cx - r, cy - r, cx + r, cy + r), 340f, 320f, false)
            // Bite indent (scalloped bite marks)
            quadTo(cx + 180f * f, cy - 140f * f, cx + 140f * f, cy - 190f * f)
            close()
        }
        canvas.drawPath(cookie, stroke)

        // Cute Face in Center of Cookie
        drawCuteEye(canvas, cx - 60f * f, cy - 20f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 60f * f, cy - 20f * f, 24f * f, stroke, eyeFill, eyeHighlight)
        // Rosy Cheeks
        canvas.drawCircle(cx - 100f * f, cy + 30f * f, 18f * f, fine)
        canvas.drawCircle(cx + 100f * f, cy + 30f * f, 18f * f, fine)
        val smile = Path().apply {
            moveTo(cx - 35f * f, cy + 35f * f)
            quadTo(cx, cy + 75f * f, cx + 35f * f, cy + 35f * f)
        }
        canvas.drawPath(smile, stroke)

        // Chocolate Chips scattered
        val chips = listOf(
            cx - 140f to cy - 100f,
            cx to cy - 140f,
            cx - 120f to cy + 120f,
            cx + 120f to cy + 110f,
            cx to cy + 160f
        )
        for ((chx, chy) in chips) {
            val chip = Path().apply {
                moveTo(chx * f, (chy - 20f) * f)
                lineTo((chx + 22f) * f, (chy + 10f) * f)
                lineTo((chx - 22f) * f, (chy + 10f) * f)
                close()
            }
            canvas.drawPath(chip, stroke)
        }

        // Falling Crumbs from the bite
        canvas.drawCircle(cx + 260f * f, cy - 220f * f, 10f * f, stroke)
        canvas.drawCircle(cx + 310f * f, cy - 180f * f, 14f * f, stroke)
    }

    fun drawPancakes(canvas: Canvas, s: Int, stroke: Paint, fine: Paint, eyeFill: Paint, eyeHighlight: Paint) {
        val f = s / 1024f
        val cx = 512f * f
        val cy = 560f * f

        // Platter Plate at bottom
        val plate = RectF(cx - 340f * f, cy + 140f * f, cx + 340f * f, cy + 240f * f)
        canvas.drawOval(plate, stroke)

        // 3 Stacked Fluffy Pancakes
        val pancakeHeights = listOf(cy + 100f, cy + 10f, cy - 80f)
        for (py in pancakeHeights) {
            val cake = RectF(cx - 260f * f, (py - 50f) * f, cx + 260f * f, (py + 50f) * f)
            canvas.drawRoundRect(cake, 45f * f, 45f * f, stroke)
        }

        // Melting Cube of Butter on Top
        val butter = Path().apply {
            moveTo(cx - 50f * f, cy - 140f * f)
            lineTo(cx + 20f * f, cy - 160f * f)
            lineTo(cx + 70f * f, cy - 140f * f)
            lineTo(cx, cy - 120f * f)
            close()
        }
        canvas.drawPath(butter, stroke)
        canvas.drawRect(RectF(cx - 50f * f, cy - 140f * f, cx, cy - 100f * f), stroke)
        canvas.drawRect(RectF(cx, cy - 140f * f, cx + 70f * f, cy - 100f * f), stroke)

        // Dripping Maple Syrup cascading down the side
        val syrup = Path().apply {
            moveTo(cx + 40f * f, cy - 110f * f)
            cubicTo(cx + 120f * f, cy - 60f * f, cx + 160f * f, cy + 40f * f, cx + 180f * f, cy + 80f * f)
            cubicTo(cx + 190f * f, cy + 130f * f, cx + 160f * f, cy + 130f * f, cx + 150f * f, cy + 80f * f)
        }
        canvas.drawPath(syrup, stroke)

        // Cute Face on Top Pancake
        drawCuteEye(canvas, cx - 45f * f, cy - 65f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        drawCuteEye(canvas, cx + 45f * f, cy - 65f * f, 14f * f, stroke, eyeFill, eyeHighlight)
        val smile = Path().apply {
            moveTo(cx - 20f * f, cy - 40f * f)
            quadTo(cx, cy - 25f * f, cx + 20f * f, cy - 40f * f)
        }
        canvas.drawPath(smile, stroke)
    }
}
