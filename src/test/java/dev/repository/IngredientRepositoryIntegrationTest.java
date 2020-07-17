package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JpaTestConfig;
import dev.dto.IngredientDto;

@SpringJUnitConfig(classes = { JpaTestConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("jpa")
public class IngredientRepositoryIntegrationTest {
	@Autowired
	IngredientRepository ir;

	@Test
	public void rechercheNomTest() {
		Optional<IngredientDto> ig = ir.rechercheParNom("Sel");
		assertThat(ig.get().getNom()).isEqualTo("Sel");
	}
}
