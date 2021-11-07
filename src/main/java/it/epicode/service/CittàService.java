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
import it.epicode.model.Città;

@Service
public class CittàService implements CommandLineRunner {

	@Autowired
	CittàRepository cittaRepository;

	@Override
	public void run(String... args) throws Exception {

	}

	public List<Città> myFindAllUsers() {
		return cittaRepository.findAll();
	}

	public Optional<Città> myFindUserById(Long myId) {
		return cittaRepository.findById(myId);
	}

	public void myInsertCitta(String nome) {
		cittaRepository.save(new Città(nome));
	}

	public List<Città> myFindByCitta(String cittaNome) {
		return cittaRepository.myFindByCitta(cittaNome);
	}
	
	public Page<Città> findAll (Pageable pageable) {
		return cittaRepository.findAll(pageable);
	}
	
	public Page<Città> myFindAllCittàPageSize(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Città> pagedResult = cittaRepository.findAll(paging);
        if(pagedResult.hasContent()) {
      return pagedResult;
      } else {
          return null;
      }
    }
	
	// Paginazione e Ordinamento
    public List<Città> myFindAllCittàPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Città> pagedResult = cittaRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Città>();
        }
    }

 // Ordinamento
    public List<Città> myFindAllCittàSorted() {
        return cittaRepository.findByOrderByNomeDesc();
    }

	

}
