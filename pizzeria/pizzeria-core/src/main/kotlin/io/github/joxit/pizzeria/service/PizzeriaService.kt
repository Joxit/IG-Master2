package io.github.joxit.pizzeria.service

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.exception.HandledException
import io.github.joxit.pizzeria.mapper.PizzaMapper
import io.github.joxit.pizzeria.persistence.PizzaDAO
import io.github.joxit.pizzeria.persistence.PizzaSDJDAO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
// For lazy loading
@Transactional(readOnly = true) // For rollback and exceptions
class PizzeriaService(
  @Autowired private val pizzaDAO: PizzaDAO,
  @Autowired private val pizzaSDJDAO: PizzaSDJDAO
) {
  companion object {
    private val LOGGER: Logger = LoggerFactory.getLogger(PizzeriaService::class.java)
  }

  fun getAll(type: String?): List<PizzaDTO> = when (type?.lowercase()) {
    "eager" -> {
      LOGGER.info("Run getAllWithIng")
      pizzaDAO.getEager().map(PizzaMapper::modelToDTO)
    }

    "custom" -> {
      LOGGER.info("Run getAllCustom")
      pizzaDAO.getCustom().map(PizzaMapper::modelToDTO)
    }

    "errornothandled" -> {
      LOGGER.info("throw RuntimeException")
      throw RuntimeException("Error Not Handled")
    }

    "errorhandled" -> {
      LOGGER.info("throw HandledException")
      throw HandledException("Error Handled")
    }

    "spring-data-jpa" -> {
      LOGGER.info("Run getAllSDJ")
      pizzaSDJDAO.findAll().map(PizzaMapper::modelToDTO)
    }

    "empty" -> {
      LOGGER.info("Run empty")
      listOf()
    }

    else -> {
      LOGGER.info("Run getAll")
      pizzaDAO.getAll().map(PizzaMapper::modelToDTO)
    }
  }
}
