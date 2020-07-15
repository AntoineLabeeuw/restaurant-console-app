package dev.service;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

/**
 * Classe de tests de la classe PlatServiceVersion1 Utilise mockito
 * 
 * @see org.mockito.Mockito
 * @author antoinelabeeuw
 *
 */
public class PlatServiceVersion1Test {
	private PlatServiceVersion1 platServiceV1;
	IPlatDao platDao;

	@BeforeEach
	public void setUp() {
		// Setup d'un mock de IPlatDao
		platDao = mock(IPlatDao.class);
		platServiceV1 = new PlatServiceVersion1(platDao);
	}

	@Test
	public void testAjouterPlatNomInvalide() {
		// Nom < 3 lettres, doit renvoyer un PlatException
		Throwable thrown = catchThrowable(() -> {
			platServiceV1.ajouterPlat("no", 2000);
		});
		assertThat(thrown).isInstanceOf(PlatException.class)
				.hasMessageContaining("un plat doit avoir un nom de plus de 3 caractères");
	}

	@Test
	public void testAjouterPlatPrixInvalide() {
		// Prix < 3, doit renvoyer un PlatException
		Throwable thrown = catchThrowable(() -> {
			platServiceV1.ajouterPlat("nom valide", 2);
		});
		assertThat(thrown).isInstanceOf(PlatException.class)
				.hasMessageContaining("le prix d'un plat doit être supérieur à 5 €");
	}

	@Test
	public void testAjouterPlatValide() {
		// Si nom > 3 lettres et Prix > 3 caractères, doit appeler IPlatDao.ajouterPlat
		platServiceV1.ajouterPlat("nomValide", 2000);
		verify(platDao).ajouterPlat("nomValide", 2000);
	}

}