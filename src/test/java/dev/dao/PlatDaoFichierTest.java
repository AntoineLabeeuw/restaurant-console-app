package dev.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;


@SpringJUnitConfig(classes =  PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	@Autowired
	PlatDaoFichier platFic;

	@Test
	public void ajoutPlatNomTest() {
		 platFic.ajouterPlat("test", 200);
		List<Plat> plats = platFic.listerPlats();
		assertThat(plats).extracting(Plat::getNom).containsExactly("test");
	}
	
	@Test
	public void ajoutPlatPrixTest() {
		 platFic.ajouterPlat("test", 200);
		List<Plat> plats = platFic.listerPlats();
		assertThat(plats).extracting(Plat::getPrixEnCentimesEuros).contains(200);
	}
}
