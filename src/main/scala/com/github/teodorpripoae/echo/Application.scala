package com.github.teodorpripoae.echo

import com.typesafe.config.ConfigFactory

object Application {
  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new RuntimeException("Please provide 1 argument 'client' or 'server')")
    }

    val config = ConfigFactory.load()

    args(0) match {
      case "client" => {
        Client.start(config)
      }
      case "server" => {
        Server.start(config)
      }
    }
  }
}
