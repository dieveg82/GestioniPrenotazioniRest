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

import it.epicode.CRUD.CittàRepository;
import it.epicode.CRUD.EdificioRepository;
import it.epicode.model.Città;
import it.epicode.model.Edificio;

@Service
public class EdificioService implements CommandLineRunner {

	@Autowired EdificioRepository edificioRepository;
	@Autowired CittàRepository cittaRepository;
	
	public List<Edificio> myFindAllUsers() {
        return edificioRepository.findAll();
    }
    
    public Optional<Edificio> myFindUserById(Long myId) {
        return edificioRepository.findById(myId);
    }
    
    public void myInsertEdificio(String nome , String indirizzo , String nomeCitta) {
    	List<Città> listCitta = cittaRepository.myFindByCitta(nomeCitta);
    	Città citta = listCitta.get(0);
    	edificioRepository.save(new Edificio(nome , indirizzo , citta));
    }
    
    public List<Edificio> myFindByEdificio(String nome) {
		return edificioRepository.myFindByEdificio(nome);
	}
    
    public Page<Edificio> myFindAllEdificioPageSize(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Edificio> pagedResult = edificioRepository.findAll(paging);
        if(pagedResult.hasContent()) {
      return pagedResult;
      } else {
          return null;
      }
    }
	
	// Paginazione e Ordinamento
    public List<Edificio> myFindAllEdificioPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Edificio> pagedResult = edificioRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Edificio>();
        }
    }

 // Ordinamento
    public List<Edificio> myFindAllEdificioSorted() {
        return edificioRepository.findByOrderByNomeDesc();
    }
    
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
