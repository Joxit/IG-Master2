package io.github.joxit.pizzeria.controller

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.request.CreatePizzaRequest
import io.github.joxit.pizzeria.request.PatchPizzaRequest
import io.github.joxit.pizzeria.request.UpdatePizzaRequest
import io.github.joxit.pizzeria.service.PizzaService
import io.github.joxit.pizzeria.service.PizzeriaService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/pizzas/")
class PizzaController(
  @param:Autowired private val pizzaService: PizzaService
) {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(PizzaController::class.java)
  }

  @GetMapping("/{name}")
  fun getPizza(@PathVariable name: String): PizzaDTO {
    LOGGER.info("getPizzeria({})", name)
    return pizzaService.getPizza(name)
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createPizza(@RequestBody pizza: CreatePizzaRequest): PizzaDTO {
    LOGGER.info("createPizza({})", pizza)
    return pizzaService.createPizza(pizza)
  }

  @PutMapping("/{name}")
  fun updatePizza(@PathVariable name: String, @RequestBody pizza: UpdatePizzaRequest): PizzaDTO {
    LOGGER.info("updatePizza({}, {})", name, pizza)
    return pizzaService.updatePizza(name, pizza)
  }

  @PatchMapping("/{name}")
  fun patchPizza(@PathVariable name: String, @RequestBody pizza: PatchPizzaRequest): PizzaDTO {
    LOGGER.info("patchPizza({}, {})", name, pizza)
    return pizzaService.patchPizza(name, pizza)
  }

  @DeleteMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deletePizza(@PathVariable name: String) {
    LOGGER.info("deletePizza({})", name)
    pizzaService.deletePizza(name)
  }
}