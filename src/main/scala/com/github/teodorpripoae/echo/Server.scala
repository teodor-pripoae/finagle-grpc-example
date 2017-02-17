package com.github.teodorpripoae.echo

import com.github.teodorpripoae.proto.echo.{EchoRequest, EchoResponse, EchoServer}
import com.twitter.finagle.buoyant.H2
import com.twitter.util.{Await, Future}
import com.typesafe.config.Config
import io.buoyant.grpc.runtime.{GrpcStatus, ServerDispatcher}

class EchoServerImpl extends EchoServer {
  override def echo(request: EchoRequest): Future[EchoResponse] = {
    request.name match {
      case Some(name) => {
        val message = s"Hello ${request.name.getOrElse("John")}"
        Future.value(EchoResponse(Some(message)))
      }
      case None => {
        Future.exception(GrpcStatus.InvalidArgument("name is required"))
      }
    }
  }
}

object Server {
  def start(config: Config) = {
    val host = config.getString("server-host")
    val port = config.getInt("server-port")
    val server = new ServerDispatcher(Seq(new EchoServer.Server(new EchoServerImpl)))
    val h2srv = H2.serve(s"${host}:${port}", server)
    Await.ready(h2srv)
  }
}
