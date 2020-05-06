package vbakaev.app.services

import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.AutoDerivation

trait JsonSupport extends ErrorAccumulatingCirceSupport with AutoDerivation
