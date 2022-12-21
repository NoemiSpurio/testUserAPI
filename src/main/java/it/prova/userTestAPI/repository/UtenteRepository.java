package it.prova.userTestAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.userTestAPI.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{
	
	Optional<Utente> findByUsername(String username);
	
	@Query("from Utente u left join fetch u.ruolo where u.id = ?1")
	Optional<Utente> findByIdConRuoli(Long id);
	
	Utente findByUsernameAndPassword(String username, String password);

}
