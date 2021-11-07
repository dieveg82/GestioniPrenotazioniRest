package it.epicode.CRUD;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Città;
import it.epicode.model.Postazione;
import it.epicode.model.TipoPostazione;
import it.epicode.model.User;



public interface CittàRepository extends JpaRepository<Città, Long> {

	@Query("SELECT u FROM Città u WHERE u.nome =:cittaNome" )
    List<Città> myFindByCitta(String cittaNome);
	
	Page<Città> findAll (Pageable pageable);
	
	/* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
    List<Città> findByOrderByNomeDesc();

	



}
