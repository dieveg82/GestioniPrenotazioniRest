package it.epicode.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Postazione;
import it.epicode.model.Prenotazione;
import it.epicode.model.TipoPostazione;
import it.epicode.service.PostazioneService;
import it.epicode.service.PrenotazioneService;

@RestController
@RequestMapping("/controllerprenotazione")
public class PrenotazioneController {

	@Autowired
	PrenotazioneService prenotazioneService;
	@Autowired
	PostazioneService postService;
	
	@GetMapping("/prenota")
	@ResponseBody String prenota(@RequestParam Long idUser, @RequestParam Long
			 idPostazione, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPrenotazione) {
		return prenotazioneService.myInserrPrenotazione(idUser, idPostazione , dataPrenotazione, LocalDate.now() );
	}
	
	@GetMapping("/findprenotazioni")
	public List<Prenotazione> myFindAllPrenotazioni() {
		return prenotazioneService.myFindAllPrenotazioni();
	}
	
	@GetMapping("/findprenotazioniByDataPrenotazione")
	List<Prenotazione> findPostazione( @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPrenotazione) {
		return prenotazioneService.myFindByData(dataPrenotazione);
	}
	
	@GetMapping(value = "/findprenotazioneallprenotazioneopagesize", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page myGetAllPrenotazionePageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Prenotazione> prenotazione = prenotazioneService.myFindAllPrenotazionePageSize(page, size);
        return prenotazione;
    }
	
	@GetMapping(value = "/mygetallprenotazionepagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Prenotazione>> myGetAllPrenotazionePageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Prenotazione> list = prenotazioneService.myFindAllPrenotazionePageSizeSort(page, size, sort);
      return new ResponseEntity<List<Prenotazione>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/mygetallprenotazionesortbydataprenotata", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prenotazione> myGetAllprenotazioneSortByDataPrenotata() {
        return prenotazioneService.myFindAllPostazioneSorted();
    }
	
	
}

