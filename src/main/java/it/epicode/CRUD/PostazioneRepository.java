package it.epicode.CRUD;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Città;
import it.epicode.model.Edificio;
import it.epicode.model.Postazione;
import it.epicode.model.TipoPostazione;
import it.epicode.model.User;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {

	/*
	 * -Un utente può ricercare le postazioni indicando il tipo di postazione
	 * desiderato e la città di interesse.
	 */

	@Query("SELECT u FROM Postazione u WHERE u.tipo = :tipo  AND u.edificio.citta.nome = :cittaNome")
	List<Postazione> myFindByTipoECitta(TipoPostazione tipo, String cittaNome);
	
	@Query("SELECT u FROM Postazione u WHERE u.tipo = :tipo" )
    List<Postazione> myFindByTipo(TipoPostazione tipo);
	
	Page<Postazione> findAll (Pageable pageable);
	
	/* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
    List<Postazione> findByOrderByCodiceDesc();

	

}
