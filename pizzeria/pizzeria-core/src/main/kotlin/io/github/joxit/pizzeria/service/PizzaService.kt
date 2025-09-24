package io.github.joxit.pizzeria.service

import io.github.joxit.pizzeria.dto.PizzaDTO
import io.github.joxit.pizzeria.exception.IngredientNotFoundExceptions
import io.github.joxit.pizzeria.exception.NotAllowedException
import io.github.joxit.pizzeria.exception.PizzaExistsExceptions
import io.github.joxit.pizzeria.exception.PizzaNotFoundExceptions
import io.github.joxit.pizzeria.mapper.PizzaMapper
import io.github.joxit.pizzeria.model.Ingredient
import io.github.joxit.pizzeria.model.Pizza
import io.github.joxit.pizzeria.persistence.IngredientDAO
import io.github.joxit.pizzeria.persistence.PizzaDAO
import io.github.joxit.pizzeria.request.CreatePizzaRequest
import io.github.joxit.pizzeria.request.PatchPizzaRequest
import io.github.joxit.pizzeria.request.UpdatePizzaRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
// For lazy loading and persist data
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
class PizzaService(
  @param:Autowired private val pizzaDAO: PizzaDAO,
  @param:Autowired private val ingredientDAO: IngredientDAO,
) {
  @Transactional(readOnly = true)
  fun getPizza(name: String): PizzaDTO {
    // TODO Améliorer le val/if en dessous grâce à Kotlin
    val pizza = pizzaDAO.getPizza(name)
    if (pizza == null) {
      throw PizzaNotFoundExceptions(name)
    }
    // TODO Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(pizza)
  }

  fun createPizza(pizzaRequest: CreatePizzaRequest): PizzaDTO {
    if (pizzaDAO.getPizza(pizzaRequest.name) != null) {
      throw PizzaExistsExceptions(pizzaRequest.name)
    }

    // TODO Factoriser cette boucle val/for grâce à Kotlin, il y a deux éléments qui peuvent être améliorés
    // 1- La boucle en elle même peut être améliorée
    // 2- La vérification après le findById peut être amélioré, cela renvoie un objet Option
    // 2.1 Vous pouvez l'améliorer en utilisant les méthodes dans Java https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html
    //     Méthodes: orElse, orElseGet, orElseThrow
    // 2.2 Vous pouvez l'améliorer en utilisant les extensions Kotlin https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm.optionals/java.util.-optional/
    //     Méthodes: getOrNull
    val ingredients = mutableListOf<Ingredient>()
    for (ingredientName in pizzaRequest.ingredients) {
      val ingredient = ingredientDAO.findById(ingredientName)
      if (ingredient.isEmpty) {
        throw IngredientNotFoundExceptions(ingredientName)
      }
      ingredients.add(ingredient.get())
    }

    val pizza = Pizza(pizzaRequest.name, pizzaRequest.price, ingredients)
    pizzaDAO.createPizza(pizza)
    // TODO Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(pizza)
  }

  fun updatePizza(name: String, pizzaRequest: UpdatePizzaRequest): PizzaDTO {
    val pizza = pizzaDAO.getPizza(name) ?: throw PizzaNotFoundExceptions(name)
    if (pizza.protected) throw NotAllowedException("The pizza `${pizza.name}` is protected")

    // TODO Factoriser cette boucle val/for comme createPizza()
    val ingredients = mutableListOf<Ingredient>()
    for (ingredientName in pizzaRequest.ingredients) {
      val ingredient = ingredientDAO.findById(ingredientName)
      if (ingredient.isEmpty) {
        throw IngredientNotFoundExceptions(ingredientName)
      }
      ingredients.add(ingredient.get())
    }
    val updatedPizza = Pizza(name, pizzaRequest.price)
    updatedPizza.ingredients = ingredients
    pizzaDAO.updatePizza(updatedPizza)
    // TODO Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(updatedPizza)
  }

  fun patchPizza(name: String, pizzaRequest: PatchPizzaRequest): PizzaDTO {
    val pizza = pizzaDAO.getPizza(name) ?: throw PizzaNotFoundExceptions(name)

    // TODO Améliorer ce var/if en utilisant Kotlin
    var ingredients = pizza.ingredients
    if (pizzaRequest.ingredients != null) {
      // TODO Factoriser cette boucle comme createPizza()
      ingredients = mutableListOf()
      for (ingredientName in pizzaRequest.ingredients) {
        val ingredient = ingredientDAO.findById(ingredientName)
        if (ingredient.isEmpty) {
          throw IngredientNotFoundExceptions(ingredientName)
        }
        ingredients.add(ingredient.get())
      }
    }

    // TODO Améliorer ce var/if en utilisant Kotlin
    var price = pizza.price
    if (pizzaRequest.price != null) {
      price = pizzaRequest.price
    }

    val newPizza = Pizza(name = name, price = price, ingredients = ingredients)
    pizzaDAO.updatePizza(newPizza)
    // TODO Trouver une alternative à PizzaMapper.modelToDTO en Kotlin
    return PizzaMapper.modelToDTO(newPizza)
  }

  /**
   * L'action de suppression de donnée est généralement idempotent dans une API REST,
   * cela veut dire que si l'élément n'existe pas, aucune erreur n'est renvoyée car l'état finale est identique.
   */
  fun deletePizza(name: String) {
    // TODO Améliorer ce val/if grâce à Kotlin
    val pizza = pizzaDAO.getPizza(name)
    if (pizza == null) {
      return
    }
    if (pizza.protected) throw NotAllowedException("The pizza `${pizza.name}` is protected")
    pizzaDAO.deletePizza(pizza)
  }
}
