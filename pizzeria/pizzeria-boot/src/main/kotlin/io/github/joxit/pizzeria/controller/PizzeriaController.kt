package io.github.joxit.pizzeria.controller

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.request.CreatePizzaRequest
import io.github.joxit.pizzeria.request.PatchPizzaRequest
import io.github.joxit.pizzeria.request.UpdatePizzaRequest
import io.github.joxit.pizzeria.service.PizzeriaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping(path = [ "/pizzas", "/pizzas/" ])
class PizzeriaController(
  @param:Autowired private val pizzeriaService: PizzeriaService
) {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(PizzeriaController::class.java)
  }

  @RequestMapping(method = [RequestMethod.GET])
  fun getPizzeria(@RequestParam(value = "type", required = false) type: String?): List<PizzaDTO> {
    LOGGER.info("getPizzeria({})", type)
    return pizzeriaService.getAll(type)
  }
}