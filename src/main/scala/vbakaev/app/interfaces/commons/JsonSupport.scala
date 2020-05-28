package vbakaev.app.interfaces.commons

import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.AutoDerivation

trait JsonSupport extends ErrorAccumulatingCirceSupport with AutoDerivation
