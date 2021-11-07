package it.epicode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Città;
import it.epicode.model.Edificio;
import it.epicode.service.EdificioService;

@RestController
@RequestMapping("/controlleredificio")
public class EdificioController {

	@Autowired
	EdificioService edificioService;

	@GetMapping("/findedificio")
	List<Edificio> findAll() {
		return edificioService.myFindAllUsers();
	}

	@GetMapping("/insertedificio")
	String insertEdificio(@RequestParam String nomeEdificio, @RequestParam String indirizzo,
			@RequestParam String nomeCitta) {
		edificioService.myInsertEdificio(nomeEdificio, indirizzo, nomeCitta);
		return "Edificio inserito con successo";
	}
	
	@GetMapping("/findedificionome")
	List<Edificio> findEdificio(@RequestParam String nomeEdificio) {
		return edificioService.myFindByEdificio(nomeEdificio);
	}
	
	@GetMapping(value = "/findedificioalledificiopagesize", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page myGetAllEdificioPageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Edificio> edificio = edificioService.myFindAllEdificioPageSize(page, size);
        return edificio;
    }
	
	@GetMapping(value = "/mygetalledificiopagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Edificio>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Edificio> list = edificioService.myFindAllEdificioPageSizeSort(page, size, sort);
      return new ResponseEntity<List<Edificio>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/mygetalledificiosortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Edificio> myGetAllusersSortByName() {
        return edificioService.myFindAllEdificioSorted();
    }

}
