package vbakaev.app.config

final case class ServerConfig(
    appRoot: String,
    interface: String,
    port: Int
)

final case class AppConfig(
    http: ServerConfig
)
