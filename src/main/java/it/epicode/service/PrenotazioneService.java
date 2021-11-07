package it.epicode.service;

import java.time.LocalDate;
import java.time.Period;
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

import it.epicode.CRUD.PostazioneRepository;
import it.epicode.CRUD.PrenotazioneRepository;
import it.epicode.CRUD.UserRepository;
import it.epicode.model.Postazione;
import it.epicode.model.Prenotazione;
import it.epicode.model.User;

@Service
public class PrenotazioneService implements CommandLineRunner {

	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PostazioneRepository postazioneRep;

	@Override
	public void run(String... args) throws Exception {
	}

	public List<Prenotazione> myFindAllPrenotazioni() {
		return prenotazioneRepository.findAll();
	}

	public Optional<Prenotazione> myFindUserById(Long myId) {
		return prenotazioneRepository.findById(myId);
	}

	public List<Prenotazione> myFindByData(LocalDate dataPrenotazione) {
		return prenotazioneRepository.findByData(dataPrenotazione);
	}

	public boolean verificaPostazioneDataEmpty(Long postazioneId, LocalDate data) {
		List<Prenotazione> listaPrenotazioniData = prenotazioneRepository.findByPostazioneEData(postazioneId, data);
		if (listaPrenotazioniData.isEmpty() == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificaPostazioneUserEmpty(User utente, LocalDate data) {
		List<Prenotazione> listaPrenotazioniUtente = prenotazioneRepository.findByUtenteEData(utente.getId(), data);
		if (listaPrenotazioniUtente.isEmpty() == true) {
			return true;
		} else {
			return false;
		}
	}

	public String myInserrPrenotazione(Long userId, Long postazioneId, LocalDate dataPrenotata, LocalDate date2) {

		Period p = Period.between(dataPrenotata, date2);
		if (p.getYears() < 0 && p.getMonths() < 0 && p.getDays() < 2) {
			return ("Non è possibile prenotare con meno di due giorni in anticipo");
		} else {

			if (verificaPostazioneDataEmpty(postazioneId, dataPrenotata) == false) {

				return ("Non è possibile prenotare per la seguente data: " + dataPrenotata.toString());
			} else {
				User user1 = userRepository.getById(userId);
				Postazione post1 = postazioneRep.getById(postazioneId);
				if (verificaPostazioneUserEmpty(user1, dataPrenotata) == true) {
					prenotazioneRepository.save(new Prenotazione(user1, post1, dataPrenotata, date2));
					return ("Salvataggio prenotazione avvenuto.");
				} else {
					return ("Non è possibile effettuare due prenotazioni per una stessa data.");
				}
			}

		}

	}
	
	public Page<Prenotazione> myFindAllPrenotazionePageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Prenotazione> pagedResult = prenotazioneRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

	// Paginazione e Ordinamento
	public List<Prenotazione> myFindAllPrenotazionePageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Prenotazione> pagedResult = prenotazioneRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Prenotazione>();
		}
	}

	// Ordinamento
	public List<Prenotazione> myFindAllPostazioneSorted() {
		return prenotazioneRepository.findByOrderByDataPrenotataDesc();
	}


}
