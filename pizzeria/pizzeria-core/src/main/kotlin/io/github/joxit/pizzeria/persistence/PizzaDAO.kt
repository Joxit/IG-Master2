package io.github.joxit.pizzeria.persistence

import io.github.joxit.pizzeria.model.Ingredient
import io.github.joxit.pizzeria.model.Pizza
import io.github.joxit.pizzeria.model.PizzaCustom
import io.github.joxit.pizzeria.model.PizzaEager
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaQuery
import java.sql.ResultSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @author Jones Magloire @Joxit
 * @since 2017-11-01
 */
@Repository
@Transactional // For rollback and exceptions
class PizzaDAO(
  @PersistenceContext private val em: EntityManager, // From JPA
  @Autowired private val jdbcTemplate: JdbcTemplate
) {

  fun getAll(): List<Pizza> {
    val cq: CriteriaQuery<Pizza> = em.criteriaBuilder.createQuery(Pizza::class.java)
    cq.select(cq.from(Pizza::class.java))
    return em.createQuery(cq).resultList
  }

  fun getEager(): List<PizzaEager> {
    val cq: CriteriaQuery<PizzaEager> = em.criteriaBuilder.createQuery(PizzaEager::class.java)
    cq.select(cq.from(PizzaEager::class.java))
    return em.createQuery(cq).resultList
  }

  fun getCustom(): List<PizzaCustom> {
    return jdbcTemplate.query(
      """
      SELECT p.name, p.price, i.name, i.price
      FROM
        Pizza p
        INNER JOIN MtM_Pizza_Ingredient m ON p.name = m.pizza
        INNER JOIN Ingredient i ON m.ingredient = i.name
        """
    ) { rs: ResultSet, _: Int ->
      val pizza = PizzaCustom(
        name = rs.getString("p.name"), price = rs.getFloat("p.price"), ingredients = mutableListOf()
      )
      val ingredient = Ingredient(
        name = rs.getString("i.name"), price = rs.getFloat("i.price")
      )
      Pair(pizza, ingredient)
    }.groupBy({ it.first }, { it.second }).map {
      it.key.ingredients.addAll(it.value)
      it.key
    }
  }

  fun getPizza(name: String): Pizza? {
    return em.find(Pizza::class.java, name)
  }

  fun createPizza(pizza: Pizza) {
    em.persist(pizza)
  }

  fun updatePizza(pizza: Pizza) {
    em.merge(pizza)
  }

  fun deletePizza(pizza: Pizza) {
    em.remove(pizza)
  }
}