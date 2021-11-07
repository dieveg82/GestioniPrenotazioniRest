package it.epicode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Città;
import it.epicode.service.CittàService;

@RestController
@RequestMapping("/controllercitta")
public class CittàController {

	@Autowired
	CittàService cittaService;

	@GetMapping("/findcitta")
	List<Città> findAll() {
		return cittaService.myFindAllUsers();
	}

	@GetMapping("/insertcitta")
	String prenota(@RequestParam String nomeCitta) {
		cittaService.myInsertCitta(nomeCitta);
		return "Citta inserita con successo";
	}

	@GetMapping("/findnome")
	List<Città> findCitta(@RequestParam String nomeCitta) {
		return cittaService.myFindByCitta(nomeCitta);
	}
	
	@GetMapping ("/findcittapage")
	public ResponseEntity<Page<Città>> getAllCittà(Pageable page) {
		Page<Città> findAll = cittaService.findAll(page);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(value = "/findcittaalluserspagesize", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page myGetAllUsersPageSize(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        Page<Città> citta = cittaService.myFindAllCittàPageSize(page, size);
        return citta;
    }
	
	@GetMapping(value = "/mygetallucittapagesizesort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Città>> myGetAllUserPageSizeSort(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
      List<Città> list = cittaService.myFindAllCittàPageSizeSort(page, size, sort);
      return new ResponseEntity<List<Città>>(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@GetMapping(value = "/mygetallcittasortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Città> myGetAllusersSortByName() {
        return cittaService.myFindAllCittàSorted();
    }

}
