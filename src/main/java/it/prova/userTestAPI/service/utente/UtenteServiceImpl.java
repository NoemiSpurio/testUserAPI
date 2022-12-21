package it.prova.userTestAPI.service.utente;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.userTestAPI.model.Utente;
import it.prova.userTestAPI.repository.UtenteRepository;

@Service
@Transactional(readOnly = true)
public class UtenteServiceImpl implements UtenteService{

	@Autowired
	private UtenteRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Utente caricaSingoloUtenteConRuoli(Long id) {
		return repository.findByIdConRuoli(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		// deve aggiornare solo nome, cognome, username, ruoli
		Utente utenteReloaded = repository.findById(utenteInstance.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		utenteReloaded.setNome(utenteInstance.getNome());
		utenteReloaded.setCognome(utenteInstance.getCognome());
		utenteReloaded.setUsername(utenteInstance.getUsername());
		utenteReloaded.setRuolo(utenteInstance.getRuolo());
		repository.save(utenteReloaded);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		utenteInstance.setDataDiNascita(LocalDate.now());
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	public List<Utente> findByExample(Utente example) {
		// TODO Da implementare
		return listAllUtenti();
	}

	public Utente findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	public Utente findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}

	@Override
	public Utente eseguiAccesso(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
