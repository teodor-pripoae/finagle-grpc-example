package com.github.teodorpripoae.echo

import com.twitter.finagle.tracing.NullTracer

object Tracing {
  val tracer = newTracer

  def newTracer = {
    NullTracer
  }
}
