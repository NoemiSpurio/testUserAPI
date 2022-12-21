package it.prova.userTestAPI.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.userTestAPI.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>{
	
	Optional<Utente> findByUsername(String username);

}
