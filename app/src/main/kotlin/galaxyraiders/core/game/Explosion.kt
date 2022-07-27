package galaxyraiders.core.game

import galaxyraiders.core.physics.Point2D
import galaxyraiders.core.physics.Vector2D
import java.util.Timer

class Explosion(
  initialPosition: Point2D,
) :
  SpaceObject("Explosion", '*', initialPosition, Vector2D(0.0, 0.0), 0.3, 0.0) {
  var isTriggered: Boolean = true
    private set

  var timer: Timer = Timer("schedule", true);

  init {
    timer.schedule(3000) {
        isTriggered = false
    }
  }
}
