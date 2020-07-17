package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {
	List<Plat> findByPrixEnCentimesEuros(int prix);

	@Query("SELECT avg(prixEnCentimesEuros) FROM Plat WHERE prixEnCentimesEuros > ?1")
	double avgPrix(int prixMin);

	@Query("SELECT i FROM Plat p join p.ingredients i WHERE p.nom=?1")
	List<Ingredient> findByNomWithIngredients(String nomPlat);

	@Transactional
	@Modifying
	@Query("UPDATE Plat SET nom = ?2 WHERE nom= ?1")
	void changerNom(String ancienNom, String nouveauNom);
}
