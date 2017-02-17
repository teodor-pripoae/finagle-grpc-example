package com.github.teodorpripoae.echo

import com.twitter.finagle.stats.NullStatsReceiver
import com.twitter.finagle.tracing.{NullTracer, Tracer}
import com.twitter.util.Try
import com.typesafe.config.ConfigFactory
import com.twitter.finagle.zipkin.thrift.ZipkinTracer

object Tracing {
  val config = ConfigFactory.load()
  val tracer = newTracer

  def newTracer: Tracer = {
    if (!config.hasPath("tracing") || !config.getBoolean("tracing.enabled")) {
      return NullTracer
    }

    if (config.hasPath("tracing.scribe-host")) {
      val scribeHost    = config.getString("tracing.scribe-host")
      val scribePort    = Try(config.getInt("tracing.scribe-port")).getOrElse(9410)
      val sampleRate    = Try(config.getDouble("tracing.sample-rate")).getOrElse(1.0)
      val statsReceiver = NullStatsReceiver

      ZipkinTracer.mk(host = scribeHost,
        port = scribePort,
        statsReceiver = statsReceiver,
        sampleRate = sampleRate.toFloat)
    } else {
      NullTracer
    }
  }
}
