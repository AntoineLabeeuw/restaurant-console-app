package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

	@Override
	public List<Plat> listerPlats() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM PLAT", new PlatRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		jdbcTemplate.update("INSERT INTO PLAT (NOM, PRIX) VALUES (?, ?)", nomPlat,prixPlat);
		return;
	}
}
