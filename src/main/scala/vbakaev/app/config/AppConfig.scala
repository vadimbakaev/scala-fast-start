package vbakaev.app.config

final case class ServerConfig(
    interface: String,
    port: Int
)

final case class AppConfig(
    http: ServerConfig
)
