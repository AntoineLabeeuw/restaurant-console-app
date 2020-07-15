package dev.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

@ContextConfiguration(classes = { PlatDaoMemoire.class, PlatServiceVersion2.class })
@ExtendWith(SpringExtension.class)
@ActiveProfiles({ "PlatMem", "Service2" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlatServiceVersion2IntegrationTest {
	@Autowired
	PlatServiceVersion2 platV2;

	// les deux premiers tests peuvent etre mis ensemble, mais essai de
	// @DirtiesContext
	@Test
	public void ajoutPlatNomTest() {
		// ajout d'un plat valide
		platV2.ajouterPlat("Patates", 1500);
		List<Plat> plats = platV2.listerPlats();
		assertThat(plats).extracting(Plat::getNom).containsExactly("Patates");
	}

	@Test
	public void ajoutPlatPrixTest() {
		// ajout d'un plat valide
		platV2.ajouterPlat("Patates", 1500);
		List<Plat> plats = platV2.listerPlats();
		assertThat(plats).extracting(Plat::getPrixEnCentimesEuros).contains(1500);
	}

	@Test
	public void ajoutPlatInvalideNomTest() {
		// Invalide si nom < 5 caractères
		Throwable thrown = catchThrowable(() -> {
			platV2.ajouterPlat("no", 2000);
		});
		assertThat(thrown).isInstanceOf(PlatException.class)
				.hasMessageContaining("un plat doit avoir un nom de plus de 5 caractères");

	}

	@Test
	public void ajoutPlatInvalidePrixTest() {
		// Invalide si prix < 10 euros
		Throwable thrown = catchThrowable(() -> {
			platV2.ajouterPlat("Patates", 10);
		});
		assertThat(thrown).isInstanceOf(PlatException.class)
				.hasMessageContaining("le prix d'un plat doit être supérieur à 10 €");

	}
}
