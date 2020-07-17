package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.dto.IngredientDto;
import dev.entite.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	@Query("SELECT i FROM Ingredient i WHERE i.nom = ?1")
	Optional<IngredientDto> rechercheParNom(String nom);
}
