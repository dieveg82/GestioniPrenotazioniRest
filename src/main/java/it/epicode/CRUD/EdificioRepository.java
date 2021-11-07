package it.epicode.CRUD;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Città;
import it.epicode.model.Edificio;



public interface EdificioRepository extends JpaRepository<Edificio, Long> {

	@Query("SELECT u FROM Edificio u WHERE u.nome =:edificioNome" )
    List<Edificio> myFindByEdificio(String edificioNome);
	
	Page<Edificio> findAll (Pageable pageable);
	
	/* Sort */
    // Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
    List<Edificio> findByOrderByNomeDesc();
}
