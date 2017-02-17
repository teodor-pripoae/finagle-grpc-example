package com.github.teodorpripoae.proto.echo

/*
 * Generated from echo.proto by io.buoyant.grpc.gen.
 *
 * You don't want to edit this.
 */


case class EchoRequest(
  name: Option[String]
)

object EchoRequest {

  object codec extends io.buoyant.grpc.runtime.Codec[EchoRequest] {
    override val decode: com.google.protobuf.CodedInputStream => EchoRequest = { pbis =>
      var nameArg: Option[String] = None
      while (!pbis.isAtEnd) {
        val tag = pbis.readTag()
        val typ = com.google.protobuf.WireFormat.getTagWireType(tag)
        val num = com.google.protobuf.WireFormat.getTagFieldNumber(tag)
        num match {
          case 1 => // name: Simple(TYPE_STRING)
            typ match {
              case com.google.protobuf.WireFormat.WIRETYPE_LENGTH_DELIMITED =>
                nameArg = Option(pbis.readString())
              case typ =>
                throw new IllegalArgumentException(s"name expected com.google.protobuf.WireFormat.WIRETYPE_LENGTH_DELIMITED got ${typ}")
            }

          case _ => // ignore unknown fields
        }
      }
      EchoRequest(nameArg)
    }

    override def encode(msg: EchoRequest, pbos: com.google.protobuf.CodedOutputStream): scala.Unit = {
      msg.name match {
        case None =>
        case Some(value) =>
          pbos.writeString(1, value)
      }
    }

    override def sizeOf(msg: EchoRequest): scala.Int = {
      var size = 0
      msg.name match {
        case None =>
        case Some(value) =>
          val sz = com.google.protobuf.CodedOutputStream.computeTagSize(1) + com.google.protobuf.CodedOutputStream.computeStringSizeNoTag(value)
          size += sz
      }
      size
    }

  }

}

case class EchoResponse(
  message: Option[String]
)

object EchoResponse {

  object codec extends io.buoyant.grpc.runtime.Codec[EchoResponse] {
    override val decode: com.google.protobuf.CodedInputStream => EchoResponse = { pbis =>
      var messageArg: Option[String] = None
      while (!pbis.isAtEnd) {
        val tag = pbis.readTag()
        val typ = com.google.protobuf.WireFormat.getTagWireType(tag)
        val num = com.google.protobuf.WireFormat.getTagFieldNumber(tag)
        num match {
          case 1 => // message: Simple(TYPE_STRING)
            typ match {
              case com.google.protobuf.WireFormat.WIRETYPE_LENGTH_DELIMITED =>
                messageArg = Option(pbis.readString())
              case typ =>
                throw new IllegalArgumentException(s"message expected com.google.protobuf.WireFormat.WIRETYPE_LENGTH_DELIMITED got ${typ}")
            }

          case _ => // ignore unknown fields
        }
      }
      EchoResponse(messageArg)
    }

    override def encode(msg: EchoResponse, pbos: com.google.protobuf.CodedOutputStream): scala.Unit = {
      msg.message match {
        case None =>
        case Some(value) =>
          pbos.writeString(1, value)
      }
    }

    override def sizeOf(msg: EchoResponse): scala.Int = {
      var size = 0
      msg.message match {
        case None =>
        case Some(value) =>
          val sz = com.google.protobuf.CodedOutputStream.computeTagSize(1) + com.google.protobuf.CodedOutputStream.computeStringSizeNoTag(value)
          size += sz
      }
      size
    }

  }

}

trait EchoServer {
  def echo(req: com.github.teodorpripoae.proto.echo.EchoRequest): com.twitter.util.Future[com.github.teodorpripoae.proto.echo.EchoResponse]
}

object EchoServer {
  class Client(
    client: com.twitter.finagle.Service[com.twitter.finagle.buoyant.h2.Request, com.twitter.finagle.buoyant.h2.Response]
  ) extends EchoServer {
    private[this] val __echoRpc =
      io.buoyant.grpc.runtime.ClientDispatcher.Rpc.UnaryToUnary(
        client, "/echo.EchoServer/Echo",
        com.github.teodorpripoae.proto.echo.EchoRequest.codec,
        com.github.teodorpripoae.proto.echo.EchoResponse.codec
      )
    override def echo(msg: com.github.teodorpripoae.proto.echo.EchoRequest): com.twitter.util.Future[com.github.teodorpripoae.proto.echo.EchoResponse] =
      __echoRpc(msg)

  }

  object Server {
    val name = "echo.EchoServer"

    def apply(iface: com.github.teodorpripoae.proto.echo.EchoServer): Server =
      new Server(iface)
  }

  class Server(iface: com.github.teodorpripoae.proto.echo.EchoServer)
      extends io.buoyant.grpc.runtime.ServerDispatcher.Service {
    override val name = Server.name
    override val rpcs = Seq[io.buoyant.grpc.runtime.ServerDispatcher.Rpc](
      new io.buoyant.grpc.runtime.ServerDispatcher.Rpc.UnaryToUnary(
        "Echo",
        iface.echo _,
        com.github.teodorpripoae.proto.echo.EchoRequest.codec,
        com.github.teodorpripoae.proto.echo.EchoResponse.codec
      )
    )
  }
}

