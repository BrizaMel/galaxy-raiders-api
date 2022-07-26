@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics
import kotlin.math.hypot

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + this.x, p.y + this.y)
  }

  operator fun plus(v: Vector2D): Point2D {
    return Point2D(v.dx + this.x, v.dy + this.y)
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    return Vector2D(this.x, this.y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D(Math.abs(this.x - p.x), Math.abs(this.y - p.y))
  }

  fun impactDirection(p: Point2D): Vector2D {
    val impactVector: Vector2D = this.impactVector(p)
    return impactVector.unit
  }

  fun contactVector(p: Point2D): Vector2D {
    val impactVector: Vector2D = this.impactVector(p)
    return impactVector.normal
  }

  fun contactDirection(p: Point2D): Vector2D {
    val impactDirection: Vector2D = this.impactDirection(p)
    return impactDirection.normal
  }

  fun distance(p: Point2D): Double {
    return hypot(x - p.x, y - p.y)
  }
}
