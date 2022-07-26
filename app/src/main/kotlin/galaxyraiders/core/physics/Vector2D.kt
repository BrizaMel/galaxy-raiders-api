@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.hypot
import kotlin.math.atan2

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = hypot(dx, dy)

  val radiant: Double
    get() = (-atan2(-dy, this * Vector2D(1.0, 0.0))) % (2 * Math.PI)

  val degree: Double
    get() = radiant * (180 / Math.PI)

  val unit: Vector2D
    get() = this / magnitude

  val normal: Vector2D
    get() = Vector2D(dy, -dx).unit

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx = dx * scalar, dy = dy * scalar)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(dx = dx / scalar, dy = dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return dx * v.dx + dy * v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx = dx + v.dx, dy = dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(x = dx + p.x, y = dy + p.y)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(dx = -dx, dy = -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx = dx - v.dx, dy = dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return this * target.unit
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return (this.scalarProject(target) * target.unit)
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(dx = this * v.dx, dy = this * v.dy)
}
