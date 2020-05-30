package vbakaev.app.interfaces.http.models.response

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@ApiResponse
final case class StatusResponse(
    @Schema(required = true, `type` = "string", description = "datetime string in the format specified by RFC 3339")
    startedAt: String,
    @Schema(required = true, `type` = "string", example = "OK", allowableValues = Array("OK"))
    status: String = "OK"
)
