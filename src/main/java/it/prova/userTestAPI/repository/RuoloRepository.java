package it.prova.userTestAPI.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.userTestAPI.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {

	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
