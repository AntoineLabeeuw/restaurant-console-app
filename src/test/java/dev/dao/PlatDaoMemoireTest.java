package dev.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

/**
 * Classe de test de la classe PlatDaoMemoire
 * 
 * @author antoinelabeeuw
 *
 */
public class PlatDaoMemoireTest {
	/** platDaoMemoire */
	private PlatDaoMemoire platDaoMemoire;

	/**
	 * SetUp du platDaoMemoire a utiliser pour tous les tests
	 */
	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {
		assertTrue(platDaoMemoire.listerPlats().isEmpty());
	}

	@Test
	void ajouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("JUnit", 2000);
		List<Plat> plats = platDaoMemoire.listerPlats();
		assertEquals(plats.size(),1);
	}
	@Test
	void ajouterPlatAjouterDeux() {
		platDaoMemoire.ajouterPlat("JUnit", 2000);
		platDaoMemoire.ajouterPlat("mot", 1500);
		List<Plat> plats = platDaoMemoire.listerPlats();
		assertEquals(plats.size(),2);
	}
	@Test
	void ajouterPlatListerContientMot() {
		platDaoMemoire.ajouterPlat("mot", 1500);
		List<Plat> plats = platDaoMemoire.listerPlats();
		assertEquals(plats.get(0).getNom(), "mot");
	}
	@Test
	void ajouterPlatListerGetPrix() {
		platDaoMemoire.ajouterPlat("mot", 1500);
		List<Plat> plats = platDaoMemoire.listerPlats();
		assertEquals(plats.get(0).getPrixEnCentimesEuros(), 1500);
	}
}
