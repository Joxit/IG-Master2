package io.github.joxit.pizzeria.vertx

import io.github.joxit.pizzeria.dto.ErrorDTO
import io.github.joxit.pizzeria.exception.HandledException
import io.github.joxit.pizzeria.service.PizzeriaService
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpHeaders
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.ext.web.handler.TimeoutHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class HttpServerVerticle : AbstractVerticle() {
  @Value("\${server.port:8080}")
  private val port = 0

  companion object {
    private val LOGGER = LoggerFactory.getLogger(HttpServerVerticle::class.java)
    private const val TIMEOUT: Long = 30000
  }

  @Autowired
  private lateinit var pizzeriaService: PizzeriaService

  override fun start(startFuture: Promise<Void>) {
    val router = Router.router(vertx)
    val bodyHandler = BodyHandler.create().setBodyLimit((1024 * 1024).toLong())

    router.route().handler(corsHandler())
    router.route().handler(TimeoutHandler.create(TIMEOUT))
    router.route().handler(bodyHandler)

    router["/pizzas"]
      .produces("application/json")
      .handler(this::requestHandler)
      .failureHandler { ctx: RoutingContext ->
        val err = ctx.failure()
        LOGGER.error(err.message)
        if (err is HandledException) {
          ctx.response().setStatusCode(400).send(ErrorDTO(400, err.message.orEmpty()).toBuffer())
        } else {
          ctx.next()
        }
      }

    val options = HttpServerOptions()
      .setHost("0.0.0.0")
      .setPort(port)

    vertx.createHttpServer(options)
      .requestHandler(router)
      .listen()
      .onComplete {
        if (it.succeeded()) {
          startFuture.complete()
        } else {
          startFuture.fail(it.cause())
          LOGGER.error("Vertex node failed to start")
        }
      }
  }

  fun ErrorDTO.toBuffer(): Buffer = JsonObject.mapFrom(this).toBuffer()

  private fun requestHandler(ctx: RoutingContext) {
    val request = ctx.request()
    val response = ctx.response()
    response.putHeader("Content-Type", "application/json")
      .setStatusCode(200)
      .end(Json.encode(pizzeriaService.getAll(request.getParam("type"))))
  }

  private fun corsHandler() = CorsHandler.create()
    .addOriginWithRegex(".*")
    .allowedMethod(HttpMethod.GET)
    .allowedMethod(HttpMethod.POST)
    .allowedMethod(HttpMethod.PUT)
    .allowedMethod(HttpMethod.PATCH)
    .allowedMethod(HttpMethod.DELETE)
    .allowedMethod(HttpMethod.OPTIONS)
    .allowedHeader(HttpHeaders.AUTHORIZATION.toString())
    .allowedHeader(HttpHeaders.CONTENT_TYPE.toString())
}