package vbakaev.app.interfaces

import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.AutoDerivation

trait JsonSupport extends ErrorAccumulatingCirceSupport with AutoDerivation
