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

import it.epicode.CRUD.UserRepository;
import it.epicode.model.Prenotazione;
import it.epicode.model.User;

@Service
public class UserService implements CommandLineRunner {

	@Autowired UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<User> myFindAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> myFindUserById(Long myId) {
        return userRepository.findById(myId);
    }
    
    public void myInsertUser(String username, String nome, String email, String password, boolean active) {
        userRepository.save(new User(username, nome, email, password, true));
    }

	public List<User> myFindByUser(String username) {
		return userRepository.myFindByUser(username);
	}
	
	public Page<User> myFindAllUserPageSize(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<User> pagedResult = userRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		} else {
			return null;
		}
	}

	// Paginazione e Ordinamento
	public List<User> myFindAllUserPageSizeSort(Integer page, Integer size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<User> pagedResult = userRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}
	}

	// Ordinamento
	public List<User> myFindAllUserSorted() {
		return userRepository.findByOrderByUsernameDesc();
	}
	
}
