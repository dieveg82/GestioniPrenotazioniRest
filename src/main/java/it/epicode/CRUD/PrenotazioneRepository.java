package it.epicode.CRUD;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Edificio;
import it.epicode.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

	 // -Una prenotazione vale per un solo giorno e puà essere effettuata solo se per
	 // quel giorno la postazione risulta libera.
	 
	@Query("SELECT u FROM Prenotazione u WHERE u.postazione.id = :postazioneId  AND u.dataPrenotata = :data")
	List<Prenotazione> findByPostazioneEData(Long postazioneId, LocalDate data);
	
	 // -Un utente può avere più prenotazioni in corso, ma non può prenotare più
	 // di una postazione per una particolare data.
	@Query("SELECT u FROM Prenotazione u WHERE u.user.id = :id  AND u.dataPrenotata = :data")
	List<Prenotazione> findByUtenteEData(Long id, LocalDate data);

	@Query("SELECT u FROM Prenotazione u WHERE u.dataPrenotata = :data")
	List<Prenotazione> findByData(LocalDate data);

	@Query("SELECT u FROM Prenotazione u WHERE u.postazione.edificio.nome =:nomeEdificio")
	List<Prenotazione> findByCittà(String nomeEdificio);

	Page<Prenotazione> findAll(Pageable pageable);

	/* Sort */
	// Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	List<Prenotazione> findByOrderByDataPrenotataDesc();

}
