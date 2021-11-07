package it.epicode.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.CRUD.CittàRepository;
import it.epicode.CRUD.EdificioRepository;
import it.epicode.CRUD.PostazioneRepository;
import it.epicode.CRUD.PrenotazioneRepository;
import it.epicode.CRUD.RoleRepository;
import it.epicode.CRUD.UserRepository;
import it.epicode.model.Città;
import it.epicode.model.Edificio;
import it.epicode.model.Postazione;
import it.epicode.model.Prenotazione;
import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.model.TipoPostazione;
import it.epicode.model.User;

@Component
public class CRUD implements CommandLineRunner {

	@Autowired
	CittàRepository cittàRepository;
	@Autowired
	EdificioRepository edificioRepository;
	@Autowired
	PostazioneRepository postazioneRepository;
	@Autowired
	PrenotazioneRepository prenotazioneRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	PostazioneService postazioneService;
	@Autowired
	PrenotazioneService prenotService;

	@Override
	public void run(String... args) throws Exception {

		Città citta1 = new Città("Firenze");
		Edificio edificio1 = new Edificio("IPERPAN", "via angusto 2", citta1);
		Postazione postazione1 = new Postazione("POST01", "scrivania", 1, TipoPostazione.OPENSPACE, edificio1);
		Role role1 = new Role(RoleType.ROLE_ADMIN);
		User user1 = new User("username3", "pinca pallina", "pinca.pallina@gmail.com", "1234", false);
		user1.getRoles().add(role1);
		// Prenotazione prenotazione1 = new Prenotazione(user1, postazione1,
		// LocalDate.of(2021, 11, 21), LocalDate.now());

		cittàRepository.save(citta1);
		edificioRepository.save(edificio1);
		postazioneRepository.save(postazione1);	
	     roleRepository.save(role1);
		userRepository.save(user1);
//		prenotazioneRepository.save(prenotazione1);

//		System.out.println(userRepository.findById((long) 1));

//		System.out.println(data1);

//		  List<Postazione> postazioneByCittaETipo =  postazioneService.myFindByTipoECitta(TipoPostazione.PRIVATO, citta1.getNome());
//		  postazioneByCittaETipo.forEach(post -> System.out.println("----- "  + post.toString()));
//		 
//		
//		  LocalDate data1= LocalDate.of(2023, 11, 21);
//		  prenotService.myInserrPrenotazione(1L, 1L, data1, LocalDate.now());

	}
}
