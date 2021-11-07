package it.epicode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.CRUD.EdificioRepository;
import it.epicode.CRUD.PostazioneRepository;
import it.epicode.model.Edificio;
import it.epicode.model.Postazione;
import it.epicode.model.TipoPostazione;

@Service
public class PostazioneService implements CommandLineRunner {

	@Autowired
	EdificioRepository edificioRepository;
	@Autowired
	PostazioneRepository postazioneRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

	public List<Postazione> myFindAllPostazione() {
		return postazioneRepository.findAll();
	}

	public Optional<Postazione> myFindUserById(Long myId) {
		return postazioneRepository.findById(myId);
	}

	public void myInsertPostazione(String codice, String descrizione, int numMaxOcc, TipoPostazione tipo,
			String nomeEdificio) {
		List<Edificio> listEdificio = edificioRepository.myFindByEdificio(nomeEdificio);
		Edificio edificio = listEdificio.get(0);
		postazioneRepository.save(new Postazione(codice, descrizione, numMaxOcc, tipo, edificio));
	}

	public List<Postazione> myFindByTipoECitta(TipoPostazione tipo, String citta) {
		return postazioneRepository.myFindByTipoECitta(tipo, citta);
	}

	public Postazione getPostById(Long id) {
		return postazioneRepository.getById(id);
	}

	public List<Postazione> myFindByTipo(TipoPostazione tipo) {
		return postazioneRepository.myFindByTipo(tipo);
	}

	public Page<Postazione> myFindAllPostazionePageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Postazione> pagedResult = postazioneRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

	// Paginazione e Ordinamento
	public List<Postazione> myFindAllPostazionePageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Postazione> pagedResult = postazioneRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Postazione>();
		}
	}

	// Ordinamento
	public List<Postazione> myFindAllPostazioneSorted() {
		return postazioneRepository.findByOrderByCodiceDesc();
	}

}
