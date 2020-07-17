package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.JpaTestConfig;
import dev.entite.Ingredient;
import dev.entite.Plat;

/**
 * Classe de test de PlatRepository
 * 
 * @author antoinelabeeuw
 *
 */
@SpringJUnitConfig(classes = { JpaTestConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")
// permet de faire des rollback dans la classe de test
@Transactional
public class PlatRepositoryIntegrationTest {

	@Autowired
	PlatRepository pr;

	@Test
	public void testFindAll() {

		List<Plat> plats = pr.findAll();
		assertThat(plats).hasSize(6);
	}

	@Test
	public void testFindAllSortAsc() {
		List<Plat> plats = pr.findAll(Sort.by(Sort.Direction.ASC, "prixEnCentimesEuros"));
		assertThat(plats).isSortedAccordingTo(Comparator.comparing(Plat::getPrixEnCentimesEuros));
	}

	@Test
	public void testFindAllSortDesc() {
		List<Plat> plats = pr.findAll(Sort.by(Sort.Direction.DESC, "prixEnCentimesEuros"));
		assertThat(plats).isSortedAccordingTo(Comparator.comparing(Plat::getPrixEnCentimesEuros).reversed());
	}

	@Test
	public void testFindAllPageable() {
		List<Plat> plats = pr.findAll(PageRequest.of(0, 2, Sort.by("nom").ascending())).toList();
		assertThat(plats).extracting(Plat::getNom).containsExactly("Blanquette de veau", "Couscous");
	}

	@Test
	public void testFindById() {
		Optional<Plat> pl = pr.findById(5);
		assertThat(pl.get().getNom()).isEqualTo("Côte de boeuf");
		assertThat(pl.get().getPrixEnCentimesEuros()).isEqualTo(1100);
	}

	@Test
	@Transactional
	public void testGetOne() {
		Plat pl = pr.getOne(4);
		assertThat(pl.getPrixEnCentimesEuros()).isEqualTo(1500);
		assertThat(pl.getNom()).isEqualTo("Blanquette de veau");
	}

	@Test
	public void testCount() {
		long nb = pr.count();
		assertThat(nb).isEqualTo(6L);
	}

	@Test
	public void testFindByPrix() {
		List<Plat> plats = pr.findByPrixEnCentimesEuros(1600);
		assertThat(plats).hasSize(1);
		assertThat(plats.get(0).getNom()).isEqualTo("Couscous");
	}

	@Test
	public void testAvgPrix() {
		double avg = pr.avgPrix(0);
		assertThat(avg).isEqualTo(1550);
	}

	@Test
	public void testFindByNomWithIngredients() {
		List<Ingredient> ingredients = pr.findByNomWithIngredients("Moules-frites");
		assertThat(ingredients).hasSize(6);
	}

	@Test
	public void testSave() {
		Plat pl = new Plat("testPlat", 3500);
		pr.save(pl);
		assertThat(pr.count()).isEqualTo(7);
	}

	@Test
	public void testChangerNom() {
		pr.changerNom("Magret de canard", "MagretCanard");
		assertThat(pr.findById(1).get().getNom()).isEqualTo("MagretCanard");
	}

}
