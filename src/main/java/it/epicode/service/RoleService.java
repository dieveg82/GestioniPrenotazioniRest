package it.epicode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import it.epicode.CRUD.RoleRepository;
import it.epicode.model.Città;
import it.epicode.model.Role;
import it.epicode.model.RoleType;

@Service
public class RoleService implements CommandLineRunner {

	@Autowired RoleRepository roleRepository;
	
	public List<Role> myFindAllRole() {
        return roleRepository.findAll();
    }
    
    public Optional<Role> myFindUserById(Long myId) {
        return roleRepository.findById(myId);
    }
    
    public void myInsertCitta(RoleType type) {
    	roleRepository.save(new Role(type));
    }
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<Role> myFindByType(RoleType type) {
		return null;
	}

}
