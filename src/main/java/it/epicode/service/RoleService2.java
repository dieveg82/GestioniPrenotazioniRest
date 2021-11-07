package it.epicode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import it.epicode.CRUD.RoleRepository;

@Service
public class RoleService2 implements CommandLineRunner {

	@Autowired RoleRepository roleRepository;	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
