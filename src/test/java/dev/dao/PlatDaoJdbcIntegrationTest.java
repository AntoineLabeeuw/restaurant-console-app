package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;

/**
 * Classe de test de PlatDaoJdbc
 * 
 * @author antoinelabeeuw
 *
 */
@ContextConfiguration(classes = { PlatDaoJdbc.class, JdbcTestConfig.class })
@ExtendWith(SpringExtension.class)
@ActiveProfiles({ "jdbc", "test" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJdbcIntegrationTest {
	/** jdbcTemplate */
	@Autowired
	JdbcTemplate jdbcTemplate;
	/** platJdbc */
	@Autowired
	PlatDaoJdbc platJdbc;

	@Test
	public void listerPlatsNonVide() {
		// Doit retourner le contenant de la base de données h2
		List<Plat> plats = platJdbc.listerPlats();
		assertThat(plats).isNotEmpty();
	}

	@Test
	public void ajouterPlatTest() {
		// si on ajoute qqchose, taille doit être +1 par rapport a avant
		platJdbc.ajouterPlat("testTest", 2000);
		List<Plat> plats2 = jdbcTemplate.query("SELECT * FROM PLAT WHERE NOM='testTest'", new PlatRowMapper());
		assertThat(plats2.get(0).getNom()).contains("testTest");
	}

}
