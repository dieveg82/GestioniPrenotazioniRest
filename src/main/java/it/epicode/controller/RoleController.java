package it.epicode.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Prenotazione;
import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.service.RoleService;

@RestController
@RequestMapping("/controllerrole")
public class RoleController {

	@Autowired
	RoleService roleSerrvice;
	
	@GetMapping("/findrole")
	List<Role> findAll() {
		return roleSerrvice.myFindAllRole();
	}
	
	@GetMapping("/findByTipe")
	List<Role> findRole( @RequestParam RoleType type) {
		return roleSerrvice.myFindByType(type);
	}


}
