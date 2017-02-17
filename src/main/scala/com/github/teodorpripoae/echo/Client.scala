package com.github.teodorpripoae.echo

import com.github.teodorpripoae.proto.echo.{EchoRequest, EchoServer}
import com.twitter.finagle.buoyant.H2
import com.twitter.util.Await
import com.typesafe.config.Config

import com.twitter.conversions.time._

object Client {
  def start(config: Config) = {
    val host = config.getString("server-host")
    val port = config.getInt("server-port")
    val dest = s"/$$/inet/${host}/${port}"
    val h2client = H2.client.withLabel("echo-client").withTracer(Tracing.tracer).newService(dest)
    val client = new EchoServer.Client(h2client)

    val res = Await.result(client.echo(EchoRequest(Some("John"))), 2.seconds)

    println(s"Result: ${res.message}")
  }
}
