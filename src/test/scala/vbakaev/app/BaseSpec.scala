package vbakaev.app

import org.scalatest.ParallelTestExecution
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AsyncWordSpecLike

trait BaseSpec extends AsyncWordSpecLike with Matchers with ParallelTestExecution
